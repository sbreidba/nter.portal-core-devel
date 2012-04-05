package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FlagReportStats}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FlagReportStats
 * @generated
 */
public class FlagReportStatsWrapper implements FlagReportStats,
    ModelWrapper<FlagReportStats> {
    private FlagReportStats _flagReportStats;

    public FlagReportStatsWrapper(FlagReportStats flagReportStats) {
        _flagReportStats = flagReportStats;
    }

    public Class<?> getModelClass() {
        return FlagReportStats.class;
    }

    public String getModelClassName() {
        return FlagReportStats.class.getName();
    }

    /**
    * Returns the primary key of this flag report stats.
    *
    * @return the primary key of this flag report stats
    */
    public long getPrimaryKey() {
        return _flagReportStats.getPrimaryKey();
    }

    /**
    * Sets the primary key of this flag report stats.
    *
    * @param primaryKey the primary key of this flag report stats
    */
    public void setPrimaryKey(long primaryKey) {
        _flagReportStats.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the flag report stats ID of this flag report stats.
    *
    * @return the flag report stats ID of this flag report stats
    */
    public long getFlagReportStatsId() {
        return _flagReportStats.getFlagReportStatsId();
    }

    /**
    * Sets the flag report stats ID of this flag report stats.
    *
    * @param flagReportStatsId the flag report stats ID of this flag report stats
    */
    public void setFlagReportStatsId(long flagReportStatsId) {
        _flagReportStats.setFlagReportStatsId(flagReportStatsId);
    }

    /**
    * Returns the fully qualified class name of this flag report stats.
    *
    * @return the fully qualified class name of this flag report stats
    */
    public java.lang.String getClassName() {
        return _flagReportStats.getClassName();
    }

    /**
    * Returns the class name ID of this flag report stats.
    *
    * @return the class name ID of this flag report stats
    */
    public long getClassNameId() {
        return _flagReportStats.getClassNameId();
    }

    /**
    * Sets the class name ID of this flag report stats.
    *
    * @param classNameId the class name ID of this flag report stats
    */
    public void setClassNameId(long classNameId) {
        _flagReportStats.setClassNameId(classNameId);
    }

    /**
    * Returns the class p k of this flag report stats.
    *
    * @return the class p k of this flag report stats
    */
    public long getClassPK() {
        return _flagReportStats.getClassPK();
    }

    /**
    * Sets the class p k of this flag report stats.
    *
    * @param classPK the class p k of this flag report stats
    */
    public void setClassPK(long classPK) {
        _flagReportStats.setClassPK(classPK);
    }

    /**
    * Returns the total entries of this flag report stats.
    *
    * @return the total entries of this flag report stats
    */
    public java.lang.Integer getTotalEntries() {
        return _flagReportStats.getTotalEntries();
    }

    /**
    * Sets the total entries of this flag report stats.
    *
    * @param totalEntries the total entries of this flag report stats
    */
    public void setTotalEntries(java.lang.Integer totalEntries) {
        _flagReportStats.setTotalEntries(totalEntries);
    }

    /**
    * Returns the total moderated of this flag report stats.
    *
    * @return the total moderated of this flag report stats
    */
    public java.lang.Integer getTotalModerated() {
        return _flagReportStats.getTotalModerated();
    }

    /**
    * Sets the total moderated of this flag report stats.
    *
    * @param totalModerated the total moderated of this flag report stats
    */
    public void setTotalModerated(java.lang.Integer totalModerated) {
        _flagReportStats.setTotalModerated(totalModerated);
    }

    /**
    * Returns the total approved of this flag report stats.
    *
    * @return the total approved of this flag report stats
    */
    public java.lang.Integer getTotalApproved() {
        return _flagReportStats.getTotalApproved();
    }

    /**
    * Sets the total approved of this flag report stats.
    *
    * @param totalApproved the total approved of this flag report stats
    */
    public void setTotalApproved(java.lang.Integer totalApproved) {
        _flagReportStats.setTotalApproved(totalApproved);
    }

    public boolean isNew() {
        return _flagReportStats.isNew();
    }

    public void setNew(boolean n) {
        _flagReportStats.setNew(n);
    }

    public boolean isCachedModel() {
        return _flagReportStats.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _flagReportStats.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _flagReportStats.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _flagReportStats.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _flagReportStats.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _flagReportStats.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _flagReportStats.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FlagReportStatsWrapper((FlagReportStats) _flagReportStats.clone());
    }

    public int compareTo(FlagReportStats flagReportStats) {
        return _flagReportStats.compareTo(flagReportStats);
    }

    @Override
    public int hashCode() {
        return _flagReportStats.hashCode();
    }

    public com.liferay.portal.model.CacheModel<FlagReportStats> toCacheModel() {
        return _flagReportStats.toCacheModel();
    }

    public FlagReportStats toEscapedModel() {
        return new FlagReportStatsWrapper(_flagReportStats.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _flagReportStats.toString();
    }

    public java.lang.String toXmlString() {
        return _flagReportStats.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _flagReportStats.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public FlagReportStats getWrappedFlagReportStats() {
        return _flagReportStats;
    }

    public FlagReportStats getWrappedModel() {
        return _flagReportStats;
    }

    public void resetOriginalValues() {
        _flagReportStats.resetOriginalValues();
    }
}
