package com.pdpsoft.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.Predicate;
import com.pdpsoft.commons.ResourceLocator;

import java.util.PropertyResourceBundle;
import java.util.List;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 30, 2009
 * Time: 8:26:26 PM
 */
public abstract class WebLoginModuleContext {
    private static final Log LOG = LogFactory.getLog(WebLoginModuleContext.class);
    private final static String RESOURCE_BUNDLE_FILE_NAME = "resources/ApplicationResources.properties";
    private static PropertyResourceBundle bundle = null;

    static {
        LOG.info("Web Login Module Context Data starts to read data from ".concat(RESOURCE_BUNDLE_FILE_NAME));
        try {
            bundle = new PropertyResourceBundle(
                    ResourceLocator.getResourceAsStream(RESOURCE_BUNDLE_FILE_NAME)
            );
        } catch (IOException e) {
            LOG.error(e);
            throw new RuntimeException("Web Login Module Context failed to read data from ".concat(RESOURCE_BUNDLE_FILE_NAME));
        }
        LOG.info("Web Login Module Context Data from ".concat(RESOURCE_BUNDLE_FILE_NAME).concat(" has been done."));
    }

    /**
     * return the values of the LoginModuleContextDataCustomEntity
     * @return a list of LoginModuleContextDataCustomEntity
     */
    public static List<LoginModuleContextDataCustomEntity> getObjects() {
        List<LoginModuleContextDataCustomEntity> contextDataCustomEntities = LoginModuleContextHolder.getLoginModuleContextDataCustomEntity();
        CollectionUtils.forAllDo(contextDataCustomEntities, new Closure() {
            public void execute(Object o) {
                LoginModuleContextDataCustomEntity entity = (LoginModuleContextDataCustomEntity) o;
                entity.setMessage(
                        bundle.getString(
                                entity.getNameKey()
                        )
                );
            }
        });
        return contextDataCustomEntities;
    }

    /**
     * returns the schema name base on city code
     * @param cityCode the city code which is our criterion
     * @return the schema name
     */
    public static String getSchemaName(final String cityCode) {
        List<LoginModuleContextDataCustomEntity> contextDataCustomEntities = getObjects();
        LoginModuleContextDataCustomEntity entity = (LoginModuleContextDataCustomEntity) CollectionUtils.find(contextDataCustomEntities, new Predicate() {
            public boolean evaluate(Object o) {
                LoginModuleContextDataCustomEntity entity = (LoginModuleContextDataCustomEntity) o;
                LOG.debug("entity.getCityCode()" + entity.getCityCode());
                LOG.debug("entity.getSchemaName()" + entity.getSchemaName());
                return entity.getCityCode().equals(cityCode);
            }
        });
        return entity.getSchemaName();
    }
}
