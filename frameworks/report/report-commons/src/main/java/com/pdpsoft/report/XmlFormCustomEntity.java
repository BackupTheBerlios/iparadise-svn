package com.pdpsoft.report;

import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 3, 2009
 * Time: 8:08:32 PM
 * this object ought to map to <forms... form id="" dto="" filePath="" />
 */
public class XmlFormCustomEntity extends G16ParentCustomEntity {
    private String id;
    private String dto;
    private String filePath;

    private List<XmlFormParameterCustomEntity> xmlFormParameterCustomEntities;

    public XmlFormCustomEntity() {
        xmlFormParameterCustomEntities = new ArrayList<XmlFormParameterCustomEntity>();
    }

    /**
     * it adds elements to the list of parameters
     * @param entity this is the input parameter
     */
    public void addParameter(XmlFormParameterCustomEntity entity) {
        xmlFormParameterCustomEntities.add(entity);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDto() {
        return dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<XmlFormParameterCustomEntity> getXmlFormParameterCustomEntities() {
        return xmlFormParameterCustomEntities;
    }

    public void setXmlFormParameterCustomEntities(List<XmlFormParameterCustomEntity> xmlFormParameterCustomEntities) {
        this.xmlFormParameterCustomEntities = xmlFormParameterCustomEntities;
    }
}
