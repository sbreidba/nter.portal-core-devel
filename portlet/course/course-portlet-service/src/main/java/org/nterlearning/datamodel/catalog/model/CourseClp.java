package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.Locale;
import java.util.Map;


public class CourseClp extends BaseModelImpl<Course> implements Course {
    private long _courseId;
    private long _companyId;
    private long _groupId;
    private long _userId;
    private String _userUuid;
    private long _feedReferenceId;
    private String _href;
    private String _fullTextHref;
    private String _courseIri;
    private Date _updatedDate;
    private String _title;
    private String _titleCurrentLanguageId;
    private String _transcriptAbstract;
    private String _transcriptAbstractCurrentLanguageId;
    private String _description;
    private String _descriptionCurrentLanguageId;
    private String _keywords;
    private String _keywordsCurrentLanguageId;
    private String _copyright;
    private String _copyrightCurrentLanguageId;
    private String _ratingLevel;
    private String _ratingLevelCurrentLanguageId;
    private String _ratingReason;
    private String _ratingReasonCurrentLanguageId;
    private String _duration;
    private String _durationStandard;
    private double _featuredStatus;
    private double _popularWeight;
    private long _accessCount;
    private long _completedCount;
    private Date _createDate;
    private boolean _removed;
    private Date _removedDate;
    private String _supersedesCourseIri;
    private String _supersededByCourseIri;
    private Date _releaseOnDate;
    private Date _acceptUntilDate;
    private String _version;
    private Date _versionDate;
    private double _price;
    private String _priceUnit;
    private String _priceTerms;
    private String _priceExpiration;
    private long _oneStarRateCount;
    private long _twoStarRateCount;
    private long _threeStarRateCount;
    private long _fourStarRateCount;
    private long _fiveStarRateCount;

    public CourseClp() {
    }

    public Class<?> getModelClass() {
        return Course.class;
    }

    public String getModelClassName() {
        return Course.class.getName();
    }

    public long getPrimaryKey() {
        return _courseId;
    }

    public void setPrimaryKey(long primaryKey) {
        setCourseId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_courseId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
    }

    public String getHref() {
        return _href;
    }

    public void setHref(String href) {
        _href = href;
    }

    public String getFullTextHref() {
        return _fullTextHref;
    }

    public void setFullTextHref(String fullTextHref) {
        _fullTextHref = fullTextHref;
    }

    public String getCourseIri() {
        return _courseIri;
    }

