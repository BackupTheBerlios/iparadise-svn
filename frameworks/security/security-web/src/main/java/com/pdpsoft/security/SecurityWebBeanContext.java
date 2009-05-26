package com.pdpsoft.security;

import com.pdpsoft.web.common.WebBeanContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 20, 2009
 * Time: 10:11:55 AM
 */
public class SecurityWebBeanContext  extends WebBeanContext {
    List<SystemUserEntity> systemUserEntities;
    List<SystemGroupEntity> systemGroupEntities;
    List<SystemActionEntity> systemActionEntities;
    AuthorizationCustomEntity authorizationCustomEntity;

    public List<SystemUserEntity> getSystemUserEntities() {
        return systemUserEntities;
    }

    public void setSystemUserEntities(List<SystemUserEntity> systemUserEntities) {
        this.systemUserEntities = systemUserEntities;
    }

    public List<SystemGroupEntity> getSystemGroupEntities() {
        return systemGroupEntities;
    }

    public void setSystemGroupEntities(List<SystemGroupEntity> systemGroupEntities) {
        this.systemGroupEntities = systemGroupEntities;
    }

    public List<SystemActionEntity> getSystemActionEntities() {
        return systemActionEntities;
    }

    public void setSystemActionEntities(List<SystemActionEntity> systemActionEntities) {
        this.systemActionEntities = systemActionEntities;
    }

    public AuthorizationCustomEntity getAuthorizationCustomEntity() {
        return authorizationCustomEntity;
    }

    public void setAuthorizationCustomEntity(AuthorizationCustomEntity authorizationCustomEntity) {
        this.authorizationCustomEntity = authorizationCustomEntity;
    }
}
