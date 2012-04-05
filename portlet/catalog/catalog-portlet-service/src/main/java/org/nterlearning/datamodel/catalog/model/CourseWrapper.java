package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Course}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Course
 * @generated
 */
public class CourseWrapper implements Course, ModelWrapper<Course> {
    private Course _course;

    public CourseWrapper(Course course) {
        _course = course;
    }

    public Class<?> getModelClass() {
        return Course.class;
    }

    public String getModelClassName() {
        return Course.class.getName();
    }

    /**
    * Returns the primary key of this course.
    *
    * @return the primary key of this course
    */
    public long getPrimaryKey() {
        return _course.getPrimaryKey();
    }

    /**
    * Sets the primary key of this course.
    *
    * @param primaryKey the primary key of this course
    */
    public void setPrimaryKey(long primaryKey) {
        _course.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the course ID of this course.
    *
    * @return the course ID of this course
    */
    public long getCourseId() {
        return _course.getCourseId();
    }

    /**
    * Sets the course ID of this course.
    *
    * @param courseId the course ID of this course
    */
    public void setCourseId(long courseId) {
        _course.setCourseId(courseId);
    }

    /**
    * Returns the company ID of this course.
    *
    * @return the company ID of this course
    */
    public long getCompanyId() {
        return _course.getCompanyId();
    }

    /**
    * Sets the company ID of this course.
    *
    * @param companyId the company ID of this course
    */
    public void setCompanyId(long companyId) {
        _course.setCompanyId(companyId);
    }

    /**
    * Returns the group ID of this course.
    *
    * @return the group ID of this course
    */
    public long getGroupId() {
        return _course.getGroupId();
    }

    /**
    * Sets the group ID of this course.
    *
    * @param groupId the group ID of this course
    */
    public void setGroupId(long groupId) {
        _course.setGroupId(groupId);
    }

    /**
    * Returns the user ID of this course.
    *
    * @return the user ID of this course
    */
    public long getUserId() {
        return _course.getUserId();
    }

    /**
    * Sets the user ID of this course.
    *
    * @param userId the user ID of this course
    */
    public void setUserId(long userId) {
        _course.setUserId(userId);
    }

    /**
    * Returns the user uuid of this course.
    *
    * @return the user uuid of this course
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _course.getUserUuid();
    }

    /**
    * Sets the user uuid of this course.
    *
    * @param userUuid the user uuid of this course
    */
    public void setUserUuid(java.lang.String userUuid) {
        _course.setUserUuid(userUuid);
    }

    /**
    * Returns the feed reference ID of this course.
    *
    * @return the feed reference ID of this course
    */
    public long getFeedReferenceId() {
        return _course.getFeedReferenceId();
    }

    /**
    * Sets the feed reference ID of this course.
    *
    * @param feedReferenceId the feed reference ID of this course
    */
    public void setFeedReferenceId(long feedReferenceId) {
        _course.setFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the href of this course.
    *
    * @return the href of this course
    */
    public java.lang.String getHref() {
        return _course.getHref();
    }

    /**
    * Sets the href of this course.
    *
    * @param href the href of this course
    */
    public void setHref(java.lang.String href) {
        _course.setHref(href);
    }

    /**
    * Returns the full text href of this course.
    *
    * @return the full text href of this course
    */
    public java.lang.String getFullTextHref() {
        return _course.getFullTextHref();
    }

    /**
    * Sets the full text href of this course.
    *
    * @param fullTextHref the full text href of this course
    */
    public void setFullTextHref(java.lang.String fullTextHref) {
        _course.setFullTextHref(fullTextHref);
    }

    /**
    * Returns the course iri of this course.
    *
    * @return the course iri of this course
    */
    public java.lang.String getCourseIri() {
        return _course.getCourseIri();
    }

    /**
    * Sets the course iri of this course.
    *
    * @param courseIri the course iri of this course
    */
    public void setCourseIri(java.lang.String courseIri) {
        _course.setCourseIri(courseIri);
    }

    /**
    * Returns the updated date of this course.
    *
    * @return the updated date of this course
    */
    public java.util.Date getUpdatedDate() {
        return _course.getUpdatedDate();
    }

    /**
    * Sets the updated date of this course.
    *
    * @param updatedDate the updated date of this course
    */
    public void setUpdatedDate(java.util.Date updatedDate) {
        _course.setUpdatedDate(updatedDate);
    }

    /**
    * Returns the title of this course.
    *
    * @return the title of this course
    */
    public java.lang.String getTitle() {
        return _course.getTitle();
    }

    /**
    * Returns the localized title of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized title of this course
    */
    public java.lang.String getTitle(java.util.Locale locale) {
        return _course.getTitle(locale);
    }

    /**
    * Returns the localized title of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized title of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
        return _course.getTitle(locale, useDefault);
    }

    /**
    * Returns the localized title of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized title of this course
    */
    public java.lang.String getTitle(java.lang.String languageId) {
        return _course.getTitle(languageId);
    }

    /**
    * Returns the localized title of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized title of this course
    */
    public java.lang.String getTitle(java.lang.String languageId,
        boolean useDefault) {
        return _course.getTitle(languageId, useDefault);
    }

    public java.lang.String getTitleCurrentLanguageId() {
        return _course.getTitleCurrentLanguageId();
    }

    public java.lang.String getTitleCurrentValue() {
        return _course.getTitleCurrentValue();
    }

    /**
    * Returns a map of the locales and localized titles of this course.
    *
    * @return the locales and localized titles of this course
    */
    public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
        return _course.getTitleMap();
    }

    /**
    * Sets the title of this course.
    *
    * @param title the title of this course
    */
    public void setTitle(java.lang.String title) {
        _course.setTitle(title);
    }

    /**
    * Sets the localized title of this course in the language.
    *
    * @param title the localized title of this course
    * @param locale the locale of the language
    */
    public void setTitle(java.lang.String title, java.util.Locale locale) {
        _course.setTitle(title, locale);
    }

    /**
    * Sets the localized title of this course in the language, and sets the default locale.
    *
    * @param title the localized title of this course
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setTitle(java.lang.String title, java.util.Locale locale,
        java.util.Locale defaultLocale) {
        _course.setTitle(title, locale, defaultLocale);
    }

    public void setTitleCurrentLanguageId(java.lang.String languageId) {
        _course.setTitleCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized titles of this course from the map of locales and localized titles.
    *
    * @param titleMap the locales and localized titles of this course
    */
    public void setTitleMap(
        java.util.Map<java.util.Locale, java.lang.String> titleMap) {
        _course.setTitleMap(titleMap);
    }

    /**
    * Sets the localized titles of this course from the map of locales and localized titles, and sets the default locale.
    *
    * @param titleMap the locales and localized titles of this course
    * @param defaultLocale the default locale
    */
    public void setTitleMap(
        java.util.Map<java.util.Locale, java.lang.String> titleMap,
        java.util.Locale defaultLocale) {
        _course.setTitleMap(titleMap, defaultLocale);
    }

    /**
    * Returns the transcript abstract of this course.
    *
    * @return the transcript abstract of this course
    */
    public java.lang.String getTranscriptAbstract() {
        return _course.getTranscriptAbstract();
    }

    /**
    * Returns the localized transcript abstract of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized transcript abstract of this course
    */
    public java.lang.String getTranscriptAbstract(java.util.Locale locale) {
        return _course.getTranscriptAbstract(locale);
    }

    /**
    * Returns the localized transcript abstract of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized transcript abstract of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getTranscriptAbstract(java.util.Locale locale,
        boolean useDefault) {
        return _course.getTranscriptAbstract(locale, useDefault);
    }

    /**
    * Returns the localized transcript abstract of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized transcript abstract of this course
    */
    public java.lang.String getTranscriptAbstract(java.lang.String languageId) {
        return _course.getTranscriptAbstract(languageId);
    }

    /**
    * Returns the localized transcript abstract of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized transcript abstract of this course
    */
    public java.lang.String getTranscriptAbstract(java.lang.String languageId,
        boolean useDefault) {
        return _course.getTranscriptAbstract(languageId, useDefault);
    }

    public java.lang.String getTranscriptAbstractCurrentLanguageId() {
        return _course.getTranscriptAbstractCurrentLanguageId();
    }

    public java.lang.String getTranscriptAbstractCurrentValue() {
        return _course.getTranscriptAbstractCurrentValue();
    }

    /**
    * Returns a map of the locales and localized transcript abstracts of this course.
    *
    * @return the locales and localized transcript abstracts of this course
    */
    public java.util.Map<java.util.Locale, java.lang.String> getTranscriptAbstractMap() {
        return _course.getTranscriptAbstractMap();
    }

    /**
    * Sets the transcript abstract of this course.
    *
    * @param transcriptAbstract the transcript abstract of this course
    */
    public void setTranscriptAbstract(java.lang.String transcriptAbstract) {
        _course.setTranscriptAbstract(transcriptAbstract);
    }

    /**
    * Sets the localized transcript abstract of this course in the language.
    *
    * @param transcriptAbstract the localized transcript abstract of this course
    * @param locale the locale of the language
    */
    public void setTranscriptAbstract(java.lang.String transcriptAbstract,
        java.util.Locale locale) {
        _course.setTranscriptAbstract(transcriptAbstract, locale);
    }

    /**
    * Sets the localized transcript abstract of this course in the language, and sets the default locale.
    *
    * @param transcriptAbstract the localized transcript abstract of this course
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setTranscriptAbstract(java.lang.String transcriptAbstract,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _course.setTranscriptAbstract(transcriptAbstract, locale, defaultLocale);
    }

    public void setTranscriptAbstractCurrentLanguageId(
        java.lang.String languageId) {
        _course.setTranscriptAbstractCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized transcript abstracts of this course from the map of locales and localized transcript abstracts.
    *
    * @param transcriptAbstractMap the locales and localized transcript abstracts of this course
    */
    public void setTranscriptAbstractMap(
        java.util.Map<java.util.Locale, java.lang.String> transcriptAbstractMap) {
        _course.setTranscriptAbstractMap(transcriptAbstractMap);
    }

    /**
    * Sets the localized transcript abstracts of this course from the map of locales and localized transcript abstracts, and sets the default locale.
    *
    * @param transcriptAbstractMap the locales and localized transcript abstracts of this course
    * @param defaultLocale the default locale
    */
    public void setTranscriptAbstractMap(
        java.util.Map<java.util.Locale, java.lang.String> transcriptAbstractMap,
        java.util.Locale defaultLocale) {
        _course.setTranscriptAbstractMap(transcriptAbstractMap, defaultLocale);
    }

    /**
    * Returns the description of this course.
    *
    * @return the description of this course
    */
    public java.lang.String getDescription() {
        return _course.getDescription();
    }

    /**
    * Returns the localized description of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized description of this course
    */
    public java.lang.String getDescription(java.util.Locale locale) {
        return _course.getDescription(locale);
    }

    /**
    * Returns the localized description of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized description of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getDescription(java.util.Locale locale,
        boolean useDefault) {
        return _course.getDescription(locale, useDefault);
    }

    /**
    * Returns the localized description of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized description of this course
    */
    public java.lang.String getDescription(java.lang.String languageId) {
        return _course.getDescription(languageId);
    }

    /**
    * Returns the localized description of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized description of this course
    */
    public java.lang.String getDescription(java.lang.String languageId,
        boolean useDefault) {
        return _course.getDescription(languageId, useDefault);
    }

    public java.lang.String getDescriptionCurrentLanguageId() {
        return _course.getDescriptionCurrentLanguageId();
    }

    public java.lang.String getDescriptionCurrentValue() {
        return _course.getDescriptionCurrentValue();
    }

    /**
    * Returns a map of the locales and localized descriptions of this course.
    *
    * @return the locales and localized descriptions of this course
    */
    public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
        return _course.getDescriptionMap();
    }

