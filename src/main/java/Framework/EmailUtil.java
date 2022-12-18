//package Framework;
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.Date;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.Authenticator;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//
//
//
//public class EmailUtil {
//	public EmailUtil(){
//		
//		String to="zucitestemail2@gmail.com";//change accordingly  
//		  final String user="zucitestemail1@gmail.com";//change accordingly  
//		  final String password="mfefgodiptuozjny";//change accordingly  
//		   
//		  //1) get the session object     
//		  Properties properties = System.getProperties();  
//		  properties.setProperty("mail.smtp.host", "smtp.gmail.com");  
//		  properties.put("mail.smtp.auth", "true");  
//		  properties.put("mail.smtp.auth", "true"); //enable authentication
//		  properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//		  
//		  Session session = Session.getDefaultInstance(properties,  
//		   new javax.mail.Authenticator() {  
//		   protected PasswordAuthentication getPasswordAuthentication() {  
//		   return new PasswordAuthentication(user,password);  
//		   }  
//		  });  
//		     
//		  //2) compose message     
//		 
//		  try{   
//			  
//			    MimeMessage message = new MimeMessage(session);  
//			    message.setFrom(new InternetAddress(user));  
//			    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
//			    message.setSubject("Astra Test Report");  
//
//		   //3) create MimeBodyPart object and set your message text     
//		    BodyPart messageBodyPart1 = new MimeBodyPart();  
//		    messageBodyPart1.setText("Hi Team \n\n PFA for Astra Automation Report");  
//		      
//		    //4) create new MimeBodyPart object and set DataHandler object to this object      
//		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
//		  
//		    String filename = "D:\\Project\\Basic_Java_TestNG_Framework_OrangeHRM\\Java_TestNG_Framework_OrangeHRM\\test-output\\emailable-report.html";//change accordingly  
//		    DataSource source = new FileDataSource(filename);  
//		    messageBodyPart2.setDataHandler(new DataHandler(source));  
//		    messageBodyPart2.setFileName(filename);  
//		     
//		     
//		    //5) create Multipart object and add MimeBodyPart objects to this object      
//		    Multipart multipart = new MimeMultipart();  
//		    multipart.addBodyPart(messageBodyPart1);  
//		    multipart.addBodyPart(messageBodyPart2);  
//		  
//		    //6) set the multiplart object to the message object  
//		    message.setContent(multipart );  
//		     
//		    //7) send message  
//		    Transport.send(message);  
//		   
//		   System.out.println("message sent....");  
//		   }catch (MessagingException ex) {ex.printStackTrace();}
//	    }
//			
//	
//	}
//
//		
//		
//	
//
//
