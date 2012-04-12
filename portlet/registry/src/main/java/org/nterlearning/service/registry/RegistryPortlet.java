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

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ProcessAction;
import javax.portlet.PortletException;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.registry.proxy.*;
import org.nterlearning.xml.nter_registry.blacklist_objects_0_1_0.ActiveStatusEnum;
import org.nterlearning.xml.nter_registry.domain_objects_0_1_0.*;

public class RegistryPortlet extends MVCPortlet {

    private Registry registry = null;

    private final String editInstitutionJSP = "/jsp/cp-reg/editInstitution.jsp";
    private final String viewInstitutionsJSP = "/jsp/cp-reg/viewInstitutions.jsp";
    private final String viewInstitutionJSP = "/jsp/cp-reg/viewInstitution.jsp";
    private final String viewServicesJSP = "/jsp/cp-reg/viewServices.jsp";
    private final String viewServiceJSP = "/jsp/cp-reg/viewService.jsp";
    private final String editServiceJSP = "/jsp/cp-reg/editService.jsp";
    private final String editBindingJSP = "/jsp/cp-reg/editBinding.jsp";

    private final String tabServices = "Services";
    private final String tabInstitutions = "Institutions";

    private static Log log = LogFactoryUtil.getLog(RegistryPortlet.class);

    private boolean debug = false;

    public Registry getRegistry() {
        if (registry == null) {
            registry = RegistryUtil.getRegistryService();
        }

        return registry;
    }

