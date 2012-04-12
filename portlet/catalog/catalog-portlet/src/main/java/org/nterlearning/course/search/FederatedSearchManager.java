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


package org.nterlearning.course.search;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.search.OpenSearchUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.util.xml.XMLFormatter;
import org.nterlearning.course.util.NterKeys;
import org.nterlearning.crawl.nutch.NutchConstants;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseImage;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.*;


/**
 * The FederatedSearchManager manages the state of a federated search. It tracks
 * total search results, which results to display per page, current search
 * delta, filter, etc.
 *
 * @author bblonski
 */
public class FederatedSearchManager {

	private final boolean dlLinkToViewURL = false;

	// Total number of results across all sources
	private int totalResultsCount;
	// Number of results to display per page
	private int searchDelta;
	// The keywords to search
	private String keywords;
	// List of all searchable portlets
	private List<Portlet> portlets;
	// Search url for pagination
	private PortletURL portletURL;
	// Page context
	private PageContext pageContext;
	// duplicate table for removing dups between local and remote searches
	private Set<Long> courseResultsSet;
    private Set<Long> componentResultsSet;

	// Open search return format
	private String format;
	private long groupId;

	public FederatedSearchManager(List<Portlet> portlets, long groupId,
        PortletURL portletURL, String format, PageContext pageContext) {

		this.portlets = portlets;
		this.keywords = StringPool.BLANK;
		this.searchDelta = 25;
		this.format = null;
		this.groupId = groupId;
		this.portletURL = portletURL;
		this.format = format;
		this.pageContext = pageContext;
		this.courseResultsSet = new HashSet<Long>();
        this.componentResultsSet = new HashSet<Long>();
	}

	public int getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setKeywords(String keywords) {
		if (!StringUtils.equals(this.keywords, keywords)) {
			this.keywords = keywords;
		}
	}

	public void setSearchDelta(int searchDelta) {
		if (this.searchDelta != searchDelta) {
			this.searchDelta = searchDelta;
		}
	}

	/**
	 * Search one portlet source. Returns all results from 0 to the end of the
     * given page.
	 *
	 * @param portletIndex The portlet to search.
	 * @param request The current page request
	 * @param page The page to search to.  Count starts at 1.
	 * @return Returns all results from 0 to the end of the given page.
	 */
	private List<ResultAndScorePair> searchSource(int portletIndex,
        HttpServletRequest request, int page) {

		Portlet portlet = portlets.get(portletIndex);
		OpenSearch openSearch = portlet.getOpenSearchInstance();
		String portletId = portlet.getPortletId();
		Locale locale = request.getLocale();
		List<ResultAndScorePair> resultMap = new ArrayList<ResultAndScorePair>();

		List<Course> validCourses;
		try {
			validCourses = CourseLocalServiceUtil.findAllValidCourses();
		}catch (SystemException e) {
			_log.error("Error finding valid courses", e);
			validCourses = new ArrayList<Course>();
		}

		try {
			// Search pages start count at 1 instead of 0.
			for (int i = 1; i <= page; i++) {

				String xml = openSearch.search(request, groupId, 0, keywords, i, searchDelta, format);
				xml = XMLFormatter.stripInvalidChars(xml);
				Document doc = SAXReaderUtil.read(xml);
				Element root = doc.getRootElement();
				String[] queryTerms = StringUtil.split(root.elementText("queryTerms"), StringPool.COMMA_AND_SPACE);
				List<Element> entries = root.elements("entry");
				int total = GetterUtil.getInteger(
						root.elementText(OpenSearchUtil.getQName("totalResults", OpenSearchUtil.OS_NAMESPACE)));

				for (Element el : entries) {
					try {

						// Remove items from inactive groups
						if(isEntryInactiveGroup(el)
                            || isEntryUnreleasedArticle(el, portletId)
                            || isEntryInvalidCourse(el, portletId, validCourses)
                            || isEntryInvalidComponent(el, portletId)) {
							_log.debug("Result not valid");
							total--;
							continue;
						}

						Double searchScore = GetterUtil.getDouble(
								(el.elementText(OpenSearchUtil.getQName("score", OpenSearchUtil.RELEVANCE_NAMESPACE))));
                        if (searchScore == 0.0) {
						    searchScore = GetterUtil.getDouble((el.elementText("score")));
                        }

						OpenSearchResult result = getResultFromElement(el, request, portletId, locale);

						if(result == null) {
							total--;
						}else {
							resultMap.add(new ResultAndScorePair(result, searchScore));
						}
					} catch (Exception e) {
                        total--;
						_log.error("Error storing entry of type: " + portlet.getOpenSearchClass(), e);
					}
				}
				totalResultsCount += total;
			}
		}
        catch (IllegalArgumentException iae) {
            _log.error(iae.getMessage());
        }
        catch (Exception e) {
			_log.error("Error displaying content of type " + portlet.getOpenSearchClass());
		}
		return resultMap;
	}


