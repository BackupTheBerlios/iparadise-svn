package com.pdpsoft.g16.business.sessionbean;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;

import java.util.List;

public interface LocalEJBInvocatorSession extends javax.ejb.EJBLocalObject {

    /**
     * Invoke the given method of the session bean specified with the jndi name
     *
     * @param jndiName   the jndiName of the SessionBean
     * @param methodName the name of the method of the SessionBean
     * @return the result of SessionBean method call
     * @throws BusinessException           in case of BusinessException in the body of SessionBean method
     * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
     */
    Object invokeSessionBean(String jndiName, String methodName) throws BusinessInvocationException, BusinessException;

    /**
     * Invoke the given method of the session bean specified with the jndi name, with given arguments
     *
     * @param jndiName   the jndiName of the SessionBean
     * @param methodName the name of the method of the SessionBean
     * @param param      the argument to pass to SessionBean method
     * @return the result of SessionBean method call
     * @throws BusinessException           in case of BusinessException in the body of SessionBean method
     * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
     */
    Object invokeSessionBean(String jndiName, String methodName, Object param) throws BusinessInvocationException, BusinessException;

    /**
     * Invoke the given method of the session bean specified with the jndi name, with given arguments
     *
     * @param jndiName   the jndiName of the SessionBean
     * @param methodName the name of the method of the SessionBean
     * @param param1     the first argument to pass to SessionBean method
     * @param param2     the second argument to pass to SessionBean method
     * @return the result of SessionBean method call
     * @throws BusinessException           in case of BusinessException in the body of SessionBean method
     * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
     */
    Object invokeSessionBean(String jndiName, String methodName, Object param1, Object param2) throws BusinessInvocationException, BusinessException;

    /**
     * Invoke the given method of the session bean specified with the jndi name, with given arguments
     *
     * @param jndiName   the jndiName of the SessionBean
     * @param methodName the name of the method of the SessionBean
     * @param params     the arguments to pass to SessionBean method
     * @return the result of SessionBean method call
     * @throws BusinessException           in case of BusinessException in the body of SessionBean method
     * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
     */
    Object invokeSessionBean(String jndiName, String methodName, List<Object> params) throws BusinessInvocationException, BusinessException;

    /**
     * This method will destroy the hibernate configuration,
     * It means that it would release the connection
     */
    void destroy();
}
