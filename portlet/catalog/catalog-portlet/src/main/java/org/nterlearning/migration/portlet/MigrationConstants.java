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

package org.nterlearning.migration.portlet;

import java.io.File;

/**
 * Used to migrate information between Liferay versions.
 */
public class MigrationConstants {
    public static final String USERS_MIGRATION_FILE_NAME = "users_migration_extract.txt";
    public static final String USERS_MIGRATION_PATH = System.getenv("CATALINA_BASE") + File.separatorChar + "webapps" +
            File.separatorChar + "nter-catalog-portlet" + File.separatorChar + "course-feeds" + File.separatorChar + USERS_MIGRATION_FILE_NAME;

    public static final String USER_GROUPS_MIGRATION_FILE_NAME = "user_groups_migration_extract.txt";
    public static final String USER_GROUPS_MIGRATION_PATH = System.getenv("CATALINA_BASE") + File.separatorChar + "webapps" +
            File.separatorChar + "nter-catalog-portlet" + File.separatorChar + "course-feeds" + File.separatorChar + USER_GROUPS_MIGRATION_FILE_NAME;

    public static final String USER_ORGS_MIGRATION_FILE_NAME = "user_orgs_migration_extract.txt";
    public static final String USER_ORGS_MIGRATION_PATH = System.getenv("CATALINA_BASE") + File.separatorChar + "webapps" +
            File.separatorChar + "nter-catalog-portlet" + File.separatorChar + "course-feeds" + File.separatorChar + USER_ORGS_MIGRATION_FILE_NAME;

    public static final String USER_ROLES_MIGRATION_FILE_NAME = "user_roles_migration_extract.txt";
    public static final String USER_ROLES_MIGRATION_PATH = System.getenv("CATALINA_BASE") + File.separatorChar + "webapps" +
            File.separatorChar + "nter-catalog-portlet" + File.separatorChar + "course-feeds" + File.separatorChar + USER_ROLES_MIGRATION_FILE_NAME;

    public static final String REVIEWS_MIGRATION_FILE_NAME = "reviews_migration.xml";
    public static final String REVIEWS_MIGRATION_PATH = System.getenv("CATALINA_BASE") + File.separatorChar + "webapps" +
            File.separatorChar + "nter-catalog-portlet" + File.separatorChar + "course-feeds" + File.separatorChar + REVIEWS_MIGRATION_FILE_NAME;

    public static final String USER_REVIEWS_MIGRATION_FILE_NAME = "user_reviews_migration_extract.txt";
    public static final String USER_REVIEWS_MIGRATION_PATH = System.getenv("CATALINA_BASE") + File.separatorChar + "webapps" +
            File.separatorChar + "nter-catalog-portlet" + File.separatorChar + "course-feeds" + File.separatorChar + USER_REVIEWS_MIGRATION_FILE_NAME ;


    public static final String USER_REVIEW_HELP_MIGRATION_FILE_NAME = "review_help_migration_extract.txt";
    public static final String USER_REVIEW_HELP_MIGRATION_PATH = System.getenv("CATALINA_BASE") + File.separatorChar + "webapps" +
            File.separatorChar + "nter-catalog-portlet" + File.separatorChar + "course-feeds" + File.separatorChar + USER_REVIEW_HELP_MIGRATION_FILE_NAME ;

}
