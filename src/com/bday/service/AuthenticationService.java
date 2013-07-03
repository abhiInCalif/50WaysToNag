package com.bday.service;

import javax.servlet.http.HttpSession;

import com.bday.utils.*;
import com.bday.view.AuthenticationView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class AuthenticationService 
{
	/**
	*  This is a restful interface for authentication.
	* 	Common REST practices should be maintained
	* */
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam(Constants.ID_NAME) String username, 
								@RequestParam(Constants.ID_PASSWORD) String password, Model model)
	{
		/* Redirect to the view that will handle the actual processing of the given
			information */
		AuthenticationView.create_user(username, password, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(@RequestParam(Constants.ID_NAME) String username, 
								@RequestParam(Constants.ID_PASSWORD) String password, HttpSession session, Model model)
	{
		AuthenticationView.login_user(username, password, session, model);
		return "JSONView";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String changePassword(@RequestParam(Constants.ID_NAME) String username,
											@RequestParam(Constants.ID_PASSWORD) String password,
											@RequestParam(Constants.ID_NEW_PASSWORD) String new_password,
											@RequestParam(Constants.TOKEN) int token,
											HttpSession session, Model model)
	{
		AuthenticationView.update_user(username, password, new_password, token, session, model);
		return "JSONView";
	}
	
}