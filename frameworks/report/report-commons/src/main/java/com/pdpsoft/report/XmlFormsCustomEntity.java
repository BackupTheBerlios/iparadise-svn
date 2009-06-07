package com.pdpsoft.report;

import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 4, 2009
 * Time: 4:26:52 PM
 */
public class XmlFormsCustomEntity extends G16ParentCustomEntity {
    private List<XmlFormCustomEntity> xmlFormCustomEntities;
    private Map<String, XmlFormCustomEntity> stringXmlFormCustomEntityMap;

    public XmlFormsCustomEntity() {
        xmlFormCustomEntities = new ArrayList<XmlFormCustomEntity>();
        stringXmlFormCustomEntityMap = new HashMap<String, XmlFormCustomEntity>();
    }

    /**
     * it adds elements to the list of forms
     * @param entity this is the input forms
     */
    public void addForm(XmlFormCustomEntity entity) {
        if(stringXmlFormCustomEntityMap.containsKey(entity.getId()))
            throw new RuntimeException(entity.getId() + " key id duplicated !!!");
        xmlFormCustomEntities.add(entity);
        stringXmlFormCustomEntityMap.put(entity.getId(), entity);
    }

    public List<XmlFormCustomEntity> getXmlFormCustomEntities() {
        return xmlFormCustomEntities;
    }

    public void setXmlFormCustomEntities(List<XmlFormCustomEntity> xmlFormCustomEntities) {
        this.xmlFormCustomEntities = xmlFormCustomEntities;
    }

    public Map<String, XmlFormCustomEntity> getStringXmlFormCustomEntityMap() {
        return stringXmlFormCustomEntityMap;
    }
}
