package com.bday.view;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

//import com.zipidat.model.*;

import java.io.Serializable;
import java.sql.*;

public class ViewManager
{
	protected Session session;
	
	public void createSession()
	{
		if(this.session != null)
		{
			if(this.session.isOpen())
			{
				this.session.close();
				this.session = null;
			}
		}
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		this.session = sf.openSession();
	}
	
	public Connection closeSession()
	{
		Connection conn = this.session.close();
		this.session = null;
		return conn;
	}
	
	public Transaction startTransaction()
	{
		return session.beginTransaction();
	}
	
	//These need to be refactored to a hibernate Utils class.......Now that I think about it
	//TODO: Refactor into Util class!
	
	public static SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public static final Session getCurrentSession()
	{
		return factory.getCurrentSession();
	}
	public static final Session openSession()
	{
		return factory.openSession();
	}
	
	public static final Serializable save(Object object, Session sess)
	{
		Transaction tr = sess.beginTransaction();
		Serializable retVal = sess.save(object);
		tr.commit();
		return retVal;
	}

	public static void update(Object object, Session sess)
	{
		Transaction tr = sess.beginTransaction();
		sess.update(object);
		tr.commit();
	}
}
