package com.bday.view;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.bday.model.UserModel;
import com.bday.utils.Constants;
import com.bday.utils.Logger;

public class AuthenticationView 
{
	
	// creates a new user, does not login the user after creation.
	public static void create_user(String email, String password, String phoneNumber, Model model)
	{
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// checks if the user exists in the database, if not, proceeds to create a new user
		if(!isUserAlready(email))
		{
			try {
				UserModel user = new UserModel();
				user.setEmail(email);
				user.setPassword(password);
				user.setPhoneNumber(phoneNumber);
				
				int id = (Integer) sess.save(user);
				sess.flush();
				tr.commit();
				
				String json = new Gson().toJson(new Integer(id)).toString();
				
				// add to the model
				model.addAttribute("json", json);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Logger.log("create_user failed to create a user");
			}
		}
		else
			model.addAttribute("json", -1);
	}
	
	// logs in user, create a session token, and returns the encoded session token
	public static void login_user(String email, String password, HttpSession session, Model model)
	{
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		
		// check if the user / password pair exists
		UserModel user = isValidUser(email, password);
		if(user != null)
		{
			// create the session token on the server, and declare the user logged in.
			// process: 1) create session token, 2) Bitwise OR it with given constant hex, add it to the model
			Timestamp d = new Timestamp(new Date().getTime());
			int token = (int) ((d.getTime()) ^ Constants.XOR_CODE);
			
			// create the session on the server....
			session.setAttribute(Constants.TOKEN, token);
			session.setAttribute(Constants.USER, user);
			
			model.addAttribute("json", new Integer(token ^ Constants.XOR_CODE));
		}
		else
			model.addAttribute("json", -1);
		
	}
	
	public static void update_user(String email, String password, String new_password, int token, HttpSession session, Model model)
	{
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		
		boolean success = false;
		
		// if valid token is passed, update the user object's user and password with new password
		// given that the user for the given user and password exists
		UserModel user = isValidUser(email, password);
		if(user != null)
		{
			int server_token = (Integer)session.getAttribute(Constants.TOKEN);
			// if session exists
			if (server_token == (token ^ Constants.XOR_CODE))
			{
				try {
					// valid session exists
					Transaction tr = sess.beginTransaction();
					user.setPassword(new_password); // md5ed password should be passed in.
					UserModel user1 = (UserModel) sess.merge(session.getAttribute(Constants.USER));
					if (!user1.getPassword().equals(new_password))
						throw new RuntimeException("Failed to update the object");
					tr.commit();
					
					success = true;
				}
				catch (Exception e)
				{
					e.printStackTrace();
					Logger.log("error while updating the user in update_user");
				}

			}
		}
		
		model.addAttribute("json", success);
	}
	
	@SuppressWarnings({"unused", "unchecked"})
	private static UserModel isValidUser(String username, String password)
	{
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		List users = sess.createQuery("from UserModel as user where user.email=? and user.password=?").setString(0, username).setString(1, password).list();
		if(users.size() > 0)
			return (UserModel) users.get(0);
		else
			return null;
	}
	
	@SuppressWarnings({"unused", "unchecked"})
	private static boolean isUserAlready(String email)
	{
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// check if the username exists in the database
		List users = sess.createQuery("from UserModel as user where user.email=?").setString(0, email).list();
		if (users.size() > 0)
			return true;
		else
			return false;
	}
}