    /**
    * Sets the description of this course.
    *
    * @param description the description of this course
    */
    public void setDescription(java.lang.String description) {
        _course.setDescription(description);
    }

    /**
    * Sets the localized description of this course in the language.
    *
    * @param description the localized description of this course
    * @param locale the locale of the language
    */
    public void setDescription(java.lang.String description,
        java.util.Locale locale) {
        _course.setDescription(description, locale);
    }

    /**
    * Sets the localized description of this course in the language, and sets the default locale.
    *
    * @param description the localized description of this course
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setDescription(java.lang.String description,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _course.setDescription(description, locale, defaultLocale);
    }

    public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
        _course.setDescriptionCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized descriptions of this course from the map of locales and localized descriptions.
    *
    * @param descriptionMap the locales and localized descriptions of this course
    */
    public void setDescriptionMap(
        java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
        _course.setDescriptionMap(descriptionMap);
    }

    /**
    * Sets the localized descriptions of this course from the map of locales and localized descriptions, and sets the default locale.
    *
    * @param descriptionMap the locales and localized descriptions of this course
    * @param defaultLocale the default locale
    */
    public void setDescriptionMap(
        java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
        java.util.Locale defaultLocale) {
        _course.setDescriptionMap(descriptionMap, defaultLocale);
    }

