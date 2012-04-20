package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the ComponentRecord service. Represents a row in the &quot;CATALOG_ComponentRecord&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ComponentRecordModel
 * @see org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl
 * @generated
 */
public interface ComponentRecord extends ComponentRecordModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.lang.String getFriendlyUpdatedDate(
        javax.servlet.jsp.PageContext pageContext);
}
