package com.bday.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;

import com.bday.manager.EmailManager;
import com.bday.manager.FamilyManager;
import com.bday.model.FamilyModel;
import com.bday.model.InviteModel;
import com.bday.model.UserModel;
import com.bday.utils.Constants;

public class InviteView {

	@SuppressWarnings("unchecked")
	public static void invite(UserModel user, List<String> emails, Model model) {
		// step 1, magic incantation
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		UserModel asking_user = (UserModel) sess.get(UserModel.class, user.getId());
		
		// safety check
		if (emails == null) throw new RuntimeException("emails is not a valid list, need to list of emails");
		
		// step 2, for loop that list
		for (String email : emails)
		{
			// get the user model associated with the given email,
			List users = sess.createQuery("from UserModel as user where user.email=?").setString(0, email).list();
			if (users.size() == 0)
			{
				// we need to send out an invite to this user....
				EmailManager emailManager = new EmailManager(email, "Hi! Your friend has invited you to join 50waystonag.com. To sign up just click <a href=\"http://50waystonag.com\">here</a>", "Sign up for 50ways");
				emailManager.sendEmail();
			}
			else
			{
				UserModel invitee = (UserModel) users.get(0);
				// add the invitation to the user;
				InviteModel invite = new InviteModel(asking_user, FamilyManager.findFirstNotNullFamily(asking_user), Constants.INVITE_MESSAGE);
				invitee.addInvite(invite);
				
				sess.save(invite);
				sess.update(invitee);
			}
		}
		
		// commit the batched request and push nothing important to the model
		Constants.toJson(emails, model);
		tr.commit();
	}

	public static void getUserInvites(HttpSession session, Model model) {
		// step 1, magic incantation
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// step 2, get the user from the session
		UserModel user = (UserModel) session.getAttribute(Constants.USER);
		user = (UserModel) sess.get(UserModel.class, user.getId());

		// get all the user invites
		List<InviteModel> invites = user.getInvitations();
		Constants.toJson(invites, model);
		tr.commit();
	}

	public static void accept(HttpSession session, int invite_id, int family_id, Model model) {
		// step 1, magic incantation
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// to accept, we clear family from the user
		UserModel user = (UserModel) session.getAttribute(Constants.USER);
		user = (UserModel) sess.get(UserModel.class, user.getId());
		
		user.setFamilies(new ArrayList<FamilyModel>());
		// now add family to the user
		FamilyModel family = (FamilyModel) sess.get(FamilyModel.class, family_id);
		user.addFamily(family);
		
		family.addMember(user);
		//remove the invite from the user
		user.removeInvite(invite_id);
		
		// update the model
		sess.update(user);
		sess.update(family);
		
		Constants.toJson(user.getInvitations(), model);
		tr.commit();
	}
	
	public static void deny(HttpSession session, int invite_id, int family_id, Model model)
	{
		Session sess = ViewManager.getCurrentSession();
		if (!sess.isOpen()) sess = ViewManager.openSession(); // safety check
		Transaction tr = sess.beginTransaction();
		
		// just simply remove the invitation
		UserModel user = (UserModel) session.getAttribute(Constants.USER);
		user = (UserModel) sess.get(UserModel.class, user.getId());
		user.removeInvite(invite_id);
		
		sess.update(user);
		
		Constants.toJson(user.getInvitations(), model);
		tr.commit();
	}

}
