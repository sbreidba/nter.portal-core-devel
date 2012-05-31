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

package org.nterlearning.datamodel.catalog.model.impl;

/**
 * The extended model implementation for the Course service. Represents a row in the &quot;CATALOG_Course&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.model.Course} interface.
 * </p>
 *
 */

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.*;
import com.liferay.portal.util.PortalUtil;
//import org.nterlearning.commerce.client.CommerceServiceStub;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.service.*;
import org.nterlearning.utils.DateUtil;
import org.nterlearning.utils.PortalProperties;
import org.nterlearning.utils.PortalPropertiesUtil;
//import org.nterlearning.xml.commerce.domain_objects_0_1_0.PaymentStatus;
//import org.nterlearning.xml.commerce.transaction_interface_0_1_0.GetPaymentStatus;
//import org.nterlearning.xml.commerce.transaction_interface_0_1_0.GetPaymentStatusResponse;
//import org.nterlearning.xml.commerce.transaction_interface_0_1_0_wsdl.TransactionInterface;
//import org.nterlearning.xml.commerce.transaction_interface_0_1_0_wsdl.ValidationError;

import javax.servlet.jsp.PageContext;
import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * The model implementation for the Course service. Represents a row in the &quot;CATALOG_Course&quot; database table,
 * with each column mapped to a property of this class. <p> Helper methods and all application logic should be put in
 * this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link
 * org.nterlearning.datamodel.catalog.model.Course} interface. </p> <p> Never reference this class directly. All methods
 * that expect a course model instance should use the {@link Course} interface instead. </p>
 */
public class CourseImpl extends CourseBaseImpl {

	private List<CourseImage> cachedImages;
	private String searchContext;
	private float searchRelevance;

	public CourseImpl() {

		Calendar maxDate = GregorianCalendar.getInstance();
		maxDate.add(Calendar.YEAR, 1000);

		setAcceptUntilDate(maxDate.getTime());
		setCreateDate(new Date());
		setRemoved(false);
		setReleaseOnDate(new Date(0));
		setUpdatedDate(new Date());
		setVersionDate(new Date());
	}

	public void startSafeImageEnumeration(Locale locale, Locale defaultLocale) {

		try {
			cachedImages = CourseImageLocalServiceUtil
					.findByCourseIdWithLanguage(this.getCourseId(), locale.toString());
			if (cachedImages.size() == 0) {
				cachedImages = CourseImageLocalServiceUtil
						.findByCourseIdWithLanguage(this.getCourseId(), defaultLocale.toString());
			}
			if (cachedImages.size() == 0) {
				cachedImages = CourseImageLocalServiceUtil.findByCourseIdWithLanguage(this.getCourseId(), "");
			}
		} catch (SystemException e) {
			cachedImages.clear();
		}
	}

	public CourseImage getSafeImage(int index) {

		if (index < cachedImages.size()) {
			return cachedImages.get(index);
		} else {
			CourseImage image = new CourseImageImpl();
			return image;
		}
	}

	public int getSafeImageCount() {
		return (cachedImages != null) ? cachedImages.size() : 0;
	}

	public List<String> getAvailableLanguageIds()
			throws SystemException {

		SortedSet<String> languageIds = new TreeSet<String>();

		List<Courses_Components> maps = Courses_ComponentsLocalServiceUtil.findByCourseId(this.getCourseId());
		for (Courses_Components map : maps) {
			Component component = ComponentLocalServiceUtil.fetchByComponentId(map.getComponentId());
			if (component != null) {
				if (!component.isRemoved()) {
					languageIds.add(component.getLanguage());
				}
			}
		}

		return Arrays.asList(languageIds.toArray(new String[0]));
	}

	public List<Contributor> getContributors()
			throws SystemException {

		return CourseLocalServiceUtil.getContributors(this);
	}

	public List<CourseImage> getCourseImages()
			throws SystemException {

		return CourseLocalServiceUtil.getCourseImages(this);
	}

	public List<CourseRelated> getCourseRelateds()
			throws SystemException {

		return CourseLocalServiceUtil.getCourseRelateds(this);
	}

	public List<CourseRequirement> getCourseRequirements()
			throws SystemException {

		return CourseLocalServiceUtil.getCourseRequirements(this);
	}

	public Contributor getCourseAuthor()
			throws SystemException {

		List<Contributor> authors = ContributorLocalServiceUtil.findByCourseIdWithRole(this.getCourseId(), "author");
		return (authors.size() > 0) ? authors.get(0) : null;
	}

	public Contributor getCourseOrganization()
			throws SystemException {

		List<Contributor> organizations = ContributorLocalServiceUtil
				.findByCourseIdWithRole(this.getCourseId(), "organization");
		return (organizations.size() > 0) ? organizations.get(0) : null;
	}