    @Override
    public void init() throws PortletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }


    /* ********** INSTITUTIONS ********** */

    @ProcessAction(name = "viewInstitutions")
    public void viewInstitutions(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>viewInstitutions");
        }
        response.setRenderParameter("jspPage", viewInstitutionsJSP);
    }

    /* ********** INSTITUTION ********** */

    @ProcessAction(name = "viewInstitution")
    public void viewInstitution(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>viewInstitution");
        }

        String institutionName = ParamUtil.getString(request, "institutionName");
        log.debug("institutionName [" + institutionName + "]");

        InstitutionBean institution = null;
        if (Validator.isNotNull(institutionName)) {
            institution = RegistryUtil.getInstitutionByName(institutionName);

            // Set the tab
            request.setAttribute("tabs", tabInstitutions);

            request.setAttribute("institution", institution);
            request.setAttribute("actionName", "viewInstitution");
         } else {
            SessionErrors.add(request, "error-updating");
         }

        response.setRenderParameter("jspPage", viewInstitutionJSP);
    }

    @ProcessAction(name = "newInstitution")
    public void newInstitution(ActionRequest request, ActionResponse response) {
        if (debug) {
            log.info(">>>>>>>>>>>>>newInstitution");
        }
        InstitutionBean institution = getInstitutionFromRequest(request);

        institution.setActiveStatus(ActiveStatusEnum.UNSPECIFIED);

        request.setAttribute("institution", institution);
        request.setAttribute("actionName", "addInstitution");
        response.setRenderParameter("jspPage", editInstitutionJSP);

        SessionMessages.add(request, "institution-added");
    }

    @ProcessAction(name = "addInstitution")
    public void addInstitution(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>addInstitution");
        }
        ArrayList<String> errors = new ArrayList<String>();

        InstitutionBean institution = getInstitutionFromRequest(request);
        String institutionName = institution.getName();
        Contact contact = institution.getContactInfo();
        String contactName = null;
        String email = null;
        if (contact != null) {
            contactName = contact.getPersonName();
            email = contact.getEmail();
        }

        if (Validator.isNull(institutionName)) {
            errors.add("error-required-institution-name");
        }
        if (Validator.isNull(contactName)) {
            errors.add("error-required-contact");
        }
        if (Validator.isNull(email)) {
            errors.add("error-required-email");
        }

        if (errors.size() == 0) {
            // Check for unique serviceName
            // TODO: implement...
            if (!isInstitutionNameUnique(institutionName)) {
                errors.add("error-adding-unique-inst");
            }
        }

        if (errors.size() == 0) {
            // Add to registry
            getRegistry().createInstitution(institution);

            SessionErrors.clear(request);
            SessionMessages.add(request, "institution-added");
            response.setRenderParameter("jspPage", viewInstitutionsJSP);
        } else {
            request.setAttribute("institution", institution);
            request.setAttribute("actionName", "addInstitution");

            for (String error : errors) {
                SessionErrors.add(request, error);
            }
            response.setRenderParameter("jspPage", editInstitutionJSP);
        }
    }

    /**
     * This Action shows the Edit screen.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @ProcessAction(name = "editInstitution")
    public void editInstitution(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>editInstitution");
        }

        String institutionName = ParamUtil.getString(request, "institutionName");
        log.debug("institutionName [" + institutionName + "]");

        InstitutionBean institution = null;
        if (Validator.isNotNull(institutionName)) {
            institution = RegistryUtil.getInstitutionByName(institutionName);

            request.setAttribute("institution", institution);
            request.setAttribute("actionName", "updateInstitution");
            response.setRenderParameter("jspPage", editInstitutionJSP);
         } else {
            SessionErrors.add(request, "error-updating");
        }

    }

    @ProcessAction(name = "updateInstitution")
    public void updateInstitution(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>updateInstitution");
        }
        ArrayList<String> errors = new ArrayList<String>();

        InstitutionBean institution = getInstitutionFromRequest(request);

        String institutionName = institution.getName();
        String contactName = null;
        String contactEmail = null;
        Contact contact = institution.getContactInfo();
        if (contact != null) {
            contactName = contact.getPersonName();
            contactEmail = contact.getEmail();
        }

        if (Validator.isNull(institutionName)) {
            errors.add("error-required-institution-name");
        }
        if (Validator.isNull(contactName)) {
            errors.add("error-required-contact");
        }
        if (Validator.isNull(contactEmail)) {
            errors.add("error-required-email");
        }

        if (debug) {
            log.info("institution.description [" + institution.getDescription() + "]");
        }

        // Is institution name unique?
        // NO UPDATE FOR NOW
//        if (errors.size() == 0) {
//            Institution dbInstitution =
//                getRegistry().getInstitutionByName(institutionName);
//            if (dbInstitution != null) {
//                if (dbInstitution.getKey().equals(institution.getKey())) {
//                    errors.add("error-adding-unique-service");
//                }
//            }
//        }

        if (errors.size() == 0) {
            // Update registry
            getRegistry().updateInstitution(institution);

            SessionMessages.add(request, "institution-updated");

            SessionErrors.clear(request);
            response.setRenderParameter("jspPage", viewInstitutionsJSP);
        } else {
            for (String error : errors) {
                log.info("Input error [" + error + "]");
                SessionErrors.add(request, error);
            }
            request.setAttribute("actionName", "updateInstitution");
            request.setAttribute("institution", institution);
            response.setRenderParameter("jspPage", editInstitutionJSP);
        }
    }

    @ProcessAction(name = "deleteInstitution")
    public void deleteInstitution(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>deleteInstitution");
        }

        String institutionKey = ParamUtil.getString(request, "institutionKey");
        log.info("Deleting institution with key [" + institutionKey + "]");

        if (Validator.isNotNull(institutionKey)) {
            Long key = Long.valueOf(institutionKey);
            try {
                getRegistry().removeInstitution(key);
                SessionMessages.add(request, "institution-deleted");
                response.setRenderParameter("jspPage", viewInstitutionsJSP);
            } catch (Exception e) {
                SessionErrors.add(request, "error-deleting");
                e.printStackTrace();
            }
        }
        else {
            SessionErrors.add(request, "error-deleting");
        }
    }

    /* ********** SERVICE ********** */

    @ProcessAction(name = "viewServices")
    public void viewServices(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>viewServices");
        }
        request.setAttribute("tabs", tabServices);
        response.setRenderParameter("jspPage", viewServicesJSP);
    }

    @ProcessAction(name = "viewService")
    public void viewService(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>viewService");
        }

        String institutionName = ParamUtil.getString(request, "institutionName");
        String serviceName = ParamUtil.getString(request, "serviceName");

        if (debug) {
            log.info("Attempting to retrieve service with serviceName [" +
                serviceName + "] and institutionName [" + institutionName + "]");
        }

        ServiceBean service = null;

        if (Validator.isNotNull(serviceName)) {
            RegistryUtil.getServiceByName(institutionName, serviceName);
            service = RegistryUtil.getServiceByName(institutionName, serviceName);

            String tabs = ParamUtil.getString(request, "tabs");
            if (debug) {
                log.info("viewService.tabs [" + tabs + "]");
            }
            if (tabs != null && !tabs.isEmpty()) {
                request.setAttribute("tabs", tabs);
            }

            request.setAttribute("service", service);
            request.setAttribute("actionName", "viewService");
         } else {
            SessionErrors.add(request, "error-updating");
         }

        request.setAttribute("institutionName", institutionName);
        request.setAttribute("serviceName", serviceName);
        response.setRenderParameter("jspPage", viewServiceJSP);
    }

    @ProcessAction(name = "newService")
    public void newService(ActionRequest request, ActionResponse response) {
        if (debug) {
            log.info(">>>>>>>>>>>>>newService");
        }
        ServiceBean service = new ServiceBean();

        String institutionName = ParamUtil.getString(request, "institutionName");

        service.setInstitutionName(institutionName);

        service.setActiveStatus(ActiveStatusEnum.ACTIVE);

        if (debug) {
            log.info("Adding new Service for insitution [" + service.getInstitutionName() + "]");
        }

        String tabs = ParamUtil.getString(request, "tabs");
        if (debug) {
            log.info("newService.tabs [" + tabs + "]");
        }
        if (tabs != null && !tabs.isEmpty()) {
            request.setAttribute("tabs", tabs);
        }

        request.setAttribute("service", service);
        request.setAttribute("institutionName", institutionName);
        request.setAttribute("actionName", "addService");
        response.setRenderParameter("jspPage", editServiceJSP);

        SessionMessages.add(request, "service-added");
    }

    @ProcessAction(name = "addService")
    public void addService(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>addService");
        }
        ArrayList<String> errors = new ArrayList<String>();

        ServiceBean service = getServiceFromRequest(request);
        String serviceName = service.getName();
        String institutionName = service.getInstitutionName();

        log.info("Adding service with serviceName [" + serviceName +
                "] institutionName [" + institutionName +
                "] RegistryInstance [" + service.getRegistryInstance() + "]");

        if (Validator.isNull(serviceName)) {
            errors.add("error-required-service-name");
        }

        if (errors.size() == 0) {
            // Check for unique serviceName
            // TODO: implement...
            if (!isServiceNameUnique(institutionName, serviceName)) {
                errors.add("error-adding-unique-service");
            }
        }

        if (errors.size() == 0) {
            if (Validator.isNotNull(institutionName)) {
                InstitutionBean institution =
                    RegistryUtil.getInstitutionByName(institutionName);
                // Add to registry
                getRegistry().createService(institution.getKey(), service);

                // Add service to institution
                institution.getService().add(service);

                SessionErrors.clear(request);

                String tabs = ParamUtil.getString(request, "tabs");
                if (debug) {
                    log.info("addService.tabs [" + tabs + "]");
                }
                if (tabs.equals(tabServices)) {
                    request.setAttribute("tabs", tabs);
                    request.setAttribute("actionName", "viewServices");
                    request.setAttribute("institution", institution);
                    response.setRenderParameter("jspPage", viewServicesJSP);
                } else {
                    SessionMessages.add(request, "institution-added");
                    request.setAttribute("institution", institution);
                    request.setAttribute("actionName", "viewInstitution");
                    response.setRenderParameter("jspPage", viewInstitutionJSP);
                }
            } else {
                log.error("Unable to add service with institution name [" +
                        institutionName + "]");
            }
        } else {
            request.setAttribute("service", service);
            request.setAttribute("actionName", "addService");

            for (String error : errors) {
                SessionErrors.add(request, error);
            }
            response.setRenderParameter("jspPage", editServiceJSP);
        }
    }

    @ProcessAction(name = "editService")
    public void editService(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>editService");
        }

        String serviceName = ParamUtil.getString(request, "serviceName");
        String institutionName = ParamUtil.getString(request, "institutionName");

        log.info("Editing service with serviceName [" + serviceName +
                "] and institutionName [" + institutionName + "]");

        String tabs = ParamUtil.getString(request, "tabs");
        if (debug) {
            log.info("editService.tabs [" + tabs + "]");
        }
        if (tabs != null && !tabs.isEmpty()) {
            request.setAttribute("tabs", tabs);
        }

        ServiceBean service = null;
        if (Validator.isNotNull(serviceName)) {
            service = RegistryUtil.getServiceByName(institutionName, serviceName);

            request.setAttribute("service", service);
            request.setAttribute("actionName", "updateService");
            response.setRenderParameter("jspPage", editServiceJSP);
         } else {
            SessionErrors.add(request, "error-updating");
         }
    }

    @ProcessAction(name = "updateService")
    public void updateService(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>updateService");
        }
        ArrayList<String> errors = new ArrayList<String>();

        ServiceBean service = getServiceFromRequest(request);

        String serviceName = service.getName();
        String institutionName = service.getInstitutionName();

        String tabs = ParamUtil.getString(request, "tabs");
        if (debug) {
            log.info("updateService.tabs [" + tabs + "]");
        }

        log.info("Updating service with serviceName [" + serviceName +
                "] and institutionName [" + institutionName + "]");

        if (Validator.isNull(serviceName)) {
            errors.add("error-required-name");
        }
        if (Validator.isNull(institutionName)) {
            errors.add("error-required-institution-name");
        }

        // Is service name unique?
        // READ-ONLY FOR NOW