    /**
    * Returns the keywords of this course.
    *
    * @return the keywords of this course
    */
    public java.lang.String getKeywords() {
        return _course.getKeywords();
    }

    /**
    * Returns the localized keywords of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized keywords of this course
    */
    public java.lang.String getKeywords(java.util.Locale locale) {
        return _course.getKeywords(locale);
    }

    /**
    * Returns the localized keywords of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized keywords of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getKeywords(java.util.Locale locale,
        boolean useDefault) {
        return _course.getKeywords(locale, useDefault);
    }

    /**
    * Returns the localized keywords of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized keywords of this course
    */
    public java.lang.String getKeywords(java.lang.String languageId) {
        return _course.getKeywords(languageId);
    }

    /**
    * Returns the localized keywords of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized keywords of this course
    */
    public java.lang.String getKeywords(java.lang.String languageId,
        boolean useDefault) {
        return _course.getKeywords(languageId, useDefault);
    }

    public java.lang.String getKeywordsCurrentLanguageId() {
        return _course.getKeywordsCurrentLanguageId();
    }

    public java.lang.String getKeywordsCurrentValue() {
        return _course.getKeywordsCurrentValue();
    }

    /**
    * Returns a map of the locales and localized keywordses of this course.
    *
    * @return the locales and localized keywordses of this course
    */
    public java.util.Map<java.util.Locale, java.lang.String> getKeywordsMap() {
        return _course.getKeywordsMap();
    }

