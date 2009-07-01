package com.pdpsoft.applicationcontext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 18, 2009
 * Time: 9:24:11 AM
 */
public class GetObjectsApplicationContext implements ApplicationContextCommand {
    /**
     * This methods handles the application meta data
     *
     * @param entity      This is the DataTransferObjectCustomEntity
     * @param properyName It's the property name of the class that is has been introduced
     *                    as a application data.
     * @return All of within expressions should map to this field.
     *         <dto classname="Customer">
     *            <attribute propertyName="status">
     *              <expression value="0" bundleKey="Active"/>
     *              <expression value="1" bundleKey="Inactive"/>
     *            </attribute>
     *         </dto>
     *         The key of map is value in xml;
     *         the value of map is bundleKey.
     */
    public static List<ApplicationContextDataCustomEntity> getObjects(final DataTransferObjectCustomEntity entity, final String properyName) {
        AttributeCustomEntity theAttribute = findSpecificAttribute(entity, properyName);
        return ApplicationDataConvertor.getApplicationContextDataCustomEntities(theAttribute.getMap(), theAttribute.getSort());
    }

    /**
     * this method is in charge of finding a specified property
     *
     * @param entity      This is the DataTransferObjectCustomEntity
     * @param properyName It's the property name of the class that is has been introduced
     *                    as a application data.
     * @return it is an instance of AttributeCustomEntity
     */
    private static AttributeCustomEntity findSpecificAttribute(DataTransferObjectCustomEntity entity, final String properyName) {
        List<AttributeCustomEntity> entityList = entity.getAttributeCustomEntityList();
        AttributeCustomEntity theAttribute = (AttributeCustomEntity)
                CollectionUtils.find(entityList, new Predicate() {
                    public boolean evaluate(Object o) {
                        AttributeCustomEntity attributeCustomEntity = (AttributeCustomEntity) o;
                        return attributeCustomEntity.getPropertyName().equalsIgnoreCase(properyName);
                    }
                });
        return theAttribute;
    }
}
