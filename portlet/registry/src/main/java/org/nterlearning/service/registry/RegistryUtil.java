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
package org.nterlearning.service.registry;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import org.nterlearning.registry.client.*;
import org.nterlearning.registry.proxy.*;


public class RegistryUtil {

    private static final String PORTLET_ID = "nterregistryportlet_WAR_nterregistryportlet";
    private static RegistryProxy registry;
    private static ActiveStatusEnum defaultActiveStatus = null;
    private static Log log = LogFactoryUtil.getLog(RegistryUtil.class);

    private RegistryUtil() {
    }

    public static RegistryProxy getRegistryService() {
        if (registry == null) {
            registry = new RegistryProxyImpl();
        }
        return registry;
    }

    public static List<ServiceTypeEnum> getServiceTypes() {
        return getRegistryService().getServiceTypes();
    }

    public static List<BindingTypeEnum> getBindingTypes() {
        return getRegistryService().getBindingTypes();
    }

    public static List<ActiveStatusEnum> getStatusTypes() {
        List<ActiveStatusEnum> statusTypes = new ArrayList<ActiveStatusEnum>();
        for (ActiveStatusEnum status : getRegistryService().getStatusTypes()) {
            if (status != ActiveStatusEnum.UNSPECIFIED) {
                statusTypes.add(status);
            }
        }
        return statusTypes;
    }

    public static List<InstitutionBean> getInstitutions() {
        List<InstitutionBean> institutionBeans = new ArrayList<InstitutionBean>();
        institutionBeans.addAll(getLocalInstitutions());
        institutionBeans.addAll(getGlobalInstitutions());

        return institutionBeans;
    }

    public static List<InstitutionBean> getLocalInstitutions() {
        List<InstitutionBean> institutions = new ArrayList<InstitutionBean>();

        institutions.addAll(getRegistryService().getInstitutions(
                ActiveStatusEnum.UNSPECIFIED, ActiveStatusEnum.UNSPECIFIED, RegistryInstance.LOCAL));

        return institutions;
    }

    private static List<InstitutionBean> getGlobalInstitutions() {
        List<InstitutionBean> institutions = new ArrayList<InstitutionBean>();
        // Retrieve 'Active' global institutions
        institutions.addAll(getRegistryService().getInstitutions(
                ActiveStatusEnum.UNSPECIFIED, ActiveStatusEnum.ACTIVE, RegistryInstance.GLOBAL));

        // If default status is 'unspecified' retrieve global "Inactive" institutions
        if (getDefaultBlacklistStatus() == ActiveStatusEnum.UNSPECIFIED) {
            institutions.addAll(getRegistryService().getInstitutions(
                ActiveStatusEnum.UNSPECIFIED, ActiveStatusEnum.INACTIVE, RegistryInstance.GLOBAL));
        }

        return institutions;
    }

    public static List<InstitutionBean> getInstitutions(
            List<InstitutionBean> institutions,
            int start, int end) {

        return getInstitutions(start, end, institutions);
     }

    private static List<InstitutionBean> getInstitutions(
            int start, int end, List<InstitutionBean> institutionList) {

        // Sort the collection - by institution name
        java.util.Collections.sort(
                institutionList, new InstitutionNameComparator());

        List<InstitutionBean> institutions = new ArrayList<InstitutionBean>();
        for (int i = start; i < end; i++) {
            institutions.add(institutionList.get(i));
        }
        return institutions;
    }

    public static class InstitutionNameComparator implements Comparator<InstitutionBean> {
        public int compare(InstitutionBean inst1, InstitutionBean inst2) {

            RegistryInstance inst1R = inst1.getRegistryInstance();
            RegistryInstance inst2R = inst2.getRegistryInstance();

            return inst2R.compareTo(inst1R);
        }
    }

    public static InstitutionBean getInstitutionByName(String name) {
        return getRegistryService().getInstitutionByName(name);
    }

    public static List<ServiceBean> getServices() {
        List<ServiceBean> serviceBeans = new ArrayList<ServiceBean>();
        serviceBeans.addAll(getLocalServices());
        serviceBeans.addAll(getGlobalServices());

        return serviceBeans;
    }

