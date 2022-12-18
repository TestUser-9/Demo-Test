package Framework;



import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
//import org.apache.poi.ss.formula.functions.Index;
import org.apache.poi.ss.formula.functions.Index;




//**********Modified by zuci for sending report via mail and added method to compress the report folder*********
public class MailFunctions {
	public static String browser;
	public static String threadCount;
	public static String total;
	public static String passed;
	public static String failed;
	public static String skipped;
	public static String startTime;
	public static String endTime;
	public static String totalTime;
	public static String URL;
	public static String totalThreadWaitTime;
	public static String executedMachine;
	public static String testCaseIds;



	public static void sendMail() throws EmailException {
		
		System.out.println("Started");
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		System.out.println("hostpass");
		//email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("vgnesh875@gmail.com", "Vicky2194$"));
		email.setSSLOnConnect(true);
		email.setFrom("me@gmail.com");
		email.setSubject("TestMailAutomation");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("vgnesh875@gmail.com");
		email.send();
		System.out.println("End");

	}

//	public static String byteReport() {
//		try {
//			zipFolder();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		File file = new File(Index.pathProject+"\\Reports\\"+Index.dir+"\\"+Index.dir+".zip");
//		byte[] bytes = null;
//		try {
//			bytes = FileUtils.readFileToByteArray(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		String encodedBase64 = Base64.getEncoder().encodeToString(bytes);
//		return encodedBase64;
//	}
//
//
//	public static Properties getServerProperties(String protocol, String host,
//			String port) {
//		Properties properties = new Properties();
//
//		// server setting
//		properties.put(String.format("mail.%s.host", protocol), host);
//		properties.put(String.format("mail.%s.port", protocol), port);
//
//		// SSL setting
//		properties.setProperty(
//				String.format("mail.%s.socketFactory.class", protocol),
//				"javax.net.ssl.SSLSocketFactory");
//		properties.setProperty(
//				String.format("mail.%s.socketFactory.fallback", protocol),
//				"false");
//		properties.setProperty(
//				String.format("mail.%s.socketFactory.port", protocol),
//				String.valueOf(port));
//
//		return properties;
//	}
//
//	public static HashMap<String, String> readMail() {
//		String protocol = "imap";
//		String host=Index.prop.getProperty("PopHost").trim();
//		String port = "993";
//		String userName=Index.prop.getProperty("MailId").trim();
//		Object content = null;
//		HashMap<String, String>eocId = new HashMap<>();
//
//		String decrypPassword = null;
//		try {
//			decrypPassword = Encrypssio.DE_decrypt(Index.prop.getProperty("Password").trim());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		String password=decrypPassword;
//
//		Properties properties = getServerProperties(protocol, host, port);
//		Session session = Session.getDefaultInstance(properties);
//
//		try {
//			// connects to the message store
//			Store store = session.getStore(protocol);
//			store.connect(userName, password);
//
//			// opens the inbox folder
//			Folder folderInbox = store.getFolder("BatchEOC");
//			folderInbox.open(Folder.READ_WRITE);
//
//			// fetches new messages from server       
//			Message[] messages = folderInbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
//			if (messages.length>0) {
//				for (int i1 = 0; i1 < messages.length; i1++) {
//					Message msg = messages[i1];           
//					String contentType = msg.getContentType();
//					String messageContent = "";
//					msg.setFlag(Flags.Flag.SEEN, true);
//					if (contentType.contains("text/plain")
//							|| contentType.contains("text/html")) {
//						try {
//							content = msg.getContent();
//							if (content != null) {
//								messageContent = content.toString();
//							}
//						} catch (Exception ex) {
//							messageContent = "[Error downloading content]";
//							ex.printStackTrace();
//						}
//					}
//					String[] eoc=messageContent.split("</td><td>");
//					for (int j = 0; j < eoc.length; j++) {
//
//						if (eoc[j].toString().trim().matches("[0-9]{7}")) {
//							eocId.put(Integer.toString(j), eoc[j]);
//							System.out.println(eoc[j]);
//						}
//					}	
//				}
//
//				// disconnect
//				folderInbox.close(false);
//				store.close();
//			}
//
//
//
//		} catch (NoSuchProviderException ex) {
//			System.out.println("No provider for protocol: " + protocol);
//			ex.printStackTrace();
//		} catch (MessagingException ex) {
//			System.out.println("Could not connect to the message store");
//			ex.printStackTrace();
//		}
//		return eocId;
//	}
//
//
//	/*public static String getTextFromMessage(Message message) throws MessagingException, IOException {
//		String result = "";
//		if (message.isMimeType("text/plain")) {
//			result = message.getContent().toString();
//		} else if (message.isMimeType("multipart/*")) {
//			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
//			result = getTextFromMimeMultipart(mimeMultipart);
//		}
//		return result;
//	}
//
//	public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
//		String result = "";
//		int count = mimeMultipart.getCount();
//		for (int i = 0; i < count; i++) {
//			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
//			if (bodyPart.isMimeType("text/html")) {
//				String html = (String)bodyPart.getContent();
//				result = result + "\n" + html;
//			}else if (bodyPart.isMimeType("text/plain")) {
//				result = result + "\n" + bodyPart.getContent();
//				break; 
//			}else if (bodyPart.getContent() instanceof MimeMultipart){
//				result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
//			}
//		}
//		return result;
//	}*/
//	public static void markAllMailAsRead(String folder) {
//		String protocol = "imap";
//		String host=Index.prop.getProperty("PopHost").trim();
//		String port = "993";
//		String userName=Index.prop.getProperty("MailId").trim();
//		Object content = null;	
//		String decrypPassword = null;
//		try {
//			decrypPassword = Encrypssio.DE_decrypt(Index.prop.getProperty("Password").trim());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		String password=decrypPassword;
//
//		Properties properties = getServerProperties(protocol, host, port);
//		Session session = Session.getDefaultInstance(properties);
//
//		try {
//			// connects to the message store
//			Store store = session.getStore(protocol);
//			store.connect(userName, password);
//
//			// opens the inbox folder
//			Folder folderInbox = store.getFolder(folder);
//			folderInbox.open(Folder.READ_WRITE);
//
//			// fetches new messages from server
//			Message[] mails= folderInbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
//
//			if (mails.length>0) {
//
//				for (int i = 0; i < mails.length; i++) {
//					Message msg = mails[i];           
//
//					msg.setFlag(Flags.Flag.SEEN, true);
//
//				}
//			}
//			// disconnect
//			folderInbox.close(false);
//			store.close();
//		} catch (NoSuchProviderException ex) {
//			System.out.println("No provider for protocol: " + protocol);
//			ex.printStackTrace();
//		} catch (MessagingException ex) {
//			System.out.println("Could not connect to the message store");
//			ex.printStackTrace();
//		}
//	}
//
//	public void sendMailOnDeactiveUsers() {
//		/*@Selvamuthukumar
//		 * Method for sending mail
//		 * No CR-Framework level update*/
//		String smtpHost=Index.prop.getProperty("SmtpHost").trim();
//		String fromMailId=Index.prop.getProperty("MailId").trim();
//		String decrypPassword = null;
//		try {
//			decrypPassword = Encrypssio.DE_decrypt(Index.prop.getProperty("Password").trim());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		String password=decrypPassword;
//		String toMailId=Index.prop.getProperty("ToMailId").trim();
//		String ccMailID=Index.prop.getProperty("CcMailID").trim();
//		String bccMailId=Index.prop.getProperty("BccMailId").trim();
//
//		// Create object of Property file
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", smtpHost);
//		props.put("mail.smtp.port", "587");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory"); 
//		// This will handle the complete authentication
//		Session session = Session.getInstance(props,
//
//				new javax.mail.Authenticator() {
//
//			protected PasswordAuthentication getPasswordAuthentication() {
//
//				return new PasswordAuthentication(fromMailId, password);
//
//			}
//
//		});
//
//		try {
//			Message message = new MimeMessage(session);
//			try {
//				message.setFrom(new InternetAddress(fromMailId,"Agadia Automation"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toMailId));
//			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(ccMailID));
//			message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bccMailId));
//			message.setSubject("Node/User Deactivation for -"+System.getenv("JOB_NAME"));
//			BodyPart messageBodyPart1 = new MimeBodyPart();
//
//			String deactive = "";
//			Iterator<String> vls=BrowserFactory.deactivatedNodeAndUsers.iterator();
//			if (BrowserFactory.deactivatedNodeAndUsers.size()>1) {
//				while (vls.hasNext()) {
//					deactive = deactive + "\n" + (String) vls.next();
//				}
//				message.setContent("Hi Team,<br>" + "Please find the details of the user/node deactivation <br>"
//						+ "<br>" + deactive + "<br>" + "<br>" + "Regards,<br>"
//						+ Encrypssio.DE_decrypt(Index.prop.getProperty("API.UserName")) + "<br>" + "<br>"
//						+ "Disclaimer:The contents of this e-mail and any attachments enclosed therein are intended solely for the addressee(s) and may contain confidential and/or privileged information. It may also be the sole intellectual and proprietary resource of Zuci Systems and therefore may be legally protected from disclosure. If you are not the intended recipient of this message, or if this message has been addressed to you by inadvertence, kindly alert the sender by email and then follow with its deletion. If you are not the intended recipient, you are hereby notified that any use, dissemination, copying, or storage of this message or its attachments is strictly prohibited and shall make you liable for consequences under applicable laws.",
//						"text/html");
//				Transport.send(message);
//			}
//
//		} catch (MessagingException e) {
//
//			throw new RuntimeException(e);
//
//		}
//
//	}
}






