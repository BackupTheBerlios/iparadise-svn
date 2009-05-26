package com.pdpsoft.sample;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Result;

import com.opensymphony.xwork2.Preparable;
import com.pdpsoft.web.common.G16Chain;
import com.pdpsoft.web.common.G16ChainContext;
import com.pdpsoft.web.common.G16ChainManager;
import com.pdpsoft.web.common.Struts2Action;
import com.pdpsoft.web.common.commands.G16GenericWebCommand;
import com.pdpsoft.web.common.commands.WebCommandEnum;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Mar 2, 2009
 * Time: 5:08:35 PM
 */
@Result(name = "SUCCESS", value = "/sample/HelloWorld.jsp")
public class PersistAction 
				extends Struts2Action
				implements Preparable{
	Table1Entity entity = null;
	SampleWebBeanContext beanContext = new SampleWebBeanContext();
	
	public void prepare() throws Exception {
		entity = new Table1Entity();
		entity.setCol2("persisted from application");
		entity.setCol3(1);
		beanContext.setTable1Entity(entity);
	}

	@Override
	public String execute() throws Exception {		
		G16ChainContext chainContext = new G16ChainContext(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		chainContext.put(G16GenericWebCommand.ENTITY, beanContext.getTable1Entity());
		G16ChainManager chainManager = new G16ChainManager(){
			public boolean run(G16ChainContext ctx) throws Exception {				
				lookForCommand(WebCommandEnum.PERSIST).execute(ctx);
				return true;
			}			
		};
		chainManager.run(chainContext);
		return "SUCCESS";
	}
}
