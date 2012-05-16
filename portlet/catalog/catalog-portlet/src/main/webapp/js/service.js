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