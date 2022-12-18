//package Framework;
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.text.SimpleDateFormat;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Properties;
//
//import javax.mail.BodyPart;
//import javax.mail.Flags;
//import javax.mail.Folder;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.NoSuchProviderException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Store;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.search.FlagTerm;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.poi.ss.formula.functions.Index;
//
//import com.core.automation.framework.ReportforTestNG.TestngEmailableReport;
//
//public class ExistingMailFunctions {
//	
//
//	
//
//
//	//**********Modified by zuci for sending report via mail and added method to compress the report folder*********
//	
//		public static String browser;
//		public static String threadCount;
//		public static String total;
//		public static String passed;
//		public static String failed;
//		public static String skipped;
//		public static String startTime;
//		public static String endTime;
//		public static String totalTime;
//		public static String URL;
//		public static String totalThreadWaitTime;
//		public static String executedMachine;
//		public static String testCaseIds;
//
//
//
//		public void sendMail() {
//			/*
//			 * try { zipFolder(); } catch (IOException e1) { // TODO Auto-generated catch
//			 * block e1.printStackTrace(); }
//			 */
//
//			//String smtpHost=Index.prop.getProperty("SmtpHost").trim();
//			String fromMailId=Index.prop.getProperty("MailId").trim();
//			String decrypPassword = null;
//			try {
//				decrypPassword = Encrypssio.DE_decrypt(Index.prop.getProperty("Password").trim());
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			String password=decrypPassword;
//			String toMailId=Index.prop.getProperty("ToMailId").trim();
//			String ccMailID=Index.prop.getProperty("CcMailID").trim();
//			String bccMailId=Index.prop.getProperty("BccMailId").trim();
//
//			Properties props = new Properties();
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", "smtp-mail.outlook.com");
//			props.put("mail.smtp.port", "587");
//			props.put("mail.smtp.socketFactory.port", "465");
//			props.put("mail.smtp.socketFactory.class",
//					"javax.net.ssl.SSLSocketFactory"); 
//			//This will handle the complete authentication
//			Session session = Session.getInstance(props,
//
//					new javax.mail.Authenticator() {
//
//				protected PasswordAuthentication getPasswordAuthentication() {
//
//					return new PasswordAuthentication(fromMailId, password);
//
//				}
//
//			});
//
//			try {
//				Message message = new MimeMessage(session);
//				try {
//					message.setFrom(new InternetAddress(fromMailId,"Agadia Automation"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toMailId));
//				message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(ccMailID));
//				message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bccMailId));
//				message.setSubject("Agadia Framework-TestNG Reports");
//
//				BodyPart messageBodyPart1 = new MimeBodyPart();
//				//	messageBodyPart1.setText("Report for the completed execution of <b>Agadia Framework</b> attached with this mail");
//				message.setContent("Report for the completed execution of Agadia Framework attached with this mail","text/html");
//
//				/*
//				 * MimeBodyPart messageBodyPart2 = new MimeBodyPart(); String filename =
//				 * Index.pathProject+"\\Reports\\"+Index.dir+"\\"+Index.dir+".zip"; DataSource
//				 * source = new FileDataSource(filename); messageBodyPart2.setDataHandler(new
//				 * DataHandler(source)); messageBodyPart2.setFileName(Index.dir+".zip");
//				 */
//
//				if (System.getenv("JOB_NAME") != null) {
//					for (String iterable_element : TestngEmailableReport.browser_map.keySet()) {
//						if (TestngEmailableReport.browser_map.get(iterable_element) != null) {
//							browser=TestngEmailableReport.browser_map.get(iterable_element);
//							break;
//						}
//					}
//					for (String iterable_element : TestngEmailableReport.url_map.keySet()) {
//						if (TestngEmailableReport.url_map.get(iterable_element) != null) {
//							URL=TestngEmailableReport.url_map.get(iterable_element);
//							break;
//						}
//					}
//
//					//message.setSubject("Agadia Framework-TestNG Reports for-"+string ); //comment by K
//					String[] sbltJobName=System.getenv("JOB_NAME").split("_");
//					message.setSubject(executedMachine+"-"+System.getenv("JOB_NAME").replace("_"+sbltJobName[sbltJobName.length-1], "")+"-"+browser);
//					message.setContent("Hi Team,<br>" +
//							"<br>"+
//							"Please find the status of the "+System.getenv("JOB_NAME")+" execution result<br>"+
//							"<br>"+
//							"Job Name       : "+System.getenv("JOB_NAME")+"<br>"+
//							"Build Number       : "+System.getenv("BUILD_NUMBER")+"<br>"+
//							"Executed Environment          : "+executedMachine+"<br>"+
//							"Browser          : "+browser+"<br>"+
//							"URL                 : "+URL+"<br>"+
//							"Thread Count : " +threadCount+"<br>"+
//							"Total # Test Case               : "+total+"<br>"+
//							"Passed            : "+passed+"<br>"+
//							"Failed              : "+failed+"<br>"+
//							"Skipped          : "+skipped+"<br>"+
//							"StartTime        : "+startTime+"<br>"+
//							"EndTime          : "+endTime+"<br>"+
//							"TestCaseIds               : "+testCaseIds+"<br>"+
//							"Total Execution Time        : "+totalTime+"<br>"+
//							//"Total Thread Wait Time          : "+totalThreadWaitTime+"<br>"+
//							"<br>"+
//							"Find the " + "<b><a href=https://qcms.agadia.com:456/Logs/ExecutionLogs.aspx>"+Index.dir+"</a></b>"  +" in QCMS ( Quality -> Execution Log)<br>" + 
//							"<br>" + 
//							"Regards,<br>" + 
//							Encrypssio.DE_decrypt(Index.prop.getProperty("API.UserName"))+
//							"<br>" + 
//							"<br>" + 
//							"Disclaimer:The contents of this e-mail and any attachments enclosed therein are intended solely for the addressee(s) and may contain confidential and/or privileged information. It may also be the sole intellectual and proprietary resource of Zuci Systems and therefore may be legally protected from disclosure. If you are not the intended recipient of this message, or if this message has been addressed to you by inadvertence, kindly alert the sender by email and then follow with its deletion. If you are not the intended recipient, you are hereby notified that any use, dissemination, copying, or storage of this message or its attachments is strictly prohibited and shall make you liable for consequences under applicable laws.",
//							"text/html"); 				
//				}
//
//				/*Multipart multipart = new MimeMultipart();
//				//multipart.addBodyPart(messageBodyPart2);
//				multipart.addBodyPart(messageBodyPart1); 
//				message.setContent(multipart); */  //uncomment by K
//
//				Transport.send(message);
//
//
//			} catch (MessagingException e) {
//
//				throw new RuntimeException(e);
//
//			}
//
//		}
//		public static void zipFolder() throws IOException {
//			File isFile= new File(Index.pathProject+"\\Reports\\"+Index.dir+"\\"+Index.dir+".zip");
//			if (!isFile.exists()) {
//				File file = new File(Index.pathProject + "\\Reports\\" + Index.dir);
//				net.lingala.zip4j.ZipFile zip = new net.lingala.zip4j.ZipFile(file + "\\" + Index.dir + ".zip");
//				File[] fileList = file.listFiles();
//				for (File file2 : fileList) {
//					zip.addFile(file2);
//				} 
//			}
//
//		}
//
//		public static String byteReport() {
//			try {
//				zipFolder();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			File file = new File(Index.pathProject+"\\Reports\\"+Index.dir+"\\"+Index.dir+".zip");
//			byte[] bytes = null;
//			try {
//				bytes = FileUtils.readFileToByteArray(file);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			String encodedBase64 = Base64.getEncoder().encodeToString(bytes);
//			return encodedBase64;
//		}
//
//
//		public static Properties getServerProperties(String protocol, String host,
//				String port) {
//			Properties properties = new Properties();
//
//			// server setting
//			properties.put(String.format("mail.%s.host", protocol), host);
//			properties.put(String.format("mail.%s.port", protocol), port);
//
//			// SSL setting
//			properties.setProperty(
//					String.format("mail.%s.socketFactory.class", protocol),
//					"javax.net.ssl.SSLSocketFactory");
//			properties.setProperty(
//					String.format("mail.%s.socketFactory.fallback", protocol),
//					"false");
//			properties.setProperty(
//					String.format("mail.%s.socketFactory.port", protocol),
//					String.valueOf(port));
//
//			return properties;
//		}
//
//		public static HashMap<String, String> readMail() {
//			String protocol = "imap";
//			String host=Index.prop.getProperty("PopHost").trim();
//			String port = "993";
//			String userName=Index.prop.getProperty("MailId").trim();
//			Object content = null;
//			HashMap<String, String>eocId = new HashMap<>();
//
//			String decrypPassword = null;
//			try {
//				decrypPassword = Encrypssio.DE_decrypt(Index.prop.getProperty("Password").trim());
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			String password=decrypPassword;
//
//			Properties properties = getServerProperties(protocol, host, port);
//			Session session = Session.getDefaultInstance(properties);
//
//			try {
//				// connects to the message store
//				Store store = session.getStore(protocol);
//				store.connect(userName, password);
//
//				// opens the inbox folder
//				Folder folderInbox = store.getFolder("BatchEOC");
//				folderInbox.open(Folder.READ_WRITE);
//
//				// fetches new messages from server       
//				Message[] messages = folderInbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
//				if (messages.length>0) {
//					for (int i1 = 0; i1 < messages.length; i1++) {
//						Message msg = messages[i1];           
//						String contentType = msg.getContentType();
//						String messageContent = "";
//						msg.setFlag(Flags.Flag.SEEN, true);
//						if (contentType.contains("text/plain")
//								|| contentType.contains("text/html")) {
//							try {
//								content = msg.getContent();
//								if (content != null) {
//									messageContent = content.toString();
//								}
//							} catch (Exception ex) {
//								messageContent = "[Error downloading content]";
//								ex.printStackTrace();
//							}
//						}
//						String[] eoc=messageContent.split("</td><td>");
//						for (int j = 0; j < eoc.length; j++) {
//
//							if (eoc[j].toString().trim().matches("[0-9]{7}")) {
//								eocId.put(Integer.toString(j), eoc[j]);
//								System.out.println(eoc[j]);
//							}
//						}	
//					}
//
//					// disconnect
//					folderInbox.close(false);
//					store.close();
//				}
//
//
//
//			} catch (NoSuchProviderException ex) {
//				System.out.println("No provider for protocol: " + protocol);
//				ex.printStackTrace();
//			} catch (MessagingException ex) {
//				System.out.println("Could not connect to the message store");
//				ex.printStackTrace();
//			}
//			return eocId;
//		}
//		}*/
//		public static void markAllMailAsRead(String folder) {
//			String protocol = "imap";
//			String host=Index.prop.getProperty("PopHost").trim();
//			String port = "993";
//			String userName=Index.prop.getProperty("MailId").trim();
//			Object content = null;	
//			String decrypPassword = null;
//			try {
//				decrypPassword = Encrypssio.DE_decrypt(Index.prop.getProperty("Password").trim());
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			String password=decrypPassword;
//
//			Properties properties = getServerProperties(protocol, host, port);
//			Session session = Session.getDefaultInstance(properties);
//
//			try {
//				// connects to the message store
//				Store store = session.getStore(protocol);
//				store.connect(userName, password);
//
//				// opens the inbox folder
//				Folder folderInbox = store.getFolder(folder);
//				folderInbox.open(Folder.READ_WRITE);
//
//				// fetches new messages from server
//				Message[] mails= folderInbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
//
//				if (mails.length>0) {
//
//					for (int i = 0; i < mails.length; i++) {
//						Message msg = mails[i];           
//
//						msg.setFlag(Flags.Flag.SEEN, true);
//
//					}
//				}
//				// disconnect
//				folderInbox.close(false);
//				store.close();
//			} catch (NoSuchProviderException ex) {
//				System.out.println("No provider for protocol: " + protocol);
//				ex.printStackTrace();
//			} catch (MessagingException ex) {
//				System.out.println("Could not connect to the message store");
//				ex.printStackTrace();
//			}
//		}
//
//		public void sendMailOnDeactiveUsers() {
//			/*@Selvamuthukumar
//			 * Method for sending mail
//			 * No CR-Framework level update*/
//		//	String smtpHost=Index.prop.getProperty("SmtpHost").trim();
//			String fromMailId=Index.prop.getProperty("MailId").trim();
//			String decrypPassword = null;
//			try {
//				decrypPassword = Encrypssio.DE_decrypt(Index.prop.getProperty("Password").trim());
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			String password=decrypPassword;
//			String toMailId=Index.prop.getProperty("ToMailId").trim();
//			String ccMailID=Index.prop.getProperty("CcMailID").trim();
//			String bccMailId=Index.prop.getProperty("BccMailId").trim();
//
//			// Create object of Property file
//			Properties props = new Properties();
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", "smtp-mail.outlook.com");
//			props.put("mail.smtp.port", "587");
//			props.put("mail.smtp.socketFactory.port", "465");
//			props.put("mail.smtp.socketFactory.class",
//					"javax.net.ssl.SSLSocketFactory"); 
//			// This will handle the complete authentication
//			Session session = Session.getInstance(props,
//
//					new javax.mail.Authenticator() {
//
//				protected PasswordAuthentication getPasswordAuthentication() {
//
//					return new PasswordAuthentication(fromMailId, password);
//
//				}
//
//			});
//
//			try {
//				Message message = new MimeMessage(session);
//				try {
//					message.setFrom(new InternetAddress(fromMailId,"Agadia Automation"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toMailId));
//				message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(ccMailID));
//				message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bccMailId));
//				message.setSubject("Node/User Deactivation for -"+System.getenv("JOB_NAME"));
//				BodyPart messageBodyPart1 = new MimeBodyPart();
//
//				String deactive = "";
//				Iterator<String> vls=BrowserFactory.deactivatedNodeAndUsers.iterator();
//				if (BrowserFactory.deactivatedNodeAndUsers.size()>1) {
//					while (vls.hasNext()) {
//						deactive = deactive + "\n" + (String) vls.next();
//					}
//					message.setContent("Hi Team,<br>" + "Please find the details of the user/node deactivation <br>"
//							+ "<br>" + deactive + "<br>" + "<br>" + "Regards,<br>"
//							+ Encrypssio.DE_decrypt(Index.prop.getProperty("API.UserName")) + "<br>" + "<br>"
//							+ "Disclaimer:The contents of this e-mail and any attachments enclosed therein are intended solely for the addressee(s) and may contain confidential and/or privileged information. It may also be the sole intellectual and proprietary resource of Zuci Systems and therefore may be legally protected from disclosure. If you are not the intended recipient of this message, or if this message has been addressed to you by inadvertence, kindly alert the sender by email and then follow with its deletion. If you are not the intended recipient, you are hereby notified that any use, dissemination, copying, or storage of this message or its attachments is strictly prohibited and shall make you liable for consequences under applicable laws.",
//							"text/html");
//					Transport.send(message);
//				}
//
//			} catch (MessagingException e) {
//
//				throw new RuntimeException(e);
//
//			}
//
//		}
//	}
//
//
//
//
//
//
//
//}
