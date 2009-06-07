package com.pdpsoft.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 4, 2009
 * Time: 3:53:29 PM
 */
public class XmlFormContextHolder {
    private static final Log LOG = LogFactory.getLog(XmlFormContextHolder.class);

    /**
     * the data that has been read from forms.xml file
     */
    private static XmlFormsCustomEntity xmlFormsCustomEntity;

    /*
        This is the singleton pattern, nobody except this class should instantiate this object
     */
    private static XmlFormContextHolder holder = new XmlFormContextHolder();
    private XmlFormContextHolder() {
        LOG.info("XML form is initialising ...");
    }

    /**
     * it handles the initiation of the XmlFormCustomEntity
     * @param xmlFormsCustom the value
     */
    public static void setXmlFormsCustomEntity(XmlFormsCustomEntity xmlFormsCustom) {
        xmlFormsCustomEntity = xmlFormsCustom;
        if(LOG.isDebugEnabled())
            debug(xmlFormsCustomEntity);
    }

    /**
     * returns a list of XmlFormsCustomEntity
     * @return list of XmlFormsCustomEntity
     */
    public static XmlFormsCustomEntity getXmlFormCustomEntity() {
        if(xmlFormsCustomEntity == null)
            throw new RuntimeException("you ought to fill data by setXmlFormsCustomEntity method");
        return xmlFormsCustomEntity;
    }

    /**
     * return an specific form
     * @param formId form identifier
     * @return an instance of XmlFormCustomEntity
     */
    public XmlFormCustomEntity getXmlFormCustomEntity(final String formId) {
        final Map<String,XmlFormCustomEntity> map = getXmlFormCustomEntity().getStringXmlFormCustomEntityMap();
        if(!map.containsKey(formId))
            throw new RuntimeException("There is not any form by " + formId);
        return map.get(formId);
    }

    /**
     * it happens only in a case of DEBUG mode.
     * @param mainEntity the value
     */
    private static void debug(XmlFormsCustomEntity mainEntity) {
        final List<XmlFormCustomEntity> xmlFormCustomEntityList = mainEntity.getXmlFormCustomEntities();
        for (XmlFormCustomEntity xmlFormCustomEntity : xmlFormCustomEntityList) {
            LOG.debug("Dto =" + xmlFormCustomEntity.getDto());
            LOG.debug("FilePath =" + xmlFormCustomEntity.getFilePath());
            LOG.debug("Id =" + xmlFormCustomEntity.getId());
            final List<XmlFormParameterCustomEntity> parameterCustomEntityList = xmlFormCustomEntity.getXmlFormParameterCustomEntities();
            for (XmlFormParameterCustomEntity customEntity : parameterCustomEntityList) {
                LOG.debug("Decorator =" + customEntity.getDecorator());
                LOG.debug("DtoParameter =" + customEntity.getDtoParameter());
                LOG.debug("FileParameter =" + customEntity.getFileParameter());
            }
        }
    }

    /**
     * @return the XML form context (never null)
     */
    public static XmlFormContextHolder getInstance() {
        return holder;
    }
    /**
     * Explicitly clears the context value from the current thread.
     */
    public static void clear() {
        holder = null;
    }
}
