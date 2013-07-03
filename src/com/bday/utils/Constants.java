package com.bday.utils;

import org.springframework.ui.Model;

import com.google.gson.Gson;

public class Constants 
{
	// login Controller constants
	public static final String ID_NAME = "username";
	public static final String ID_PASSWORD = "password";
	public static final String ID_NEW_PASSWORD = "new_password";
	public static final String ID_SECOND_PASSWORD = "second_password";
	
	
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
	
	
}
