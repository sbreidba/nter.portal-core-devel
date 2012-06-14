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

<% // flag-global-review.jsp %>
<%@include file="init.jsp" %>

<%
	// if the review does not exist?
	// if the user is not logged in, show an error and a link to log in (with return back to the course? this page is useless without post)
	// otherwise
	// if details was submitted, validate it
	// if valid, save and show a link back to the course
	// otherwise, show the form with error messages and a cancel button back to the course
	// otherwise, show form that asks for details (like js popup dialog) and a cancel button back to the course

	HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
	Object oldMeta = httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
	List meta = new ArrayList();
	if (oldMeta != null) meta = (List) httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
	long reviewId = ParamUtil.getLong(request, "classPK");
	try {
        GlobalCourseReview globalCourseReview = GlobalCourseReviewLocalServiceUtil.getGlobalCourseReview(reviewId);
		if (globalCourseReview != null) {
            Course course = CourseLocalServiceUtil.getCourse(globalCourseReview.getCourseId());

		    PortalUtil.addPageSubtitle(LanguageUtil.get(pageContext, "flag-review"),
				PortalUtil.getHttpServletRequest(renderRequest));
		    PortalUtil.addPageSubtitle(" - global review for " + course.getTitle(locale), PortalUtil.getHttpServletRequest(renderRequest));
		    meta.add("<link rel=\"canonical\" href=\"" + course.getUrl() + "\" />");
        }
	} catch (NumberFormatException e) {
	} catch (PortalException e) {
	} catch (SystemException e) {
	}


	// we should be using renderResponse.createElement (http://blogs.oracle.com/deepakg/entry/setting_markup_head_elements_in)
	// but it doesn't seem to work in all circumstances, e.g. maximized portlets
	meta.add("<meta name=\"robots\" content=\"noindex\" />");
	httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);
%>

Flag page goes here. See comments for details. Very similar to flag js popup dialog