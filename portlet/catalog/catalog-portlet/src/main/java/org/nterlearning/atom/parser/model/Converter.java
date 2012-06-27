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

package org.nterlearning.atom.parser.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import org.nterlearning.atom.enumerations.NterEntryType;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.FeedContext;
import org.nterlearning.atom.parser.dao.CatalogDataModelUtils;
import org.nterlearning.atom.parser.dao.NterCatalogCourseDependencyException;
import org.nterlearning.atom.parser.dao.NterCatalogRecordDependencyException;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.course.enumerations.ContributorRoleType;
import org.nterlearning.datamodel.catalog.NoSuchCourseException;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.model.impl.ContributorImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseRequirementImpl;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.apache.abdera.model.Person;

import java.util.*;

/**
 * This class converts between NTER Atom parser data model classes and NTER
 * catalog data model classes.
 */
public class Converter {

    private static final String DEFAULT_JAVA_LANGUAGE = "en_US";

    private static Log log = LogFactoryUtil.getLog(Converter.class);


    /**
     * Converts parser asset categories into catalog AssetCategory object.
     *
     * @param parserAssetCategories List of Parser versions of the
     * assetCategory
     * @param vocabularyId Associated vocabularyId
     * @param fc Associated feedContext object the vocabulary was found in
     *
     * @return List of AssetCategory objects
     */
    public static List<AssetCategory> parserAssetCategoriesToCatalog(
            List<NterCategory> parserAssetCategories, long vocabularyId, FeedContext fc) {

        List<AssetCategory> catalogAssetCategories = new ArrayList<AssetCategory>();
        // categoryId will be incremental number for each parsed feed catalog category
        long categoryId = 0;
        // parentCategoryId must be set to zero for 1st level in hierarchy
        long parentCategoryId = 0;

        for (NterCategory parserAssetCategory : parserAssetCategories) {
            categoryId++;

            AssetCategory catalogAssetCategory = parserToCatalog(
                    parserAssetCategory, categoryId, vocabularyId, parentCategoryId, fc);

            catalogAssetCategories.add(catalogAssetCategory);

            List<AssetCategory> nestedCatalogAssetCategories = new ArrayList<AssetCategory>();
            List<AssetCategory> nestedCategories =
                    getNestedCategories(parserAssetCategory, categoryId,
                            vocabularyId, fc, nestedCatalogAssetCategories, categoryId);

            for (AssetCategory nestedCategory : nestedCategories) {
                catalogAssetCategories.add(nestedCategory);
                categoryId++;
            }
        }

        return catalogAssetCategories;
    }


    public static List<AssetCategory> getNestedCategories(
            NterCategory parserAssetCategory, long parentCategoryId,
            long vocabularyId, FeedContext fc,
            List<AssetCategory> nestedCatalogAssetCategories, long nestedCategoryId) {

        List<NterCategory> nestedAssetCategories = parserAssetCategory.getCategories();
        for (NterCategory nestedAssetCategory : nestedAssetCategories) {
            nestedCategoryId++;
            AssetCategory nestedCatalogAssetCategory =
                    parserToCatalog(nestedAssetCategory, nestedCategoryId,
                            vocabularyId, parentCategoryId, fc);
            nestedCatalogAssetCategories.add(nestedCatalogAssetCategory);
            getNestedCategories(nestedAssetCategory, nestedCategoryId,
                    vocabularyId, fc, nestedCatalogAssetCategories, nestedCategoryId);
        }

        return nestedCatalogAssetCategories;
    }


    /**
     * Converts a single parser asset category object to a catalog asset
     * category object.
     *
     * @param parserAssetCategory Parser AssetCategory object to convert
     * @param categoryId Id of the category to parse
     * @param vocabularyId Id of the vocabular to process
     * @param parentCategoryId The Id of the parent category to this category id
     * (may be 0)
     * @param fc The associated FeedContext object
     *
     * @return Converted AssetCategory object
     */
    public static AssetCategory parserToCatalog(NterCategory parserAssetCategory,
            long categoryId, long vocabularyId, long parentCategoryId, FeedContext fc) {

        Date now = new Date();

        AssetCategory catalogAssetCategory =
                AssetCategoryLocalServiceUtil.createAssetCategory(categoryId);
        catalogAssetCategory.setGroupId(fc.getFeedReferenceGroupId());
        catalogAssetCategory.setCompanyId(fc.getCompanyId());
        catalogAssetCategory.setUserId(fc.getUserId());
        catalogAssetCategory.setCreateDate(now);
        catalogAssetCategory.setModifiedDate(now);
        catalogAssetCategory.setName(parserAssetCategory.getCategoryId().getText());
        catalogAssetCategory.setParentCategoryId(parentCategoryId);
        catalogAssetCategory.setVocabularyId(vocabularyId);

        for (NterTitle title : parserAssetCategory.getTitles()) {
            catalogAssetCategory.setTitle(title.getText(),
                    parserLangToCatalogLocale(title.getLanguage()));
        }
        
        return catalogAssetCategory;
    }


