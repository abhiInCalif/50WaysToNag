package com.bday.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel 
{
	private int id;
	private String email;
	private String name;
	private String password;
	private String phoneNumber;
	private transient List<TaskModel> tasks;
	private transient List<FamilyModel> families;
	private List<InviteModel> invitations;
	
	public UserModel()
	{
		this.tasks = new ArrayList<TaskModel>();
		this.families = new ArrayList<FamilyModel>();
		this.invitations = new ArrayList<InviteModel>();
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the username
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param username the username to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	
	public void addTask(TaskModel mTask) {
		this.tasks.add(mTask);
	}

	public void addInvite(InviteModel mInvite) {
		this.invitations.add(mInvite);
	}

	public FamilyModel getFirstNotNullFamily() 
	{
		for (int i = 0; i < families.size(); i++)
		{
			if (families.get(i) != null)
				return families.get(i);
		}
		
		// else create a new family, associate it and add it to the
		// grouping
		FamilyModel family = new FamilyModel();
		family.addMember(this);
		addFamily(family);
		
		return family;
	}
	
	
}
