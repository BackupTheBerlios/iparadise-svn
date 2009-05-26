package com.pdpsoft.web;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 16, 2009
 * Time: 9:36:34 PM
 * the code has been moved from KAFA project, but unfortunately
 * there was not any author specified, but i think it has been
 * developed by Mohammad Shams. I've changed some naming conventions
 * and the packaging.
 *
 * @author M. H. Shamsi
 * @version 1.0.0
 * @date Jun 19, 2005
 */
public class SolarDatePlugIn implements PlugIn {
    public void init(ActionServlet actionServlet, ModuleConfig moduleConfig) throws ServletException {
        ConvertUtils.register(new DateConvertor(), Date.class);
    }

    public void destroy() {
    }
}