	public String getCourseDomain()
			throws SystemException {

        String href = "";

        // neither href or fulltexthref are required feed elements
        if (!this.getHref().isEmpty()) {
            href = this.getHref();
        }
        else if (!this.getFullTextHref().isEmpty()) {
            href = this.getFullTextHref();
        }
        else {
            try {
                // as a last resort, grab the href from the parent feedReference
                FeedReference feedRef =
                        FeedReferenceLocalServiceUtil.getFeedReference(this.getFeedReferenceId());
                href = feedRef.getHref();
            }
            catch (Exception e) {
                //
            }
        }

		String domain = extractDomainFromContentProvider(href);
		List<Contributor> organizations = ContributorLocalServiceUtil
				.findByCourseIdWithRole(this.getCourseId(), "organization");
		if (organizations.size() > 0) {
			domain = organizations.get(0).getContributorName();
		}
		return domain;
	}

	public static String extractDomainFromContentProvider(String content) {
		String domain = "";
		try {
			URL url = new URL(URLDecoder.decode(content, "utf-8"));
			domain = url.getHost();
		} catch (Exception e) {
			//
		}
		return domain;
	}

	public List<CourseReview> getCourseReviews()
			throws SystemException {

		return CourseLocalServiceUtil.getCourseReviews(this);
	}

	public List<Courses_Components> getCourses_componentses()
			throws SystemException {

		return CourseLocalServiceUtil.getCourses_Componentses(this);
	}

	public String getFriendlyDuration(PageContext pageContext)
			throws DatatypeConfigurationException {

		if (getDuration().equals("")) {
			return LanguageUtil.format(pageContext, "course-duration-unknown", "UnKnown");
		} else {
			try {
				return DateUtil.getFriendlyDuration(pageContext, getDuration());
			} catch (DatatypeConfigurationException dc3) {
				return LanguageUtil.format(pageContext, "course-duration-unknown", "UnKnown");
			}
		}
	}

	public String getFriendlyVersionDate(PageContext pageContext) {

		return DateUtil.getFriendlyDate(pageContext, this.getVersionDate());
	}

	public String getUrl()
			throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(getGroupId());
		String groupFriendlyURL = group.getFriendlyURL();

