package com.pdpsoft.security;

import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 28, 2009
 * Time: 3:14:29 PM
 * It is root element of xml: <login-modules> ... </login-modules>
 */
public class LoginModulesCustomEntity extends G16ParentCustomEntity {
    private List<LoginModuleCustomEntity> loginModuleCustomEntities;

    public LoginModulesCustomEntity() {
        loginModuleCustomEntities = new ArrayList<LoginModuleCustomEntity>();
    }

    /**
     * will be called by digester
     * @param entity the mapping of XML
     */
    public void addLoginModuleCustomEntity(LoginModuleCustomEntity entity) {
        loginModuleCustomEntities.add(entity);
    }

    public List<LoginModuleCustomEntity> getLoginModuleCustomEntities() {
        return loginModuleCustomEntities;
    }

    public void setLoginModuleCustomEntities(List<LoginModuleCustomEntity> loginModuleCustomEntities) {
        this.loginModuleCustomEntities = loginModuleCustomEntities;
    }
}
