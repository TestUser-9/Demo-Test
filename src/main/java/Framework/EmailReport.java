//package Framework;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import com.testing.framework.EmailUtils;
//
//public class EmailReport {
//	
//	public static void SendEmailReport() throws Exception {
//		EmailUtils emailUtils = new EmailUtils();
//		File file = new File("config.properties");
//		FileInputStream fileInput = new FileInputStream(file);
//		Properties properties = new Properties();
//		properties.load(fileInput);
//		String attachments ="D:\\\\Project\\\\Basic_Java_TestNG_Framework_OrangeHRM\\\\Java_TestNG_Framework_OrangeHRM\\\\test-output\\\\emailable-report.html";
//		//attachments.("D:\\Project\\Basic_Java_TestNG_Framework_OrangeHRM\\Java_TestNG_Framework_OrangeHRM\\test-output\\emailable-report.html");
//		emailUtils.sendUsingSMTP(properties, "TestSubject","Body", attachments);
//	}
//	
//
//}
