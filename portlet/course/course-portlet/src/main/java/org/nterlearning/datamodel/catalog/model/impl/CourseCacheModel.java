package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.Course;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Course in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Course
 * @generated
 */
public class CourseCacheModel implements CacheModel<Course>, Serializable {
    public long courseId;
    public long companyId;
    public long groupId;
    public long userId;
    public long feedReferenceId;
    public String href;
    public String fullTextHref;
    public String courseIri;
    public long updatedDate;
    public String title;
    public String transcriptAbstract;
    public String description;
    public String keywords;
    public String copyright;
    public String ratingLevel;
    public String ratingReason;
    public String duration;
    public String durationStandard;
    public double featuredStatus;
    public double popularWeight;
    public long accessCount;
    public long completedCount;
    public long createDate;
    public boolean removed;
    public long removedDate;
    public String supersedesCourseIri;
    public String supersededByCourseIri;
    public long releaseOnDate;
    public long acceptUntilDate;
    public String version;
    public long versionDate;
    public double price;
    public String priceUnit;
    public String priceTerms;
    public String priceExpiration;
    public long oneStarRateCount;
    public long twoStarRateCount;
    public long threeStarRateCount;
    public long fourStarRateCount;
    public long fiveStarRateCount;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(81);

        sb.append("{courseId=");
        sb.append(courseId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", feedReferenceId=");
        sb.append(feedReferenceId);
        sb.append(", href=");
        sb.append(href);
        sb.append(", fullTextHref=");
        sb.append(fullTextHref);
        sb.append(", courseIri=");
        sb.append(courseIri);
        sb.append(", updatedDate=");
        sb.append(updatedDate);
        sb.append(", title=");
        sb.append(title);
        sb.append(", transcriptAbstract=");
        sb.append(transcriptAbstract);
        sb.append(", description=");
        sb.append(description);
        sb.append(", keywords=");
        sb.append(keywords);
        sb.append(", copyright=");
        sb.append(copyright);
        sb.append(", ratingLevel=");
        sb.append(ratingLevel);
        sb.append(", ratingReason=");
        sb.append(ratingReason);
        sb.append(", duration=");
        sb.append(duration);
        sb.append(", durationStandard=");
        sb.append(durationStandard);
        sb.append(", featuredStatus=");
        sb.append(featuredStatus);
        sb.append(", popularWeight=");
        sb.append(popularWeight);
        sb.append(", accessCount=");
        sb.append(accessCount);
        sb.append(", completedCount=");
        sb.append(completedCount);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", removed=");
        sb.append(removed);
        sb.append(", removedDate=");
        sb.append(removedDate);
        sb.append(", supersedesCourseIri=");
        sb.append(supersedesCourseIri);
        sb.append(", supersededByCourseIri=");
        sb.append(supersededByCourseIri);
        sb.append(", releaseOnDate=");
        sb.append(releaseOnDate);
        sb.append(", acceptUntilDate=");
        sb.append(acceptUntilDate);
        sb.append(", version=");
        sb.append(version);
        sb.append(", versionDate=");
        sb.append(versionDate);
        sb.append(", price=");
        sb.append(price);
        sb.append(", priceUnit=");
        sb.append(priceUnit);
        sb.append(", priceTerms=");
        sb.append(priceTerms);
        sb.append(", priceExpiration=");
        sb.append(priceExpiration);
        sb.append(", oneStarRateCount=");
        sb.append(oneStarRateCount);
        sb.append(", twoStarRateCount=");
        sb.append(twoStarRateCount);
        sb.append(", threeStarRateCount=");
        sb.append(threeStarRateCount);
        sb.append(", fourStarRateCount=");
        sb.append(fourStarRateCount);
        sb.append(", fiveStarRateCount=");
        sb.append(fiveStarRateCount);
        sb.append("}");

        return sb.toString();
    }

    public Course toEntityModel() {
        CourseImpl courseImpl = new CourseImpl();

        courseImpl.setCourseId(courseId);
        courseImpl.setCompanyId(companyId);
        courseImpl.setGroupId(groupId);
        courseImpl.setUserId(userId);
        courseImpl.setFeedReferenceId(feedReferenceId);

        if (href == null) {
            courseImpl.setHref(StringPool.BLANK);
        } else {
            courseImpl.setHref(href);
        }

        if (fullTextHref == null) {
            courseImpl.setFullTextHref(StringPool.BLANK);
        } else {
            courseImpl.setFullTextHref(fullTextHref);
        }

        if (courseIri == null) {
            courseImpl.setCourseIri(StringPool.BLANK);
        } else {
            courseImpl.setCourseIri(courseIri);
        }

        if (updatedDate == Long.MIN_VALUE) {
            courseImpl.setUpdatedDate(null);
        } else {
            courseImpl.setUpdatedDate(new Date(updatedDate));
        }

        if (title == null) {
            courseImpl.setTitle(StringPool.BLANK);
        } else {
            courseImpl.setTitle(title);
        }

        if (transcriptAbstract == null) {
            courseImpl.setTranscriptAbstract(StringPool.BLANK);
        } else {
            courseImpl.setTranscriptAbstract(transcriptAbstract);
        }

        if (description == null) {
            courseImpl.setDescription(StringPool.BLANK);
        } else {
            courseImpl.setDescription(description);
        }

        if (keywords == null) {
            courseImpl.setKeywords(StringPool.BLANK);
        } else {
            courseImpl.setKeywords(keywords);
        }

        if (copyright == null) {
            courseImpl.setCopyright(StringPool.BLANK);
        } else {
            courseImpl.setCopyright(copyright);
        }

        if (ratingLevel == null) {
            courseImpl.setRatingLevel(StringPool.BLANK);
        } else {
            courseImpl.setRatingLevel(ratingLevel);
        }

        if (ratingReason == null) {
            courseImpl.setRatingReason(StringPool.BLANK);
        } else {
            courseImpl.setRatingReason(ratingReason);
        }

        if (duration == null) {
            courseImpl.setDuration(StringPool.BLANK);
        } else {
            courseImpl.setDuration(duration);
        }

        if (durationStandard == null) {
            courseImpl.setDurationStandard(StringPool.BLANK);
        } else {
            courseImpl.setDurationStandard(durationStandard);
        }

        courseImpl.setFeaturedStatus(featuredStatus);
        courseImpl.setPopularWeight(popularWeight);
        courseImpl.setAccessCount(accessCount);
        courseImpl.setCompletedCount(completedCount);

        if (createDate == Long.MIN_VALUE) {
            courseImpl.setCreateDate(null);
        } else {
            courseImpl.setCreateDate(new Date(createDate));
        }

        courseImpl.setRemoved(removed);

        if (removedDate == Long.MIN_VALUE) {
            courseImpl.setRemovedDate(null);
        } else {
            courseImpl.setRemovedDate(new Date(removedDate));
        }

        if (supersedesCourseIri == null) {
            courseImpl.setSupersedesCourseIri(StringPool.BLANK);
        } else {
            courseImpl.setSupersedesCourseIri(supersedesCourseIri);
        }

        if (supersededByCourseIri == null) {
            courseImpl.setSupersededByCourseIri(StringPool.BLANK);
        } else {
            courseImpl.setSupersededByCourseIri(supersededByCourseIri);
        }

        if (releaseOnDate == Long.MIN_VALUE) {
            courseImpl.setReleaseOnDate(null);
        } else {
            courseImpl.setReleaseOnDate(new Date(releaseOnDate));
        }

        if (acceptUntilDate == Long.MIN_VALUE) {
            courseImpl.setAcceptUntilDate(null);
        } else {
            courseImpl.setAcceptUntilDate(new Date(acceptUntilDate));
        }

        if (version == null) {
            courseImpl.setVersion(StringPool.BLANK);
        } else {
            courseImpl.setVersion(version);
        }

        if (versionDate == Long.MIN_VALUE) {
            courseImpl.setVersionDate(null);
        } else {
            courseImpl.setVersionDate(new Date(versionDate));
        }

        courseImpl.setPrice(price);

        if (priceUnit == null) {
            courseImpl.setPriceUnit(StringPool.BLANK);
        } else {
            courseImpl.setPriceUnit(priceUnit);
        }

        if (priceTerms == null) {
            courseImpl.setPriceTerms(StringPool.BLANK);
        } else {
            courseImpl.setPriceTerms(priceTerms);
        }

        if (priceExpiration == null) {
            courseImpl.setPriceExpiration(StringPool.BLANK);
        } else {
            courseImpl.setPriceExpiration(priceExpiration);
        }

        courseImpl.setOneStarRateCount(oneStarRateCount);
        courseImpl.setTwoStarRateCount(twoStarRateCount);
        courseImpl.setThreeStarRateCount(threeStarRateCount);
        courseImpl.setFourStarRateCount(fourStarRateCount);
        courseImpl.setFiveStarRateCount(fiveStarRateCount);

        courseImpl.resetOriginalValues();

        return courseImpl;
    }
}
