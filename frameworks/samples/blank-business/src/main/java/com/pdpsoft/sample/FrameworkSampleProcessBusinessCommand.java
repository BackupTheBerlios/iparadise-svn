/**
 * 
 */
package com.pdpsoft.sample;

import java.util.Map;

import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.business.PersistenceManagerFactory;
import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;
//import com.pdpsoft.commonbiz.util.applicationcontex.G16ApplicationContextBeans;
/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Mar 3, 2009
 * Time: 5:53:02 PM
 */
/**
 * @author Chris Shayan
 *
 */
public class FrameworkSampleProcessBusinessCommand 		
			implements G16CommonBusinessCommand {
	PersistenceManager persistenceManager = PersistenceManagerFactory.getDefault();
	G16CommonBusinessCommand sampleDI;
	/* (non-Javadoc)
	 * @see com.pdpsoft.commonbiz.util.G16CommonBusinessCommand#execute(java.lang.Object)
	 */
	public Object execute(Object arg0) throws G16CommonBusinessException,
			HibernateEXC {		
		Table1Entity entity = null;
		try {
			Map<Integer, Table1Entity> map = (Map<Integer, Table1Entity>) arg0;
			System.out.println("In business process command and value is"
					+ map.get(0).getCol2());
			sampleDI.execute(null);
			entity = new Table1Entity();
			entity.setCol1(4);
			entity.setCol2("passed from Spring business");
			entity.setCol3(1);
		} catch (Exception e) {
			System.out.println("***********************");
			e.printStackTrace();
		}
		return entity;
	}
	/**
	 * 
	 * @param sampleDI
	 */
	public void setSampleDI(G16CommonBusinessCommand sampleDI) {
		this.sampleDI = sampleDI;
	}	
}
