package com.bday.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bday.view.TaskView;

@Controller
@RequestMapping("/tasks")
public class TasksService 
{
	@RequestMapping(method = RequestMethod.GET)
	public String getUserTasks(HttpSession session, Model model)
	{
		TaskView.getUserTasks(session, model);
		return "JSONView";
	}

}
