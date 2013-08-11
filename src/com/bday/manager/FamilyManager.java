package com.bday.manager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bday.model.FamilyModel;
import com.bday.model.UserModel;
import com.bday.view.ViewManager;

public class FamilyManager {
	
	public static FamilyModel getFamilyForUser(UserModel user)
	{
		// step 1, magic incantation
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		UserModel user_db = (UserModel) sess.get(UserModel.class, user.getId());
		
		return user_db.getFamily();
	}

}
