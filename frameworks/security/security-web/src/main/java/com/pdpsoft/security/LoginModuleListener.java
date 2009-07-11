package com.pdpsoft.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 30, 2009
 * Time: 8:03:51 PM
 */
public class LoginModuleListener implements ServletContextListener {
    private static final Log LOG = LogFactory.getLog(LoginModuleListener.class);
    private static final String LOGIN_MODULE_CONTEXT_XML_FILE = "loginModules.xml"; 

    public void contextInitialized(ServletContextEvent event) {
        LOG.info("login module listener starts ...");
        LoginModulesCustomEntity customEntity = LoginModuleDigesterRule.parseLoginModules(LOGIN_MODULE_CONTEXT_XML_FILE);
        LoginModuleContextHolder.getInstance().setLoginModule(customEntity);
        LOG.info("login module listener done   ...");
    }

    public void contextDestroyed(ServletContextEvent event) {
        LOG.info("login module listener destroyed ...");
    }
}
