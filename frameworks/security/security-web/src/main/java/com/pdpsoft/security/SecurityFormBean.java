package com.pdpsoft.security;

import com.pdpsoft.PdpSoftActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 28, 2009
 * Time: 9:46:19 AM
 */
public class SecurityFormBean extends PdpSoftActionForm {
    private SystemUserEntity systemUserEntity = new SystemUserEntity();
    private SystemGroupEntity systemGroupEntity = new SystemGroupEntity();
    private String confirmPassword;
    private SystemGroupCustomEntity[] systemGroupCustomEntities;
    private SystemGroupCustomEntity[] editSystemGroupCustomEntities;
    private List<LoginModuleContextDataCustomEntity> schemas;
    private String cityCode;
    
    public SystemUserEntity getSystemUserEntity() {
        return systemUserEntity;
    }

    public void setSystemUserEntity(SystemUserEntity systemUserEntity) {
        this.systemUserEntity = systemUserEntity;
    }

    public SystemGroupEntity getSystemGroupEntity() {
        return systemGroupEntity;
    }

    public void setSystemGroupEntity(SystemGroupEntity systemGroupEntity) {
        this.systemGroupEntity = systemGroupEntity;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public SystemGroupCustomEntity[] getSystemGroupCustomEntities() {
        return systemGroupCustomEntities;
    }

    public void setSystemGroupCustomEntities(SystemGroupCustomEntity[] systemGroupCustomEntities) {
        this.systemGroupCustomEntities = systemGroupCustomEntities;
    }

    public SystemGroupCustomEntity[] getEditSystemGroupCustomEntities() {
        return editSystemGroupCustomEntities;
    }

    public void setEditSystemGroupCustomEntities(SystemGroupCustomEntity[] editSystemGroupCustomEntities) {
        this.editSystemGroupCustomEntities = editSystemGroupCustomEntities;
    }

    public List<LoginModuleContextDataCustomEntity> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<LoginModuleContextDataCustomEntity> schemas) {
        this.schemas = schemas;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
