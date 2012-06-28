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

package org.nterlearning.migration.model;

/**
 * Message Board Category Map information for migration
 * For example the old values are v6.0.6 the new values are v6.1 schema
 */
public class MbCategoryMap {

    private long oldCategoryId;
    private long newCategoryId;
    private long oldParentCategoryId;
    private long newParentCategoryId;

    public long getOldCategoryId() {
        return oldCategoryId;
    }
    public void setOldCategoryId(long newValue) {
        oldCategoryId = newValue;
    }

    public long getNewCategoryId() {
        return newCategoryId;
    }
    public void setNewCategoryId(long newValue) {
        newCategoryId = newValue;
    }

    public long getOldParentCategoryId() {
        return oldParentCategoryId;
    }
    public void setOldParentCategoryId(long newValue) {
        oldParentCategoryId = newValue;
    }

    public long getNewParentCategoryId() {
        return newParentCategoryId;
    }
    public void setNewParentCategoryId(long newValue) {
        newParentCategoryId = newValue;
    }

}
