create table CATALOG_Component (
	componentId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	feedReferenceId LONG,
	componentIri VARCHAR(255) null,
	updatedDate DATE null,
	language VARCHAR(10) null,
	href VARCHAR(3999) null,
	fullTextHref VARCHAR(3999) null,
	title VARCHAR(2000) null,
	description VARCHAR(3999) null,
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
	componentIri VARCHAR(255) null,
	updatedDate DATE null,
	completionStatus VARCHAR(50) null,
	completionPercent INTEGER
);

create table CATALOG_Contributor (
	contributorId LONG not null primary key,
	courseId LONG,
	componentId LONG,
	role VARCHAR(50) null,
	contributorName VARCHAR(200) null,
	virtualCardData VARCHAR(3999) null
);

create table CATALOG_Course (
	courseId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	feedReferenceId LONG,
	href VARCHAR(3999) null,
	fullTextHref VARCHAR(3999) null,
	courseIri VARCHAR(255) null,
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
	supersedesCourseIri VARCHAR(255) null,
	supersededByCourseIri VARCHAR(255) null,
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
	language VARCHAR(10) null,
	imageId LONG,
	alternateText VARCHAR(3999) null,
	sourceImageUrl VARCHAR(3999) null,
	mimeType VARCHAR(50) null
);

create table CATALOG_CourseRecord (
	courseRecordId LONG not null primary key,
	feedReferenceId LONG,
	courseRecordIri VARCHAR(255) null,
	userId LONG,
	singleSignOnValue VARCHAR(255) null,
	courseIri VARCHAR(255) null,
	updatedDate DATE null,
	completionStatus VARCHAR(50) null,
	removed BOOLEAN,
	removedDate DATE null,
	userHidden BOOLEAN,
	assigned BOOLEAN
);

create table CATALOG_CourseRelated (
	courseRelatedId LONG not null primary key,
	courseId LONG,
	relatedCourseId LONG,
	relatedCourseIri VARCHAR(255) null,
	relationshipType VARCHAR(50) null
);

create table CATALOG_CourseRequirement (
	courseRequirementId LONG not null primary key,
	courseId LONG,
	requirementType VARCHAR(50) null,
	requirementValue STRING null
);

create table CATALOG_CourseReview (
	courseReviewId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	courseId LONG,
	userId LONG,
	summary VARCHAR(250) null,
	content VARCHAR(3999) null,
	createDate DATE null,
	modifiedDate DATE null,
	weightedScore DOUBLE,
	removed BOOLEAN,
	removedDate DATE null
);

create table CATALOG_Courses_Components (
	coursesComponentsId LONG not null primary key,
	courseId LONG,
	courseIri VARCHAR(255) null,
	componentId LONG,
	componentIri VARCHAR(255) null,
	orderWeight DOUBLE,
	sectionType VARCHAR(50) null,
	componentType VARCHAR(50) null,
	mimeType VARCHAR(50) null,
	coursePaymentRequired BOOLEAN,
	componentPaymentRequired BOOLEAN
);

create table CATALOG_ExternalLink (
	linkId LONG not null primary key,
	courseId LONG,
	componentId LONG,
	linkType VARCHAR(255) null,
	linkUrl VARCHAR(3999) null
);

create table CATALOG_FeedReference (
	feedReferenceId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	contentProviderId VARCHAR(255) null,
	href VARCHAR(3999) null,
	hrefHash VARCHAR(255) null,
	pshb VARCHAR(3999) null,
	pshbSubscribed BOOLEAN,
	feedIri VARCHAR(255) null,
	feedType VARCHAR(1) null,
	feedVersion VARCHAR(25) null,
	trustworthyWeight DOUBLE,
	createDate DATE null,
	removed BOOLEAN,
	removedDate DATE null,
	removedReason VARCHAR(1) null,
	syncDate DATE null,
	syncSuccess BOOLEAN
);

create table CATALOG_FeedSyncHistory (
	syncId LONG not null primary key,
	feedReferenceId LONG,
	syncDate DATE null,
	success BOOLEAN,
	syncMessage VARCHAR(2000) null,
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
	title VARCHAR(2000) null,
	content VARCHAR(3999) null,
	flagReason VARCHAR(75) null,
	flagComment VARCHAR(3999) null,
	moderateAction VARCHAR(75) null,
	moderatorComment VARCHAR(3999) null,
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
	courseReviewIri VARCHAR(255) null,
	updatedDate DATE null,
	courseIri VARCHAR(255) null,
	href VARCHAR(3999) null,
	nterInstance VARCHAR(75) null,
	courseId LONG,
	userDisplayName VARCHAR(255) null,
	singleSignOnValue VARCHAR(255) null,
	summary VARCHAR(250) null,
	content VARCHAR(3999) null,
	createDate DATE null,
	modifiedDate DATE null,
	starScore DOUBLE,
	fromTrustedReviewer BOOLEAN,
	removed BOOLEAN,
	removedDate DATE null,
	isHidden BOOLEAN
);