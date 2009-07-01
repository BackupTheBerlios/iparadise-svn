package com.pdpsoft.security;

import com.pdpsoft.PdpSoftWebStrutsAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 28, 2009
 * Time: 9:37:59 AM
 */
public class SecurityAction extends PdpSoftWebStrutsAction {
    final static Log LOG = LogFactory.getLog(SecurityAction.class);

    public ActionForward signinPage(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        SecurityFormBean bean = (SecurityFormBean) form;
        bean.setSchemas(WebLoginModuleContext.getObjects());
        return mapping.findForward("login-page");
    }
    public ActionForward signin(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        LOG.info("The process of authentication and authorization starts ....");
        SecurityFormBean bean = (SecurityFormBean) form;
        final String cityCode = bean.getCityCode();
        request.getSession().setAttribute(WEB_LOGIN_MODULE_CITY_CODE_CONTEXT, cityCode);
        LOG.debug("cityCode =" + cityCode);
        final String schemaName = WebLoginModuleContext.getSchemaName(cityCode);
        LOG.debug("schemaName =" + schemaName);
        request.getSession().setAttribute(WEB_LOGIN_MODULE_SCHEMA_CONTEXT, schemaName);
        LoginModuleContextHolder.getInstance().setSelectedSchemaName(schemaName);
        LoginModuleContextHolder.getInstance().setSelectedCityCode(cityCode);
        return mapping.findForward(
                SecurityWebUtility.signin(form, request, response)
        );
    }

    public ActionForward printUsername(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
//        SecurityContext context = SecurityContextHolder.getContext();
        SystemUserEntity systemUserEntity = (SystemUserEntity) request.getSession().getAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT);
        List<SystemActionEntity> actionEntities = (List<SystemActionEntity>) request.getSession().getAttribute(PdpSoftWebStrutsAction.SECURITY_CONTEXT_AUTHORIZATION);

        LOG.info("SecurityContext in action is " + actionEntities);
        LOG.info("AuthorizationCustomEntity in action is" + actionEntities);
        LOG.info("The logged user is :".concat(systemUserEntity.getUserName()));
        SystemUserEntity argument = new SystemUserEntity();
        LOG.info("The username within object is :".concat(argument.getUserNameIdentifier()) );
        return mapping.findForward("success-login-page");
    }

    public ActionForward signout(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        return mapping.findForward("login-page");
    }
}
