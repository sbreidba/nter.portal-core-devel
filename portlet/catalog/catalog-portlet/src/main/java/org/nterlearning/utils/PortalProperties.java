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

package org.nterlearning.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * This class defines a list of custom properties found in the
 * portal-ext.properties file
 */
public class PortalProperties {

    // E-Commerce properties
    public static final String ECOMMERCE_TRANSACTION_URL = "org.nterlearning.ecommerce.transaction.url";
    public static final String ECOMMERCE_CONFIGURATION_URL = "org.nterlearning.ecommerce.configuration.url";
    public static final String ECOMMERCE_ENTITLEMENT_URL = "org.nterlearning.ecommerce.entitlement.url";

    // Service Registry properties
    public static final String SERVICE_REGISTRY_GLOBAL_URL = "org.nterlearning.registry.global.url";

    // PubSubHubbub properties
    public static final String PUSH_CALLBACK_URL = "org.nterlearning.atom.parser.push.callbackUrl";

    // Nutch properties
    public static final String NUTCH_ROLE_PROPERTY = "nter.nutch.role";
    public static final String NUTCH_HOME_DIR_PROPERTY = "nter.nutch.home.dir";
    public static final String NUTCH_DATA_DIR_PROPERTY = "nter.nutch.data.dir";
    public static final String NUTCH_INDEXER_TYPE_PROPERTY = "nter.nutch.indexer.type";
    public static final String NUTCH_MASTER_ROLE = "master";

    // Solr properties
    public static final String SOLR_INDEXER_NAME = "solr";
    public static final String SOLR_URL_PROPERTY = "nter.nutch.solr.url";
    public static final String SOLR_USER_PROPERTY = "nter.nutch.solr.user";
    public static final String SOLR_PASSWORD_PROPERTY = "nter.nutch.solr.password";
    
    // Reporting properties
    public static final String PDF_REPORT_DIR = "org.nterlearning.exporter.reports.dir.pdf";
    public static final String JRXML_REPORT_DIR = "org.nterlearning.exporter.reports.dir.jrxml";

    // SSO properties
    public static final String SSO_PROPERTY = "nter.sso.type";
    public static final String SSO_SHIB_PROPERTY_VALUE = "Shibboleth";
    public static final String SSO_CAS_PROPERTY_VALUE = "CAS";

    public static final Set<String> SUPPORTED_SSO_IMPLEMENTATIONS = new HashSet<String>();
    static {
        SUPPORTED_SSO_IMPLEMENTATIONS.add(SSO_SHIB_PROPERTY_VALUE);
        SUPPORTED_SSO_IMPLEMENTATIONS.add(SSO_CAS_PROPERTY_VALUE);
    }

    // Review Properties
    public static final String TRUSTED_REVIEWER_PROPERTY = "org.nterlearning.trustedreviewer";

    // Test configuration settings
    public static final String NTER_CREATE_TEST_USERS = "nter.test.user.create";
}
