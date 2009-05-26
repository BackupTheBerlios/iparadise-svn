package com.pdpsoft.security;

import com.pdpsoft.PdpSoftEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 2:39:37 PM
 */
public class GroupActionEntity extends PdpSoftEntity {
    /**
     * Identifier of this entity
     */
    private Integer groupActionId;

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
     * Determine the status of management of current user;
     */
    public static enum AccessTypeEnumeration {
        FULL, READ_ONLY, NO_ACCESS
    }

    /**
     * Below you will find relations with
     * other objects
     */
    private SystemGroupEntity systemGroupEntity;
    private SystemActionEntity systemActionEntity;

    /**
     * default constructor
     */
    public GroupActionEntity() {
    }

    /**
     * @param groupActionId groupActionId
     */
    public GroupActionEntity(Integer groupActionId) {
        this.groupActionId = groupActionId;
    }

    public GroupActionEntity(AccessTypeEnumeration enumeration, SystemGroupEntity systemGroupEntity, SystemActionEntity systemActionEntity) {
        setAccess(enumeration);
        this.systemGroupEntity = systemGroupEntity;
        this.systemActionEntity = systemActionEntity;
    }

    public GroupActionEntity(Integer groupActionId, AccessTypeEnumeration enumeration, SystemGroupEntity systemGroupEntity, SystemActionEntity systemActionEntity) {
        this.groupActionId = groupActionId;
        setAccess(enumeration);
        this.systemGroupEntity = systemGroupEntity;
        this.systemActionEntity = systemActionEntity;
    }

    public void setAccess(AccessTypeEnumeration enumeration) {
        if (enumeration.equals(AccessTypeEnumeration.FULL))
            access = "F";
        if (enumeration.equals(AccessTypeEnumeration.READ_ONLY))
            access = "R";
        if (enumeration.equals(AccessTypeEnumeration.NO_ACCESS))
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

    public Integer getGroupActionId() {
        return groupActionId;
    }

    public void setGroupActionId(Integer groupActionId) {
        this.groupActionId = groupActionId;
    }

    public SystemGroupEntity getSystemGroupEntity() {
        return systemGroupEntity;
    }

    public void setSystemGroupEntity(SystemGroupEntity systemGroupEntity) {
        this.systemGroupEntity = systemGroupEntity;
    }

    public SystemActionEntity getSystemActionEntity() {
        return systemActionEntity;
    }

    public void setSystemActionEntity(SystemActionEntity systemActionEntity) {
        this.systemActionEntity = systemActionEntity;
    }
}
