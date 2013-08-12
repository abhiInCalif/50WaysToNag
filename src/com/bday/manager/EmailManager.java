package com.bday.manager;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailManager {
	
	private static final String USERNAME = "50waystonag@gmail.com";
	public static final String SUBJECT = "Your daily insult reminder";
	private String to;
	private String message;
	private String subject;
	private Properties props;
	private Session session;

	public EmailManager(String to, String message, String subject)
	{
		this.to = to;
		this.message = message;
		this.subject = subject;
		
		setupEmailClient();
	}
	
	private void setupEmailClient()
	{
		final String username = USERNAME;
		final String password = "fortunecookies";
		
		this.props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
	}
	
	public void sendEmail()
	{
		try {
			 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
			message.setSubject(this.subject);
			message.setContent(this.message, "text/html");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
