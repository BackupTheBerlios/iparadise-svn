package com.pdpsoft.web.common;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Aug 31, 2006
 * Time: 11:51:24 AM
 */
public abstract class PortalAction { //extends org.apache.struts.action.Action {
/*    private static final Log log = LogFactory.getLog(Action.class);

    public ActionForward execute(ActionMapping mapping,
                                 org.apache.struts.action.ActionForm actionForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        response.addHeader("charset", "utf-8");

        final String reqCode = request.getParameter("reqCode");

        if ((reqCode == null) || (reqCode.equals("")))
            return viewPortal(mapping, actionForm, request, response);

        Method method;
        try {
            method = getMethod(reqCode);
        } catch (NoSuchMethodException e) {
            log.error(e);
            throw e;
        }

        ActionForward forward;

        try {
            Object[] args = {mapping, actionForm, request, response};
            forward = (ActionForward) method.invoke(this, args);
        } catch (IllegalAccessException e) {
            log.error(e);
            throw e;
        } catch (IllegalArgumentException e) {
            log.error(e);
            throw e;
        } catch (InvocationTargetException e) {
            log.error(e);
            throw e;
        }

        return forward;
    }

    public abstract ActionForward viewPortal(ActionMapping mapping,
                                             org.apache.struts.action.ActionForm actionForm,
                                             HttpServletRequest request,
                                             HttpServletResponse httpServletResponse) throws Exception;

    *//**
     * The set of Method objects we have introspected for this class,
     * keyed by method name.  This collection is populated as different
     * methods are called, so that introspection needs to occur only
     * once per method name.
     *//*
    protected final Map<String, Method> methods = new HashMap<String, Method>();
    *//**
     * The set of argument type classes for the reflected method call.  These
     * are the same for all calls, so calculate them only once.
     *//*
    protected Class[] types = {
            ActionMapping.class,
            org.apache.struts.action.ActionForm.class,
            HttpServletRequest.class,
            HttpServletResponse.class
    };

    *//**
     * Introspect the current class to identify a method of the specified
     * name that accepts the same parameter types as the <code>execute</code>
     * method does.
     *
     * @param name Name of the method to be introspected
     * @return method
     * @throws NoSuchMethodException if no such method can be found
     *//*
    protected Method getMethod(String name)
            throws NoSuchMethodException {

        synchronized (methods) {
            Method method = methods.get(name);
            if (method == null) {
                method = this.getClass().getMethod(name, types);
                methods.put(name, method);
            }
            return (method);
        }
    }
*/}
