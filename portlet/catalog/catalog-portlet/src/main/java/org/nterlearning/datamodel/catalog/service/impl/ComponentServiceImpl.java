package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.NoSuchComponentException;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.ComponentServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the component remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.ComponentService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.base.ComponentServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.ComponentServiceUtil
 */
public class ComponentServiceImpl extends ComponentServiceBaseImpl {

    public List<Component> findByCompanyId(long companyId)
            throws SystemException {
        return ComponentLocalServiceUtil.findByCompanyId(companyId);
    }


    public Component findByComponentId(long componentId)
            throws NoSuchComponentException, SystemException {
        return ComponentLocalServiceUtil.findByComponentId(componentId);
    }


    public Component findByComponentIri(String componentIri)
            throws NoSuchComponentException, SystemException {
        return ComponentLocalServiceUtil.findByComponentIri(componentIri);
    }


    public List<Component> findByFeedReferenceId(Long feedRefId)
            throws SystemException {
        return ComponentLocalServiceUtil.findByFeedReferenceId(feedRefId);
    }
}
