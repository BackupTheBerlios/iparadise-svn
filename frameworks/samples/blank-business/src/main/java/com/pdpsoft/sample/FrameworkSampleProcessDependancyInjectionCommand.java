/**
 * 
 */
package com.pdpsoft.sample;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Mar 3, 2009
 * Time: 6:19:36 PM
 */
/**
 * @author Chris Shayan
 *
 */
public class FrameworkSampleProcessDependancyInjectionCommand implements
		G16CommonBusinessCommand {

	/* (non-Javadoc)
	 * @see com.pdpsoft.commonbiz.util.G16CommonBusinessCommand#execute(java.lang.Object)
	 */
	public Object execute(Object arg0) throws G16CommonBusinessException,
			HibernateEXC {
		System.out.println("com.pdpsoft.sample.FrameworkSampleProcessDependancyInjectionCommand executed!!!!");
		return null;
	}

}