    /**
     * Determines if an entry represents an invalid course.  This returns true
     * only if the entry represents a course (based on the value of EntryClass)
     * and it either does not exist on the local system, or it is not a valid
     * course.  Otherwise, false is returned.
     *
     * @param el The result element (may or may not represent a course)
     * @param portletId The search portlet that generated the result
     * @param validCourses A list of valid courses
     *
     * @return true if element is an invalid course.
     */
	private boolean isEntryInvalidCourse(Element el, String portletId,
                                         List<Course> validCourses) {

        String elementClass = el.elementText(NutchConstants.CLASS_INDEX_TAG);

        if (elementClass.equals(Course.class.getName())) {
            String id = null;
            Course course;
            try {
                if (portletId.equals(NterKeys.COURSE_SEARCH_PORTLET)) {
                    id = el.elementText(NutchConstants.CLASS_PK_TAG);
                    course = CourseLocalServiceUtil.getCourse(Long.valueOf(id));
                }
                else {
                    id = el.elementText(NutchConstants.IRI_INDEX_TAG);
                    course = CourseLocalServiceUtil.fetchByCourseIri(id);
                }

                return !validCourses.contains(course);
            }
            catch (Exception e) {
                _log.error("Could not find course with id/iri of [" + id + "]");
                return true;
            }
        }
        else {
            return false;
        }
	}


    /**
     * Determines if an entry represents an invalid component.  This returns
     * true only if the entry represents a component and it is either removed
     * (deleted) or the user does not have authorization to search for
     * components.  If the entry does not represent a component, then false is
     * returned.
     *
     * @param el The result element (may or may not represent a component)
     * @param portletId The search portletId
     *
     * @return true if element is an invalid component, or if the user does not
     * have search rights.
     */
    private boolean isEntryInvalidComponent(Element el, String portletId) {

        String elementClass = el.elementText(NutchConstants.CLASS_INDEX_TAG);

        if (elementClass.equals(Component.class.getName())) {
            Component component;
            String id = null;
            try {
                if (portletId.equals(NterKeys.COMPONENT_SEARCH_PORTLET)) {
                    id = el.elementText(NutchConstants.CLASS_PK_TAG);
                    component = ComponentLocalServiceUtil.getComponent(Long.valueOf(id));
                }
                else {
                    id = el.elementText(NutchConstants.IRI_INDEX_TAG);
                    component = ComponentLocalServiceUtil.fetchByComponentIri(id);
                }

                if (component.isRemoved()) {
                    return true;
                }
            }
            catch (Exception e) {
                _log.error("Could not find component with id/iri of [" + id + "]");
                return true;
            }

            return !ComponentOpenSearchImpl.isSearchAuthorized();
        }
        else {
            return false;
        }
    }


	private boolean isEntryUnreleasedArticle(Element el, String portletId)
			throws SystemException, PortalException {

		if (!portletId.equals(PortletKeys.JOURNAL)) {
			return false;
		}

		String articleId = el.elementText(
				OpenSearchUtil.getQName(Field.ENTRY_CLASS_PK, OpenSearchUtil.LIFERAY_NAMESPACE));
		long entryGroupId = GetterUtil.getLong(
				el.elementText(OpenSearchUtil.getQName("groupId", OpenSearchUtil.LIFERAY_NAMESPACE)));
		JournalArticle article = JournalArticleLocalServiceUtil.getArticle(entryGroupId, articleId);

		return (DateUtil.compareTo(article.getDisplayDate(), new Date()) > 0);
	}

	private boolean isEntryInactiveGroup(Element el) {
		long entryGroupId = GetterUtil.getLong(
				el.elementText(OpenSearchUtil.getQName("groupId", OpenSearchUtil.LIFERAY_NAMESPACE)));
		if (Validator.isNull(entryGroupId)) {
			return false;
		}

        try {
		    Group entryGroup = GroupServiceUtil.getGroup(entryGroupId);
		    return !entryGroup.isActive();
        }
        catch (Exception e) {
            _log.error(e.getMessage());
            return true;
        }
	}

