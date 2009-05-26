package com.pdpsoft.commonbiz;
/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Feb 21, 2009
 * Time: 3:07:06 PM
 */
public class Table1Entity {
	private Integer col1;
	private String col2;
	private Integer col3;
	
	public Table1Entity() {}

	public Table1Entity(Integer col1, String col2, Integer col3) {
		super();
		this.col1 = col1;
		this.col2 = col2;
		this.col3 = col3;
	}

	/**
	 * @return the col1
	 */
	public Integer getCol1() {
		return col1;
	}

	/**
	 * @param col1 the col1 to set
	 */
	public void setCol1(Integer col1) {
		this.col1 = col1;
	}

	/**
	 * @return the col2
	 */
	public String getCol2() {
		return col2;
	}

	/**
	 * @param col2 the col2 to set
	 */
	public void setCol2(String col2) {
		this.col2 = col2;
	}

	/**
	 * @return the col3
	 */
	public Integer getCol3() {
		return col3;
	}

	/**
	 * @param col3 the col3 to set
	 */
	public void setCol3(Integer col3) {
		this.col3 = col3;
	}
	
	
}
