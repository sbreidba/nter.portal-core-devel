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
 * Message Board Message Map information for migration
 * For example the old values are v6.0.6 the new values are v6.1 schema
 */
public class MbMessageMap {

    private long oldMessageId;
    private long newMessageId;
    private long oldThreadId;
    private long newThreadId;
    private long oldParentMessageId;
    private long newParentMessageId;

    public long getOldMessageId() {
        return oldMessageId;
    }

    public void setOldMessageId(long newValue) {
        oldMessageId = newValue;
    }

    public long getNewMessageId() {
        return newMessageId;
    }

    public void setNewMessageId(long newValue) {
        newMessageId = newValue;
    }

    public long getOldThreadId() {
        return oldThreadId;
    }

    public void setOldThreadId(long newValue) {
        oldThreadId = newValue;
    }

    public long getNewThreadId() {
        return newThreadId;
    }

    public void setNewThreadId(long newValue) {
        newThreadId = newValue;
    }

    public long getOldParentMessageId() {
        return oldParentMessageId;
    }

    public void setOldParentMessageId(long newValue) {
        oldParentMessageId = newValue;
    }

    public long getNewParentMessageId() {
        return newParentMessageId;
    }

    public void setNewParentMessageId(long newValue) {
        newParentMessageId = newValue;
    }

}
