package com.pdpsoft.web.configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;

import com.pdpsoft.commonbiz.GenericPojoCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;
import com.pdpsoft.web.ServiceLocatorCommand;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Feb 23, 2009
 * Time: 4:31:59 PM
 * @author Chris Shayan
 *
 */
public class PojoServiceLocator implements ServiceLocatorCommand {

	GenericPojoCommand command = GenericPojoCommand.getInstance();

	private PojoServiceLocator(){}

	private static class PojoServiceLocatorHolder {
		private static PojoServiceLocator locator = new PojoServiceLocator();
	}

	public static PojoServiceLocator getInstance() {
		return PojoServiceLocatorHolder.locator;
	}

	/* (non-Javadoc)
	 * @see com.pdpsoft.web.ServiceLocatorCommand#invokeBusiness(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public Object invokeBusiness(String jndiName, String methodName,
			Object param) throws BusinessException, BusinessInvocationException {
        final List<Object> list = new ArrayList<Object>(1);
        list.add(param);
        return invocation(methodName, list);
	}

	/* (non-Javadoc)
	 * @see com.pdpsoft.web.ServiceLocatorCommand#invokeBusiness(java.lang.String, java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public Object invokeBusiness(String jndiName, String methodName,
			Object param1, Object param2) throws BusinessException,
			BusinessInvocationException {
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(param1);
		arguments.add(param2);
        return invocation(methodName, arguments);
	}

	/* (non-Javadoc)
	 * @see com.pdpsoft.web.ServiceLocatorCommand#invokeBusiness(java.lang.String, java.lang.String, java.util.List)
	 */
	public Object invokeBusiness(String jndiName, String methodName,
			List<Object> params) throws BusinessException,
			BusinessInvocationException {
		List<Object> arguments = new ArrayList<Object>(params);
        if (params == null) {
            arguments = new ArrayList<Object>(0);
        }
        return invocation(methodName, arguments);
	}

	private Object invocation(String methodName, List<Object> arguments)
			throws BusinessInvocationException, BusinessException {
		try {
			return MethodUtils.invokeMethod(getInstance().command, methodName,
					arguments.toArray());
        } catch (IllegalAccessException e) {
            throw new BusinessInvocationException(e);
        } catch (NoSuchMethodException e) {
            throw new BusinessInvocationException(e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof BusinessException) {
                throw(BusinessException) e.getCause();
            }
            throw new BusinessException(e.getCause(), ((G16CommonBusinessException) e.getCause()).getErrorNumber());
        }
	}
}
