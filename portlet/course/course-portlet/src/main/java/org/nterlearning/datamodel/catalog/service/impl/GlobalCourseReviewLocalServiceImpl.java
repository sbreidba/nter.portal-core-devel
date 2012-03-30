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

package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.service.base.GlobalCourseReviewLocalServiceBaseImpl;
import org.nterlearning.datamodel.catalog.service.persistence.GlobalCourseReviewFinderUtil;

import java.util.List;

/**
 * The implementation of the global course review local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.GlobalCourseReviewLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalServiceUtil
 */
public class GlobalCourseReviewLocalServiceImpl
    extends GlobalCourseReviewLocalServiceBaseImpl {

   @Override
    public GlobalCourseReview addGlobalCourseReview(GlobalCourseReview globalCourseReview) throws SystemException {
        long id = counterLocalService.increment(GlobalCourseReview.class.getName());
        globalCourseReview.setPrimaryKey(id);
        return super.addGlobalCourseReview(globalCourseReview);
    }

    public void setReviewIsHidden(long globalCourseReviewId, Boolean hidden)
        throws SystemException, NoSuchGlobalCourseReviewException {

        GlobalCourseReview globalCourseReview = globalCourseReviewPersistence.findByPrimaryKey(globalCourseReviewId);

        globalCourseReview.setIsHidden(hidden);
        globalCourseReviewPersistence.update(globalCourseReview, true);
    }

    public GlobalCourseReview findByCourseReviewIri(String courseReviewIri)
        throws SystemException, NoSuchGlobalCourseReviewException {
        return globalCourseReviewPersistence.findByCourseReviewIri(courseReviewIri);
    }
    public List<GlobalCourseReview> findByCourseId(long courseId) throws SystemException {
        return globalCourseReviewPersistence.findByCourseId(courseId);
    }

    public List<GlobalCourseReview> findByCourseIri(String courseIri) throws SystemException {
        return globalCourseReviewPersistence.findByCourseIri(courseIri);
    }

    public List<GlobalCourseReview> findValidByCourseId(long courseId, int start, int end) throws SystemException {
        return GlobalCourseReviewFinderUtil.findValidByCourseId(courseId, start, end);
    }

    public long countValidByCourseId(long courseId) throws SystemException {
        return GlobalCourseReviewFinderUtil.countValidByCourseId(courseId);
    }
}