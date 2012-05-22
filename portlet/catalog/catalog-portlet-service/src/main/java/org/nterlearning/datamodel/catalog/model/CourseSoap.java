package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.nterlearning.datamodel.catalog.service.http.CourseServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.nterlearning.datamodel.catalog.service.http.CourseServiceSoap
 * @generated
 */
public class CourseSoap implements Serializable {
    private long _courseId;
    private long _companyId;
    private long _groupId;
    private long _userId;
    private long _feedReferenceId;
    private String _href;
    private String _fullTextHref;
    private String _courseIri;
    private Date _updatedDate;
    private String _title;
    private String _transcriptAbstract;
    private String _description;
    private String _keywords;
    private String _copyright;
    private String _ratingLevel;
    private String _ratingReason;
    private String _duration;
    private String _durationStandard;
    private double _featuredStatus;
    private double _popularWeight;
    private long _accessCount;
    private long _completedCount;
    private Date _createDate;
    private boolean _removed;
    private Date _removedDate;
    private String _supersedesCourseIri;
    private String _supersededByCourseIri;
    private Date _releaseOnDate;
    private Date _acceptUntilDate;
    private String _version;
    private Date _versionDate;
    private double _price;
    private String _priceUnit;
    private String _priceTerms;
    private String _priceExpiration;
    private long _oneStarRateCount;
    private long _twoStarRateCount;
    private long _threeStarRateCount;
    private long _fourStarRateCount;
    private long _fiveStarRateCount;

    public CourseSoap() {
    }

