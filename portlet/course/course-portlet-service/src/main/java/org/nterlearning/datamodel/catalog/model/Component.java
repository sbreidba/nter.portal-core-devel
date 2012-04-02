package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Component service. Represents a row in the &quot;CATALOG_Component&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ComponentModel
 * @see org.nterlearning.datamodel.catalog.model.impl.ComponentImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.ComponentModelImpl
 * @generated
 */
public interface Component extends ComponentModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link org.nterlearning.datamodel.catalog.model.impl.ComponentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.lang.String getFriendlyVersionDate(
        javax.servlet.jsp.PageContext pageContext);

    public java.lang.String getFriendlyUpdateDate(
        javax.servlet.jsp.PageContext pageContext);

    public java.lang.String getSearchContext();

    public void setSearchContext(java.lang.String searchContext);

    public float getSearchRelevance();

    public void setSearchRelevance(float searchRelevance);

    public java.lang.String getUrl()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getUrl(java.lang.String lang)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public boolean isPurchased(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void updateIndex();
}
