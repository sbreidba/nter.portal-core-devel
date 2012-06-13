package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import org.nterlearning.datamodel.catalog.model.ComponentClp;
import org.nterlearning.datamodel.catalog.model.ComponentRecordClp;
import org.nterlearning.datamodel.catalog.model.ContributorClp;
import org.nterlearning.datamodel.catalog.model.CourseClp;
import org.nterlearning.datamodel.catalog.model.CourseImageClp;
import org.nterlearning.datamodel.catalog.model.CourseRecordClp;
import org.nterlearning.datamodel.catalog.model.CourseRelatedClp;
import org.nterlearning.datamodel.catalog.model.CourseRequirementClp;
import org.nterlearning.datamodel.catalog.model.CourseReviewClp;
import org.nterlearning.datamodel.catalog.model.Courses_ComponentsClp;
import org.nterlearning.datamodel.catalog.model.ExternalLinkClp;
import org.nterlearning.datamodel.catalog.model.FeedReferenceClp;
import org.nterlearning.datamodel.catalog.model.FeedSyncHistoryClp;
import org.nterlearning.datamodel.catalog.model.FlagReportClp;
import org.nterlearning.datamodel.catalog.model.FlagReportStatsClp;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReviewClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ClpSerializer {
    private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
    private static ClassLoader _classLoader;
    private static String _servletContextName;

    public static String getServletContextName() {
        if (Validator.isNotNull(_servletContextName)) {
            return _servletContextName;
        }

        synchronized (ClpSerializer.class) {
            if (Validator.isNotNull(_servletContextName)) {
                return _servletContextName;
            }

            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Class<?> portletPropsClass = classLoader.loadClass(
                        "com.liferay.util.portlet.PortletProps");

                Method getMethod = portletPropsClass.getMethod("get",
                        new Class<?>[] { String.class });

                String portletPropsServletContextName = (String) getMethod.invoke(null,
                        "nter-catalog-portlet-deployment-context");

                if (Validator.isNotNull(portletPropsServletContextName)) {
                    _servletContextName = portletPropsServletContextName;
                }
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info(
                        "Unable to locate deployment context from portlet properties");
                }
            }

            if (Validator.isNull(_servletContextName)) {
                try {
                    String propsUtilServletContextName = PropsUtil.get(
                            "nter-catalog-portlet-deployment-context");

                    if (Validator.isNotNull(propsUtilServletContextName)) {
                        _servletContextName = propsUtilServletContextName;
                    }
                } catch (Throwable t) {
                    if (_log.isInfoEnabled()) {
                        _log.info(
                            "Unable to locate deployment context from portal properties");
                    }
                }
            }

            if (Validator.isNull(_servletContextName)) {
                _servletContextName = "nter-catalog-portlet";
            }

            return _servletContextName;
        }
    }

    public static void setClassLoader(ClassLoader classLoader) {
        _classLoader = classLoader;
    }

    public static Object translateInput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(ComponentClp.class.getName())) {
            return translateInputComponent(oldModel);
        }

        if (oldModelClassName.equals(ComponentRecordClp.class.getName())) {
            return translateInputComponentRecord(oldModel);
        }

        if (oldModelClassName.equals(ContributorClp.class.getName())) {
            return translateInputContributor(oldModel);
        }

        if (oldModelClassName.equals(CourseClp.class.getName())) {
            return translateInputCourse(oldModel);
        }

        if (oldModelClassName.equals(CourseImageClp.class.getName())) {
            return translateInputCourseImage(oldModel);
        }

        if (oldModelClassName.equals(CourseRecordClp.class.getName())) {
            return translateInputCourseRecord(oldModel);
        }

        if (oldModelClassName.equals(CourseRelatedClp.class.getName())) {
            return translateInputCourseRelated(oldModel);
        }

        if (oldModelClassName.equals(CourseRequirementClp.class.getName())) {
            return translateInputCourseRequirement(oldModel);
        }

        if (oldModelClassName.equals(CourseReviewClp.class.getName())) {
            return translateInputCourseReview(oldModel);
        }

        if (oldModelClassName.equals(Courses_ComponentsClp.class.getName())) {
            return translateInputCourses_Components(oldModel);
        }

        if (oldModelClassName.equals(ExternalLinkClp.class.getName())) {
            return translateInputExternalLink(oldModel);
        }

        if (oldModelClassName.equals(FeedReferenceClp.class.getName())) {
            return translateInputFeedReference(oldModel);
        }

        if (oldModelClassName.equals(FeedSyncHistoryClp.class.getName())) {
            return translateInputFeedSyncHistory(oldModel);
        }

        if (oldModelClassName.equals(FlagReportClp.class.getName())) {
            return translateInputFlagReport(oldModel);
        }

        if (oldModelClassName.equals(FlagReportStatsClp.class.getName())) {
            return translateInputFlagReportStats(oldModel);
        }

        if (oldModelClassName.equals(GlobalCourseReviewClp.class.getName())) {
            return translateInputGlobalCourseReview(oldModel);
        }

        return oldModel;
    }

    public static Object translateInput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateInput(curObj));
        }

        return newList;
    }

    public static Object translateInputComponent(BaseModel<?> oldModel) {
        ComponentClp oldCplModel = (ComponentClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.ComponentImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setComponentId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getComponentId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCompanyId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCompanyId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getGroupId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setFeedReferenceId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getFeedReferenceId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setComponentIri",
                        new Class[] { String.class });

                String value4 = oldCplModel.getComponentIri();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setUpdatedDate",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getUpdatedDate();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setLanguage",
                        new Class[] { String.class });

                String value6 = oldCplModel.getLanguage();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setHref",
                        new Class[] { String.class });

                String value7 = oldCplModel.getHref();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setFullTextHref",
                        new Class[] { String.class });

                String value8 = oldCplModel.getFullTextHref();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setTitle",
                        new Class[] { String.class });

                String value9 = oldCplModel.getTitle();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value10 = oldCplModel.getDescription();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setCopyright",
                        new Class[] { String.class });

                String value11 = oldCplModel.getCopyright();

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setDisplayWidth",
                        new Class[] { Integer.TYPE });

                Integer value12 = new Integer(oldCplModel.getDisplayWidth());

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setDisplayHeight",
                        new Class[] { Integer.TYPE });

                Integer value13 = new Integer(oldCplModel.getDisplayHeight());

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value14 = oldCplModel.getCreateDate();

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setRemoved",
                        new Class[] { Boolean.TYPE });

                Boolean value15 = new Boolean(oldCplModel.getRemoved());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setRemovedDate",
                        new Class[] { Date.class });

                Date value16 = oldCplModel.getRemovedDate();

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setVersion",
                        new Class[] { String.class });

                String value17 = oldCplModel.getVersion();

                method17.invoke(newModel, value17);

                Method method18 = newModelClass.getMethod("setVersionDate",
                        new Class[] { Date.class });

                Date value18 = oldCplModel.getVersionDate();

                method18.invoke(newModel, value18);

                Method method19 = newModelClass.getMethod("setPrice",
                        new Class[] { Double.TYPE });

                Double value19 = new Double(oldCplModel.getPrice());

                method19.invoke(newModel, value19);

                Method method20 = newModelClass.getMethod("setPriceUnit",
                        new Class[] { String.class });

                String value20 = oldCplModel.getPriceUnit();

                method20.invoke(newModel, value20);

                Method method21 = newModelClass.getMethod("setPriceTerms",
                        new Class[] { String.class });

                String value21 = oldCplModel.getPriceTerms();

                method21.invoke(newModel, value21);

                Method method22 = newModelClass.getMethod("setPriceExpiration",
                        new Class[] { String.class });

                String value22 = oldCplModel.getPriceExpiration();

                method22.invoke(newModel, value22);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputComponentRecord(BaseModel<?> oldModel) {
        ComponentRecordClp oldCplModel = (ComponentRecordClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setComponentRecordId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getComponentRecordId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCourseRecordId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCourseRecordId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setComponentIri",
                        new Class[] { String.class });

                String value2 = oldCplModel.getComponentIri();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUpdatedDate",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getUpdatedDate();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCompletionStatus",
                        new Class[] { String.class });

                String value4 = oldCplModel.getCompletionStatus();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCompletionPercent",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getCompletionPercent());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputContributor(BaseModel<?> oldModel) {
        ContributorClp oldCplModel = (ContributorClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.ContributorImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setContributorId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getContributorId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCourseId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setComponentId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getComponentId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setRole",
                        new Class[] { String.class });

                String value3 = oldCplModel.getRole();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setContributorName",
                        new Class[] { String.class });

                String value4 = oldCplModel.getContributorName();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setVirtualCardData",
                        new Class[] { String.class });

                String value5 = oldCplModel.getVirtualCardData();

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputCourse(BaseModel<?> oldModel) {
        CourseClp oldCplModel = (CourseClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.CourseImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getCourseId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCompanyId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCompanyId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getGroupId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getUserId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setFeedReferenceId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getFeedReferenceId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setHref",
                        new Class[] { String.class });

                String value5 = oldCplModel.getHref();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setFullTextHref",
                        new Class[] { String.class });

                String value6 = oldCplModel.getFullTextHref();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setCourseIri",
                        new Class[] { String.class });

                String value7 = oldCplModel.getCourseIri();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setUpdatedDate",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getUpdatedDate();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setTitle",
                        new Class[] { String.class });

                String value9 = oldCplModel.getTitle();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setTranscriptAbstract",
                        new Class[] { String.class });

                String value10 = oldCplModel.getTranscriptAbstract();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value11 = oldCplModel.getDescription();

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setKeywords",
                        new Class[] { String.class });

                String value12 = oldCplModel.getKeywords();

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setCopyright",
                        new Class[] { String.class });

                String value13 = oldCplModel.getCopyright();

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setRatingLevel",
                        new Class[] { String.class });

                String value14 = oldCplModel.getRatingLevel();

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setRatingReason",
                        new Class[] { String.class });

                String value15 = oldCplModel.getRatingReason();

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setDuration",
                        new Class[] { String.class });

                String value16 = oldCplModel.getDuration();

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setDurationStandard",
                        new Class[] { String.class });

                String value17 = oldCplModel.getDurationStandard();

                method17.invoke(newModel, value17);

                Method method18 = newModelClass.getMethod("setFeaturedStatus",
                        new Class[] { Double.TYPE });

                Double value18 = new Double(oldCplModel.getFeaturedStatus());

                method18.invoke(newModel, value18);

                Method method19 = newModelClass.getMethod("setPopularWeight",
                        new Class[] { Double.TYPE });

                Double value19 = new Double(oldCplModel.getPopularWeight());

                method19.invoke(newModel, value19);

                Method method20 = newModelClass.getMethod("setAccessCount",
                        new Class[] { Long.TYPE });

                Long value20 = new Long(oldCplModel.getAccessCount());

                method20.invoke(newModel, value20);

                Method method21 = newModelClass.getMethod("setCompletedCount",
                        new Class[] { Long.TYPE });

                Long value21 = new Long(oldCplModel.getCompletedCount());

                method21.invoke(newModel, value21);

                Method method22 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value22 = oldCplModel.getCreateDate();

                method22.invoke(newModel, value22);

                Method method23 = newModelClass.getMethod("setRemoved",
                        new Class[] { Boolean.TYPE });

                Boolean value23 = new Boolean(oldCplModel.getRemoved());

                method23.invoke(newModel, value23);

                Method method24 = newModelClass.getMethod("setRemovedDate",
                        new Class[] { Date.class });

                Date value24 = oldCplModel.getRemovedDate();

                method24.invoke(newModel, value24);

                Method method25 = newModelClass.getMethod("setSupersedesCourseIri",
                        new Class[] { String.class });

                String value25 = oldCplModel.getSupersedesCourseIri();

                method25.invoke(newModel, value25);

                Method method26 = newModelClass.getMethod("setSupersededByCourseIri",
                        new Class[] { String.class });

                String value26 = oldCplModel.getSupersededByCourseIri();

                method26.invoke(newModel, value26);

                Method method27 = newModelClass.getMethod("setReleaseOnDate",
                        new Class[] { Date.class });

                Date value27 = oldCplModel.getReleaseOnDate();

                method27.invoke(newModel, value27);

                Method method28 = newModelClass.getMethod("setAcceptUntilDate",
                        new Class[] { Date.class });

                Date value28 = oldCplModel.getAcceptUntilDate();

                method28.invoke(newModel, value28);

                Method method29 = newModelClass.getMethod("setVersion",
                        new Class[] { String.class });

                String value29 = oldCplModel.getVersion();

                method29.invoke(newModel, value29);

                Method method30 = newModelClass.getMethod("setVersionDate",
                        new Class[] { Date.class });

                Date value30 = oldCplModel.getVersionDate();

                method30.invoke(newModel, value30);

                Method method31 = newModelClass.getMethod("setPrice",
                        new Class[] { Double.TYPE });

                Double value31 = new Double(oldCplModel.getPrice());

                method31.invoke(newModel, value31);

                Method method32 = newModelClass.getMethod("setPriceUnit",
                        new Class[] { String.class });

                String value32 = oldCplModel.getPriceUnit();

                method32.invoke(newModel, value32);

                Method method33 = newModelClass.getMethod("setPriceTerms",
                        new Class[] { String.class });

                String value33 = oldCplModel.getPriceTerms();

                method33.invoke(newModel, value33);

                Method method34 = newModelClass.getMethod("setPriceExpiration",
                        new Class[] { String.class });

                String value34 = oldCplModel.getPriceExpiration();

                method34.invoke(newModel, value34);

                Method method35 = newModelClass.getMethod("setOneStarRateCount",
                        new Class[] { Long.TYPE });

                Long value35 = new Long(oldCplModel.getOneStarRateCount());

                method35.invoke(newModel, value35);

                Method method36 = newModelClass.getMethod("setTwoStarRateCount",
                        new Class[] { Long.TYPE });

                Long value36 = new Long(oldCplModel.getTwoStarRateCount());

                method36.invoke(newModel, value36);

                Method method37 = newModelClass.getMethod("setThreeStarRateCount",
                        new Class[] { Long.TYPE });

                Long value37 = new Long(oldCplModel.getThreeStarRateCount());

                method37.invoke(newModel, value37);

                Method method38 = newModelClass.getMethod("setFourStarRateCount",
                        new Class[] { Long.TYPE });

                Long value38 = new Long(oldCplModel.getFourStarRateCount());

                method38.invoke(newModel, value38);

                Method method39 = newModelClass.getMethod("setFiveStarRateCount",
                        new Class[] { Long.TYPE });

                Long value39 = new Long(oldCplModel.getFiveStarRateCount());

                method39.invoke(newModel, value39);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputCourseImage(BaseModel<?> oldModel) {
        CourseImageClp oldCplModel = (CourseImageClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setCourseImageId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getCourseImageId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCourseId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setOrderWeight",
                        new Class[] { Double.TYPE });

                Double value2 = new Double(oldCplModel.getOrderWeight());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setLanguage",
                        new Class[] { String.class });

                String value3 = oldCplModel.getLanguage();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setImageId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getImageId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setAlternateText",
                        new Class[] { String.class });

                String value5 = oldCplModel.getAlternateText();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setSourceImageUrl",
                        new Class[] { String.class });

                String value6 = oldCplModel.getSourceImageUrl();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setMimeType",
                        new Class[] { String.class });

                String value7 = oldCplModel.getMimeType();

                method7.invoke(newModel, value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputCourseRecord(BaseModel<?> oldModel) {
        CourseRecordClp oldCplModel = (CourseRecordClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setCourseRecordId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getCourseRecordId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setFeedReferenceId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getFeedReferenceId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCourseRecordIri",
                        new Class[] { String.class });

                String value2 = oldCplModel.getCourseRecordIri();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getUserId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setSingleSignOnValue",
                        new Class[] { String.class });

                String value4 = oldCplModel.getSingleSignOnValue();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCourseIri",
                        new Class[] { String.class });

                String value5 = oldCplModel.getCourseIri();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setUpdatedDate",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getUpdatedDate();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setCompletionStatus",
                        new Class[] { String.class });

                String value7 = oldCplModel.getCompletionStatus();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setRemoved",
                        new Class[] { Boolean.TYPE });

                Boolean value8 = new Boolean(oldCplModel.getRemoved());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setRemovedDate",
                        new Class[] { Date.class });

                Date value9 = oldCplModel.getRemovedDate();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setUserHidden",
                        new Class[] { Boolean.TYPE });

                Boolean value10 = new Boolean(oldCplModel.getUserHidden());

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setAssigned",
                        new Class[] { Boolean.TYPE });

                Boolean value11 = new Boolean(oldCplModel.getAssigned());

                method11.invoke(newModel, value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputCourseRelated(BaseModel<?> oldModel) {
        CourseRelatedClp oldCplModel = (CourseRelatedClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.CourseRelatedImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setCourseRelatedId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getCourseRelatedId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCourseId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setRelatedCourseId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getRelatedCourseId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setRelatedCourseIri",
                        new Class[] { String.class });

                String value3 = oldCplModel.getRelatedCourseIri();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setRelationshipType",
                        new Class[] { String.class });

                String value4 = oldCplModel.getRelationshipType();

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputCourseRequirement(BaseModel<?> oldModel) {
        CourseRequirementClp oldCplModel = (CourseRequirementClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.CourseRequirementImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setCourseRequirementId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getCourseRequirementId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCourseId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setRequirementType",
                        new Class[] { String.class });

                String value2 = oldCplModel.getRequirementType();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setRequirementValue",
                        new Class[] { String.class });

                String value3 = oldCplModel.getRequirementValue();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputCourseReview(BaseModel<?> oldModel) {
        CourseReviewClp oldCplModel = (CourseReviewClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.CourseReviewImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setCourseReviewId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getCourseReviewId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCompanyId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCompanyId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getGroupId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getCourseId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getUserId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setSummary",
                        new Class[] { String.class });

                String value5 = oldCplModel.getSummary();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setContent",
                        new Class[] { String.class });

                String value6 = oldCplModel.getContent();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value7 = oldCplModel.getCreateDate();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setModifiedDate",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getModifiedDate();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setWeightedScore",
                        new Class[] { Double.TYPE });

                Double value9 = new Double(oldCplModel.getWeightedScore());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setRemoved",
                        new Class[] { Boolean.TYPE });

                Boolean value10 = new Boolean(oldCplModel.getRemoved());

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setRemovedDate",
                        new Class[] { Date.class });

                Date value11 = oldCplModel.getRemovedDate();

                method11.invoke(newModel, value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputCourses_Components(BaseModel<?> oldModel) {
        Courses_ComponentsClp oldCplModel = (Courses_ComponentsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setCoursesComponentsId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getCoursesComponentsId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCourseId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCourseIri",
                        new Class[] { String.class });

                String value2 = oldCplModel.getCourseIri();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setComponentId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getComponentId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setComponentIri",
                        new Class[] { String.class });

                String value4 = oldCplModel.getComponentIri();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setOrderWeight",
                        new Class[] { Double.TYPE });

                Double value5 = new Double(oldCplModel.getOrderWeight());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setSectionType",
                        new Class[] { String.class });

                String value6 = oldCplModel.getSectionType();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setComponentType",
                        new Class[] { String.class });

                String value7 = oldCplModel.getComponentType();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setMimeType",
                        new Class[] { String.class });

                String value8 = oldCplModel.getMimeType();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setCoursePaymentRequired",
                        new Class[] { Boolean.TYPE });

                Boolean value9 = new Boolean(oldCplModel.getCoursePaymentRequired());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setComponentPaymentRequired",
                        new Class[] { Boolean.TYPE });

                Boolean value10 = new Boolean(oldCplModel.getComponentPaymentRequired());

                method10.invoke(newModel, value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputExternalLink(BaseModel<?> oldModel) {
        ExternalLinkClp oldCplModel = (ExternalLinkClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setLinkId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getLinkId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCourseId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setComponentId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getComponentId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setLinkType",
                        new Class[] { String.class });

                String value3 = oldCplModel.getLinkType();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setLinkUrl",
                        new Class[] { String.class });

                String value4 = oldCplModel.getLinkUrl();

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputFeedReference(BaseModel<?> oldModel) {
        FeedReferenceClp oldCplModel = (FeedReferenceClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.FeedReferenceImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setFeedReferenceId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getFeedReferenceId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCompanyId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCompanyId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getGroupId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setContentProviderId",
                        new Class[] { String.class });

                String value3 = oldCplModel.getContentProviderId();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setHref",
                        new Class[] { String.class });

                String value4 = oldCplModel.getHref();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setHrefHash",
                        new Class[] { String.class });

                String value5 = oldCplModel.getHrefHash();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPshb",
                        new Class[] { String.class });

                String value6 = oldCplModel.getPshb();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setPshbSubscribed",
                        new Class[] { Boolean.TYPE });

                Boolean value7 = new Boolean(oldCplModel.getPshbSubscribed());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setFeedIri",
                        new Class[] { String.class });

                String value8 = oldCplModel.getFeedIri();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setFeedType",
                        new Class[] { String.class });

                String value9 = oldCplModel.getFeedType();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setFeedVersion",
                        new Class[] { String.class });

                String value10 = oldCplModel.getFeedVersion();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setTrustworthyWeight",
                        new Class[] { Double.TYPE });

                Double value11 = new Double(oldCplModel.getTrustworthyWeight());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value12 = oldCplModel.getCreateDate();

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setRemoved",
                        new Class[] { Boolean.TYPE });

                Boolean value13 = new Boolean(oldCplModel.getRemoved());

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setRemovedDate",
                        new Class[] { Date.class });

                Date value14 = oldCplModel.getRemovedDate();

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setRemovedReason",
                        new Class[] { String.class });

                String value15 = oldCplModel.getRemovedReason();

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setSyncDate",
                        new Class[] { Date.class });

                Date value16 = oldCplModel.getSyncDate();

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setSyncSuccess",
                        new Class[] { Boolean.TYPE });

                Boolean value17 = new Boolean(oldCplModel.getSyncSuccess());

                method17.invoke(newModel, value17);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputFeedSyncHistory(BaseModel<?> oldModel) {
        FeedSyncHistoryClp oldCplModel = (FeedSyncHistoryClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.FeedSyncHistoryImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setSyncId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getSyncId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setFeedReferenceId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getFeedReferenceId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setSyncDate",
                        new Class[] { Date.class });

                Date value2 = oldCplModel.getSyncDate();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setSuccess",
                        new Class[] { Boolean.TYPE });

                Boolean value3 = new Boolean(oldCplModel.getSuccess());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setSyncMessage",
                        new Class[] { String.class });

                String value4 = oldCplModel.getSyncMessage();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setNumberOfEntries",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getNumberOfEntries());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputFlagReport(BaseModel<?> oldModel) {
        FlagReportClp oldCplModel = (FlagReportClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.FlagReportImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setUuid",
                        new Class[] { String.class });

                String value0 = oldCplModel.getUuid();

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setFlagReportId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getFlagReportId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getGroupId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCompanyId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getCompanyId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getUserId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setClassNameId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getClassNameId());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setClassPK",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getClassPK());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value7 = oldCplModel.getCreateDate();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setTitle",
                        new Class[] { String.class });

                String value8 = oldCplModel.getTitle();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setContent",
                        new Class[] { String.class });

                String value9 = oldCplModel.getContent();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setFlagReason",
                        new Class[] { String.class });

                String value10 = oldCplModel.getFlagReason();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setFlagComment",
                        new Class[] { String.class });

                String value11 = oldCplModel.getFlagComment();

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setModerateAction",
                        new Class[] { String.class });

                String value12 = oldCplModel.getModerateAction();

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setModeratorComment",
                        new Class[] { String.class });

                String value13 = oldCplModel.getModeratorComment();

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setStatus",
                        new Class[] { Integer.TYPE });

                Integer value14 = new Integer(oldCplModel.getStatus());

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setStatusByUserId",
                        new Class[] { Long.TYPE });

                Long value15 = new Long(oldCplModel.getStatusByUserId());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setStatusByUserName",
                        new Class[] { String.class });

                String value16 = oldCplModel.getStatusByUserName();

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setStatusDate",
                        new Class[] { Date.class });

                Date value17 = oldCplModel.getStatusDate();

                method17.invoke(newModel, value17);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputFlagReportStats(BaseModel<?> oldModel) {
        FlagReportStatsClp oldCplModel = (FlagReportStatsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.FlagReportStatsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setFlagReportStatsId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getFlagReportStatsId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setClassNameId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getClassNameId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setClassPK",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getClassPK());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setTotalEntries",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getTotalEntries());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setTotalModerated",
                        new Class[] { Integer.TYPE });

                Integer value4 = new Integer(oldCplModel.getTotalModerated());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setTotalApproved",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getTotalApproved());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputGlobalCourseReview(BaseModel<?> oldModel) {
        GlobalCourseReviewClp oldCplModel = (GlobalCourseReviewClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setGlobalCourseReviewId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getGlobalCourseReviewId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCompanyId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCompanyId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getGroupId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCourseReviewIri",
                        new Class[] { String.class });

                String value3 = oldCplModel.getCourseReviewIri();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setUpdatedDate",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getUpdatedDate();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCourseIri",
                        new Class[] { String.class });

                String value5 = oldCplModel.getCourseIri();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setHref",
                        new Class[] { String.class });

                String value6 = oldCplModel.getHref();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setNterInstance",
                        new Class[] { String.class });

                String value7 = oldCplModel.getNterInstance();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setCourseId",
                        new Class[] { Long.TYPE });

                Long value8 = new Long(oldCplModel.getCourseId());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setUserDisplayName",
                        new Class[] { String.class });

                String value9 = oldCplModel.getUserDisplayName();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setSingleSignOnValue",
                        new Class[] { String.class });

                String value10 = oldCplModel.getSingleSignOnValue();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setSummary",
                        new Class[] { String.class });

                String value11 = oldCplModel.getSummary();

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setContent",
                        new Class[] { String.class });

                String value12 = oldCplModel.getContent();

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value13 = oldCplModel.getCreateDate();

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setModifiedDate",
                        new Class[] { Date.class });

                Date value14 = oldCplModel.getModifiedDate();

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setStarScore",
                        new Class[] { Double.TYPE });

                Double value15 = new Double(oldCplModel.getStarScore());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setFromTrustedReviewer",
                        new Class[] { Boolean.TYPE });

                Boolean value16 = new Boolean(oldCplModel.getFromTrustedReviewer());

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setRemoved",
                        new Class[] { Boolean.TYPE });

                Boolean value17 = new Boolean(oldCplModel.getRemoved());

                method17.invoke(newModel, value17);

                Method method18 = newModelClass.getMethod("setRemovedDate",
                        new Class[] { Date.class });

                Date value18 = oldCplModel.getRemovedDate();

                method18.invoke(newModel, value18);

                Method method19 = newModelClass.getMethod("setIsHidden",
                        new Class[] { Boolean.TYPE });

                Boolean value19 = new Boolean(oldCplModel.getIsHidden());

                method19.invoke(newModel, value19);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateInput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateInput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Object translateOutput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.ComponentImpl")) {
            return translateOutputComponent(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl")) {
            return translateOutputComponentRecord(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.ContributorImpl")) {
            return translateOutputContributor(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.CourseImpl")) {
            return translateOutputCourse(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl")) {
            return translateOutputCourseImage(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl")) {
            return translateOutputCourseRecord(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.CourseRelatedImpl")) {
            return translateOutputCourseRelated(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.CourseRequirementImpl")) {
            return translateOutputCourseRequirement(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.CourseReviewImpl")) {
            return translateOutputCourseReview(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl")) {
            return translateOutputCourses_Components(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl")) {
            return translateOutputExternalLink(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.FeedReferenceImpl")) {
            return translateOutputFeedReference(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.FeedSyncHistoryImpl")) {
            return translateOutputFeedSyncHistory(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.FlagReportImpl")) {
            return translateOutputFlagReport(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.FlagReportStatsImpl")) {
            return translateOutputFlagReportStats(oldModel);
        }

        if (oldModelClassName.equals(
                    "org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl")) {
            return translateOutputGlobalCourseReview(oldModel);
        }

        return oldModel;
    }

    public static Object translateOutput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateOutput(curObj));
        }

        return newList;
    }

    public static Object translateOutput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateOutput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateOutput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Object translateOutputComponent(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ComponentClp newModel = new ComponentClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getComponentId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setComponentId(value0);

                Method method1 = oldModelClass.getMethod("getCompanyId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCompanyId(value1);

                Method method2 = oldModelClass.getMethod("getGroupId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value2);

                Method method3 = oldModelClass.getMethod("getFeedReferenceId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setFeedReferenceId(value3);

                Method method4 = oldModelClass.getMethod("getComponentIri");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setComponentIri(value4);

                Method method5 = oldModelClass.getMethod("getUpdatedDate");

                Date value5 = (Date) method5.invoke(oldModel, (Object[]) null);

                newModel.setUpdatedDate(value5);

                Method method6 = oldModelClass.getMethod("getLanguage");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setLanguage(value6);

                Method method7 = oldModelClass.getMethod("getHref");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setHref(value7);

                Method method8 = oldModelClass.getMethod("getFullTextHref");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setFullTextHref(value8);

                Method method9 = oldModelClass.getMethod("getTitle");

                String value9 = (String) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setTitle(value9);

                Method method10 = oldModelClass.getMethod("getDescription");

                String value10 = (String) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value10);

                Method method11 = oldModelClass.getMethod("getCopyright");

                String value11 = (String) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setCopyright(value11);

                Method method11CurrentLanguageId = oldModelClass.getMethod(
                        "getCopyrightCurrentLanguageId");

                String value11CurrentLanguageId = (String) method11CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setCopyrightCurrentLanguageId(value11CurrentLanguageId);

                Method method12 = oldModelClass.getMethod("getDisplayWidth");

                Integer value12 = (Integer) method12.invoke(oldModel,
                        (Object[]) null);

                newModel.setDisplayWidth(value12);

                Method method13 = oldModelClass.getMethod("getDisplayHeight");

                Integer value13 = (Integer) method13.invoke(oldModel,
                        (Object[]) null);

                newModel.setDisplayHeight(value13);

                Method method14 = oldModelClass.getMethod("getCreateDate");

                Date value14 = (Date) method14.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value14);

                Method method15 = oldModelClass.getMethod("getRemoved");

                Boolean value15 = (Boolean) method15.invoke(oldModel,
                        (Object[]) null);

                newModel.setRemoved(value15);

                Method method16 = oldModelClass.getMethod("getRemovedDate");

                Date value16 = (Date) method16.invoke(oldModel, (Object[]) null);

                newModel.setRemovedDate(value16);

                Method method17 = oldModelClass.getMethod("getVersion");

                String value17 = (String) method17.invoke(oldModel,
                        (Object[]) null);

                newModel.setVersion(value17);

                Method method18 = oldModelClass.getMethod("getVersionDate");

                Date value18 = (Date) method18.invoke(oldModel, (Object[]) null);

                newModel.setVersionDate(value18);

                Method method19 = oldModelClass.getMethod("getPrice");

                Double value19 = (Double) method19.invoke(oldModel,
                        (Object[]) null);

                newModel.setPrice(value19);

                Method method20 = oldModelClass.getMethod("getPriceUnit");

                String value20 = (String) method20.invoke(oldModel,
                        (Object[]) null);

                newModel.setPriceUnit(value20);

                Method method21 = oldModelClass.getMethod("getPriceTerms");

                String value21 = (String) method21.invoke(oldModel,
                        (Object[]) null);

                newModel.setPriceTerms(value21);

                Method method22 = oldModelClass.getMethod("getPriceExpiration");

                String value22 = (String) method22.invoke(oldModel,
                        (Object[]) null);

                newModel.setPriceExpiration(value22);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputComponentRecord(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ComponentRecordClp newModel = new ComponentRecordClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getComponentRecordId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setComponentRecordId(value0);

                Method method1 = oldModelClass.getMethod("getCourseRecordId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCourseRecordId(value1);

                Method method2 = oldModelClass.getMethod("getComponentIri");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setComponentIri(value2);

                Method method3 = oldModelClass.getMethod("getUpdatedDate");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setUpdatedDate(value3);

                Method method4 = oldModelClass.getMethod("getCompletionStatus");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setCompletionStatus(value4);

                Method method5 = oldModelClass.getMethod("getCompletionPercent");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setCompletionPercent(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContributor(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContributorClp newModel = new ContributorClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getContributorId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setContributorId(value0);

                Method method1 = oldModelClass.getMethod("getCourseId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value1);

                Method method2 = oldModelClass.getMethod("getComponentId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setComponentId(value2);

                Method method3 = oldModelClass.getMethod("getRole");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setRole(value3);

                Method method4 = oldModelClass.getMethod("getContributorName");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setContributorName(value4);

                Method method5 = oldModelClass.getMethod("getVirtualCardData");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setVirtualCardData(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputCourse(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                CourseClp newModel = new CourseClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getCourseId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value0);

                Method method1 = oldModelClass.getMethod("getCompanyId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCompanyId(value1);

                Method method2 = oldModelClass.getMethod("getGroupId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value2);

                Method method3 = oldModelClass.getMethod("getUserId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value3);

                Method method4 = oldModelClass.getMethod("getFeedReferenceId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setFeedReferenceId(value4);

                Method method5 = oldModelClass.getMethod("getHref");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setHref(value5);

                Method method6 = oldModelClass.getMethod("getFullTextHref");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setFullTextHref(value6);

                Method method7 = oldModelClass.getMethod("getCourseIri");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setCourseIri(value7);

                Method method8 = oldModelClass.getMethod("getUpdatedDate");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setUpdatedDate(value8);

                Method method9 = oldModelClass.getMethod("getTitle");

                String value9 = (String) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setTitle(value9);

                Method method9CurrentLanguageId = oldModelClass.getMethod(
                        "getTitleCurrentLanguageId");

                String value9CurrentLanguageId = (String) method9CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setTitleCurrentLanguageId(value9CurrentLanguageId);

                Method method10 = oldModelClass.getMethod(
                        "getTranscriptAbstract");

                String value10 = (String) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setTranscriptAbstract(value10);

                Method method10CurrentLanguageId = oldModelClass.getMethod(
                        "getTranscriptAbstractCurrentLanguageId");

                String value10CurrentLanguageId = (String) method10CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setTranscriptAbstractCurrentLanguageId(value10CurrentLanguageId);

                Method method11 = oldModelClass.getMethod("getDescription");

                String value11 = (String) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value11);

                Method method11CurrentLanguageId = oldModelClass.getMethod(
                        "getDescriptionCurrentLanguageId");

                String value11CurrentLanguageId = (String) method11CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescriptionCurrentLanguageId(value11CurrentLanguageId);

                Method method12 = oldModelClass.getMethod("getKeywords");

                String value12 = (String) method12.invoke(oldModel,
                        (Object[]) null);

                newModel.setKeywords(value12);

                Method method12CurrentLanguageId = oldModelClass.getMethod(
                        "getKeywordsCurrentLanguageId");

                String value12CurrentLanguageId = (String) method12CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setKeywordsCurrentLanguageId(value12CurrentLanguageId);

                Method method13 = oldModelClass.getMethod("getCopyright");

                String value13 = (String) method13.invoke(oldModel,
                        (Object[]) null);

                newModel.setCopyright(value13);

                Method method13CurrentLanguageId = oldModelClass.getMethod(
                        "getCopyrightCurrentLanguageId");

                String value13CurrentLanguageId = (String) method13CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setCopyrightCurrentLanguageId(value13CurrentLanguageId);

                Method method14 = oldModelClass.getMethod("getRatingLevel");

                String value14 = (String) method14.invoke(oldModel,
                        (Object[]) null);

                newModel.setRatingLevel(value14);

                Method method14CurrentLanguageId = oldModelClass.getMethod(
                        "getRatingLevelCurrentLanguageId");

                String value14CurrentLanguageId = (String) method14CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setRatingLevelCurrentLanguageId(value14CurrentLanguageId);

                Method method15 = oldModelClass.getMethod("getRatingReason");

                String value15 = (String) method15.invoke(oldModel,
                        (Object[]) null);

                newModel.setRatingReason(value15);

                Method method15CurrentLanguageId = oldModelClass.getMethod(
                        "getRatingReasonCurrentLanguageId");

                String value15CurrentLanguageId = (String) method15CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setRatingReasonCurrentLanguageId(value15CurrentLanguageId);

                Method method16 = oldModelClass.getMethod("getDuration");

                String value16 = (String) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setDuration(value16);

                Method method17 = oldModelClass.getMethod("getDurationStandard");

                String value17 = (String) method17.invoke(oldModel,
                        (Object[]) null);

                newModel.setDurationStandard(value17);

                Method method18 = oldModelClass.getMethod("getFeaturedStatus");

                Double value18 = (Double) method18.invoke(oldModel,
                        (Object[]) null);

                newModel.setFeaturedStatus(value18);

                Method method19 = oldModelClass.getMethod("getPopularWeight");

                Double value19 = (Double) method19.invoke(oldModel,
                        (Object[]) null);

                newModel.setPopularWeight(value19);

                Method method20 = oldModelClass.getMethod("getAccessCount");

                Long value20 = (Long) method20.invoke(oldModel, (Object[]) null);

                newModel.setAccessCount(value20);

                Method method21 = oldModelClass.getMethod("getCompletedCount");

                Long value21 = (Long) method21.invoke(oldModel, (Object[]) null);

                newModel.setCompletedCount(value21);

                Method method22 = oldModelClass.getMethod("getCreateDate");

                Date value22 = (Date) method22.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value22);

                Method method23 = oldModelClass.getMethod("getRemoved");

                Boolean value23 = (Boolean) method23.invoke(oldModel,
                        (Object[]) null);

                newModel.setRemoved(value23);

                Method method24 = oldModelClass.getMethod("getRemovedDate");

                Date value24 = (Date) method24.invoke(oldModel, (Object[]) null);

                newModel.setRemovedDate(value24);

                Method method25 = oldModelClass.getMethod(
                        "getSupersedesCourseIri");

                String value25 = (String) method25.invoke(oldModel,
                        (Object[]) null);

                newModel.setSupersedesCourseIri(value25);

                Method method26 = oldModelClass.getMethod(
                        "getSupersededByCourseIri");

                String value26 = (String) method26.invoke(oldModel,
                        (Object[]) null);

                newModel.setSupersededByCourseIri(value26);

                Method method27 = oldModelClass.getMethod("getReleaseOnDate");

                Date value27 = (Date) method27.invoke(oldModel, (Object[]) null);

                newModel.setReleaseOnDate(value27);

                Method method28 = oldModelClass.getMethod("getAcceptUntilDate");

                Date value28 = (Date) method28.invoke(oldModel, (Object[]) null);

                newModel.setAcceptUntilDate(value28);

                Method method29 = oldModelClass.getMethod("getVersion");

                String value29 = (String) method29.invoke(oldModel,
                        (Object[]) null);

                newModel.setVersion(value29);

                Method method30 = oldModelClass.getMethod("getVersionDate");

                Date value30 = (Date) method30.invoke(oldModel, (Object[]) null);

                newModel.setVersionDate(value30);

                Method method31 = oldModelClass.getMethod("getPrice");

                Double value31 = (Double) method31.invoke(oldModel,
                        (Object[]) null);

                newModel.setPrice(value31);

                Method method32 = oldModelClass.getMethod("getPriceUnit");

                String value32 = (String) method32.invoke(oldModel,
                        (Object[]) null);

                newModel.setPriceUnit(value32);

                Method method33 = oldModelClass.getMethod("getPriceTerms");

                String value33 = (String) method33.invoke(oldModel,
                        (Object[]) null);

                newModel.setPriceTerms(value33);

                Method method34 = oldModelClass.getMethod("getPriceExpiration");

                String value34 = (String) method34.invoke(oldModel,
                        (Object[]) null);

                newModel.setPriceExpiration(value34);

                Method method35 = oldModelClass.getMethod("getOneStarRateCount");

                Long value35 = (Long) method35.invoke(oldModel, (Object[]) null);

                newModel.setOneStarRateCount(value35);

                Method method36 = oldModelClass.getMethod("getTwoStarRateCount");

                Long value36 = (Long) method36.invoke(oldModel, (Object[]) null);

                newModel.setTwoStarRateCount(value36);

                Method method37 = oldModelClass.getMethod(
                        "getThreeStarRateCount");

                Long value37 = (Long) method37.invoke(oldModel, (Object[]) null);

                newModel.setThreeStarRateCount(value37);

                Method method38 = oldModelClass.getMethod(
                        "getFourStarRateCount");

                Long value38 = (Long) method38.invoke(oldModel, (Object[]) null);

                newModel.setFourStarRateCount(value38);

                Method method39 = oldModelClass.getMethod(
                        "getFiveStarRateCount");

                Long value39 = (Long) method39.invoke(oldModel, (Object[]) null);

                newModel.setFiveStarRateCount(value39);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputCourseImage(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                CourseImageClp newModel = new CourseImageClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getCourseImageId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setCourseImageId(value0);

                Method method1 = oldModelClass.getMethod("getCourseId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value1);

                Method method2 = oldModelClass.getMethod("getOrderWeight");

                Double value2 = (Double) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setOrderWeight(value2);

                Method method3 = oldModelClass.getMethod("getLanguage");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setLanguage(value3);

                Method method4 = oldModelClass.getMethod("getImageId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setImageId(value4);

                Method method5 = oldModelClass.getMethod("getAlternateText");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setAlternateText(value5);

                Method method6 = oldModelClass.getMethod("getSourceImageUrl");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setSourceImageUrl(value6);

                Method method7 = oldModelClass.getMethod("getMimeType");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setMimeType(value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputCourseRecord(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                CourseRecordClp newModel = new CourseRecordClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getCourseRecordId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setCourseRecordId(value0);

                Method method1 = oldModelClass.getMethod("getFeedReferenceId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setFeedReferenceId(value1);

                Method method2 = oldModelClass.getMethod("getCourseRecordIri");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setCourseRecordIri(value2);

                Method method3 = oldModelClass.getMethod("getUserId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value3);

                Method method4 = oldModelClass.getMethod("getSingleSignOnValue");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setSingleSignOnValue(value4);

                Method method5 = oldModelClass.getMethod("getCourseIri");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setCourseIri(value5);

                Method method6 = oldModelClass.getMethod("getUpdatedDate");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setUpdatedDate(value6);

                Method method7 = oldModelClass.getMethod("getCompletionStatus");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setCompletionStatus(value7);

                Method method8 = oldModelClass.getMethod("getRemoved");

                Boolean value8 = (Boolean) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setRemoved(value8);

                Method method9 = oldModelClass.getMethod("getRemovedDate");

                Date value9 = (Date) method9.invoke(oldModel, (Object[]) null);

                newModel.setRemovedDate(value9);

                Method method10 = oldModelClass.getMethod("getUserHidden");

                Boolean value10 = (Boolean) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setUserHidden(value10);

                Method method11 = oldModelClass.getMethod("getAssigned");

                Boolean value11 = (Boolean) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setAssigned(value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputCourseRelated(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                CourseRelatedClp newModel = new CourseRelatedClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getCourseRelatedId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setCourseRelatedId(value0);

                Method method1 = oldModelClass.getMethod("getCourseId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value1);

                Method method2 = oldModelClass.getMethod("getRelatedCourseId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setRelatedCourseId(value2);

                Method method3 = oldModelClass.getMethod("getRelatedCourseIri");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setRelatedCourseIri(value3);

                Method method4 = oldModelClass.getMethod("getRelationshipType");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setRelationshipType(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputCourseRequirement(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                CourseRequirementClp newModel = new CourseRequirementClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getCourseRequirementId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setCourseRequirementId(value0);

                Method method1 = oldModelClass.getMethod("getCourseId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value1);

                Method method2 = oldModelClass.getMethod("getRequirementType");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setRequirementType(value2);

                Method method3 = oldModelClass.getMethod("getRequirementValue");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setRequirementValue(value3);

                Method method3CurrentLanguageId = oldModelClass.getMethod(
                        "getRequirementValueCurrentLanguageId");

                String value3CurrentLanguageId = (String) method3CurrentLanguageId.invoke(oldModel,
                        (Object[]) null);

                newModel.setRequirementValueCurrentLanguageId(value3CurrentLanguageId);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputCourseReview(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                CourseReviewClp newModel = new CourseReviewClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getCourseReviewId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setCourseReviewId(value0);

                Method method1 = oldModelClass.getMethod("getCompanyId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCompanyId(value1);

                Method method2 = oldModelClass.getMethod("getGroupId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value2);

                Method method3 = oldModelClass.getMethod("getCourseId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value3);

                Method method4 = oldModelClass.getMethod("getUserId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value4);

                Method method5 = oldModelClass.getMethod("getSummary");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setSummary(value5);

                Method method6 = oldModelClass.getMethod("getContent");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setContent(value6);

                Method method7 = oldModelClass.getMethod("getCreateDate");

                Date value7 = (Date) method7.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value7);

                Method method8 = oldModelClass.getMethod("getModifiedDate");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setModifiedDate(value8);

                Method method9 = oldModelClass.getMethod("getWeightedScore");

                Double value9 = (Double) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeightedScore(value9);

                Method method10 = oldModelClass.getMethod("getRemoved");

                Boolean value10 = (Boolean) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setRemoved(value10);

                Method method11 = oldModelClass.getMethod("getRemovedDate");

                Date value11 = (Date) method11.invoke(oldModel, (Object[]) null);

                newModel.setRemovedDate(value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputCourses_Components(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Courses_ComponentsClp newModel = new Courses_ComponentsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getCoursesComponentsId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setCoursesComponentsId(value0);

                Method method1 = oldModelClass.getMethod("getCourseId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value1);

                Method method2 = oldModelClass.getMethod("getCourseIri");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setCourseIri(value2);

                Method method3 = oldModelClass.getMethod("getComponentId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setComponentId(value3);

                Method method4 = oldModelClass.getMethod("getComponentIri");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setComponentIri(value4);

                Method method5 = oldModelClass.getMethod("getOrderWeight");

                Double value5 = (Double) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setOrderWeight(value5);

                Method method6 = oldModelClass.getMethod("getSectionType");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setSectionType(value6);

                Method method7 = oldModelClass.getMethod("getComponentType");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setComponentType(value7);

                Method method8 = oldModelClass.getMethod("getMimeType");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setMimeType(value8);

                Method method9 = oldModelClass.getMethod(
                        "getCoursePaymentRequired");

                Boolean value9 = (Boolean) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setCoursePaymentRequired(value9);

                Method method10 = oldModelClass.getMethod(
                        "getComponentPaymentRequired");

                Boolean value10 = (Boolean) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setComponentPaymentRequired(value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputExternalLink(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ExternalLinkClp newModel = new ExternalLinkClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getLinkId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setLinkId(value0);

                Method method1 = oldModelClass.getMethod("getCourseId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value1);

                Method method2 = oldModelClass.getMethod("getComponentId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setComponentId(value2);

                Method method3 = oldModelClass.getMethod("getLinkType");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setLinkType(value3);

                Method method4 = oldModelClass.getMethod("getLinkUrl");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setLinkUrl(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputFeedReference(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                FeedReferenceClp newModel = new FeedReferenceClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getFeedReferenceId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setFeedReferenceId(value0);

                Method method1 = oldModelClass.getMethod("getCompanyId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCompanyId(value1);

                Method method2 = oldModelClass.getMethod("getGroupId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value2);

                Method method3 = oldModelClass.getMethod("getContentProviderId");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setContentProviderId(value3);

                Method method4 = oldModelClass.getMethod("getHref");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setHref(value4);

                Method method5 = oldModelClass.getMethod("getHrefHash");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setHrefHash(value5);

                Method method6 = oldModelClass.getMethod("getPshb");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setPshb(value6);

                Method method7 = oldModelClass.getMethod("getPshbSubscribed");

                Boolean value7 = (Boolean) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setPshbSubscribed(value7);

                Method method8 = oldModelClass.getMethod("getFeedIri");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setFeedIri(value8);

                Method method9 = oldModelClass.getMethod("getFeedType");

                String value9 = (String) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setFeedType(value9);

                Method method10 = oldModelClass.getMethod("getFeedVersion");

                String value10 = (String) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setFeedVersion(value10);

                Method method11 = oldModelClass.getMethod(
                        "getTrustworthyWeight");

                Double value11 = (Double) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setTrustworthyWeight(value11);

                Method method12 = oldModelClass.getMethod("getCreateDate");

                Date value12 = (Date) method12.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value12);

                Method method13 = oldModelClass.getMethod("getRemoved");

                Boolean value13 = (Boolean) method13.invoke(oldModel,
                        (Object[]) null);

                newModel.setRemoved(value13);

                Method method14 = oldModelClass.getMethod("getRemovedDate");

                Date value14 = (Date) method14.invoke(oldModel, (Object[]) null);

                newModel.setRemovedDate(value14);

                Method method15 = oldModelClass.getMethod("getRemovedReason");

                String value15 = (String) method15.invoke(oldModel,
                        (Object[]) null);

                newModel.setRemovedReason(value15);

                Method method16 = oldModelClass.getMethod("getSyncDate");

                Date value16 = (Date) method16.invoke(oldModel, (Object[]) null);

                newModel.setSyncDate(value16);

                Method method17 = oldModelClass.getMethod("getSyncSuccess");

                Boolean value17 = (Boolean) method17.invoke(oldModel,
                        (Object[]) null);

                newModel.setSyncSuccess(value17);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputFeedSyncHistory(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                FeedSyncHistoryClp newModel = new FeedSyncHistoryClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getSyncId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setSyncId(value0);

                Method method1 = oldModelClass.getMethod("getFeedReferenceId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setFeedReferenceId(value1);

                Method method2 = oldModelClass.getMethod("getSyncDate");

                Date value2 = (Date) method2.invoke(oldModel, (Object[]) null);

                newModel.setSyncDate(value2);

                Method method3 = oldModelClass.getMethod("getSuccess");

                Boolean value3 = (Boolean) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setSuccess(value3);

                Method method4 = oldModelClass.getMethod("getSyncMessage");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setSyncMessage(value4);

                Method method5 = oldModelClass.getMethod("getNumberOfEntries");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setNumberOfEntries(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputFlagReport(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                FlagReportClp newModel = new FlagReportClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getUuid");

                String value0 = (String) method0.invoke(oldModel,
                        (Object[]) null);

                newModel.setUuid(value0);

                Method method1 = oldModelClass.getMethod("getFlagReportId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setFlagReportId(value1);

                Method method2 = oldModelClass.getMethod("getGroupId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value2);

                Method method3 = oldModelClass.getMethod("getCompanyId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setCompanyId(value3);

                Method method4 = oldModelClass.getMethod("getUserId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value4);

                Method method5 = oldModelClass.getMethod("getClassNameId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setClassNameId(value5);

                Method method6 = oldModelClass.getMethod("getClassPK");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setClassPK(value6);

                Method method7 = oldModelClass.getMethod("getCreateDate");

                Date value7 = (Date) method7.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value7);

                Method method8 = oldModelClass.getMethod("getTitle");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setTitle(value8);

                Method method9 = oldModelClass.getMethod("getContent");

                String value9 = (String) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setContent(value9);

                Method method10 = oldModelClass.getMethod("getFlagReason");

                String value10 = (String) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlagReason(value10);

                Method method11 = oldModelClass.getMethod("getFlagComment");

                String value11 = (String) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlagComment(value11);

                Method method12 = oldModelClass.getMethod("getModerateAction");

                String value12 = (String) method12.invoke(oldModel,
                        (Object[]) null);

                newModel.setModerateAction(value12);

                Method method13 = oldModelClass.getMethod("getModeratorComment");

                String value13 = (String) method13.invoke(oldModel,
                        (Object[]) null);

                newModel.setModeratorComment(value13);

                Method method14 = oldModelClass.getMethod("getStatus");

                Integer value14 = (Integer) method14.invoke(oldModel,
                        (Object[]) null);

                newModel.setStatus(value14);

                Method method15 = oldModelClass.getMethod("getStatusByUserId");

                Long value15 = (Long) method15.invoke(oldModel, (Object[]) null);

                newModel.setStatusByUserId(value15);

                Method method16 = oldModelClass.getMethod("getStatusByUserName");

                String value16 = (String) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setStatusByUserName(value16);

                Method method17 = oldModelClass.getMethod("getStatusDate");

                Date value17 = (Date) method17.invoke(oldModel, (Object[]) null);

                newModel.setStatusDate(value17);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputFlagReportStats(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                FlagReportStatsClp newModel = new FlagReportStatsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getFlagReportStatsId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setFlagReportStatsId(value0);

                Method method1 = oldModelClass.getMethod("getClassNameId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setClassNameId(value1);

                Method method2 = oldModelClass.getMethod("getClassPK");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setClassPK(value2);

                Method method3 = oldModelClass.getMethod("getTotalEntries");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setTotalEntries(value3);

                Method method4 = oldModelClass.getMethod("getTotalModerated");

                Integer value4 = (Integer) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setTotalModerated(value4);

                Method method5 = oldModelClass.getMethod("getTotalApproved");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setTotalApproved(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputGlobalCourseReview(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                GlobalCourseReviewClp newModel = new GlobalCourseReviewClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getGlobalCourseReviewId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setGlobalCourseReviewId(value0);

                Method method1 = oldModelClass.getMethod("getCompanyId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCompanyId(value1);

                Method method2 = oldModelClass.getMethod("getGroupId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value2);

                Method method3 = oldModelClass.getMethod("getCourseReviewIri");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setCourseReviewIri(value3);

                Method method4 = oldModelClass.getMethod("getUpdatedDate");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setUpdatedDate(value4);

                Method method5 = oldModelClass.getMethod("getCourseIri");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setCourseIri(value5);

                Method method6 = oldModelClass.getMethod("getHref");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setHref(value6);

                Method method7 = oldModelClass.getMethod("getNterInstance");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setNterInstance(value7);

                Method method8 = oldModelClass.getMethod("getCourseId");

                Long value8 = (Long) method8.invoke(oldModel, (Object[]) null);

                newModel.setCourseId(value8);

                Method method9 = oldModelClass.getMethod("getUserDisplayName");

                String value9 = (String) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setUserDisplayName(value9);

                Method method10 = oldModelClass.getMethod(
                        "getSingleSignOnValue");

                String value10 = (String) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setSingleSignOnValue(value10);

                Method method11 = oldModelClass.getMethod("getSummary");

                String value11 = (String) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setSummary(value11);

                Method method12 = oldModelClass.getMethod("getContent");

                String value12 = (String) method12.invoke(oldModel,
                        (Object[]) null);

                newModel.setContent(value12);

                Method method13 = oldModelClass.getMethod("getCreateDate");

                Date value13 = (Date) method13.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value13);

                Method method14 = oldModelClass.getMethod("getModifiedDate");

                Date value14 = (Date) method14.invoke(oldModel, (Object[]) null);

                newModel.setModifiedDate(value14);

                Method method15 = oldModelClass.getMethod("getStarScore");

                Double value15 = (Double) method15.invoke(oldModel,
                        (Object[]) null);

                newModel.setStarScore(value15);

                Method method16 = oldModelClass.getMethod(
                        "getFromTrustedReviewer");

                Boolean value16 = (Boolean) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setFromTrustedReviewer(value16);

                Method method17 = oldModelClass.getMethod("getRemoved");

                Boolean value17 = (Boolean) method17.invoke(oldModel,
                        (Object[]) null);

                newModel.setRemoved(value17);

                Method method18 = oldModelClass.getMethod("getRemovedDate");

                Date value18 = (Date) method18.invoke(oldModel, (Object[]) null);

                newModel.setRemovedDate(value18);

                Method method19 = oldModelClass.getMethod("getIsHidden");

                Boolean value19 = (Boolean) method19.invoke(oldModel,
                        (Object[]) null);

                newModel.setIsHidden(value19);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }
}
