package com.pdpsoft.applicationcontext;
import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Naziq and ChrisShayan.com
 * Date: May 17, 2009
 * Time: 4:50:23 PM
 */
public final class ApplicationDataCustomEntity extends G16ParentCustomEntity {
    /*
        All of objects in application data are mapped to this property.
     */
    private List<DataTransferObjectCustomEntity> dataTransferObjectCustomEntities;

    public ApplicationDataCustomEntity() {
        dataTransferObjectCustomEntities = new ArrayList<DataTransferObjectCustomEntity>();
    }

    public void addDataTransferObjectCustomEntity(DataTransferObjectCustomEntity entity) {
        dataTransferObjectCustomEntities.add(entity);
    }

    public ApplicationDataCustomEntity(List<DataTransferObjectCustomEntity> dataTransferObjectCustomEntities) {
        this.dataTransferObjectCustomEntities = dataTransferObjectCustomEntities;
    }

    public List<DataTransferObjectCustomEntity> getStructureCustomEntities() {
        return dataTransferObjectCustomEntities;
    }

    public void setStructureCustomEntities(List<DataTransferObjectCustomEntity> dataTransferObjectCustomEntities) {
        this.dataTransferObjectCustomEntities = dataTransferObjectCustomEntities;
    }
}
