package com.bday.manager;

import java.io.IOException;

import com.techventus.server.voice.Voice;

public class VoiceManager {
	
	private Voice v;
	private static final String USERNAME = "50waystonag";
	private static final String PASSWORD = "fortunecookies";
	
	public VoiceManager()
	{
		try {
			v = new Voice(USERNAME, PASSWORD);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendSMS(String number, String text)
	{
		try {
			v.login();
			v.sendSMS(number, text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
