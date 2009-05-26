package com.pdpsoft.web.common;

/*
import java.util.Iterator;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
*/
import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;
import com.pdpsoft.web.common.commands.FindByPrimaryKeyWebCommand;
import com.pdpsoft.web.common.commands.GetAllWebCommand;
import com.pdpsoft.web.common.commands.InsertWebCommand;
import com.pdpsoft.web.common.commands.PersistWebCommand;
import com.pdpsoft.web.common.commands.ProcessWebCommand;
import com.pdpsoft.web.common.commands.RemoveWebCommand;
import com.pdpsoft.web.common.commands.UpdateWebCommand;
import com.pdpsoft.web.common.commands.WebCommandEnum;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Aug 31, 2006
 * Time: 11:51:24 AM
 */
public class Struts2Action { //extends ActionSupport {
	/*
	 * default value of Apache COR in XML bindings
	 */
	public final static String CATALOG = "catalog";
	/**
	 *
	 * @param commandName determine which Command/Chain should retrieve
	 * @return an instance of Command/Chain
	 */
/*	public Command lookForCommand(final String commandName) {
		Catalog catalog = (Catalog)ServletActionContext.getRequest().getSession().getServletContext().getAttribute(CATALOG);
		if(commandName == null)
			throw new RuntimeException("There is not any command with the name of " + commandName);
		return catalog.getCommand(commandName);
	}*/

	/**
	 *
	 * @param commandEnum determine which Command should retrieve
	 * @return an instance of Command/Chain
	 * @deprecated use instead CommandFactory.java
	 */
	public Command lookForCommand(final WebCommandEnum commandEnum) {
		Command command = null;
		if(commandEnum.equals(WebCommandEnum.FIND_BY_PK))
			command = new FindByPrimaryKeyWebCommand();
		if(commandEnum.equals(WebCommandEnum.GET_ALL))
			command = new GetAllWebCommand();
		if(commandEnum.equals(WebCommandEnum.INSERT))
			command = new InsertWebCommand();
		if(commandEnum.equals(WebCommandEnum.PERSIST))
			command = new PersistWebCommand();
		if(commandEnum.equals(WebCommandEnum.PROCESS))
			command = new ProcessWebCommand();
		if(commandEnum.equals(WebCommandEnum.REMOVE))
			command = new RemoveWebCommand();
		if(commandEnum.equals(WebCommandEnum.UPDATE))
			command = new UpdateWebCommand();
		return command;
	}

}
