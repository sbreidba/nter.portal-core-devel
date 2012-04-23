package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.Courses_Components;

import java.io.Serializable;

/**
 * The cache model class for representing Courses_Components in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Courses_Components
 * @generated
 */
public class Courses_ComponentsCacheModel implements CacheModel<Courses_Components>,
    Serializable {
    public long coursesComponentsId;
    public long courseId;
    public String courseIri;
    public long componentId;
    public String componentIri;
    public double orderWeight;
    public String sectionType;
    public String componentType;
    public String mimeType;
    public boolean coursePaymentRequired;
    public boolean componentPaymentRequired;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{coursesComponentsId=");
        sb.append(coursesComponentsId);
        sb.append(", courseId=");
        sb.append(courseId);
        sb.append(", courseIri=");
        sb.append(courseIri);
        sb.append(", componentId=");
        sb.append(componentId);
        sb.append(", componentIri=");
        sb.append(componentIri);
        sb.append(", orderWeight=");
        sb.append(orderWeight);
        sb.append(", sectionType=");
        sb.append(sectionType);
        sb.append(", componentType=");
        sb.append(componentType);
        sb.append(", mimeType=");
        sb.append(mimeType);
        sb.append(", coursePaymentRequired=");
        sb.append(coursePaymentRequired);
        sb.append(", componentPaymentRequired=");
        sb.append(componentPaymentRequired);
        sb.append("}");

        return sb.toString();
    }

    public Courses_Components toEntityModel() {
        Courses_ComponentsImpl courses_ComponentsImpl = new Courses_ComponentsImpl();

        courses_ComponentsImpl.setCoursesComponentsId(coursesComponentsId);
        courses_ComponentsImpl.setCourseId(courseId);

        if (courseIri == null) {
            courses_ComponentsImpl.setCourseIri(StringPool.BLANK);
        } else {
            courses_ComponentsImpl.setCourseIri(courseIri);
        }

        courses_ComponentsImpl.setComponentId(componentId);

        if (componentIri == null) {
            courses_ComponentsImpl.setComponentIri(StringPool.BLANK);
        } else {
            courses_ComponentsImpl.setComponentIri(componentIri);
        }

        courses_ComponentsImpl.setOrderWeight(orderWeight);

        if (sectionType == null) {
            courses_ComponentsImpl.setSectionType(StringPool.BLANK);
        } else {
            courses_ComponentsImpl.setSectionType(sectionType);
        }

        if (componentType == null) {
            courses_ComponentsImpl.setComponentType(StringPool.BLANK);
        } else {
            courses_ComponentsImpl.setComponentType(componentType);
        }

        if (mimeType == null) {
            courses_ComponentsImpl.setMimeType(StringPool.BLANK);
        } else {
            courses_ComponentsImpl.setMimeType(mimeType);
        }

        courses_ComponentsImpl.setCoursePaymentRequired(coursePaymentRequired);
        courses_ComponentsImpl.setComponentPaymentRequired(componentPaymentRequired);

        courses_ComponentsImpl.resetOriginalValues();

        return courses_ComponentsImpl;
    }
}
