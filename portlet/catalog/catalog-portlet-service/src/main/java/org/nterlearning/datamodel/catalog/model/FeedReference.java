package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the FeedReference service. Represents a row in the &quot;CATALOG_FeedReference&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FeedReferenceModel
 * @see org.nterlearning.datamodel.catalog.model.impl.FeedReferenceImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.FeedReferenceModelImpl
 * @generated
 */
public interface FeedReference extends FeedReferenceModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link org.nterlearning.datamodel.catalog.model.impl.FeedReferenceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Determines the correct name of the owner, regardless of whether or not
    * the owning group is a group, community, organization, or company.
    *
    * Typically, returning the name of the group will only return the primary
    * key in the corresponding owner table.
    *
    * @return The name of the owning entity.
    */
    public java.lang.String getOwnerName();

    /**
    * Parses out the 'tag:...' portion of the Feed IRI.
    *
    * @return The tag substring of the Feed IRI
    */
    public java.lang.String getTagSubstring();
}
