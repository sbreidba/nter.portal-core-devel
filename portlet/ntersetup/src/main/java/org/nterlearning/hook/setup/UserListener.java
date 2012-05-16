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


package org.nterlearning.hook.setup;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;


/**
 * This class is designed to be a listener for the User objects.
 */
public class UserListener implements ModelListener<User> {

    protected long mCompanyId = 0;
    protected long mGuestGroupId = 0;

    static Log mLog = LogFactoryUtil.getLog(UserListener.class);


    /**
     * Ensures that each user is associated with a default group and
     * organization.
     *
     * @param user The newly created user
     * @throws ModelListenerException
     */
    public void onAfterCreate(User user) throws ModelListenerException {
        initHook();

        try {
            GroupLocalServiceUtil.addUserGroups(user.getUserId(),
                    new long[] {mGuestGroupId});
        }
        catch (Exception e) {
            mLog.error(e.getMessage());    
        }
    }


    public void onBeforeCreate(User user) throws ModelListenerException {}

    public void onAfterRemove(User user) throws ModelListenerException {}
    public void onBeforeRemove(User user) throws ModelListenerException {}

    public void onAfterUpdate(User user) throws ModelListenerException {}
    public void onBeforeUpdate(User user) throws ModelListenerException {}
    
    public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {}
    public void onBeforeAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {}

    public void onBeforeRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {}
    public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {}


    /*
     * Initializes the hook by setting up common properties needed by all
     * routines.
     */
    protected void initHook() {

        if (mCompanyId != 0) {
            return;
        }
        
        try {
            mCompanyId = CompanyThreadLocal.getCompanyId();
            mGuestGroupId = GroupLocalServiceUtil.getGroup(mCompanyId, "Guest").getGroupId();
        }
        catch (Exception e) {
            mGuestGroupId = 0;
        }
    }
}
