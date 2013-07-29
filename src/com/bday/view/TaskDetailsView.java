package com.bday.view;

import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;

import com.bday.manager.EmailManager;
import com.bday.manager.InsultManager;
import com.bday.model.TaskModel;
import com.bday.model.UserModel;
import com.bday.utils.Constants;
import com.google.gson.reflect.TypeToken;

public class TaskDetailsView {

	public static void nag(HttpSession session, int id, Model model) {
		// send an email to their email account and to their registered phone number
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		TaskModel task = (TaskModel) sess.get(TaskModel.class, id);
		UserModel user = task.getAssignee();
		int nagLevel = task.getNagStatus();
		task.setNagStatus(++nagLevel);
		
		// send the message
		String message = InsultManager.indianInsults[nagLevel % InsultManager.indianInsults.length];
		EmailManager email = new EmailManager(user.getEmail(), message, EmailManager.SUBJECT);
		email.sendEmail();
		String phone = user.getPhoneNumber();
		phone = phone.replaceAll("-", "");
		email = new EmailManager(phone, message, EmailManager.SUBJECT); 
		email.sendEmail();
		
		Constants.toJson(task, model);
		tr.commit();
	}

	public static void assignTask(String username, int id, Model model) {
		// TODO Auto-generated method stub
		
	}

	public static void put(HttpSession session, TaskModel mTask, int id, String user_email, Model model) {
		// update the task model in the database
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// step 1, set the assignee from the email
		UserModel user = (UserModel) sess.createQuery("from UserModel as user where user.email=?").setString(0, user_email).list().get(0);
		if(user == null) return; // error occured;
		
		mTask.setId(id);
		mTask.setAssignee(user);
		
		UserModel sess_user = (UserModel) session.getAttribute(Constants.USER);
		if(user.getEmail().equals(sess_user.getEmail()))
		{
			sess_user.addTask(mTask);
			session.setAttribute(Constants.USER, sess_user);
		}
		
		// step 2, merge the task with the database task
		sess.merge(mTask);
		
		Type taskType = new TypeToken<TaskModel>() {}.getType();
		Constants.toJson(mTask, taskType, model);
		tr.commit();
	}

	public static void get(int id, Model model) {
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		TaskModel task = (TaskModel) sess.get(TaskModel.class, id);
		
		Constants.toJson(task, model);
		tr.commit();
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
