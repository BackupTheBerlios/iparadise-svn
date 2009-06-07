package com.pdpsoft.report;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 6, 2009
 * Time: 9:53:03 AM
 */
public class XmlFormAdaptor {
    private static final Log LOG = LogFactory.getLog(XmlFormAdaptor.class);

    private XmlFormAdaptor() {
        // no instantiation
    }

    /**
     * this method is in charge of transforming all data.
     *
     * @param entity the main parameter of the method
     * @return an instance of Map which the key is fileParameter
     *         and the value is decorated object which is returned
     *         by fileParameter within the dto specified class.
     */
    public static Map<String, Object> transform(final XmlFormCustomEntity entity, final Object dtoClass) {
        final Map<String, Object> map = new HashMap<String, Object>();
        final List<XmlFormParameterCustomEntity> parameterCustomEntityList = entity.getXmlFormParameterCustomEntities();
        CollectionUtils.forAllDo(parameterCustomEntityList, new Closure() {
            public void execute(Object o) {
                XmlFormParameterCustomEntity customEntity = (XmlFormParameterCustomEntity) o;
                final String fileParameter = customEntity.getFileParameter();
                map.put(fileParameter,
                        valueTransformer(
                                customEntity.getDtoParameter(),
                                customEntity.getXmlFormDecorator(),
                                dtoClass
                        ));
            }
        });
        return map;
    }

    /**
     * this method is in charge of transforming the data into map
     *
     * @param dtoParameter     this is the parameter in the class
     * @param xmlFormDecorator the decorator object
     * @param dtoClass         the transfer object
     */
    private static Object valueTransformer(String dtoParameter, XmlFormDecorator xmlFormDecorator, Object dtoClass) {
        final String getterName = "get" + String.valueOf(dtoParameter.charAt(0)).toUpperCase() + String.valueOf(dtoParameter.substring(1));
        LOG.debug("the getter method is =" + getterName);
        try {
            final Method method = dtoClass.getClass().getMethod(getterName);
            Object originValue = method.invoke(dtoClass);
            LOG.debug("the original value is =" + originValue);
            return xmlFormDecorator.decorate(originValue);
        } catch (NoSuchMethodException e) {
            LOG.info("there is not any getter for " + dtoParameter);
            LOG.error(e);
        } catch (InvocationTargetException e) {
            LOG.info("there is InvocationTargetException for " + dtoParameter);
            LOG.error(e);
        } catch (IllegalAccessException e) {
            LOG.info("there is IllegalAccessException for " + dtoParameter);
            LOG.error(e);
        }
        return null;
    }
}
