package com.pdpsoft.security;

import com.pdpsoft.PdpSoftEntity;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 1:44:46 PM
 */
public class SystemUserEntity extends PdpSoftEntity {
    /**
     * Identifier of this entity
     */
    private Integer systemUserId;

    /**
     * Properties of this entity
     */
    private String firstName;
    private String surname;
    private String userName;
    private String password;
    private String email;

    /**
     * It's an enumeration hence there's not any
     * usual setter of this object, you ought to
     * use the StatusTypeEnumeration.
     */
    private String status;

    /**
     * Below you will find relations with
     * other objects
     */
    private Set<UserGroupEntity> userGroupEntities;
    private Set<UserActionEntity> userActionEntities;

    /**
     * Determine the status of current user;
     */
    public static enum StatusTypeEnumeration {
        ACTIVE, INACTIVE
    }

    /**
     * default contstructor
     */
    public SystemUserEntity() {
    }

    /**
     * @param systemUserId systemUserId
     */
    public SystemUserEntity(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    /**
     * @param firstName             First Name
     * @param surname               Surname
     * @param userName              user name
     * @param password              password
     * @param email                 email
     * @param statusTypeEnumeration statusTypeEnumeration
     */
    public SystemUserEntity(String firstName, String surname, String userName, String password, String email, StatusTypeEnumeration statusTypeEnumeration) {
        this.firstName = firstName;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.email = email;
        setStatus(statusTypeEnumeration);
    }

    /**
     * @param systemUserId          User Identifier
     * @param firstName             First Name
     * @param surname               Surname
     * @param userName              user name
     * @param password              password
     * @param email                 email
     * @param statusTypeEnumeration statusTypeEnumeration
     */
    public SystemUserEntity(Integer systemUserId, String firstName, String surname, String userName, String password, String email, StatusTypeEnumeration statusTypeEnumeration) {
        this.systemUserId = systemUserId;
        this.firstName = firstName;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.email = email;
        setStatus(statusTypeEnumeration);
    }

    public Integer getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(StatusTypeEnumeration statusTypeEnumeration) {
        if (statusTypeEnumeration.equals(StatusTypeEnumeration.ACTIVE))
            status = "A";
        if (statusTypeEnumeration.equals(StatusTypeEnumeration.INACTIVE))
            status = "I";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StatusTypeEnumeration getStatusEnumeration() {
        if (status.equalsIgnoreCase("A"))
            return StatusTypeEnumeration.ACTIVE;
        if (status.equalsIgnoreCase("I"))
            return StatusTypeEnumeration.INACTIVE;
        return null;
    }

    public String getStatus() {
        return status;
    }

    public Set<UserGroupEntity> getUserGroupEntities() {
        return userGroupEntities;
    }

    public void setUserGroupEntities(Set<UserGroupEntity> userGroupEntities) {
        this.userGroupEntities = userGroupEntities;
    }

    public Set<UserActionEntity> getUserActionEntities() {
        return userActionEntities;
    }

    public void setUserActionEntities(Set<UserActionEntity> userActionEntities) {
        this.userActionEntities = userActionEntities;
    }
}
