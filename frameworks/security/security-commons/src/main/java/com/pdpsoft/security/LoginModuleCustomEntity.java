package com.pdpsoft.security;

import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 28, 2009
 * Time: 3:17:29 PM
 * It handles the tag <login-module ...> ... </login-module>
 */
public class LoginModuleCustomEntity extends G16ParentCustomEntity {
    // it is database schema name that we should login should be occured at it.
    private String defaultSchemaName;
    // list of schemas that user can be changed them.
    private List<LoginSchemasCustomEntity> loginSchemasCustomEntities;
    // name of hibernate configuration file
    private String hibernateConfigurationName;
    // this is the name of default code
    private String defaultCityCode;

    public LoginModuleCustomEntity() {
        loginSchemasCustomEntities = new ArrayList<LoginSchemasCustomEntity>();
    }


    public String getDefaultSchemaName() {
        return defaultSchemaName;
    }

    public void setDefaultSchemaName(String defaultSchemaName) {
        this.defaultSchemaName = defaultSchemaName;
    }

    public List<LoginSchemasCustomEntity> getLoginSchemasCustomEntities() {
        return loginSchemasCustomEntities;
    }

    public void setLoginSchemasCustomEntities(List<LoginSchemasCustomEntity> loginSchemasCustomEntities) {
        this.loginSchemasCustomEntities = loginSchemasCustomEntities;
    }

    public String getHibernateConfigurationName() {
        return hibernateConfigurationName;
    }

    public void setHibernateConfigurationName(String hibernateConfigurationName) {
        this.hibernateConfigurationName = hibernateConfigurationName;
    }

    public String getDefaultCityCode() {
        return defaultCityCode;
    }

    public void setDefaultCityCode(String defaultCityCode) {
        this.defaultCityCode = defaultCityCode;
    }

    /**
     * will be called by digester
     * @param entity the mapping of XML
     */
    public void addLoginSchemasCustomEntity(LoginSchemasCustomEntity entity) {
        loginSchemasCustomEntities.add(entity);
    }
}
