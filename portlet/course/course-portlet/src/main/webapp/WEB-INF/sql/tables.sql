create table CATALOG_Component (
	componentId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	feedReferenceId LONG,
	componentIri VARCHAR(75) null,
	updatedDate DATE null,
	language VARCHAR(75) null,
	href VARCHAR(75) null,
	fullTextHref VARCHAR(75) null,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	copyright STRING null,
	displayWidth INTEGER,
	displayHeight INTEGER,
	createDate DATE null,
	removed BOOLEAN,
	removedDate DATE null,
	version VARCHAR(75) null,
	versionDate DATE null,
	price DOUBLE,
	priceUnit VARCHAR(75) null,
	priceTerms VARCHAR(75) null,
	priceExpiration VARCHAR(75) null
);

create table CATALOG_ComponentRecord (
	componentRecordId LONG not null primary key,
	courseRecordId LONG,
	componentIri VARCHAR(75) null,
	updatedDate DATE null,
	completionStatus VARCHAR(75) null,
	completionPercent INTEGER
);

create table CATALOG_Contributor (
	contributorId LONG not null primary key,
	courseId LONG,
	componentId LONG,
	role VARCHAR(75) null,
	contributorName VARCHAR(75) null,
	virtualCardData VARCHAR(75) null
);

create table CATALOG_Course (
	courseId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	feedReferenceId LONG,
	href VARCHAR(75) null,
	fullTextHref VARCHAR(75) null,
	courseIri VARCHAR(75) null,
	updatedDate DATE null,
	title STRING null,
	transcriptAbstract STRING null,
	description STRING null,
	keywords STRING null,
	copyright STRING null,
	ratingLevel STRING null,
	ratingReason STRING null,
	duration VARCHAR(75) null,
	durationStandard VARCHAR(75) null,
	featuredStatus DOUBLE,
	popularWeight DOUBLE,
	accessCount LONG,
	completedCount LONG,
	createDate DATE null,
	removed BOOLEAN,
	removedDate DATE null,
	supersedesCourseIri VARCHAR(75) null,
	supersededByCourseIri VARCHAR(75) null,
	releaseOnDate DATE null,
	acceptUntilDate DATE null,
	version VARCHAR(75) null,
	versionDate DATE null,
	price DOUBLE,
	priceUnit VARCHAR(75) null,
	priceTerms VARCHAR(75) null,
	priceExpiration VARCHAR(75) null,
	oneStarRateCount LONG,
	twoStarRateCount LONG,
	threeStarRateCount LONG,
	fourStarRateCount LONG,
	fiveStarRateCount LONG
);

create table CATALOG_CourseImage (
	courseImageId LONG not null primary key,
	courseId LONG,
	orderWeight DOUBLE,
	language VARCHAR(75) null,
	imageId LONG,
	alternateText VARCHAR(75) null,
	sourceImageUrl VARCHAR(75) null,
	mimeType VARCHAR(75) null
);

create table CATALOG_CourseRecord (
	courseRecordId LONG not null primary key,
	feedReferenceId LONG,
	courseRecordIri VARCHAR(75) null,
	userId LONG,
	singleSignOnValue VARCHAR(75) null,
	courseIri VARCHAR(75) null,
	updatedDate DATE null,
	completionStatus VARCHAR(75) null,
	removed BOOLEAN,
	removedDate DATE null,
	userHidden BOOLEAN,
	assigned BOOLEAN
);

create table CATALOG_CourseRelated (
	courseRelatedId LONG not null primary key,
	courseId LONG,
	relatedCourseId LONG,
	relatedCourseIri VARCHAR(75) null,
	relationshipType VARCHAR(75) null
);

create table CATALOG_CourseRequirement (
	courseRequirementId LONG not null primary key,
	courseId LONG,
	requirementType VARCHAR(75) null,
	requirementValue STRING null
);

create table CATALOG_CourseReview (
	courseReviewId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	courseId LONG,
	userId LONG,
	summary VARCHAR(75) null,
	content VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	weightedScore DOUBLE,
	removed BOOLEAN,
	removedDate DATE null
);

create table CATALOG_Courses_Components (
	coursesComponentsId LONG not null primary key,
	courseId LONG,
	courseIri VARCHAR(75) null,
	componentId LONG,
	componentIri VARCHAR(75) null,
	orderWeight DOUBLE,
	sectionType VARCHAR(75) null,
	componentType VARCHAR(75) null,
	mimeType VARCHAR(75) null,
	coursePaymentRequired BOOLEAN,
	componentPaymentRequired BOOLEAN
);

create table CATALOG_ExternalLink (
	linkId LONG not null primary key,
	courseId LONG,
	componentId LONG,
	linkType VARCHAR(75) null,
	linkUrl VARCHAR(75) null
);

create table CATALOG_FeedReference (
	feedReferenceId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	contentProviderId VARCHAR(75) null,
	href VARCHAR(75) null,
	hrefHash VARCHAR(75) null,
	pshb VARCHAR(75) null,
	pshbSubscribed BOOLEAN,
	feedIri VARCHAR(75) null,
	feedType VARCHAR(75) null,
	feedVersion VARCHAR(75) null,
	trustworthyWeight DOUBLE,
	createDate DATE null,
	removed BOOLEAN,
	removedDate DATE null,
	removedReason VARCHAR(75) null,
	syncDate DATE null,
	syncSuccess BOOLEAN
);

create table CATALOG_FeedSyncHistory (
	syncId LONG not null primary key,
	feedReferenceId LONG,
	syncDate DATE null,
	success BOOLEAN,
	syncMessage VARCHAR(75) null,
	numberOfEntries INTEGER
);

create table CATALOG_FlagReport (
	uuid_ VARCHAR(75) null,
	flagReportId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	classNameId LONG,
	classPK LONG,
	createDate DATE null,
	title VARCHAR(75) null,
	content VARCHAR(75) null,
	flagReason VARCHAR(75) null,
	flagComment VARCHAR(75) null,
	moderateAction VARCHAR(75) null,
	moderatorComment VARCHAR(75) null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table CATALOG_FlagReportStats (
	flagReportStatsId LONG not null primary key,
	classNameId LONG,
	classPK LONG,
	totalEntries INTEGER,
	totalModerated INTEGER,
	totalApproved INTEGER
);

create table CATALOG_GlobalCourseReview (
	globalCourseReviewId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	courseReviewIri VARCHAR(75) null,
	updatedDate DATE null,
	courseIri VARCHAR(75) null,
	href VARCHAR(75) null,
	nterInstance VARCHAR(75) null,
	courseId LONG,
	userDisplayName VARCHAR(75) null,
	singleSignOnValue VARCHAR(75) null,
	summary VARCHAR(75) null,
	content VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	starScore DOUBLE,
	fromTrustedReviewer BOOLEAN,
	removed BOOLEAN,
	removedDate DATE null,
	isHidden BOOLEAN
);