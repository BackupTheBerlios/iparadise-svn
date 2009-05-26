package com.pdpsoft.web;

import java.util.List;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Feb 23, 2009
 * Time: 4:03:19 PM
 */
public interface ServiceLocatorCommand {

	/**
	 * Invoke the given method of the session bean specified with the jndi name, with given arguments
	 * on the specified ejb container
	 *
	 * @param jndiName   the jndiName of the SessionBean
	 * @param methodName the name of the method of the SessionBean
	 * @param param      the argument to pass to SessionBean method
	 * @return the result of SessionBean method call
	 * @throws BusinessException           in case of BusinessException in the body of SessionBean method
	 * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
	 */
	public abstract Object invokeBusiness(final String jndiName,
			final String methodName, final Object param)
			throws BusinessException, BusinessInvocationException;

	/**
	 * Invoke the given method of the session bean specified with the jndi name, with given arguments
	 * on the specified ejb container
	 *
	 * @param jndiName   the jndiName of the SessionBean
	 * @param methodName the name of the method of the SessionBean
	 * @param param1     the first argument to pass to SessionBean method
	 * @param param2     the second argument to pass to SessionBean method
	 * @return the result of SessionBean method call
	 * @throws BusinessException           in case of BusinessException in the body of SessionBean method
	 * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
	 */
	public abstract Object invokeBusiness(final String jndiName,
			final String methodName, final Object param1, final Object param2)
			throws BusinessException, BusinessInvocationException;

	/**
	 * Invoke the given method of the session bean specified with the jndi name, with given arguments
	 * on the specified ejb container
	 *
	 * @param jndiName   the jndiName of the SessionBean
	 * @param methodName the name of the method of the SessionBean
	 * @param params     the arguments to pass to SessionBean method
	 * @return the result of SessionBean method call
	 * @throws BusinessException           in case of BusinessException in the body of SessionBean method
	 * @throws BusinessInvocationException in case of Exception while trying to call SessionBean Method
	 */
	public abstract Object invokeBusiness(final String jndiName,
			final String methodName, final List<Object> params)
			throws BusinessException, BusinessInvocationException;

}