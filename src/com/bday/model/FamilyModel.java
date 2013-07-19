package com.bday.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abhinavkhanna
 *
 */
public class FamilyModel {
	
	private int id;
	private List<UserModel> members;
	private List<TaskModel> tasks;
	
	public FamilyModel()
	{
		this.members = new ArrayList<UserModel>();
		this.tasks = new ArrayList<TaskModel>();
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
	 * @return the members
	 */
	public List<UserModel> getMembers() {
		return members;
	}
	/**
	 * @param members the members to set
	 */
	public void setMembers(List<UserModel> members) {
		this.members = members;
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
	
	public void addMember(UserModel user) {
		this.members.add(user);
	}
	
	public void addTask(TaskModel mTask) {
		this.tasks.add(mTask);
	}
	
	

}
