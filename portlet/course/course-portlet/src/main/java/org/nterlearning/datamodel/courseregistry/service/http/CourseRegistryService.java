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
 * CourseRegistryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.nterlearning.datamodel.courseregistry.service.http;

public interface CourseRegistryService extends javax.xml.rpc.Service {
    public String getCourseRegistryPortAddress();

    public org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryInterface getCourseRegistryPort() throws javax.xml.rpc.ServiceException;

    public org.nterlearning.datamodel.courseregistry.service.http.CourseRegistryInterface getCourseRegistryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}