    /**
     * Converts a list of Parser Component ref objects into Course_Component
     * objects.
     *
     * @param parserComponentRefs The ParserComponentRef objects to convert into
     * Course_Components
     * @param courseId CourseId to process the components for
     * @param setMissingOrderWeights - if true, the method will set any
     * orderweights that are missing to a number equal to the link's position in
     * the list.  For example, if the 3rd link in the list does not have an order
     * weight, it will be set to 3.0 if this parameter is set to true.
     * @param dependenciesArePersisted - if true, this method assumes all
     * dependencies are persisted and throws an NterCatalogCourseDependencyException
     * if the dependencies are not found in persistence. If false, it ignores any
     * missing dependencies.
     *
     * @return Converted list of Course_Components objects
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay dbo Exception
     * @throws com.liferay.portal.kernel.exception.PortalException Liferay dbo
     * Exception
     * @throws NterCatalogCourseDependencyException Throws in required dependencies
     * are missing.
     */
    public static List<Courses_Components> parserComponentRefsToCatalog(
            List<NterComponentRef> parserComponentRefs, long courseId,
            boolean setMissingOrderWeights, boolean dependenciesArePersisted)
            throws SystemException, NterCatalogCourseDependencyException, PortalException {

        List<Courses_Components> courseComponents = new Vector<Courses_Components>();

        for (int i = 0; i < parserComponentRefs.size(); i++) {
            NterComponentRef parserComponentRef = parserComponentRefs.get(i);
            Courses_Components courseComponent = parserToCatalog(
                    parserComponentRef, courseId, dependenciesArePersisted);

            if (setMissingOrderWeights) {

                if (courseComponent.getOrderWeight() == NterExtension.MISSING_ORDER_WEIGHT) {
                    courseComponent.setOrderWeight(i);
                }
            }

            // add the link
            courseComponents.add(courseComponent);
        }

        return courseComponents;
    }


