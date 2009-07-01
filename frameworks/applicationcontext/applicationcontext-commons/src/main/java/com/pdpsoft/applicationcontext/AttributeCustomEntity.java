package com.pdpsoft.applicationcontext;

import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Naziq and ChrisShayan.com
 * Date: May 17, 2009
 * Time: 5:02:55 PM
 */
public class AttributeCustomEntity extends G16ParentCustomEntity {
    /*
        It's the property name of the class that is has been introduced
        as a application data.
     */
    private String propertyName;
    /*
        All of within expressions should map to this field.
        <dto classname="Customer">
            <attribute propertyName="status">
                <expression value="0" bundleKey="Active"/>
                <expression value="1" bundleKey="Inactive"/>
            </attribute>
        </dto>
        The key of map is value in xml;
        the value of map is bundleKey.
     */
    Map<String, String> map;
    Map<String, String> sort;


    public AttributeCustomEntity() {
        map = new HashMap<String, String>();
        sort = new HashMap<String, String>();
    }

    public AttributeCustomEntity(String propertyName, Map<String, String> map) {
        this.propertyName = propertyName;
        this.map = map;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getSort() {
        return sort;
    }

    public void setSort(Map<String, String> sort) {
        this.sort = sort;
    }

    public void addMap(String value, String bundleKey, String sortItem) {
        if(map.containsKey(value))
            throw new RuntimeException("There is dublicate in your application context data xml file, the value is:" + value
            + " and the bundlekey is:" + bundleKey + ". The property is:" + propertyName);
        map.put(value, bundleKey);
        sort.put(value, sortItem);
    }
}
