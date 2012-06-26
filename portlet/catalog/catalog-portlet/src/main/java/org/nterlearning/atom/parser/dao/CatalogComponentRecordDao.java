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


package org.nterlearning.atom.parser.dao;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;

import java.util.List;
import java.util.Vector;


public class CatalogComponentRecordDao extends AbstractDao<ComponentRecord> {

    public CatalogComponentRecordDao() {
        super();
    }


    @Override
    public void persistAdd(ComponentRecord componentRecord)
            throws SystemException {
        addTargetComponentRecord(componentRecord);
    }


    @Override
    public void persistUpdate(ComponentRecord componentRecord)
            throws SystemException {
        updateTargetComponentRecord(componentRecord);
    }


    @Override
    public void persistDelete(ComponentRecord componentRecord)
            throws SystemException {
        deleteTargetComponentRecord(componentRecord);
    }


    @Override
    //This primaryKey is needed for update persist
    public long getPrimaryKey(ComponentRecord componentRecord) {
        return componentRecord.getPrimaryKey();
    }


    @Override
    //This primaryKey is used during update persist
    public void setPrimaryKey(ComponentRecord componentRecord, long primaryKey) {
        componentRecord.setPrimaryKey(primaryKey);
    }


    @Override
    //This primaryKey of parent is used to retrieve entries to revert
    public List<ComponentRecord> getRevertList(long parentPrimaryKey) {
        List<ComponentRecord> componentRecords = new Vector<ComponentRecord>();
        try {
            return CourseRecordLocalServiceUtil.getComponentRecords(parentPrimaryKey);
        }
        catch (SystemException e) {
            return componentRecords;
        }
    }


    @Override
    //combination of courseRecordId and componentIri make each entry unique
    public String getContents(ComponentRecord componentRecord) {
        return (componentRecord.getCourseRecordId() + "+" + componentRecord.getComponentIri());
    }


    @Override
    //This identifier is returned if issue found during persist
    public String getId(ComponentRecord componentRecord) {
        return componentRecord.getComponentIri();
    }


    @Override
    public String getLabel(ComponentRecord componentRecord) {
        return "ComponentRecord componentIRI";
    }


    /**
     * @param targetComponentRecord
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static void addTargetComponentRecord(ComponentRecord targetComponentRecord)
            throws SystemException {
        ComponentRecordLocalServiceUtil.addComponentRecord(targetComponentRecord);
    }


    /**
     * @param targetComponentRecord
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static void updateTargetComponentRecord(ComponentRecord targetComponentRecord)
            throws SystemException {
        ComponentRecordLocalServiceUtil.updateComponentRecord(targetComponentRecord);
    }


    /**
     * @param targetComponentRecords
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static void deleteTargetComponentRecords(List<ComponentRecord> targetComponentRecords)
            throws SystemException {
        //used to process transaction exception
        for (ComponentRecord targetComponentRecord : targetComponentRecords) {
            deleteTargetComponentRecord(targetComponentRecord);
        }
    }


    /**
     * @param targetComponentRecord
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static void deleteTargetComponentRecord(ComponentRecord targetComponentRecord)
            throws SystemException {
        ComponentRecordLocalServiceUtil.deleteComponentRecord(targetComponentRecord);
    }
}