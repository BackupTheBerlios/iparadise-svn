package com.pdpsoft.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 6, 2009
 * Time: 9:33:56 AM
 */
public class XmlFormListener implements ServletContextListener {
    private static final Log LOG = LogFactory.getLog(XmlFormListener.class);
    private static final String FORMS_XML_FILE = "forms.xml"; 

    public void contextInitialized(ServletContextEvent event) {
        LOG.info("xml form listener starts ...");
        final XmlFormsCustomEntity customEntity = XmlFormDigesterRule.parseXmlForms(FORMS_XML_FILE);
        XmlFormContextHolder.setXmlFormsCustomEntity(customEntity);
        LOG.info("xml form listener done");
    }

    public void contextDestroyed(ServletContextEvent event) {
        LOG.info("xml form listener destroyed ...");
        XmlFormContextHolder.clear();
    }
}
