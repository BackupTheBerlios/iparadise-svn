/**
 * Ashnaco - G16
 * User: d_farid, h_shayan
 * Date: Nov 1, 2006
 * Time: 10:23:11 AM
 */
package com.pdpsoft.business.sessionbean;

import com.pdpsoft.business.BusinessException;
import com.pdpsoft.business.BusinessInvocationException;
import com.pdpsoft.commons.InitializationException;
import org.apache.commons.beanutils.MethodUtils;

import javax.ejb.EJBHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class EJBInvocatorUtility {
    //cache map
    private static final ConcurrentHashMap<String, Object> businessCache =
            new ConcurrentHashMap<String, Object>();
    public static final String DEFAULT_METHOD_NAME = "process";
    private transient final InitialContext context;

    private static class EJBInvocatorUtilityHolder {//NOPMD
        private static EJBInvocatorUtility invocatorUtility;

        static {
            try {
                invocatorUtility = new EJBInvocatorUtility();
            } catch (NamingException e) {
                throw new InitializationException("NamingException while initializing context ", e);
            }
        }
    }

    public static EJBInvocatorUtility getInstance() {
        return EJBInvocatorUtilityHolder.invocatorUtility;
    }

    EJBInvocatorUtility() throws NamingException {
        context = new InitialContext();
    }

    /**
     * Call the Ejb with reflection
     *
     * @param jndiName   the jndiName of the SessionBean
     * @param methodName the name of the method of the SessionBean
     * @param params     the arguments to pass to SessionBean method
     * @return the result of SessionBean method call
     * @throws com.pdpsoft.business.BusinessException
     *          in case of BusinessException in the body of SessionBean method
     * @throws com.pdpsoft.business.BusinessInvocationException
     *          in case of Exception while trying to call SessionBean Method
     */
    public Object callEJBBusiness(final String jndiName, final String methodName, final List<Object> params) throws BusinessException, BusinessInvocationException {
        Object ejb;
        try {
            ejb = retrieveBusinessFromCache(jndiName);
            String str = methodName;
            if (str == null) {
                str = DEFAULT_METHOD_NAME;
            }
            List<Object> arguments = new ArrayList<Object>(params);
            if (params == null) {
                arguments = new ArrayList<Object>(0);
            }
            return MethodUtils.invokeMethod(ejb, str, arguments.toArray());
        } catch (IllegalAccessException e) {
            throw new BusinessInvocationException(e);
        } catch (NoSuchMethodException e) {
            throw new BusinessInvocationException(e);
        } catch (NamingException e) {
            throw new BusinessInvocationException(e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof BusinessException) {
                throw(BusinessException) e.getCause();
            } else {
                if (e.getCause() instanceof RemoteException) {
                    invalidateSessionBeanInstanceInCache(jndiName);
                }
            }
            throw new BusinessException(e.getCause());
        }
    }


    /**
     * Retrieve the instance of the SessionBean from cache or if not available
     * Create an instance of the SessionBean and put it in cache
     *
     * @param jndiName the jndiName of the SessionBean
     * @return the SessionBean instance with the given jndiName
     * @throws NoSuchMethodException     in case of Exception while trying to call create method of
     *                                   SessionBean Home
     * @throws IllegalAccessException    in case of Exception while trying to call create method of
     *                                   SessionBean Home
     * @throws NamingException           in case of exception while looking up the jndiName
     * @throws InvocationTargetException in case of Exception while trying to call create method of
     *                                   SessionBean Home
     */
    public Object retrieveBusinessFromCache(final String jndiName) throws NoSuchMethodException, IllegalAccessException, NamingException, InvocationTargetException {
        Object business = businessCache.get(jndiName);
        if (business == null) {
            business = lookupEjb(jndiName);
            businessCache.put(jndiName, business);
        }
        return business;
    }

    private void invalidateSessionBeanInstanceInCache(final String jndiName) {
        businessCache.remove(jndiName);
    }

    private Object lookupEjb(final String businessName) throws NamingException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final EJBHome ejbHome = (EJBHome) context.lookup(businessName);
        return MethodUtils.invokeMethod(ejbHome, "create", null);
    }
}
