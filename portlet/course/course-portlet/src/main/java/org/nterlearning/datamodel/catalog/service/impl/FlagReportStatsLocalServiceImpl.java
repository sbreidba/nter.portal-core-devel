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
import org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException;
import org.nterlearning.datamodel.catalog.model.FlagReportStats;
import org.nterlearning.datamodel.catalog.service.base.FlagReportStatsLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the flag report stats local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.FlagReportStatsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.FlagReportStatsLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.FlagReportStatsLocalServiceUtil
 */
public class FlagReportStatsLocalServiceImpl
    extends FlagReportStatsLocalServiceBaseImpl {

    @Override
    public FlagReportStats addFlagReportStats(FlagReportStats flagReportStats) throws SystemException {
        long id = counterLocalService.increment(FlagReportStats.class.getName());
        flagReportStats.setPrimaryKey(id);
        return super.addFlagReportStats(flagReportStats);
    }

    public void incrementTotalEntries(long classNameId, long classPK)
            throws SystemException, NoSuchFlagReportStatsException {
        FlagReportStats flagReportStats = flagReportStatsPersistence.findByClassNameIdWithClassPK(classNameId, classPK);

        flagReportStats.setTotalEntries(flagReportStats.getTotalEntries()+1);
        flagReportStatsPersistence.update(flagReportStats, true);
    }

    public void incrementTotalModerated(long classNameId, long classPK)
            throws SystemException, NoSuchFlagReportStatsException {
        FlagReportStats flagReportStats = flagReportStatsPersistence.findByClassNameIdWithClassPK(classNameId, classPK);

        flagReportStats.setTotalModerated(flagReportStats.getTotalModerated()+1);
        flagReportStatsPersistence.update(flagReportStats, true);
    }

    public void incrementTotalApproved(long classNameId, long classPK)
            throws SystemException, NoSuchFlagReportStatsException {
        FlagReportStats flagReportStats = flagReportStatsPersistence.findByClassNameIdWithClassPK(classNameId, classPK);

        flagReportStats.setTotalApproved(flagReportStats.getTotalApproved()+1);
        flagReportStatsPersistence.update(flagReportStats, true);
    }

    public FlagReportStats findByFlagReportStatsId(long flagReportStatsId) throws NoSuchFlagReportStatsException, SystemException {
        return flagReportStatsPersistence.findByflagReportStatsId(flagReportStatsId);
    }

    public FlagReportStats fetchByFlagReportStatsId(long flagReportStatsId) throws SystemException {
        return flagReportStatsPersistence.fetchByflagReportStatsId(flagReportStatsId);
    }

    public FlagReportStats findByClassNameIdWithClassPK(long classNameId, long classPK) throws NoSuchFlagReportStatsException, SystemException {
        return flagReportStatsPersistence.findByClassNameIdWithClassPK(classNameId, classPK);
    }

    public FlagReportStats fetchByClassNameIdWithClassPK(long classNameId, long classPK) throws SystemException {
        return flagReportStatsPersistence.fetchByClassNameIdWithClassPK(classNameId, classPK);
    }

    public Integer countAll() throws SystemException {
        return flagReportStatsPersistence.countAll();
    }

    public List<FlagReportStats> findAll(int start, int end) throws SystemException {
        return flagReportStatsPersistence.findAll(start, end);
    }
}