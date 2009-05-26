package com.pdpsoft.applicationcontext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 17, 2009
 * Time: 6:57:34 PM
 * Associates a given ApplicationContext with the current execution thread.
 */
public class ApplicationContextHolder implements ApplicationContext {
    private static final Log LOG = LogFactory.getLog(ApplicationContextHolder.class);
    /*
        This is the ThreadLocal Pattern that it should keep the ApplicationContext
     */
    private static ThreadLocal<ApplicationDataCustomEntity> applicationDataCustomEntityThreadLocal = new ThreadLocal<ApplicationDataCustomEntity>();

    private static ApplicationDataCustomEntity applicationDataCustomEntity;
    /*
        This is the singleton pattern, nobody except this class should instantiate this object
     */
    private static ApplicationContextHolder contextHolder = new ApplicationContextHolder();

    private ApplicationContextHolder() {
        LOG.info("Application context holder starts to be initialized ...");
    }

    /**
     * This method handles the access to application data
     *
     * @return an instance of ApplicationDataCustomEntity
     */
    public ApplicationDataCustomEntity getApplicationContext() {
        ApplicationDataCustomEntity customEntity = applicationDataCustomEntityThreadLocal.get();
        if (customEntity != null)
            LOG.info("Application context holder is trying to register " + customEntity.getStructureCustomEntities().size() + " data.");
        if (applicationDataCustomEntity != null)
            LOG.info("Application context holder is trying to register " + applicationDataCustomEntity.getStructureCustomEntities().size() + " data.");
        if (customEntity != null)
            return customEntity;
        return applicationDataCustomEntity;
    }

    /**
     * This method used to put the context to the thread.
     *
     * @param context it's an instance of ApplicationDataCustomEntity that
     *                we ought to keep it via threadlocal pattern.
     */
    public void setApplicationContext(ApplicationDataCustomEntity context) {
        if (context != null)
            LOG.info("Application context holder is trying to register " + context.getStructureCustomEntities().size() + " data.");
        applicationDataCustomEntityThreadLocal.set(context);
        applicationDataCustomEntity = context;
        assert context != null;
        List<DataTransferObjectCustomEntity> objectCustomEntities = context.getStructureCustomEntities();
        LOG.info("Application context holder is registerd with the size of " + objectCustomEntities.size() + ".");
        if (LOG.isDebugEnabled()) {
            ApplicationContextUtility.debug(objectCustomEntities);
        }
    }

    /**
     * @return the application context (never null)
     */
    public static ApplicationContextHolder getApplicationContextHolder() {
        return contextHolder;
    }

    /**
     * Explicitly clears the context value from the current thread.
     */
    public static void clearContext() {
        applicationDataCustomEntityThreadLocal.set(null);
    }

    /**
     * This methods handles the application meta data
     *
     * @param entityName  Full qualified class name.
     * @param properyName It's the property name of the class that is has been introduced
     *                    as a application data.
     * @return All of within expressions should map to this field.
     *         <dto classname="Customer">
     *         <attribute propertyName="status">
     *         <expression value="0" bundleKey="Active"/>
     *         <expression value="1" bundleKey="Inactive"/>
     *         </attribute>
     *         </dto>
     *         The key of map is value in xml;
     *         the value of map is bundleKey.
     */
    public List<ApplicationContextDataCustomEntity> getObjects(final String entityName, final String properyName) {
        DataTransferObjectCustomEntity entity = ApplicationContextUtility.findSpecifiedClassName(entityName);
        return GetObjectsApplicationContext.getObjects(entity, properyName);
    }

}