	/**
	 * Formats a result element into an html string for display in a ResultRow.
	 *
	 * @param el Element to format
	 * @param request Servlet request
	 * @param portletId Portlet Id
	 * @param locale The current display locale
	 * @return HTML string formatted
	 * @throws javax.portlet.PortletException
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 */
	private OpenSearchResult getResultFromElement(Element el,
        HttpServletRequest request, String portletId, Locale locale)
			throws PortletException, PortalException, SystemException {

        // which search request issued this query?
        String primarySearch = request.getParameter("primarySearch");

		// Summary
		String entryClassName = el.elementText("entryClassName");
		long entryClassPK = GetterUtil.getLong(el.elementText("entryClassPK"));
        String entryClassIri = null;
		String entryTitle = el.elementText("title");
		String summary = el.elementText("summary");
		String entryHref = (el.element("link") != null) ? el.element("link").attributeValue("href") : null;

		if (entryHref == null) {
			entryHref = el.elementText("link");
		}

		if (portletId.equals(NterKeys.EXTERNAL_SEARCH_PORTLET)) {
			entryClassIri = el.elementText(el.getQName(NutchConstants.IRI_INDEX_TAG)).trim();

            if (processCourseResult(primarySearch, entryClassName)) {
                try {
                    Course course = CourseLocalServiceUtil.findByCourseIri(entryClassIri);
                    entryClassPK = course.getPrimaryKey();
                    summary = StringUtil.shorten(course.getDescription(locale), 200);
                    entryHref = course.getUrl();

                    // prevent duplication between external and local search
                    if (!courseResultsSet.add(entryClassPK)) {
                        return null;
                    }
                }
                catch (Exception e) {
                    _log.error("Could not find course with IRI: [" + entryClassIri + "]");
                }
            }
            else if (processComponentResult(primarySearch, entryClassName)) {
                try {
                    if (!ComponentOpenSearchImpl.isSearchAuthorized()) {
                        return null;
                    }

                    Component component = ComponentLocalServiceUtil.fetchByComponentIri(entryClassIri);
                    entryClassPK = component.getComponentId();
                    summary = StringUtil.shorten(component.getDescription(), 200);
                    entryHref = component.getUrl();

                    // prevent duplication between external and local search
                    if (!componentResultsSet.add(entryClassPK)) {
                        return null;
                    }
                }
                catch (Exception e) {
                    _log.error("Could not find component with IRI: [" + entryClassIri + "]");
                }
            }
            else {
                // should never happen, but used to prevent future exceptions
                return null;
            }
		}

		double rating = 0.0;
		long[] categoryIds = {};
		long entryGroupId = GetterUtil
				.getLong(el.elementText(OpenSearchUtil.getQName("groupId", OpenSearchUtil.LIFERAY_NAMESPACE)));

		// Localize courses display
		if (portletId.equals(NterKeys.COURSE_SEARCH_PORTLET)) {
			// add to duplicates for filtering later
			courseResultsSet.add(entryClassPK);
			entryTitle = LocalizationUtil.getLocalization(entryTitle, LanguageUtil.getLanguageId(locale));
			summary = LocalizationUtil.getLocalization(summary, LanguageUtil.getLanguageId(locale));
		}

        if (portletId.equals(NterKeys.COMPONENT_SEARCH_PORTLET)) {
            componentResultsSet.add(entryClassPK);
			entryTitle = LocalizationUtil.getLocalization(entryTitle, LanguageUtil.getLanguageId(locale));
			summary = LocalizationUtil.getLocalization(summary, LanguageUtil.getLanguageId(locale));
        }

		// Format Document Library Entries
		if (portletId.equals(PortletKeys.DOCUMENT_LIBRARY) ||
                (portletId.equals(PortletKeys.SEARCH) &&
                        entryClassName.equals(DLFileEntry.class.getName()))) {
			long folderId = GetterUtil.getLong(HttpUtil.getParameter(entryHref, "_20_folderId", false));
			String name = GetterUtil.getString(HttpUtil.getParameter(entryHref, "_20_name", false));

			DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(entryGroupId, folderId, name);

			entryTitle = fileEntry.getTitle();

			if (portletId.equals(PortletKeys.SEARCH)) {
				entryTitle =
						PortalUtil.getPortletTitle(PortletKeys.DOCUMENT_LIBRARY, locale) + " " + CharPool.RAQUO + " "
								+ entryTitle;
			}

			if (dlLinkToViewURL) {
				long dlPlid = PortalUtil.getPlidFromPortletId(fileEntry.getGroupId(), PortletKeys.DOCUMENT_LIBRARY);

				PortletURL viewURL = PortletURLFactoryUtil
						.create(request, PortletKeys.DOCUMENT_LIBRARY, dlPlid, PortletRequest.RENDER_PHASE);

				viewURL.setParameter("struts_action", "/document_library/view_file_entry");
				viewURL.setParameter("redirect", portletURL.toString());
				viewURL.setParameter("folderId", String.valueOf(fileEntry.getFolderId()));
				viewURL.setParameter("name", HtmlUtil.unescape(name));

				entryHref = viewURL.toString();
			}
		}

		// Format Tags
		String tagsString = el.elementText("tags");
		tagsString = Validator.isNotNull(tagsString) ? tagsString.replaceAll("[\\[\\]]", "") : StringPool.BLANK;

		// Ratings
		rating = GetterUtil.getDouble(el.elementText("ratings"));

		OpenSearchResult result = new OpenSearchResult(portletId, entryTitle, summary, entryHref, rating, tagsString,
				categoryIds, entryGroupId, keywords, entryClassName);

		if (Validator.isNotNull(entryClassName) && Validator.isNotNull(entryClassPK)) {
			try {
				result.setStats(entryClassName, entryClassPK, pageContext);
                result.classIri = entryClassIri;

				if (entryClassName.equals(Course.class.getName())) {
                    ThemeDisplay themeDisplay = (ThemeDisplay) pageContext.getRequest()
                            .getAttribute(WebKeys.THEME_DISPLAY);
					Course course = CourseLocalServiceUtil.getCourse(entryClassPK);
					result.setOwner(course.getOwnerName(themeDisplay.getCompanyId()),
							course.getOwnerUrl(themeDisplay.getCompanyId()));

					course.startSafeImageEnumeration(locale, locale);
					if (course.getSafeImageCount() > 0) {
                        CourseImage image = course.getSafeImage(0);
                        result.setImage(image.getSmallImageUrl(themeDisplay), image.getAlternateText());
					}
				}
			} catch (Exception e) {
				_log.warn("Unable to process course.  Not displaying result.");
			}
		}

		return result;
	}

