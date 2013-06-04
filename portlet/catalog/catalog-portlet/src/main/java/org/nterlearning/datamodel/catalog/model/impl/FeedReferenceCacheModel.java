package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.FeedReference;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing FeedReference in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FeedReference
 * @generated
 */
public class FeedReferenceCacheModel implements CacheModel<FeedReference>,
    Serializable {
    public long feedReferenceId;
    public long companyId;
    public long groupId;
    public String contentProviderId;
    public String href;
    public String hrefHash;
    public String pshb;
    public boolean pshbSubscribed;
    public String feedIri;
    public String feedType;
    public String feedVersion;
    public double trustworthyWeight;
    public long createDate;
    public boolean removed;
    public long removedDate;
    public String removedReason;
    public long syncDate;
    public boolean syncSuccess;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{feedReferenceId=");
        sb.append(feedReferenceId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", contentProviderId=");
        sb.append(contentProviderId);
        sb.append(", href=");
        sb.append(href);
        sb.append(", hrefHash=");
        sb.append(hrefHash);
        sb.append(", pshb=");
        sb.append(pshb);
        sb.append(", pshbSubscribed=");
        sb.append(pshbSubscribed);
        sb.append(", feedIri=");
        sb.append(feedIri);
        sb.append(", feedType=");
        sb.append(feedType);
        sb.append(", feedVersion=");
        sb.append(feedVersion);
        sb.append(", trustworthyWeight=");
        sb.append(trustworthyWeight);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", removed=");
        sb.append(removed);
        sb.append(", removedDate=");
        sb.append(removedDate);
        sb.append(", removedReason=");
        sb.append(removedReason);
        sb.append(", syncDate=");
        sb.append(syncDate);
        sb.append(", syncSuccess=");
        sb.append(syncSuccess);
        sb.append("}");

        return sb.toString();
    }

    public FeedReference toEntityModel() {
        FeedReferenceImpl feedReferenceImpl = new FeedReferenceImpl();

        feedReferenceImpl.setFeedReferenceId(feedReferenceId);
        feedReferenceImpl.setCompanyId(companyId);
        feedReferenceImpl.setGroupId(groupId);

        if (contentProviderId == null) {
            feedReferenceImpl.setContentProviderId(StringPool.BLANK);
        } else {
            feedReferenceImpl.setContentProviderId(contentProviderId);
        }

        if (href == null) {
            feedReferenceImpl.setHref(StringPool.BLANK);
        } else {
            feedReferenceImpl.setHref(href);
        }

        if (hrefHash == null) {
            feedReferenceImpl.setHrefHash(StringPool.BLANK);
        } else {
            feedReferenceImpl.setHrefHash(hrefHash);
        }

        if (pshb == null) {
            feedReferenceImpl.setPshb(StringPool.BLANK);
        } else {
            feedReferenceImpl.setPshb(pshb);
        }

        feedReferenceImpl.setPshbSubscribed(pshbSubscribed);

        if (feedIri == null) {
            feedReferenceImpl.setFeedIri(StringPool.BLANK);
        } else {
            feedReferenceImpl.setFeedIri(feedIri);
        }

        if (feedType == null) {
            feedReferenceImpl.setFeedType(StringPool.BLANK);
        } else {
            feedReferenceImpl.setFeedType(feedType);
        }

        if (feedVersion == null) {
            feedReferenceImpl.setFeedVersion(StringPool.BLANK);
        } else {
            feedReferenceImpl.setFeedVersion(feedVersion);
        }

        feedReferenceImpl.setTrustworthyWeight(trustworthyWeight);

        if (createDate == Long.MIN_VALUE) {
            feedReferenceImpl.setCreateDate(null);
        } else {
            feedReferenceImpl.setCreateDate(new Date(createDate));
        }

        feedReferenceImpl.setRemoved(removed);

        if (removedDate == Long.MIN_VALUE) {
            feedReferenceImpl.setRemovedDate(null);
        } else {
            feedReferenceImpl.setRemovedDate(new Date(removedDate));
        }

        if (removedReason == null) {
            feedReferenceImpl.setRemovedReason(StringPool.BLANK);
        } else {
            feedReferenceImpl.setRemovedReason(removedReason);
        }

        if (syncDate == Long.MIN_VALUE) {
            feedReferenceImpl.setSyncDate(null);
        } else {
            feedReferenceImpl.setSyncDate(new Date(syncDate));
        }

        feedReferenceImpl.setSyncSuccess(syncSuccess);

        feedReferenceImpl.resetOriginalValues();

        return feedReferenceImpl;
    }
}
