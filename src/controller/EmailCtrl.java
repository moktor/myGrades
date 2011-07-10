package controller;
import java.io.Serializable;
import java.util.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.*;
@Named
@SessionScoped
public class EmailCtrl implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -899691885342085265L;

	// Send a simple, single part, text/plain e-mail
	

	public String sendEmail() {

	// SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
	String from = "<myGrades>coderbitch@ieks.de";
	String to = "j.lehmann@fg.de";
	// SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
	String host = "smtp.1und1.de";

	// Create properties, get Session
	Properties props = new Properties();
	props.setProperty("mail.user", "coderbitch@ieks.de");
	 props.setProperty("mail.password", "hurenfink");

	// If using static Transport.send(),
	// need to specify which host to send it to
	props.put("smtp.1und1.de", host);
	// To see what is going on behind the scene
	props.put("mail.debug", "true");
	Session session = Session.getInstance(props);

	try {
	// Instantiatee a message
	Message msg = new MimeMessage(session);

	//Set message attributes
	msg.setFrom(new InternetAddress(from));
	InternetAddress[] address = {new InternetAddress(to)};
	msg.setRecipients(Message.RecipientType.TO, address);
	msg.setSubject("Test E-Mail through Java");
	msg.setSentDate(new Date());

	// Set message content
	msg.setText("This is a test of sending a " +
	"plain text e-mail through Java.\n" +
	"Here is line 2.");

	//Send the message
	Transport.send(msg);
	}
	catch (MessagingException mex) {
	// Prints all nested (chained) exceptions as well
	mex.printStackTrace();
	}
	
	return "index";
	}}//End of class
	