    /**
     * Converts a single Parser Component ref to a Courses_Components object.
     *
     * @param parserComponentRef Component ref to convert
     * @param courseId Associated courseId of the component ref
     * @param dependenciesArePersisted - if true, this method assumes all
     * dependencies are persisted and throws an NterCatalogCourseDependencyException
     * if the dependencies are not found in persistence. If false, it ignores any
     * missing dependencies.
     *
     * @return Converted Courses_Components object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay DBO exception
     * @throws com.liferay.portal.kernel.exception.PortalException Liferay DBO exception
     * @throws NterCatalogCourseDependencyException Throws if required dependencies
     * are missing.
     */
    public static Courses_Components parserToCatalog(NterComponentRef parserComponentRef,
            long courseId, boolean dependenciesArePersisted)
            throws SystemException, NterCatalogCourseDependencyException, PortalException {

        String catalogComponentIri = parserComponentRef.getComponentId().trim();
        Component catalogComponent =
                ComponentLocalServiceUtil.fetchByComponentIri(catalogComponentIri);
        Course catalogCourse;
        boolean coursePaymentRequired = false;
        
        try {
            catalogCourse = CourseLocalServiceUtil.getCourse(courseId);
            if (catalogCourse.getPrice() > 0.0) {
                coursePaymentRequired = true;
            }
        }
        catch (NoSuchCourseException e) {
            catalogCourse = null;
        }

        if (dependenciesArePersisted) {
            if (catalogComponent == null) {
                throw new NterCatalogCourseDependencyException(
                        "Could not find component from the IRI [" +
                                catalogComponentIri + "] in componentRef");
            }

            if (catalogCourse == null) {
                throw new NterCatalogCourseDependencyException(
                        "Could not find course IRI using courseID [" + courseId + "]");
            }

            return CatalogDataModelUtils.createCourses_Components(
                    catalogCourse.getCourseIri(), courseId, catalogComponentIri,
                    catalogComponent.getComponentId(), parserComponentRef.getOrderWeight(),
                    parserComponentRef.getSectionType(), parserComponentRef.getComponentType(),
                    parserComponentRef.getMimeType(), coursePaymentRequired,
                    parserComponentRef.getComponentPaymentRequired());
        }
        else {
            if ((catalogComponent == null) || (catalogCourse == null)) {

                if (catalogCourse != null) {
                    return CatalogDataModelUtils.createCourses_Components(
                            catalogCourse.getCourseIri(), courseId,
                            catalogComponentIri, parserComponentRef.getOrderWeight(),
                            parserComponentRef.getSectionType(), parserComponentRef.getComponentType(),
                            parserComponentRef.getMimeType(), coursePaymentRequired,
                            parserComponentRef.getComponentPaymentRequired());
                }

                if (catalogComponent != null) {
                    return CatalogDataModelUtils.createCourses_Components(
                            catalogCourse.getCourseIri(), catalogComponentIri,
                            catalogComponent.getComponentId(),
                            parserComponentRef.getOrderWeight(),
                            parserComponentRef.getSectionType(), parserComponentRef.getComponentType(),
                            parserComponentRef.getMimeType(), coursePaymentRequired,
                            parserComponentRef.getComponentPaymentRequired());
                }
                else {
                    return CatalogDataModelUtils.createCourses_Components(
                            catalogComponentIri, parserComponentRef.getOrderWeight(),
                            parserComponentRef.getSectionType(), parserComponentRef.getComponentType(),
                            parserComponentRef.getMimeType(), coursePaymentRequired,
                            parserComponentRef.getComponentPaymentRequired());
                }
            }
            else {
                return CatalogDataModelUtils.createCourses_Components(
                        catalogCourse.getCourseIri(), courseId, catalogComponentIri,
                        catalogComponent.getComponentId(), parserComponentRef.getOrderWeight(),
                        parserComponentRef.getSectionType(), parserComponentRef.getComponentType(),
                        parserComponentRef.getMimeType(), coursePaymentRequired,
                        parserComponentRef.getComponentPaymentRequired());
            }
        }
    }


    /**
     * Converts a list of image entries found in the atom feed into a list of
     * CourseImage objects.
     *
     * @param parserImages List of images from the Atom feed to be converted into
     * CourseImage objects.
     * @param courseId The courseId the images are associated with
     * @param setMissingOrderWeights - if true, the method will set any order
     * weights that are missing to a number equal to the link's position in the
     * list.  For example, if the 3rd link in the list does not have an order
     * weight, it will be set to 3.0 if this parameter is set to true.
     *
     * @return List of converted CourseImage objects
     */
    public static List<CourseImage> parserImagesToCatalog(List<NterImage> parserImages,
            long courseId, boolean setMissingOrderWeights) {

        List<CourseImage> catalogImages = new Vector<CourseImage>();
        for (int i = 0; i < parserImages.size(); i++) {

            NterImage parserImage = parserImages.get(i);
            CourseImage catalogImage = parserToCatalog(parserImage, courseId);

            // if we're setting missing order weights
            if (setMissingOrderWeights) {
                // make sure it's missing
                if (catalogImage.getOrderWeight() == 0) {
                    catalogImage.setOrderWeight(i);
                }
            }

            // add the image
            catalogImages.add(catalogImage);
        }

        return catalogImages;
    }


    /**
     * Converts a single image entry found in the Atom feed into a CourseImage
     * object.
     *
     * @param parserImage Atom feed entry representing an image.
     * @param courseId The id of the course the image is associated with
     *
     * @return A courseImage object
     */
    public static CourseImage parserToCatalog(NterImage parserImage, long courseId) {
        CourseImage catalogImage = new CourseImageImpl();
        catalogImage.setOrderWeight(parserImage.getOrderWeight());
        catalogImage.setLanguage(parserLangToCatalogLang(parserImage.getLanguage()));
        catalogImage.setAlternateText(parserImage.getAlt());
        catalogImage.setSourceImageUrl(parserImage.getHref());
        catalogImage.setMimeType(parserImage.getMimeType());
        catalogImage.setCourseId(courseId);

        return catalogImage;
    }


    /**
     * Converts a list of author atom entries into a list of Contributors for the
     * given entry and entry type.
     *
     * @param persons List of person entries found in the Atom entry
     * @param entryId - either the course or component database ID
     * @param entryType - NterEntryType.COURSE or COURSE_COMPONENT
     *
     * @return List of contributers based on the List of persons and entry
     */

