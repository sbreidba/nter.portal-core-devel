package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.FlagReportStats;

import java.io.Serializable;

/**
 * The cache model class for representing FlagReportStats in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FlagReportStats
 * @generated
 */
public class FlagReportStatsCacheModel implements CacheModel<FlagReportStats>,
    Serializable {
    public long flagReportStatsId;
    public long classNameId;
    public long classPK;
    public Integer totalEntries;
    public Integer totalModerated;
    public Integer totalApproved;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{flagReportStatsId=");
        sb.append(flagReportStatsId);
        sb.append(", classNameId=");
        sb.append(classNameId);
        sb.append(", classPK=");
        sb.append(classPK);
        sb.append(", totalEntries=");
        sb.append(totalEntries);
        sb.append(", totalModerated=");
        sb.append(totalModerated);
        sb.append(", totalApproved=");
        sb.append(totalApproved);
        sb.append("}");

        return sb.toString();
    }

    public FlagReportStats toEntityModel() {
        FlagReportStatsImpl flagReportStatsImpl = new FlagReportStatsImpl();

        flagReportStatsImpl.setFlagReportStatsId(flagReportStatsId);
        flagReportStatsImpl.setClassNameId(classNameId);
        flagReportStatsImpl.setClassPK(classPK);
        flagReportStatsImpl.setTotalEntries(totalEntries);
        flagReportStatsImpl.setTotalModerated(totalModerated);
        flagReportStatsImpl.setTotalApproved(totalApproved);

        flagReportStatsImpl.resetOriginalValues();

        return flagReportStatsImpl;
    }
}
