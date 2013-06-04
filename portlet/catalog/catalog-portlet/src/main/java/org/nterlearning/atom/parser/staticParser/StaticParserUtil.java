/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.nterlearning.atom.parser.staticParser;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.atom.parser.FeedContext;
import org.nterlearning.atom.parser.push.PubSubHubbubSubscriber;
import org.nterlearning.atom.parser.push.PushKeys;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.ExternalLink;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;
import org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.ExternalLinkLocalServiceUtil;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class StaticParserUtil {

    private static final Log mLog = LogFactoryUtil.getLog(StaticParserUtil.class);


    /**
     * Detects any Pubsubhubbub link entries in a feed.  If present, ensures
     * that the hubs are all subscribed to.
     *
     * @param feed Atom Feed potentially containing hub links
     * @param fc FeedContext object associated with the feed
     * @param feedReference FeedReference object based on the Atom Feed
     *
     * @return An updated feedReference object containing the hubs and updated
     *         subscription status (if applicable).
     */
    public static FeedReference processHubs(Feed feed, FeedContext fc,
            FeedReference feedReference) {

        List<Link> hubLinks = feed.getLinks(PushKeys.PUSH_HUB_LINK_REL_ATTR_VAL);
        StringBuffer feedHubLinks = new StringBuffer("");
        Boolean subscribeSuccess = false;

        // if there are any push hubs, attempt to subscribe to them.
        if (hubLinks.size() > 0) {
            for (Link hubLink : hubLinks) {
                try {
                    int statusCode =
                            PubSubHubbubSubscriber.getInstance().subscribe(
                                    hubLink.getHref().toString(), fc.getFeedUrl());
                    subscribeSuccess = (statusCode == HttpServletResponse.SC_NO_CONTENT) ||
                            (statusCode == HttpServletResponse.SC_ACCEPTED) ||
                            subscribeSuccess;
                }
                catch (Exception e) {
                    mLog.error("Unable to subscribe to hub: " +
                            hubLink.getHref().toString() + ".  " + e.getMessage());
                }

                feedHubLinks.append(hubLink.getHref().toString());
                if (hubLinks.size() > 1) {
                    feedHubLinks.append(",");
                }
            }
        }

        feedReference.setPshbSubscribed(subscribeSuccess);
        feedReference.setPshb(feedHubLinks.toString());

        return feedReference;
    }


    /**
     * Retrieves all courses in the database that appear in the given feed.  If a
     * course is not found in the database, but exists in the feed, then a Course
     * is still added to the returned list, but only the course IRI will be set.
     *
     * @param feed Feed containing course entries
     *
     * @return List of course objects based on the course entries found in feed
     */
    public static List<Course> retrieveCourses(Feed feed) {

        List<Course> courses = new Vector<Course>();
        for (Entry entry : feed.getEntries()) {
            try {
                Course course =
                        CourseLocalServiceUtil.fetchByCourseIri(entry.getId().toString());
                if (course == null) {
                    course = new CourseImpl();
                    course.setCourseIri(entry.getId().toString());
                }

                courses.add(course);
            }
            catch (Exception e) {
                mLog.error("Could not find course [" +entry.getId().toString() +
                            " ] in database.");
            }
        }

        return courses;
    }


    /**
     * Compares the list of courses in the feed with those already found in the
     * database.  If a course was previously found in this feed, but is now
     * missing from the feed, it will be marked as removed.
     *
     * @param entries List of Course entry objects found in the feed
     * @param fc The FeedContext for the given feed
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay Exception
     */
    public static void removeMissingCourseEntries(List<Entry> entries, FeedContext fc)
            throws SystemException {

        List<Course> dbCourses =
                CourseLocalServiceUtil.findByFeedReferenceId(fc.getFeedReferenceId());
        Set entrySet = new HashSet();

        for (Entry entry : entries) {
            entrySet.add(entry.getId().toString());
        }

        for (Course course : dbCourses) {
            if (!entrySet.contains(course.getCourseIri())) {
                course.setRemoved(true);
                course.setRemovedDate(new Date());
                CourseLocalServiceUtil.updateCourse(course, true);
            }

            // if the feed contains a course component that was previously
            // missing but is now included (so readded to the feed), then the
            // updateParserCourse routine will un-remove it.
        }
    }


    /**
     * Compares the list of course components in the feed with those already
     * found in the database.  If a course component was previously found in
     * this feed, but is now missing, it will be marked as removed.
     * <p/>
     * Note that this should only be run for feeds that provide a complete
     * listing every time they are processed.
     *
     * @param entries List of CourseComponent entry objects found in the feed
     * @param fc The FeedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay Exception
     */
    public static void removeMissingCourseComponentEntries(List<Entry> entries, FeedContext fc)
            throws SystemException {

        List<Component> dbComponents =
                ComponentLocalServiceUtil.findByFeedReferenceId(fc.getFeedReferenceId());
        Set entrySet = new HashSet();

        for (Entry entry : entries) {
            entrySet.add(entry.getId().toString());
        }

        for (Component component : dbComponents) {
            if (!entrySet.contains(component.getComponentIri())) {
                component.setRemoved(true);
                component.setRemovedDate(new Date());
                ComponentLocalServiceUtil.updateComponent(component, true);
            }

            // if the feed contains a course component that was previously
            // missing but is now included (so readded to the feed), then the
            // updateCourseComponent routine will un-remove it.
        }
    }


    /**
     * Persists a list of download links for either a course or course-
     * component.  During processing, any missing links are also removed.
     *
     * @param courseId Id of the course to add the links to.  If 0, then it is
     * assumed that the links are for a course-component.
     * @param componentId Id of the component to add the links to.  If 0, then
     * it is assumed that the links are for a course.
     * @param downloadLinks List of download links from the entry.
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - Liferay DBO exception
     */
    public static void persistExternalDownloadLinks(long courseId, long componentId,
            List<Link> downloadLinks)
            throws SystemException {

        List<ExternalLink> existingDLLinks;
        List<ExternalLink> removedLinks;
        String linkHref;

        if (courseId != 0) {
            existingDLLinks = ExternalLinkLocalServiceUtil.findByCourseId(courseId);
            removedLinks = existingDLLinks;
        }
        else if (componentId != 0) {
            existingDLLinks = ExternalLinkLocalServiceUtil.findByComponentId(componentId);
            removedLinks = existingDLLinks;
        }
        else {
            throw new SystemException("Either courseId or componentId must be non-zero");
        }

        for (Link link : downloadLinks) {
            Boolean newLink = true;
            linkHref = link.getHref().toString();

            for (ExternalLink existingLink : existingDLLinks) {
                // if the mime-type and url are the same, keep it
                // if url is the same, but with a new mime-type, update it
                if (link.getAttributeValue("type").equals(existingLink.getLinkType()) &&
                        linkHref.equals(existingLink.getLinkUrl())) {
                    removedLinks.remove(existingLink);

                    newLink = false;
                    break;
                }
                else if (linkHref.equals(existingLink.getLinkUrl())) {
                    existingLink.setLinkType(link.getAttributeValue("type"));
                    ExternalLinkLocalServiceUtil.updateExternalLink(existingLink);
                    removedLinks.remove(existingLink);

                    newLink = false;
                    break;
                }
            }

            if (newLink) {
                ExternalLink externalLink = new ExternalLinkImpl();
                externalLink.setNew(true);
                externalLink.setLinkType(link.getAttributeValue("type"));
                externalLink.setLinkUrl(linkHref);
                externalLink.setCourseId(courseId);
                externalLink.setComponentId(componentId);

                ExternalLinkLocalServiceUtil.addExternalLink(externalLink);
            }
        }

        // remove missing download links
        for (ExternalLink removedLink : removedLinks) {
            ExternalLinkLocalServiceUtil.deleteExternalLink(removedLink);
        }
    }
}
