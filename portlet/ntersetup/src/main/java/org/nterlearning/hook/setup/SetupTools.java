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
}
