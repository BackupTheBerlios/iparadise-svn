package com.pdpsoft.applicationcontext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 18, 2009
 * Time: 1:30:32 PM
 */
class ApplicationContextUtility {
    private static final Log LOG = LogFactory.getLog(ApplicationContextUtility.class);

    /**
     * it happens only in a case of DEBUG mode.
     *
     * @param objectCustomEntities the records of XML files
     */
    public static void debug(List<DataTransferObjectCustomEntity> objectCustomEntities) {
        for (DataTransferObjectCustomEntity entity : objectCustomEntities) {
            LOG.debug(entity.getFullQualifiedClassName() + " has been registered.");
            List<AttributeCustomEntity> entityList = entity.getAttributeCustomEntityList();
            for (AttributeCustomEntity customEntity : entityList) {
                LOG.debug("the property name is : " + customEntity.getPropertyName());
                List<ApplicationContextDataCustomEntity> contextDataCustomEntities = ApplicationDataConvertor.getApplicationContextDataCustomEntities(customEntity.getMap(), customEntity.getSort());
                for (ApplicationContextDataCustomEntity expr : contextDataCustomEntities) {
                    LOG.debug(new StringBuilder().append("the expression is :")
                            .append(expr.getValue())
                            .append(" and the bundle key is: ")
                            .append(expr.getBundleKey())
                            .toString());
                }
            }
        }
    }

    /**
     * Find a specific class
     *
     * @param entityName full entity qualified name
     */
    public static DataTransferObjectCustomEntity findSpecifiedClassName(final String entityName) {
        List<DataTransferObjectCustomEntity> objectCustomEntities = ApplicationContextHolder.getApplicationContextHolder().getApplicationContext().getStructureCustomEntities();
        return (DataTransferObjectCustomEntity) CollectionUtils
                .find(objectCustomEntities, new Predicate() {
                    public boolean evaluate(Object o) {
                        DataTransferObjectCustomEntity customEntity = (DataTransferObjectCustomEntity) o;
                        return customEntity.getFullQualifiedClassName().equals(entityName);
                    }
                });
    }

}
