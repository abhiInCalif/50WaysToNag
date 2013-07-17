package com.bday.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bday.model.TaskModel;
import com.bday.utils.Constants;
import com.bday.view.FamilyView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/family/{id}")
public class FamilyTaskService {
	
	// post a new task under this family name
	@RequestMapping(method = RequestMethod.POST)
	public String post(@RequestBody String request, @PathVariable int id, Model model)
	{
		JsonObject jRequest = Constants.parse(request);
		String user_email = jRequest.get(Constants.ASSIGNEE).getAsString();
		String title = jRequest.get(Constants.TITLE).getAsString();
		String description = jRequest.get(Constants.DESC).getAsString();
		boolean isCompleted = jRequest.get(Constants.IS_COMPLETED).getAsBoolean();
		int nagStatus = jRequest.get(Constants.NAG_STATUS).getAsInt();
		TaskModel mTask = new TaskModel(title, description, nagStatus, isCompleted);
		FamilyView.postTask(mTask, id, user_email, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@PathVariable int id, Model model)
	{
		FamilyView.get(id, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String addMemberToHome(@PathVariable int id, HttpSession session, Model model)
	{
		FamilyView.addMemberToHome(id, session, model);
		return "JSONView";
	}
	
	

}
