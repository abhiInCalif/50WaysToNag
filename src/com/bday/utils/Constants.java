package com.bday.utils;

import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Constants 
{
	// login Controller constants
	public static final String ID_NAME = "username";
	public static final String ID_PASSWORD = "password";
	public static final String ID_NEW_PASSWORD = "new_password";
	public static final String ID_SECOND_PASSWORD = "second_password";
	private static final JsonParser json = new JsonParser();
	
	
	// XOR code for the sessions
	public static final int XOR_CODE =  0x0B62;
	public static final String TOKEN = "token";
	public static final String USER = "user";
	
	
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
	
}
