create index IX_40FE5EB on CATALOG_Component (companyId);
create unique index IX_79837A2B on CATALOG_Component (componentId);
create unique index IX_B6F316B4 on CATALOG_Component (componentIri);
create index IX_D559B7FB on CATALOG_Component (feedReferenceId);

create index IX_F02B3FE5 on CATALOG_ComponentRecord (componentIri);
create index IX_FD31BB49 on CATALOG_ComponentRecord (courseRecordId);

create index IX_CB6D3637 on CATALOG_Contributor (componentId, role);
create index IX_13E73E45 on CATALOG_Contributor (courseId);
create index IX_BB413D6F on CATALOG_Contributor (courseId, role);

create index IX_916DF701 on CATALOG_Course (companyId);
create unique index IX_DC2DEA11 on CATALOG_Course (courseId);
create unique index IX_A996A38E on CATALOG_Course (courseIri);
create index IX_39F02091 on CATALOG_Course (feedReferenceId);
create index IX_785A49C3 on CATALOG_Course (groupId);
create index IX_15D50201 on CATALOG_Course (supersededByCourseIri);

create index IX_57BADBB6 on CATALOG_CourseImage (courseId, language);

create index IX_18C5C77D on CATALOG_CourseRecord (courseIri);
create unique index IX_4C1C196C on CATALOG_CourseRecord (courseRecordIri);
create index IX_26EA5540 on CATALOG_CourseRecord (feedReferenceId);
create index IX_62CC5A25 on CATALOG_CourseRecord (singleSignOnValue);
create index IX_F1E6F672 on CATALOG_CourseRecord (userId);

create index IX_5A723680 on CATALOG_CourseRelated (courseId, relationshipType);
create index IX_58120FBE on CATALOG_CourseRelated (relatedCourseIri);

create index IX_5064CEBB on CATALOG_CourseRequirement (courseId, requirementType);

create index IX_E71EF349 on CATALOG_CourseReview (companyId);
create index IX_39C844C9 on CATALOG_CourseReview (courseId);
create index IX_DE4CBCE7 on CATALOG_CourseReview (courseId, removed);
create index IX_B2BCC403 on CATALOG_CourseReview (courseId, userId);
create index IX_B52DB80B on CATALOG_CourseReview (groupId);
create index IX_5A06F2F1 on CATALOG_CourseReview (removed);
create index IX_C73EA4B9 on CATALOG_CourseReview (userId);

create index IX_E1C947F on CATALOG_Courses_Components (componentId);
create index IX_B57D46E0 on CATALOG_Courses_Components (componentIri);
create index IX_B09E9C13 on CATALOG_Courses_Components (courseId);
create index IX_633C31CC on CATALOG_Courses_Components (courseIri);

create index IX_1019BAB7 on CATALOG_ExternalLink (componentId);
create index IX_B568FE3F on CATALOG_ExternalLink (componentId, linkType);
create index IX_7085E0DB on CATALOG_ExternalLink (courseId);
create index IX_6186C763 on CATALOG_ExternalLink (courseId, linkType);

create index IX_8D2DD69B on CATALOG_FeedReference (companyId);
create index IX_EA7BE028 on CATALOG_FeedReference (contentProviderId);
create unique index IX_AF19FFC5 on CATALOG_FeedReference (feedIri);
create index IX_4751FA19 on CATALOG_FeedReference (feedType);
create index IX_2CFFF4BD on CATALOG_FeedReference (feedVersion);
create index IX_B9E147DD on CATALOG_FeedReference (groupId);
create index IX_FED63E89 on CATALOG_FeedReference (groupId, feedType);
create index IX_5C63F053 on CATALOG_FeedReference (groupId, removed);
create index IX_B6002BA on CATALOG_FeedReference (hrefHash);

create index IX_D6F92BD on CATALOG_FeedSyncHistory (feedReferenceId);

create index IX_576E2B81 on CATALOG_FlagReport (classNameId, classPK);
create index IX_E6EC4E7C on CATALOG_FlagReport (companyId);
create unique index IX_CF8CD55B on CATALOG_FlagReport (flagReportId);
create index IX_22EE43FE on CATALOG_FlagReport (groupId);
create index IX_A020BE08 on CATALOG_FlagReport (uuid_);
create unique index IX_4AA5FFA2 on CATALOG_FlagReport (uuid_, groupId);

create unique index IX_A94C16F4 on CATALOG_FlagReportStats (classNameId, classPK);
create unique index IX_5818DD2B on CATALOG_FlagReportStats (flagReportStatsId);

create index IX_FE886166 on CATALOG_GlobalCourseReview (companyId);
create index IX_7C9A1F0C on CATALOG_GlobalCourseReview (courseId);
create index IX_16B10DF3 on CATALOG_GlobalCourseReview (courseIri);
create unique index IX_46A270BB on CATALOG_GlobalCourseReview (courseReviewIri);
create index IX_F9660968 on CATALOG_GlobalCourseReview (groupId);