    private static List<ServiceBean> getLocalServices() {
        List<ServiceBean> services = new ArrayList<ServiceBean>();

        services.addAll(getRegistryService().getServices(
                ActiveStatusEnum.UNSPECIFIED, ActiveStatusEnum.UNSPECIFIED, RegistryInstance.LOCAL));

        return services;
    }

    private static List<ServiceBean> getGlobalServices() {
        List<ServiceBean> services = new ArrayList<ServiceBean>();
        // Retrieve 'Active' global institutions
        services.addAll(getRegistryService().getServices(
                ActiveStatusEnum.UNSPECIFIED, ActiveStatusEnum.ACTIVE, RegistryInstance.GLOBAL));
        // If default status is 'unspecified' retrieve global "Inactive" services
        if (getDefaultBlacklistStatus() == ActiveStatusEnum.UNSPECIFIED) {
            services.addAll(getRegistryService().getServices(
                ActiveStatusEnum.UNSPECIFIED, ActiveStatusEnum.INACTIVE, RegistryInstance.GLOBAL));
        }

        return services;
    }

    public static List<ServiceBean> getServices(List<ServiceBean> services,
            int start, int end) {
        return getServices(start, end, services);
    }

    private static List<ServiceBean> getServices(
            int start, int end, List<ServiceBean> serviceList) {

        // Sort the collection - by service name
        java.util.Collections.sort(
                serviceList, new ServiceNameComparator());

        List<ServiceBean> services = new ArrayList<ServiceBean>();
        for (int i = start; i < end; i++) {
            services.add(serviceList.get(i));
        }
        return services;
    }

    public static class ServiceNameComparator implements Comparator<ServiceBean> {
        public int compare(ServiceBean serv1, ServiceBean serv2) {

            RegistryInstance serv1R = serv1.getRegistryInstance();
            RegistryInstance serv2R = serv2.getRegistryInstance();

            return serv2R.compareTo(serv1R);
        }
    }

    public static ServiceBean getServiceByName(String institutionName, String serviceName) {
        return getRegistryService().getServiceByName(institutionName, serviceName);
    }

    public static List<Binding> getBindings(List<Binding> services,
            int start, int end) {
        return getBindings(start, end, services);
    }

    private static List<Binding> getBindings(
            int start, int end, List<Binding> bindingList) {

        // Sort the collection - by service name
        java.util.Collections.sort(
                bindingList, new BindingNameComparator());

        List<Binding> bindings = new ArrayList<Binding>();
        for (int i = start; i < end; i++) {
            bindings.add(bindingList.get(i));
        }
        return bindings;
    }

    public static class BindingNameComparator implements Comparator<Binding> {
        public int compare(Binding x, Binding y) {

          String xName = x.getDescription();
          String yName = y.getDescription();

          return xName.compareTo(yName);
        }
    }

    public static String getActionURL(javax.servlet.http.HttpServletRequest request,
            String action, Map<String, String> paramMap) {

        StringBuilder sb = new StringBuilder();
        sb.append("'manage?");
        sb.append("p_auth=");
        sb.append(AuthTokenUtil.getToken(request));
        sb.append("&amp;");
        sb.append("p_p_id=");
        sb.append(PORTLET_ID);
        sb.append("&amp;");
        sb.append("p_p_lifecycle=1");
        sb.append("&amp;");
        sb.append("p_p_state=maximized");
        sb.append("&amp;");
        sb.append("p_p_mode=view");
        sb.append("&amp;");
        sb.append("_");
        sb.append(PORTLET_ID);
        sb.append("_javax.portlet.action=");
        sb.append(action);

        if (paramMap != null) {
            for (String key : paramMap.keySet()) {
                sb.append("&amp;");
                sb.append("_");
                sb.append(PORTLET_ID);
                sb.append("_");
                sb.append(key);
                sb.append("=");

                String encodedString = null;
                try {
                    encodedString = URLEncoder.encode(paramMap.get(key), "UTF-8");
                } catch (Exception e) {
                    log.error("Error encoding URL value [" + paramMap.get(key) + "]", e);
                }
                if (encodedString != null) {
                    sb.append(encodedString);
                }
            }
        }

        return sb.toString();
    }

