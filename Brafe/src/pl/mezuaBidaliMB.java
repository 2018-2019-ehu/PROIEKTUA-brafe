package pl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@Named
@RequestScoped
public class mezuaBidaliMB {

	   public void mezuaSortuEtaBidali(/*String nora,String nondik,String izena*/){    
	      // Recipient's email ID needs to be mentioned.
	      String to = "adrinitro96@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "brafe@brafe.com";

	      // Assuming you are sending email from localhost
	      String host = "192.168.0.23";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("Brafe: ez erantzun mezu honi");

	         // Now set the actual message
	         message.setText("Mezu honen bitartez adierazten da,adrian erabiltzaileak etxera bueltatzen ari dela");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	}
