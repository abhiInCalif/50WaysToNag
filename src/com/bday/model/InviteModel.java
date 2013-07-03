package com.bday.model;

public class InviteModel {
	
	private int id;
	private String message;
	private FamilyModel family;
	private UserModel asker;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the family
	 */
	public FamilyModel getFamily() {
		return family;
	}
	/**
	 * @param family the family to set
	 */
	public void setFamily(FamilyModel family) {
		this.family = family;
	}
	/**
	 * @return the asker
	 */
	public UserModel getAsker() {
		return asker;
	}
	/**
	 * @param asker the asker to set
	 */
	public void setAsker(UserModel asker) {
		this.asker = asker;
	}
	
	

}
