package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Course service. Represents a row in the &quot;CATALOG_Course&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CourseModel
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseModelImpl
 * @generated
 */
public interface Course extends CourseModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link org.nterlearning.datamodel.catalog.model.impl.CourseImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void startSafeImageEnumeration(java.util.Locale locale,
        java.util.Locale defaultLocale);

    public org.nterlearning.datamodel.catalog.model.CourseImage getSafeImage(
        int index);

    public int getSafeImageCount();

    public java.util.List<java.lang.String> getAvailableLanguageIds()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements()
        throws com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Contributor getCourseAuthor()
        throws com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Contributor getCourseOrganization()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getCourseDomain()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_componentses()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getFriendlyDuration(
        javax.servlet.jsp.PageContext pageContext)
        throws javax.xml.datatype.DatatypeConfigurationException;

    public java.lang.String getFriendlyVersionDate(
        javax.servlet.jsp.PageContext pageContext);

    public java.lang.String getUrl()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getUrl(java.lang.String languageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Determines the correct name of the owner, regardless of whether or not the owning group is a group, community,
    * organization, or company. Typically, returning the name of the group will only return the primary key in the
    * corresponding owner table. Note that if the course is owned by the "Guest" community, a special value is returned.
    *
    * @return The name of the owning entity.
    */
    public java.lang.String getOwnerName(long themeCompanyId);

    public java.lang.String getOwnerUrl(long themeCompanyId);

    public boolean isFeatured();

    public boolean hasNewerVersion();

    public org.nterlearning.datamodel.catalog.model.Course getMostRecentVersion()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> getAllVersions()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getSearchContext();

    public void setSearchContext(java.lang.String searchContext);

    public float getSearchRelevance();

    public void setSearchRelevance(float searchRelevance);

    /**
    * Determines if the course should be indexed or not.
    *
    * @return Returns true if the course should be indexed, false otherwise.
    */
    public boolean isIndexable();

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
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean isPurchased(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}