    public static List<Contributor> parserAuthorsToCatalog(List<Person> persons,
            long entryId, NterEntryType entryType, StaticNterAtomParser parser) {

        return parserPersonsToCatalog(persons, ContributorRoleType.AUTHOR,
                entryId, entryType, parser);
    }


    /**
     * Converts a list of contributor atom entries into a list of Contributors for
     * the given entry and entry type.
     *
     * @param persons List of person entries found in the Atom entry
     * @param entryId - either the course or component database ID
     * @param entryType - NterEntryType.COURSE or COURSE_COMPONENT
     *
     * @return List of contributers based on the List of persons and entry
     */
    public static List<Contributor> parserContributorsToCatalog(List<Person> persons,
            long entryId, NterEntryType entryType, StaticNterAtomParser parser) {
        return parserPersonsToCatalog(persons, ContributorRoleType.CONTRIBUTOR,
                entryId, entryType, parser);
    }


    /**
     * Converts a list of Atom people entries (either authors or contributors) into
     * a list of Contributor objects.
     *
     * @param persons List of person entries to convert
     * @param role Their role (either author or contributor)
     * @param entryId - either the course or component database ID
     * @param entryType - NterEntryType.COURSE or COURSE_COMPONENT
     *
     * @return A List of Contributors
     */
    public static List<Contributor> parserPersonsToCatalog(List<Person> persons,
            ContributorRoleType role, long entryId, NterEntryType entryType,
            StaticNterAtomParser parser) {

        List<Contributor> contributors = new Vector<Contributor>();
        for (Person person : persons) {
            contributors.add(parserToCatalog(person, role, entryId, entryType, parser));
        }

        return contributors;
    }
    

    /**
     * Converts an Atom person entry (either authors or contributors) into a
     * Contributor object.
     *
     * @param person person entry to convert
     * @param role Their role (either author or contributor)
     * @param entryId - either the course or component database ID
     * @param entryType - NterEntryType.COURSE or COURSE_COMPONENT
     *
     * @return A Contributor
     */
    public static Contributor parserToCatalog(Person person, ContributorRoleType role,
                                       long entryId, NterEntryType entryType,
                                       StaticNterAtomParser parser) {

        Contributor courseContributor = new ContributorImpl();
        courseContributor.setContributorName(person.getName());
        courseContributor.setVirtualCardData(parser.getVCard(person));
        courseContributor.setRole(role.value());

        if (entryType.equals(NterEntryType.COURSE)) {
            courseContributor.setCourseId(entryId);
        }
        else if (entryType.equals(NterEntryType.COURSE_COMPONENT)) {
            courseContributor.setComponentId(entryId);
        }
        else {
            log.warn("Unhandled entry type [" + entryType + "] for contributor parsing");
        }

        return courseContributor;
    }

    /**
     * Converts a list of Nter Organizations into a List of Contributors.
     *
     * @param organizations List of organizations to convert
     * @param entryId - either the course or component database ID
     * @param entryType - NterEntryType.COURSE or COURSE_COMPONENT
     *
     * @return Converted Contributors
     */
    public static List<Contributor> parserOrganizationsToCatalog(
            List<NterOrganization> organizations, long entryId, NterEntryType entryType) {
        return parserOrganizationsToCatalog(organizations,
                ContributorRoleType.ORGANIZATION, entryId, entryType);
    }


    /**
     * Converts a list of Nter Organization objects into a list of contributor
     * objects.
     *
     * @param organizations List of organizations to convert
     * @param role The Type
     * @param entryId - either the course or component database ID
     * @param entryType - NterEntryType.COURSE or COURSE_COMPONENT
     *
     * @return List of contributor objects created from the organizations
     */
    public static List<Contributor> parserOrganizationsToCatalog(
            List<NterOrganization> organizations, ContributorRoleType role,
            long entryId, NterEntryType entryType) {

        List<Contributor> contributors = new Vector<Contributor>();
        for (NterOrganization organization : organizations) {
            contributors.add(parserToCatalog(organization, role, entryId, entryType));
        }

        return contributors;
    }


