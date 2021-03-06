package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;

/**
 * The extended model base implementation for the FeedReference service. Represents a row in the &quot;CATALOG_FeedReference&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FeedReferenceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedReferenceImpl
 * @see org.nterlearning.datamodel.catalog.model.FeedReference
 * @generated
 */
public abstract class FeedReferenceBaseImpl extends FeedReferenceModelImpl
    implements FeedReference {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a feed reference model instance should use the {@link FeedReference} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            FeedReferenceLocalServiceUtil.addFeedReference(this);
        } else {
            FeedReferenceLocalServiceUtil.updateFeedReference(this);
        }
    }
}