    public void setCourseIri(String courseIri) {
        _courseIri = courseIri;
    }

    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;
    }

    public String getTitle() {
        return _title;
    }

    public String getTitle(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getTitle(languageId);
    }

    public String getTitle(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getTitle(languageId, useDefault);
    }

    public String getTitle(String languageId) {
        return LocalizationUtil.getLocalization(getTitle(), languageId);
    }

    public String getTitle(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getTitle(), languageId,
            useDefault);
    }

    public String getTitleCurrentLanguageId() {
        return _titleCurrentLanguageId;
    }

    public String getTitleCurrentValue() {
        Locale locale = getLocale(_titleCurrentLanguageId);

        return getTitle(locale);
    }

    public Map<Locale, String> getTitleMap() {
        return LocalizationUtil.getLocalizationMap(getTitle());
    }

    public void setTitle(String title) {
        _title = title;
    }

    public void setTitle(String title, Locale locale) {
        setTitle(title, locale, LocaleUtil.getDefault());
    }

    public void setTitle(String title, Locale locale, Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(title)) {
            setTitle(LocalizationUtil.updateLocalization(getTitle(), "Title",
                    title, languageId, defaultLanguageId));
        } else {
            setTitle(LocalizationUtil.removeLocalization(getTitle(), "Title",
                    languageId));
        }
    }

    public void setTitleCurrentLanguageId(String languageId) {
        _titleCurrentLanguageId = languageId;
    }

    public void setTitleMap(Map<Locale, String> titleMap) {
        setTitleMap(titleMap, LocaleUtil.getDefault());
    }

    public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale) {
        if (titleMap == null) {
            return;
        }

        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(portalClassLoader);
            }

            Locale[] locales = LanguageUtil.getAvailableLocales();

            for (Locale locale : locales) {
                String title = titleMap.get(locale);

                setTitle(title, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public String getTranscriptAbstract() {
        return _transcriptAbstract;
    }

    public String getTranscriptAbstract(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getTranscriptAbstract(languageId);
    }

    public String getTranscriptAbstract(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getTranscriptAbstract(languageId, useDefault);
    }

    public String getTranscriptAbstract(String languageId) {
        return LocalizationUtil.getLocalization(getTranscriptAbstract(),
            languageId);
    }

    public String getTranscriptAbstract(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getTranscriptAbstract(),
            languageId, useDefault);
    }

    public String getTranscriptAbstractCurrentLanguageId() {
        return _transcriptAbstractCurrentLanguageId;
    }

    public String getTranscriptAbstractCurrentValue() {
        Locale locale = getLocale(_transcriptAbstractCurrentLanguageId);

        return getTranscriptAbstract(locale);
    }

    public Map<Locale, String> getTranscriptAbstractMap() {
        return LocalizationUtil.getLocalizationMap(getTranscriptAbstract());
    }

    public void setTranscriptAbstract(String transcriptAbstract) {
        _transcriptAbstract = transcriptAbstract;
    }

    public void setTranscriptAbstract(String transcriptAbstract, Locale locale) {
        setTranscriptAbstract(transcriptAbstract, locale,
            LocaleUtil.getDefault());
    }

    public void setTranscriptAbstract(String transcriptAbstract, Locale locale,
        Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(transcriptAbstract)) {
            setTranscriptAbstract(LocalizationUtil.updateLocalization(
                    getTranscriptAbstract(), "TranscriptAbstract",
                    transcriptAbstract, languageId, defaultLanguageId));
        } else {
            setTranscriptAbstract(LocalizationUtil.removeLocalization(
                    getTranscriptAbstract(), "TranscriptAbstract", languageId));
        }
    }

    public void setTranscriptAbstractCurrentLanguageId(String languageId) {
        _transcriptAbstractCurrentLanguageId = languageId;
    }

    public void setTranscriptAbstractMap(
        Map<Locale, String> transcriptAbstractMap) {
        setTranscriptAbstractMap(transcriptAbstractMap, LocaleUtil.getDefault());
    }

    public void setTranscriptAbstractMap(
        Map<Locale, String> transcriptAbstractMap, Locale defaultLocale) {
        if (transcriptAbstractMap == null) {
            return;
        }

        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(portalClassLoader);
            }

            Locale[] locales = LanguageUtil.getAvailableLocales();

            for (Locale locale : locales) {
                String transcriptAbstract = transcriptAbstractMap.get(locale);

                setTranscriptAbstract(transcriptAbstract, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public String getDescription() {
        return _description;
    }

    public String getDescription(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getDescription(languageId);
    }

    public String getDescription(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getDescription(languageId, useDefault);
    }

    public String getDescription(String languageId) {
        return LocalizationUtil.getLocalization(getDescription(), languageId);
    }

    public String getDescription(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getDescription(), languageId,
            useDefault);
    }

    public String getDescriptionCurrentLanguageId() {
        return _descriptionCurrentLanguageId;
    }

    public String getDescriptionCurrentValue() {
        Locale locale = getLocale(_descriptionCurrentLanguageId);

        return getDescription(locale);
    }

    public Map<Locale, String> getDescriptionMap() {
        return LocalizationUtil.getLocalizationMap(getDescription());
    }

    public void setDescription(String description) {
        _description = description;
    }

    public void setDescription(String description, Locale locale) {
        setDescription(description, locale, LocaleUtil.getDefault());
    }

    public void setDescription(String description, Locale locale,
        Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(description)) {
            setDescription(LocalizationUtil.updateLocalization(
                    getDescription(), "Description", description, languageId,
                    defaultLanguageId));
        } else {
            setDescription(LocalizationUtil.removeLocalization(
                    getDescription(), "Description", languageId));
        }
    }

    public void setDescriptionCurrentLanguageId(String languageId) {
        _descriptionCurrentLanguageId = languageId;
    }

    public void setDescriptionMap(Map<Locale, String> descriptionMap) {
        setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
    }

    public void setDescriptionMap(Map<Locale, String> descriptionMap,
        Locale defaultLocale) {
        if (descriptionMap == null) {
            return;
        }

        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(portalClassLoader);
            }

            Locale[] locales = LanguageUtil.getAvailableLocales();

            for (Locale locale : locales) {
                String description = descriptionMap.get(locale);

                setDescription(description, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public String getKeywords() {
        return _keywords;
    }

    public String getKeywords(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getKeywords(languageId);
    }

    public String getKeywords(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getKeywords(languageId, useDefault);
    }

    public String getKeywords(String languageId) {
        return LocalizationUtil.getLocalization(getKeywords(), languageId);
    }

    public String getKeywords(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getKeywords(), languageId,
            useDefault);
    }

    public String getKeywordsCurrentLanguageId() {
        return _keywordsCurrentLanguageId;
    }

    public String getKeywordsCurrentValue() {
        Locale locale = getLocale(_keywordsCurrentLanguageId);

        return getKeywords(locale);
    }

    public Map<Locale, String> getKeywordsMap() {
        return LocalizationUtil.getLocalizationMap(getKeywords());
    }

    public void setKeywords(String keywords) {
        _keywords = keywords;
    }

    public void setKeywords(String keywords, Locale locale) {
        setKeywords(keywords, locale, LocaleUtil.getDefault());
    }

    public void setKeywords(String keywords, Locale locale, Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(keywords)) {
            setKeywords(LocalizationUtil.updateLocalization(getKeywords(),
                    "Keywords", keywords, languageId, defaultLanguageId));
        } else {
            setKeywords(LocalizationUtil.removeLocalization(getKeywords(),
                    "Keywords", languageId));
        }
    }

    public void setKeywordsCurrentLanguageId(String languageId) {
        _keywordsCurrentLanguageId = languageId;
    }

    public void setKeywordsMap(Map<Locale, String> keywordsMap) {
        setKeywordsMap(keywordsMap, LocaleUtil.getDefault());
    }

    public void setKeywordsMap(Map<Locale, String> keywordsMap,
        Locale defaultLocale) {
        if (keywordsMap == null) {
            return;
        }

        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(portalClassLoader);
            }

            Locale[] locales = LanguageUtil.getAvailableLocales();

            for (Locale locale : locales) {
                String keywords = keywordsMap.get(locale);

                setKeywords(keywords, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public String getCopyright() {
        return _copyright;
    }

    public String getCopyright(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getCopyright(languageId);
    }

    public String getCopyright(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getCopyright(languageId, useDefault);
    }

    public String getCopyright(String languageId) {
        return LocalizationUtil.getLocalization(getCopyright(), languageId);
    }

    public String getCopyright(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getCopyright(), languageId,
            useDefault);
    }

    public String getCopyrightCurrentLanguageId() {
        return _copyrightCurrentLanguageId;
    }

    public String getCopyrightCurrentValue() {
        Locale locale = getLocale(_copyrightCurrentLanguageId);

        return getCopyright(locale);
    }

    public Map<Locale, String> getCopyrightMap() {
        return LocalizationUtil.getLocalizationMap(getCopyright());
    }

    public void setCopyright(String copyright) {
        _copyright = copyright;
    }

    public void setCopyright(String copyright, Locale locale) {
        setCopyright(copyright, locale, LocaleUtil.getDefault());
    }

    public void setCopyright(String copyright, Locale locale,
        Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(copyright)) {
            setCopyright(LocalizationUtil.updateLocalization(getCopyright(),
                    "Copyright", copyright, languageId, defaultLanguageId));
        } else {
            setCopyright(LocalizationUtil.removeLocalization(getCopyright(),
                    "Copyright", languageId));
        }
    }

    public void setCopyrightCurrentLanguageId(String languageId) {
        _copyrightCurrentLanguageId = languageId;
    }

    public void setCopyrightMap(Map<Locale, String> copyrightMap) {
        setCopyrightMap(copyrightMap, LocaleUtil.getDefault());
    }

    public void setCopyrightMap(Map<Locale, String> copyrightMap,
        Locale defaultLocale) {
        if (copyrightMap == null) {
            return;
        }

        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(portalClassLoader);
            }

            Locale[] locales = LanguageUtil.getAvailableLocales();

            for (Locale locale : locales) {
                String copyright = copyrightMap.get(locale);

                setCopyright(copyright, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public String getRatingLevel() {
        return _ratingLevel;
    }

    public String getRatingLevel(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getRatingLevel(languageId);
    }

    public String getRatingLevel(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getRatingLevel(languageId, useDefault);
    }

    public String getRatingLevel(String languageId) {
        return LocalizationUtil.getLocalization(getRatingLevel(), languageId);
    }

    public String getRatingLevel(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getRatingLevel(), languageId,
            useDefault);
    }

    public String getRatingLevelCurrentLanguageId() {
        return _ratingLevelCurrentLanguageId;
    }

    public String getRatingLevelCurrentValue() {
        Locale locale = getLocale(_ratingLevelCurrentLanguageId);

        return getRatingLevel(locale);
    }

    public Map<Locale, String> getRatingLevelMap() {
        return LocalizationUtil.getLocalizationMap(getRatingLevel());
    }

    public void setRatingLevel(String ratingLevel) {
        _ratingLevel = ratingLevel;
    }

    public void setRatingLevel(String ratingLevel, Locale locale) {
        setRatingLevel(ratingLevel, locale, LocaleUtil.getDefault());
    }

    public void setRatingLevel(String ratingLevel, Locale locale,
        Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(ratingLevel)) {
            setRatingLevel(LocalizationUtil.updateLocalization(
                    getRatingLevel(), "RatingLevel", ratingLevel, languageId,
                    defaultLanguageId));
        } else {
            setRatingLevel(LocalizationUtil.removeLocalization(
                    getRatingLevel(), "RatingLevel", languageId));
        }
    }

    public void setRatingLevelCurrentLanguageId(String languageId) {
        _ratingLevelCurrentLanguageId = languageId;
    }

    public void setRatingLevelMap(Map<Locale, String> ratingLevelMap) {
        setRatingLevelMap(ratingLevelMap, LocaleUtil.getDefault());
    }

    public void setRatingLevelMap(Map<Locale, String> ratingLevelMap,
        Locale defaultLocale) {
        if (ratingLevelMap == null) {
            return;
        }

        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(portalClassLoader);
            }

            Locale[] locales = LanguageUtil.getAvailableLocales();

            for (Locale locale : locales) {
                String ratingLevel = ratingLevelMap.get(locale);

                setRatingLevel(ratingLevel, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public String getRatingReason() {
        return _ratingReason;
    }

    public String getRatingReason(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getRatingReason(languageId);
    }

    public String getRatingReason(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getRatingReason(languageId, useDefault);
    }

    public String getRatingReason(String languageId) {
        return LocalizationUtil.getLocalization(getRatingReason(), languageId);
    }

    public String getRatingReason(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getRatingReason(), languageId,
            useDefault);
    }

    public String getRatingReasonCurrentLanguageId() {
        return _ratingReasonCurrentLanguageId;
    }

    public String getRatingReasonCurrentValue() {
        Locale locale = getLocale(_ratingReasonCurrentLanguageId);

        return getRatingReason(locale);
    }

    public Map<Locale, String> getRatingReasonMap() {
        return LocalizationUtil.getLocalizationMap(getRatingReason());
    }

    public void setRatingReason(String ratingReason) {
        _ratingReason = ratingReason;
    }

    public void setRatingReason(String ratingReason, Locale locale) {
        setRatingReason(ratingReason, locale, LocaleUtil.getDefault());
    }

    public void setRatingReason(String ratingReason, Locale locale,
        Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(ratingReason)) {
            setRatingReason(LocalizationUtil.updateLocalization(
                    getRatingReason(), "RatingReason", ratingReason,
                    languageId, defaultLanguageId));
        } else {
            setRatingReason(LocalizationUtil.removeLocalization(
                    getRatingReason(), "RatingReason", languageId));
        }
    }

    public void setRatingReasonCurrentLanguageId(String languageId) {
        _ratingReasonCurrentLanguageId = languageId;
    }

    public void setRatingReasonMap(Map<Locale, String> ratingReasonMap) {
        setRatingReasonMap(ratingReasonMap, LocaleUtil.getDefault());
    }

    public void setRatingReasonMap(Map<Locale, String> ratingReasonMap,
        Locale defaultLocale) {
        if (ratingReasonMap == null) {
            return;
        }

        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(portalClassLoader);
            }

            Locale[] locales = LanguageUtil.getAvailableLocales();

            for (Locale locale : locales) {
                String ratingReason = ratingReasonMap.get(locale);

                setRatingReason(ratingReason, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public String getDuration() {
        return _duration;
    }

    public void setDuration(String duration) {
        _duration = duration;
    }

    public String getDurationStandard() {
        return _durationStandard;
    }

    public void setDurationStandard(String durationStandard) {
        _durationStandard = durationStandard;
    }

    public double getFeaturedStatus() {
        return _featuredStatus;
    }

    public void setFeaturedStatus(double featuredStatus) {
        _featuredStatus = featuredStatus;
    }

    public double getPopularWeight() {
        return _popularWeight;
    }

    public void setPopularWeight(double popularWeight) {
        _popularWeight = popularWeight;
    }

    public long getAccessCount() {
        return _accessCount;
    }

    public void setAccessCount(long accessCount) {
        _accessCount = accessCount;
    }

    public long getCompletedCount() {
        return _completedCount;
    }

    public void setCompletedCount(long completedCount) {
        _completedCount = completedCount;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public boolean getRemoved() {
        return _removed;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public void setRemoved(boolean removed) {
        _removed = removed;
    }

    public Date getRemovedDate() {
        return _removedDate;
    }

    public void setRemovedDate(Date removedDate) {
        _removedDate = removedDate;
    }

    public String getSupersedesCourseIri() {
        return _supersedesCourseIri;
    }

    public void setSupersedesCourseIri(String supersedesCourseIri) {
        _supersedesCourseIri = supersedesCourseIri;
    }

    public String getSupersededByCourseIri() {
        return _supersededByCourseIri;
    }

    public void setSupersededByCourseIri(String supersededByCourseIri) {
        _supersededByCourseIri = supersededByCourseIri;
    }

    public Date getReleaseOnDate() {
        return _releaseOnDate;
    }

    public void setReleaseOnDate(Date releaseOnDate) {
        _releaseOnDate = releaseOnDate;
    }

    public Date getAcceptUntilDate() {
        return _acceptUntilDate;
    }

    public void setAcceptUntilDate(Date acceptUntilDate) {
        _acceptUntilDate = acceptUntilDate;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public Date getVersionDate() {
        return _versionDate;
    }

    public void setVersionDate(Date versionDate) {
        _versionDate = versionDate;
    }

    public double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public String getPriceUnit() {
        return _priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        _priceUnit = priceUnit;
    }

    public String getPriceTerms() {
        return _priceTerms;
    }

    public void setPriceTerms(String priceTerms) {
        _priceTerms = priceTerms;
    }

    public String getPriceExpiration() {
        return _priceExpiration;
    }

    public void setPriceExpiration(String priceExpiration) {
        _priceExpiration = priceExpiration;
    }

    public long getOneStarRateCount() {
        return _oneStarRateCount;
    }

    public void setOneStarRateCount(long oneStarRateCount) {
        _oneStarRateCount = oneStarRateCount;
    }

    public long getTwoStarRateCount() {
        return _twoStarRateCount;
    }

    public void setTwoStarRateCount(long twoStarRateCount) {
        _twoStarRateCount = twoStarRateCount;
    }

    public long getThreeStarRateCount() {
        return _threeStarRateCount;
    }

    public void setThreeStarRateCount(long threeStarRateCount) {
        _threeStarRateCount = threeStarRateCount;
    }

    public long getFourStarRateCount() {
        return _fourStarRateCount;
    }

    public void setFourStarRateCount(long fourStarRateCount) {
        _fourStarRateCount = fourStarRateCount;
    }

    public long getFiveStarRateCount() {
        return _fiveStarRateCount;
    }

    public void setFiveStarRateCount(long fiveStarRateCount) {
        _fiveStarRateCount = fiveStarRateCount;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseLocalServiceUtil.addCourse(this);
        } else {
            CourseLocalServiceUtil.updateCourse(this);
        }
    }

    @Override
    public Course toEscapedModel() {
        return (Course) Proxy.newProxyInstance(Course.class.getClassLoader(),
            new Class[] { Course.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CourseClp clone = new CourseClp();

        clone.setCourseId(getCourseId());
        clone.setCompanyId(getCompanyId());
        clone.setGroupId(getGroupId());
        clone.setUserId(getUserId());
        clone.setFeedReferenceId(getFeedReferenceId());
        clone.setHref(getHref());
        clone.setFullTextHref(getFullTextHref());
        clone.setCourseIri(getCourseIri());
        clone.setUpdatedDate(getUpdatedDate());
        clone.setTitle(getTitle());
        clone.setTranscriptAbstract(getTranscriptAbstract());
        clone.setDescription(getDescription());
        clone.setKeywords(getKeywords());
        clone.setCopyright(getCopyright());
        clone.setRatingLevel(getRatingLevel());
        clone.setRatingReason(getRatingReason());
        clone.setDuration(getDuration());
        clone.setDurationStandard(getDurationStandard());
        clone.setFeaturedStatus(getFeaturedStatus());
        clone.setPopularWeight(getPopularWeight());
        clone.setAccessCount(getAccessCount());
        clone.setCompletedCount(getCompletedCount());
        clone.setCreateDate(getCreateDate());
        clone.setRemoved(getRemoved());
        clone.setRemovedDate(getRemovedDate());
        clone.setSupersedesCourseIri(getSupersedesCourseIri());
        clone.setSupersededByCourseIri(getSupersededByCourseIri());
        clone.setReleaseOnDate(getReleaseOnDate());
        clone.setAcceptUntilDate(getAcceptUntilDate());
        clone.setVersion(getVersion());
        clone.setVersionDate(getVersionDate());
        clone.setPrice(getPrice());
        clone.setPriceUnit(getPriceUnit());
        clone.setPriceTerms(getPriceTerms());
        clone.setPriceExpiration(getPriceExpiration());
        clone.setOneStarRateCount(getOneStarRateCount());
        clone.setTwoStarRateCount(getTwoStarRateCount());
        clone.setThreeStarRateCount(getThreeStarRateCount());
        clone.setFourStarRateCount(getFourStarRateCount());
        clone.setFiveStarRateCount(getFiveStarRateCount());

        return clone;
    }

    public int compareTo(Course course) {
        int value = 0;

        if (getFeaturedStatus() < course.getFeaturedStatus()) {
            value = -1;
        } else if (getFeaturedStatus() > course.getFeaturedStatus()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        CourseClp course = null;

        try {
            course = (CourseClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = course.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(81);

        sb.append("{courseId=");
        sb.append(getCourseId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", feedReferenceId=");
        sb.append(getFeedReferenceId());
        sb.append(", href=");
        sb.append(getHref());
        sb.append(", fullTextHref=");
        sb.append(getFullTextHref());
        sb.append(", courseIri=");
        sb.append(getCourseIri());
        sb.append(", updatedDate=");
        sb.append(getUpdatedDate());
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", transcriptAbstract=");
        sb.append(getTranscriptAbstract());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", keywords=");
        sb.append(getKeywords());
        sb.append(", copyright=");
        sb.append(getCopyright());
        sb.append(", ratingLevel=");
        sb.append(getRatingLevel());
        sb.append(", ratingReason=");
        sb.append(getRatingReason());
        sb.append(", duration=");
        sb.append(getDuration());
        sb.append(", durationStandard=");
        sb.append(getDurationStandard());
        sb.append(", featuredStatus=");
        sb.append(getFeaturedStatus());
        sb.append(", popularWeight=");
        sb.append(getPopularWeight());
        sb.append(", accessCount=");
        sb.append(getAccessCount());
        sb.append(", completedCount=");
        sb.append(getCompletedCount());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", removed=");
        sb.append(getRemoved());
        sb.append(", removedDate=");
        sb.append(getRemovedDate());
        sb.append(", supersedesCourseIri=");
        sb.append(getSupersedesCourseIri());
        sb.append(", supersededByCourseIri=");
        sb.append(getSupersededByCourseIri());
        sb.append(", releaseOnDate=");
        sb.append(getReleaseOnDate());
        sb.append(", acceptUntilDate=");
        sb.append(getAcceptUntilDate());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", versionDate=");
        sb.append(getVersionDate());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", priceUnit=");
        sb.append(getPriceUnit());
        sb.append(", priceTerms=");
        sb.append(getPriceTerms());
        sb.append(", priceExpiration=");
        sb.append(getPriceExpiration());
        sb.append(", oneStarRateCount=");
        sb.append(getOneStarRateCount());
        sb.append(", twoStarRateCount=");
        sb.append(getTwoStarRateCount());
        sb.append(", threeStarRateCount=");
        sb.append(getThreeStarRateCount());
        sb.append(", fourStarRateCount=");
        sb.append(getFourStarRateCount());
        sb.append(", fiveStarRateCount=");
        sb.append(getFiveStarRateCount());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(124);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.Course");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedReferenceId</column-name><column-value><![CDATA[");
        sb.append(getFeedReferenceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>href</column-name><column-value><![CDATA[");
        sb.append(getHref());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fullTextHref</column-name><column-value><![CDATA[");
        sb.append(getFullTextHref());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseIri</column-name><column-value><![CDATA[");
        sb.append(getCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updatedDate</column-name><column-value><![CDATA[");
        sb.append(getUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>transcriptAbstract</column-name><column-value><![CDATA[");
        sb.append(getTranscriptAbstract());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>keywords</column-name><column-value><![CDATA[");
        sb.append(getKeywords());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>copyright</column-name><column-value><![CDATA[");
        sb.append(getCopyright());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ratingLevel</column-name><column-value><![CDATA[");
        sb.append(getRatingLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ratingReason</column-name><column-value><![CDATA[");
        sb.append(getRatingReason());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>duration</column-name><column-value><![CDATA[");
        sb.append(getDuration());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>durationStandard</column-name><column-value><![CDATA[");
        sb.append(getDurationStandard());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>featuredStatus</column-name><column-value><![CDATA[");
        sb.append(getFeaturedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>popularWeight</column-name><column-value><![CDATA[");
        sb.append(getPopularWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accessCount</column-name><column-value><![CDATA[");
        sb.append(getAccessCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>completedCount</column-name><column-value><![CDATA[");
        sb.append(getCompletedCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>removed</column-name><column-value><![CDATA[");
        sb.append(getRemoved());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>removedDate</column-name><column-value><![CDATA[");
        sb.append(getRemovedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>supersedesCourseIri</column-name><column-value><![CDATA[");
        sb.append(getSupersedesCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>supersededByCourseIri</column-name><column-value><![CDATA[");
        sb.append(getSupersededByCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>releaseOnDate</column-name><column-value><![CDATA[");
        sb.append(getReleaseOnDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acceptUntilDate</column-name><column-value><![CDATA[");
        sb.append(getAcceptUntilDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionDate</column-name><column-value><![CDATA[");
        sb.append(getVersionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceUnit</column-name><column-value><![CDATA[");
        sb.append(getPriceUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceTerms</column-name><column-value><![CDATA[");
        sb.append(getPriceTerms());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceExpiration</column-name><column-value><![CDATA[");
        sb.append(getPriceExpiration());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>oneStarRateCount</column-name><column-value><![CDATA[");
        sb.append(getOneStarRateCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>twoStarRateCount</column-name><column-value><![CDATA[");
        sb.append(getTwoStarRateCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>threeStarRateCount</column-name><column-value><![CDATA[");
        sb.append(getThreeStarRateCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fourStarRateCount</column-name><column-value><![CDATA[");
        sb.append(getFourStarRateCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fiveStarRateCount</column-name><column-value><![CDATA[");
        sb.append(getFiveStarRateCount());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
