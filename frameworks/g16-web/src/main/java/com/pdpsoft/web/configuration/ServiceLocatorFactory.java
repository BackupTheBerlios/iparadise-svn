package com.pdpsoft.web.configuration;

import com.pdpsoft.web.BusinessDelegate;
import com.pdpsoft.web.ServiceLocatorCommand;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Feb 23, 2009
 * Time: 3:48:33 PM
 */
public final class ServiceLocatorFactory {
	public final static ServiceLocatorCommand factory(final ServiceLocatorTypeEnum type) {
		ServiceLocatorCommand command = null;
		
		if(type.equals(ServiceLocatorTypeEnum.POJO))
			command = PojoServiceLocator.getInstance(); 

/*		if(type.equals(ServiceLocatorTypeEnum.EJBLocal))
			command = new 
*/			
		if(type.equals(ServiceLocatorTypeEnum.EJBRemote))
			command = BusinessDelegate.getInstance();
		return command;
	}
}
