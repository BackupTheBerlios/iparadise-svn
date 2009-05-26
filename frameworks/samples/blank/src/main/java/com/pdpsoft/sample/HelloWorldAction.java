package com.pdpsoft.sample;

import org.apache.struts2.config.Action;
import org.apache.struts2.config.Result;

import com.opensymphony.xwork2.Preparable;
import com.pdpsoft.web.common.Struts2Action;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Mar 2, 2009
 * Time: 10:44:21 AM
 */
@Result(name = "SUCCESS", value = "/sample/HelloWorld.jsp")
public class HelloWorldAction 
					extends Struts2Action
					implements Preparable{

	private String message;
	
	public void prepare() throws Exception {
		setMessage("Hello World!!!");
		
	}
	@Override
	public String execute() throws Exception {
		System.out.println("HelloWorldAction.execute");
		return "SUCCESS";
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}			
}
