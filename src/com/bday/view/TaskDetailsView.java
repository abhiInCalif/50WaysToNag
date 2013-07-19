package com.bday.view;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;

import com.bday.model.TaskModel;
import com.bday.model.UserModel;
import com.bday.utils.Constants;

public class TaskDetailsView {

	public static void nag(int id, Model model) {
		// TODO Auto-generated method stub
		
	}

	public static void assignTask(String username, int id, Model model) {
		// TODO Auto-generated method stub
		
	}

	public static void put(TaskModel mTask, int id, String user_email, Model model) {
		// update the task model in the database
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// step 1, set the assignee from the email
		UserModel user = (UserModel) sess.createQuery("from UserModel as user where user.email=?").setString(0, user_email).list().get(0);
		if(user == null) return; // error occured;
		
		mTask.setId(id);
		mTask.setAssignee(user);
		
		// step 2, merge the task with the database task
		sess.merge(mTask);
		
		Constants.toJson(mTask, model);
		tr.commit();
	}

	public static void get(int id, Model model) {
		// TODO Auto-generated method stub
		
	}

	public static void delete(int id, Model model) 
	{
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		TaskModel mTask = (TaskModel) sess.get(TaskModel.class, id);
		
		sess.delete(mTask);
		tr.commit();
	}
	

}