    /**
    * Sets the keywords of this course.
    *
    * @param keywords the keywords of this course
    */
    public void setKeywords(java.lang.String keywords) {
        _course.setKeywords(keywords);
    }

    /**
    * Sets the localized keywords of this course in the language.
    *
    * @param keywords the localized keywords of this course
    * @param locale the locale of the language
    */
    public void setKeywords(java.lang.String keywords, java.util.Locale locale) {
        _course.setKeywords(keywords, locale);
    }

    /**
    * Sets the localized keywords of this course in the language, and sets the default locale.
    *
    * @param keywords the localized keywords of this course
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setKeywords(java.lang.String keywords, java.util.Locale locale,
        java.util.Locale defaultLocale) {
        _course.setKeywords(keywords, locale, defaultLocale);
    }

    public void setKeywordsCurrentLanguageId(java.lang.String languageId) {
        _course.setKeywordsCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized keywordses of this course from the map of locales and localized keywordses.
    *
    * @param keywordsMap the locales and localized keywordses of this course
    */
    public void setKeywordsMap(
        java.util.Map<java.util.Locale, java.lang.String> keywordsMap) {
        _course.setKeywordsMap(keywordsMap);
    }

    /**
    * Sets the localized keywordses of this course from the map of locales and localized keywordses, and sets the default locale.
    *
    * @param keywordsMap the locales and localized keywordses of this course
    * @param defaultLocale the default locale
    */
    public void setKeywordsMap(
        java.util.Map<java.util.Locale, java.lang.String> keywordsMap,
        java.util.Locale defaultLocale) {
        _course.setKeywordsMap(keywordsMap, defaultLocale);
    }

    /**
    * Returns the copyright of this course.
    *
    * @return the copyright of this course
    */
    public java.lang.String getCopyright() {
        return _course.getCopyright();
    }

    /**
    * Returns the localized copyright of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized copyright of this course
    */
    public java.lang.String getCopyright(java.util.Locale locale) {
        return _course.getCopyright(locale);
    }

    /**
    * Returns the localized copyright of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized copyright of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getCopyright(java.util.Locale locale,
        boolean useDefault) {
        return _course.getCopyright(locale, useDefault);
    }

    /**
    * Returns the localized copyright of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized copyright of this course
    */
    public java.lang.String getCopyright(java.lang.String languageId) {
        return _course.getCopyright(languageId);
    }

    /**
    * Returns the localized copyright of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized copyright of this course
    */
    public java.lang.String getCopyright(java.lang.String languageId,
        boolean useDefault) {
        return _course.getCopyright(languageId, useDefault);
    }

    public java.lang.String getCopyrightCurrentLanguageId() {
        return _course.getCopyrightCurrentLanguageId();
    }

    public java.lang.String getCopyrightCurrentValue() {
        return _course.getCopyrightCurrentValue();
    }

    /**
    * Returns a map of the locales and localized copyrights of this course.
    *
    * @return the locales and localized copyrights of this course
    */
    public java.util.Map<java.util.Locale, java.lang.String> getCopyrightMap() {
        return _course.getCopyrightMap();
    }

    /**
    * Sets the copyright of this course.
    *
    * @param copyright the copyright of this course
    */
    public void setCopyright(java.lang.String copyright) {
        _course.setCopyright(copyright);
    }

    /**
    * Sets the localized copyright of this course in the language.
    *
    * @param copyright the localized copyright of this course
    * @param locale the locale of the language
    */
    public void setCopyright(java.lang.String copyright, java.util.Locale locale) {
        _course.setCopyright(copyright, locale);
    }

