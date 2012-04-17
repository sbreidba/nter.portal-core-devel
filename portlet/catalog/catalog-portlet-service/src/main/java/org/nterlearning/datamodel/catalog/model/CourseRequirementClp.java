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

import org.nterlearning.datamodel.catalog.service.CourseRequirementLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Locale;
import java.util.Map;


public class CourseRequirementClp extends BaseModelImpl<CourseRequirement>
    implements CourseRequirement {
    private long _courseRequirementId;
    private long _courseId;
    private String _requirementType;
    private String _requirementValue;
    private String _requirementValueCurrentLanguageId;

    public CourseRequirementClp() {
    }

    public Class<?> getModelClass() {
        return CourseRequirement.class;
    }

    public String getModelClassName() {
        return CourseRequirement.class.getName();
    }

    public long getPrimaryKey() {
        return _courseRequirementId;
    }

    public void setPrimaryKey(long primaryKey) {
        setCourseRequirementId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_courseRequirementId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getCourseRequirementId() {
        return _courseRequirementId;
    }

    public void setCourseRequirementId(long courseRequirementId) {
        _courseRequirementId = courseRequirementId;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public String getRequirementType() {
        return _requirementType;
    }

    public void setRequirementType(String requirementType) {
        _requirementType = requirementType;
    }

    public String getRequirementValue() {
        return _requirementValue;
    }

    public String getRequirementValue(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getRequirementValue(languageId);
    }

    public String getRequirementValue(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getRequirementValue(languageId, useDefault);
    }

    public String getRequirementValue(String languageId) {
        return LocalizationUtil.getLocalization(getRequirementValue(),
            languageId);
    }

    public String getRequirementValue(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getRequirementValue(),
            languageId, useDefault);
    }

    public String getRequirementValueCurrentLanguageId() {
        return _requirementValueCurrentLanguageId;
    }

    public String getRequirementValueCurrentValue() {
        Locale locale = getLocale(_requirementValueCurrentLanguageId);

        return getRequirementValue(locale);
    }

    public Map<Locale, String> getRequirementValueMap() {
        return LocalizationUtil.getLocalizationMap(getRequirementValue());
    }

    public void setRequirementValue(String requirementValue) {
        _requirementValue = requirementValue;
    }

    public void setRequirementValue(String requirementValue, Locale locale) {
        setRequirementValue(requirementValue, locale, LocaleUtil.getDefault());
    }

    public void setRequirementValue(String requirementValue, Locale locale,
        Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(requirementValue)) {
            setRequirementValue(LocalizationUtil.updateLocalization(
                    getRequirementValue(), "RequirementValue",
                    requirementValue, languageId, defaultLanguageId));
        } else {
            setRequirementValue(LocalizationUtil.removeLocalization(
                    getRequirementValue(), "RequirementValue", languageId));
        }
    }

    public void setRequirementValueCurrentLanguageId(String languageId) {
        _requirementValueCurrentLanguageId = languageId;
    }

    public void setRequirementValueMap(Map<Locale, String> requirementValueMap) {
        setRequirementValueMap(requirementValueMap, LocaleUtil.getDefault());
    }

    public void setRequirementValueMap(
        Map<Locale, String> requirementValueMap, Locale defaultLocale) {
        if (requirementValueMap == null) {
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
                String requirementValue = requirementValueMap.get(locale);

                setRequirementValue(requirementValue, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseRequirementLocalServiceUtil.addCourseRequirement(this);
        } else {
            CourseRequirementLocalServiceUtil.updateCourseRequirement(this);
        }
    }

    @Override
    public CourseRequirement toEscapedModel() {
        return (CourseRequirement) Proxy.newProxyInstance(CourseRequirement.class.getClassLoader(),
            new Class[] { CourseRequirement.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CourseRequirementClp clone = new CourseRequirementClp();

        clone.setCourseRequirementId(getCourseRequirementId());
        clone.setCourseId(getCourseId());
        clone.setRequirementType(getRequirementType());
        clone.setRequirementValue(getRequirementValue());

        return clone;
    }

    public int compareTo(CourseRequirement courseRequirement) {
        long primaryKey = courseRequirement.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        CourseRequirementClp courseRequirement = null;

        try {
            courseRequirement = (CourseRequirementClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = courseRequirement.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{courseRequirementId=");
        sb.append(getCourseRequirementId());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", requirementType=");
        sb.append(getRequirementType());
        sb.append(", requirementValue=");
        sb.append(getRequirementValue());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.CourseRequirement");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>courseRequirementId</column-name><column-value><![CDATA[");
        sb.append(getCourseRequirementId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>requirementType</column-name><column-value><![CDATA[");
        sb.append(getRequirementType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>requirementValue</column-name><column-value><![CDATA[");
        sb.append(getRequirementValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
