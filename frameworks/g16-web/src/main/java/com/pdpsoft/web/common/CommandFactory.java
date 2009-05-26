package com.pdpsoft.web.common;

/*
import java.util.Iterator;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
*/
import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;
import com.pdpsoft.web.common.commands.*;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Aug 31, 2006
 * Time: 11:51:24 AM
 */
public class CommandFactory { //extends ActionSupport {
	/**
	 *
	 * @param commandEnum determine which Command should retrieve
	 * @return an instance of Command/Chain
	 */
	public static Command lookForCommand(final WebCommandEnum commandEnum) {
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
		if(commandEnum.equals(WebCommandEnum.SEARCH))
			command = new SearchWebCommand();
		return command;
	}

}
