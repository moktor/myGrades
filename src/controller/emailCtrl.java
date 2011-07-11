package controller;


import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;

@LoggedIn
@Named
@SessionScoped


public class emailCtrl implements Serializable {

/**
 * 
 */


public static Properties props = null;
public static Session session = null;
String username = "info.myGrades@googlemail.com";
String password = "hurenfink";


private static final long serialVersionUID = -899691885342085265L;

// Send a simple, single part, text/plain e-mail


public String sendEmail(String email, String courseName) {
 
 
 
 Properties props = new Properties();
 props.put("mail.smtp.host", "smtp.googlemail.com");
 props.put("mail.smtp.socketFactory.port", "465");
 props.put("mail.smtp.socketFactory.class",
   "javax.net.ssl.SSLSocketFactory");
 props.put("mail.smtp.auth", "true");
 props.put("mail.smtp.port", "465");

 Session session = Session.getDefaultInstance(props,
  new javax.mail.Authenticator() {
   protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication("info.myGrades@googlemail.com","hurenfink");
   }
  });

 try {

  Message message = new MimeMessage(session);
  message.setFrom(new InternetAddress("info.myGrades@googlemail.com"));
  message.setRecipients(Message.RecipientType.TO,
    InternetAddress.parse(email));
  message.setSubject("Der Kurs " + courseName + " wurde bewertet.");
  message.setText("Ergebnisse in Prüfungsfach " + courseName + ", " +
  		"\nStudienabschnittsversion PO vom 08.10.2007, " +
  		"\nPrüfungsfachversion Standard sind ab sofort einsehbar. " +
  		"\n\n Diese automatisch versandte Email informiert Sie über die Verbuchung des Ergebnisses der oben genannten Prüfung. " +
  		"\nBitte antworten Sie NICHT auf diese Email. " +
  		"\nBitte haben Sie Verständnis dafür, dass wegen Datenschutz Prüfungsergebnisse nicht per Email bekannt gegeben werden dürfen. " +
  		"\nAuch eine telefonische Auskunft ist nicht erlaubt. " +
  		"\n\n\nhttp://www.myGrades.de");

  Transport.send(message);

 } catch (MessagingException e) {
  throw new RuntimeException(e);
 }
 
 return "index";
}
}