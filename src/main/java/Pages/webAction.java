package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentTest;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.LogStatus;

//import Framework.EmailUtil;
import Framework.ExtentReport;
import Framework.Log;
import Framework.PropertyReader;
import Framework.ScreenShotCapture;


public class webAction {

	//Driver declaration
	protected WebDriver driver;

	//Variable declaration
	public static WebElement outValue = null;
	public static  List<WebElement> outvalues=null;
	String currentPath;
	public int stepNum = 1;
	public static ExtentTest test;
	public String message = "";
	public String testCaseStatus;
	String excelCellValue;
	public String testCaseID;
	String title;
	String cellValue;
	String cell_Value;
	String cell_Value_in;
	String cell_Value1;
	String numbericexcelCellValue;
	String cell1_value;
//	 Properties properties = null;
//	 Session session = null;
//	 Store store = null;
//     Folder inbox = null;
	  


	//Creating objects
	ExtentReport extentReportObject = new ExtentReport();
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm");

	//Config properties
	public String url_to = PropertyReader.readProperty("url");
	//public String url_to = PropertyReader.readProperty("makemytripurl");
	
	

	public String user = PropertyReader.readProperty("Username");
	public String password = PropertyReader.readProperty("Password");
	
	

	
	HashMap<String, String> locatorsHashMap = new HashMap<String, String>();
	HashMap<String, String> elementHashMap = new HashMap<String, String>();
	HashMap<String, String> excelHashMap= new HashMap<String, String>();
	public static HashMap<String, String> browserHashMap= new HashMap<String, String>();//newly created
	public static HashMap<String, String> testHashMap=new HashMap<String, String>();
	//Locators
	/*@FindBy(xpath="//input[contains(@class,'v-textfield')][@type='text']") 
	public static WebElement loginUsername;*/

	@BeforeSuite
	public void before_suite() throws IOException {
		try {
			DOMConfigurator.configure("log4j.xml");
			extentReportObject.publishReports();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("FAIL-Could not initiate extend reports");
		}
	}
	@BeforeClass
	//This method is for before class operations
	public void before_Class(ITestContext testName) throws IOException{
		try {
			System.out.println(browserHashMap.get(testName.getName()));
			launchDriver(browserHashMap.get(testName.getName()));
			getUrl();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("System could not navigate to application URL");
		}
	}

	@AfterClass
	public void logoutFromApplication() throws IOException, InterruptedException {
		try {
			excelFileReaderOfLocators("HomePage");
			driverWait();
			element("btnWelcome").click();
			Thread.sleep(1000);
			element("btnLogout").click();
			driverWait();
		} catch (Exception e) {
			Assert.fail("Unable to logout from the application");
			e.printStackTrace();
		}
	}


	@AfterSuite
//        public void mailReport() throws Exception {
//		  Thread.sleep(100);
//	      EmailUtil util = new EmailUtil(); 
//		  
//	  }
	//This method is to logout and close driver
	public void closeDriver() throws InterruptedException, IOException {

		if (driver != null) {
			driver.close();
		}
		ExtentReport.extent.flush();
	}
	/**
	 * This method is used to launch the driver
	 */
	
	public void launchDriver(String BrowserSetup) {
		try {
			if (BrowserSetup.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			} else{
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			}
		}

		catch (Exception e){
			e.printStackTrace();
			Assert.fail("Could not open the browser");
		}
	}

