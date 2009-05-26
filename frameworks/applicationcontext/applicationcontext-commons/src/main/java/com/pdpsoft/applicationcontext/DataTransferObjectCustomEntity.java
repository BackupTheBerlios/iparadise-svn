package com.pdpsoft.applicationcontext;

import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Naziq and ChrisShayan.com
 * Date: May 17, 2009
 * Time: 4:54:03 PM
 */
public class DataTransferObjectCustomEntity extends G16ParentCustomEntity {
    /*
        It is the full qualified class name of the object,
        such as: com.pdpsoft.securty.SystemUserEntity
        Please keep in mind the FULL address is needed.
     */
    private String fullQualifiedClassName;
    /*
        List of attributes of the specified entity.
     */
    private List<AttributeCustomEntity> attributeCustomEntityList;

    public DataTransferObjectCustomEntity() {
        attributeCustomEntityList = new ArrayList<AttributeCustomEntity>();
    }

    public DataTransferObjectCustomEntity(String fullQualifiedClassName, List<AttributeCustomEntity> attributeCustomEntityList) {
        this.fullQualifiedClassName = fullQualifiedClassName;
        this.attributeCustomEntityList = attributeCustomEntityList;
    }

    public void addAttributeCustomEntity(AttributeCustomEntity entity) {
        attributeCustomEntityList.add(entity);
    }
    public String getFullQualifiedClassName() {
        return fullQualifiedClassName;
    }

    public void setFullQualifiedClassName(String fullQualifiedClassName) {
        this.fullQualifiedClassName = fullQualifiedClassName;
    }

    public List<AttributeCustomEntity> getAttributeCustomEntityList() {
        return attributeCustomEntityList;
    }

    public void setAttributeCustomEntityList(List<AttributeCustomEntity> attributeCustomEntityList) {
        this.attributeCustomEntityList = attributeCustomEntityList;
    }
}
