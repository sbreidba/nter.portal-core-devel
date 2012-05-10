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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.*;
import com.liferay.portal.service.*;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;
import com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil;
import com.liferay.util.PwdGenerator;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.nterlearning.course.hook.SetupAction;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.migration.model.*;
import org.nterlearning.utils.PortalProperties;
import org.nterlearning.utils.PortalPropertiesUtil;
import org.nterlearning.utils.ReviewUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.*;
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
        String migrateUsers = PropsUtil.get(PortalProperties.NTER_MIGRATE_USERS);
        Boolean createUser = ((migrateUsers != null) && migrateUsers.equals("true"));

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

        if (createUser) {
            for (UserExtract userItem : userList) {
                // extract values from list
                String ssoValue = userItem.getSsoValue();
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
                    mLog.info("Created user: " + firstName + " " + lastName +
                            " with email:" + emailAddress +
                            " group count: " + groupIds.length +
                            " org count: " + organizationIds.length +
                            " role count: " + roleIds.length);

                    SetupAction.addSsoInformation(user);
                } catch (Exception e) {
                    // probably account already exists, but log just in case
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
        }
        catch (DuplicateUserScreenNameException e) {
            user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName);
        }

        return user;
    }

    /**
     * Find User's groups
     */
    public long[] findUserGroups(ArrayList<UserGroupsExtract> userGroupList, String ssoValue, long companyId)
            throws PortalException, SystemException {
        Group userGroup;
        Group guestGroup = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST);
        long[] groupIds = new long[] {guestGroup.getGroupId()};
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
    public long[] findUserOrganizations(ArrayList<UserOrgsExtract> userOrgList, String ssoValue, long companyId)
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
    public long[] findUserRoles(ArrayList<UserRolesExtract> userRoleList, String ssoValue, long companyId)
            throws PortalException, SystemException {
        Role userRole;
        Role defaultRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        long roleIds[] = new long[] {defaultRole.getRoleId()};
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
     * Migration process using a file for user helpful/not helpful review score
     *
     * @param request  HTTP Request handler
     * @param response HTTP response handler
     */
    public void processMigrateUserReviewImport(ActionRequest request, ActionResponse response)
            throws FileNotFoundException, IOException, ParseException, PortalException, SystemException {

        mLog.info("Importing user local course reviews");

        // Extract local course reviews  to migrate
        String userReviewsFilename = MigrationConstants.USER_REVIEWS_MIGRATION_PATH;
        ArrayList<UserReviewExtract> userReviewList = readUserReviewExtractAsArrayList(userReviewsFilename);

        for (UserReviewExtract userItem : userReviewList) {
            // extract values from list
            String ssoValue = userItem.getSsoValue();
            String emailAddress = userItem.getEmailAddress();
            String courseIri = userItem.getCourseIri();
            long score = Long.valueOf(userItem.getScore());
            String summary = userItem.getSummary();
            String content = userItem.getContent();
            Date createDate = userItem.getCreateDate();
            Date modifiedDate = userItem.getModifiedDate();
            boolean removed = userItem.getRemoved();
            Date removedDate = userItem.getRemovedDate();

            long courseId = 0;
            long userId = 0;

            // Find user based on the UserIdMapper assignment
            UserIdMapper userMapper;
            try {
                userMapper =
                        UserIdMapperLocalServiceUtil.getUserIdMapperByExternalUserId(PortalPropertiesUtil.getSsoImplementation(),
                                ssoValue);
                userId = userMapper.getUserId();
            } catch (Exception e) {
                mLog.warn("Could not find local user which maps to external user id " + ssoValue);
            }

            // Find course based on courseIri assignment
            Course course;
            try {
                course = CourseLocalServiceUtil.fetchByCourseIri(courseIri);
                courseId = course.getCourseId();
            } catch (Exception e) {
                // probably course does not exist, but log just in case
                mLog.warn("Could not find course which maps to courseIRI: " + courseIri);
            }

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
    }

    /**
     * Migration process using a file for user helpful/not helpful review score
     *
     * @param request  HTTP Request handler
     * @param response HTTP response handler
     */
    public void processMigrateReviewHelpImport(ActionRequest request, ActionResponse response)
            throws FileNotFoundException, IOException, PortalException, SystemException {

        mLog.info("Importing user helpful/not helpful scores for course reviews");

        // Extract user review scores to migrate
        String userReviewHelpFilename = MigrationConstants.USER_REVIEW_HELP_MIGRATION_PATH;
        ArrayList<UserReviewHelpExtract> userReviewHelpList = readUserReviewHelpExtractAsArrayList(userReviewHelpFilename);

        for (UserReviewHelpExtract userItem : userReviewHelpList) {
            // extract values from list
            String ssoValue = userItem.getSsoValue();
            String emailAddress = userItem.getEmailAddress();
            String courseIri = userItem.getCourseIri();
            long score = Long.valueOf(userItem.getScore());

            long courseId = 0;
            long userId = 0;

            // Find user based on the UserIdMapper assignment
            UserIdMapper userMapper;
            try {
                userMapper =
                        UserIdMapperLocalServiceUtil.getUserIdMapperByExternalUserId(PortalPropertiesUtil.getSsoImplementation(),
                                ssoValue);
                userId = userMapper.getUserId();
            } catch (Exception e) {
                mLog.warn("Could not find local user which maps to external user id " + ssoValue);
            }

            // Find course based on courseIri assignment
            Course course;
            try {
                course = CourseLocalServiceUtil.fetchByCourseIri(courseIri);
                courseId = course.getCourseId();
            } catch (Exception e) {
                // probably course does not exist, but log just in case
                mLog.warn("Could not find course which maps to courseIRI: " + courseIri);
            }

            // When user, course, and review exist, migrate the extracted review helpful/not helpful
            if (userId != 0 && courseId != 0) {
                List<CourseReview> courseReviewList;
                RatingsEntry ratingsEntry;
                try {
                    courseReviewList = CourseReviewLocalServiceUtil.findByCourseIdWithUserId(userId, courseId);
                    if (courseReviewList.size() == 0) {
                       mLog.warn("Review not found for CourseIri: " + courseIri + " UserId: " + ssoValue);
                    }
                    for (CourseReview courseReview : courseReviewList) {
                        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                                RatingsEntryLocalServiceUtil.class.getName(), request);

                        RatingsEntryLocalServiceUtil.updateEntry(userId, CourseReview.class.getName(), courseReview.getPrimaryKey(), score, serviceContext);
                        mLog.info("User Helpful Score added for CourseIri: " + courseIri + " UserId: " + ssoValue);
                    }
                } catch (Exception e) {
                    // review does not exist
                    mLog.warn("Could not find user " + ssoValue + " review for course " + courseIri);
                }
            }
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
            throws  IOException {
        ArrayList<UserExtract> storeValues = new ArrayList<UserExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserExtract userEntry = new UserExtract();
                userEntry.setSsoValue(dataValue[0]);
                userEntry.setMapperType(dataValue[1]);
                userEntry.setOrgName(dataValue[2]);
                userEntry.setScreenName(dataValue[3]);
                userEntry.setEmailAddress(dataValue[4]);
                userEntry.setFirstName(dataValue[5]);
                userEntry.setMiddleName(dataValue[6]);
                userEntry.setLastName(dataValue[7]);
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
            throws  IOException {
        ArrayList<UserGroupsExtract> storeValues = new ArrayList<UserGroupsExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

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
            throws  IOException {
        ArrayList<UserOrgsExtract> storeValues = new ArrayList<UserOrgsExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

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
            throws  IOException, IllegalArgumentException {
        ArrayList<UserRolesExtract> storeValues = new ArrayList<UserRolesExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

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
            throws  IOException, ParseException {
        ArrayList<UserReviewExtract> storeValues = new ArrayList<UserReviewExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserReviewExtract userEntry = new UserReviewExtract();
                userEntry.setSsoValue(dataValue[0]);
                userEntry.setMapperType(dataValue[1]);
                userEntry.setEmailAddress(dataValue[2]);
                userEntry.setCourseIri(dataValue[3]);
                userEntry.setScore(dataValue[4]);
                userEntry.setSummary(dataValue[5]);
                userEntry.setContent(dataValue[6]);
                userEntry.setCreateDate(new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"").parse(dataValue[7]));
                userEntry.setModifiedDate(new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"").parse(dataValue[8]));
                if (dataValue[9].equals("1")) {
                    userEntry.setRemoved(true);
                } else  {
                    userEntry.setRemoved(false);
                }
                if (dataValue[10].length() != 0) {
                    userEntry.setRemovedDate(new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"").parse(dataValue[10]));
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
            throws  IOException {
        ArrayList<UserReviewHelpExtract> storeValues = new ArrayList<UserReviewHelpExtract>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String dataValue[] = line.split("\t");
                UserReviewHelpExtract userEntry = new UserReviewHelpExtract();
                userEntry.setSsoValue(dataValue[0]);
                userEntry.setMapperType(dataValue[1]);
                userEntry.setEmailAddress(dataValue[2]);
                userEntry.setCourseIri(dataValue[3]);
                userEntry.setScore(dataValue[4]);
                storeValues.add(userEntry);
            }
        } catch (FileNotFoundException e) {
            mLog.error("File not found: " + fileName);
        } catch (IOException e) {
            mLog.error("IO Exception processing file: " + fileName);
        }
        return storeValues;
    }

    static Log mLog = LogFactoryUtil.getLog(MigrationPortlet.class);

}
