package com.bday.utils;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;

import com.bday.model.TaskModel;
import com.bday.model.UserModel;
import com.bday.view.ViewManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Constants 
{
	// login Controller constants
	public static final String ID_NAME = "username";
	public static final String ID_EMAIL = "email";
	public static final String ID_PASSWORD = "password";
	public static final String ID_NEW_PASSWORD = "new_password";
	public static final String ID_SECOND_PASSWORD = "second_password";
	private static final JsonParser json = new JsonParser();
	
	
	// XOR code for the sessions
	public static final int XOR_CODE =  0x0B62;
	public static final String TOKEN = "token";
	public static final String USER = "user";
	public static final String NAG_STATUS = "nagStatus";
	public static final String IS_COMPLETED = "isCompleted";
	public static final String DESC = "details";
	public static final String TITLE = "title";
	public static final String ASSIGNEE = "assignee";
	
	
	// public utility functions
	public static void toJson(Object o, Model m)
	{
		Gson gson = new Gson();
		String json = gson.toJson(o);
		m.addAttribute("json", json);
	}
	
	// public utility parse JSON function
	public static JsonObject parse(String string)
	{
		return (JsonObject) json.parse(string);
	}
	
	public static TaskModel getTaskModelFromRequest(JsonObject jRequest)
	{
		String title = jRequest.get(Constants.TITLE).getAsString();
		String description = jRequest.get(Constants.DESC).getAsString();
		boolean isCompleted = jRequest.get(Constants.IS_COMPLETED).getAsBoolean();
		int nagStatus = jRequest.get(Constants.NAG_STATUS).getAsInt();
		return new TaskModel(title, description, nagStatus, isCompleted);
	}
	
}