	/**
	 * Search all portlet sources and return a list of result lists. Returns all
     *  results up to end of given page number.
	 *
	 * @param request
	 * @param page page to search to
	 * @return List of all ResultAndScorePair lists.
	 */
	public List<List<ResultAndScorePair>> getAllSearchResults(HttpServletRequest request, int page) {

		List<List<ResultAndScorePair>> allResults = new ArrayList<List<ResultAndScorePair>>();
		totalResultsCount = 0;
		for (int portletIndex = 0; portletIndex < portlets.size() && Validator.isNotNull(keywords); portletIndex++) {
			allResults.add(portletIndex, searchSource(portletIndex, request, page));
		}
		return allResults;
	}

	public List<OpenSearchResult> getPageResults(HttpServletRequest request, int page) {

		List<List<ResultAndScorePair>> allResults = getAllSearchResults(request, page);
		List<OpenSearchResult> displayResults = new ArrayList<OpenSearchResult>();

		for (int searchIndex = 0; searchIndex < searchDelta * page; searchIndex++) {
			double maxSearchScore = -1;
			int chosenPortlet = -1;
			for (int portletIndex = 0; portletIndex < allResults.size(); portletIndex++) {
				List<ResultAndScorePair> list = allResults.get(portletIndex);
				if (list.isEmpty()) {
					continue;
				}
				double searchScore = list.get(0).getSearchScore();
				if (searchScore > maxSearchScore) {
					maxSearchScore = searchScore;
					chosenPortlet = portletIndex;
				}
			}
			if (chosenPortlet != -1 && searchIndex < searchDelta * page - searchDelta) {
				ResultAndScorePair tmp = allResults.get(chosenPortlet).remove(0);
				if(_log.isDebugEnabled()) {
					_log.debug("Removed " + tmp.toString() + "from results");
				}
			} else if (chosenPortlet != -1) {
				if(_log.isDebugEnabled()) {
					_log.debug("Adding top result from portlet " + portlets.get(chosenPortlet).toString());
				}
				displayResults.add(allResults.get(chosenPortlet).remove(0).getResultRow());
			}
		}
		return displayResults;
	}


    /**
     * Returns true if the result is from either a global search or a course
     * specific search and if the result represents a course object.
     *
     * @param primarySearch Null if a global search, the opensearch impl otherwise
     * @param entryClass result's class
     *
     * @return True if the result should be processes as a course, false otherwise
     */
    private boolean processCourseResult(String primarySearch, String entryClass) {
        return ((primarySearch == null) ||
                (primarySearch.equals(CourseOpenSearchImpl.class.getName()))) &&
                entryClass.equals(Course.class.getName());
    }


    /**
     * Returns true if the result is from either a global search or a
     * component specific search and if the result represents a component object.
     *
     * @param primarySearch Null if a global search, the opensearch impl otherwise
     * @param entryClass result's class
     *
     * @return True if the result should be processes as a component,
     * false otherwise
     */
    private boolean processComponentResult(String primarySearch, String entryClass) {
        return ((primarySearch == null) ||
                (primarySearch.equals(ComponentOpenSearchImpl.class.getName()))) &&
                entryClass.equals(Component.class.getName());
    }

	private static final Log _log = LogFactoryUtil.getLog(FederatedSearchManager.class);
}