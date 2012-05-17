package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.FeedReferenceServiceBaseImpl;

/**
 * The implementation of the feed reference remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.FeedReferenceService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.base.FeedReferenceServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.FeedReferenceServiceUtil
 */
public class FeedReferenceServiceImpl extends FeedReferenceServiceBaseImpl {


    public FeedReference findByFeedIri(String feedIri)
            throws NoSuchFeedReferenceException, SystemException {
        return FeedReferenceLocalServiceUtil.findByFeedIri(feedIri);
    }

    public FeedReference findByFeedHref(String href)
            throws NoSuchFeedReferenceException, SystemException {
        return FeedReferenceLocalServiceUtil.findByFeedHref(href);
    }

}