    /**
    * Sets the localized copyright of this course in the language, and sets the default locale.
    *
    * @param copyright the localized copyright of this course
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setCopyright(java.lang.String copyright,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _course.setCopyright(copyright, locale, defaultLocale);
    }

    public void setCopyrightCurrentLanguageId(java.lang.String languageId) {
        _course.setCopyrightCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized copyrights of this course from the map of locales and localized copyrights.
    *
    * @param copyrightMap the locales and localized copyrights of this course
    */
    public void setCopyrightMap(
        java.util.Map<java.util.Locale, java.lang.String> copyrightMap) {
        _course.setCopyrightMap(copyrightMap);
    }

    /**
    * Sets the localized copyrights of this course from the map of locales and localized copyrights, and sets the default locale.
    *
    * @param copyrightMap the locales and localized copyrights of this course
    * @param defaultLocale the default locale
    */
    public void setCopyrightMap(
        java.util.Map<java.util.Locale, java.lang.String> copyrightMap,
        java.util.Locale defaultLocale) {
        _course.setCopyrightMap(copyrightMap, defaultLocale);
    }

    /**
    * Returns the rating level of this course.
    *
    * @return the rating level of this course
    */
    public java.lang.String getRatingLevel() {
        return _course.getRatingLevel();
    }

    /**
    * Returns the localized rating level of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized rating level of this course
    */
    public java.lang.String getRatingLevel(java.util.Locale locale) {
        return _course.getRatingLevel(locale);
    }

    /**
    * Returns the localized rating level of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized rating level of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getRatingLevel(java.util.Locale locale,
        boolean useDefault) {
        return _course.getRatingLevel(locale, useDefault);
    }

    /**
    * Returns the localized rating level of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized rating level of this course
    */
    public java.lang.String getRatingLevel(java.lang.String languageId) {
        return _course.getRatingLevel(languageId);
    }

    /**
    * Returns the localized rating level of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized rating level of this course
    */
    public java.lang.String getRatingLevel(java.lang.String languageId,
        boolean useDefault) {
        return _course.getRatingLevel(languageId, useDefault);
    }

    public java.lang.String getRatingLevelCurrentLanguageId() {
        return _course.getRatingLevelCurrentLanguageId();
    }

    public java.lang.String getRatingLevelCurrentValue() {
        return _course.getRatingLevelCurrentValue();
    }

    /**
    * Returns a map of the locales and localized rating levels of this course.
    *
    * @return the locales and localized rating levels of this course
    */
    public java.util.Map<java.util.Locale, java.lang.String> getRatingLevelMap() {
        return _course.getRatingLevelMap();
    }

    /**
    * Sets the rating level of this course.
    *
    * @param ratingLevel the rating level of this course
    */
    public void setRatingLevel(java.lang.String ratingLevel) {
        _course.setRatingLevel(ratingLevel);
    }

    /**
    * Sets the localized rating level of this course in the language.
    *
    * @param ratingLevel the localized rating level of this course
    * @param locale the locale of the language
    */
    public void setRatingLevel(java.lang.String ratingLevel,
        java.util.Locale locale) {
        _course.setRatingLevel(ratingLevel, locale);
    }

    /**
    * Sets the localized rating level of this course in the language, and sets the default locale.
    *
    * @param ratingLevel the localized rating level of this course
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setRatingLevel(java.lang.String ratingLevel,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _course.setRatingLevel(ratingLevel, locale, defaultLocale);
    }

    public void setRatingLevelCurrentLanguageId(java.lang.String languageId) {
        _course.setRatingLevelCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized rating levels of this course from the map of locales and localized rating levels.
    *
    * @param ratingLevelMap the locales and localized rating levels of this course
    */
    public void setRatingLevelMap(
        java.util.Map<java.util.Locale, java.lang.String> ratingLevelMap) {
        _course.setRatingLevelMap(ratingLevelMap);
    }

    /**
    * Sets the localized rating levels of this course from the map of locales and localized rating levels, and sets the default locale.
    *
    * @param ratingLevelMap the locales and localized rating levels of this course
    * @param defaultLocale the default locale
    */
    public void setRatingLevelMap(
        java.util.Map<java.util.Locale, java.lang.String> ratingLevelMap,
        java.util.Locale defaultLocale) {
        _course.setRatingLevelMap(ratingLevelMap, defaultLocale);
    }

    /**
    * Returns the rating reason of this course.
    *
    * @return the rating reason of this course
    */
    public java.lang.String getRatingReason() {
        return _course.getRatingReason();
    }

    /**
    * Returns the localized rating reason of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized rating reason of this course
    */
    public java.lang.String getRatingReason(java.util.Locale locale) {
        return _course.getRatingReason(locale);
    }

