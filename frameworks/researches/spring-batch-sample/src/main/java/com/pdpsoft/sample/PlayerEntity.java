package com.pdpsoft.sample;

import java.io.Serializable;

public class PlayerEntity implements Serializable {
	private String id;
	private String lastName;
	private String firstName;
	private String position;
	private int birthYear;
	private int debutYear;		
	
	public PlayerEntity() {
		super();
	}

	public PlayerEntity(String id, String lastName, String firstName,
			String position, int birthYear, int debutYear) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.position = position;
		this.birthYear = birthYear;
		this.debutYear = debutYear;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the birthYear
	 */
	public int getBirthYear() {
		return birthYear;
	}

	/**
	 * @param birthYear the birthYear to set
	 */
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * @return the debutYear
	 */
	public int getDebutYear() {
		return debutYear;
	}

	/**
	 * @param debutYear the debutYear to set
	 */
	public void setDebutYear(int debutYear) {
		this.debutYear = debutYear;
	}
	
	
}
