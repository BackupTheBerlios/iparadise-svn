package com.pdpsoft.security;

import com.pdpsoft.PdpSoftWebStrutsAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 4, 2009
 * Time: 10:53:28 PM
 */
public class SecurityContextWebFilter implements Filter {
    private FilterConfig filterConfig = null;
    private String parameterName;
    private String parameterValue;

    final static Log LOG = LogFactory.getLog(SecurityContextWebFilter.class);

    /**
     * @param filterConfig it's a filterConfig
     * @throws ServletException ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.parameterName  = filterConfig.getInitParameter("parameterName");
        this.parameterValue = filterConfig.getInitParameter("parameterValue"); 
        LOG.debug("SecurityContextWebFilter init ...");
    }

    /**
     * @param servletRequest  it's request
     * @param servletResponse it's response
     * @param filterChain     it's a chain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String reqCodeValue = request.getParameter(parameterName);


        if(StringUtils.equals(reqCodeValue, parameterValue)) {
            request.getSession().removeAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT);
            request.getSession().removeAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT_AUTHORIZATION);
            request.getSession().invalidate();
            SecurityContextHolder.clearContext();
            // Go to the servlet
            filterChain.doFilter(servletRequest, servletResponse);
            return;            
        }

        Object filterApplied = request.getSession(true).getAttribute(PdpSoftWebStrutsAction.SECURITY_FILTER_APPLIED);
        boolean isFilterApplied = false;
        if (filterApplied != null)
            isFilterApplied = (Boolean) filterApplied;
        try {
            if (isFilterApplied) {
                LOG.debug("SecurityContextWebFilter doFilter ...");
                preChain(servletRequest, servletResponse, filterChain);
            }

            LOG.debug("SecurityContextWebFilter filter chain ...");
            // Go to the servlet
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (isFilterApplied)
                postChain(servletRequest, servletResponse, filterChain);
        }
    }

    public void destroy() {
        LOG.debug("SecurityContextWebFilter destroy ...");
        filterConfig = null;
    }

    /**
     * @param servletRequest  it's request
     * @param servletResponse it's response
     * @param filterChain     it's a chain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    private void preChain(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("SecurityContextWebFilter preChain ...");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session != null) {
            SystemUserEntity systemUserEntity = (SystemUserEntity) session.getAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT);
            List<SystemActionEntity> actionEntities = (List<SystemActionEntity>) session.getAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT_AUTHORIZATION);

            LOG.debug("SecurityContextWebFilter preChain systemUserEntity is " + systemUserEntity);
            LOG.debug("SecurityContextWebFilter preChain actionEntities is " + actionEntities);
            if (systemUserEntity != null && actionEntities != null) {
                AuthorizationCustomEntity customEntity = new AuthorizationCustomEntity(systemUserEntity, actionEntities);
                LOG.debug("SecurityContextWebFilter preChain customEntity username is " + customEntity.getSystemUserEntity().getUserName());
                SecurityContextHolder.getContext().setAuthentication(customEntity);
            }
        }
    }

    /**
     * @param servletRequest  it's request
     * @param servletResponse it's response
     * @param filterChain     it's a chain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    private void postChain(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        SecurityContext securityContext = SecurityContextHolder.getContext();

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        if (session != null) {
            session.setAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT, securityContext.getAuthentication());
            session.setAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT_AUTHORIZATION, securityContext.getAuthorizations());
        }
    }
}
