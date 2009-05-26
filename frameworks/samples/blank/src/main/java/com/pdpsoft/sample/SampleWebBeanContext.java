package com.pdpsoft.sample;

import java.util.List;

import com.pdpsoft.web.common.WebBeanContext;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Mar 3, 2009
 * Time: 10:04:17 AM
 */
public class SampleWebBeanContext extends WebBeanContext {
	private Table1Entity table1Entity;
	private List<Table1Entity> table1Entities; 
	
	public SampleWebBeanContext() {
	}
	
	public Table1Entity getTable1Entity() {
		return table1Entity;
	}

	public void setTable1Entity(Table1Entity table1Entity) {
		this.table1Entity = table1Entity;
	}

	public List<Table1Entity> getTable1Entities() {
		return table1Entities;
	}

	public void setTable1Entities(List<Table1Entity> table1Entities) {
		this.table1Entities = table1Entities;
	}
	
}
