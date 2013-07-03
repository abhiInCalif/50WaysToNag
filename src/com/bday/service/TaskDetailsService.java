package com.bday.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bday.model.TaskModel;
import com.bday.view.TaskDetailsView;
import com.google.gson.Gson;

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
	public String put(@RequestParam("json") String json, 
									@PathVariable int id, Model model)
	{
		TaskModel mTask = new Gson().fromJson(json, TaskModel.class);
		TaskDetailsView.put(mTask, id, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String nag(int id, Model model)
	{
		TaskDetailsView.nag(id, model);
		return "JSONView";
	}
	
	@RequestMapping(value="/assign", method = RequestMethod.POST)
	public String assignTask(@RequestParam("user") String username, 
									@PathVariable int id, Model model)
	{
		TaskDetailsView.assignTask(username, id, model);
		return "JSONView";
	}

}
