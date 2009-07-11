package com.pdpsoft.security;

import com.pdpsoft.PdpSoftWebStrutsAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 4, 2009
 * Time: 10:53:28 PM
 */
public class WebLoginModuleFilter implements Filter {
    private FilterConfig filterConfig = null;

    final static Log LOG = LogFactory.getLog(WebLoginModuleFilter.class);

    /**
     * @param filterConfig it's a filterConfig
     * @throws javax.servlet.ServletException ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        LOG.debug("WebLoginModule inits ...");
    }

    /**
     * @param servletRequest  it's request
     * @param servletResponse it's response
     * @param filterChain     it's a chain
     * @throws java.io.IOException            IOException
     * @throws javax.servlet.ServletException ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            LOG.debug("WebLoginModuleFilter doFilter ...");
            preChain(servletRequest, servletResponse, filterChain);
            LOG.debug("WebLoginModuleFilter filter chain ...");
            // Go to the servlet
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            postChain(servletRequest, servletResponse, filterChain);
        }
    }

    public void destroy() {
        LOG.debug("WebLoginModuleFilter destroy ...");
        filterConfig = null;
    }

    /**
     * @param servletRequest  it's request
     * @param servletResponse it's response
     * @param filterChain     it's a chain
     * @throws java.io.IOException            IOException
     * @throws javax.servlet.ServletException ServletException
     */
    private void preChain(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("WebLoginModuleFilter preChain ...");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session != null) {
            final String loginSchema = (String) session.getAttribute(PdpSoftWebStrutsAction.WEB_LOGIN_MODULE_SCHEMA_CONTEXT);
            final String cityCode = (String) session.getAttribute(PdpSoftWebStrutsAction.WEB_LOGIN_MODULE_CITY_CODE_CONTEXT);

            LOG.debug("WebLoginModuleFilter preChain loginSchema is " + loginSchema);
            LOG.debug("WebLoginModuleFilter preChain cityCode is " + cityCode);
            if (loginSchema != null) {
                LoginModuleContextHolder.getInstance().setSelectedSchemaName(loginSchema);
            }
            if (cityCode != null) {
                LoginModuleContextHolder.getInstance().setSelectedCityCode(cityCode);
            }
        }
    }

    /**
     * @param servletRequest  it's request
     * @param servletResponse it's response
     * @param filterChain     it's a chain
     * @throws java.io.IOException            IOException
     * @throws javax.servlet.ServletException ServletException
     */
    private void postChain(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final String schemaName = LoginModuleContextHolder.getInstance().getSelectedSchemaName();
        final String cityCode = LoginModuleContextHolder.getInstance().getSelectedCityCode();
        LOG.debug("WebLoginModuleFilter postChain schemaName is " + schemaName);
        LOG.debug("WebLoginModuleFilter postChain cityCode is " + cityCode);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        if (session != null) {
            session.setAttribute(PdpSoftWebStrutsAction.WEB_LOGIN_MODULE_SCHEMA_CONTEXT, schemaName);
            session.setAttribute(PdpSoftWebStrutsAction.WEB_LOGIN_MODULE_CITY_CODE_CONTEXT, cityCode);
        }
    }
}