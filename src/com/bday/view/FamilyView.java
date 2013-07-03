package com.bday.view;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;

import com.bday.model.FamilyModel;
import com.bday.model.TaskModel;
import com.bday.model.UserModel;
import com.bday.utils.Constants;
import com.google.gson.Gson;

public class FamilyView {

	public static void postTask(TaskModel mTask, int family_id, Model model) {
		// adds a task to the given Family object
		// step 1, grab the family object from the id;
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		FamilyModel family = (FamilyModel) sess.get(FamilyModel.class, family_id);
		
		// step 2, add the task and save the object
		family.addTask(mTask);
		sess.update(family);
		tr.commit();
		
		// Gson the family object and return it;
		Constants.toJson(family, model);
	}

	public static void get(int family_id, Model model) {
		// gets all the tasks for the given family
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		FamilyModel family = (FamilyModel) sess.get(FamilyModel.class, family_id);
		
		// Gson that bitch
		Constants.toJson(family, model);
	}
	
	// The session is going to be used to draw the user object from which one can add to
	// the family object the user;
	public static void addMemberToHome(int family_id, HttpSession session, Model model) {
		// step 1, retrieve the home
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		FamilyModel family = (FamilyModel) sess.get(FamilyModel.class, family_id);
		
		// Step 2, get the user from the session;
		UserModel user = (UserModel) session.getAttribute(Constants.USER);
		user.addFamily(family);
		user = (UserModel) sess.merge(user);
		
		// add the user to the family and save
		family.addMember(user);
		sess.update(family);
		tr.commit();
		
		Constants.toJson(family, model);
	}
	

}
