package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.service.Courses_ComponentsLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.Courses_ComponentsServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the courses_ components remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.Courses_ComponentsService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.base.Courses_ComponentsServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.Courses_ComponentsServiceUtil
 */
public class Courses_ComponentsServiceImpl extends Courses_ComponentsServiceBaseImpl {

    public List<Courses_Components> findByCourseId(Long courseId)
            throws SystemException {
        return Courses_ComponentsLocalServiceUtil.findByCourseId(courseId);
    }

    public List<Courses_Components> findByCourseIri(String courseIri)
            throws SystemException {
        return Courses_ComponentsLocalServiceUtil.findByCourseIri(courseIri);
    }

    public List<Courses_Components> findByComponentId(Long componentId)
            throws SystemException {
        return Courses_ComponentsLocalServiceUtil.findByComponentId(componentId);
    }

    public List<Courses_Components> findByComponentIri(String componentIri)
            throws SystemException {
        return Courses_ComponentsLocalServiceUtil.findByComponentIri(componentIri);
    }
}
