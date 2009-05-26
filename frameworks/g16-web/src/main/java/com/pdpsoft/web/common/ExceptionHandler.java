package com.pdpsoft.web.common;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;
import com.pdpsoft.commons.G16Exception;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Sep 4, 2006
 * Time: 2:55:46 PM
 */
public final class ExceptionHandler {
    WebException webException = null;
    HttpServletRequest request = null;
    ActionMapping mapping = null;

    protected static Log log = LogFactory.getLog(ExceptionHandler.class);

    public ExceptionHandler(WebException webException, HttpServletRequest request, ActionMapping mapping) {
        this.webException = webException;
        this.request = request;
        this.mapping = mapping;
    }


    /**
     * Handle the message and generate approperiate WebException from the given message number
     *
     * @param messageNumber The number of specific message
     * @param request       ServletRequest
     * @param mapping       Struts ActionMapping
     * @return ActionForward for exception
     */
    public static ActionForward handleMessage(int messageNumber, HttpServletRequest request, ActionMapping mapping) {
        WebException webException = new WebException("Succedd Message");
        webException.setErrorNumber(messageNumber);
        System.out.println("ExceptionHandler.handleMessage");
        System.out.println("webException = " + webException);
        ExceptionHandler exceptionHandler = new ExceptionHandler(webException, request, mapping);
        return exceptionHandler.handleException();
    }

    /**
     * Handle the exception and generate approperiate WebException from the given exception
     *
     * @param e       the thrown Exception
     * @param request ServletRequest
     * @param mapping Struts ActionMapping
     * @return ActionForward for exception
     */
    public static ActionForward handleException(G16Exception e, HttpServletRequest request, ActionMapping mapping) {
        WebException webException;
        if (e instanceof WebException) {
            webException = (WebException) e;
        } else {
            webException = new WebException(e.getMessage());
            webException.setErrorNumber(e.getErrorNumber());
            log.error(webException);
            log.error(webException.getErrorNumber());
        }
        ExceptionHandler exceptionHandler = new ExceptionHandler(webException, request, mapping);
        return exceptionHandler.handleException();
    }

    public ActionForward handleException() {
        ErrorCatalogTransferObject to;

        ErrorCatalogImpl errorCatalog = new ErrorCatalogImpl();
        to = errorCatalog.getErrorCatalog(webException);

        String customerKey = to.getCustomerKey();
        if (customerKey == null) {
            customerKey = "Com.AshnaCo.G16.Bundle.CMS.exceptions.Errors";
        }

        ActionErrors errors = new ActionErrors();
        ActionError error;
        Object[] params = webException.getMessageParameters();
        if (params == null)
            error = new ActionError(customerKey);
        else
            error = new ActionError(customerKey, params);
        errors.add(ActionErrors.GLOBAL_ERROR, error);
        saveErrors(request, errors);
        return (mapping.findForward(to.getDestination()));
    }

    /**
     * Save the specified error messages keys into the appropriate request
     * attribute for use by the &lt;html:errors&gt; tag, if any messages
     * are required.  Otherwise, ensure that the request attribute is not
     * created.
     *
     * @param request The servlet request we are processing
     * @param errors  Error messages object
     */
    protected void saveErrors(HttpServletRequest request,
                              ActionErrors errors) {

        // Remove any error messages attribute if none are required
        if ((errors == null) || errors.isEmpty()) {
            request.removeAttribute(Globals.ERROR_KEY);
//            return;
        }

        // Save the error messages we need
//        request.getSession().setAttribute(Globals.ERROR_KEY, errors);
        request.setAttribute(Globals.ERROR_KEY, errors);
    }
}
