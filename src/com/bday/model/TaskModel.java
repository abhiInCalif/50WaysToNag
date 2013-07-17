package com.bday.model;

public class TaskModel {
	
	private int id;
	private UserModel assignee;
	private String details;
	private String title;
	private boolean isCompleted;
	private int nagStatus;
	
	public TaskModel(String title, String description, int nagStatus, boolean isCompleted) {
		this.title = title;
		this.details = description;
		this.isCompleted = isCompleted;
		this.nagStatus = nagStatus;
	}
	
	public TaskModel()
	{
		// empty default constructor for all other cases
	}
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
	 * @return the assignee
	 */
	public UserModel getAssignee() {
		return assignee;
	}
	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(UserModel assignee) {
		this.assignee = assignee;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the isCompleted
	 */
	public boolean getisCompleted() {
		return isCompleted;
	}
	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setisCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	/**
	 * @return the nagStatus
	 */
	public int getNagStatus() {
		return nagStatus;
	}
	/**
	 * @param nagStatus the nagStatus to set
	 */
	public void setNagStatus(int nagStatus) {
		this.nagStatus = nagStatus;
	}
	
	

}