	//This method is to navigate to the homepage URL
	public void getUrl() {
		try {
			driver.navigate().to(url_to);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail(" Could not navigate to the Application Login page");
		}
	}
	//This method is to Initialize the elements
	public boolean initializeElement() {
		boolean returnValue = false;
		try {
			PageFactory.initElements(driver, this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public boolean pageValidation(String expectedTitle) {
		boolean returnValue=false;
		try {
			String title=driver.getTitle();
			if (title.equals(expectedTitle)) {
				message = "User navigation to the "+expectedTitle+" Application Page";
			} else {
				message = "User navigation to the "+expectedTitle+" Application Page";
				Assert.fail(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Could not valudate the page");
		}
		return returnValue;
	}
	public void popupmessage() {
		driver.switchTo().alert().dismiss();
		
	}



	public void excelFileReaderOfLocators(String pageName) throws IOException{

		String filePath =  System.getProperty("user.dir")+"\\src\\data\\Locators.xlsx";
		String fileName =  PropertyReader.readProperty("fileName");//to take file type
		String sheetName =  pageName;
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook Workbook = null;    
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			Workbook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			Workbook = new HSSFWorkbook(inputStream);
		}
		Sheet WorkSheet = Workbook.getSheet(sheetName);
		int rowCount = WorkSheet.getLastRowNum()-WorkSheet.getFirstRowNum();

		try {
			for (int i = 0; i < rowCount+1; i++) {
				Row row = WorkSheet.getRow(i);
				Cell cell= row.getCell(0);
				String cell_Value_locators = cell.toString();
				//System.out.println(cell_Value);
				Cell cell1= row.getCell(1);
				String cell_Value_By = cell1.toString();
				Cell cell_Out_Value= row.getCell(2);
				String CoutValue = cell_Out_Value.toString();
				locatorsHashMap.put(cell_Value_locators, cell_Value_By);
				elementHashMap.put(cell_Value_locators,CoutValue);				
				inputStream.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to read the Excel File");
		}

	}	

	//This is to read the locators
	public WebElement element(String element_Name) {	

		String byLocator=locatorsHashMap.get(element_Name);
		String valueLocator= elementHashMap.get(element_Name);

		WebDriverWait switchWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			switch (byLocator) {
			case "classname":
				//@FindBy(className=valueLocator) ;
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.className(valueLocator)));
				outValue=driver.findElement(By.className(valueLocator));
				break;
			case "cssselector":
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(valueLocator)));
				outValue=driver.findElement(By.cssSelector(valueLocator));		
				break;
			case "id":
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.id(valueLocator)));
				outValue=driver.findElement(By.id(valueLocator));		
				break;
			case "linktext":
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(valueLocator)));
				outValue=driver.findElement(By.linkText(valueLocator));		
				break;
			case "name":
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.name(valueLocator)));
				outValue=driver.findElement(By.name(valueLocator));		
				break;
			case "partiallinktext":
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(valueLocator)));
				outValue=driver.findElement(By.partialLinkText(valueLocator));		
				break;
			case "tagname":
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(valueLocator)));
				outValue=driver.findElement(By.tagName(valueLocator));		
				break;
			case "xpath":
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueLocator)));
				outValue=driver.findElement(By.xpath(valueLocator));
				break;
			default:
				break;
			}	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Unable to locate the element");
		}
		return outValue;
	}
	//findelements locator
	public List<WebElement> elements(String element_Name, String value) {	

		String byLocator=locatorsHashMap.get(element_Name);
		String valueLocator= elementHashMap.get(element_Name);
		if (value.length()>0) {
		valueLocator = String.format(valueLocator, value);
		}

		WebDriverWait switchWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			switch (byLocator) {
			case "classname":
				//@FindBy(className=valueLocator) ;
				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.className(valueLocator)));
				outvalues=driver.findElements(By.className(valueLocator));
				break;
			case "cssselector":
//				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(valueLocator)));
				outvalues=driver.findElements(By.cssSelector(valueLocator));		
				break;
			case "id":
//				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.id(valueLocator)));
				outvalues=driver.findElements(By.id(valueLocator));		
				break;
			case "linktext":
//				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(valueLocator)));
				outvalues=driver.findElements(By.linkText(valueLocator));		
				break;
			case "name":
//				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.name(valueLocator)));
				outvalues=driver.findElements(By.name(valueLocator));		
				break;
			case "partiallinktext":
//				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(valueLocator)));
				outvalues=driver.findElements(By.partialLinkText(valueLocator));		
				break;
			case "tagname":
//				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(valueLocator)));
				outvalues=driver.findElements(By.tagName(valueLocator));		
				break;
			case "xpath":
