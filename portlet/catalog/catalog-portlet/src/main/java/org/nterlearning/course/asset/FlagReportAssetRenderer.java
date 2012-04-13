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

package org.nterlearning.course.asset;

import com.liferay.portlet.asset.model.BaseAssetRenderer;
import org.nterlearning.course.util.WebKeys;
import org.nterlearning.datamodel.catalog.model.FlagReport;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.Locale;

/**
 * flagReport information in AssetEntry entity
 */
public class FlagReportAssetRenderer extends BaseAssetRenderer {

	public FlagReportAssetRenderer(FlagReport flagReport) {
		_flagReport = flagReport;
	}

	public long getClassPK() {
		return _flagReport.getFlagReportId();
	}

	public long getGroupId() {
		return _flagReport.getGroupId();
	}

	public String getSummary(Locale locale) {
		return _flagReport.getContent();
	}

	public String getTitle(Locale locale) {
		return _flagReport.getTitle();
	}

    public long getUserId() {
		return _flagReport.getUserId();
	}

	public String getUuid() {
		return _flagReport.getUuid();
	}

	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String template)
		throws Exception {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute(WebKeys.FLAG_REPORT, _flagReport);

			return "/flagreport/jsp/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	private FlagReport _flagReport;

}