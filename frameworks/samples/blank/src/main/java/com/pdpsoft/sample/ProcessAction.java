package com.pdpsoft.sample;

import java.util.HashMap;
import java.util.Map;

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
public class ProcessAction 
				extends Struts2Action
				implements Preparable{
	Table1Entity entity = null;
	SampleWebBeanContext beanContext = new SampleWebBeanContext();
	
	public void prepare() throws Exception {
		entity = new Table1Entity();
		entity.setCol1(4);
		entity.setCol2("passed from Struts 2 Action");
		entity.setCol3(1);
		beanContext.setTable1Entity(entity);
	}

	@Override
	public String execute() throws Exception {		
		G16ChainContext chainContext = new G16ChainContext(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		chainContext.setWebBeanContext(beanContext);
		G16ChainManager chainManager = new G16ChainManager(){
			public boolean run(G16ChainContext ctx) throws Exception {				
				Map<Integer, Table1Entity> map = new HashMap<Integer, Table1Entity>(1);
				map.put(0, entity);
		        ctx.put(G16GenericWebCommand.ASPECT_NAME, "FrameworkSampleProcessBusinessCommand");
		        ctx.put(G16GenericWebCommand.PARAMETER, map);
		        ctx.put(G16GenericWebCommand.ACTIONFORM_PROP_NAME, "table1Entity");
				lookForCommand(WebCommandEnum.PROCESS).execute(ctx);
				return true;
			}			
		};
		chainManager.run(chainContext);
		System.out.println("The value retrieved is :" + beanContext.getTable1Entity().getCol2());
		return "SUCCESS";
	}
}
