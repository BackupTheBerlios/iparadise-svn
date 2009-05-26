package com.pdpsoft.security;

import com.pdpsoft.PdpSoftActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 28, 2009
 * Time: 9:46:19 AM
 */
public class SecurityFormBean extends PdpSoftActionForm {
    private SystemUserEntity systemUserEntity = new SystemUserEntity();
    private Date myDate;
    private Date myDateLabel = new Date();

    public SystemUserEntity getSystemUserEntity() {
        return systemUserEntity;
    }

    public void setSystemUserEntity(SystemUserEntity systemUserEntity) {
        this.systemUserEntity = systemUserEntity;
    }

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        System.out.println("myDate = " + myDate);
        this.myDate = myDate;
    }

    public Date getMyDateLabel() {
        return myDateLabel;
    }

    public void setMyDateLabel(Date myDateLabel) {
        this.myDateLabel = myDateLabel;
    }
}
