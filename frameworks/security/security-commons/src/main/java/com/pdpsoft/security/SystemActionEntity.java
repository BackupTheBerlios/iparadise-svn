package com.pdpsoft.security;

import com.pdpsoft.PdpSoftEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 2:04:02 PM
 */
public class SystemActionEntity extends PdpSoftEntity {
    /**
     * Identifier of this entity
     */
    private Integer systemActionId;

    /**
     * Properties of this entity
     */
    private String code;
    private String description;

    /**
     * Below you will find relations with
     * other objects
     */

    /**
     * It determines the upper level of the SystemActionEntity;
     * such as: Security is node and sub-level of that can be
     * user pages, role pages, and [...]
     */
    private SystemActionEntity systemActionEntity;

    /**
     * default contstructor
     */
    public SystemActionEntity() {
    }

    /**
     * @param systemActionId systemActionId
     */
    public SystemActionEntity(Integer systemActionId) {
        this.systemActionId = systemActionId;
    }

    /**
     * @param code               code
     * @param description        description
     * @param systemActionEntity systemActionEntity
     */
    public SystemActionEntity(String code, String description, SystemActionEntity systemActionEntity) {
        this.code = code;
        this.description = description;
        this.systemActionEntity = systemActionEntity;
    }

    /**
     * @param systemActionId     systemActionId
     * @param code               code
     * @param description        description
     * @param systemActionEntity systemActionEntity
     */
    public SystemActionEntity(Integer systemActionId, String code, String description, SystemActionEntity systemActionEntity) {
        this.systemActionId = systemActionId;
        this.code = code;
        this.description = description;
        this.systemActionEntity = systemActionEntity;
    }

    public Integer getSystemActionId() {
        return systemActionId;
    }

    public void setSystemActionId(Integer systemActionId) {
        this.systemActionId = systemActionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SystemActionEntity getSystemActionEntity() {
        return systemActionEntity;
    }

    public void setSystemActionEntity(SystemActionEntity systemActionEntity) {
        this.systemActionEntity = systemActionEntity;
    }
}
