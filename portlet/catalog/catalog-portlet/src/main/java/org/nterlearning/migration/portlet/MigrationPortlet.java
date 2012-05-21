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

import com.liferay.portal.DuplicateUserScreenNameException;
import com.liferay.portal.NoSuchUserIdMapperException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.*;
import com.liferay.portal.service.*;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;
import com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil;
import com.liferay.util.PwdGenerator;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.migration.model.*;
import org.nterlearning.utils.PortalProperties;
import org.nterlearning.utils.PortalPropertiesUtil;
import org.nterlearning.utils.ReviewUtil;

import javax.management.MBeanTrustPermission;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.*;
import javax.portlet.PortletPreferences;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Migration of information from different versions of Liferay.
 */
public class MigrationPortlet extends MVCPortlet {

    /**
     * Migrates a collection  users.
     * <p/>
     * Note: These users will only need to be migrated once.
     * To ensure this, the property
     * <code>nter.migrate.users.create</code> must be set in the
     * <code>portal-ext.properties</code> file.
     */
    public void processMigrateUserImport(ActionRequest request, ActionResponse response)
            throws FileNotFoundException, IOException, PortalException, SystemException {

        String migrateUsers = PropsUtil.get(PortalProperties.NTER_MIGRATE_PERMITTED);
        Boolean createUser = ((migrateUsers != null) && migrateUsers.equals("true"));
        if (createUser) {

            // Extract list of users to migrate
            String usersFilename = MigrationConstants.USERS_MIGRATION_PATH;
            ArrayList<UserExtract> userList = readUserExtractAsArrayList(usersFilename);

            // Extract list of user's groups
            String userGroupsFilename = MigrationConstants.USER_GROUPS_MIGRATION_PATH;
            ArrayList<UserGroupsExtract> userGroupList = readUserGroupsExtractAsArrayList(userGroupsFilename);

            // Extract list of user's organizations
            String userOrgsFilename = MigrationConstants.USER_ORGS_MIGRATION_PATH;
            ArrayList<UserOrgsExtract> userOrgList = readUserOrgsExtractAsArrayList(userOrgsFilename);

            // Extract list of user's roles
            String userRolesFilename = MigrationConstants.USER_ROLES_MIGRATION_PATH;
            ArrayList<UserRolesExtract> userRoleList = readUserRolesExtractAsArrayList(userRolesFilename);

            long companyId = PortalUtil.getDefaultCompanyId();

            for (UserExtract userItem : userList) {
                // extract values from list
                String ssoValue = userItem.getSsoValue();
                String mapperType = userItem.getMapperType();
                String screenName = userItem.getScreenName();
                String firstName = userItem.getFirstName();
                String middleName = userItem.getMiddleName();
                String lastName = userItem.getLastName();
                String emailAddress = userItem.getEmailAddress();
                String jobTitle = userItem.getJobTitle();
                // generate random password, users will be using Shib for login/password
                String password = PwdGenerator.getPassword();
                Boolean male = true;

                long groupIds[] = findUserGroups(userGroupList, ssoValue, companyId);
                long organizationIds[] = findUserOrganizations(userOrgList, ssoValue, companyId);
                long roleIds[] = findUserRoles(userRoleList, ssoValue, companyId);

                try {
                    User user = addUser(companyId, screenName, firstName,
                            middleName, lastName, password, emailAddress,
                            male, jobTitle,
                            roleIds, groupIds, organizationIds);

                    addSsoInformation(user, ssoValue);
                } catch (Exception e) {
                    mLog.info(ExceptionUtils.getFullStackTrace(e));
                }
            }
        } else {
            mLog.warn("Migration Feature not available. Portal-ext.properties migration is false.");
        }
    }

    private User addUser(long companyId, String screenName, String firstName,
                         String middleName, String lastName, String password, String emailAddress,
                         boolean male, String jobTitle,
                         long[] roleIds, long[] groupIds, long[] organizationIds) throws Exception {

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setAddGroupPermissions(true);
        serviceContext.setAddGuestPermissions(true);
        serviceContext.setCompanyId(companyId);
        long groupId = GroupLocalServiceUtil.getCompanyGroup(companyId).getGroupId();
        serviceContext.setScopeGroupId(groupId);

        User user;
        try {
            user = UserLocalServiceUtil.addUser(0, companyId, false, password,
                    password, false, screenName, emailAddress, 0,
                    StringPool.BLANK, Locale.US, firstName, middleName,
                    lastName, 0, 0, male, Calendar.JANUARY, 1, 1970, jobTitle,
                    groupIds, organizationIds, roleIds, null, false,
                    serviceContext);
            mLog.info("Created user: " + firstName + " " + lastName +
                    " with email:" + emailAddress +
                    " group count: " + groupIds.length +
                    " org count: " + organizationIds.length +
                    " role count: " + roleIds.length);

        } catch (DuplicateUserScreenNameException e) {
            mLog.info("User " + screenName + " already exists.");
            user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName);
        }

