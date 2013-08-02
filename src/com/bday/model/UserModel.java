package com.bday.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bday.view.ViewManager;

public class UserModel 
{
	private int id;
	private String email;
	private String name;
	private transient String password;
	private String phoneNumber;
	private transient List<TaskModel> tasks;
	private transient List<FamilyModel> families;
	private transient List<InviteModel> invitations;
	
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
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		this.families = ((UserModel) sess.get(UserModel.class, this.id)).getFamilies();
		
		for (int i = 0; i < families.size(); i++)
		{
			if (families.get(i) != null)
				return families.get(i);
		}
		
		return null;
	}

	public void removeInvite(int inviteId) {
		// loop through invites remove the one with the same id;
		for (int i = 0; i < invitations.size(); i++)
		{
			InviteModel invite = invitations.get(i);
			if (invite.getId() == inviteId)
				invitations.remove(i);
		}
	}

	public void removeTask(int taskId) {
		// loop through tasks remove the one with the same id;
		for (int i = 0; i < tasks.size(); i++)
		{
			TaskModel task = tasks.get(i);
			if (task.getId() == taskId)
				tasks.remove(i);
		}
	}
	
	
}
