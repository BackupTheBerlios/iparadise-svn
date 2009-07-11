package com.pdpsoft.security;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jul 7, 2009
 * Time: 12:00:07 PM
 */
public class SecurityLoginModuleValidateFilter implements Filter {
    private static Log log = LogFactory.getLog(SecurityLoginModuleValidateFilter.class);
    protected FilterConfig filterConfig = null;
    protected String excludedUrls[];

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            log.debug("Valid user");
            chain.doFilter(request, response);
        } else {
            log.debug("Not a valid user");
            String uri = ((HttpServletRequest) request).getRequestURI();

            //If user is try to log in let him
            //The servlet and pathes that deals with login are hard coded here
            if (isExcludedUrl(uri) || uri.substring(uri.length() - 1, uri.length()).equalsIgnoreCase("/")) {
                log.debug("Login");
                chain.doFilter(request, response);
            } else { //Uesr is not valid and tries to access some actions, send him to error page
                //This may happen when session expiers too
                log.debug("to access: " + uri);
                //The sendRedirect make sures that the images and css in Logon.jsp shows correctly.
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/pages/com/pdpsoft/error.jsp");
            }
        }

    }

    public void init(FilterConfig conf) throws ServletException {
        filterConfig = conf;
        excludedUrls = conf.getInitParameter("exclude").split(",");
    }

    /**
     * Exclude the url if it specified in the filter initlal parameter
     *
     * @param uri
     * @return
     */
    private boolean isExcludedUrl(String uri) {
        if (excludedUrls == null || excludedUrls.length == 0) {
            return true;
        }
        for (String exclude : excludedUrls) {
            if (StringUtils.contains(uri, exclude))
                return true;
        }
        return false;
    }

    public void destroy() {
        this.filterConfig = null;
        excludedUrls = null;
    }

}
