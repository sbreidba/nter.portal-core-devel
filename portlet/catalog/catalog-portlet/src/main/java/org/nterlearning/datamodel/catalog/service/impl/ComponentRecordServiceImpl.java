package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;
import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.ComponentRecordServiceBaseImpl;

import java.util.List;
import java.util.Locale;

/**
 * The implementation of the component record remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.ComponentRecordService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.base.ComponentRecordServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.ComponentRecordServiceUtil
 */
public class ComponentRecordServiceImpl extends ComponentRecordServiceBaseImpl {

    public List<ComponentRecord> findByComponentIri(String componentIri)
            throws NoSuchComponentRecordException, SystemException {
        return ComponentRecordLocalServiceUtil.findByComponentIri(componentIri);
    }

    public List<ComponentRecord> findByCourseRecordId(long courseRecordId)
            throws SystemException {
        return ComponentRecordLocalServiceUtil.findByCourseRecordId(courseRecordId);
    }
}