        return user;
    }

    private static void addSsoInformation(User user, String ssoValue) {
        try {
            UserIdMapper userMapper;
            try {
                userMapper =
                        UserIdMapperLocalServiceUtil.getUserIdMapper(
                                user.getUserId(), PortalPropertiesUtil.getSsoImplementation());
            } catch (NoSuchUserIdMapperException ne) {
                userMapper =
                        UserIdMapperLocalServiceUtil.updateUserIdMapper(
                                user.getUserId(), PortalPropertiesUtil.getSsoImplementation(),
                                null, ssoValue);
            }
        } catch (Exception e) {
            mLog.info(ExceptionUtils.getFullStackTrace(e));
        }
    }

    /**
     * Find User's groups
     */
    private long[] findUserGroups(ArrayList<UserGroupsExtract> userGroupList, String ssoValue, long companyId)
            throws PortalException, SystemException {
        Group userGroup;
        Group guestGroup = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST);
        long[] groupIds = new long[]{guestGroup.getGroupId()};
        ArrayList<Long> matchingGroups = new ArrayList<Long>();

        // Loop through group list for matching user values
        for (UserGroupsExtract userGroupItem : userGroupList) {
            if (userGroupItem.getSsoValue().equals(ssoValue)) {
                //found match, retrieve pk from liferay
                try {
                    userGroup = GroupLocalServiceUtil.getFriendlyURLGroup(companyId, userGroupItem.getFriendlyUrl());
                    matchingGroups.add(userGroup.getOrganizationId());
                } catch (Exception e) {
                    mLog.warn("User " + ssoValue + " has Group " + userGroupItem.getFriendlyUrl() +
                            " not found in new Liferay Schema");
                }
            }
        }
        if (!matchingGroups.isEmpty()) {
            groupIds = new long[matchingGroups.size()];
            for (int i = 0; i < matchingGroups.size(); i++) {
                groupIds[i] = matchingGroups.get(i);
            }
        }
        return groupIds;
    }

    /**
     * Find User's organizations
     */
    private long[] findUserOrganizations(ArrayList<UserOrgsExtract> userOrgList, String ssoValue, long companyId)
            throws PortalException, SystemException {
        Organization userOrg;
        long organizationIds[] = new long[]{OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID};
        ArrayList<Long> matchingOrgs = new ArrayList<Long>();
        // Loop through organization list for matching user values

        for (UserOrgsExtract userOrgItem : userOrgList) {
            if (userOrgItem.getSsoValue().equals(ssoValue)) {
                //found match, retrieve pk from liferay
                try {
                    userOrg = OrganizationLocalServiceUtil.getOrganization(companyId, userOrgItem.getOrgName());
                    matchingOrgs.add(userOrg.getOrganizationId());
                } catch (Exception e) {
                    mLog.warn("User " + ssoValue + " has Organization " + userOrgItem.getOrgName() +
                            " not found in new Liferay Schema");
                }
            }
        }
        if (!matchingOrgs.isEmpty()) {
            organizationIds = new long[matchingOrgs.size()];
            for (int i = 0; i < matchingOrgs.size(); i++) {
                organizationIds[i] = matchingOrgs.get(i);
            }
        }
        return organizationIds;
    }

    /**
     * Find User's Roles
     */
    private long[] findUserRoles(ArrayList<UserRolesExtract> userRoleList, String ssoValue, long companyId)
            throws PortalException, SystemException {
        Role userRole;
        Role defaultRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        long roleIds[] = new long[]{defaultRole.getRoleId()};
        ArrayList<Long> matchingRoles = new ArrayList<Long>();

        // Loop through Role list for matching user values
        for (UserRolesExtract userRoleItem : userRoleList) {
            if (userRoleItem.getSsoValue().equals(ssoValue)) {
                //found match, retrieve pk from liferay
                try {
                    userRole = RoleLocalServiceUtil.getRole(companyId, userRoleItem.getRoleName());
                    matchingRoles.add(userRole.getRoleId());
                } catch (Exception e) {
                    mLog.warn("User " + ssoValue + " has Role " + userRoleItem.getRoleName() +
                            " not found in new Liferay Schema");
                }
            }
        }
        if (!matchingRoles.isEmpty()) {
            roleIds = new long[matchingRoles.size()];
            for (int i = 0; i < matchingRoles.size(); i++) {
                roleIds[i] = matchingRoles.get(i);
            }
        }
        return roleIds;
    }

