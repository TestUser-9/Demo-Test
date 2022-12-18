package Framework;
import org.testng.annotations.Test;

import Pages.webAction;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadingMails extends webAction {

	    Properties properties = null;
	    private Session session = null;
	    private Store store = null;
	    private Folder inbox = null;
	    private String userName = "zucitestemail1@gmail.com";// provide user name
	    private String password = "mfefgodiptuozjny";// provide password
	    private String saveDirectory = System.getProperty("user.dir") + "\\SaveEmails";

	    public void readMails() {
	        properties = new Properties();
	        properties.setProperty("mail.store.protocol", "imaps");
	        try {
	            session = Session.getDefaultInstance(properties, null);
	            store = session.getStore("imaps");
	            store.connect("imap.gmail.com", userName, password);
	            inbox = store.getFolder("INBOX");

	            int unreadMailCount = inbox.getUnreadMessageCount();
	            System.out.println("No. of Unread Mails = " + unreadMailCount);

	            inbox.open(Folder.READ_WRITE);

	            Message messages[] = inbox.getMessages();
	            System.out.println("No. of Total Mails = " + messages.length);
	            for (int i = messages.length; i > (messages.length - unreadMailCount); i--) {
	                Message message = messages[i - 1];

	                Address[] from = message.getFrom();
	                System.out.println("====================================== Mail no.: " + i + " start ======================================");
	                System.out.println("Date: " + message.getSentDate());
	                System.out.println("From: " + from[0]);
	                System.out.println("Subject: " + message.getSubject());

	                String contentType = message.getContentType();
	                String messageContent = "";

	                // store attachment file name, separated by comma
	                String attachFiles = "";

	                if (contentType.contains("multipart")) {
	                    // content may contain attachments
	                    Multipart multiPart = (Multipart) message.getContent();
	                    int numberOfParts = multiPart.getCount();
	                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
	                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
	                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	                            // this part is attachment
	                            String fileName = part.getFileName();
	                            attachFiles += fileName + ", ";
	                            part.saveFile(saveDirectory + File.separator + fileName);
	                        } else {
	                            // this part may be the message content
	                            messageContent = part.getContent().toString();
	                        }
	                    }

	                    if (attachFiles.length() > 1) {
	                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
	                    }
	                } else if (contentType.contains("text/plain")
	                        || contentType.contains("text/html")) {
	                    Object content = message.getContent();
	                    if (content != null) {
	                        messageContent = content.toString();
	                    }
	                }
	                System.out.println("Attachments: " + attachFiles);
	      
	                System.out.println("====================================== Mail no.: " + i + " end ======================================");
	            }

	            // disconnect
	            inbox.close(false);
	            store.close();
	        } catch (NoSuchProviderException ex) {
	            System.out.println("No provider for pop3.");
	            ex.printStackTrace();
	        } catch (MessagingException ex) {
	            System.out.println("Could not connect to the message store");
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	    }  
	}


