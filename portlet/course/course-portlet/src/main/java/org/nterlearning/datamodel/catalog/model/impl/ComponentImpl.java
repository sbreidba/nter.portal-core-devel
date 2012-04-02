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

import org.nterlearning.commerce.client.CommerceServiceStub;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.utils.DateUtil;
import org.nterlearning.utils.PortalProperties;

import org.nterlearning.utils.PortalPropertiesUtil;
import org.nterlearning.xml.commerce.domain_objects_0_1_0.PaymentStatus;
import org.nterlearning.xml.commerce.transaction_interface_0_1_0.GetPaymentStatus;
import org.nterlearning.xml.commerce.transaction_interface_0_1_0.GetPaymentStatusResponse;
import org.nterlearning.xml.commerce.transaction_interface_0_1_0_wsdl.TransactionInterface;
import org.nterlearning.xml.commerce.transaction_interface_0_1_0_wsdl.ValidationError;

import javax.servlet.jsp.PageContext;
import java.math.BigDecimal;
import java.math.RoundingMode;

//public class ComponentImpl extends ComponentBaseImpl {
public class ComponentImpl extends ComponentBaseImpl {

	private String searchContext;
	private float searchRelevance;


	private static Log mLog = LogFactoryUtil.getLog(ComponentImpl.class);

	public ComponentImpl() {
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
			TransactionInterface transactionInterface;
			String transactionWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_TRANSACTION_URL);
			String configurationWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_CONFIGURATION_URL);
			String entitlementWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_ENTITLEMENT_URL);

			CommerceServiceStub commerceService = new CommerceServiceStub(transactionWsdlURL, configurationWsdlURL,
					entitlementWsdlURL);
			transactionInterface = commerceService.getTransactionInterface();

			GetPaymentStatus paymentStatus = new GetPaymentStatus();
            String studentId =
                    UserIdMapperLocalServiceUtil.getUserIdMapper(
                            userId, PortalPropertiesUtil.getSsoImplementation()).getExternalUserId();
			paymentStatus.setStudentId(studentId);
			paymentStatus.setCourseId(getComponentIri());
			paymentStatus.setResourceId("NTER");
			BigDecimal price = new BigDecimal(getPrice());
			paymentStatus.setPrice(price.setScale(2, RoundingMode.UP));

            try {
				GetPaymentStatusResponse paymentResponse =
                        transactionInterface.getPaymentStatus(paymentStatus);
				return paymentResponse.getStatus() == PaymentStatus.COMPLETED;
			}
            catch (ValidationError e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
        }
        else {
            return true;
        }
    }


    public void updateIndex() {
        try {
            Indexer indexer = IndexerRegistryUtil.getIndexer(Component.class);
            indexer.reindex(this);
        }
        catch (SearchException e) {
            mLog.error("Could not update index for component: " + this.getComponentId());
        }
        catch (Exception e) {
            mLog.error("Could not process indexer.");
        }
    }
}