//  team decided to use sql extract, delimited files for all migration including local course reviews.
//    /**
//     * Migration process using a local course review feed
//     *
//     * @param request  HTTP Request handler
//     * @param response HTTP response handler
//     */
//    public void processMigrateReviewFeedImport(ActionRequest request, ActionResponse response)
//            throws FileNotFoundException, IOException, PortalException, SystemException {
//
//        mLog.info("Importing local course reviews");
//
//        try {
//            // get the local reviews migration feed
//            String feedString = readFileAsString(MigrationConstants.REVIEWS_MIGRATION_PATH);
//
//            // parse it into an Abdera Feed
//            Parser parser = AbderaSingleton.getInstance().getParser();
//            Document<Feed> doc = parser.parse(new StringReader(feedString));
//            Feed feed = doc.getRoot();
//
//            // create the Feed Context
//
//            FeedContext fc = new FeedContext(MigrationConstants.REVIEWS_MIGRATION_PATH);
//
//            // process it like a normal feed
//            AtomFeedProcessor.processFeed(fc, feed);
//
//        } catch (FileNotFoundException e) {
//            mLog.warn("Migration Review File not found.");
//        } catch (IOException e) {
//            mLog.warn("Migration Review File IO Exception");
//        } catch (SystemException e) {
//            throw new SystemException(e);
//        } catch (PortalException e) {
//            throw new PortalException(e);
//        }
//    }

    /**
     * Migration process using a file for user reviews
     *
     * @param request  HTTP Request handler
     * @param response HTTP response handler
     */
    public void processMigrateUserReviewImport(ActionRequest request, ActionResponse response)
            throws FileNotFoundException, IOException, ParseException, PortalException, SystemException {

        String migrateReviews = PropsUtil.get(PortalProperties.NTER_MIGRATE_PERMITTED);
        Boolean createReviews = ((migrateReviews != null) && migrateReviews.equals("true"));
        if (createReviews) {

            mLog.info("Importing user local course reviews");

            // Extract local course reviews  to migrate
            String userReviewsFilename = MigrationConstants.USER_REVIEWS_MIGRATION_PATH;
            ArrayList<UserReviewExtract> userReviewList = readUserReviewExtractAsArrayList(userReviewsFilename);

            for (UserReviewExtract userItem : userReviewList) {
                // extract values from list
                String ssoValue = userItem.getSsoValue();
                String mapperType = userItem.getMapperType();
                String emailAddress = userItem.getEmailAddress();
                String courseIri = userItem.getCourseIri();
                long score = userItem.getScore();
                String summary = userItem.getSummary();
                String content = userItem.getContent();
                Date createDate = userItem.getCreateDate();
                Date modifiedDate = userItem.getModifiedDate();
                boolean removed = userItem.getRemoved();
                Date removedDate = userItem.getRemovedDate();

                // Find user based on the UserIdMapper assignment
                long userId = findUserFromExternalId(mapperType, ssoValue);

                // Find course based on courseIri assignment
                long courseId = findCourseFromIri(courseIri);

                // When user, course exist, migrate the extracted review
                if (userId != 0 && courseId != 0) {
                    try {
                        // Verify this review has not previously been migrated - only 1 review per user for each course.
                        List<CourseReview> courseReviewList = CourseReviewLocalServiceUtil.findByCourseIdWithUserId(userId, courseId);
                        if (courseReviewList.size() == 0) {
                            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                                    CourseReviewLocalServiceUtil.class.getName(), request);

                            CourseReviewLocalServiceUtil.migrateCourseReview(userId, courseId, summary, content, score,
                                    createDate, modifiedDate, removed, removedDate, serviceContext);
                            mLog.info("User Review added for CourseIri: " + courseIri + " UserId: " + ssoValue);
                        } else {
                            mLog.warn("Could not add review for user " + ssoValue + " review for course " + courseIri +
                                    " because review already exists.");
                        }
                    } catch (Exception e) {
                        mLog.error("Could not add review for user " + ssoValue + " review for course " + courseIri +
                                " because of exception: " + e);
                    }
                }
            }
        } else {
            mLog.warn("Migration Feature not available. Portal-ext.properties migration is false.");
        }
    }

    /**
     * Find user based on the UserIdMapper assignment
     */
    private long findUserFromExternalId(String mapperType, String ssoValue)
            throws SystemException {

        UserIdMapper userMapper;
        long userId = 0;
        try {
            userMapper =
                    UserIdMapperLocalServiceUtil.getUserIdMapperByExternalUserId(mapperType, ssoValue);
            userId = userMapper.getUserId();
        } catch (Exception e) {
            mLog.warn("Could not find local user which maps to external user id " + ssoValue);
        }
        return userId;
    }

    /**
     * Find course id based on the Iri
     */
    private long findCourseFromIri(String courseIri)
            throws SystemException {

        long courseId = 0;
        // Find course based on courseIri assignment
        Course course;
        try {
            course = CourseLocalServiceUtil.fetchByCourseIri(courseIri);
            courseId = course.getCourseId();
        } catch (Exception e) {
            // probably course does not exist, but log just in case
            mLog.warn("Could not find course which maps to courseIRI: " + courseIri);
        }
        return courseId;
    }


    /**
     * Migration process using a file for user helpful/not helpful review score
     *
     * @param request  HTTP Request handler
     * @param response HTTP response handler
     */
    public void processMigrateReviewHelpImport(ActionRequest request, ActionResponse response)
            throws FileNotFoundException, IOException, PortalException, SystemException {

        String migrateReviews = PropsUtil.get(PortalProperties.NTER_MIGRATE_PERMITTED);
        Boolean createReviews = ((migrateReviews != null) && migrateReviews.equals("true"));
        if (createReviews) {

            mLog.info("Importing user helpful/not helpful scores for course reviews");

            // Extract user review scores to migrate
            String userReviewHelpFilename = MigrationConstants.USER_REVIEW_HELP_MIGRATION_PATH;
            ArrayList<UserReviewHelpExtract> userReviewHelpList = readUserReviewHelpExtractAsArrayList(userReviewHelpFilename);

            for (UserReviewHelpExtract userItem : userReviewHelpList) {
                // extract values from list
                String voteSsoValue = userItem.getVoteSsoValue();
                String voteMapperType = userItem.getVoteMapperType();
                String reviewSsoValue = userItem.getReviewSsoValue();
                String reviewMapperType = userItem.getReviewMapperType();
                String courseIri = userItem.getCourseIri();
                long score = userItem.getScore();

                // Find vote user based on the UserIdMapper assignment
                long voteUserId = findUserFromExternalId(voteMapperType, voteSsoValue);
                // Find review user based on the UserIdMapper assignment
                long reviewUserId = findUserFromExternalId(reviewMapperType, reviewSsoValue);

                // Find course based on courseIri assignment
                long courseId = findCourseFromIri(courseIri);

                // When all users, course, and review exist, migrate the extracted review helpful/not helpful
                if (voteUserId != 0 && reviewUserId != 0 && courseId != 0) {
                    List<CourseReview> courseReviewList;
                    RatingsEntry ratingsEntry;
                    try {
                        courseReviewList = CourseReviewLocalServiceUtil.findByCourseIdWithUserId(reviewUserId, courseId);
                        if (courseReviewList.size() == 0) {
                            mLog.warn("Review not found for CourseIri: " + courseIri + " UserId: " + reviewSsoValue);
                        }
                        for (CourseReview courseReview : courseReviewList) {
                            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                                    RatingsEntryLocalServiceUtil.class.getName(), request);

                            RatingsEntryLocalServiceUtil.updateEntry(voteUserId, CourseReview.class.getName(), courseReview.getPrimaryKey(), score, serviceContext);
                            mLog.info("User Helpful Score added for CourseIri: " + courseIri + " Vote UserId: " + voteSsoValue);
                        }
                    } catch (Exception e) {

                        mLog.warn("Could not find load vote for user " + voteSsoValue +
                                " review for course " + courseIri + " reviewed by " + reviewSsoValue);
                    }
                }
            }
        } else {
            mLog.warn("Migration Feature not available. Portal-ext.properties migration is false.");
        }

    }

    /**
     * Migration process force update of review weighted score based on  helpful/not helpful reviews in RatingsStats
     *
     * @param request  HTTP Request handler
     * @param response HTTP response handler
     */
    public void processUpdateReviewWeightedScore(ActionRequest request, ActionResponse response)
            throws PortalException, SystemException {
        List<CourseReview> courseReviewList = CourseReviewLocalServiceUtil.getCourseReviews(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        for (CourseReview courseReview : courseReviewList) {
            try {
                // Calculate and assign weighted value
                RatingsStats stats = RatingsStatsLocalServiceUtil.getStats(CourseReview.class.getName(), courseReview.getCourseReviewId());
                // This should equal the number of positive ratings as long as all ratings are +/-1.
                int positive = ((int) stats.getTotalScore() + stats.getTotalEntries()) / 2;
                double weightedScore = ReviewUtil.wilsonScore(positive, stats.getTotalEntries(), 0.05);
                CourseReviewLocalServiceUtil.updateCourseReviewRating(courseReview.getCourseReviewId(), weightedScore);
            } catch (Exception e) {
                mLog.warn("Problem calculating weighted score for Course Review with PK: " + courseReview.getPrimaryKey() +
                        " based on courseId: " + courseReview.getCourseId() + " and userId " + courseReview.getUserId());
            }
        }
    }

    /**
     * Migration process using a file for MB Categories
     *
     * @param request  HTTP Request handler
     * @param response HTTP response handler
     */
    public void processMigrateMBCategoryImport(ActionRequest request, ActionResponse response)
            throws FileNotFoundException, IOException, ParseException, PortalException, SystemException {

        String migrateMB = PropsUtil.get(PortalProperties.NTER_MIGRATE_PERMITTED);
        Boolean createMB = ((migrateMB != null) && migrateMB.equals("true"));
        if (createMB) {
            mLog.info("Importing MB Categories");

            // Extract local course reviews  to migrate
            String mbCategoryFilename = MigrationConstants.MB_CATEGORY_MIGRATION_PATH;
            ArrayList<MbCategoryExtract> categoryList = readMbCategoryExtractAsArrayList(mbCategoryFilename);
            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    MBCategoryLocalServiceUtil.class.getName(), request);

            mLog.info("Adding parent categories");
            boolean parentFlag = true;
            // Load parent categories and establish a map of old/new categories to verify
            ArrayList<MbCategoryMap> categoryMapList = new ArrayList<MbCategoryMap>();
            categoryMapList = insertMbCategorySet(parentFlag, categoryList, categoryMapList, serviceContext);

            // add subcategories, skip if all ready processed
            mLog.info("Adding child categories");
            parentFlag = false;
            ArrayList<MbCategoryMap> subCategoryMapList = new ArrayList<MbCategoryMap>();
            subCategoryMapList = insertMbCategorySet(parentFlag, categoryList, categoryMapList, serviceContext);
        } else {
            mLog.warn("Migration Feature not available. Portal-ext.properties migration is false.");
        }
    }


    /**
     * Insert MB Categories
     */
    private ArrayList<MbCategoryMap> insertMbCategorySet(boolean parentFlag, ArrayList<MbCategoryExtract> categoryList,
                                                        ArrayList<MbCategoryMap> categoryMapList, ServiceContext serviceContext)
            throws PortalException, SystemException {

        for (MbCategoryExtract categoryItem : categoryList) {
            // extract values from list
            String ssoValue = categoryItem.getSsoValue();
            String mapperType = categoryItem.getMapperType();
            String emailAddress = categoryItem.getEmailAddress();
            String userName = categoryItem.getUserName();
            long categoryId = categoryItem.getCategoryId();
            long parentCategoryId = categoryItem.getParentCategoryId();
            String name = categoryItem.getName();
            String description = categoryItem.getDescription();
            String displayStyle = categoryItem.getDisplayStyle();
            Date createDate = categoryItem.getCreateDate();
            Date modifiedDate = categoryItem.getModifiedDate();
            Date lastPostDate = categoryItem.getLastPostDate();
            String inEmailAddress = categoryItem.getInEmailAddress();
            String inProtocol = categoryItem.getInProtocol();
            String inServerName = categoryItem.getInServerName();
            int inServerPort = categoryItem.getInServerPort();
            boolean inUseSSL = categoryItem.getInUseSSL();
            String inUserName = categoryItem.getInUserName();
            String inPassword = categoryItem.getInPassword();
            int inReadInterval = categoryItem.getInReadInterval();
            String outEmailAddress = categoryItem.getOutEmailAddress();
            boolean outCustom = categoryItem.getOutCustom();
            String outServerName = categoryItem.getOutServerName();
            int outServerPort = categoryItem.getOutServerPort();
            boolean outUseSSL = categoryItem.getOutUseSSL();
            String outUserName = categoryItem.getOutUserName();
            String outPassword = categoryItem.getOutPassword();
            boolean allowAnonymous = false; // not in v6.0.6 schema
            boolean mailingListActive = categoryItem.getMailingListActive();

            long companyId = PortalUtil.getDefaultCompanyId();

            // Verify this category has not previously been migrated.
            boolean categoryFound = migratedMBCategory(name, description);

            if (categoryFound) {
                if (parentFlag && parentCategoryId == 0) {
                    mLog.warn("Message Board Category '" + name + "' already exists and was not re-created.");
                } else if (!parentFlag && parentCategoryId !=0) {
                    mLog.warn("Message Board Category '" + name + "' already exists and was not re-created.");
                }

            } else {
                // Find user based on the UserIdMapper assignment
                long userId = findUserFromExternalId(mapperType, ssoValue);

                // Use admin user if valid user not found
                if (userId == 0) {
                    mLog.info("Inserting MBCategory: '" + name + "' using Admin user");
                    //userId = UserLocalServiceUtil.getUserIdByEmailAddress(companyId, "admin@nterlearning.org");
                    userId = UserLocalServiceUtil.getUserIdByScreenName(companyId, "admin");
                }

                MBCategory mbCategory;
                try {
                    if (parentFlag && parentCategoryId == 0) {

                        // Create MB parent (root) categories
                        mbCategory = MBCategoryLocalServiceUtil.addCategory(userId, parentCategoryId, name,
                                description, displayStyle,
                                inEmailAddress, inProtocol, inServerName, inServerPort, inUseSSL, inUserName, inPassword, inReadInterval,
                                outEmailAddress, outCustom, outServerName, outServerPort, outUseSSL, outUserName, outPassword,
                                allowAnonymous, mailingListActive, serviceContext);
                        mLog.info("Added Category: '" + name + "'. Inserted by userId: " + ssoValue);

                        //assign group and guest permissions  (true)
                        ResourceLocalServiceUtil.addResources(
                                mbCategory.getCompanyId(), mbCategory.getGroupId(), mbCategory.getUserId(),
                                MBCategory.class.getName(), mbCategory.getCategoryId(),
                                false, true, true);

                        //create old/new map of ids
                        boolean addFlag = true;
                        maintainCategoryMap(addFlag, categoryMapList, categoryId, mbCategory.getCategoryId(),
                               parentCategoryId, mbCategory.getParentCategoryId());

                    } else if (parentFlag) {
                        // Save sub-categories for recursive processing
                        boolean addFlag = true;
                        maintainCategoryMap(addFlag, categoryMapList, categoryId, -1,
                               parentCategoryId, -1);

                    } else {

                        if (parentCategoryId != 0) {
                            // insert subcategory
                            // find new categoryId of parent
                            // Categories are sorted in extract query so "parents" will always be processed first.
                            // Maintain the map so hierarchy created properly
                             for (MbCategoryMap newMapItem : categoryMapList) {
                                if (parentCategoryId == newMapItem.getOldCategoryId()) {
                                    long newParentCategoryId = newMapItem.getNewCategoryId();

                                    mbCategory = MBCategoryLocalServiceUtil.addCategory(userId, newParentCategoryId, name,
                                            description, displayStyle,
                                            inEmailAddress, inProtocol, inServerName, inServerPort, inUseSSL, inUserName, inPassword, inReadInterval,
                                            outEmailAddress, outCustom, outServerName, outServerPort, outUseSSL, outUserName, outPassword,
                                            allowAnonymous, mailingListActive, serviceContext);
                                    mLog.info("Added MBCategory: '" + name + "'. Inserted by userId: " + ssoValue);

                                    //assign group and guest permissions  (true)
                                    ResourceLocalServiceUtil.addResources(
                                            mbCategory.getCompanyId(), mbCategory.getGroupId(), mbCategory.getUserId(),
                                            MBCategory.class.getName(), mbCategory.getCategoryId(),
                                            false, true, true);

                                    //create old/new map of ids
                                    boolean addFlag = false;
                                    maintainCategoryMap(addFlag, categoryMapList, categoryId, mbCategory.getCategoryId(),
                                            parentCategoryId, mbCategory.getParentCategoryId());

                                    break;
                                }          //matching category found
                            }              //for loop
                        }                 //non-parent
                    }
                } catch (Exception e) {
                    // log issue creating the MBCategory
                    mLog.warn("Could not add MBCategory: '" + name + "' Inserted by userId: " + ssoValue);
                }
            }
        }
        return categoryMapList;
    }

    /**
     * Determine MB Category already exists
     */
    private boolean migratedMBCategory(String name, String description)
            throws SystemException {

        boolean categoryFound = false;

        List<MBCategory> existingCategories = MBCategoryLocalServiceUtil.getMBCategories(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        for (MBCategory existingCategory : existingCategories) {
            if (name.equals(existingCategory.getName()) && description.equals(existingCategory.getDescription())) {
                categoryFound = true;
                break;
            }
        }
        return categoryFound;
    }

    /**
     * Maintain map of category old and new id attributes
     */
    private ArrayList<MbCategoryMap> maintainCategoryMap(boolean addFlag, ArrayList<MbCategoryMap> categoryMapList,
           long oldCategoryId, long newCategoryId, long oldParentCategoryId, long newParentCategoryId)
           throws PortalException, SystemException {

        MbCategoryMap mapItem = new MbCategoryMap();
        mapItem.setOldCategoryId(oldCategoryId);
        mapItem.setNewCategoryId(newCategoryId);
        mapItem.setOldParentCategoryId(oldParentCategoryId);
        mapItem.setNewParentCategoryId(newParentCategoryId);

        if (addFlag) {
            categoryMapList.add(mapItem);
        } else {
            //update the map of the current record with new Id values
            int mapIndex = 0;
            for (MbCategoryMap fixMapItem : categoryMapList) {
                if (mapItem.getOldCategoryId() == fixMapItem.getOldCategoryId()) {
                    categoryMapList.set(mapIndex, mapItem);
                    break;
                }
                mapIndex++;
            }
        }
        return categoryMapList;
    }

    /**
     * Migration process using a file for MB Messages
     *
     * @param request  HTTP Request handler
     * @param response HTTP response handler
     */
    public void processMigrateMBMessageImport(ActionRequest request, ActionResponse response)
            throws FileNotFoundException, IOException, ParseException, PortalException, SystemException {

        String migrateMB = PropsUtil.get(PortalProperties.NTER_MIGRATE_PERMITTED);
        Boolean createMB = ((migrateMB != null) && migrateMB.equals("true"));
        if (createMB) {
            mLog.info("Importing MB Messages");

            // Extract local course reviews  to migrate
            String mbMessageFilename = MigrationConstants.MB_MESSAGE_MIGRATION_PATH;
            ArrayList<MbMessageExtract> messageList = readMbMessageExtractAsArrayList(mbMessageFilename);
            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    MBMessageLocalServiceUtil.class.getName(), request);

            PortletPreferences preferences = request.getPreferences();
            String format = GetterUtil.getString(
                    preferences.getValue("messageFormat", null),
                    MBMessageConstants.DEFAULT_FORMAT);

            mLog.info("Adding root messages");
            boolean rootFlag = true;
            // Load root messages and establish a map of old/new messages to insert entire message thread
            ArrayList<MbMessageMap> messageMapList = new ArrayList<MbMessageMap>();
            messageMapList = insertMbMessageSet(rootFlag, messageList, messageMapList, format, serviceContext);

            // add message thread responses to root message, skip if all ready processed
            mLog.info("Adding message threads");
            rootFlag = false;
            ArrayList<MbMessageMap> subMessageMapList = new ArrayList<MbMessageMap>();
            subMessageMapList = insertMbMessageSet(rootFlag, messageList, messageMapList, format, serviceContext);
        } else {
            mLog.warn("Migration Feature not available. Portal-ext.properties migration is false.");
        }

    }

    /**
     * Insert MB Messages
     */
    private ArrayList<MbMessageMap> insertMbMessageSet(boolean rootFlag, ArrayList<MbMessageExtract> messageList,
                                                      ArrayList<MbMessageMap> messageMapList, String format, ServiceContext serviceContext)
            throws PortalException, SystemException {

        for (MbMessageExtract messageItem : messageList) {
            // extract values from list
            String ssoValue = messageItem.getSsoValue();
            String mapperType = messageItem.getMapperType();
            String emailAddress = messageItem.getEmailAddress();
            String userName = messageItem.getUserName();
            String name = messageItem.getName();
            String description = messageItem.getDescription();
            Date createDate = messageItem.getCreateDate();
            Date modifiedDate = messageItem.getModifiedDate();
            long messageId = messageItem.getMessageId();
            long threadId = messageItem.getThreadId();
            long rootMessageId = messageItem.getRootMessageId();
            long parentMessageId = messageItem.getParentMessageId();
            String subject = messageItem.getSubject();
            String body = messageItem.getBody();
            boolean attachments = messageItem.getAttachments();
            boolean anonymous = messageItem.getAnonymous();
            double priority = messageItem.getPriority();
            boolean allowPingbacks = messageItem.getAllowPingbacks();

            // list is used for attachments which are are not going to migrate at this time
            List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
                    new ArrayList<ObjectValuePair<String, InputStream>>(5);

            long companyId = PortalUtil.getDefaultCompanyId();
            long groupId = GroupLocalServiceUtil.getGroup(companyId,GroupConstants.GUEST).getGroupId();

            // Verify this message has not previously been migrated.
            boolean messageFound = migratedMBMessage(subject, body);

            if (messageFound) {
                if (rootFlag && parentMessageId == 0) {
                    mLog.warn("Message Board message '" + subject + "' already exists and was not re-created.");
                } else if (!rootFlag && parentMessageId !=0) {
                    mLog.warn("Message Board message '" + subject + "' already exists and was not re-created.");
                }

            } else {

                // Obtain category.
                long category = determineMBCategory(name, description);

                // Messages are sorted in extract query.
                // Root message will be first followed by message responses.
                MBMessage mbMessage;
                try {
                    if (rootFlag && parentMessageId == 0) {
                        // Find user based on the UserIdMapper assignment
                        long userId = findUserFromExternalId(mapperType, ssoValue);

                        // Insert as guest user if user not valid in new schema
                        if (userId == 0) {
                            userId = UserLocalServiceUtil.getDefaultUser(companyId).getUserId();
                            mLog.warn("Inserting message as guest user");
                        }

                        // Create MB root messages
                        mbMessage = MBMessageLocalServiceUtil.addMessage(userId, userName, groupId,
                                category, subject, body, format, inputStreamOVPs, anonymous,
                                priority, allowPingbacks, serviceContext);
                        mLog.info("Added Message: " + subject + " UserName: " + userName);

                        //assign group and guest permissions  (true)
                        ResourceLocalServiceUtil.addResources(
                                mbMessage.getCompanyId(), mbMessage.getGroupId(), mbMessage.getUserId(),
                                MBMessage.class.getName(), mbMessage.getMessageId(),
                                false, true, true);

                        //create old/new map of ids
                        boolean addFlag = true;
                        maintainMessageMap(addFlag, messageMapList,
                                messageId, mbMessage.getMessageId(), threadId, mbMessage.getThreadId(),
                                parentMessageId, mbMessage.getParentMessageId());


                    } else if (rootFlag) {
                        // Save sub-messages
                        //create old/new map of ids
                        boolean addFlag = true;
                        maintainMessageMap(addFlag, messageMapList,
                                messageId, -1, threadId, -1,
                                parentMessageId, -1);


                    } else {
                        // insert thread messages
                        // find new messageId, threadId of parent
                        // extract query in order so parent message should exist to enter thread messages in proper order
                        // maintain the map

                        if (parentMessageId != 0) {

                            // Find user based on the UserIdMapper assignment
                            long userId = findUserFromExternalId(mapperType, ssoValue);

                            // Insert as guest user if user not valid in new schema
                            if (userId == 0) {
                                userId = UserLocalServiceUtil.getDefaultUser(companyId).getUserId();
                                mLog.warn("Inserting message as guest user");
                            }

                            // find parent message in map and create new message
                            for (MbMessageMap newMapItem : messageMapList) {

                                if (parentMessageId == newMapItem.getOldMessageId()) {
                                    long newThreadId = newMapItem.getNewThreadId();
                                    long newParentMessageId = newMapItem.getNewMessageId();

                                    mbMessage = MBMessageLocalServiceUtil.addMessage(userId, userName, groupId,
                                            category, newThreadId, newParentMessageId, subject, body, format, inputStreamOVPs, anonymous,
                                            priority, allowPingbacks, serviceContext);
                                    mLog.info("Added MBMessage: " + subject + " Inserted by userName: " + userName);

                                    //assign group and guest permissions  (true)
                                    ResourceLocalServiceUtil.addResources(
                                            mbMessage.getCompanyId(), mbMessage.getGroupId(), mbMessage.getUserId(),
                                            MBMessage.class.getName(), mbMessage.getMessageId(),
                                            false, true, true);

                                    //create old/new map of ids
                                    boolean addFlag = false;
                                    maintainMessageMap(addFlag, messageMapList,
                                            messageId, mbMessage.getMessageId(), threadId, mbMessage.getThreadId(),
                                            parentMessageId, mbMessage.getParentMessageId());

                                    break;
                                }          //matching message found
                            }              //for loop
                        }                 //non-parent
                    }
                } catch (Exception e) {
                    // log issue creating the MBMessage
                    mLog.warn("Could not add MBMessage: " + subject + ". Inserted by userName: " + userName);
                }
            }
        }

        return messageMapList;
    }

    /**
     * Determine MB Message already exists
     */
    private boolean migratedMBMessage(String subject, String body)
            throws SystemException {
        boolean messageFound = false;

        List<MBMessage> existingMessages = MBMessageLocalServiceUtil.getMBMessages(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        for (MBMessage existingMessage : existingMessages) {
            if (subject.equals(existingMessage.getSubject()) && body.equals(existingMessage.getBody())) {
                messageFound = true;
                break;
            }
        }
        return messageFound;
    }

    /**
     * Determine MB category id
     */
    private long determineMBCategory(String name, String description)
            throws SystemException {

        long category = -1;
        List<MBCategory> categoryList = MBCategoryLocalServiceUtil.getMBCategories(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        for (MBCategory categoryItem : categoryList) {
            if (categoryItem.getName().equals(name) &&
                    categoryItem.getDescription().equals(description)) {
                category = categoryItem.getCategoryId();
                break;
            }
        }
        if (category == -1) {
            mLog.warn("Could not add find MBCategory: '" + name + "' for MB Message");
        }
        return category;
    }

    /**
     * Maintain map of message old and new id attributes
     */
    private ArrayList<MbMessageMap> maintainMessageMap(boolean addFlag, ArrayList<MbMessageMap> messageMapList,
                                                       long oldMessageId, long newMessageId,
                                                       long oldThreadId, long newThreadId,
                                                       long oldParentMessageId, long newParentMessageId)
            throws PortalException, SystemException {

        MbMessageMap mapItem = new MbMessageMap();
        mapItem.setOldMessageId(oldMessageId);
        mapItem.setNewMessageId(newMessageId);
        mapItem.setOldThreadId(oldThreadId);
        mapItem.setNewThreadId(newThreadId);
        mapItem.setOldParentMessageId(oldParentMessageId);
        mapItem.setNewParentMessageId(newParentMessageId);

        if (addFlag) {
            messageMapList.add(mapItem);
        } else {
            //update the map of the current record with new Id values
            int mapIndex = 0;
            for (MbMessageMap fixMapItem : messageMapList) {
                if (mapItem.getOldMessageId() == fixMapItem.getOldMessageId()) {
                    messageMapList.set(mapIndex, mapItem);
                    break;
                }
                mapIndex++;
            }
        }
        return messageMapList;
    }

    //
    // methods to read extract files
    //

    private static String readFileAsString(String filePath) throws IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null) try {
                f.close();
            } catch (IOException ignored) {
            }
        }
        return new String(buffer);
    }

    private static ArrayList<UserExtract> readUserExtractAsArrayList(String fileName)
            throws IOException {
        ArrayList<UserExtract> storeValues = new ArrayList<UserExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), "UTF8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserExtract userEntry = new UserExtract();
                userEntry.setSsoValue(dataValue[0]);
                userEntry.setMapperType(dataValue[1]);
                userEntry.setOrgName(dataValue[2]);
                // remove any plus signs from screen name
                String newScreenName = dataValue[3].replace("+", "_");
                userEntry.setScreenName(newScreenName);
                userEntry.setEmailAddress(dataValue[4]);
                userEntry.setFirstName(dataValue[5]);
                userEntry.setMiddleName(dataValue[6]);
                userEntry.setLastName(dataValue[7]);
                // hardwire fix for user Rune with incorrect UTF-8 last name
                if (userEntry.getFirstName() .equals("Rune") &&
                        userEntry.getEmailAddress() .equals("rune.sortun@kaefer.no")) {
                    userEntry.setLastName("S" + "\u00F8" + "rtun");
                }
                userEntry.setJobTitle(dataValue[8]);

                storeValues.add(userEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        }
        return storeValues;
    }

    private static ArrayList<UserGroupsExtract> readUserGroupsExtractAsArrayList(String fileName)
            throws IOException {
        ArrayList<UserGroupsExtract> storeValues = new ArrayList<UserGroupsExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), "UTF8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserGroupsExtract userEntry = new UserGroupsExtract();
                userEntry.setSsoValue(dataValue[0]);
                userEntry.setMapperType(dataValue[1]);
                userEntry.setEmailAddress(dataValue[2]);
                userEntry.setFriendlyUrl(dataValue[3]);

                storeValues.add(userEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        }
        return storeValues;
    }

    private static ArrayList<UserOrgsExtract> readUserOrgsExtractAsArrayList(String fileName)
            throws IOException {
        ArrayList<UserOrgsExtract> storeValues = new ArrayList<UserOrgsExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), "UTF8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserOrgsExtract userEntry = new UserOrgsExtract();
                userEntry.setSsoValue(dataValue[0]);
                userEntry.setMapperType(dataValue[1]);
                userEntry.setEmailAddress(dataValue[2]);
                userEntry.setOrgName(dataValue[3]);

                storeValues.add(userEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        }
        return storeValues;
    }

    private static ArrayList<UserRolesExtract> readUserRolesExtractAsArrayList(String fileName)
            throws IOException, IllegalArgumentException {
        ArrayList<UserRolesExtract> storeValues = new ArrayList<UserRolesExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), "UTF8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserRolesExtract userEntry = new UserRolesExtract();
                userEntry.setSsoValue(dataValue[0]);
                userEntry.setMapperType(dataValue[1]);
                userEntry.setEmailAddress(dataValue[2]);
                userEntry.setRoleName(dataValue[3]);

                storeValues.add(userEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        }
        return storeValues;
    }

    private static ArrayList<UserReviewExtract> readUserReviewExtractAsArrayList(String fileName)
            throws IOException, ParseException {
        ArrayList<UserReviewExtract> storeValues = new ArrayList<UserReviewExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), "UTF8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserReviewExtract userEntry = new UserReviewExtract();
                userEntry.setSsoValue(dataValue[0]);
                userEntry.setMapperType(dataValue[1]);
                userEntry.setEmailAddress(dataValue[2]);
                userEntry.setCourseIri(dataValue[3]);
                userEntry.setScore(Long.valueOf(dataValue[4]));
                //extract query replaced all cr/lf in summary and content with <p /> so revert values here
                String tempSummary = dataValue[5].replace("<p />", "\r\n");
                tempSummary = tempSummary.replace("<br />", "\n");
                String tempContent = dataValue[6].replace("<p />", "\r\n");
                tempContent = tempContent.replace("<br />", "\n");
                userEntry.setSummary(tempSummary);
                userEntry.setContent(tempContent);
                if (dataValue[7].length() == 19) {
                    userEntry.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataValue[7]));
                }
                if (dataValue[8].length() == 19) {
                    userEntry.setModifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataValue[8]));
                }
                if ("1".equals(dataValue[9])) {
                    userEntry.setRemoved(true);
                } else {
                    userEntry.setRemoved(false);
                }
                if (dataValue[10].length() == 19) {
                    userEntry.setRemovedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataValue[10]));
                }

                storeValues.add(userEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        }
        return storeValues;
    }

    private static ArrayList<UserReviewHelpExtract> readUserReviewHelpExtractAsArrayList(String fileName)
            throws IOException {
        ArrayList<UserReviewHelpExtract> storeValues = new ArrayList<UserReviewHelpExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), "UTF8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserReviewHelpExtract userEntry = new UserReviewHelpExtract();
                userEntry.setVoteSsoValue(dataValue[0]);
                userEntry.setVoteMapperType(dataValue[1]);
                userEntry.setVoteEmailAddress(dataValue[2]);
                userEntry.setReviewSsoValue(dataValue[3]);
                userEntry.setReviewMapperType(dataValue[4]);
                userEntry.setReviewEmailAddress(dataValue[5]);
                userEntry.setCourseIri(dataValue[6]);
                userEntry.setScore(Long.valueOf(dataValue[7]));

                storeValues.add(userEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        }
        return storeValues;
    }

    private static ArrayList<MbCategoryExtract> readMbCategoryExtractAsArrayList(String fileName)
            throws IOException, ParseException {
        ArrayList<MbCategoryExtract> storeValues = new ArrayList<MbCategoryExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), "UTF8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                MbCategoryExtract categoryEntry = new MbCategoryExtract();
                categoryEntry.setSsoValue(dataValue[0]);
                categoryEntry.setMapperType(dataValue[1]);
                categoryEntry.setEmailAddress(dataValue[2]);
                categoryEntry.setUserName(dataValue[3]);
                categoryEntry.setCategoryId(Long.valueOf(dataValue[4]));
                categoryEntry.setParentCategoryId(Long.valueOf(dataValue[5]));
                //extract query replaced all cr/lf in name and description with <p /> so revert values here
                String tempName = dataValue[6].replace("<p />", "\r\n");
                tempName = tempName.replace("<br />", "\n");
                String tempDescription = dataValue[7].replace("<p />", "\r\n");
                tempDescription = tempDescription.replace("<br />", "\n");
                categoryEntry.setName(tempName);
                categoryEntry.setDescription(tempDescription);
                if (dataValue[8].length() == 19) {
                    categoryEntry.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataValue[8]));
                }
                if (dataValue[9].length() == 19) {
                    categoryEntry.setModifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataValue[9]));
                }
                if (dataValue[10].length() == 19) {
                    categoryEntry.setLastPostDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataValue[10]));
                }
                categoryEntry.setInEmailAddress(dataValue[11]);
                categoryEntry.setInProtocol(dataValue[12]);
                categoryEntry.setInServerName(dataValue[13]);
                categoryEntry.setInServerPort(Integer.valueOf(dataValue[14]));
                if ("1".equals(dataValue[15])) {
                    categoryEntry.setInUseSSL(true);
                } else {
                    categoryEntry.setInUseSSL(false);
                }
                categoryEntry.setInUserName(dataValue[16]);
                categoryEntry.setInPassword(dataValue[17]);
                categoryEntry.setInReadInterval(Integer.valueOf(dataValue[18]));
                categoryEntry.setOutEmailAddress(dataValue[19]);
                if ("1".equals(dataValue[20])) {
                    categoryEntry.setOutCustom(true);
                } else {
                    categoryEntry.setOutCustom(false);
                }
                categoryEntry.setOutServerName(dataValue[21]);
                categoryEntry.setOutServerPort(Integer.valueOf(dataValue[22]));
                if ("1".equals(dataValue[23])) {
                    categoryEntry.setOutUseSSL(true);
                } else {
                    categoryEntry.setOutUseSSL(false);
                }
                categoryEntry.setOutUserName(dataValue[24]);
                categoryEntry.setOutPassword(dataValue[25]);
                if ("1".equals(dataValue[26])) {
                    categoryEntry.setMailingListActive(true);
                } else {
                    categoryEntry.setMailingListActive(false);
                }
                //new attribute in lrv61
                categoryEntry.setDisplayStyle("default");

                storeValues.add(categoryEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        } catch (ParseException e) {
            mLog.error("Parse Exception processing file: " + fileName + e);
        }
        return storeValues;
    }

    private static ArrayList<MbMessageExtract> readMbMessageExtractAsArrayList(String fileName)
            throws IOException, ParseException {
        ArrayList<MbMessageExtract> storeValues = new ArrayList<MbMessageExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), "UTF8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                MbMessageExtract messageEntry = new MbMessageExtract();
                messageEntry.setSsoValue(dataValue[0]);
                messageEntry.setMapperType(dataValue[1]);
                messageEntry.setEmailAddress(dataValue[2]);
                messageEntry.setUserName(dataValue[3]);
                //extract query replaced all cr/lf in name and description with <p /> so revert values here
                String tempName = dataValue[4].replace("<p />", "\r\n");
                tempName = tempName.replace("<br />", "\n");
                String tempDescription = dataValue[5].replace("<p />", "\r\n");
                tempDescription = tempDescription.replace("<br />", "\n");
                messageEntry.setName(tempName);
                messageEntry.setDescription(tempDescription);
                if (dataValue[6].length() == 19) {
                    messageEntry.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataValue[6]));
                }
                if (dataValue[7].length() == 19) {
                    messageEntry.setModifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataValue[7]));
                }
                messageEntry.setMessageId(Long.valueOf(dataValue[8]));
                messageEntry.setThreadId(Long.valueOf(dataValue[9]));
                messageEntry.setRootMessageId(Long.valueOf(dataValue[10]));
                messageEntry.setParentMessageId(Long.valueOf(dataValue[11]));
                //extract query replaced all cr/lf in subject and body with <p /> so revert values here
                String tempSubject = dataValue[12].replace("<p />", "\r\n");
                tempSubject = tempSubject.replace("<br />", "\n");
                String tempBody = dataValue[13].replace("<p />", "\r\n");
                tempBody = tempBody.replace("<br />", "\n");
                messageEntry.setSubject(tempSubject);
                messageEntry.setBody(tempBody);
                if ("1".equals(dataValue[14])) {
                    messageEntry.setAttachments(true);
                } else {
                    messageEntry.setAttachments(false);
                }
                if ("1".equals(dataValue[15])) {
                    messageEntry.setAnonymous(true);
                } else {
                    messageEntry.setAnonymous(false);
                }
                messageEntry.setPriority(Double.valueOf(dataValue[16]));
                if ("1".equals(dataValue[17])) {
                    messageEntry.setAllowPingbacks(true);
                } else {
                    messageEntry.setAllowPingbacks(false);
                }

                storeValues.add(messageEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        } catch (ParseException e) {
            mLog.error("Parse Exception processing file: " + fileName + e);
        }
        return storeValues;
    }

    static Log mLog = LogFactoryUtil.getLog(MigrationPortlet.class);

}
