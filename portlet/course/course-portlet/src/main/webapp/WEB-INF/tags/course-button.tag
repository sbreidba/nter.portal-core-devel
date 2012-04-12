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

<%-- course-button.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%@ attribute name="buttonCssClass" type="java.lang.String" required="false" %>
<%@ attribute name="isPurchased" type="java.lang.Boolean" required="true" %>
<%@ attribute name="course" type="org.nterlearning.datamodel.catalog.model.Course" required="true" %>
<%@ attribute name="courseRecord" type="org.nterlearning.datamodel.catalog.model.CourseRecord" required="false" %>
<%@ attribute name="finishedComponentCount" type="java.lang.Integer" required="true" %>
<%@ attribute name="activeComponentCount" type="java.lang.Integer" required="true" %>
<%@ attribute name="resumeComponent" type="org.nterlearning.datamodel.catalog.model.Component" required="false" %>
<%@ attribute name="failedComponent" type="org.nterlearning.datamodel.catalog.model.Component" required="false" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>

<%@ tag import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ tag import="com.liferay.portal.util.PortalUtil" %>
<%@ tag import="org.nterlearning.commerce.client.CommerceServiceStub" %>
<%@ tag import="org.nterlearning.course.enumerations.CompletionStatusType" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.FeedReference" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil" %>
<%@ tag import="org.nterlearning.utils.PortalProperties" %>
<%@ tag import="org.nterlearning.xml.commerce.configuration_interface_0_1_0.GetPaymentConfig" %>
<%@ tag import="org.nterlearning.xml.commerce.configuration_interface_0_1_0.GetPaymentConfigResponse" %>
<%@ tag import="org.nterlearning.xml.commerce.configuration_interface_0_1_0_wsdl.ConfigurationInterface" %>
<%@ tag import="org.nterlearning.xml.commerce.domain_objects_0_1_0.PaymentProcessor" %>
<%@ tag import="com.liferay.portal.service.UserIdMapperLocalServiceUtil" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<% // Course removed then do not display a button to start, pay, continue etc.
if (!course.isRemoved()) {
        if (themeDisplay.isSignedIn()) {
            // When student signed in check to see if course requires payment and check payment status

            if (course.getPrice() > 0 && !isPurchased) {
				// get content provider id from feed reference
				long feedReferenceId = course.getFeedReferenceId();
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

                String transactionWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_TRANSACTION_URL);
			    String configurationWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_CONFIGURATION_URL);
			    String entitlementWsdlURL = PropsUtil.get(PortalProperties.ECOMMERCE_ENTITLEMENT_URL);
                
				CommerceServiceStub commerceService = new CommerceServiceStub(transactionWsdlURL, configurationWsdlURL,
						entitlementWsdlURL);
				ConfigurationInterface configurationInterface = commerceService.getConfigurationInterface();
				GetPaymentConfig getPaymentConfig = new GetPaymentConfig();
				getPaymentConfig.setConfigId(PaymentProcessor.PAY_PAL);
				GetPaymentConfigResponse paymentResponse = configurationInterface.getPaymentConfig(getPaymentConfig);
				%>
                <form action="<%=paymentResponse.getConfigurationEntry().getActionURL()%>" method="post">
                    <input type="hidden" name="cmd" value="_xclick">
                    <input type="hidden" name="business" value="<%=paymentResponse.getConfigurationEntry().getSellerId()%>">
                    <input type="hidden" name="lc" value="US">
                    <input type="hidden" name="item_name" value="<%= course.getTitle(locale) %>">
                    <input type="hidden" name="item_number" value="<%= course.getCourseIri() %>">
                    <input type="hidden" name="amount" value="<%= course.getPrice() %>">
                    <input type="hidden" name="currency_code" value="<%= course.getPriceUnit() %>">
                    <input type="hidden" name="button_subtype" value="services">
                    <input type="hidden" name="no_note" value="1">
                    <input type="hidden" name="no_shipping" value="1">
                    <input type="hidden" name="rm" value="1">
                    <input type="hidden" name="return" value="<%= PortalUtil.getPortalURL(request) %><%= course.getUrl() %>">
                    <input type="hidden" name="cancel_return" value="<%= PortalUtil.getPortalURL(request) %><%= course.getUrl() %>">
                    <input type="hidden" name="bn" value="PP-BuyNowBF:btn_buynowCC_LG.gif:NonHosted">
                    <input type="hidden" name="address_override" value="1">
                    <input type="hidden" name="notify_url" value="<%=paymentResponse.getConfigurationEntry().getNotifyURL()%>">
                    <input type="hidden" name="email" value="<%= user.getEmailAddress() %>">
                    <input type="hidden" name="custom" value="studentId=<%= studentId %>|nterId=NTER|cpId=<%= cpId %>">
                    <button type="submit" class="purchase"><%= LanguageUtil.get(pageContext,
							"course-actions-purchase") %></button>
					<%--<input type="image" src="<%=paymentResponse.getConfigurationEntry().getButtonURL()%>" border="0" name="submit"--%>
						   <%--alt="PayPal - The safer, easier way to pay online!">--%>
                    <%--<img alt="" border="0" src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">--%>
                </form>

    <%
            } else {
                // else course is free or has been purchased.

                // assign a href
                 String resumeComponentHref = "";
                 int resumeDisplayWidth = 0;
                 int resumeDisplayHeight = 0;
                 if (resumeComponent != null) {
                    resumeComponentHref = resumeComponent.getHref();
                    resumeDisplayWidth = resumeComponent.getDisplayWidth();
                    resumeDisplayHeight = resumeComponent.getDisplayHeight();
                 }

                 if (courseRecord == null) {
                   // No student progress, display generic button
    %>
                    <a href="<%= resumeComponentHref %>" class="join-course button" target="_blank">
						<%= LanguageUtil.get(pageContext, "enter-course") %>
                    </a>
    <%
                } else {
                    // If progress exists display button based on student record progress
                    String courseCompletionStatus = courseRecord.getCompletionStatus();
                    String buttonText = "";

                    // Completed or Failed Course status the button will not be present.
                    if (courseCompletionStatus.equals(CompletionStatusType.IN_PROGRESS.getDbValue())) {
                        if (activeComponentCount > 0 || finishedComponentCount > 0) {
                            //student has started course
                            buttonText = LanguageUtil.get(pageContext, "course-actions-continue");
                        } else {
                            //student assigned or enrolled but not started
                            buttonText = LanguageUtil.get(pageContext, "course-actions-start");
                        }
                    } else if (courseCompletionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
                        if (failedComponent != null) {
                            resumeComponentHref = failedComponent.getHref();
                        }
                        buttonText = LanguageUtil.get(pageContext, "course-actions-retry");
                    }

                    if (!buttonText.equals("") && !resumeComponentHref.equals("")) {
    %>
                        <a href="<%= resumeComponentHref %>"
                            class="<%=buttonCssClass %>" target="_blank"><%= buttonText %>
                        </a>
    <%              }

                }  //courseRecord check
            }  //price check
     } else { // Student must login %>

        <a href="<%= themeDisplay.getURLSignIn() %>"
            class="<%=buttonCssClass %>"><%= LanguageUtil.get(pageContext, "login-enter-course") %>
        </a>

    <% } //login check

}  // Course Removed - do not display button  %>




