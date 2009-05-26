package com.pdpsoft.applicationcontext;

import com.pdpsoft.commons.ResourceLocator;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;
import java.util.PropertyResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 18, 2009
 * Time: 4:17:10 PM
 * This class is in charge of managing application contexts
 */
public abstract class WebApplicationContext {
    private final static Log LOG = LogFactory.getLog(WebApplicationContext.class);
    private final static String RESOURCE_BUNDLE_FILE_NAME = "resources/ApplicationResources.properties";
    private static PropertyResourceBundle bundle = null;

    static {
        LOG.info("Application Context Data starts to read data from ".concat(RESOURCE_BUNDLE_FILE_NAME));
        try {
            bundle = new PropertyResourceBundle(
                    ResourceLocator.getResourceAsStream(RESOURCE_BUNDLE_FILE_NAME)
            );
        } catch (IOException e) {
            LOG.error(e);
            throw new RuntimeException("Application Context failed to read data from ".concat(RESOURCE_BUNDLE_FILE_NAME));
        }
        LOG.info("Application Context Data from ".concat(RESOURCE_BUNDLE_FILE_NAME).concat(" has been done."));
    }

    /**
     * This methods handles the application meta data
     *
     * @param entityName  Full qualified class name.
     * @param properyName It's the property name of the class that is has been introduced
     *                    as a application data.
     * @return All of within expressions should map to this field.
     *         <dto classname="Customer">
     *          <attribute propertyName="status">
     *              <expression value="0" bundleKey="Active"/>
     *              <expression value="1" bundleKey="Inactive"/>
     *          </attribute>
     *         </dto>
     *         The key of map is value in xml;
     *         the value of map is bundleKey.
     */
    public static List<ApplicationContextDataCustomEntity> getObjects(final String entityName, final String properyName) {
        List<ApplicationContextDataCustomEntity> contextDataCustomEntities = ApplicationContextHolder
                .getApplicationContextHolder()
                .getObjects(entityName, properyName);
        CollectionUtils.forAllDo(contextDataCustomEntities, new Closure() {
            public void execute(Object o) {
                ApplicationContextDataCustomEntity entity = (ApplicationContextDataCustomEntity) o;
                entity.setMessage(
                        /*
                             this part we read data from resource bundle
                             and then we put it into the object, so if your
                             language is Farsi the message should be farsi.
                        */
                        bundle.getString(
                                entity.getBundleKey()
                        )
                );
            }
        });
        return contextDataCustomEntities;
    }

    /**
     * This methods handles the application meta data
     *
     * @param entityName  Full qualified class name.
     * @param properyName It's the property name of the class that is has been introduced
     *                    as a application data.
     * @param value The value that the only one row should be fetch
     * @return Only one specific expressions should map to this field.
     *         <dto classname="Customer">
     *          <attribute propertyName="status">
     *              <expression value="0" bundleKey="Active"/>
     *              <expression value="1" bundleKey="Inactive"/>
     *          </attribute>
     *         </dto>
     *         The key of map is value in xml;
     *         the value of map is bundleKey.
     */
    public static ApplicationContextDataCustomEntity getObject(final String entityName, final String properyName, final Object value) {
        List<ApplicationContextDataCustomEntity> contextDataCustomEntities = getObjects(entityName, properyName);
        return (ApplicationContextDataCustomEntity) CollectionUtils.find(contextDataCustomEntities, new Predicate() {
            public boolean evaluate(Object o) {
                ApplicationContextDataCustomEntity entity = (ApplicationContextDataCustomEntity) o;
                return entity.getValue().equals(value);
            }
        });
    }
}
