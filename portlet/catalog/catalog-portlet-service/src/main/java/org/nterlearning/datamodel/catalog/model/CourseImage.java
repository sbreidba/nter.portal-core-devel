package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the CourseImage service. Represents a row in the &quot;CATALOG_CourseImage&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CourseImageModel
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl
 * @generated
 */
public interface CourseImage extends CourseImageModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.lang.String getLargeImageUrl(
        com.liferay.portal.theme.ThemeDisplay themeDisplay);

    public java.lang.String getMediumImageUrl(
        com.liferay.portal.theme.ThemeDisplay themeDisplay);

    public java.lang.String getSmallImageUrl(
        com.liferay.portal.theme.ThemeDisplay themeDisplay);
}
