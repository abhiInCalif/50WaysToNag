package com.bday.service;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bday.model.FamilyModel;
import com.bday.model.TaskModel;
import com.bday.model.UserModel;
import com.bday.utils.Constants;
import com.bday.view.FamilyView;
import com.bday.view.NewFamilyView;
import com.bday.view.TaskView;
import com.bday.view.ViewManager;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/family")
public class FamilyService
{
	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpSession session, Model model)
	{
		NewFamilyView.create(session, model);
		return "JSONView";
	}

	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public String addTask(@RequestBody String request, HttpSession session, Model model)
	{
		// all the requirements to create the task object
		JsonObject jRequest = Constants.parse(request);
		String user_email = jRequest.get(Constants.ASSIGNEE).getAsString();
		String title = jRequest.get(Constants.TITLE).getAsString();
		String description = jRequest.get(Constants.DESC).getAsString();
		boolean isCompleted = jRequest.get(Constants.IS_COMPLETED).getAsBoolean();
		int nagStatus = jRequest.get(Constants.NAG_STATUS).getAsInt();
		TaskModel mTask = new TaskModel(title, description, nagStatus, isCompleted);
		UserModel user = (UserModel) session.getAttribute(Constants.USER);
		// defaults to the first family that the user is a part of
		int family_id = user.getFirstNotNullFamily().getId();

		// issue the request
		FamilyView.postTask(session, mTask, family_id, user_email, model);
		return "JSONView";
	}

	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public String getAllTasks(HttpSession session, Model model)
	{
		// hack around, needs to be refactored properlly
		// gets all the tasks for the given family
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// retrieve session object and get the family id (0)
		UserModel user = (UserModel) session.getAttribute(Constants.USER);
		user = (UserModel) sess.merge(user);
		
		FamilyModel family = user.getFirstNotNullFamily();
		
		if (family == null)
		{
			// create a new family
			// else create a new family, associate it and add it to the
			// grouping
			
			family = new FamilyModel();
			family.addMember(user);
			user.addFamily(family);
			
			sess.save(family);
			sess.update(user);
			tr.commit();
		}
		
		int id = family.getId();
		// issue request
		TaskView.getFamilyTasks(id, model);
		return "JSONView";
	}

	@RequestMapping(value="/members", method = RequestMethod.GET)
	public String getFamilyMembers(HttpSession session, Model model)
	{
		FamilyView.getMembers(session, model);
		return "JSONView";
	}

}
