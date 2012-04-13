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

package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import org.nterlearning.course.enumerations.ContributorRoleType;
import org.nterlearning.crawl.nutch.CrawlTool;
import org.nterlearning.datamodel.catalog.NoSuchComponentException;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.service.ContributorLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.ComponentLocalServiceBaseImpl;
import org.nterlearning.datamodel.catalog.service.persistence.ComponentFinderUtil;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * The implementation of the component local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.ComponentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.ComponentLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil
 */
public class ComponentLocalServiceImpl extends ComponentLocalServiceBaseImpl {

    private static Log mLog = LogFactoryUtil.getLog(ComponentLocalServiceImpl.class);


    @Override
    public Component addComponent(Component component) throws SystemException {
        long id = counterLocalService.increment(Component.class.getName());
        component.setPrimaryKey(id);

        // this is needed because <0.6.3, components were not automatically
        // assigned to a group
        if (component.getGroupId() == 0) {
            FeedReference feedRef =
                    feedReferencePersistence.fetchByPrimaryKey(component.getFeedReferenceId());
            component.setGroupId(feedRef.getGroupId());
        }

        component.updateIndex();

        return super.addComponent(component);
    }


    public Component addComponent(long companyId, long feedReferenceId,
            String componentIRI, String description, String title, String href,
            String lang, Date updateDate, int displayHeight, int displayWidth,
            String version, Date versionDate, double price, String priceUnit,
            String priceTerms, String priceExpiration)
            throws SystemException {

        FeedReference feedRef =
                feedReferencePersistence.fetchByPrimaryKey(feedReferenceId);

        return addComponent(companyId, feedRef.getGroupId(), feedReferenceId,
                componentIRI, description, title, href, null, lang, updateDate,
                displayHeight, displayWidth, version, versionDate, price,
                priceUnit, priceTerms, priceExpiration);
    }


    public Component addComponent(long companyId, long groupId, long feedReferenceId,
            String componentIRI, String description, String title, String href,
            String fullTextHref, String lang, Date updateDate, int displayHeight,
            int displayWidth, String version, Date versionDate, double price,
            String priceUnit, String priceTerms, String priceExpiration)
            throws SystemException {

        long componentId = counterLocalService.increment(Component.class.getName());

        Component component = componentPersistence.create(componentId);
        component.setCompanyId(companyId);
        component.setGroupId(groupId);
        component.setFeedReferenceId(feedReferenceId);
        component.setComponentIri(componentIRI);
        component.setHref(href);
        component.setFullTextHref(fullTextHref);
        component.setLanguage(lang);
        component.setTitle(title);
        component.setDescription(description);
        component.setDisplayHeight(displayHeight);
        component.setDisplayWidth(displayWidth);
        component.setCreateDate(new Date());
        component.setUpdatedDate(updateDate);
        component.setRemoved(false);
        // do not set removedDate
        component.setVersion(version);
        component.setVersionDate(versionDate);
        component.setPrice(price);
        component.setPriceUnit(priceUnit);
        component.setPriceTerms(priceTerms);
        component.setPriceExpiration(priceExpiration);

        component.updateIndex();

        return super.addComponent(component);
    }


    public void deleteComponent(Component component)
            throws PortalException, SystemException {

        deleteAllChildren(component);

        // remove from index
        Indexer indexer = IndexerRegistryUtil.getIndexer(Component.class);
        indexer.delete(component);
        removeComponentFromNutchIndex(component);

        componentPersistence.remove(component);
    }


    public void deleteComponent(long componentId)
            throws PortalException, SystemException {

        Component component = componentPersistence.findByPrimaryKey(componentId);
        deleteComponent(component);
    }


    public void deleteAllChildren(Component component)
            throws PortalException, SystemException {

        // remove contributors
        for (Contributor contributor : componentPersistence.getContributors(component.getComponentId())) {
            ContributorLocalServiceUtil.deleteContributor(contributor);
        }

        // remove external links
        externalLinkPersistence.removeByComponentId(component.getComponentId());
    }


