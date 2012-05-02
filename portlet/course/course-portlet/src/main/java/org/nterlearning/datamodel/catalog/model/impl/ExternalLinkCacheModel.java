package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.ExternalLink;

import java.io.Serializable;

/**
 * The cache model class for representing ExternalLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLink
 * @generated
 */
public class ExternalLinkCacheModel implements CacheModel<ExternalLink>,
    Serializable {
    public long linkId;
    public long courseId;
    public long componentId;
    public String linkType;
    public String linkUrl;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{linkId=");
        sb.append(linkId);
        sb.append(", courseId=");
        sb.append(courseId);
        sb.append(", componentId=");
        sb.append(componentId);
        sb.append(", linkType=");
        sb.append(linkType);
        sb.append(", linkUrl=");
        sb.append(linkUrl);
        sb.append("}");

        return sb.toString();
    }

    public ExternalLink toEntityModel() {
        ExternalLinkImpl externalLinkImpl = new ExternalLinkImpl();

        externalLinkImpl.setLinkId(linkId);
        externalLinkImpl.setCourseId(courseId);
        externalLinkImpl.setComponentId(componentId);

        if (linkType == null) {
            externalLinkImpl.setLinkType(StringPool.BLANK);
        } else {
            externalLinkImpl.setLinkType(linkType);
        }

        if (linkUrl == null) {
            externalLinkImpl.setLinkUrl(StringPool.BLANK);
        } else {
            externalLinkImpl.setLinkUrl(linkUrl);
        }

        externalLinkImpl.resetOriginalValues();

        return externalLinkImpl;
    }
}
