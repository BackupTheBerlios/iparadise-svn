package com.pdpsoft.security;

import com.pdpsoft.PdpSoftCustomEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sajedi
 * Date: Apr 28, 2009
 * Time: 10:15:16 PM
 */
public class SystemUserCustomEntity extends PdpSoftCustomEntity {
    /**
     * It holds the user
     */
    private SystemUserEntity systemUserEntity;

    /**
     * It holds the groups that this user already have them
     */
    private List<SystemGroupEntity> systemGroupEntities;

     /**
     * It holds the groups that this user already have them and checked manager
     */
     private List<SystemGroupCustomEntity> systemGroupCustomEntities;

    /**
     * It holds the group name of user
     */
    private String userGroupName;

    //getter and setter
    public SystemUserCustomEntity() {
    }

    public SystemUserEntity getSystemUserEntity() {
        return systemUserEntity;
    }

    public void setSystemUserEntity(SystemUserEntity systemUserEntity) {
        this.systemUserEntity = systemUserEntity;
    }

    public List<SystemGroupEntity> getSystemGroupEntities() {
        return systemGroupEntities;
    }

    public void setSystemGroupEntities(List<SystemGroupEntity> systemGroupEntities) {
        this.systemGroupEntities = systemGroupEntities;
    }

    public List<SystemGroupCustomEntity> getSystemGroupCustomEntities() {
        return systemGroupCustomEntities;
    }

    public void setSystemGroupCustomEntities(List<SystemGroupCustomEntity> systemGroupCustomEntities) {
        this.systemGroupCustomEntities = systemGroupCustomEntities;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }
}