    @Override
    public Component updateComponent(Component component, boolean merge)
            throws SystemException {

        component.updateIndex();

        if (component.isRemoved()) {
            removeComponentFromNutchIndex(component);
        }

        component.setNew(false);
        return super.updateComponent(component, merge);
    }


    @Override
    public Component updateComponent(Component component)
            throws SystemException {

        component.updateIndex();

        if (component.isRemoved()) {
            removeComponentFromNutchIndex(component);
        }

        component.setNew(false);
        return super.updateComponent(component);
    }


    /**
     * Clears the cache for all components stored in this session.  This
     * should only be needed in a multi-threaded environment, where a thread is
     * not notified of persistence updates done in a different thread.
     */
    public void clearCache() {
        componentPersistence.clearCache();
    }


    /**
     * Clears the cache for all components stored in this session.  This
     * should only be needed in a multi-threaded environment, where a thread is
     * not notified of persistence updates done in a different thread.
     *
     * @param component Component to remove from the cache
     */
    public void clearCache(Component component) {
        componentPersistence.clearCache(component);
    }


    /**
     * Clears the cache for all components stored in this session.  This
     * should only be needed in a multi-threaded environment, where a thread is
     * not notified of persistence updates done in a different thread.
     *
     * @param componentId Id of component to remove from cache
     */
    public void clearCache(Long componentId) {
        try {
            Component component = componentPersistence.fetchByComponentId(componentId);
            clearCache(component);
        }
        catch (Exception e) {
            mLog.error("Could not find component with id: " + componentId);
        }
    }


    public List<Component> findByCompanyId(long companyId)
            throws SystemException {
        return componentPersistence.findByCompanyId(companyId);
    }


    public Component findByComponentId(long componentId)
            throws NoSuchComponentException, SystemException {
        return componentPersistence.findByComponentId(componentId);
    }


    public Component fetchByComponentId(long componentId)
            throws SystemException {
        return componentPersistence.fetchByComponentId(componentId);
    }


    public Component findByComponentIri(String componentIri)
            throws NoSuchComponentException, SystemException {
        return componentPersistence.findByComponentIri(componentIri);
    }


    public List<Component> findByFeedReferenceId(Long feedRefId) throws SystemException {
        return componentPersistence.findByFeedReferenceId(feedRefId);
    }


    public Component fetchByComponentIri(String componentIri) throws SystemException {
        return componentPersistence.fetchByComponentIri(componentIri);
    }


    public List<Contributor> getAuthors(Component component) throws SystemException {
        return contributorPersistence.
                findByComponentIdWithRole(component.getComponentId(),
                                          ContributorRoleType.AUTHOR.value());
    }


    public List<Contributor> getContributors(Component component)
            throws SystemException {
        return componentPersistence.getContributors(component.getComponentId());
    }


    public List<Contributor> getContributors(long componentPrimaryKey)
            throws SystemException {
        return componentPersistence.getContributors(componentPrimaryKey);
    }


    public List<ComponentRecord> getComponentRecords(Component component)
            throws SystemException {
        return componentPersistence.getComponentRecords(component.getComponentId());
    }


    public List<Courses_Components> getCourses_Componentses(Component component)
            throws SystemException {
        return componentPersistence.getCourses_Componentses(component.getComponentId());
    }


    public List<ExternalLink> getExternalLinks(Component component) throws SystemException {
        return componentPersistence.getExternalLinks(component.getComponentId());
    }


    public List<ExternalLink> getExternalLinks(long componentId) throws SystemException {
        return componentPersistence.getExternalLinks(componentId);
    }


    public List<Object[]> findByCourseIdLanguageSorted(long courseId, Locale locale,
            int start, int end) throws SystemException {
        return ComponentFinderUtil.findByCourseIdLanguageSorted(courseId, locale, start, end);
    }


    private void removeComponentFromNutchIndex(Component component) {
        if (CrawlTool.getInstance().isMaster()) {
            CrawlTool.getInstance().removeFromIndex(component);
        }
    }
}