    /**
    * Returns the localized rating reason of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized rating reason of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getRatingReason(java.util.Locale locale,
        boolean useDefault) {
        return _course.getRatingReason(locale, useDefault);
    }

    /**
    * Returns the localized rating reason of this course in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized rating reason of this course
    */
    public java.lang.String getRatingReason(java.lang.String languageId) {
        return _course.getRatingReason(languageId);
    }

    /**
    * Returns the localized rating reason of this course in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized rating reason of this course
    */
    public java.lang.String getRatingReason(java.lang.String languageId,
        boolean useDefault) {
        return _course.getRatingReason(languageId, useDefault);
    }

    public java.lang.String getRatingReasonCurrentLanguageId() {
        return _course.getRatingReasonCurrentLanguageId();
    }

    public java.lang.String getRatingReasonCurrentValue() {
        return _course.getRatingReasonCurrentValue();
    }

    /**
    * Returns a map of the locales and localized rating reasons of this course.
    *
    * @return the locales and localized rating reasons of this course
    */
    public java.util.Map<java.util.Locale, java.lang.String> getRatingReasonMap() {
        return _course.getRatingReasonMap();
    }

    /**
    * Sets the rating reason of this course.
    *
    * @param ratingReason the rating reason of this course
    */
    public void setRatingReason(java.lang.String ratingReason) {
        _course.setRatingReason(ratingReason);
    }

    /**
    * Sets the localized rating reason of this course in the language.
    *
    * @param ratingReason the localized rating reason of this course
    * @param locale the locale of the language
    */
    public void setRatingReason(java.lang.String ratingReason,
        java.util.Locale locale) {
        _course.setRatingReason(ratingReason, locale);
    }

    /**
    * Sets the localized rating reason of this course in the language, and sets the default locale.
    *
    * @param ratingReason the localized rating reason of this course
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setRatingReason(java.lang.String ratingReason,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _course.setRatingReason(ratingReason, locale, defaultLocale);
    }

    public void setRatingReasonCurrentLanguageId(java.lang.String languageId) {
        _course.setRatingReasonCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized rating reasons of this course from the map of locales and localized rating reasons.
    *
    * @param ratingReasonMap the locales and localized rating reasons of this course
    */
    public void setRatingReasonMap(
        java.util.Map<java.util.Locale, java.lang.String> ratingReasonMap) {
        _course.setRatingReasonMap(ratingReasonMap);
    }

    /**
    * Sets the localized rating reasons of this course from the map of locales and localized rating reasons, and sets the default locale.
    *
    * @param ratingReasonMap the locales and localized rating reasons of this course
    * @param defaultLocale the default locale
    */
    public void setRatingReasonMap(
        java.util.Map<java.util.Locale, java.lang.String> ratingReasonMap,
        java.util.Locale defaultLocale) {
        _course.setRatingReasonMap(ratingReasonMap, defaultLocale);
    }

    /**
    * Returns the duration of this course.
    *
    * @return the duration of this course
    */
    public java.lang.String getDuration() {
        return _course.getDuration();
    }

    /**
    * Sets the duration of this course.
    *
    * @param duration the duration of this course
    */
    public void setDuration(java.lang.String duration) {
        _course.setDuration(duration);
    }

    /**
    * Returns the duration standard of this course.
    *
    * @return the duration standard of this course
    */
    public java.lang.String getDurationStandard() {
        return _course.getDurationStandard();
    }

    /**
    * Sets the duration standard of this course.
    *
    * @param durationStandard the duration standard of this course
    */
    public void setDurationStandard(java.lang.String durationStandard) {
        _course.setDurationStandard(durationStandard);
    }

    /**
    * Returns the featured status of this course.
    *
    * @return the featured status of this course
    */
    public double getFeaturedStatus() {
        return _course.getFeaturedStatus();
    }

    /**
    * Sets the featured status of this course.
    *
    * @param featuredStatus the featured status of this course
    */
    public void setFeaturedStatus(double featuredStatus) {
        _course.setFeaturedStatus(featuredStatus);
    }

    /**
    * Returns the popular weight of this course.
    *
    * @return the popular weight of this course
    */
    public double getPopularWeight() {
        return _course.getPopularWeight();
    }

    /**
    * Sets the popular weight of this course.
    *
    * @param popularWeight the popular weight of this course
    */
    public void setPopularWeight(double popularWeight) {
        _course.setPopularWeight(popularWeight);
    }

    /**
    * Returns the access count of this course.
    *
    * @return the access count of this course
    */
    public long getAccessCount() {
        return _course.getAccessCount();
    }

