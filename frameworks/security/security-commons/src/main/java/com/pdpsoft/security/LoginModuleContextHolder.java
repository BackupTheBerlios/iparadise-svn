package com.pdpsoft.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 30, 2009
 * Time: 7:14:54 PM
 */
public class LoginModuleContextHolder {
    private static final Log LOG = LogFactory.getLog(LoginModuleContextHolder.class);

    /**
     * the data that has been read from loginModules.xml file
     */
    private static LoginModulesCustomEntity loginModulesCustomEntity;
    
    private static String defaultCityCode = null;
    private static String defaultSchemaName = null;
    private static String hibernateConfigurationName = null;
    private static List<LoginModuleContextDataCustomEntity> loginModuleContextDataCustomEntity = null;

    /**
     * whenever the user change the value of the schema hence this argument ought to have value.
     */
    private static ThreadLocal<String> selectedSchemaName = new ThreadLocal<String>();
    private static ThreadLocal<String> selectedCityCode   = new ThreadLocal<String>();
    /*
        This is the singleton pattern, nobody except this class should instantiate this object
     */
    private static LoginModuleContextHolder holder = new LoginModuleContextHolder();

    private LoginModuleContextHolder() {
        LOG.info("login module context is initialising ...");
    }

    /**
     * This method handles the access to login module data
     * @return an instance of LoginModulesCustomEntity
     */
    public LoginModulesCustomEntity getLoginModule() {
        if(loginModulesCustomEntity != null)
            LOG.info("login modules custom entity has been fetched with the size of :" + loginModulesCustomEntity.getLoginModuleCustomEntities().size());
        return loginModulesCustomEntity;
    }

    /**
     * this method is in charge of setting data that has been fetched from database
     * @param entity is an instance of LoginModulesCustomEntity
     */
    public void setLoginModule(LoginModulesCustomEntity entity) {
        loginModulesCustomEntity = entity;
        List<LoginModuleCustomEntity> entityList = loginModulesCustomEntity.getLoginModuleCustomEntities();
        if(LOG.isDebugEnabled())
            debug(entityList);
        defaultCityCode = getLoginModuleCustomEntity().getDefaultCityCode();
        defaultSchemaName = getLoginModuleCustomEntity().getDefaultSchemaName();
        hibernateConfigurationName = getLoginModuleCustomEntity().getHibernateConfigurationName();
        loginModuleContextDataCustomEntity = getLoginModuleCustomEntity().getLoginSchemasCustomEntities().get(0).getCustomEntities();
    }

    /**
     * it happens only in a case of DEBUG mode.
     * @param entityList
     */
    private void debug(List<LoginModuleCustomEntity> entityList) {
        for (LoginModuleCustomEntity customEntity : entityList) {
            LOG.debug("customEntity.getDefaultSchemaName=" + customEntity.getDefaultSchemaName());
            LOG.debug("customEntity.getHibernateConfigurationName=" + customEntity.getHibernateConfigurationName());
            List<LoginSchemasCustomEntity> schemasCustomEntityList = customEntity.getLoginSchemasCustomEntities();
            for (LoginSchemasCustomEntity schemasCustomEntity : schemasCustomEntityList) {
                LOG.debug("schemasCustomEntity.getSchemas().size=" + schemasCustomEntity.getSchemas().size());
            }
        }
    }

    /**
     * @return the login module context (never null)
     */
    public static LoginModuleContextHolder getInstance() {
        return holder;
    }

    /**
     * Explicitly clears the context value from the current thread.
     */
    public static void clear() {
        holder = null;
    }

    /**
     * return an instance of LoginModuleCustomEntity
     * @return an instance of LoginModuleCustomEntity
     */
    public LoginModuleCustomEntity getLoginModuleCustomEntity() {
        // in this version we suppose to have only one instnace of it
        return getLoginModule().getLoginModuleCustomEntities().get(0);
    }

    // getter and setter of selectedSchemaName, as a thread local pattern
    /**
     * this method puts the value into the threadlocal parameter
     * @param schemaName the schema name
     */
    public void setSelectedSchemaName(final String schemaName) {
        LOG.debug("the user tried to change the schema to ".concat(schemaName));
        selectedSchemaName.set(schemaName);
    }
    /**
     * returns the value from threadlocal
     * @return the current schema name
     */
    public String getSelectedSchemaName() {
        return selectedSchemaName.get();
    }

    /**
     * this methods put the city code
     * @param cityCode city code
     */
    public void setSelectedCityCode(final String cityCode) {
        LOG.debug("the user tried to change the city code to ".concat(cityCode));
        selectedCityCode.set(cityCode);
    }

    /**
     * returns the value from threadlocal
     * @return the current city code
     */
    public String getSelectedCityCode() {
        return selectedCityCode.get();
    }

    // getters of properties, these are mute properties
    public static String getDefaultSchemaName() {
        return defaultSchemaName;
    }

    public static String getHibernateConfigurationName() {
        return hibernateConfigurationName;
    }

    public static List<LoginModuleContextDataCustomEntity> getLoginModuleContextDataCustomEntity() {
        return loginModuleContextDataCustomEntity;
    }
}
