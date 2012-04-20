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


/**
 * CourseRegistryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.nterlearning.datamodel.courseregistry.service.http;

public class CourseRegistryServiceLocator extends org.apache.axis.client.Service implements org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryService {

    public CourseRegistryServiceLocator() {
    }

    public CourseRegistryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CourseRegistryServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CourseRegistryPort
    private String CourseRegistryPort_address = "http://127.0.0.1:8080/course-registry-portlet/axis/CourseRegistryService";

    public String getCourseRegistryPortAddress() {
        return CourseRegistryPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String CourseRegistryPortWSDDServiceName = "CourseRegistryPort";

    public String getCourseRegistryPortWSDDServiceName() {
        return CourseRegistryPortWSDDServiceName;
    }

    public void setCourseRegistryPortWSDDServiceName(String name) {
        CourseRegistryPortWSDDServiceName = name;
    }

    public org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryInterface getCourseRegistryPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CourseRegistryPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCourseRegistryPort(endpoint);
    }

    public org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryInterface getCourseRegistryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryPortSoapBindingStub _stub = new org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryPortSoapBindingStub(portAddress, this);
            _stub.setPortName(getCourseRegistryPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCourseRegistryPortEndpointAddress(String address) {
        CourseRegistryPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryPortSoapBindingStub _stub = new org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryPortSoapBindingStub(new java.net.URL(CourseRegistryPort_address), this);
                _stub.setPortName(getCourseRegistryPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("CourseRegistryPort".equals(inputPortName)) {
            return getCourseRegistryPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:http.service.nter.sri.com", "CourseRegistryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:http.service.nter.sri.com", "CourseRegistryPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("CourseRegistryPort".equals(portName)) {
            setCourseRegistryPortEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}