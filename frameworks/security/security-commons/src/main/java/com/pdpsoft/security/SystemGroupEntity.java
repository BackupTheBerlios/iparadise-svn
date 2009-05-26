package com.pdpsoft.security;

import com.pdpsoft.PdpSoftEntity;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 1:15:13 PM
 */
public class SystemGroupEntity extends PdpSoftEntity {
    /**
     * Identifier of this entity
     */
    private Integer systemGroupId;

    /**
     * Properties of this entity
     */
    private String name;
    private String description;

    /**
     * Below you will find relations with
     * other objects
     */
    private Set<UserGroupEntity> userGroupEntities;
    private Set<GroupActionEntity> groupActionEntities;

    /**
     * default contstructor
     */
    public SystemGroupEntity() {
        super();
    }

    /**
     * @param systemGroupId Identifier
     */
    public SystemGroupEntity(Integer systemGroupId) {
        super();
        this.systemGroupId = systemGroupId;
    }

    /**
     * @param name        Name
     * @param description description
     */
    public SystemGroupEntity(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    /**
     * @param systemGroupId Identifier
     * @param name          name
     * @param description   description
     */
    public SystemGroupEntity(Integer systemGroupId, String name, String description) {
        super();
        this.systemGroupId = systemGroupId;
        this.name = name;
        this.description = description;
    }

    public Integer getSystemGroupId() {
        return systemGroupId;
    }

    public void setSystemGroupId(Integer systemGroupId) {
        this.systemGroupId = systemGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserGroupEntity> getUserGroupEntities() {
        return userGroupEntities;
    }

    public void setUserGroupEntities(Set<UserGroupEntity> userGroupEntities) {
        this.userGroupEntities = userGroupEntities;
    }

    public Set<GroupActionEntity> getGroupActionEntities() {
        return groupActionEntities;
    }

    public void setGroupActionEntities(Set<GroupActionEntity> groupActionEntities) {
        this.groupActionEntities = groupActionEntities;
    }
}
