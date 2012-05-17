Liferay.Service.register("Liferay.Service.CATALOG", "org.nterlearning.datamodel.catalog.service", "nter-catalog-portlet");

Liferay.Service.registerClass(
	Liferay.Service.CATALOG, "Component",
	{
		findByCompanyId: true,
		findByComponentId: true,
		findByComponentIri: true,
		findByFeedReferenceId: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.CATALOG, "ComponentRecord",
	{
		findByComponentIri: true,
		findByCourseRecordId: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.CATALOG, "Course",
	{
		findAllValidCourses: true,
		findByCourseId: true,
		findByCourseIri: true,
		findByGroupId: true,
		findByCompanyId: true,
		findByFeedReferenceId: true,
		findAllCourses: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.CATALOG, "CourseRecord",
	{
		findByPrimaryKey: true,
		findByCourseRecordIri: true,
		findByCourseIri: true,
		findBySingleSignOnValue: true,
		findByUserId: true,
		findByFeedReferenceId: true,
		getComponentRecords: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.CATALOG, "CourseReview",
	{
		findScoreByReviewId: true,
		findScoreByCourseId: true,
		findByCourseId: true,
		findByCourseIdWithUserId: true,
		findByUserId: true,
		findByCourseIdWithRemoved: true,
		countByCourseIdWithRemoved: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.CATALOG, "Courses_Components",
	{
		findByCourseId: true,
		findByCourseIri: true,
		findByComponentId: true,
		findByComponentIri: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.CATALOG, "FeedReference",
	{
		findByFeedIri: true,
		findByFeedHref: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.CATALOG, "GlobalCourseReview",
	{
		findByCourseReviewIri: true,
		findByCourseId: true,
		findByCourseIri: true,
		findValidByCourseId: true,
		countValidByCourseId: true
	}
);