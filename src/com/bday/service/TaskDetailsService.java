package com.bday.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bday.model.TaskModel;
import com.bday.utils.Constants;
import com.bday.view.TaskDetailsView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/tasks/{id}")
public class TaskDetailsService {
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@PathVariable int id, Model model)
	{
		TaskDetailsView.get(id, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String put(HttpSession session, @RequestBody String request, @PathVariable int id, Model model)
	{
		JsonObject jRequest = Constants.parse(request);
		String user_email = jRequest.get(Constants.ASSIGNEE).getAsString();
		TaskModel mTask = Constants.getTaskModelFromRequest(jRequest);
		TaskDetailsView.put(session, mTask, id, user_email, model);
		return "JSONView";
	}
	
	// this will cause a sms to be sent at some point
	// will depend on the local
	@RequestMapping(method = RequestMethod.POST)
	public String nag(HttpSession session, @PathVariable int id, Model model)
	{
		TaskDetailsView.nag(session, id, model);
		return "JSONView";
	}
	
	// Deprecated
	@RequestMapping(value="/assign", method = RequestMethod.POST)
	public String assignTask(@RequestParam("user") String username, 
									@PathVariable int id, Model model)
	{
		TaskDetailsView.assignTask(username, id, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteTask(@PathVariable int id, Model model)
	{
		TaskDetailsView.delete(id, model);
		return "JSONView";
	}

}
