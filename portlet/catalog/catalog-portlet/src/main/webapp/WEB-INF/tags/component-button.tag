<%--
  National Training and Education Resource (NTER)
  Copyright (C) 2012 SRI International

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or (at
  your option) any later version.

  This program is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
  02110-1301, USA.
  --%>

<%-- component-button.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ tag import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ tag import="com.liferay.portal.service.UserIdMapperLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.util.PortalUtil" %>

<%@ tag import="org.nterlearning.course.enumerations.*" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.ExternalLink" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.FeedReference" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil" %>
<%@ tag import="org.nterlearning.utils.PortalProperties" %>

<%@ tag import="java.util.List" %>
<%@ tag import="org.nterlearning.commerce.client.ConfigurationClient" %>
<%@ tag import="org.nterlearning.commerce.client.ConfigurationClientImpl" %>
<%@ tag import="org.nterlearning.commerce.configuration.client.PaymentProcessor" %>
<%@ tag import="org.nterlearning.commerce.configuration.client.PaymentConfig" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<%@ attribute name="buttonCssClass" type="java.lang.String" required="false" %>
<%@ attribute name="isPurchased" type="java.lang.Boolean" required="true" %>
<%@ attribute name="component" type="org.nterlearning.datamodel.catalog.model.Component" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>

<% // Component removed then do not display a button to start, pay, continue etc.
if (!component.isRemoved()) {
    if (themeDisplay.isSignedIn()) {

        if (component.getPrice() > 0 && !isPurchased) {
            // get content provider id from feed reference
            long feedReferenceId = component.getFeedReferenceId();
            FeedReference feedReference = FeedReferenceLocalServiceUtil.getFeedReference(feedReferenceId);
            String cpId = feedReference.getContentProviderId();
            String studentId = null;
            try {
                studentId = UserIdMapperLocalServiceUtil.getUserIdMappers(
                                user.getUserId()).get(0).getExternalUserId();
            }
            catch (IndexOutOfBoundsException e) {
                return;
            }

            try {
                String configWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_CONFIGURATION_URL);
                String configEmail= PropsUtil.get(PortalProperties.ECOMMERCE_EMAIL);
                String configPassword = PropsUtil.get(PortalProperties.ECOMMERCE_PASSWORD);
                ConfigurationClient client = new ConfigurationClientImpl(configEmail, configPassword, configWsdlURL);

                PaymentConfig paymentConfig =
                    client.getPaymentConfig(PaymentProcessor.PAY_PAL);
                if (paymentConfig != null) {
            %>

            <form action="<%=paymentConfig.getActionURL()%>" method="post">
                <input type="hidden" name="cmd" value="_xclick">
                <input type="hidden" name="business" value="<%=paymentConfig.getSellerId()%>">
                <input type="hidden" name="lc" value="US">
                <input type="hidden" name="item_name" value="<%= component.getTitle() %>">
                <input type="hidden" name="item_number" value="<%= component.getComponentIri() %>">
                <input type="hidden" name="amount" value="<%= component.getPrice() %>">
                <input type="hidden" name="currency_code" value="<%= component.getPriceUnit() %>">
                <input type="hidden" name="button_subtype" value="services">
                <input type="hidden" name="no_note" value="1">
                <input type="hidden" name="no_shipping" value="1">
                <input type="hidden" name="rm" value="1">
                <input type="hidden" name="return" value="<%= PortalUtil.getPortalURL(request) %><%= request.getRequestURL() %>">
                <input type="hidden" name="cancel_return" value="<%= PortalUtil.getPortalURL(request) %><%= request.getRequestURL() %>">
                <input type="hidden" name="bn" value="PP-BuyNowBF:btn_buynowCC_LG.gif:NonHosted">
                <input type="hidden" name="address_override" value="1">
                <input type="hidden" name="notify_url" value="<%=paymentConfig.getNotifyURL()%>">
                <input type="hidden" name="email" value="<%= user.getEmailAddress() %>">
                <input type="hidden" name="custom" value="studentId=<%= studentId %>|nterId=NTER|cpId=<%= cpId %>">
                <button type="submit" class="purchase">
                    <%= LanguageUtil.get(pageContext, "component-details-purchase") %>
                </button>
            </form>

<%              }
            } catch (Exception e) {
               // commerce service not available
            }
        }
        else {
            // else course is free or has been purchased.

            List<ExternalLink> dlLinks = ComponentLocalServiceUtil.getExternalLinks(component);
            %>

            <div class="highlightbox">
                <h4 id="download-links"><%= LanguageUtil.get(pageContext, "component-details-download-header")%></h4>
                <ul class="toc">
                <%
                    for (ExternalLink dlLink : dlLinks) { %>
                        <li>
                            <a href='<%= dlLink.getLinkUrl() %>' class="join-course-button">
                                <%= ExternalLinkType.labelOfMimeType(dlLink.getLinkType()) %>
                            </a>
                        </li>
                    <% }
                %>
                </ul>
            </div>  <%
        }
    }  // signed in?
    else { // Student must login
        if (component.getPrice() > 0) { %>
            <a href='<%= themeDisplay.getURLSignIn() %>' class='<%= buttonCssClass %>'>
                <%= LanguageUtil.get(pageContext, "component-details-sign-in-purchase")%>
            </a>
        <%}
        else { %>
            <a href='<%= themeDisplay.getURLSignIn() %>' class='<%= buttonCssClass %>'>
                <%= LanguageUtil.get(pageContext, "component-details-sign-in-download")%>
            </a>
    <%  }
    }
}  // Component Removed - do not display button  %>