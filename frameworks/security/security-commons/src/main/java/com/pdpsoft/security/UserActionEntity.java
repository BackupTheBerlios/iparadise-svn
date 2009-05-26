package com.pdpsoft.security;

import com.pdpsoft.PdpSoftEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 2:39:37 PM
 */
public class UserActionEntity extends PdpSoftEntity {
    /**
     * Identifier of this entity
     */
    private Integer userActionId;

    /**
     * Properties of this entity
     */
    /**
     * It's an enumeration hence there's not any
     * usual setter of this object, you ought to
     * use the AccessTypeEnumeration.
     */
    private String access;

    /**
     * Below you will find relations with
     * other objects
     */
    private SystemUserEntity systemUserEntity;
    private SystemActionEntity systemActionEntity;
    private SystemGroupEntity systemGroupEntity;

    /**
     * default constructor
     */
    public UserActionEntity() {
    }

    /**
     * @param userActionId user Action Id
     */
    public UserActionEntity(Integer userActionId) {
        this.userActionId = userActionId;
    }

    /**
     * @param enumeration        AccessTypeEnumeration
     * @param systemUserEntity   SystemUserEntity
     * @param systemActionEntity SystemActionEntity
     */
    public UserActionEntity(GroupActionEntity.AccessTypeEnumeration enumeration, SystemUserEntity systemUserEntity, SystemActionEntity systemActionEntity) {
        setAccess(enumeration);
        this.systemUserEntity = systemUserEntity;
        this.systemActionEntity = systemActionEntity;
    }

    /**
     * @param userActionId       user Action Id
     * @param enumeration        AccessTypeEnumeration
     * @param systemUserEntity   SystemUserEntity
     * @param systemActionEntity SystemActionEntity
     */
    public UserActionEntity(Integer userActionId, GroupActionEntity.AccessTypeEnumeration enumeration, SystemUserEntity systemUserEntity, SystemActionEntity systemActionEntity) {
        this.userActionId = userActionId;
        setAccess(enumeration);
        this.systemUserEntity = systemUserEntity;
        this.systemActionEntity = systemActionEntity;
    }

    public void setAccess(GroupActionEntity.AccessTypeEnumeration enumeration) {
        if (enumeration.equals(GroupActionEntity.AccessTypeEnumeration.FULL))
            access = "F";
        if (enumeration.equals(GroupActionEntity.AccessTypeEnumeration.READ_ONLY))
            access = "R";
        if (enumeration.equals(GroupActionEntity.AccessTypeEnumeration.NO_ACCESS))
            access = "N";
    }

    public GroupActionEntity.AccessTypeEnumeration getAccessTypeEnumeration() {
        if (access.equalsIgnoreCase("F"))
            return GroupActionEntity.AccessTypeEnumeration.FULL;
        if (access.equalsIgnoreCase("R"))
            return GroupActionEntity.AccessTypeEnumeration.READ_ONLY;
        if (access.equalsIgnoreCase("N"))
            return GroupActionEntity.AccessTypeEnumeration.NO_ACCESS;
        return null;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAccess() {
        return access;
    }

    public Integer getUserActionId() {
        return userActionId;
    }

    public void setUserActionId(Integer userActionId) {
        this.userActionId = userActionId;
    }

    public SystemUserEntity getSystemUserEntity() {
        return systemUserEntity;
    }

    public void setSystemUserEntity(SystemUserEntity systemUserEntity) {
        this.systemUserEntity = systemUserEntity;
    }

    public SystemActionEntity getSystemActionEntity() {
        return systemActionEntity;
    }

    public void setSystemActionEntity(SystemActionEntity systemActionEntity) {
        this.systemActionEntity = systemActionEntity;
    }

    public SystemGroupEntity getSystemGroupEntity() {
        return systemGroupEntity;
    }

    public void setSystemGroupEntity(SystemGroupEntity systemGroupEntity) {
        this.systemGroupEntity = systemGroupEntity;
    }
}