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
 * CourseRegistrySoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.nterlearning.datamodel.courseregistry.model;

public class CourseRegistrySoap  implements java.io.Serializable {
    private long companyId;

    private long courseRegistryId;

    private java.util.Calendar dateCreated;

    private String description;

    private String endpoint;

    private java.util.Calendar lastModified;

    private long primaryKey;

    private String provider;

    public CourseRegistrySoap() {
    }

    public CourseRegistrySoap(
           long companyId,
           long courseRegistryId,
           java.util.Calendar dateCreated,
           String description,
           String endpoint,
           java.util.Calendar lastModified,
           long primaryKey,
           String provider) {
           this.companyId = companyId;
           this.courseRegistryId = courseRegistryId;
           this.dateCreated = dateCreated;
           this.description = description;
           this.endpoint = endpoint;
           this.lastModified = lastModified;
           this.primaryKey = primaryKey;
           this.provider = provider;
    }

    /**
     * Gets the companyId value for this CourseRegistrySoap.
     *
     * @return companyId
     */
    public long getCompanyId() {
        return companyId;
    }

    /**
     * Sets the companyId value for this CourseRegistrySoap.
     *
     * @param companyId
     */
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets the courseRegistryId value for this CourseRegistrySoap.
     *
     * @return courseRegistryId
     */
    public long getCourseRegistryId() {
        return courseRegistryId;
    }

    /**
     * Sets the courseRegistryId value for this CourseRegistrySoap.
     *
     * @param courseRegistryId
     */
    public void setCourseRegistryId(long courseRegistryId) {
        this.courseRegistryId = courseRegistryId;
    }

    /**
     * Gets the dateCreated value for this CourseRegistrySoap.
     *
     * @return dateCreated
     */
    public java.util.Calendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the dateCreated value for this CourseRegistrySoap.
     *
     * @param dateCreated
     */
    public void setDateCreated(java.util.Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the description value for this CourseRegistrySoap.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description value for this CourseRegistrySoap.
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the endpoint value for this CourseRegistrySoap.
     *
     * @return endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the endpoint value for this CourseRegistrySoap.
     *
     * @param endpoint
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Gets the lastModified value for this CourseRegistrySoap.
     *
     * @return lastModified
     */
    public java.util.Calendar getLastModified() {
        return lastModified;
    }

    /**
     * Sets the lastModified value for this CourseRegistrySoap.
     *
     * @param lastModified
     */
    public void setLastModified(java.util.Calendar lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets the primaryKey value for this CourseRegistrySoap.
     *
     * @return primaryKey
     */
    public long getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the primaryKey value for this CourseRegistrySoap.
     *
     * @param primaryKey
     */
    public void setPrimaryKey(long primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the provider value for this CourseRegistrySoap.
     *
     * @return provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Sets the provider value for this CourseRegistrySoap.
     *
     * @param provider
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CourseRegistrySoap)) return false;
        CourseRegistrySoap other = (CourseRegistrySoap) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.companyId == other.getCompanyId() &&
            this.courseRegistryId == other.getCourseRegistryId() &&
            ((this.dateCreated==null && other.getDateCreated()==null) ||
             (this.dateCreated!=null &&
              this.dateCreated.equals(other.getDateCreated()))) &&
            ((this.description==null && other.getDescription()==null) ||
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.endpoint==null && other.getEndpoint()==null) ||
             (this.endpoint!=null &&
              this.endpoint.equals(other.getEndpoint()))) &&
            ((this.lastModified==null && other.getLastModified()==null) ||
             (this.lastModified!=null &&
              this.lastModified.equals(other.getLastModified()))) &&
            this.primaryKey == other.getPrimaryKey() &&
            ((this.provider==null && other.getProvider()==null) ||
             (this.provider!=null &&
              this.provider.equals(other.getProvider())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Long(getCompanyId()).hashCode();
        _hashCode += new Long(getCourseRegistryId()).hashCode();
        if (getDateCreated() != null) {
            _hashCode += getDateCreated().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getEndpoint() != null) {
            _hashCode += getEndpoint().hashCode();
        }
        if (getLastModified() != null) {
            _hashCode += getLastModified().hashCode();
        }
        _hashCode += new Long(getPrimaryKey()).hashCode();
        if (getProvider() != null) {
            _hashCode += getProvider().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CourseRegistrySoap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.nter.sri.com", "CourseRegistrySoap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "companyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("courseRegistryId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "courseRegistryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateCreated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateCreated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endpoint");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endpoint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModified");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastModified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "primaryKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provider");
        elemField.setXmlName(new javax.xml.namespace.QName("", "provider"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}