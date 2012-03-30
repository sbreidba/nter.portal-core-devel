package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.Component;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Component in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Component
 * @generated
 */
public class ComponentCacheModel implements CacheModel<Component>, Serializable {
    public long componentId;
    public long companyId;
    public long groupId;
    public long feedReferenceId;
    public String componentIri;
    public long updatedDate;
    public String language;
    public String href;
    public String fullTextHref;
    public String title;
    public String description;
    public String copyright;
    public int displayWidth;
    public int displayHeight;
    public long createDate;
    public boolean removed;
    public long removedDate;
    public String version;
    public long versionDate;
    public double price;
    public String priceUnit;
    public String priceTerms;
    public String priceExpiration;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(47);

        sb.append("{componentId=");
        sb.append(componentId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", feedReferenceId=");
        sb.append(feedReferenceId);
        sb.append(", componentIri=");
        sb.append(componentIri);
        sb.append(", updatedDate=");
        sb.append(updatedDate);
        sb.append(", language=");
        sb.append(language);
        sb.append(", href=");
        sb.append(href);
        sb.append(", fullTextHref=");
        sb.append(fullTextHref);
        sb.append(", title=");
        sb.append(title);
        sb.append(", description=");
        sb.append(description);
        sb.append(", copyright=");
        sb.append(copyright);
        sb.append(", displayWidth=");
        sb.append(displayWidth);
        sb.append(", displayHeight=");
        sb.append(displayHeight);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", removed=");
        sb.append(removed);
        sb.append(", removedDate=");
        sb.append(removedDate);
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
        sb.append("}");

        return sb.toString();
    }

    public Component toEntityModel() {
        ComponentImpl componentImpl = new ComponentImpl();

        componentImpl.setComponentId(componentId);
        componentImpl.setCompanyId(companyId);
        componentImpl.setGroupId(groupId);
        componentImpl.setFeedReferenceId(feedReferenceId);

        if (componentIri == null) {
            componentImpl.setComponentIri(StringPool.BLANK);
        } else {
            componentImpl.setComponentIri(componentIri);
        }

        if (updatedDate == Long.MIN_VALUE) {
            componentImpl.setUpdatedDate(null);
        } else {
            componentImpl.setUpdatedDate(new Date(updatedDate));
        }

        if (language == null) {
            componentImpl.setLanguage(StringPool.BLANK);
        } else {
            componentImpl.setLanguage(language);
        }

        if (href == null) {
            componentImpl.setHref(StringPool.BLANK);
        } else {
            componentImpl.setHref(href);
        }

        if (fullTextHref == null) {
            componentImpl.setFullTextHref(StringPool.BLANK);
        } else {
            componentImpl.setFullTextHref(fullTextHref);
        }

        if (title == null) {
            componentImpl.setTitle(StringPool.BLANK);
        } else {
            componentImpl.setTitle(title);
        }

        if (description == null) {
            componentImpl.setDescription(StringPool.BLANK);
        } else {
            componentImpl.setDescription(description);
        }

        if (copyright == null) {
            componentImpl.setCopyright(StringPool.BLANK);
        } else {
            componentImpl.setCopyright(copyright);
        }

        componentImpl.setDisplayWidth(displayWidth);
        componentImpl.setDisplayHeight(displayHeight);

        if (createDate == Long.MIN_VALUE) {
            componentImpl.setCreateDate(null);
        } else {
            componentImpl.setCreateDate(new Date(createDate));
        }

        componentImpl.setRemoved(removed);

        if (removedDate == Long.MIN_VALUE) {
            componentImpl.setRemovedDate(null);
        } else {
            componentImpl.setRemovedDate(new Date(removedDate));
        }

        if (version == null) {
            componentImpl.setVersion(StringPool.BLANK);
        } else {
            componentImpl.setVersion(version);
        }

        if (versionDate == Long.MIN_VALUE) {
            componentImpl.setVersionDate(null);
        } else {
            componentImpl.setVersionDate(new Date(versionDate));
        }

        componentImpl.setPrice(price);

        if (priceUnit == null) {
            componentImpl.setPriceUnit(StringPool.BLANK);
        } else {
            componentImpl.setPriceUnit(priceUnit);
        }

        if (priceTerms == null) {
            componentImpl.setPriceTerms(StringPool.BLANK);
        } else {
            componentImpl.setPriceTerms(priceTerms);
        }

        if (priceExpiration == null) {
            componentImpl.setPriceExpiration(StringPool.BLANK);
        } else {
            componentImpl.setPriceExpiration(priceExpiration);
        }

        componentImpl.resetOriginalValues();

        return componentImpl;
    }
}
