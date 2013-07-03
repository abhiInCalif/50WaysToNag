package com.bday.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bday.view.NewFamilyView;

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
	
}
