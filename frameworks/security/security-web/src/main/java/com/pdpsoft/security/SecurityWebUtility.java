package com.pdpsoft.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.HashMap;
import com.pdpsoft.web.common.CommandFactory;
import com.pdpsoft.web.common.G16ChainContext;
import com.pdpsoft.web.common.G16ChainManager;
import com.pdpsoft.web.common.G16Chain;
import com.pdpsoft.web.common.commands.G16GenericWebCommand;
import com.pdpsoft.web.common.commands.WebCommandEnum;
import com.pdpsoft.PdpSoftWebStrutsAction;
import org.apache.commons.chain.Command;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 3, 2009
 * Time: 8:24:46 AM
 */
class SecurityWebUtility {
    final static Log LOG = LogFactory.getLog(SecurityWebUtility.class);
    
    /**
     *
     * @param form It's struts actionform
     * @param request Normal Request
     * @param response Normal Response
     * @return it has two different values:
     *          <i>failure-login-page</i> : whenever login fails
     *          <i>success-login-page</i> : when login done.
     */
    public static String signin(ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        final SecurityWebBeanContext webBeanContext = new SecurityWebBeanContext();
        final SecurityFormBean bean = (SecurityFormBean) form;
        final String loginStatus = "loginStatus";

        G16ChainContext g16ChainContext = new G16ChainContext(request, response);
        g16ChainContext.setWebBeanContext(webBeanContext);
        G16ChainManager chainManager = new G16ChainManager() {
            public boolean run(G16ChainContext ctx) throws Exception {
                Map<Integer, Object> map = new HashMap<Integer, Object>(1);                
                map.put(0, SecurityAlgorithm.seal(bean.getSystemUserEntity()));

                ctx.put(G16GenericWebCommand.ASPECT_NAME, "ParadiseAuthenticationAuthorizationService");
                ctx.put(G16GenericWebCommand.PARAMETER, map);
                ctx.put(G16GenericWebCommand.ACTIONFORM_PROP_NAME, "authorizationCustomEntity");
                /*
                  This variable is used to determine that does any exception
                  occured during login, the default is TRUE
                 */
                ctx.put(loginStatus, true);

                try {
                    Command command = CommandFactory.lookForCommand(WebCommandEnum.PROCESS);
                    G16Chain chain = new G16Chain();
                    chain.addCommand(command);
                    chain.execute(ctx);
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                    /*
                        It means the process has some exceptions, hence the forward should change.
                     */
                    ctx.put(loginStatus, false);
                    initCombo(bean);
                }
                return false;
            }
        };
        try {
            chainManager.run(g16ChainContext);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        LOG.info("The process of authentication and authorization is done.");

        String mapForward = "failure-login-page";
        /*
            If there isn't any error, hence we should redirect to application
         */
        if(Boolean.valueOf(String.valueOf(g16ChainContext.get(loginStatus)))) {
            /**
             * Try to put the authorization and authentication in threadlocal
             */
            setSecurityContext(webBeanContext, request);
            mapForward = "success-login-page";
        }

        LOG.info("System is redirecting to ".concat(mapForward));
        return mapForward;
    }

    /**
     * Set the SecurityContext in ThreadLocal Pattern
     * @param webBeanContext
     */
    public static void setSecurityContext(SecurityWebBeanContext webBeanContext, HttpServletRequest request) {
        LOG.info("Web Security Context ....");
        AuthorizationCustomEntity customEntity = webBeanContext.getAuthorizationCustomEntity();
        LOG.info("webBeanContext.getAuthorizationCustomEntity() is " + webBeanContext.getAuthorizationCustomEntity());

        SecurityContextHolder.getContext().setAuthentication(customEntity);
        LOG.info("SecurityContextHolder.getContext().getAuthentication() is " + SecurityContextHolder.getContext().getAuthentication());

        HttpSession session = request.getSession();
        session.setAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT, customEntity.getSystemUserEntity());
        session.setAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT_AUTHORIZATION, customEntity.getSystemActionEntities());
        session.setAttribute(PdpSoftWebStrutsAction.SECURITY_FILTER_APPLIED, Boolean.TRUE);
    }

    public static void initCombo(SecurityFormBean bean) {
        bean.setSchemas(WebLoginModuleContext.getObjects());
    }

}