    /**
    * Sets the access count of this course.
    *
    * @param accessCount the access count of this course
    */
    public void setAccessCount(long accessCount) {
        _course.setAccessCount(accessCount);
    }

    /**
    * Returns the completed count of this course.
    *
    * @return the completed count of this course
    */
    public long getCompletedCount() {
        return _course.getCompletedCount();
    }

    /**
    * Sets the completed count of this course.
    *
    * @param completedCount the completed count of this course
    */
    public void setCompletedCount(long completedCount) {
        _course.setCompletedCount(completedCount);
    }

    /**
    * Returns the create date of this course.
    *
    * @return the create date of this course
    */
    public java.util.Date getCreateDate() {
        return _course.getCreateDate();
    }

    /**
    * Sets the create date of this course.
    *
    * @param createDate the create date of this course
    */
    public void setCreateDate(java.util.Date createDate) {
        _course.setCreateDate(createDate);
    }

    /**
    * Returns the removed of this course.
    *
    * @return the removed of this course
    */
    public boolean getRemoved() {
        return _course.getRemoved();
    }

    /**
    * Returns <code>true</code> if this course is removed.
    *
    * @return <code>true</code> if this course is removed; <code>false</code> otherwise
    */
    public boolean isRemoved() {
        return _course.isRemoved();
    }

    /**
    * Sets whether this course is removed.
    *
    * @param removed the removed of this course
    */
    public void setRemoved(boolean removed) {
        _course.setRemoved(removed);
    }

    /**
    * Returns the removed date of this course.
    *
    * @return the removed date of this course
    */
    public java.util.Date getRemovedDate() {
        return _course.getRemovedDate();
    }

    /**
    * Sets the removed date of this course.
    *
    * @param removedDate the removed date of this course
    */
    public void setRemovedDate(java.util.Date removedDate) {
        _course.setRemovedDate(removedDate);
    }

    /**
    * Returns the supersedes course iri of this course.
    *
    * @return the supersedes course iri of this course
    */
    public java.lang.String getSupersedesCourseIri() {
        return _course.getSupersedesCourseIri();
    }

    /**
    * Sets the supersedes course iri of this course.
    *
    * @param supersedesCourseIri the supersedes course iri of this course
    */
    public void setSupersedesCourseIri(java.lang.String supersedesCourseIri) {
        _course.setSupersedesCourseIri(supersedesCourseIri);
    }

    /**
    * Returns the superseded by course iri of this course.
    *
    * @return the superseded by course iri of this course
    */
    public java.lang.String getSupersededByCourseIri() {
        return _course.getSupersededByCourseIri();
    }

    /**
    * Sets the superseded by course iri of this course.
    *
    * @param supersededByCourseIri the superseded by course iri of this course
    */
    public void setSupersededByCourseIri(java.lang.String supersededByCourseIri) {
        _course.setSupersededByCourseIri(supersededByCourseIri);
    }

    /**
    * Returns the release on date of this course.
    *
    * @return the release on date of this course
    */
    public java.util.Date getReleaseOnDate() {
        return _course.getReleaseOnDate();
    }

    /**
    * Sets the release on date of this course.
    *
    * @param releaseOnDate the release on date of this course
    */
    public void setReleaseOnDate(java.util.Date releaseOnDate) {
        _course.setReleaseOnDate(releaseOnDate);
    }

    /**
    * Returns the accept until date of this course.
    *
    * @return the accept until date of this course
    */
    public java.util.Date getAcceptUntilDate() {
        return _course.getAcceptUntilDate();
    }

    /**
    * Sets the accept until date of this course.
    *
    * @param acceptUntilDate the accept until date of this course
    */
    public void setAcceptUntilDate(java.util.Date acceptUntilDate) {
        _course.setAcceptUntilDate(acceptUntilDate);
    }

    /**
    * Returns the version of this course.
    *
    * @return the version of this course
    */
    public java.lang.String getVersion() {
        return _course.getVersion();
    }

    /**
    * Sets the version of this course.
    *
    * @param version the version of this course
    */
    public void setVersion(java.lang.String version) {
        _course.setVersion(version);
    }

    /**
    * Returns the version date of this course.
    *
    * @return the version date of this course
    */
    public java.util.Date getVersionDate() {
        return _course.getVersionDate();
    }

    /**
    * Sets the version date of this course.
    *
    * @param versionDate the version date of this course
    */
    public void setVersionDate(java.util.Date versionDate) {
        _course.setVersionDate(versionDate);
    }

    /**
    * Returns the price of this course.
    *
    * @return the price of this course
    */
    public double getPrice() {
        return _course.getPrice();
    }

