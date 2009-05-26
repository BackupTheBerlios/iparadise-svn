package com.pdpsoft.applicationcontext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 18, 2009
 * Time: 1:59:36 PM
 */
public class ApplicationContextListener implements ServletContextListener {
    private static final Log LOG = LogFactory.getLog(ApplicationContextListener.class);
    private static final String APPLICATION_CONTEXT_DATA_XML_FILE = "application-context-data.xml";
    
    public void contextInitialized(ServletContextEvent event) {
        LOG.info("Application context starts ...");
        ApplicationDataCustomEntity applicationDataCustomEntity = xmlparsing();
        ApplicationContextHolder.getApplicationContextHolder().setApplicationContext(applicationDataCustomEntity);
        LOG.info("Application context done.");
    }

    /**
     * This method is in charge of XML parsing
     * @return an instance of ApplicationDataCustomEntity
     */
    private ApplicationDataCustomEntity xmlparsing() {
        return ApplicationContextDataDigesterRule.xmlParsing(APPLICATION_CONTEXT_DATA_XML_FILE);
    }

    public void contextDestroyed(ServletContextEvent event) {
        LOG.info("Application context data destroyed.");
    }
}