//				switchWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueLocator)));
				outvalues=driver.findElements(By.xpath(valueLocator));
				break;
			default:
				break;
			}	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Unable to locate the element");
		}
		return outvalues;
	}


	//This method is to login to the application
		boolean val=false;
		public boolean loginToApplication() throws IOException, InterruptedException {
		try {
			excelFileReaderOfLocators("LoginPage");
			element("loginUsername").sendKeys(user);
			element("loginPassword").sendKeys(password);
			element("btnLogin").click();
			driverWait();
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail(" Could not login to the application");
		}
		return val;
	}

	//This method is to wait the driver upto the expected conditions
	public void driverUntilWait(WebElement element) {
		WebDriverWait driverwait=new WebDriverWait (driver, Duration.ofSeconds(10));
		driverwait.until(ExpectedConditions.elementToBeClickable(element));
	}


	//This method is to read the data from the excel file
	public String excelFileReader(String varName) throws IOException{
		String filePath =  System.getProperty("user.dir")+"\\src\\data\\inputData1_update.xlsx";
		String fileName =  PropertyReader.readProperty("fileName");
		String sheetName =  PropertyReader.readProperty("sheetName");
		String column_Value= PropertyReader.readProperty("setofData_ColumnNumber");
		int c_Value = Integer.parseInt(column_Value);
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook Workbook = null;    
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			Workbook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			Workbook = new HSSFWorkbook(inputStream);
		}
		Sheet WorkSheet = Workbook.getSheet(sheetName);
		int rowCount = WorkSheet.getLastRowNum()-WorkSheet.getFirstRowNum();

		for (int i = 0; i < rowCount+1; i++) {
			Row row = WorkSheet.getRow(i);//row get selected
			Cell cell= row.getCell(0);//column get selected and pick the cell value
			cell_Value = cell.toString();//convert the value into string(var name)
			if (cell_Value.equalsIgnoreCase(varName))//
			{
				Cell cell_Out_Value= row.getCell(c_Value);//get the column values(var values)
				excelCellValue=cell_Out_Value.getStringCellValue();
				inputStream.close();
				break;
			}		
		}
		return excelCellValue;

	}
	//newly created
	public void excelFileReaderOfNumeric(String varTest) throws IOException{

		String filePath =  System.getProperty("user.dir")+"\\src\\data\\inputData1_update.xlsx";
		String fileName =  PropertyReader.readProperty("fileName");
		String sheetName =  PropertyReader.readProperty("sheetName");
		String column_Value= PropertyReader.readProperty("setofData_ColumnNumber");
		int c_Value = Integer.parseInt(column_Value);
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook Workbook = null;    
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			Workbook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			Workbook = new HSSFWorkbook(inputStream);
		}
		Sheet WorkSheet = Workbook.getSheet(sheetName);
		int rowCount = WorkSheet.getLastRowNum()-WorkSheet.getFirstRowNum();

		for (int i = 0; i < rowCount+1; i++) {
			Row row = WorkSheet.getRow(i);
			Cell cell= row.getCell(0);
			cell_Value = cell.toString();
			//System.out.println(cell_Value);
			if (cell_Value.equalsIgnoreCase(varTest))
			{
				//for (int j = 0; j < rowCount+1; j++) {
				Row row1 = WorkSheet.getRow(i);//next column  
				Cell cell1= row1.getCell(1);
				if (cell1!=null) {
					cell_Value1 = cell1.toString();
					Cell cell_Out_Value = row1.getCell(c_Value);
					if (cell_Out_Value!=null) {
						numbericexcelCellValue = cell_Out_Value.toString();
						excelHashMap.put(cell_Value1, numbericexcelCellValue);
					}
				}
				inputStream.close();
			}
		}	
	}


	//This method is to write the values in excel file
	public void excelFileWriter(String inputVariable, String inputValue) throws IOException{
		try {
			String filePath_in =  System.getProperty("user.dir")+"\\src\\data\\inputData1_update.xlsx";
			String fileName_in =  PropertyReader.readProperty("fileName_writer");
			String sheetName_in =  PropertyReader.readProperty("sheetName_writer");
			String column_Value= PropertyReader.readProperty("setofData_ColumnNumber");
			int c_Value = Integer.parseInt(column_Value);

			FileInputStream inputStream = new FileInputStream(filePath_in);
			Workbook Workbook = null;    
			String fileExtensionName = fileName_in.substring(fileName_in.indexOf("."));
			if(fileExtensionName.equals(".xlsx")){
				Workbook = new XSSFWorkbook(inputStream);
			}
			else if(fileExtensionName.equals(".xls")){
				Workbook = new HSSFWorkbook(inputStream);
			}
			Sheet WorkSheet = Workbook.getSheet(sheetName_in);
			int rowCount = WorkSheet.getLastRowNum()-WorkSheet.getFirstRowNum();
			for (int i = 0; i < rowCount+1; i++) {
				Row row = WorkSheet.getRow(i);
				Cell cell= row.getCell(1);
				cell_Value = cell.toString();
				if (cell_Value.equalsIgnoreCase(inputVariable)) {
					row.createCell(c_Value).setCellValue(inputValue);
					inputStream.close();
					FileOutputStream outputStream = new FileOutputStream(filePath_in);
					Workbook.write(outputStream);


					outputStream.close();
				}
				}
			
		} catch (Exception e) {
			
			// TODO: handle exception
			e.printStackTrace();
					}

	}

	//This method is to write the values in excel file
	public void excelFileWriterforTestNG(String inputVariable, String inputValue ,String Spath) throws IOException{
		String filePath_in =  System.getProperty("user.dir")+"\\src\\data\\TestNGXML.xlsx";
		String fileName_in =  PropertyReader.readProperty("fileName_writer");
		String sheetName_in =  PropertyReader.readProperty("sheetName_writer");
		String column_Value= "7";
		int c_Value = Integer.parseInt(column_Value);

		/*System.out.println(inputVariable);
			System.out.println(inputValue);*/
		FileInputStream inputStream = new FileInputStream(filePath_in);
		Workbook Workbook = null;    
		String fileExtensionName = fileName_in.substring(fileName_in.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			Workbook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			Workbook = new HSSFWorkbook(inputStream);
		}
		Sheet WorkSheet = Workbook.getSheet(sheetName_in);
		int rowCount = WorkSheet.getLastRowNum()-WorkSheet.getFirstRowNum();
		for (int i = 0; i < rowCount+1; i++) {
			Row row = WorkSheet.getRow(i);
			Cell cell= row.getCell(2);
			Cell cell_brw= row.getCell(1);
			/*if (row.getCell(6)!=null && row.getCell(3).toString().equalsIgnoreCase("Yes")) {
				Cell cell1= row.getCell(6);
				cell1_value=cell1.toString();
				if (cell_Value.equalsIgnoreCase(inputVariable)) {
					row.createCell(c_Value).setCellValue(inputValue);
					if (Spath!=null) {
						row.createCell(c_Value+1).setCellValue(Spath);
					}
					inputStream.close();
					FileOutputStream outputStream = new FileOutputStream(filePath_in);
					Workbook.write(outputStream);
					outputStream.close();
				}
			}else */if 
			((cell.toString()+"_"+cell_brw.toString()).equalsIgnoreCase(inputVariable)&&row.getCell(3).toString().equalsIgnoreCase("Yes") ) {
				row.createCell(c_Value).setCellValue(inputValue);
				if (Spath!=null) {
					row.createCell(c_Value+1).setCellValue(Spath);
				}
				inputStream.close();
				FileOutputStream outputStream = new FileOutputStream(filePath_in);
				Workbook.write(outputStream);
				outputStream.close();
			}
		}
	}

	//Initialize the robot class
	protected Robot keyboardAction() throws AWTException {
		Robot keyboard=new Robot();
		return keyboard;
	}

	//This method is to wait the driver 
	public void driverWait() throws InterruptedException {
		Thread.sleep(2000);
	}

	//This method is to generate filename for report
	public static String generateFileName(ITestResult result) {
		Date date = new Date();
		String fileName = result.getName() + "_" + sdf.format(date);
		return fileName;
	}

	@AfterMethod
	//This method is to get the result or save the screenshot path
	public void getResult(ITestResult result, ITestContext testName) {//listner
		try {
			String screenShotPath = "";
			// creating object for ScreenShotCapture
			ScreenShotCapture screenshotObject = new ScreenShotCapture();
			if (result.getStatus() == ITestResult.FAILURE) {
				Log.error(message);
				test.log(LogStatus.FAIL, " FAIL - " + message );
				//test.log(LogStatus.
				// Logging into JIRA for Failure
				test.log(LogStatus.FAIL, result.getThrowable());
				if (driver != null) {
					screenShotPath = screenshotObject.capture(driver, generateFileName(result));

					test.log(LogStatus.FAIL,
							"Snapshot below: " + result.getMethod() + test.addScreenCapture(screenShotPath + ".png"));
				}
				String spath=System.getProperty("user.dir") + File.separator + "src" + File.separator + "reports"
						+ File.separator + screenShotPath + ".png";
				//excelFileWriterforTestNG(testHashMap.get(testName.getName()),"Fail",spath);
				testCaseStatus = "FAIL";
			} else {
				testCaseStatus = "PASS";
				test.log(LogStatus.PASS, "PASS-Test case Executed Successfully");
				//excelFileWriterforTestNG(testHashMap.get(testName.getName()),"Pass",null);
			}
			

			// Ending Extent Test
//			ExtentReport.extent.endTest(test);

			ExtentReport.extent.flush();
			// Logout post pass or fail
			if (driver != null) {
				driver.quit();
			}
			driver = null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	//zip util
	public void ZipUtils() throws IOException {
		
		//public void ZipUnzip() throws IOException {
			        String zip =Zip.zip(new File("./test-output"));
			        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("./test-outputZipped.zip"),1000);
			        byte[] decode = Base64.getDecoder().decode(zip);
			        stream.write(decode);
			        stream.close();

			       
		}
	public static void emailUtil(){
		
		String to="zucitestemail2@gmail.com";//change accordingly  
		  final String user="zucitestemail1@gmail.com";//change accordingly  
		  final String password="mfefgodiptuozjny";//change accordingly  
		   
		  //1) get the session object     
		  Properties properties = System.getProperties();  
		  properties.setProperty("mail.smtp.host", "smtp.gmail.com");  
		  properties.put("mail.smtp.auth", "true");  
		  properties.put("mail.smtp.auth", "true"); //enable authentication
		  properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		  
		  Session session = Session.getDefaultInstance(properties,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(user,password);  
		   }  
		  }); 
		     
		  //2) compose message     
		 
		  try{   
			  
			    MimeMessage message = new MimeMessage(session);  
			    message.setFrom(new InternetAddress(user));  
			    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
			    message.setSubject("Astra Test Report");  

		   //3) create MimeBodyPart object and set your message text     
		    BodyPart messageBodyPart1 = new MimeBodyPart();  
		    messageBodyPart1.setText("Hi Team \n\n PFA for Astra Automation Report");  
		      
		    //4) create new MimeBodyPart object and set DataHandler object to this object      
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		  
		    String filename = "C:\\Users\\indhu.gurunadhan\\Downloads\\vi\\Java_TestNG_Framework_OrangeHRM (1)\\Java_TestNG_Framework_OrangeHRM\\test-output\\emailable-report.html";//change accordingly  
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName(filename);  
		     
		     
		    //5) create Multipart object and add MimeBodyPart objects to this object      
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(messageBodyPart2);  
		  
		    //6) set the multiplart object to the message object  
		    message.setContent(multipart );  
		     
		    //7) send message  
		    Transport.send(message);  
		   
		   System.out.println("message sent....");  
		   }catch (MessagingException ex) {ex.printStackTrace();}
	    }
	
