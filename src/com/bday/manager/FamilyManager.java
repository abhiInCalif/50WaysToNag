package com.bday.manager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bday.model.FamilyModel;
import com.bday.model.UserModel;
import com.bday.view.ViewManager;

public class FamilyManager 
{
	public static FamilyModel findFirstNotNullFamily(UserModel user)
	{
		// gets all the tasks for the given family
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// ensure its in the session, no guarantees remember
		user = (UserModel) sess.merge(user);
		FamilyModel family = user.getFirstNotNullFamily();
		
		if (family == null)
		{
			// create a new family
			// else create a new family, associate it and add it to the
			// grouping
			
			family = new FamilyModel();
			family.addMember(user);
			user.addFamily(family);
			
			sess.save(family);
			sess.update(user);
			tr.commit();
		}
		
		return family;
	}
}
