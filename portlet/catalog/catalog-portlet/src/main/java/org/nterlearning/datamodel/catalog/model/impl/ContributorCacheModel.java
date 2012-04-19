package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.Contributor;

import java.io.Serializable;

/**
 * The cache model class for representing Contributor in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Contributor
 * @generated
 */
public class ContributorCacheModel implements CacheModel<Contributor>,
    Serializable {
    public long contributorId;
    public long courseId;
    public long componentId;
    public String role;
    public String contributorName;
    public String virtualCardData;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{contributorId=");
        sb.append(contributorId);
        sb.append(", courseId=");
        sb.append(courseId);
        sb.append(", componentId=");
        sb.append(componentId);
        sb.append(", role=");
        sb.append(role);
        sb.append(", contributorName=");
        sb.append(contributorName);
        sb.append(", virtualCardData=");
        sb.append(virtualCardData);
        sb.append("}");

        return sb.toString();
    }

    public Contributor toEntityModel() {
        ContributorImpl contributorImpl = new ContributorImpl();

        contributorImpl.setContributorId(contributorId);
        contributorImpl.setCourseId(courseId);
        contributorImpl.setComponentId(componentId);

        if (role == null) {
            contributorImpl.setRole(StringPool.BLANK);
        } else {
            contributorImpl.setRole(role);
        }

        if (contributorName == null) {
            contributorImpl.setContributorName(StringPool.BLANK);
        } else {
            contributorImpl.setContributorName(contributorName);
        }

        if (virtualCardData == null) {
            contributorImpl.setVirtualCardData(StringPool.BLANK);
        } else {
            contributorImpl.setVirtualCardData(virtualCardData);
        }

        contributorImpl.resetOriginalValues();

        return contributorImpl;
    }
}
