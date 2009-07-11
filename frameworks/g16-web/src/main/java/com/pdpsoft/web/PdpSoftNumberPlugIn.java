package com.pdpsoft.web;

import org.apache.struts.action.PlugIn;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;

/**
 * Created by IntelliJ IDEA.
 * User: saman
 * Date: May 20, 2009
 * Time: 7:22:06 PM
 */
public class PdpSoftNumberPlugIn implements PlugIn {
    public void destroy() {
    }

    public void init(ActionServlet actionServlet, ModuleConfig moduleConfig) throws ServletException {
        ConvertUtils.register(new IntegerConverter(), Integer.class);
        ConvertUtils.register(new LongConverter(), Long.class);
        ConvertUtils.register(new DoubleConverter(), Double.class);
    }
}
