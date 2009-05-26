package com.pdpsoft.web.common;

import javax.servlet.*;
import java.io.IOException;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Sep 8, 2006
 * Time: 11:19:32 AM
 */
public class SetCharacterEncodingFilter implements Filter {
    protected String encoding = null;
    protected FilterConfig filterConfig = null;

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        String encoding = selectEncoding(request);
        if (encoding != null)
            request.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        this.encoding = "utf-8";
    }

    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }
}