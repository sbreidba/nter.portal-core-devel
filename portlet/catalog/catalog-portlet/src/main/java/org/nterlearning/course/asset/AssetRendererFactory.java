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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil;

public class AssetRendererFactory extends BaseAssetRendererFactory {

    public static final String CLASS_NAME = FlagReport.class.getName();
    public static final String TYPE = "flagReport";

    public AssetRenderer getAssetRenderer(long classPK, int type)
        throws PortalException, SystemException {

        FlagReport flagReport = FlagReportLocalServiceUtil.getFlagReport(classPK);

        return new FlagReportAssetRenderer(flagReport);
    }

    public String getClassName() {
        return CLASS_NAME;
    }

    public String getType() {
        return TYPE;
    }

}