    /**
     * Converts an Nter Organization objects into a contributor object.
     *
     * @param organization Organization to convert
     * @param role The Type
     * @param entryId - either the course or component database ID
     * @param entryType - NterEntryType.COURSE or COURSE_COMPONENT
     *
     * @return contributor created from the organizations
     */
    public static Contributor parserToCatalog(NterOrganization organization,
            ContributorRoleType role, long entryId, NterEntryType entryType) {

        Contributor courseContributor = new ContributorImpl();
        courseContributor.setContributorName(organization.getText());
        // organization does not use virtualCardData
        courseContributor.setRole(role.value());
        if (entryType.equals(NterEntryType.COURSE)) {
            courseContributor.setCourseId(entryId);
        }
        else if (entryType.equals(NterEntryType.COURSE_COMPONENT)) {
            courseContributor.setComponentId(entryId);
        }
        else {
            log.warn("Unhandled entry type [" + entryType + "] for contributor parsing");
        }

        return courseContributor;
    }


    /**
     * Converts a list of NterRelated model objects into a list of Catalog
     * CourseRelated objects.
     *
     * @param parserRelateds List of NterRelated objects to convert
     * @param courseId Associated CourseId for the objects
     * @param dependenciesArePersisted - if true, this method assumes all
     * dependencies are persisted and  throws an NterCatalogCourseDependencyException
     * if the dependencies are not found in persistence.  If false, it ignores any
     * missing dependencies.
     *
     * @return Converted CourseRelated objects.
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay DBO exception
     * @throws NterCatalogCourseDependencyException Thrown if
     * dependenciesArePersisted is true, and the required dependencies are not
     * found.
     */
    public static List<CourseRelated> parserRelatedsToCatalog(List<NterRelated> parserRelateds,
            long courseId, boolean dependenciesArePersisted)
            throws SystemException, NterCatalogCourseDependencyException {

        List<CourseRelated> catalogRelateds = new Vector<CourseRelated>();
        for (NterRelated parserRelated : parserRelateds) {
            CourseRelated related = parserToCatalog(parserRelated, courseId,
                    dependenciesArePersisted);
            if (related != null) {
                catalogRelateds.add(related);
            }
        }

        return catalogRelateds;
    }


    /**
     * Converts a list of NterRelated model objects into a list of Catalog
     * CourseRelated objects.
     *
     * @param parserRelated NterRelated object to convert
     * @param courseId Associated CourseId for the objects
     * @param dependenciesArePersisted - if true, this method assumes all
     * dependencies are persisted and  throws an NterCatalogCourseDependencyException
     * if the dependencies are not found in persistence.  If false, it ignores any
     * missing dependencies.
     *
     * @return Converted CourseRelated object.
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay DBO exception
     * @throws NterCatalogCourseDependencyException Thrown if
     * dependenciesArePersisted is true, and the required dependencies are not
     * found.
     */
    public static CourseRelated parserToCatalog(NterRelated parserRelated,
            long courseId, boolean dependenciesArePersisted)
            throws SystemException, NterCatalogCourseDependencyException {

        String catalogCourseIri = parserRelated.getRelatedEntryId().trim();
        Course relatedCourse = CourseLocalServiceUtil.fetchByCourseIri(catalogCourseIri);
                                                                // if we're told dependencies should be there
        if (dependenciesArePersisted) {
            if (relatedCourse == null) {
                throw new NterCatalogCourseDependencyException(
                        "Could not find related course from the IRI ["
                                + catalogCourseIri + "]");
            }

            return CatalogDataModelUtils.createCourseRelated(courseId,
                    relatedCourse.getCourseId(), catalogCourseIri,
                    parserRelated.getRelationship().toString());
        }
        else {
            if (relatedCourse == null) {
                return CatalogDataModelUtils.createCourseRelated(courseId,
                        catalogCourseIri, parserRelated.getRelationship().toString());
            }
            else {
                return CatalogDataModelUtils.createCourseRelated(courseId,
                        relatedCourse.getCourseId(), catalogCourseIri,
                        parserRelated.getRelationship().toString());
            }
        }
    }


    /**
     * Converts a list of NterRequirement objects into CourseRequirement objects.
     *
     * @param parserRequirements List of NterRequirements
     * @param courseId CourseId the requirements are associated with
     *
     * @return List of CourseRequirement objects
     */
    public static List<CourseRequirement> parserRequirementsToCatalog(
            List<NterRequirement> parserRequirements, long courseId) {

        List<CourseRequirement> catalogRequirements = new Vector<CourseRequirement>();
        for (NterRequirement parserRequirement : parserRequirements) {
            catalogRequirements.add(parserToCatalog(parserRequirement, courseId));
        }

        return catalogRequirements;
    }


