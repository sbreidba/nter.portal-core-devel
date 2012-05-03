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

package org.nterlearning.course.search;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil;

import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseImage;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;

import javax.servlet.jsp.PageContext;


public class OpenSearchResult {

    // type == portlet_id
	public String type;
	public String title;
	public String summary;
	public double rating;
	public String tags;
	public long[] categoryIds;
	public String href;
	public String keywords;
	public long groupId;
	public String ownerName;
	public String ownerURL;
	public String imgURL;
	public String imgAltTxt;

	public String className;
	public long classPK;
    public String classIri;

	public OpenSearchResult(
		String type, String title, String summary, String href, double rating,
		String tags, long[] categoryIds, long groupId, String keywords,
		String className) {

		super();
		this.type = type;
		this.title = title;
		this.summary = summary;
		this.rating = rating;
		this.tags = tags;
		this.categoryIds = categoryIds;
		this.href = href;
		this.keywords = keywords;
		this.groupId = groupId;
		this.className = className;
        this.classIri = null;
	}

	public void setStats(String className, long classPK, PageContext pageContext)
		throws SystemException, PortalException {

		this.className = className;
		this.classPK = classPK;
		rating =
			RatingsStatsLocalServiceUtil.getStats(className, classPK).getAverageScore();
		categoryIds =
			AssetCategoryLocalServiceUtil.getCategoryIds(className, classPK);

		if (className.equals(CourseImpl.class.getName())) {
			Course course = CourseLocalServiceUtil.getCourse(classPK);
			ThemeDisplay themeDisplay = (ThemeDisplay) pageContext.getRequest().getAttribute(WebKeys.THEME_DISPLAY);
			ownerName = course.getOwnerName(themeDisplay.getCompanyId());
			if (course.getCourseImages().size() > 0) {
				CourseImage img = course.getCourseImages().get(0);
				if (Validator.isNotNull(img)) {
					imgURL = img.getSourceImageUrl();
				}
			}
		}
	}


    public void setClassInformation(String className, String classIri, Long classPK) {
        this.className = className;
        this.classIri = classIri;
        this.classPK = classPK;
    }

	public void setImage(String url, String altText) {
		imgURL = url;
		imgAltTxt = altText;
	}

	public void setOwner(String ownerName, String ownerUrl) {
		this.ownerName = ownerName;
		this.ownerURL = ownerUrl;
	}
}