		// TODO allow for customization of course-details page name.
		return PortalUtil.getPathFriendlyURLPublic() + groupFriendlyURL
                + "/course-details?cid=" + getCourseId();
	}

	public String getUrl(String languageId)
			throws PortalException, SystemException {

		return getUrl() + "&lang=" + languageId;
	}

	/**
	 * Determines the correct name of the owner, regardless of whether or not the owning group is a group, community,
	 * organization, or company. Typically, returning the name of the group will only return the primary key in the
	 * corresponding owner table. Note that if the course is owned by the "Guest" community, a special value is returned.
	 *
	 * @return The name of the owning entity.
	 */
	public String getOwnerName(long themeCompanyId) {

		String ownerName = null;

		try {
			Group guestGroup = GroupLocalServiceUtil.getGroup(themeCompanyId, GroupConstants.GUEST);
			long guestGroupId = guestGroup.getGroupId();

			Group ownerGroup = GroupLocalServiceUtil.getGroup(getGroupId());
			long ownerClassId = ownerGroup.getClassNameId();

			if (getGroupId() == guestGroupId) {
				ownerName = CompanyLocalServiceUtil.getCompany(themeCompanyId).getName();
			} else if (ownerClassId == ClassNameLocalServiceUtil.getClassNameId(Company.class)) {
				ownerName = CompanyLocalServiceUtil.getCompany(ownerGroup.getClassPK()).getName();
			} else if (ownerClassId == ClassNameLocalServiceUtil.getClassNameId(Organization.class)) {
				ownerName = OrganizationLocalServiceUtil.getOrganization(ownerGroup.getClassPK()).getName();
			} else if (ownerClassId == ClassNameLocalServiceUtil.getClassNameId(Group.class)) {
				ownerName = ownerGroup.getName();
			}
		} catch (Exception e) {
			_log.error("Error obtaining owner name for course id: " + this.getPrimaryKey());
			_log.error(e);
		}

		return ownerName;
	}

	public String getOwnerUrl(long themeCompanyId) {
		String ownerUrl = null;

		try {
			Group group = GroupLocalServiceUtil.getGroup(getGroupId());

			Group guestGroup = GroupLocalServiceUtil.getGroup(themeCompanyId, GroupConstants.GUEST);
			long guestGroupId = guestGroup.getGroupId();

			if (getGroupId() == guestGroupId) {
				ownerUrl = PortalUtil.getPathFriendlyURLPublic() + guestGroup.getFriendlyURL() + "/courses";
			} else {
				ownerUrl = PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL();
			}
		} catch (Exception e) {
			_log.error("Error obtaining owner URL for course id: " + this.getPrimaryKey());
			_log.error(e);
		}

		return ownerUrl;
	}

	public boolean isFeatured() {
		return (getFeaturedStatus() > 0);
	}

	public boolean hasNewerVersion() {
		return (!this.getSupersededByCourseIri().equals(""));
	}

	// return newest course, if the newer course isn't found then return this
	public Course getMostRecentVersion()
			throws PortalException, SystemException {

		if (Validator.isNull(this.getCourseId())) {
			return (this);
		} else {
			Course recentCourse = CourseLocalServiceUtil.getCourse(this.getCourseId());

			String recentIri = recentCourse.getSupersededByCourseIri();
			if (!recentIri.equals("")) {
				Course course = CourseLocalServiceUtil.findByCourseIri(recentIri);
				if (course != null) {
					recentCourse = course;
				}
			}
			return (recentCourse);
		}
	}

	public List<Course> getAllVersions()
			throws PortalException, SystemException {

		if (Validator.isNull(this.getCourseId())) {
			return new ArrayList<Course>();
		} else {

			Course recentCourse = this.getMostRecentVersion();

			List<Course> courses = CourseLocalServiceUtil.findBySupersededByCourseIri(recentCourse.getCourseIri());

			// create a list that is mutatable
			List<Course> allCourses = new ArrayList<Course>();
			for (Course course : courses) {
				allCourses.add(course);
			}
			allCourses.add(recentCourse);

			// sort in descending date order
			Collections.sort(allCourses, new Comparator<Course>() {

				public int compare(Course c1, Course c2) {

					return c2.getUpdatedDate().compareTo(c1.getUpdatedDate());
				}
			});
			return allCourses;
		}
	}

	public String getSearchContext() {
		return searchContext;
	}

	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}

	public float getSearchRelevance() {
		return searchRelevance;
	}

	public void setSearchRelevance(float searchRelevance) {
		this.searchRelevance = searchRelevance;
	}

	/**
	 * Determines if the course should be indexed or not.
	 *
	 * @return Returns true if the course should be indexed, false otherwise.
	 */
	public boolean isIndexable() {
		return !this.isRemoved() && this.getSupersededByCourseIri().equals("");
	}

	/**
	 * Performs the appropriate index operations on the course. If the course is
     * new, not removed, and is not superseded by another course, add it to the
     * index. If the course is removed, or superseded by another course, remove
     * it from the index. If the course supersedes another course, ensure the
     * superseded course is removed.
	 *
	 * @throws SystemException - Liferay Exception
	 */
	public void updateIndex()
			throws SystemException  {

        if (!isIndexable()) {
            return;
        }

        try {
            Indexer indexer = IndexerRegistryUtil.getIndexer(Course.class);
            indexer.reindex(this);

            // if this course supersedes another course, remove that original course
            // from the index
            if (!this.getSupersedesCourseIri().equals("")) {
                Course originalCourse = CourseLocalServiceUtil.fetchByCourseIri(
                        this.getSupersedesCourseIri());
                if (originalCourse != null) {
                    indexer.delete(originalCourse);
                }
            }
        }
        catch (SearchException se) {
            _log.error("Could not reindex course: " + this.getCourseIri()
                        + "due to: " + se.getMessage());
        }
        catch (Exception e) {
            _log.error("Could not load indexer for Course Class");
        }
	}

    // TODO temporary method while waiting for e-commerce service to be completed
    public boolean isPurchased(long userId) 	throws SystemException, PortalException {
        return (false);
    }
//	public boolean isPurchased(long userId)
//			throws SystemException, PortalException {
//		if (getPrice() > 0.0) {
//			TransactionInterface transactionInterface;
//			String transactionWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_TRANSACTION_URL);
//			String configurationWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_CONFIGURATION_URL);
//			String entitlementWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_ENTITLEMENT_URL);
//
//			CommerceServiceStub commerceService = new CommerceServiceStub(transactionWsdlURL, configurationWsdlURL,
//					entitlementWsdlURL);
//			transactionInterface = commerceService.getTransactionInterface();
//
//			GetPaymentStatus paymentStatus = new GetPaymentStatus();
//            String studentId =
//                    UserIdMapperLocalServiceUtil.getUserIdMapper(
//                            userId, PortalPropertiesUtil.getSsoImplementation()).getExternalUserId();
//			paymentStatus.setStudentId(studentId);
//			paymentStatus.setCourseId(getCourseIri());
//			paymentStatus.setResourceId("NTER");
//			BigDecimal price = new BigDecimal(getPrice());
//			paymentStatus.setPrice(price.setScale(2, RoundingMode.UP));
//			try {
//				GetPaymentStatusResponse paymentResponse = transactionInterface.getPaymentStatus(paymentStatus);
//				return paymentResponse.getStatus() == PaymentStatus.COMPLETED;
//			} catch (ValidationError e) {
//				throw new SystemException(e);
//			}
//		} else {
//			return true;
//		}
//
//	}

	private static Log _log = LogFactoryUtil.getLog(CourseImpl.class);
}
