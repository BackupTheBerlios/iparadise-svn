package com.pdpsoft.g16.business.sessionbean;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;

import java.rmi.RemoteException;
import java.util.List;

public interface EJBInvocatorSession extends javax.ejb.EJBObject {

    /**
     * Invoke the given method of the session bean specified with the jndi name
     *
     * @param jndiName   the jndiName of the SessionBean
     * @param methodName the name of the method of the SessionBean
     * @return the result of SessionBean method call
     * @throws BusinessException           in case of BusinessException in the body of SessionBean method
     * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
     * @throws java.rmi.RemoteException in case of RemoteException
     */
    Object invokeSessionBean(String jndiName, String methodName) throws RemoteException, BusinessInvocationException, BusinessException;

    /**
     * Invoke the given method of the session bean specified with the jndi name, with given arguments
     *
     * @param jndiName   the jndiName of the SessionBean
     * @param methodName the name of the method of the SessionBean
     * @param param      the argument to pass to SessionBean method
     * @return the result of SessionBean method call
     * @throws BusinessException           in case of BusinessException in the body of SessionBean method
     * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
     * @throws java.rmi.RemoteException in case of RemoteException
     */
    Object invokeSessionBean(String jndiName, String methodName, Object param) throws RemoteException, BusinessInvocationException, BusinessException;

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
     * @throws java.rmi.RemoteException  in case of RemoteException
     */
    Object invokeSessionBean(String jndiName, String methodName, Object param1, Object param2) throws RemoteException, BusinessInvocationException, BusinessException;

    /**
     * Invoke the given method of the session bean specified with the jndi name, with given arguments
     *
     * @param jndiName   the jndiName of the SessionBean
     * @param methodName the name of the method of the SessionBean
     * @param params     the arguments to pass to SessionBean method
     * @return the result of SessionBean method call
     * @throws BusinessException           in case of BusinessException in the body of SessionBean method
     * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
     * @throws java.rmi.RemoteException  in case of RemoteException
     */
    Object invokeSessionBean(String jndiName, String methodName, List<Object> params) throws RemoteException, BusinessInvocationException, BusinessException;

    /**
     * This method will destroy the hibernate configuration,
     * It means that it would release the connection.
     * @throws java.rmi.RemoteException  in case of RemoteException
     */
    void destroy() throws RemoteException;
}
