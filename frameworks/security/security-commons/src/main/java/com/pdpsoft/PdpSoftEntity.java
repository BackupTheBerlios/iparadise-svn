package com.pdpsoft;

import com.pdpsoft.commons.G16ParentEntity;
import com.pdpsoft.security.SecurityContextHolder;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan
 * Date: Apr 19, 2009
 * Time: 1:04:28 PM
 */
public class PdpSoftEntity extends G16ParentEntity {

    /**
     * userName and actionDate are used for
     * have a latest of activities that has
     * been done in this record.
     */
    private String userNameIdentifier;

    /**
     * userName and actionDate are used for
     * have a latest of activities that has
     * been done in this record.
     */
    private Date actionDate;

    /*
       Hibernate provides automatic versioning. Each entity instance has a version,
       which can be a number or a timestamp. Hibernate increments an object’s version
       when it’s modified, compares versions automatically, and throws an exception if a
       conflict is detected. Consequently, you add this version property to all your persistent
       entity classes to enable optimistic locking. however, version numbers must not be modified
       by the application. The <version> property mapping in XML must be placed
       immediately after the identifier property mapping:
        <class name="Item" table="ITEM">
            <id .../>
            <version name="version" access="field" column="OBJ_VERSION"/>
            ...
        </class> 
     */
    private int version;

    /**
     * The cityCode is not used in this version. because the database of each city is different
     * and there is not any conflict amoung data. If the billing system is integrated with
     * different cities, this code should be used otherwise. 
     */
    private Integer cityCode;

    public PdpSoftEntity() {
        actionDate = new Date();
    }

    public String getUserNameIdentifier() {
        if (userNameIdentifier == null || userNameIdentifier.equalsIgnoreCase(""))
            userNameIdentifier = SecurityContextHolder.getContext().getAuthentication().getUserName();
        return userNameIdentifier;
    }

    public void setUserNameIdentifier(String userNameIdentifier) {
        this.userNameIdentifier = userNameIdentifier;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }
}