    public static String getActionHREF(
            javax.servlet.http.HttpServletRequest request,
            String urlLabel, String action,
            Map<String, String> paramMap) {

        StringBuilder sb = new StringBuilder();
        sb.append("<a href='manage?");
        sb.append("p_auth=");
        sb.append(AuthTokenUtil.getToken(request));
        sb.append("&amp;");
        sb.append("p_p_id=");
        sb.append(PORTLET_ID);
        sb.append("&amp;");
        sb.append("p_p_lifecycle=1");
        sb.append("&amp;");
        sb.append("p_p_state=maximized");
        sb.append("&amp;");
        sb.append("p_p_mode=view");
        sb.append("&amp;");
        sb.append("_");
        sb.append(PORTLET_ID);
        sb.append("_javax.portlet.action=");
        sb.append(action);

        if (paramMap != null) {
            for (String key : paramMap.keySet()) {
                sb.append("&amp;");
                sb.append("_");
                sb.append(PORTLET_ID);
                sb.append("_");
                sb.append(key);
                sb.append("=");

                String encodedString = null;
                try {
                    encodedString = URLEncoder.encode(paramMap.get(key), "UTF-8");
                } catch (Exception e) {
                    log.error("Error encoding URL value [" + paramMap.get(key) + "]", e);
                }
                if (encodedString != null) {
                    sb.append(encodedString);
                }
            }
        }

        sb.append("'>");
        sb.append(urlLabel);
        sb.append("</a>");

        return sb.toString();
    }

    public static RegistryInstance getRegistryInstanceFromValue(String value) {
        RegistryInstance registryInstance = null;
        if (value != null && !value.isEmpty()) {
            try {
                registryInstance = RegistryInstance.valueOf(value.toUpperCase());
            } catch (Exception e) {
                log.warn("Unexpected exception creating RegistryInstance enum from value [" +
                    value + "], acceptable values are [" +
                    getPrintableRegistryInstanceValues() + "]. Will try fromValue"
                );
            }

            if (registryInstance == null) {
                try {
                    registryInstance = RegistryInstance.fromValue(value);
                } catch (Exception e) {
                    log.error("Unexpected exception creating RegistryInstance enum from value [" +
                        value + "], acceptable values are [" +
                        getPrintableRegistryInstanceValues() + "]"
                    );
                }
            }
        } else {
            log.error("Unable to create RegistryInstance enum from value [" + value + "]");
        }
        return registryInstance;
    }

    public static ActiveStatusEnum getActiveStatusFromValue(String value) {
        ActiveStatusEnum activeStatus = null;
        if (value != null && !value.isEmpty()) {
            try {
                activeStatus =
                        ActiveStatusEnum.fromValue(value);
            } catch (Exception e) {
                log.error("Unexpected exception creating ActiveStatusEnum from value [" +
                    value + "], acceptable values are [" +
                    getPrintableStatusTypeValues() + "]");
            }
        } else {
            log.error("Unable to create ActiveStatusEnum from value [" + value + "]");
        }
        return activeStatus;
    }

    private static ActiveStatusEnum getDefaultBlacklistStatus() {
        if (defaultActiveStatus == null) {
            try {
                String status =
                        PropsUtil.get(PropKeyConst.BLACKLIST_DEFAULT_STATUS);
                defaultActiveStatus = ActiveStatusEnum.fromValue(status);
            } catch (Exception e) {
                log.error("Error setting blacklist default status setting [" +
                        PropKeyConst.BLACKLIST_DEFAULT_STATUS + "]", e);
            }
        }

        if (defaultActiveStatus == null) {
            defaultActiveStatus = ActiveStatusEnum.ACTIVE;
            log.info("Setting default blacklist status to [" + defaultActiveStatus + "]");
        }

        return defaultActiveStatus;
    }

    private static String getPrintableStatusTypeValues() {
        StringBuilder sb = new StringBuilder();
        ActiveStatusEnum[] values =
                ActiveStatusEnum.values();
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private static String getPrintableRegistryInstanceValues() {
        StringBuilder sb = new StringBuilder();
        RegistryInstance[] values = RegistryInstance.values();
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < (values.length - 1)) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}