    /**
    * Sets the price of this course.
    *
    * @param price the price of this course
    */
    public void setPrice(double price) {
        _course.setPrice(price);
    }

    /**
    * Returns the price unit of this course.
    *
    * @return the price unit of this course
    */
    public java.lang.String getPriceUnit() {
        return _course.getPriceUnit();
    }

    /**
    * Sets the price unit of this course.
    *
    * @param priceUnit the price unit of this course
    */
    public void setPriceUnit(java.lang.String priceUnit) {
        _course.setPriceUnit(priceUnit);
    }

    /**
    * Returns the price terms of this course.
    *
    * @return the price terms of this course
    */
    public java.lang.String getPriceTerms() {
        return _course.getPriceTerms();
    }

    /**
    * Sets the price terms of this course.
    *
    * @param priceTerms the price terms of this course
    */
    public void setPriceTerms(java.lang.String priceTerms) {
        _course.setPriceTerms(priceTerms);
    }

    /**
    * Returns the price expiration of this course.
    *
    * @return the price expiration of this course
    */
    public java.lang.String getPriceExpiration() {
        return _course.getPriceExpiration();
    }

    /**
    * Sets the price expiration of this course.
    *
    * @param priceExpiration the price expiration of this course
    */
    public void setPriceExpiration(java.lang.String priceExpiration) {
        _course.setPriceExpiration(priceExpiration);
    }

    /**
    * Returns the one star rate count of this course.
    *
    * @return the one star rate count of this course
    */
    public long getOneStarRateCount() {
        return _course.getOneStarRateCount();
    }

    /**
    * Sets the one star rate count of this course.
    *
    * @param oneStarRateCount the one star rate count of this course
    */
    public void setOneStarRateCount(long oneStarRateCount) {
        _course.setOneStarRateCount(oneStarRateCount);
    }

    /**
    * Returns the two star rate count of this course.
    *
    * @return the two star rate count of this course
    */
    public long getTwoStarRateCount() {
        return _course.getTwoStarRateCount();
    }

    /**
    * Sets the two star rate count of this course.
    *
    * @param twoStarRateCount the two star rate count of this course
    */
    public void setTwoStarRateCount(long twoStarRateCount) {
        _course.setTwoStarRateCount(twoStarRateCount);
    }

    /**
    * Returns the three star rate count of this course.
    *
    * @return the three star rate count of this course
    */
    public long getThreeStarRateCount() {
        return _course.getThreeStarRateCount();
    }

    /**
    * Sets the three star rate count of this course.
    *
    * @param threeStarRateCount the three star rate count of this course
    */
    public void setThreeStarRateCount(long threeStarRateCount) {
        _course.setThreeStarRateCount(threeStarRateCount);
    }

    /**
    * Returns the four star rate count of this course.
    *
    * @return the four star rate count of this course
    */
    public long getFourStarRateCount() {
        return _course.getFourStarRateCount();
    }

    /**
    * Sets the four star rate count of this course.
    *
    * @param fourStarRateCount the four star rate count of this course
    */
    public void setFourStarRateCount(long fourStarRateCount) {
        _course.setFourStarRateCount(fourStarRateCount);
    }

    /**
    * Returns the five star rate count of this course.
    *
    * @return the five star rate count of this course
    */
    public long getFiveStarRateCount() {
        return _course.getFiveStarRateCount();
    }

    /**
    * Sets the five star rate count of this course.
    *
    * @param fiveStarRateCount the five star rate count of this course
    */
    public void setFiveStarRateCount(long fiveStarRateCount) {
        _course.setFiveStarRateCount(fiveStarRateCount);
    }

    public boolean isNew() {
        return _course.isNew();
    }

    public void setNew(boolean n) {
        _course.setNew(n);
    }

    public boolean isCachedModel() {
        return _course.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _course.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _course.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _course.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _course.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _course.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _course.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CourseWrapper((Course) _course.clone());
    }

    public int compareTo(org.nterlearning.datamodel.catalog.model.Course course) {
        return _course.compareTo(course);
    }

    @Override
    public int hashCode() {
        return _course.hashCode();
    }

    public com.liferay.portal.model.CacheModel<org.nterlearning.datamodel.catalog.model.Course> toCacheModel() {
        return _course.toCacheModel();
    }

    public org.nterlearning.datamodel.catalog.model.Course toEscapedModel() {
        return new CourseWrapper(_course.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _course.toString();
    }

    public java.lang.String toXmlString() {
        return _course.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _course.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Course getWrappedCourse() {
        return _course;
    }

    public Course getWrappedModel() {
        return _course;
    }

    public void resetOriginalValues() {
        _course.resetOriginalValues();
    }
}
