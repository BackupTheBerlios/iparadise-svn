package com.pdpsoft.security;

import com.pdpsoft.PdpSoftCustomEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 27, 2009
 * Time: 2:35:16 PM
 */
public class AuthorizationCustomEntity extends PdpSoftCustomEntity {
    /**
     * It holds the user that has been recently logged in
     */
    private SystemUserEntity systemUserEntity;

    /**
     * It holds the actions that this user already have them
     */
    private List<SystemActionEntity> systemActionEntities;

    public AuthorizationCustomEntity() {
    }

    public AuthorizationCustomEntity(SystemUserEntity systemUserEntity, List<SystemActionEntity> systemActionEntities) {
        this.systemUserEntity = systemUserEntity;
        this.systemActionEntities = systemActionEntities;
    }

    public SystemUserEntity getSystemUserEntity() {
        return systemUserEntity;
    }

    public void setSystemUserEntity(SystemUserEntity systemUserEntity) {
        this.systemUserEntity = systemUserEntity;
    }

    public List<SystemActionEntity> getSystemActionEntities() {
        return systemActionEntities;
    }

    public void setSystemActionEntities(List<SystemActionEntity> systemActionEntities) {
        this.systemActionEntities = systemActionEntities;
    }
}
