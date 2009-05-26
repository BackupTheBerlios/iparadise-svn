package com.pdpsoft.security;

import com.pdpsoft.PdpSoftCustomEntity;

/**
 * Created by IntelliJ IDEA.
 * User: sajedi
 * Date: May 6, 2009
 * Time: 11:25:18 AM
 */
public class SystemGroupCustomEntity extends PdpSoftCustomEntity {

    /**
     * It holds the group
     */
    private SystemGroupEntity systemGroupEntity;

    /**
     * It true if the group select for user
     */
    private Boolean isCheckedGroup;

    /**
     * It true if the user is group's manager
     */
    private Boolean isCheckedManager;

    /*
    * It is a temp variable just for retrieving data from checkbox on jsp
    * */
    private String tempStringForIsCheckedGroup;

    /*
   * It is a temp variable just for retrieving data from checkbox on jsp
   * */
    private String tempStringForisCheckedManager;


    //getter and setter
    public SystemGroupEntity getSystemGroupEntity() {
        return systemGroupEntity;
    }

    public void setSystemGroupEntity(SystemGroupEntity systemGroupEntity) {
        this.systemGroupEntity = systemGroupEntity;
    }

    public Boolean getCheckedGroup() {
        return isCheckedGroup;
    }

    public void setCheckedGroup(Boolean checkedGroup) {
        isCheckedGroup = checkedGroup;
    }

    public Boolean getCheckedManager() {
        return isCheckedManager;
    }

    public void setCheckedManager(Boolean checkedManager) {
        isCheckedManager = checkedManager;
    }

    public String getTempStringForisCheckedManager() {
        return tempStringForisCheckedManager;
    }

    public void setTempStringForisCheckedManager(String tempStringForisCheckedManager) {
        this.tempStringForisCheckedManager = tempStringForisCheckedManager;
        if (tempStringForisCheckedManager.equalsIgnoreCase("on"))
            this.isCheckedManager = true;
        if (tempStringForisCheckedManager.equals("") ||
                tempStringForisCheckedManager == "" ||
                tempStringForisCheckedManager == null)
            this.isCheckedManager = false;
    }

    public String getTempStringForIsCheckedGroup() {
        return tempStringForIsCheckedGroup;
    }

    public void setTempStringForIsCheckedGroup(String tempStringForIsCheckedGroup) {
        this.tempStringForIsCheckedGroup = tempStringForIsCheckedGroup;
        if (tempStringForIsCheckedGroup.equalsIgnoreCase("on"))
            this.isCheckedGroup = true;
        if (tempStringForIsCheckedGroup.equals("") ||
                tempStringForIsCheckedGroup == "" ||
                tempStringForIsCheckedGroup == null)
            this.isCheckedGroup = false;
    }
}
