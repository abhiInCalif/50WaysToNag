package com.bday.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bday.utils.*;
import com.bday.view.AuthenticationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class AuthenticationService 
{
	/**
	*  This is a restful interface for authentication.
	* 	Common REST practices should be maintained
	* */
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody String request, Model model)
	{
		/* Redirect to the view that will handle the actual processing of the given
			information */
		JsonObject jRequest = Constants.parse(request);
		String email = jRequest.get(Constants.ID_EMAIL).getAsString();
		String password = jRequest.get(Constants.ID_PASSWORD).getAsString();
		String phoneNumber = jRequest.get(Constants.ID_PHONE).getAsString();
		AuthenticationView.create_user(email, password, phoneNumber, model);
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
	public String changePassword(@RequestBody String request, HttpSession session, Model model)
	{
		JsonObject jRequest = Constants.parse(request);
		String username = jRequest.get(Constants.ID_NAME).getAsString();
		String password = jRequest.get(Constants.ID_PASSWORD).getAsString();
		String new_password = jRequest.get(Constants.ID_NEW_PASSWORD).getAsString();
		int token = jRequest.get(Constants.TOKEN).getAsInt();
		AuthenticationView.update_user(username, password, new_password, token, session, model);
		return "JSONView";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "JSONView";
	}
	
}