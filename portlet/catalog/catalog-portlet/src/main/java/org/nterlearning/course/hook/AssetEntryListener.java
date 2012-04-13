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

package org.nterlearning.course.hook;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portlet.asset.model.AssetEntry;
import org.nterlearning.atom.generator.NterActivityStreamGenerator;
import org.nterlearning.datamodel.catalog.model.CourseReview;

public class AssetEntryListener implements ModelListener<AssetEntry> {

    static Log mLog = LogFactoryUtil.getLog(AssetEntryListener.class);

    public void onBeforeAddAssociation(Object o, String s, Object o1) throws ModelListenerException {
    }

    public void onAfterAddAssociation(Object o, String s, Object o1) throws ModelListenerException {
    }

    public void onBeforeRemoveAssociation(Object o, String s, Object o1) throws ModelListenerException {
    }

    public void onAfterRemoveAssociation(Object o, String s, Object o1) throws ModelListenerException {
    }

    public void onBeforeCreate(AssetEntry assetEntry) throws ModelListenerException {
    }

    /**
     * After a courseReview has been added, create an activity feed with verb add.
     *
     * @param  assetEntry The freshly added courseReview
     * @throws com.liferay.portal.ModelListenerException Standard Liferay exception
     */
    public void onAfterCreate(AssetEntry assetEntry) throws ModelListenerException {
        try {
            if (CourseReview.class.getName().equals(assetEntry.getClassName())) {
                mLog.info("Course Review created with id: " + assetEntry.getClassPK());
                NterActivityStreamGenerator.courseReviewCreated(assetEntry.getClassPK());
            }
        }
        catch (Exception e) {
            mLog.error(ExceptionUtils.getFullStackTrace(e));
        }
    }

    public void onBeforeRemove(AssetEntry assetEntry) throws ModelListenerException {
    	try {
            if (CourseReview.class.getName().equals(assetEntry.getClassName())) {
                mLog.info("Course Review deleted with id: " + assetEntry.getClassPK());
                NterActivityStreamGenerator.courseReviewDeleted(assetEntry.getClassPK());
            }
        }
        catch (Exception e) {
            mLog.error(ExceptionUtils.getFullStackTrace(e));
        }
    }

    /**
     * After a courseReview has been deleted, create an activity feed with verb remove.
     *
     * @param assetEntry The freshly deleted courseReview
     * @throws com.liferay.portal.ModelListenerException Standard Liferay exception
     */
    public void onAfterRemove(AssetEntry assetEntry) throws ModelListenerException {        
    }

    public void onBeforeUpdate(AssetEntry assetEntry) throws ModelListenerException {
    }

    /**
     * After a courseReview has been updated, create an activity feed with verb update.
     *
     * @param assetEntry The freshly updated courseReview
     * @throws com.liferay.portal.ModelListenerException Standard Liferay exception
     */
    public void onAfterUpdate(AssetEntry assetEntry) throws ModelListenerException {
        try {
            if (CourseReview.class.getName().equals(assetEntry.getClassName())) {
                mLog.info("Course Review updated with id: " + assetEntry.getClassPK());
                NterActivityStreamGenerator.courseReviewUpdated(assetEntry.getClassPK());
            }
        }
        catch (Exception e) {
            mLog.error(ExceptionUtils.getFullStackTrace(e));
        }
    }
}
