package com.bday.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bday.model.TaskModel;
import com.bday.view.FamilyView;
import com.google.gson.Gson;

@Controller
@RequestMapping("/family/{id}")
public class FamilyTaskService {
	
	// post a new task under this family name
	@RequestMapping(method = RequestMethod.POST)
	public String post(@RequestParam("json") String json, @PathVariable int id, Model model)
	{
		TaskModel mTask = new Gson().fromJson(json, TaskModel.class);
		FamilyView.postTask(mTask, id, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@PathVariable int id, Model model)
	{
		FamilyView.get(id, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addMemberToHome(@PathVariable int id, HttpSession session, Model model)
	{
		FamilyView.addMemberToHome(id, session, model);
		return "JSONView";
	}
	
	

}