//	   public void readMails() {
//		   String userName = "zucitestemail2@gmail.com";// provide user name
//			  String passwordmail = "uaafnthrotohbfzw";// provide password
//			  String saveDirectory = System.getProperty("user.dir") + "\\SaveEmails";
//	        properties = new Properties();
//	        properties.setProperty("mail.store.protocol", "imaps");
//	        try {
//	            session = Session.getDefaultInstance(properties, null);
//	            store = session.getStore("imaps");
//	            store.connect("imap.gmail.com", userName, passwordmail);
//	            inbox = store.getFolder("INBOX");
//
//	            int unreadMailCount = inbox.getUnreadMessageCount();
//	            System.out.println("No. of Unread Mails = " + unreadMailCount);
//
//	            inbox.open(Folder.READ_WRITE);
//
//	            Message messages[] = inbox.getMessages();
//	            System.out.println("No. of Total Mails = " + messages.length);
//	            for (int i = messages.length; i > (messages.length - unreadMailCount); i--) {
//	                Message message = messages[i - 1];
//
//	                Address[] from = message.getFrom();
//	                System.out.println("====================================== Mail no.: " + i + " start ======================================");
//	                System.out.println("Date: " + message.getSentDate());
//	                System.out.println("From: " + from[0]);
//	                System.out.println("Subject: " + message.getSubject());
//
//	                String contentType = message.getContentType();
//	                String messageContent = "";
//
//	                // store attachment file name, separated by comma
//	                String attachFiles = "";
//
//	                if (contentType.contains("multipart")) {
//	                    // content may contain attachments
//	                    Multipart multiPart = (Multipart) message.getContent();
//	                    int numberOfParts = multiPart.getCount();
//	                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
//	                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
//	                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
//	                            // this part is attachment
//	                            String fileName = part.getFileName();
//	                            attachFiles += fileName + ", ";
//	                            part.saveFile(saveDirectory + File.separator + fileName);
//	                        } else {
//	                            // this part may be the message content
//	                            messageContent = part.getContent().toString();
//	                        }
//	                    }
//
//	                    if (attachFiles.length() > 1) {
//	                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
//	                    }
//	                } else if (contentType.contains("text/plain")
//	                        || contentType.contains("text/html")) {
//	                    Object content = message.getContent();
//	                    if (content != null) {
//	                        messageContent = content.toString();
//	                    }
//	                }
//	                System.out.println("Attachments: " + attachFiles);
//	      
//	                System.out.println("====================================== Mail no.: " + i + " end ======================================");
//	            }
//
//	            // disconnect
//	            inbox.close(false);
//	            store.close();
//	        } catch (NoSuchProviderException ex) {
//	            System.out.println("No provider for pop3.");
//	            ex.printStackTrace();
//	        } catch (MessagingException ex) {
//	            System.out.println("Could not connect to the message store");
//	            ex.printStackTrace();
//	        } catch (IOException ex) {
//	            ex.printStackTrace();
//	        }
//
//	    }

}