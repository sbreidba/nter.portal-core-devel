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
 * The extended model implementation for the FlagReport service. Represents a row in the &quot;CATALOG_FlagReport&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.model.FlagReport} interface.
 * </p>
 *
 */

import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.nterlearning.datamodel.catalog.model.FlagReport;

public class FlagReportImpl extends FlagReportModelImpl implements FlagReport {
//public class FlagReportImpl extends FlagReportBaseImpl {
	public FlagReportImpl() {
	}

    public boolean isModerated() {
        return ((this.getStatus() == WorkflowConstants.STATUS_APPROVED) || (this.getStatus() == WorkflowConstants.STATUS_DENIED)  );
    }

    public boolean isAssigned() {
        return (this.getStatusByUserId() != 0 );
    }

}
