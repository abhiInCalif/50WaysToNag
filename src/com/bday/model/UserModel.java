package com.bday.model;

import java.util.List;

public class UserModel 
{
	private int id;
	private String username;
	private String password;
	private List<TaskModel> tasks;
	private List<FamilyModel> families;
	private List<InviteModel> invitations;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the tasks
	 */
	public List<TaskModel> getTasks() {
		return tasks;
	}
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<TaskModel> tasks) {
		this.tasks = tasks;
	}
	/**
	 * @return the families
	 */
	public List<FamilyModel> getFamilies() {
		return families;
	}
	/**
	 * @param families the families to set
	 */
	public void setFamilies(List<FamilyModel> families) {
		this.families = families;
	}
	/**
	 * @return the invitations
	 */
	public List<InviteModel> getInvitations() {
		return invitations;
	}
	/**
	 * @param invitations the invitations to set
	 */
	public void setInvitations(List<InviteModel> invitations) {
		this.invitations = invitations;
	}
	
	public void addFamily(FamilyModel newFamily) {
		this.families.add(newFamily);
	}
	
	
}
