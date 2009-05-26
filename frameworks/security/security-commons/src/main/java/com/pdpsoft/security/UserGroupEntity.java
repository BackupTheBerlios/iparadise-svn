package com.pdpsoft.security;

import com.pdpsoft.PdpSoftEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 2:16:50 PM
 */
public class UserGroupEntity extends PdpSoftEntity {
    /**
     * Identifier of this entity
     */
    private Integer userGroupId;

    /**
     * Properties of this entity
     */
    /**
     * It's an enumeration hence there's not any
     * usual setter of this object, you ought to
     * use the ManagerTypeEnumeration.
     */
    private String manager;

    /**
     * Below you will find relations with
     * other objects
     */
    private SystemGroupEntity systemGroupEntity;
    private SystemUserEntity systemUserEntity;

    /**
     * Determine the status of management of current user;
     */
    public static enum ManagerEnumeration {
        MANAGER, NOT_A_MANAGER
    }

    /**
     * default constructor
     */
    public UserGroupEntity() {
    }

    /**
     * @param userGroupId userGroupId
     */
    public UserGroupEntity(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * @param manager           manager
     * @param systemGroupEntity systemGroupEntity
     * @param systemUserEntity  systemUserEntity
     */
    public UserGroupEntity(ManagerEnumeration manager, SystemGroupEntity systemGroupEntity, SystemUserEntity systemUserEntity) {
        setManager(manager);
        this.systemGroupEntity = systemGroupEntity;
        this.systemUserEntity = systemUserEntity;
    }

    /**
     * @param userGroupId       userGroupId
     * @param manager           manager
     * @param systemGroupEntity systemGroupEntity
     * @param systemUserEntity  systemUserEntity
     */
    public UserGroupEntity(Integer userGroupId, ManagerEnumeration manager, SystemGroupEntity systemGroupEntity, SystemUserEntity systemUserEntity) {
        this.userGroupId = userGroupId;
        setManager(manager);
        this.systemGroupEntity = systemGroupEntity;
        this.systemUserEntity = systemUserEntity;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public SystemGroupEntity getSystemGroupEntity() {
        return systemGroupEntity;
    }

    public void setSystemGroupEntity(SystemGroupEntity systemGroupEntity) {
        this.systemGroupEntity = systemGroupEntity;
    }

    public SystemUserEntity getSystemUserEntity() {
        return systemUserEntity;
    }

    public void setSystemUserEntity(SystemUserEntity systemUserEntity) {
        this.systemUserEntity = systemUserEntity;
    }

    public void setManager(ManagerEnumeration enumeration) {
        if (enumeration.equals(ManagerEnumeration.MANAGER))
            this.manager = "M";
        if (enumeration.equals(ManagerEnumeration.NOT_A_MANAGER))
            this.manager = "N";
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public ManagerEnumeration getManagerEnumeration() {
        if (manager.equalsIgnoreCase("M"))
            return ManagerEnumeration.MANAGER;
        if (manager.equalsIgnoreCase("N"))
            return ManagerEnumeration.NOT_A_MANAGER;
        return null;
    }
}
