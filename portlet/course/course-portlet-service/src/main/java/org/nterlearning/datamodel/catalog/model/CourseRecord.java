package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the CourseRecord service. Represents a row in the &quot;CATALOG_CourseRecord&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CourseRecordModel
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseRecordModelImpl
 * @generated
 */
public interface CourseRecord extends CourseRecordModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.lang.String getFriendlyUpdatedDate(
        javax.servlet.jsp.PageContext pageContext);
}
