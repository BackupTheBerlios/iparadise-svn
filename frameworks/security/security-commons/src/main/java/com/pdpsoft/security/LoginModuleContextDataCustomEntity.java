package com.pdpsoft.security;

import com.pdpsoft.commons.G16ParentCustomEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 30, 2009
 * Time: 7:39:25 PM
 * I used this class instead of to send map over tiers.
 *  This XML part is going to map to this class
 *  <schema nameKey="11" schema-name="22" />
 */
public class LoginModuleContextDataCustomEntity extends G16ParentCustomEntity {
    /*
        This is the value part of XML, or it is the key parameter in Map<key, value)
     */
    private String schemaName;
    /*
        This is the nameKey of XML
     */
    private String nameKey;
    /*
        This property should have value whenever we use it in web
     */
    private String message;
    /*
        this is the code of the city
     */
    private String cityCode;

    public LoginModuleContextDataCustomEntity(String schemaName, String nameKey, String cityCode) {
        this.schemaName = schemaName;
        this.nameKey = nameKey;
        this.cityCode = cityCode;
    }

    public LoginModuleContextDataCustomEntity(String schemaName, String nameKey) {
        this.schemaName = schemaName;
        this.nameKey = nameKey;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
