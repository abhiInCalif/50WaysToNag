package com.bday.view;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;

import com.bday.model.FamilyModel;
import com.bday.model.UserModel;
import com.bday.utils.Constants;
import com.google.gson.Gson;

public class NewFamilyView {

	public static void create(HttpSession session, Model model) {
		// step one, create the new FamilyObject
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		FamilyModel new_family = new FamilyModel();
		
		// step 2, get the current session user model
		UserModel user = (UserModel) session.getAttribute(Constants.USER);
		if(user != null) // valid session
		{
			user.addFamily(new_family);
			user = (UserModel) sess.merge(user);
			
			// step 3, add user to the family
			new_family.addMember(user);
			
			// step 4, save it all
			sess.save(new_family);
			tr.commit();
		}
		
		// gson the user and send it back
		Gson gson = new Gson();
		String json = gson.toJson(user);
		model.addAttribute("json", json);
	}

}
