package com.bday.view;

import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;



import com.bday.model.FamilyModel;
import com.bday.model.TaskModel;
import com.bday.model.UserModel;
import com.bday.utils.Constants;
import com.google.gson.reflect.TypeToken;

public class TaskView {

	public static void getUserTasks(HttpSession session, Model model) 
	{
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// step one, retrieve the user from the session
		UserModel session_user = (UserModel) session.getAttribute(Constants.USER);
		
		// step two, use the id of the user to query for an updated
		// userObject
		UserModel user = (UserModel) sess.get(UserModel.class, session_user.getId());
		
		// step three retrieve all the tasks from the user object
		List<TaskModel> tasks = user.getTasks();
		
		Type taskType = new TypeToken<List<TaskModel>>() {}.getType();
		Constants.toJson(tasks, taskType, model);
	}
	
	public static void getFamilyTasks(int family_id, Model model)
	{
		// gets all the tasks for the given family
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		FamilyModel family = (FamilyModel) sess.get(FamilyModel.class, family_id);
		
		if (family == null) throw new RuntimeException("No family created. please create a family first.");
		
		// Gson that bitch
		Constants.toJson(family.getTasks(), model);
		tr.commit();
	}

}