    /**
     * Converts a NterRequirement object into a CourseRequirement object.
     *
     * @param parserRequirement List of NterRequirements
     * @param courseId CourseId the requirements are associated with
     *
     * @return CourseRequirement object
     */
    public static CourseRequirement parserToCatalog(
            NterRequirement parserRequirement, long courseId) {

        CourseRequirement catalogRequirement = new CourseRequirementImpl();
        catalogRequirement.setCourseId(courseId);
        Locale locale = parserLangToCatalogLocale(parserRequirement.getLanguage());
        catalogRequirement.setRequirementType(parserRequirement.getRequirementType());
        catalogRequirement.setRequirementValue(parserRequirement.getText().toString(), locale);

        return catalogRequirement;
    }


    /**
     * Converts a list of NterComponentRecord objects into ComponentRecords to
     * be stored in the database.
     *
     * @param parserComponentRecords Original record object to convert
     * @param courseRecordId Associated courseRecordId
     * @param dependenciesArePersisted if true, this method assumes all
     * dependencies are persisted and throws an NterCatalogCourseDependencyException
     * if the dependencies are not found in persistence.  If false, it ignores
     * any missing dependencies.
     *
     * @return Converted list of ComponentRecord objects
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay DBO Exception
     * @throws NterCatalogRecordDependencyException Thrown when
     * dependenciesArePersisted is true and the required dependencies are not
     * found.
     */
    public static List<ComponentRecord> parserComponentRecordsToCatalog(
            List<NterComponentRecord> parserComponentRecords, long courseRecordId,
            boolean dependenciesArePersisted)
            throws SystemException, NterCatalogRecordDependencyException {

        List<ComponentRecord> catalogComponentRecords = new Vector<ComponentRecord>();
        for (NterComponentRecord parserComponentRecord : parserComponentRecords) {
            catalogComponentRecords.add(
                    parserComponentRecordToCatalog(parserComponentRecord,
                            courseRecordId, dependenciesArePersisted));
        }

        return catalogComponentRecords;
    }


    /**
     * Converts a NterComponentRecord object into a ComponentRecord to be stored in
     * the database.
     *
     * @param parserComponentRecord The NterComponentRecord to convert to a
     * ComponentRecord object
     * @param courseRecordId Id of the CourseRecord containing the entry
     * @param dependenciesArePersisted - if true, this method assumes all
     * dependencies are persisted and throws an NterCatalogCourseDependencyException
     * if the dependencies are not found in persistence.  If false, it ignores any
     * missing dependencies.
     *
     * @return Student ComponentRecord object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - Standard Liferay Exception
     * @throws NterCatalogRecordDependencyException - Thrown when required
     * dependencies are not available
     */
    public static ComponentRecord parserComponentRecordToCatalog(
            NterComponentRecord parserComponentRecord,
            long courseRecordId, boolean dependenciesArePersisted)
            throws SystemException, NterCatalogRecordDependencyException {

        String catalogComponentIri = parserComponentRecord.getComponentId();

        Component component = ComponentLocalServiceUtil.fetchByComponentIri(catalogComponentIri);

        if (dependenciesArePersisted && (component == null)) {
            throw new NterCatalogRecordDependencyException(
                    "Could not find related component from the IRI ["
                            + catalogComponentIri + "]");
        }

        // return component regardless of whether or not component exists
        return CatalogDataModelUtils.createComponentRecord(courseRecordId,
                catalogComponentIri, parserComponentRecord.getProgressDate(),
                parserComponentRecord.getCompletionPercent(),
                parserComponentRecord.getProgress());
    }


    /**
     * XML requires language abbreviations to be separated from their country with
     * a hyphen (e.g., "en-US"), but Liferay (or possibly Java) requires an
     * underscore (e.g., "en_US").  This method converts from the parser/hyphen way
     * to the Liferay/underscore way.
     *
     * @param langId String representing an object's language
     *
     * @return Liferay compliant representation of the language
     */
    private static String parserLangToCatalogLang(String langId) {
        return (langId == null) ? null : langId.replace('-', '_');
    }


    /**
     * Converts from a language in a parser object to a Locale in a catalog
     * object.
     *
     * @param langId original language
     *
     * @return Locale based on the provided String
     */
    private static Locale parserLangToCatalogLocale(String langId) {
        return LocaleUtil.fromLanguageId(parserLangToCatalogLang(langId));
    }
}