package com.pdpsoft.applicationcontext;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Naziq and ChrisShayan.com
 * Date: May 17, 2009
 * Time: 5:06:41 PM
 * @deprecated No need anymore to the class of ExpressCustomEntity
 */
public class ExpressionCustomEntity {
    /*
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

    public ExpressionCustomEntity() {
    }

    public ExpressionCustomEntity(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