    public static CourseSoap toSoapModel(Course model) {
        CourseSoap soapModel = new CourseSoap();

        soapModel.setCourseId(model.getCourseId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setUserId(model.getUserId());
        soapModel.setFeedReferenceId(model.getFeedReferenceId());
        soapModel.setHref(model.getHref());
        soapModel.setFullTextHref(model.getFullTextHref());
        soapModel.setCourseIri(model.getCourseIri());
        soapModel.setUpdatedDate(model.getUpdatedDate());
        soapModel.setTitle(model.getTitle());
        soapModel.setTranscriptAbstract(model.getTranscriptAbstract());
        soapModel.setDescription(model.getDescription());
        soapModel.setKeywords(model.getKeywords());
        soapModel.setCopyright(model.getCopyright());
        soapModel.setRatingLevel(model.getRatingLevel());
        soapModel.setRatingReason(model.getRatingReason());
        soapModel.setDuration(model.getDuration());
        soapModel.setDurationStandard(model.getDurationStandard());
        soapModel.setFeaturedStatus(model.getFeaturedStatus());
        soapModel.setPopularWeight(model.getPopularWeight());
        soapModel.setAccessCount(model.getAccessCount());
        soapModel.setCompletedCount(model.getCompletedCount());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setRemoved(model.getRemoved());
        soapModel.setRemovedDate(model.getRemovedDate());
        soapModel.setSupersedesCourseIri(model.getSupersedesCourseIri());
        soapModel.setSupersededByCourseIri(model.getSupersededByCourseIri());
        soapModel.setReleaseOnDate(model.getReleaseOnDate());
        soapModel.setAcceptUntilDate(model.getAcceptUntilDate());
        soapModel.setVersion(model.getVersion());
        soapModel.setVersionDate(model.getVersionDate());
        soapModel.setPrice(model.getPrice());
        soapModel.setPriceUnit(model.getPriceUnit());
        soapModel.setPriceTerms(model.getPriceTerms());
        soapModel.setPriceExpiration(model.getPriceExpiration());
        soapModel.setOneStarRateCount(model.getOneStarRateCount());
        soapModel.setTwoStarRateCount(model.getTwoStarRateCount());
        soapModel.setThreeStarRateCount(model.getThreeStarRateCount());
        soapModel.setFourStarRateCount(model.getFourStarRateCount());
        soapModel.setFiveStarRateCount(model.getFiveStarRateCount());

        return soapModel;
    }

    public static CourseSoap[] toSoapModels(Course[] models) {
        CourseSoap[] soapModels = new CourseSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CourseSoap[][] toSoapModels(Course[][] models) {
        CourseSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CourseSoap[models.length][models[0].length];
        } else {
            soapModels = new CourseSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CourseSoap[] toSoapModels(List<Course> models) {
        List<CourseSoap> soapModels = new ArrayList<CourseSoap>(models.size());

        for (Course model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CourseSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _courseId;
    }

    public void setPrimaryKey(long pk) {
        setCourseId(pk);
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
    }

    public String getHref() {
        return _href;
    }

    public void setHref(String href) {
        _href = href;
    }

    public String getFullTextHref() {
        return _fullTextHref;
    }

    public void setFullTextHref(String fullTextHref) {
        _fullTextHref = fullTextHref;
    }

    public String getCourseIri() {
        return _courseIri;
    }

    public void setCourseIri(String courseIri) {
        _courseIri = courseIri;
    }

    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getTranscriptAbstract() {
        return _transcriptAbstract;
    }

    public void setTranscriptAbstract(String transcriptAbstract) {
        _transcriptAbstract = transcriptAbstract;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getKeywords() {
        return _keywords;
    }

    public void setKeywords(String keywords) {
        _keywords = keywords;
    }

    public String getCopyright() {
        return _copyright;
    }

    public void setCopyright(String copyright) {
        _copyright = copyright;
    }

    public String getRatingLevel() {
        return _ratingLevel;
    }

    public void setRatingLevel(String ratingLevel) {
        _ratingLevel = ratingLevel;
    }

    public String getRatingReason() {
        return _ratingReason;
    }

    public void setRatingReason(String ratingReason) {
        _ratingReason = ratingReason;
    }

    public String getDuration() {
        return _duration;
    }

    public void setDuration(String duration) {
        _duration = duration;
    }

    public String getDurationStandard() {
        return _durationStandard;
    }

    public void setDurationStandard(String durationStandard) {
        _durationStandard = durationStandard;
    }

    public double getFeaturedStatus() {
        return _featuredStatus;
    }

    public void setFeaturedStatus(double featuredStatus) {
        _featuredStatus = featuredStatus;
    }

    public double getPopularWeight() {
        return _popularWeight;
    }

    public void setPopularWeight(double popularWeight) {
        _popularWeight = popularWeight;
    }

    public long getAccessCount() {
        return _accessCount;
    }

    public void setAccessCount(long accessCount) {
        _accessCount = accessCount;
    }

    public long getCompletedCount() {
        return _completedCount;
    }

    public void setCompletedCount(long completedCount) {
        _completedCount = completedCount;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public boolean getRemoved() {
        return _removed;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public void setRemoved(boolean removed) {
        _removed = removed;
    }

    public Date getRemovedDate() {
        return _removedDate;
    }

    public void setRemovedDate(Date removedDate) {
        _removedDate = removedDate;
    }

    public String getSupersedesCourseIri() {
        return _supersedesCourseIri;
    }

    public void setSupersedesCourseIri(String supersedesCourseIri) {
        _supersedesCourseIri = supersedesCourseIri;
    }

    public String getSupersededByCourseIri() {
        return _supersededByCourseIri;
    }

    public void setSupersededByCourseIri(String supersededByCourseIri) {
        _supersededByCourseIri = supersededByCourseIri;
    }

    public Date getReleaseOnDate() {
        return _releaseOnDate;
    }

    public void setReleaseOnDate(Date releaseOnDate) {
        _releaseOnDate = releaseOnDate;
    }

    public Date getAcceptUntilDate() {
        return _acceptUntilDate;
    }

    public void setAcceptUntilDate(Date acceptUntilDate) {
        _acceptUntilDate = acceptUntilDate;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public Date getVersionDate() {
        return _versionDate;
    }

    public void setVersionDate(Date versionDate) {
        _versionDate = versionDate;
    }

    public double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public String getPriceUnit() {
        return _priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        _priceUnit = priceUnit;
    }

    public String getPriceTerms() {
        return _priceTerms;
    }

    public void setPriceTerms(String priceTerms) {
        _priceTerms = priceTerms;
    }

    public String getPriceExpiration() {
        return _priceExpiration;
    }

    public void setPriceExpiration(String priceExpiration) {
        _priceExpiration = priceExpiration;
    }

    public long getOneStarRateCount() {
        return _oneStarRateCount;
    }

    public void setOneStarRateCount(long oneStarRateCount) {
        _oneStarRateCount = oneStarRateCount;
    }

    public long getTwoStarRateCount() {
        return _twoStarRateCount;
    }

    public void setTwoStarRateCount(long twoStarRateCount) {
        _twoStarRateCount = twoStarRateCount;
    }

    public long getThreeStarRateCount() {
        return _threeStarRateCount;
    }

    public void setThreeStarRateCount(long threeStarRateCount) {
        _threeStarRateCount = threeStarRateCount;
    }

    public long getFourStarRateCount() {
        return _fourStarRateCount;
    }

    public void setFourStarRateCount(long fourStarRateCount) {
        _fourStarRateCount = fourStarRateCount;
    }

    public long getFiveStarRateCount() {
        return _fiveStarRateCount;
    }

    public void setFiveStarRateCount(long fiveStarRateCount) {
        _fiveStarRateCount = fiveStarRateCount;
    }
}
