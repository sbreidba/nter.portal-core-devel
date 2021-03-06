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

package org.nterlearning.datamodel.catalog.model.impl;

/**
 * The extended model implementation for the Component service. Represents a row in the &quot;CATALOG_Component&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.model.Component} interface.
 * </p>
 *
 */
import com.liferay.portal.NoSuchUserIdMapperException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserIdMapperLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import org.nterlearning.commerce.client.TransactionClient;
import org.nterlearning.commerce.client.TransactionClientImpl;
import org.nterlearning.commerce.transaction.client.*;
import org.nterlearning.course.enumerations.ContributorRoleType;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Contributor;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.ContributorLocalServiceUtil;
import org.nterlearning.utils.DateUtil;
import org.nterlearning.utils.PortalProperties;
import org.nterlearning.utils.PortalPropertiesUtil;

import javax.servlet.jsp.PageContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ComponentImpl extends ComponentBaseImpl implements Component {

	private String searchContext;
	private float searchRelevance;

	private static Log mLog = LogFactoryUtil.getLog(ComponentImpl.class);

	public ComponentImpl() {
	}


    public List<Contributor> getContributors()
            throws Exception {
        return ComponentLocalServiceUtil.getContributors(this);
    }


    public Contributor getComponentAuthor()
            throws SystemException {
        List<Contributor> authors =
                ContributorLocalServiceUtil.findByComponentIdWithRole(this.getComponentId(),
                                                                      ContributorRoleType.AUTHOR.value());
        return (authors.size() > 0) ? authors.get(0) : null;
    }


    public String getFriendlyVersionDate(PageContext pageContext) {
          return  DateUtil.getFriendlyDate(pageContext,this.getVersionDate());
    }

    public String getFriendlyUpdateDate(PageContext pageContext) {
        return DateUtil.getFriendlyDate(pageContext, this.getUpdatedDate());
    }

	public String getSearchContext() {
		return searchContext;
	}

	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}

	public float getSearchRelevance() {
		return searchRelevance;
	}

	public void setSearchRelevance(float searchRelevance) {
		this.searchRelevance = searchRelevance;
	}

    public String getUrl() throws PortalException, SystemException {
        String groupFriendlyUrl =
                GroupLocalServiceUtil.getGroup(getGroupId()).getFriendlyURL();

        return PortalUtil.getPathFriendlyURLPublic() + groupFriendlyUrl
                + "/component-details?ccid=" + getComponentId();
    }

    public String getUrl(String lang) throws PortalException, SystemException {
        return getUrl() + "&lang=" + lang;
    }

    public boolean isPurchased(long userId)
            throws SystemException, PortalException {

        if (getPrice() > 0) {
            String transactionWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_TRANSACTION_URL);
            String transactionEmail= PropsUtil.get(PortalProperties.ECOMMERCE_EMAIL);
            String transactionPassword = PropsUtil.get(PortalProperties.ECOMMERCE_PASSWORD);
            TransactionClient client = new TransactionClientImpl(transactionEmail, transactionPassword, transactionWsdlURL);

            String institution = "NTER";
            BigDecimal price = new BigDecimal(getPrice());
            price = (price.setScale(2, RoundingMode.UP));
            String iri = getComponentIri();

            try {
                String studentId =
                        UserIdMapperLocalServiceUtil.getUserIdMapper(userId,
                        PortalPropertiesUtil.getSsoImplementation()).getExternalUserId();
                PaymentStatus paymentStatus = client.getPaymentStatus(institution, studentId, iri, price);
                return  paymentStatus.equals(PaymentStatus.COMPLETED);
            }
            catch (NoSuchUserIdMapperException nsu) {
                mLog.error("Could not find externalUserId (SSO) for: " + userId);
                return false;
            }
            catch (Exception e) {
                mLog.error("Could not determine payment status for userId: " + userId +
                        " for component: " + iri);
                return false;
            }
        }
        else {
            return true;
        }
    }


    public void updateIndex() {
        try {
            Indexer indexer = IndexerRegistryUtil.getIndexer(Component.class);

            if (this.isRemoved()) {
                indexer.delete(this);
            }
            else {
                indexer.reindex(this);
            }
        }
        catch (SearchException e) {
            mLog.error("Could not update index for component: " + this.getComponentId());
        }
        catch (Exception e) {
            mLog.error("Could not process indexer.");
        }
    }
}