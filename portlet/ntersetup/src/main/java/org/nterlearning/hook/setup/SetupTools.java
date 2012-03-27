/**
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
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

//import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class provides a common set of tools for the various Setup hooks.
 */
public class SetupTools {

    static Log mLog = LogFactoryUtil.getLog(SetupTools.class);

    /**
     * Generates a concatenated vocabulary name based on the organization's name
     * and the names of any parent organizations.  The model is
     * [parentOrg.name] [parentOrg.name] [org.name].
     *
     * @param org The organization to generate a name for.
     *
     * @return The new vocabulary name.
     */
    protected static String generateVocabularyName(Organization org) {
        String name = org.getName();

        long orgParentId = org.getParentOrganizationId();
        Organization parentOrg;

        while (orgParentId != 0) {
            try {
                parentOrg = OrganizationLocalServiceUtil.getOrganization(orgParentId);
                name = parentOrg.getName() + " " + name;
                orgParentId = parentOrg.getParentOrganizationId();
            }
            catch (Exception e) {
                return name;
            }
        }

        return name;
    }
    

    /**
     * Finds a category based on the parent vocabulary and the category's name.
     *
     * @param name The name to search for
     * @param vocab The parent vocabulary
     *
     * @return The corresponding AssetCategory, or null if one is not found.
     */
    static AssetCategory findCategoryByNameAndVocabulary(String name, AssetVocabulary vocab) {

        if (vocab == null) {
            return null;
        }

        try {
            List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories();

            for (AssetCategory category : categories) {
                if (category.getName().equals(name) &&
                    (category.getVocabularyId() == vocab.getVocabularyId())) {
                    return category;
                }
            }

            return null;
        }
        catch (Exception e) {
            return null;
        }
    }


    /**
     * Finds a category based on the parent vocabulary and the category's name.
     *
     * @param name The name to search for
     * @param vocabularyId The parent vocabulary's ID
     *
     * @return The corresponding AssetCategory, or null if one is not found.
     */
    static AssetCategory findCategoryByNameAndVocabulary(String name, long vocabularyId) {
        AssetVocabulary vocab;
        try {
            vocab = AssetVocabularyLocalServiceUtil.getAssetVocabulary(vocabularyId);
        }
        catch (Exception e) {
            vocab = null;
        }

        return findCategoryByNameAndVocabulary(name, vocab);
    }


    /**
     * Imports a given LAR (Liferay Archive File) and applies it to a particular
     * group.
     *
     * @param userId The userId to manage the import (can be default user)
     * @param groupId The groupId to apply the LAR to.
     * @param privateLayout True to apply this to the group's private pages,
     * false otherwise.
     * @param larFileName The LAR filename.  The file should be stored in the
     * default 'deploy' directory and the name should not include this path.
     */
    public static void addDefaultLayoutsByLar(long userId, long groupId,
                              boolean privateLayout, String larFileName) {

        File larDir;
        String larDirPath = ".";
        try {
            larDirPath = PrefsPropsUtil.getString(PropsKeys.AUTO_DEPLOY_TOMCAT_DEST_DIR) +
                                    "/nter-setup-portlet/webapp/lar/";
            larDir = new File(larDirPath);
        }
        catch (Exception e) {
            mLog.error("Could not find deploy directory at: " + larDirPath + " Using current directory instead");
            larDir = new File(".");
        }

        File larFile = new File(larDir, larFileName);
        if (!larFile.exists()) {
            mLog.error("Could not find the LAR file: " + larDirPath + larFile.getName());
            return;
        }

        Map<String, String[]> parameterMap = new HashMap<String, String[]>();

        parameterMap.put(PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
                         new String[] {Boolean.FALSE.toString()});
        
        parameterMap.put(PortletDataHandlerKeys.PORTLET_SETUP,
                         new String[] {Boolean.TRUE.toString()});
        parameterMap.put(PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS,
                         new String[] {Boolean.FALSE.toString()});
        parameterMap.put(PortletDataHandlerKeys.PORTLET_USER_PREFERENCES,
                         new String[] {Boolean.FALSE.toString()});
        parameterMap.put(PortletDataHandlerKeys.DELETE_PORTLET_DATA,
                         new String[] {Boolean.FALSE.toString()});
        parameterMap.put(PortletDataHandlerKeys.PORTLET_DATA,
                         new String[] {Boolean.TRUE.toString()});
        parameterMap.put(PortletDataHandlerKeys.DATA_STRATEGY,
                         new String[] {PortletDataHandlerKeys.DATA_STRATEGY_COPY_AS_NEW});

        parameterMap.put(PortletDataHandlerKeys.PERMISSIONS,
                         new String[] {Boolean.FALSE.toString()});
        parameterMap.put(PortletDataHandlerKeys.USER_PERMISSIONS,
                         new String[] {Boolean.FALSE.toString()});

        parameterMap.put(PortletDataHandlerKeys.THEME,
                         new String[] {Boolean.FALSE.toString()});
        parameterMap.put(PortletDataHandlerKeys.CATEGORIES,
                         new String[] {Boolean.FALSE.toString()});

        parameterMap.put(PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE,
                         new String[] {PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE_MERGE_BY_LAYOUT_UUID});
        parameterMap.put(PortletDataHandlerKeys.PORTLETS_MERGE_MODE,
                         new String[] {PortletDataHandlerKeys.PORTLETS_MERGE_MODE_REPLACE});

        try {
            System.out.print("Import Layouts for userId: " + userId + "groupId: " + groupId);
            LayoutLocalServiceUtil.importLayouts(userId, groupId, privateLayout,
                    parameterMap, larFile);  
        }
//        catch (DuplicateFileException de) {
//            mLog.info("Data already exists for groupId" + groupId + "\n" + de.getMessage());
//        }
        catch (Exception e) {
            mLog.error("Error processing groupId: " + groupId + "\n" + e.getMessage());
        }
    }
}