//        if (errors.size() == 0) {
//            ServiceBean dbService =
//                getRegistry().getServiceByName(institutionName, serviceName);
//            if (dbService != null) {
//                if (dbService.getKey().equals(service.getKey())) {
//                    errors.add("error-adding-unique-service");
//                }
//            }
//        }

        if (errors.size() == 0) {
            // Update registry
            getRegistry().updateService(service);

            SessionMessages.add(request, "service-updated");
            SessionErrors.clear(request);

            InstitutionBean institution =
                RegistryUtil.getInstitutionByName(institutionName);

            if (tabs.equals(tabServices)) {
                request.setAttribute("tabs", tabs);
                request.setAttribute("actionName", "viewServices");
                request.setAttribute("institution", institution);
                response.setRenderParameter("jspPage", viewServicesJSP);
            } else {
                request.setAttribute("actionName", "viewInstitution");
                request.setAttribute("institution", institution);
                response.setRenderParameter("jspPage", viewInstitutionJSP);
            }
        } else {
            for (String error : errors) {
                SessionErrors.add(request, error);
            }
            request.setAttribute("actionName", "updateService");
            request.setAttribute("service", service);
            response.setRenderParameter("jspPage", viewServiceJSP);
        }
    }

    @ProcessAction(name = "deleteService")
    public void deleteService(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>deleteService");
        }

        String serviceKey = ParamUtil.getString(request, "serviceKey");
        String institutionName = ParamUtil.getString(request, "institutionName");

        log.info("Deleting service with serviceKey [" + serviceKey +
                "] and institutionName [" + institutionName + "]");

        if (Validator.isNotNull(serviceKey)) {
            Long key = Long.valueOf(serviceKey);
            try {
                getRegistry().removeService(key);
                SessionMessages.add(request, "service-deleted");
             } catch (Exception e) {
                SessionErrors.add(request, "error-deleting");
                log.error("Unexpected exception deleting service [" +
                        serviceKey + "]", e);
             }

             if (Validator.isNotNull(institutionName)) {
                 InstitutionBean institution =
                     RegistryUtil.getInstitutionByName(institutionName);

                String tabs = ParamUtil.getString(request, "tabs");
                if (debug) {
                    log.info("deleteService.tabs [" + tabs + "]");
                }
                if (tabs.equals(tabServices)) {
                    request.setAttribute("tabs", tabs);
                    request.setAttribute("actionName", "viewServices");
                    request.setAttribute("institution", institution);
                    response.setRenderParameter("jspPage", viewServicesJSP);
                } else {
                    request.setAttribute("actionName", "viewInstitution");
                    request.setAttribute("institution", institution);
                    response.setRenderParameter("jspPage", viewInstitutionJSP);
                }
             }
        }
        else {
            SessionErrors.add(request, "error-deleting");
        }
    }

    /* ********** BINDING ********** */

    @ProcessAction(name = "newBinding")
    public void newBinding(ActionRequest request, ActionResponse response) {
        if (debug) {
            log.info(">>>>>>>>>>>>>newBinding");
        }

        Binding binding = new Binding();

        String institutionName = ParamUtil.getString(request, "institutionName");
        String serviceName = ParamUtil.getString(request, "serviceName");

        log.info("New binding for service [" + serviceName +
                "] and institution [" + institutionName + "]");

        ServiceBean service =
            RegistryUtil.getServiceByName(institutionName, serviceName);

        request.setAttribute("service", service);
        request.setAttribute("binding", binding);
        request.setAttribute("actionName", "addBinding");
        response.setRenderParameter("jspPage", editBindingJSP);
    }

    @ProcessAction(name = "addBinding")
    public void addBinding(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>addBinding");
        }
        ArrayList<String> errors = new ArrayList<String>();
        Binding binding = getBindingFromRequest(request);

        ServiceBean service = null;
        String institutionName = ParamUtil.getString(request, "institutionName");
        String serviceName = ParamUtil.getString(request, "serviceName");

        log.info("Adding binding for service [" + serviceName +
                "] and institution [" + institutionName + "]");

        if (Validator.isNotNull(institutionName) && Validator.isNotNull(serviceName)) {
            service =
                RegistryUtil.getServiceByName(institutionName, serviceName);
        }

        if (binding != null) {
            String description = binding.getDescription();
            String endPoint = binding.getAccessPoint();

            if (Validator.isNull(description)) {
                errors.add("binding-required-descr");
            }
            if (Validator.isNull(endPoint)) {
                errors.add("binding-required-endpoint");
            }
        }

        if (errors.size() == 0) {
           if (service != null) {
                // New binding
                if (binding.getKey() == null) {
                    service.getBinding().add(binding);
                } else {
                    // Update binding
                    List<Binding> bindings = service.getBinding();
                    for (Binding serviceBinding : bindings) {
                        if (serviceBinding.getKey().equals(binding.getKey())) {
                            // Update service binding
                            serviceBinding.setDescription(binding.getDescription());
                            serviceBinding.setAccessPoint(binding.getAccessPoint());
                            serviceBinding.setBindingType(binding.getBindingType());
                            break;
                        }
                    }
                }

                // Update service
                getRegistry().updateService(service);

                service = RegistryUtil.getServiceByName(institutionName, serviceName);
            }

            SessionErrors.clear(request);
            request.setAttribute("actionName", "viewService");
            request.setAttribute("service", service);
            request.setAttribute("institutionName", institutionName);
            request.setAttribute("serviceName", serviceName);
            response.setRenderParameter("jspPage", viewServiceJSP);
        } else {
            binding = new Binding();
            request.setAttribute("binding", binding);

            request.setAttribute("actionName", "addBinding");
            request.setAttribute("institutionName", institutionName);
            request.setAttribute("serviceName", serviceName);

            SessionErrors.clear(request);
            for (String error : errors) {
                SessionErrors.add(request, error);
            }
            response.setRenderParameter("jspPage", editBindingJSP);
        }

        // Place the service in the request
        if (service != null) {
            request.setAttribute("service", service);
        }
    }

    @ProcessAction(name = "editBinding")
    public void editBinding(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>editBinding");
        }

        String bindingKey = ParamUtil.getString(request, "bindingKey");
        String institutionName = ParamUtil.getString(request, "institutionName");
        String serviceName = ParamUtil.getString(request, "serviceName");
        String accessPoint = ParamUtil.getString(request, "accessPoint");

        log.info("Edit binding with key [" + bindingKey + "] for service [" +
                serviceName + "] and institution [" + institutionName + "]");

        Binding binding = null;

        if (Validator.isNotNull(accessPoint) && Validator.isNotNull(bindingKey)) {
            Long bindKey = Long.valueOf(bindingKey);

            binding = getRegistry().getBindingByKey(bindKey);
            if (binding != null) {

                ServiceBean service =
                    RegistryUtil.getServiceByName(institutionName, serviceName);

                request.setAttribute("service", service);
                request.setAttribute("binding", binding);
                request.setAttribute("actionName", "updateBinding");
                response.setRenderParameter("jspPage", editBindingJSP);
            } else {
                SessionErrors.add(request, "error-retrieving");
            }

         } else {
            SessionErrors.add(request, "error-updating");
         }
    }

    @ProcessAction(name = "updateBinding")
    public void updateBinding(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>updateBinding");
        }
        ArrayList<String> errors = new ArrayList<String>();

        Binding binding = getBindingFromRequest(request);

        String institutionName = ParamUtil.getString(request, "institutionName");
        String serviceName = ParamUtil.getString(request, "serviceName");

        Long bindingKey = binding.getKey();

        log.info("Updating binding with key [" + bindingKey + "] for service [" +
                serviceName + "] and institution [" + institutionName + "]");

        String description = binding.getDescription();
        String endpoint = binding.getAccessPoint();

        if (Validator.isNull(description)) {
            errors.add("binding-required-descr");
        }

        if (Validator.isNull(endpoint)) {
            errors.add("binding-required-endpoint");
        }

        if (errors.size() == 0) {
            // Retrieve service
            ServiceBean service =
                RegistryUtil.getServiceByName(institutionName, serviceName);
            if (Validator.isNotNull(bindingKey)) {
                // Update binding
                List<Binding> bindings = service.getBinding();
                for (Binding serviceBinding : bindings) {
                    if (serviceBinding.getKey().equals(binding.getKey())) {
                        serviceBinding.setDescription(binding.getDescription());
                        serviceBinding.setBindingType(binding.getBindingType());
                        serviceBinding.setAccessPoint(binding.getAccessPoint());
                    }
                }
            } else {
                // Add new
                service.getBinding().add(binding);
            }

            // Update service
            getRegistry().updateService(service);

            SessionMessages.add(request, "binding-updated");

            SessionErrors.clear(request);

            request.setAttribute("serviceName", serviceName);
            request.setAttribute("institutionName", institutionName);
            request.setAttribute("service", service);
            request.setAttribute("actionName", "viewService");
            response.setRenderParameter("jspPage", viewServiceJSP);
        } else {
            for (String error : errors) {
                SessionErrors.add(request, error);
            }
            request.setAttribute("actionName", "updateBinding");
            request.setAttribute("binding", binding);
            response.setRenderParameter("jspPage", viewServiceJSP);
        }
    }

    @ProcessAction(name = "viewBinding")
    public void viewBinding(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>viewBinding");
        }
        String institutionName = ParamUtil.getString(request, "institutionName");
        String serviceName = ParamUtil.getString(request, "serviceName");
        String accessPoint = ParamUtil.getString(request, "accessPoint");

        log.info("Retrieving binding with accessPoint [" +
                accessPoint + "] for service [" +
                serviceName + "] and institution [" + institutionName + "]");

        ServiceBean service = null;
        if (Validator.isNotNull(serviceName)) {
            service = RegistryUtil.getServiceByName(institutionName, serviceName);
            Binding binding = null;
            for (Binding serviceBinding : service.getBinding()) {
                if (accessPoint.equals(serviceBinding.getAccessPoint())) {
                    binding = serviceBinding;
                   break;
                }
            }

            request.setAttribute("binding", binding);
            request.setAttribute("actionName", "viewBinding");
         } else {
            SessionErrors.add(request, "error-updating");
         }

        response.setRenderParameter("jspPage", editBindingJSP);
    }

    @ProcessAction(name = "deleteBinding")
    public void deleteBinding(ActionRequest request, ActionResponse response)
        throws Exception {
        if (debug) {
            log.info(">>>>>>>>>>>>>deleteBinding");
        }

        String institutionName = ParamUtil.getString(request, "institutionName");
        String serviceName = ParamUtil.getString(request, "serviceName");
        String bindingKey = ParamUtil.getString(request, "bindingKey");

        log.info("Deleting binding with key [" +
                bindingKey + "] for service [" +
                serviceName + "] and institution [" + institutionName + "]");

        if (Validator.isNotNull(bindingKey) &&
                Validator.isNotNull(institutionName) &&
                Validator.isNotNull(serviceName)) {

            Long key = Long.valueOf(bindingKey);
            try {
                ServiceBean service =
                    RegistryUtil.getServiceByName(institutionName, serviceName);
                if (debug) {
                    log.info("Deleting binding from service RegistryInstance [" +
                            service.getRegistryInstance() + "]");
                }

                // Remove binding from the current service object (memory)
                List<Binding> bindings = service.getBinding();
                for (Binding binding : bindings) {
                    if (binding.getKey().longValue() == key.longValue()) {
                        if (debug) {
                            log.info("Located binding key for deletion [" + bindingKey + "]");
                        }
                        bindings.remove(binding);
                        break;
                    }
                }

                // Delete binding from registry
                getRegistry().removeBinding(key);

                request.setAttribute("actionName", "viewService");
                response.setRenderParameter("jspPage", viewServiceJSP);

                request.setAttribute("institutionName", institutionName);
                request.setAttribute("serviceName", serviceName);
                request.setAttribute("service", service);

            } catch (Exception e) {
                SessionErrors.add(request, "error-deleting");
                e.printStackTrace();
            }
        }
        else {
            SessionErrors.add(request, "error-deleting");
        }
    }

    /* ********** PRIVATE METHODS ********** */

    private InstitutionBean getInstitutionFromRequest(ActionRequest request) {
        InstitutionBean institution =
            (InstitutionBean)request.getAttribute("institution");
        if (debug) {
            log.info("Retrieving institution from request [" + institution + "]");
        }

        if (institution == null) {
            institution = new InstitutionBean();
        }

        // key
        String institutionKey = ParamUtil.getString(request, "institutionKey");
        if (Validator.isNotNull(institutionKey)) {
            institution.setKey(Long.valueOf(institutionKey));
        }
        // name
        institution.setName(ParamUtil.getString(request, "institutionName"));
        // description
        String description = ParamUtil.getString(request, "description");
        if (debug) {
            log.info("description [" + description + "]");
        }
        if (description == null) {
            description = "";
        }
        institution.setDescription(description);

        Contact contact = new Contact();
        // contact name
        contact.setPersonName(ParamUtil.getString(request, "contactName"));
        // contact description
        String contactDescription = ParamUtil.getString(request, "contactDescription");
        if (contactDescription == null) {
            contactDescription = "";
        }
        contact.setDescription(contactDescription);
        // contact address
        String contactAddress = ParamUtil.getString(request, "contactAddress");
        if (contactAddress == null) {
            contactAddress = "";
        }
        contact.setAddress(contactAddress);
        // contact email
        contact.setEmail(ParamUtil.getString(request, "contactEmail"));
        // contact phone
        String contactPhone = ParamUtil.getString(request, "contactPhone");
        if (contactPhone == null) {
            contactPhone = "";
        }
        contact.setPhone(contactPhone);

        institution.setContactInfo(contact);

        // active status
        ActiveStatusEnum activeStatus = ActiveStatusEnum.UNSPECIFIED;
        String status = ParamUtil.getString(request, "statusType");
        if (Validator.isNotNull(status)) {
            try {
                activeStatus = ActiveStatusEnum.fromValue(status);
            } catch (Exception e) {
                log.error("Exception attepting to create ActiveStatusEnum fromValue [" +
                        status + "]");
            }
        } else {
            log.error("StatusType is 'Null' setting to [" +
                    ActiveStatusEnum.UNSPECIFIED + "]");
        }
        institution.setActiveStatus(activeStatus);

        // Registry instance
        RegistryInstance registryInstance = null;
        String regInstance = ParamUtil.getString(request, "registryInstance");
        if (regInstance != null && !regInstance.isEmpty()) {
            registryInstance =
                RegistryUtil.getRegistryInstanceFromValue(regInstance);
        } else {
            registryInstance = RegistryInstance.LOCAL;
        }
        institution.setRegistryInstance(registryInstance);

        return institution;
    }

    private ServiceBean getServiceFromRequest(ActionRequest request) {
        ServiceBean service = (ServiceBean)request.getAttribute("service");
        if (debug) {
            log.info("Retrieving service from request [" + service + "]");
        }
        if (service == null) {
            service = new ServiceBean();
        }

        String serviceKey = ParamUtil.getString(request, "serviceKey");
        log.debug("Attempting to retrieve service with key [" + serviceKey + "]");
        if (Validator.isNotNull(serviceKey)) {
            service.setKey(Long.valueOf(serviceKey));
        }

        service.setName(ParamUtil.getString(request, "serviceName"));

        String description = ParamUtil.getString(request, "description");
        if (description == null) {
            description = "";
        }
        service.setDescription(description);

        // Service Type
        String serviceType = ParamUtil.getString(request, "serviceType");
        if (Validator.isNotNull(serviceType)) {
            ServiceTypeEnum serviceTypeEnum =
                ServiceTypeEnum.fromValue(serviceType);
            service.setServiceType(serviceTypeEnum);
        } else {
            log.error("ServiceType is 'Null'");
        }

        // Status Type
        ActiveStatusEnum activeStatus = ActiveStatusEnum.UNSPECIFIED;
        String status = ParamUtil.getString(request, "statusType");
        if (Validator.isNotNull(status)) {
            try {
                activeStatus = ActiveStatusEnum.fromValue(status);
            } catch (Exception e) {
                log.error("Exception attepting to create ActiveStatusEnum fromValue [" +
                        status + "]");
            }
        } else {
            log.error("StatusType is 'Null' setting to [" +
                    ActiveStatusEnum.UNSPECIFIED + "]");
        }
        service.setActiveStatus(activeStatus);

        // Institution
        String institutionName =
                ParamUtil.getString(request, "institutionName");
        service.setInstitutionName(institutionName);

        // Registry instance
        RegistryInstance registryInstance = null;
        String regInstance = ParamUtil.getString(request, "registryInstance");
        if (debug) {
            log.info("RegistryInstance [" + regInstance + "]");
        }
        if (regInstance != null && !regInstance.isEmpty()) {
            registryInstance =
                RegistryUtil.getRegistryInstanceFromValue(regInstance);
            if (debug) {
                log.info("RegistryInstanceEnum [" + registryInstance + "]");
            }
        } else {
            registryInstance = RegistryInstance.LOCAL;
        }
        if (debug) {
            log.info("setting RegistryInstanceEnum [" + registryInstance + "]");
        }
        service.setRegistryInstance(registryInstance);

        if (debug) {
            log.info("service.RegistryInstanceEnum [" + service.getRegistryInstance() + "]");
        }

        return service;
    }

    private Binding getBindingFromRequest(ActionRequest request) {
        Binding binding = (Binding)request.getAttribute("binding");
        if (debug) {
            log.info("Retrieving binding from request [" + binding + "]");
        }

        String bindingKey = ParamUtil.getString(request, "bindingKey");
        String description = ParamUtil.getString(request, "description");
        String accessPoint = ParamUtil.getString(request, "accessPoint");
        String bindingType = ParamUtil.getString(request, "bindingType");

        if (binding == null) {
            binding = new Binding();
        }

        if (Validator.isNotNull(bindingKey)) {
            binding.setKey(Long.valueOf(bindingKey));
        }
        binding.setDescription(description);
        binding.setAccessPoint(accessPoint);

        if (Validator.isNotNull(bindingType)) {
            BindingTypeEnum bindingTypeEnum =
                BindingTypeEnum.fromValue(bindingType);
            binding.setBindingType(bindingTypeEnum);
        } else {
            log.error("BindingType is 'Null'");
        }

        return binding;
    }

    private boolean isInstitutionNameUnique(String institutionName) {
        InstitutionBean institution = RegistryUtil.getInstitutionByName(institutionName);
        if (debug) {
            log.info("isInstitutionName [" + institutionName + "] Unique [" +
                    (institution == null) + "] institution [" +
                    (institution != null ? institution.getName() : null) + "]");
        }
        return (institution == null);
    }

    private boolean isServiceNameUnique(String institutionName, String serviceName) {
        ServiceBean service = RegistryUtil.getServiceByName(institutionName, serviceName);
        return (service == null);
    }

}
