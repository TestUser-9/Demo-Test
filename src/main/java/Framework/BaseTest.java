//package Framework;
//
//import java.awt.AWTException;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.UnsupportedFlavorException;
//import java.awt.event.KeyEvent;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TimeZone;
//
//import org.apache.commons.io.FileUtils;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
////import org.openqa.selenium.remote.server.handler.SendKeys;
////import org.openqa.selenium.safari.SafariDriver.WindowType;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
//
//import com.core.automation.framework.ReportforTestNG.TestngEmailableReport;
//import com.sun.jersey.server.impl.wadl.WadlApplicationContextInjectionProxy;
//
//import ru.yandex.qatools.ashot.AShot;
//import ru.yandex.qatools.ashot.Screenshot;
//import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
//public class BaseTest extends FrameworkConstants { {
//	
//
//	
//
//	/**
//	 * Represents the BaseTest along with common setup methods
//	 * 
//	 * @author sariya.khan
//	 * @version 1.0
//	 */
//
//		static Map<Integer, ArrayList<Object>> responseData = new HashMap<Integer, ArrayList<Object>>();
//		public static BrowserFactory browserFactory = BrowserFactory.getInstance();
//		public static Target tarOutValue = null;
//		public static WebDriver driver =browserFactory.getDriver();
//		public static String overallStartTime,overallEndTime;
//		Date starttime,endtime;
//		public static String classname;
//		public static String url;
//		public static String pahub_url;
//		public static String epa_url;
//		public static String promptpa_url;
//		//********* Added code by zuci ****************//
//		public static SimpleDateFormat time = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		protected static HashMap<String, Object> postApiData= new HashMap<>();
//		public static String releaseId;
//		public static String strBrowserType;
//		public static HashMap<String,String> qcmsResponseData = new HashMap<>();
//		public static File TestDataFile;
//		public static HashMap<String,String> dbEOCDetails = new HashMap<>();
//		public static LinkedHashMap<String,String> dbDrugDetails = new LinkedHashMap<>();
//		public static String instanceName;
//		public static HashMap<String,String> relatedTestCaseAPI = new HashMap<>();
//		public static HashMap<String,String> relatedTestCaseAPIResult = new HashMap<>();
//		public static String exePlatform="";
//		//********* Added code End ****************//
//
//		public WebDriver getDriver() {
//
//			return browserFactory.getDriver();
//
//		}
//
//
//		private void setDriver(WebDriver driver) { 
//			this.driver = browserFactory.getDriver(); 
//		}
//
//
//
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : setup <br>
//		 * Description : Used to setup initial configuration<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : <br>
//		 * Return Value : <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : <br>
//		 * =========================================================================
//		 * <br>
//		 * @throws Exception 
//		 */
//		// @BeforeTest(/* alwaysRun = true, description = "Class Level Setup!" */)
//		@BeforeClass
//		public void setup(ITestContext cname) throws Exception {
//			try {
//				synchronized (browserFactory) {
//					Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\agadia-test-pahub\\src\\test\\resources\\AlreadyOpenedWindowClose.exe");
//					getTestDataFromQcmsResponse(cname.getName());
//					ExcelUtil.testdata.put(Thread.currentThread().getName() + ".testName",cname.getName());
//					String[] caseID= cname.getName().split("_");
//					if (MailFunctions.testCaseIds==null)
//						MailFunctions.testCaseIds=caseID[0].replace("TS", "")+",";
//					else if (!MailFunctions.testCaseIds.contains(caseID[0].replace("TS", ""))) 
//						MailFunctions.testCaseIds=MailFunctions.testCaseIds+caseID[0].replace("TS", "")+",";
//
//					TestngEmailableReport.QAQwnerMap.put(cname.getName(), qcmsResponseData.get(Thread.currentThread().getName() + ".QaOwner"));
//					String browser_source=Index.prop.getProperty("browser_source").trim();
//					String jenkins_browser_source = System.getProperty("TargetBrowser");// To read a jenkins browser source
//
//					if (jenkins_browser_source==null) {
//						if (browser_source.equalsIgnoreCase("Config")) 
//							strBrowserType = Index.prop.getProperty("browserType").trim();
//						else if (browser_source.equalsIgnoreCase("Testdata")) 
//							strBrowserType = qcmsResponseData.get(Thread.currentThread().getName() + ".TargetBrowser");
//					}else 
//						strBrowserType =jenkins_browser_source;
//
//
//					TestngEmailableReport.browser_map.put(cname.getName(), strBrowserType);			
//					String jenkins_execution_instance = System.getProperty("ExecuteEnvironment");// To read a jenkins execution instance
//					if (jenkins_execution_instance==null) {
//						if (Index.prop.getProperty("execution.InstanceSource").trim().equalsIgnoreCase("Config"))
//							instanceName=Index.prop.getProperty("execution_instance").trim();
//						else if (Index.prop.getProperty("execution.InstanceSource").trim().equalsIgnoreCase("Testdata")) 
//							instanceName=qcmsResponseData.get(Thread.currentThread().getName() + ".Environment");
//					}else 
//						instanceName=jenkins_execution_instance;
//					//Promptpa customer url name fetching
//					FileReader reader = new FileReader(System.getProperty("user.dir")+"\\agadia-test-pahub\\src\\test\\resources\\PromptPaUrlCustomers.json");                        
//					JSONParser jsonParser = new JSONParser();
//					JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
//					String customer_url = "NA";
//					if(jsonObject.containsKey(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName")))
//					{
//						String customerUrlName = jsonObject.get(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName")).toString().trim();
//						customer_url = customerUrlName.equalsIgnoreCase("")?"":"_"+customerUrlName;
//					}
//					Log.info("ApplicableClient : "+ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName"));
//					Log.info("PromptPa url customer name : "+customer_url);
//					String errorMessage = "PromptPa url customer name not configured in "+System.getProperty("user.dir")+"\\agadia-test-pahub\\src\\test\\resources\\PromptPaUrlCustomers.json";
//					switch (qcmsResponseData.get(Thread.currentThread().getName() + ".Source").toLowerCase()) {
//					case "pahub":					
//						switch (instanceName.toLowerCase()) {
//						case "qa":
//							url = Index.prop.getProperty("qa_url").trim();
//							epa_url = Index.prop.getProperty("epa_qa_url").trim();
//							promptpa_url = customer_url.equalsIgnoreCase("NA")?errorMessage:Index.prop.getProperty("promptpa_qa").trim()+customer_url;
//							break;
//						case "auto_qa":
//							url = Index.prop.getProperty("autoqa_url").trim();
//							epa_url = Index.prop.getProperty("epa_auto_qa_url").trim();
//							promptpa_url = customer_url.equalsIgnoreCase("NA")?errorMessage:Index.prop.getProperty("promptpa_auto_qa").trim()+customer_url;
//							break;
//						case "pre-uat":
//							url = Index.prop.getProperty("Pre-UAT_url").trim();
//							epa_url = Index.prop.getProperty("epa_pre_uat_url").trim();
//							promptpa_url = customer_url.equalsIgnoreCase("NA")?errorMessage:Index.prop.getProperty("promptpa_pre_uat").trim()+customer_url;
//							break;
//						case "staging":
//							url = Index.prop.getProperty("Staging_url").trim();
//							break;
//						case "obsolete_auto_qa":
//							url = Index.prop.getProperty("obsolete_auto_qa").trim();
//							break;
//						case "obsolete_qa":
//							url = Index.prop.getProperty("obsolete_qa_url").trim();
//							break;
//						case "uiux_qa":
//							url = Index.prop.getProperty("UiUx_qa_url").trim();
//							break;
//						case "qapoc":
//							url = Index.prop.getProperty("qapoc_url").trim();
//							break;	
//						default:
//							System.out.println("Url switch case came to default mode");
//							break;
//						}
//						pahub_url = url;
//						break;
//					case "epa":
//						switch (instanceName.toLowerCase()) {
//						case "auto_qa":
//							url = Index.prop.getProperty("epa_auto_qa_url").trim();
//							pahub_url = Index.prop.getProperty("autoqa_url").trim();
//							epa_url = Index.prop.getProperty("epa_auto_qa_url").trim();
//							promptpa_url = customer_url.equalsIgnoreCase("NA")?errorMessage:Index.prop.getProperty("promptpa_auto_qa").trim()+customer_url;
//							break;
//						case "qa": case "qapoc":
//							url = Index.prop.getProperty("epa_qa_url").trim();
//							pahub_url = Index.prop.getProperty("qa_url").trim();
//							epa_url = Index.prop.getProperty("epa_qa_url").trim();
//							promptpa_url = customer_url.equalsIgnoreCase("NA")?errorMessage:Index.prop.getProperty("promptpa_qa").trim()+customer_url;
//							break;
//						case "obsolete_auto_qa":
//							url = Index.prop.getProperty("epa_auto_qa_url").trim();
//							pahub_url = Index.prop.getProperty("obsolete_auto_qa").trim();
//							break;
//						case "obsolete_qa":
//							url = Index.prop.getProperty("epa_qa_url").trim();
//							pahub_url = Index.prop.getProperty("obsolete_qa_url").trim();
//							break;
//						case "pre-uat":
//							url = Index.prop.getProperty("epa_pre_uat_url").trim();
//							pahub_url = Index.prop.getProperty("Pre-UAT_url").trim();
//							epa_url = Index.prop.getProperty("epa_pre_uat_url").trim();
//							promptpa_url = customer_url.equalsIgnoreCase("NA")?errorMessage:Index.prop.getProperty("promptpa_pre_uat").trim()+customer_url;
//							break;
//						case "uiux_qa":
//							url = Index.prop.getProperty("epa_qa_url").trim();
//							pahub_url = Index.prop.getProperty("UiUx_qa_url").trim();
//							epa_url = Index.prop.getProperty("epa_qa_url").trim();
//							promptpa_url = customer_url.equalsIgnoreCase("NA")?errorMessage:Index.prop.getProperty("promptpa_qa").trim()+customer_url;
//							break;
//						default:
//							System.out.println("Url switch case came to default mode");
//							break;
//						}
//						break;
//					case "promptpa":
//						switch (instanceName.toLowerCase()) {
//						case "auto_qa":
//							url = Index.prop.getProperty("promptpa_auto_qa").trim()+customer_url;
//							pahub_url = Index.prop.getProperty("autoqa_url").trim();
//							epa_url = Index.prop.getProperty("epa_auto_qa_url").trim();
//							promptpa_url = Index.prop.getProperty("promptpa_auto_qa").trim()+customer_url;
//							break;
//						case "qa":
//							url = Index.prop.getProperty("promptpa_qa").trim()+customer_url;
//							pahub_url = Index.prop.getProperty("qa_url").trim();
//							epa_url = Index.prop.getProperty("epa_qa_url").trim();
//							promptpa_url = Index.prop.getProperty("promptpa_qa").trim()+customer_url;
//							break;
//						case "obsolete_auto_qa":
//							url = Index.prop.getProperty("promptpa_auto_qa").trim()+customer_url;
//							pahub_url = Index.prop.getProperty("obsolete_auto_qa").trim();
//							break;
//						case "obsolete_qa":
//							url = Index.prop.getProperty("promptpa_qa").trim()+customer_url;
//							pahub_url = Index.prop.getProperty("obsolete_qa_url").trim();
//							break;
//						case "pre-uat":
//							url = Index.prop.getProperty("promptpa_pre_uat").trim()+customer_url;
//							pahub_url = Index.prop.getProperty("Pre-UAT_url").trim();
//							epa_url = Index.prop.getProperty("epa_pre_uat_url").trim();
//							promptpa_url = Index.prop.getProperty("promptpa_pre_uat").trim()+customer_url;
//							break;
//						case "uiux_qa":
//							url = Index.prop.getProperty("promptpa_qa").trim()+customer_url;
//							pahub_url = Index.prop.getProperty("UiUx_qa_url").trim();
//							epa_url = Index.prop.getProperty("epa_qa_url").trim();
//							promptpa_url = Index.prop.getProperty("promptpa_qa").trim()+customer_url;
//							break;
//						case "qapoc":
//							if (ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName").equalsIgnoreCase("Geisinger")) {
//								url = "https://qapoc.pahub.com/PromptPA/ghp/MemberHome.aspx";
//								pahub_url = Index.prop.getProperty("qapoc_url").trim();
//								epa_url = Index.prop.getProperty("epa_qa_url").trim();
//								promptpa_url = "https://qapoc.pahub.com/PromptPA/ghp/MemberHome.aspx";
//							}else
//								Assert.fail("qapoc environment is not available for-"+ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName"));
//							break;	
//						default:
//							System.out.println("Url switch case came to default mode");
//							break;
//						}
//						break;
//					default:
//						System.out.println("Environment switch case came to default mode");
//						break;
//					}
//
//
//					//
//					postApiData.put(Thread.currentThread().getName()+".TestCaseId", caseID[0]);
//					postApiData.put(Thread.currentThread().getName()+".Browser", TestngEmailableReport.browser_map.get(cname.getName()));
//					postApiData.put(Thread.currentThread().getName()+".Client", caseID[1]);
//					postApiData.put(Thread.currentThread().getName()+".Environment", instanceName);
//					postApiData.put(Thread.currentThread().getName()+".ExecuteComments",null);
//					postApiData.put(Thread.currentThread().getName()+".TestResult","Skipped");
//					postApiData.put(Thread.currentThread().getName()+".SystemName",null);
//					postApiData.put(Thread.currentThread().getName()+".AttachmentFile",null);
//					postApiData.put(Thread.currentThread().getName()+".FileName",null);
//
//					TestngEmailableReport.url_map.put(cname.getName(), url);
//					TestngEmailableReport.pahuburl_map.put(cname.getName(), pahub_url);
//					TestngEmailableReport.epa_map.put(cname.getName(), epa_url);
//					TestngEmailableReport.promptpa_map.put(cname.getName(), promptpa_url);
//					TestngEmailableReport.claimTest_map.put(cname.getName(), null);
//					postApiData.put(Thread.currentThread().getName()+".MemberSecondEligibilityComment", "NA");
//					if (ExcelUtil.testdata.containsKey(Thread.currentThread().getName() + "." + "ClaimTest")) {
//						if (ExcelUtil.testdata.get(Thread.currentThread().getName() + "." + "ClaimTest")
//								.equalsIgnoreCase("Skip"))
//							TestngEmailableReport.claimTest_map.replace(cname.getName(), "M");
//						else if (ExcelUtil.testdata.get(Thread.currentThread().getName() + "." + "ClaimTest")
//								.equalsIgnoreCase("Submit"))
//							TestngEmailableReport.claimTest_map.replace(cname.getName(), "S");
//					}else
//						TestngEmailableReport.claimTest_map.replace(cname.getName(), "NA");
//
//					TestngEmailableReport.ManuallyAddedPatient_map.put(cname.getName(), null);
//
//					TestngEmailableReport.Authorization_map.put(cname.getName(), "UN");
//				/*	if (!ExcelUtil.testdata.containsKey(Thread.currentThread().getName() + ".Authorization")) {
//						String[] tname = cname.getName().split("_");
//						String lname = tname[tname.length-1];
//						if(!cname.getName().toString().contains("Approval")|| lname.contains("Denied")||lname.contains("Denial")||lname.contains("Abort")) {
//							TestngEmailableReport.Authorization_map.put(cname.getName(), "");
//							ExcelUtil.testdata.put(Thread.currentThread().getName() + ".Authorization", "");
//						}
//						else
//							ExcelUtil.testdata.put(Thread.currentThread().getName() + ".Authorization", "null");
//					}*/
//					JavaFileReader javaFileReader = new JavaFileReader();
//					String[] authObj = {"AuthorizationsDetailsPage","new"};
//					boolean IsAuthApplicable = javaFileReader.IsStringExist(Index.javaFilePath.get(cname.getName()),authObj);
//					if(!IsAuthApplicable)
//						TestngEmailableReport.Authorization_map.put(cname.getName(), "NA");
//					if (qcmsResponseData.get(Thread.currentThread().getName() + ".RelatedTestCases") != null) {
//						if (!qcmsResponseData.get(Thread.currentThread().getName() + ".RelatedTestCases")
//								.equalsIgnoreCase("null")) {
//							for (String iterable_element : qcmsResponseData
//									.get(Thread.currentThread().getName() + ".RelatedTestCases").split(",")) {
//								relatedTestCaseAPIResult
//								.put(Thread.currentThread().getName() + "." + iterable_element.toString(),"Skipped as Parent TC Failed");
//							}
//						}
//					}
//
//					Log.info("javaFilePath : "+Index.javaFilePath.get(cname.getName()));
//					Log.info("Authorization initial status : "+TestngEmailableReport.Authorization_map.get(cname.getName()));
//					Log.info("Opening the browser");
//					BrowserFactory.setDriver(strBrowserType, cname);
//					browserFactory.getDriver().manage().window().maximize();
//					browserFactory.getDriver().get(url);
//					if (TestngEmailableReport.HubAndNodeUrl_map.get(cname.getName()).equalsIgnoreCase("Local"))
//						postApiData.put(Thread.currentThread().getName() + ".SystemName","Local("+System.getProperty("user.name")+")");
//					else
//						postApiData.put(Thread.currentThread().getName() + ".SystemName",TestngEmailableReport.HubAndNodeUrl_map.get(cname.getName()));
//
//					if (!ExcelUtil.testdata.containsKey(Thread.currentThread().getName() + ".NPI")) 
//						ExcelUtil.testdata.put(Thread.currentThread().getName() + ".NPI", "");
//					if (!ExcelUtil.testdata.containsKey(Thread.currentThread().getName() + ".ManuallyAddedPatient")) 
//						ExcelUtil.testdata.put(Thread.currentThread().getName() + ".ManuallyAddedPatient", "");
//					if (!ExcelUtil.testdata.containsKey(Thread.currentThread().getName() + ".Autonotificationcompleted")) 
//						ExcelUtil.testdata.put(Thread.currentThread().getName() + ".Autonotificationcompleted", "false");
//					if (!ExcelUtil.testdata.containsKey(Thread.currentThread().getName() + ".Autoauthorizationcompleted")) 
//						ExcelUtil.testdata.put(Thread.currentThread().getName() + ".Autoauthorizationcompleted", "false");
//
//					System.out.println(cname.getName()+" is running on -"+TestngEmailableReport.HubAndNodeUrl_map.get(cname.getName()));
//				}
//			}
//			catch (Exception e){
//				e.printStackTrace();
//				if (Index.dynamicLoginThread.containsKey(Thread.currentThread().getName()))
//					Index.dynamicLogins.replace(Index.dynamicLoginThread.get(Thread.currentThread().getName()), true);	
//				synchronized (cname) {
//					TriggerAPI(cname);
//				}
//				Assert.fail("Could not complete the @beforeClass actions");
//			}
//
//
//
//
//		}
//		@BeforeSuite
//		public void beforeSuite() throws ParseException {
//			Date dte = new Date();
//			overallStartTime=time.format(dte);
//			starttime = time.parse(time.format(dte));
//		}
//		//*********************************Added code end****************************
//
//
//
//		@AfterMethod()
//		public void afterMethod(ITestResult methodresult, ITestContext contextmethod) {
//
//			String[] cname=contextmethod.getName().split("_");
//			try {
//				List<String> logMsg =Reporter.getOutput(methodresult);
//				StringBuffer strbuffer = new StringBuffer();
//				for (String stringLog : logMsg) {
//					strbuffer.append(stringLog);
//					strbuffer.append("\n");
//				} 
//				if (methodresult.getStatus() == ITestResult.FAILURE) {
//
//					StackTraceElement[] error=methodresult.getThrowable().getStackTrace();
//					strbuffer.append("-Exception-");
//					strbuffer.append("\n");
//					strbuffer.append(methodresult.getThrowable().toString());
//					strbuffer.append("\n");
//					for (int i = 0; i < error.length; i++) {
//						strbuffer.append(error[i]);
//					}				
//					postApiData.put(Thread.currentThread().getName()+".TestResult", "Fail");
//
//					Calendar cal=Calendar.getInstance();
//					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
//					String timestamp = sdf.format(cal.getTime());
//					timestamp= timestamp.replace(":", "-");
//					browserFactory.getDriver().manage().window().fullscreen();
//					String encodedString = "";
//					/*if(TestngEmailableReport.browser_map.get(contextmethod.getName()).equalsIgnoreCase("IE")||
//							TestngEmailableReport.browser_map.get(contextmethod.getName()).equalsIgnoreCase("Chrome")||
//							TestngEmailableReport.browser_map.get(contextmethod.getName()).equalsIgnoreCase("Edge"))
//					{*/
//					File DestFile=new File(Index.pathProject+"\\Reports\\"+Index.dir+"\\"+TestngEmailableReport.clientName_map.get(contextmethod.getName())+"_"+cname[0]+"_DS"+postApiData.get(Thread.currentThread().getName()+".DataSetId")+"_"+methodresult.getName()+"_"+timestamp+".png");
//					TakesScreenshot scrShot =((TakesScreenshot) browserFactory.getDriver());
//					File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//					byte[] fileContent = scrShot.getScreenshotAs(OutputType.BYTES);
//					encodedString = Base64.getEncoder().encodeToString(fileContent);
//					//Copy file at destination
//					FileUtils.copyFile(SrcFile, DestFile);
//					//			ExcelUtil.excelReport(contextmethod.getName(), "Fail", methodresult.getName());
//					//}
//					browserFactory.getDriver().manage().window().maximize();
//					/*else {
//						File DestFolder=new File(Index.pathProject+"\\Reports\\"+Index.dir);
//		                if(!DestFolder.exists())
//		                    DestFolder.mkdir();
//		                File DestFile=new File(Index.pathProject+"\\Reports\\"+Index.dir+"\\"+TestngEmailableReport.clientName_map.get(contextmethod.getName())+"_"+cname[0]+"_"+methodresult.getName()+"_"+timestamp+".png");
//		                browserFactory.getDriver().manage().window().fullscreen();
//		                Screenshot s =new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browserFactory.getDriver());
//		                ImageIO.write(s.getImage(),"PNG",DestFile);
//		                byte[] fileContent = FileUtils.readFileToByteArray(DestFile);
//		                encodedString = Base64.getEncoder().encodeToString(fileContent);
//					}*/
//					TestngEmailableReport.sShotClientName_map.put(contextmethod.getName()+"."+methodresult.getName(), "../"+Index.dir.toString()+"/"+TestngEmailableReport.clientName_map.get(contextmethod.getName())+"_"+cname[0]+"_DS"+postApiData.get(Thread.currentThread().getName()+".DataSetId")+"_"+methodresult.getName()+"_"+timestamp+".png");
//					Log.info(methodresult.getName()+"- Fail");
//
//					if (TestngEmailableReport.eocid_map.get(contextmethod.getName())!=null) 
//						postApiData.put(Thread.currentThread().getName() + ".ExecuteComments", "EOCID - "+TestngEmailableReport.eocid_map.get(contextmethod.getName())+"  "+strbuffer.toString());
//					else if(strbuffer.toString().contains("No records available in the downloaded excel file"))
//						postApiData.put(Thread.currentThread().getName() + ".ExecuteComments", "INFO: No records available in the downloaded excel file"+"\n"+strbuffer.toString());
//					else 
//						postApiData.put(Thread.currentThread().getName() + ".ExecuteComments", strbuffer.toString());
//
//					postApiData.put(Thread.currentThread().getName()+".FileName",TestngEmailableReport.clientName_map.get(contextmethod.getName())+"_"+cname[0]+"_"+methodresult.getName()+"_"+timestamp+".png");
//					postApiData.put(Thread.currentThread().getName()+".AttachmentFile", encodedString);
//
//					if (relatedTestCaseAPI.get(Thread.currentThread().getName()+"."+methodresult.getName()) != null ) {
//						relatedTestCaseAPIResult.put(Thread.currentThread().getName()+"."+relatedTestCaseAPI.get(Thread.currentThread().getName()+"."+methodresult.getName()).trim(), "Fail");
//						relatedTestCaseAPIResult.put(Thread.currentThread().getName()+"."+relatedTestCaseAPI.get(Thread.currentThread().getName()+"."+methodresult.getName()).trim()+".FileName", TestngEmailableReport.clientName_map.get(contextmethod.getName())+"_"+cname[0]+"_"+methodresult.getName()+"_"+timestamp+".png");
//						relatedTestCaseAPIResult.put(Thread.currentThread().getName()+"."+relatedTestCaseAPI.get(Thread.currentThread().getName()+"."+methodresult.getName()).trim()+".ImageBytes", encodedString);
//						relatedTestCaseAPIResult.put(Thread.currentThread().getName()+"."+relatedTestCaseAPI.get(Thread.currentThread().getName()+"."+methodresult.getName()).trim()+".ExecuteComments", strbuffer.toString());
//					}
//				}
//				else {
//					//			ExcelUtil.excelReport(contextmethod.getName(), "Pass", " ");
//					Log.info(methodresult.getName()+"- Pass");
//					postApiData.put(Thread.currentThread().getName()+".TestResult", "Pass");
//					if (TestngEmailableReport.eocid_map.get(contextmethod.getName())!=null) 
//						postApiData.put(Thread.currentThread().getName() + ".ExecuteComments", "EOCID - "+TestngEmailableReport.eocid_map.get(contextmethod.getName())+"  "+"Last successfull method -"+ methodresult.getName());
//					else 
//						postApiData.put(Thread.currentThread().getName() + ".ExecuteComments", "Last successfull method -"+ methodresult.getName());
//
//					if (relatedTestCaseAPI.get(Thread.currentThread().getName()+"."+methodresult.getName()) != null )
//					{
//						relatedTestCaseAPIResult.put(Thread.currentThread().getName()+"."+relatedTestCaseAPI.get(Thread.currentThread().getName()+"."+methodresult.getName()).trim(), "Pass");
//						relatedTestCaseAPIResult.put(Thread.currentThread().getName()+"."+relatedTestCaseAPI.get(Thread.currentThread().getName()+"."+methodresult.getName()).trim()+".ExecuteComments", postApiData.get(Thread.currentThread().getName() + ".ExecuteComments").toString());
//					}
//					if(strbuffer.toString().contains("No records available in the downloaded excel file"))
//						postApiData.put(Thread.currentThread().getName() + ".ExecuteComments", "INFO: No records available in the downloaded excel file"+"\n"+postApiData.get(Thread.currentThread().getName() + ".ExecuteComments"));
//
//				}
//				if (methodresult.getName().contains("AdditionalInfo")&& methodresult.getStatus()==ITestResult.SUCCESS && !contextmethod.getName().contains("TS80504") && !contextmethod.getName().contains("TS80503")) {
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//					}
//					loadProgress(By.id("ctl00_img_Loading"));
//					WaitPageLoad(By.xpath("//*[@id='ctl00_EocUsercontrol_lblEOCID' or @id='PaHubControl1_lblEOCID']"));
//					Target ele = new Target(By.xpath("//*[@id='ctl00_EocUsercontrol_lblEOCID' or @id='PaHubControl1_lblEOCID']"),"EOC ID locator");
//					if(verifyElementVisible(ele,true))
//					{
//						if (browserFactory.getDriver().findElement(By.xpath("//*[@id='ctl00_EocUsercontrol_lblEOCID' or @id='PaHubControl1_lblEOCID']")).getText().equalsIgnoreCase("")) {
//							try {
//								Thread.sleep(10000);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//						}
//						TestngEmailableReport.eocid_map.put(contextmethod.getName(), browserFactory.getDriver().findElement(By.xpath("//*[@id='ctl00_EocUsercontrol_lblEOCID' or @id='PaHubControl1_lblEOCID']")).getText());
//						postApiData.put(Thread.currentThread().getName()+".ExecuteComments", "EOCID - "+TestngEmailableReport.eocid_map.get(contextmethod.getName())+"  "+"Last successfull method -"+ methodresult.getName());
//					}
//				}
//			}catch (IOException e) {
//				//reportMessage(STEP_STATUS_FAIL, "I am in after method");		
//				e.printStackTrace();
//			}
//
//		}
//		@AfterSuite(alwaysRun=true)
//		public void afterSuite() throws ParseException, FileNotFoundException {
//			Date dte = new Date();
//			endtime = time.parse(time.format(dte));
//			overallEndTime=time.format(dte);
//			TestngEmailableReport.lngDuration=endtime.getTime()-starttime.getTime();
//			if (TestngEmailableReport.lngDuration<=0) {
//				TestngEmailableReport.lngDuration=TestngEmailableReport.lngDuration+86400000;
//			}
//			if (browserFactory.getDriver()!=null) {
//				browserFactory.getDriver().close();
//			}
//			/*if (Index.prop.getProperty("Execution.Platform").trim().equalsIgnoreCase("Hub_Node")&&browserFactory.deactivatedNodes.contains(";"))
//				Index.tcount=Integer.toString((Integer.parseInt(Index.tcount)-browserFactory.deactivatedNodes.split(";").length));*/
//
//			if (instanceName.equalsIgnoreCase("qa")) {
//				try {
//					JSONParser parser = new JSONParser();
//					Object obj = parser.parse(new FileReader(System.getProperty("user.dir")
//							+ "\\agadia-test-pahub\\src\\test\\resources\\CustomConfigLoginCredentials.json"));
//					JSONObject jsonObject = (JSONObject) obj;
//					Iterator iterator = jsonObject.keySet().iterator();
//					while (iterator.hasNext()) {
//						String key = (String) iterator.next();
//						JSONObject details = (JSONObject) jsonObject.get(key);
//						ExcelUtil.testdata.put(Thread.currentThread().getName() + ".testName", "TS00000_FlagReset");
//						ExcelUtil.testdata.put(Thread.currentThread().getName() + ".customerName", key);
//						ExcelUtil.testdata.put(Thread.currentThread().getName() + ".Login_Id",details.get("resetlogin").toString());
//						postApiData.put(Thread.currentThread().getName() + ".SystemName", System.getProperty("user.name"));
//						postApiData.put(Thread.currentThread().getName() + ".Environment", instanceName);
//						switch (key.toLowerCase()) {
//						case "ingeniorx":
//							apiCustomerConfig("AppendDuplicateEocInfo", "1");
//							break;
//						default:
//							System.out.println("Suite level CC flag reset -There is no customer case available for -"+key);
//							break;
//						}
//					}
//
//				} catch (IOException | org.json.simple.parser.ParseException e) {
//					e.printStackTrace();
//				} 
//			}
//			if (BaseTest.exePlatform.equalsIgnoreCase("Hub_Node")) {
//				MailFunctions mail = new MailFunctions();
//				mail.sendMailOnDeactiveUsers();
//			}
//		}
//
//		public void WaitPageLoad(By locator) {
//			long waitTimeSeconds = 0;
//			long start = System.currentTimeMillis();
//			long finish =0;
//			try {
//				String jenkins_pageload_wait = System.getProperty("PageLoadWait");// To read a jenkins execution PageLoad Wait
//				if(jenkins_pageload_wait==null) {
//					waitTimeSeconds = Long.parseLong(Index.prop.getProperty("PageLoadWait").trim());
//				}
//				else {
//					waitTimeSeconds=Long.parseLong(System.getProperty("PageLoadWait"));
//				}
//				if (browserFactory.getDriver().findElements(locator).size() > 0) {
//					finish = System.currentTimeMillis();
//					String totalTime = String.valueOf(finish - start);
//					reportMessage(STEP_STATUS_DONE, "Page load wait, Page Loaded Succesfully");
//					reportMessage(STEP_STATUS_DONE, "Wait from config : "+String.valueOf(waitTimeSeconds) +"and time taken to load element '"+locator.toString()+"' is "+totalTime+" ms");
//				} else {
//					WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTimeSeconds));
//					WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//					if (we != null) {
//						finish = System.currentTimeMillis();
//						String totalTime = String.valueOf(finish - start);
//						reportMessage(STEP_STATUS_DONE, "Page load wait, Page Loaded Succesfully");
//						reportMessage(STEP_STATUS_DONE, "Wait from config : "+String.valueOf(waitTimeSeconds) +" and time taken to load element '"+locator.toString()+"' is "+totalTime+" ms");
//					} else {
//						finish = System.currentTimeMillis();
//						String totalTime = String.valueOf(finish - start);
//						reportMessage(STEP_STATUS_FAIL,
//								"Page load wait, Page Not Loaded in given time seconds " + waitTimeSeconds);
//						reportMessage(STEP_STATUS_DONE, "Wait from config : "+String.valueOf(waitTimeSeconds) +" and time taken to load element '"+locator.toString()+"' is "+totalTime+" ms");
//						Index.testres = "Failed";
//						Log.info("Slow Page Load");
//					}
//				}
//			} catch (WebDriverException wde) {
//				reportMessage(STEP_STATUS_WARN, "Page load wait, Page Not Loaded in given time seconds " + waitTimeSeconds+" seconds");
//				Log.info("Browser got exited before it gets connected");
//			} 
//		}
//
//
//		public void loadProgress(By locator) {
//			long start = System.currentTimeMillis();
//			long finish = 0;
//			try {
//				//********* Added code by zuci to get processing wait from config file ****************//
//				long WaitTime;
//				long visibleWaitTime;
//				String jenkins_processing_wait = System.getProperty("ProcessingWait");// To read a jenkins execution Processing Wait
//				if(jenkins_processing_wait==null) {
//					WaitTime=Long.parseLong(Index.prop.getProperty("ProcessingWait").trim());
//					visibleWaitTime=Long.parseLong(Index.prop.getProperty("ProcessingVisibilityWait").trim());
//				}
//				else {
//					WaitTime=Long.parseLong(System.getProperty("ProcessingWait"));
//					visibleWaitTime=Long.parseLong(Index.prop.getProperty("ProcessingVisibilityWait").trim());
//				}
//				WebDriverWait visiblewait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(visibleWaitTime));
//				visiblewait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//				WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(WaitTime));
//				wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//				Log.info("Processing Model window is closed");
//				finish = System.currentTimeMillis();
//				String totalTime = String.valueOf(finish - start);
//				reportMessage(STEP_STATUS_PASS, "Processing wait : "+ WaitTime);
//				reportMessage(STEP_STATUS_PASS, locator.toString() + "Element Disappeared");
//				reportMessage(STEP_STATUS_DONE, "Process Wait from config : "+String.valueOf(WaitTime) +" and time taken to load element '"+locator.toString()+"' is "+totalTime+" ms");
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		public void selectValueFromDropDown(By byElement, String type, String value) {
//			try {
//				if(browserFactory.getDriver().findElement(byElement).isDisplayed()) {
//					Select select = new Select(browserFactory.getDriver().findElement(byElement));
//					type.toLowerCase();
//					switch(type) {
//					case "value":
//						select.selectByValue(value);
//						break;
//					case "index":
//						int val = Integer.parseInt(value);
//						select.selectByIndex(val);
//						break;
//					case "text":
//						select.selectByVisibleText(value);
//						break;
//					}
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		public void checkboxAction(By locator, String action)
//		{
//			WebElement element = browserFactory.getDriver().findElement(locator);
//			boolean state = element.isSelected();
//			if((action.equalsIgnoreCase("checked") && !state) || (action.equalsIgnoreCase("unchecked") && state))
//			{
//				click(locator);
//			}
//		}
//
//
//		public int getElementsCount(By element)
//		{	
//			int size = browserFactory.getDriver().findElements(element).size();
//			return size;
//		}
//
//
//
//
//		//This is to read the locators
//		public Target element(String element_Name) {	
//
//			String byLocator=ExcelUtil.locatorsHashMap.get(Thread.currentThread().getName()+"."+element_Name).toLowerCase().replace(" ", "");
//			String valueLocator= ExcelUtil.elementHashMap.get(Thread.currentThread().getName()+"."+element_Name);
//
//			WebDriverWait switchWait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(Long.parseLong(Index.prop.getProperty("PageLoadWait").trim())));
//			try {
//				switch (byLocator) {
//				case "classname":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.className(valueLocator)));
//					tarOutValue = new Target(By.className(valueLocator), element_Name);
//					break;
//				case "cssselector":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(valueLocator)));
//					tarOutValue = new Target(By.cssSelector(valueLocator), element_Name);
//					break;
//				case "id":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.id(valueLocator)));
//					tarOutValue = new Target(By.id(valueLocator), element_Name);
//					break;
//				case "linktext":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(valueLocator)));
//					tarOutValue = new Target(By.linkText(valueLocator), element_Name);
//					break;
//				case "name":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.name(valueLocator)));
//					tarOutValue = new Target(By.name(valueLocator), element_Name);
//					break;
//				case "partiallinktext":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(valueLocator)));
//					tarOutValue = new Target(By.partialLinkText(valueLocator), element_Name);
//					break;
//				case "tagname":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(valueLocator)));
//					tarOutValue = new Target(By.tagName(valueLocator), element_Name);
//					break;
//				case "xpath":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueLocator)));
//					tarOutValue = new Target(By.xpath(valueLocator), element_Name);
//					break;
//				default:
//					System.out.println("Element locator came to default");
//					break;
//				}	
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				Assert.fail("Unable to locate the element");
//			}
//			return tarOutValue;
//		}
//		//***************Added by zuci to find the dynamic element*********//
//		public Target dynamicElement(String element_Name, String dynamic_variable) {	
//
//			String byLocator=ExcelUtil.locatorsHashMap.get(Thread.currentThread().getName()+"."+element_Name).toLowerCase().replace(" ", "");
//			String valueLocator_in= ExcelUtil.elementHashMap.get(Thread.currentThread().getName()+"."+element_Name);
//			String split[]=valueLocator_in.split("\\+");
//			String valueLocator=split[0]+dynamic_variable+split[2];
//			WebDriverWait switchWait = new WebDriverWait(browserFactory.getDriver(),Duration.ofSeconds(Long.parseLong(Index.prop.getProperty("PageLoadWait").trim())));
//			try {
//				switch (byLocator) {
//				case "classname":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.className(valueLocator)));
//					tarOutValue = new Target(By.className(valueLocator), element_Name);
//					break;
//				case "cssselector":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(valueLocator)));
//					tarOutValue = new Target(By.cssSelector(valueLocator), element_Name);
//					break;
//				case "id":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.id(valueLocator)));
//					tarOutValue = new Target(By.id(valueLocator), element_Name);
//					break;
//				case "linktext":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(valueLocator)));
//					tarOutValue = new Target(By.linkText(valueLocator), element_Name);
//					break;
//				case "name":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.name(valueLocator)));
//					tarOutValue = new Target(By.name(valueLocator), element_Name);
//					break;
//				case "partiallinktext":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(valueLocator)));
//					tarOutValue = new Target(By.partialLinkText(valueLocator), element_Name);
//					break;
//				case "tagname":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(valueLocator)));
//					tarOutValue = new Target(By.tagName(valueLocator), element_Name);
//					break;
//				case "xpath":
//					switchWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueLocator)));
//					tarOutValue = new Target(By.xpath(valueLocator), element_Name);
//					break;
//				default:
//					System.out.println("Element locator came to default");
//					break;
//				}	
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				Assert.fail("Unable to locate the element");
//			}
//			return tarOutValue;
//		}
//
//		///************Added code end************//
//
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : click <br>
//		 * Description : Used to click the element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : elementLocation,String,boolean<br>
//		 * Return Value : <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : called by click overriden method<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void click(By elementLocation, String locatorInfo, boolean booleanAssert) {
//			boolean hasElementPresent;
//			boolean hasElementClickable;
//			// boolean hasElementNext;
//			String STEP_STATUS;
//			if (booleanAssert) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//				Index.testres = "Failed";
//			}
//			try {
//				hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 3);
//				hasElementClickable = waitForElementClickable(elementLocation);
//
//				if (hasElementPresent && hasElementClickable) {
//					// if (hasElementPresent) {
//					List<WebElement> elements = this.browserFactory.getDriver().findElements(elementLocation);
//					int elementsSize = elements.size();
//					if (elementsSize > 1) {
//						reportMessage(STEP_STATUS_WARN,
//								"Multiple Elements Found '" + locatorInfo.toString() + "', clicked on first element");
//					}
//					// scrollToElement(elements.get(0));
//
//					if (hasElementPresent && elementDisplayedOnPage(elementLocation)) {
//						if (waitForLoading(browserFactory.getDriver(), elementLocation, 30)) {
//							elements.get(0).click();
//						}
//					}
//					reportMessage(STEP_STATUS_PASS, "Click on Button '" + locatorInfo.toString() + "'");
//				} else {
//
//					reportMessage(STEP_STATUS, "Element Not Found '" + locatorInfo.toString() + "'");
//					Index.testres = "Failed";
//					Log.info("Slow Page Load therefore no element found");
//				}
//			} catch (org.openqa.selenium.TimeoutException e) {
//				reportMessage(STEP_STATUS, "Timeout Exception Presence of Element. " + locatorInfo.toString());
//				Index.testres = "Failed";
//				Log.info("Slow Page Load");
//			}
//
//		}
//
//		public void clickePAelement1(By elementLocation, String locatorInfo, boolean booleanAssert) {
//			boolean hasElementPresent;
//			// boolean hasElementClickable;
//			// boolean hasElementNext;
//			String STEP_STATUS;
//			if (booleanAssert) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			try {
//
//				hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 10);
//				// hasElementClickable = waitForElementClickable(elementLocation);
//
//				if (hasElementPresent) {
//					List<WebElement> elements = this.browserFactory.getDriver().findElements(elementLocation);
//					int elementsSize = elements.size();
//					if (elementsSize > 1) {
//						reportMessage(STEP_STATUS_WARN,
//								"Multiple Elements Found '" + locatorInfo.toString() + "', clicked on first element");
//					}
//					// scrollToElement(elements.get(0));
//
//					if (hasElementPresent && elementDisplayedOnPage(elementLocation)) {
//						if (waitForLoading(browserFactory.getDriver(), elementLocation, 10)) {
//							// elements.get(0).clear();
//							elements.get(0).click();
//						}
//					}
//					reportMessage(STEP_STATUS_PASS, "Click on Button '" + locatorInfo.toString() + "'");
//				} else {
//
//					reportMessage(STEP_STATUS, "Element Not Found '" + locatorInfo.toString() + "'");
//
//				}
//			} catch (org.openqa.selenium.TimeoutException e) {
//				reportMessage(STEP_STATUS, "Timeout Exception Presence of Element. " + locatorInfo.toString());
//				Log.info("Slow Page Load");
//			}
//
//		}
//
//		public void clickePAelement(Target elementTarget) {
//			click1(elementTarget.locator, elementTarget.locatorInfo, false);
//		}
//
//		public void clickePAelement(Target elementTarget, boolean hasAssert) {
//			click1(elementTarget.locator, elementTarget.locatorInfo, hasAssert);
//		}
//
//		public void clickePAelement(By locator) {
//			click1(locator, "", false);
//		}
//
//		public void clickePAelement(By locator, boolean hasAssert) {
//			click1(locator, "", hasAssert);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : click1 <br>
//		 * Description : Used to click the element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : elementLocation,String,boolean<br>
//		 * Return Value : <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : called by click overriden method<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void click1_old(By elementLocation, String targetInfo, boolean booleanAssert) {
//			boolean hasElementPresent;
//			boolean hasElementClickable;
//			boolean blnClickButton = false;
//			// boolean hasElementNext;
//			String STEP_STATUS;
//			if (booleanAssert) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			WebElement element;
//			try {
//				hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 30);
//				hasElementClickable = waitForElementClickable(elementLocation);
//				if (hasElementPresent && hasElementClickable) {
//					element = browserFactory.getDriver().findElement(elementLocation);
//					if (element.isEnabled()) {
//						//Thread.sleep(5); //Commanded by Zuci QA
//						element.click();
//						blnClickButton = true;
//						// Thread.sleep(5);
//						/*
//						 * if(elementdisplayedonpage(elementLocation)) { element.click(); blnClickButton
//						 * = true; }else {
//						 */
//						reportMessage(STEP_STATUS_PASS, "CLICK_BUTTON : clicked '" + targetInfo + "' button successfully.");// }
//						//browserFactory.getDriver().findElement(elementLocation).click();
//					}
//
//					if (!blnClickButton) {
//						String status = booleanAssert ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//						reportMessage(status,
//								"CLICK_BUTTON : click '" + targetInfo + "' button failed. Button not enabled");
//						Log.info("Button is not enabled for click");
//
//					} 
//					//				else {
//					//					reportMessage(STEP_STATUS_PASS, "CLICK_BUTTON : clicked '" + targetInfo + "' button successfully.");
//					//				}
//
//				} else {
//					String status = booleanAssert ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//					reportMessage(status, "CLICK_BUTTON : click button '" + targetInfo + "' failed. Element not present");
//					Log.info("Slow Page Load therefore no element found");
//				}
//			} catch (Exception e) {
//				Log.info(e.getMessage());
//				String status = booleanAssert ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "CLICK_BUTTON : click button '" + targetInfo + "' failed. Exception found");
//				Log.info("Slow Page Load");
//			}
//		}
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : clickImage <br>
//		 * Description : Used to click the Image element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : elementLocation<br>
//		 * Return Value : <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : clickImage(By saveImage=By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void clickImage(Target target) {
//			boolean hasImageElementPresent;
//			String STEP_STATUS;
//			boolean booleanAssert = false;
//			if (booleanAssert) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//			}
//			try {
//				hasImageElementPresent = waitForElementPresent(target);
//
//				if (hasImageElementPresent) {
//					List<WebElement> elements = this.browserFactory.getDriver().findElements(target.locator);
//					int elementsSize = elements.size();
//					if (elementsSize > 1) {
//						reportMessage(STEP_STATUS_WARN,	"Multiple Elements Found '" + target.locatorInfo + "', clicked on first element");
//					}
//					// scrollToElement(elements.get(0));
//					((JavascriptExecutor) browserFactory.getDriver()).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
//							elements.get(0));
//					reportMessage(STEP_STATUS_PASS, "Click on Button '" + elements.get(0).toString() + "'");
//				} else {
//
//					reportMessage(STEP_STATUS, "Element Not Found '" + target.locatorInfo + "'");
//
//				}
//			} catch (org.openqa.selenium.TimeoutException e) {
//				reportMessage(STEP_STATUS, "Timeout Exception Presence of Element. " + target.locatorInfo);
//				Log.info("Slow Page Load");
//			}
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : click <br>
//		 * Description : Used to click the element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : Target<br>
//		 * Return Value : <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : click(Target NextButton)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void click(Target elementTarget) {
//			click1(elementTarget.locator, elementTarget.locatorInfo, false);
//			//	btn_click(elementTarget);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : click <br>
//		 * Description : Used to click the element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : Target,boolean<br>
//		 * Return Value : <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : click(Target NextButton,true)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void click(Target elementTarget, boolean hasAssert) {
//			click1(elementTarget.locator, elementTarget.locatorInfo, hasAssert);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : click <br>
//		 * Description : Used to click the element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value : <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : click(By)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void click(By locator) {
//			click1(locator, "", false);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : click <br>
//		 * Description : Used to click the element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,boolean<br>
//		 * Return Value : <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : click(By,true)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void click(By locator, boolean hasAssert) {
//			click1(locator, "", hasAssert);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : waitForLoading <br>
//		 * Description : Used to wait for loading of an element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : Webdriver,By,long<br>
//		 * Return Value :boolean <br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : waitForLoading(driver,By.id(),30)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public static boolean waitForLoading(WebDriver driver, By locator, long timeout) {
//			boolean isVisible = true;
//			try {
//				new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(timeout)).ignoring(StaleElementReferenceException.class)
//				.until(ExpectedConditions.visibilityOfElementLocated(locator));
//			} catch (org.openqa.selenium.TimeoutException e) {
//				isVisible = false;
//			} // catch() {}
//			return isVisible;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : waittillNextElement <br>
//		 * Description : Used to wait for loading of next element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : Webdriver,By,long<br>
//		 * Return Value : boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : waitForLoading(driver,By.id(),30)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public static boolean waitTillNextElement(WebDriver driver, By locator, long timeout) {
//			new WebDriverWait(browserFactory.getDriver(),Duration.ofSeconds(timeout)).ignoring(StaleElementReferenceException.class)
//			.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//			return true;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : waitForDropdownElementPresent <br>
//		 * Description : Used to wait for loading of dropdown element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value : boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : waitForDropdownElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		private boolean waitForDropdownElementPresent(By locator) {
//			boolean hasElementExist = false;
//
//			if (browserFactory.getDriver().findElements(locator).size() > 0) {
//				hasElementExist = true;
//			} else {
//				try {
//					long waitTime = getWaitTime();
//					//	WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), 10);
//					WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTime));
//					List<WebElement> we = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
//					if (we != null) {
//						hasElementExist = true;
//					}
//				} catch (org.openqa.selenium.TimeoutException te) {
//					reportMessage(STEP_STATUS_WARN,
//							"Wait for Element Present, Page Not Loaded in given time seconds " + WAIT_ELEMENT_MAX);
//					Log.info("Slow Page Load");
//				}
//			}
//			return hasElementExist;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : waitForElementPresent <br>
//		 * Description : Used to wait for element presence<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value : boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : waitForElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		private boolean waitForElementPresent(Target target) {
//			boolean hasElementExist = false;
//			if (browserFactory.getDriver().findElements(target.locator).size() > 0) {
//				hasElementExist = true;
//			} else {
//				try {
//					long waitTime = getWaitTime();
//					//WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), 10);
//					WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTime));
//					WebElement we = wait.ignoring(StaleElementReferenceException.class)
//							.until(ExpectedConditions.presenceOfElementLocated(target.locator));
//					if (we != null) {
//						hasElementExist = true;
//					}
//				} catch (org.openqa.selenium.TimeoutException te) {
//					reportMessage(STEP_STATUS_WARN, "Wait for Element " + target.locatorInfo
//							+ " Present failed, Page Not Loaded in given time seconds " + WAIT_ELEMENT_MIN);
//					Log.info("Slow Page Load");
//				}
//			}
//			return hasElementExist;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : waitForElementVisible <br>
//		 * Description : Used to wait for element presence<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value : boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : waitForElementVisible(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		private boolean waitForElementVisible(Target target) {
//			boolean hasElementExist = false;
//			try {
//				if (browserFactory.getDriver().findElement(target.locator).isDisplayed()) {
//					hasElementExist = true;
//				} else {
//					long waitTime = getWaitTime();
//					WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(5));
//					//	WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), waitTime);
//					WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(target.locator));
//					if (we != null) {
//						hasElementExist = true;
//					}
//				}
//			} catch (org.openqa.selenium.TimeoutException te) {
//				reportMessage(STEP_STATUS_WARN, "Wait for Element " + target.locatorInfo
//						+ " Present failed, Page Not Loaded in given time seconds " + WAIT_ELEMENT_MIN);
//				Log.info("Slow Page Load");
//			} catch (org.openqa.selenium.NoSuchElementException te) {
//				reportMessage(STEP_STATUS_WARN, "Wait for Element " + target.locatorInfo
//						+ " Present failed, Page Not Loaded in given time seconds " + WAIT_ELEMENT_MIN);
//				Log.info("Slow Page Load");
//			}
//			return hasElementExist;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : waitForElementClickable <br>
//		 * Description : Used to wait for element to be clickable<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value : boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : \(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		private boolean waitForElementClickable(By locator) {
//			boolean hasElementClickable = false;
//
//			if (browserFactory.getDriver().findElements(locator).size() > 0) {
//				hasElementClickable = true;
//			} else {
//				try {
//					long waitTime = getWaitTime();
//					//	WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), 30);
//					WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTime));
//					WebElement we = wait.until(ExpectedConditions.elementToBeClickable(locator));
//					if (we != null) {
//						hasElementClickable = true;
//					}
//				} catch (org.openqa.selenium.TimeoutException te) {
//					reportMessage(STEP_STATUS_WARN,
//							"Wait for Element Clickable, Element was not Clickable by given time seconds "
//									+ WAIT_ELEMENT_MAX);
//					Log.info("Slow Page Load");
//				}
//			}
//			return hasElementClickable;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : waitForPageToLoad <br>
//		 * Description : Used to wait for page to get load<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,int<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : waitForPageToLoad(By.id(),30)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public void waitForPageToLoad(By locator, int waitTimeSeconds) {
//			try {
//				if (browserFactory.getDriver().findElements(locator).size() > 0) {
//					reportMessage(STEP_STATUS_DONE, "Page load wait, Page Loaded Succesfully");
//				} else {
//					WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTimeSeconds));
//					WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//					if (we != null) {
//						reportMessage(STEP_STATUS_DONE, "Page load wait, Page Loaded Succesfully");
//					} else {
//						reportMessage(STEP_STATUS_FAIL,
//								"Page load wait, Page Not Loaded in given time seconds " + waitTimeSeconds);
//						Index.testres = "Failed";
//						Log.info("Slow Page Load");
//					}
//				}
//			} catch (WebDriverException wde) {
//				reportMessage(STEP_STATUS_WARN, "Page load wait, Page Not Loaded in given time seconds " + waitTimeSeconds);
//				Log.info("Browser got exited before it gets connected");
//			}
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : verifyElementVisible <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : verifyElementVisible(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean verifyElementVisible(Target target, boolean assertType) {
//			// boolean waitforElementLoading=waitForLoading(driver, target.locator, 30);
//			String STEP_STATUS;
//			if (assertType) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			boolean hasElementVisible = waitForElementVisible(target);
//			reportMessage(STEP_STATUS_FIND, "VISIBILITY_OF_ELEMENT '" + target.locatorInfo);
//			if (hasElementVisible) {
//
//				reportMessage(STEP_STATUS_PASS,
//						"VISIBILITY_OF_ELEMENT Elements Present'" + target.locatorInfo + "', Element Present");
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "VISIBILITY_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//				Log.info("Element not in visible mode");
//			}
//			return hasElementVisible;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : typeText <br>
//		 * Description : Used to type text on textbox<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : typeText(By.id(),"text")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public void typeText2(By elementLocation, String text, String targetInfo, boolean booleanAssert) {
//			boolean hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 10);
//
//			if (!text.isEmpty()) {
//				reportMessage(STEP_STATUS_FIND,
//						"TYPE_TEXT Element '" + elementLocation.toString() + "' Data '" + text + "'");
//				try {
//					if (hasElementPresent) {
//
//						List<WebElement> elements = this.browserFactory.getDriver().findElements(elementLocation);
//						int elementsSize = elements.size();
//						if (elementsSize > 1) {
//							reportMessage(STEP_STATUS_WARN, "Multiple Elements Found '" + targetInfo + "', on page");
//						} else {
//							reportMessage(STEP_STATUS_PASS, "Elements Found '" + targetInfo + "', on page");
//						}
//						// scrollToElement(elements.get(0));
//						// Thread.sleep(5);
//						if (waitForLoading(browserFactory.getDriver(), elementLocation, 5)) {
//							browserFactory.getDriver().findElement(elementLocation).clear();
//							browserFactory.getDriver().findElement(elementLocation).click();
//
//						}
//
//						if (browserFactory.getDriver().findElement(elementLocation).getText().isEmpty()) {
//							Thread.sleep(5);
//							// browserFactory.getDriver().navigate().refresh();
//							browserFactory.getDriver().findElement(elementLocation).click();
//							if (waitForLoading(browserFactory.getDriver(), elementLocation, 5)) {
//								browserFactory.getDriver().findElement(elementLocation).sendKeys(text);
//							}
//
//							reportMessage(STEP_STATUS_PASS, "Elements Found '" + targetInfo + "', typed on element");
//							//
//							reportMessage(STEP_STATUS_PASS, browserFactory.getDriver().findElement(elementLocation).getText());
//						}
//
//						// elements.get(0).sendKeys(text);
//						// Assert.assertEquals(elements.get(0).getText(), text);
//					} else {
//						reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + targetInfo + "'");
//						Index.testres = "Failed";
//					}
//				} catch (org.openqa.selenium.TimeoutException e) {
//					reportMessage(STEP_STATUS_FAIL, "Wait for Element Elapsed, Element '" + targetInfo
//							+ "'search failed by given time seconds with timeout exception " + WAIT_ELEMENT_MAX);
//					Log.info("Slow Page Load");
//				} catch (InterruptedException e) {
//
//					reportMessage(STEP_STATUS_FAIL, "Wait for Element Elapsed, Element '" + targetInfo
//							+ "'search failed by given time seconds with timeout exception " + WAIT_ELEMENT_MAX);
//				} catch (org.openqa.selenium.StaleElementReferenceException e) {
//
//					browserFactory.getDriver().findElement(elementLocation).click();
//					browserFactory.getDriver().findElement(elementLocation).sendKeys(text);
//					reportMessage(STEP_STATUS_PASS, "Elements Found '" + targetInfo + "', typed on element");
//				} catch (org.openqa.selenium.InvalidElementStateException e) {
//					long waitTime = getWaitTime();
//					//				new WebDriverWait(browserFactory.getDriver(), 30).until(ExpectedConditions.elementToBeClickable(elementLocation))
//					//				.sendKeys(text);
//					new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTime)).until(ExpectedConditions.elementToBeClickable(elementLocation))
//					.sendKeys(text);
//				}
//			} else {
//				reportMessage(STEP_STATUS_FAIL, targetInfo + " Excel Text is empty");
//			}
//		}
//
//		public void typeText(Target elementTarget, String text) {
//			typeText2(elementTarget.locator, text, elementTarget.locatorInfo, false);
//		}
//
//		public void typeText(Target elementTarget, String text, boolean hasAssert) {
//			typeText2(elementTarget.locator, text, elementTarget.locatorInfo, hasAssert);
//		}
//
//		public void typeText(By locator, String text) {
//			typeText2(locator, text, "", false);
//		}
//
//		public void typeText(By locator, String text, boolean hasAssert) {
//			typeText2(locator, text, "", hasAssert);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : selectByVisibleText <br>
//		 * Description : Used to select text from drop down list<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : selectByVisibleText(By.id(),"text")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void selectByVisibleText2(By elementLocation, String text, String targetInfo, boolean booleanAssert) {
//			// boolean hasElementPresent = waitForLoading(driver, elementLocation, 60);
//			boolean hasDropdownElementPresent = waitForDropdownElementPresent(elementLocation);
//			reportMessage(STEP_STATUS_FIND,
//					"SELECT_BY_VISIBLE_TEXT Element '" + elementLocation.toString() + "' Data '" + text + "'");
//			try {
//				// if (hasElementPresent && hasDropdownElementPresent) {
//				if (hasDropdownElementPresent) {
//
//					List<WebElement> elements = this.browserFactory.getDriver().findElements(elementLocation);
//					int elementsSize = elements.size();
//					// scrollToElement(elements.get(0));
//					// Select dropdown = new Select(elements.get(0));
//					if (waitForLoading(browserFactory.getDriver(), elementLocation, 5)) {
//						// dropdown.selectByVisibleText(text);
//						Thread.sleep(5);
//						boolean hasOptionSelected = selectListItemExists(elementLocation, text);
//						if (!hasOptionSelected) {
//							reportMessage(STEP_STATUS_FAIL,
//									"Element Select Option Not Found '" + elementLocation.toString() + "'");
//							Index.testres = "Failed";
//						}
//
//					}
//					if (elementsSize > 1) {
//						reportMessage(STEP_STATUS_WARN, "Multiple Elements Found '" + elementLocation.toString()
//						+ "', Selected by Visible Text first element");
//					} else {
//						reportMessage(STEP_STATUS_PASS,
//								"Elements Found '" + elementLocation.toString() + "', Selected List By Text");
//					}
//				} else {
//					reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + elementLocation.toString() + "'");
//					Index.testres = "Failed";
//				}
//			} catch (org.openqa.selenium.TimeoutException e) {
//				reportMessage(STEP_STATUS_FAIL, "Wait for Element Elapsed, Element '" + targetInfo
//						+ "'search failed by given time seconds with timeout exception " + WAIT_ELEMENT_MAX);
//			} catch (InterruptedException e) {
//
//				reportMessage(STEP_STATUS_FAIL, "Wait for Element Elapsed, Element '" + targetInfo
//						+ "'search failed by given time seconds with timeout exception " + WAIT_ELEMENT_MAX);
//			}
//		}
//
//		/*
//		 * public boolean selectOptionfromDropDown(Target elementTarget, String text) {
//		 * elementSelect.f return false; }
//		 */
//
//		public void selectByVisibleText(Target elementTarget, String text) {
//			selectByVisibleText2(elementTarget.locator, text, elementTarget.locatorInfo, false);
//		}
//
//		public void selectByVisibleText(Target elementTarget, String text, boolean hasAssert) {
//			selectByVisibleText2(elementTarget.locator, text, elementTarget.locatorInfo, hasAssert);
//		}
//
//		public void selectByVisibleText(By locator, String text) {
//			selectByVisibleText2(locator, text, "", false);
//		}
//
//		public void selectByVisibleText(By locator, String text, boolean hasAssert) {
//			selectByVisibleText2(locator, text, "", hasAssert);
//		}
//
//		// ******************
//
//		public boolean selectListItemExists(By elementTargetBy, String text) {
//			boolean blnListItemExists = false;
//			List<WebElement> selectelements = getOptions(elementTargetBy);
//			for (WebElement selectelement : selectelements) {
//				if (selectelement.getText().equals(text)) {
//					selectelement.click();
//					blnListItemExists = true;
//					break;
//				}
//			}
//			return blnListItemExists;
//		}
//
//		public List<WebElement> getOptions(By objTest) {
//			WebElement element = browserFactory.getDriver().findElement(objTest);
//			return element.findElements(By.tagName("option"));
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : SelectByValue <br>
//		 * Description : Used to select value from drop down list<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : SelectByValue(By.id(),"value")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void selectByValue(By elementLocation, String value) {
//			boolean hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 60);
//			boolean hasDropdownElementPresent = waitForDropdownElementPresent(elementLocation);
//			reportMessage(STEP_STATUS_FIND,
//					"SELECT_BY_VALUE Element '" + elementLocation.toString() + "' Data '" + value + "'");
//			if (hasElementPresent && hasDropdownElementPresent) {
//
//				List<WebElement> elements = this.browserFactory.getDriver().findElements(elementLocation);
//				// scrollToElement(elements.get(0));
//				Select option = new Select(elements.get(0));
//				option.selectByValue(value);
//				int elementsSize = elements.size();
//
//				if (elementsSize > 1) {
//					reportMessage(STEP_STATUS_WARN, "Multiple Elements Found '" + elementLocation.toString()
//					+ "', Selected by Value first element");
//				} else {
//					reportMessage(STEP_STATUS_PASS,
//							"Elements Found '" + elementLocation.toString() + "', Selected List By Value");
//				}
//
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + elementLocation.toString() + "'");
//				Index.testres = "Failed";
//			}
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : selectByIndex <br>
//		 * Description : Used to select Index from drop down list<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,int<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : selectByIndex(By.id(),2)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void selectByIndex2(By elementLocation, int index, String targetInfo, boolean booleanAssert) {
//			boolean hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 60);
//			boolean hasDropdownElementPresent = waitForDropdownElementPresent(elementLocation);
//
//			reportMessage(STEP_STATUS_PASS, hasElementPresent + "&&" + hasDropdownElementPresent);
//			reportMessage(STEP_STATUS_FIND,
//					"SELECT_BY_INDEX Element '" + elementLocation.toString() + "' Data '" + index + "'");
//			try {
//				if (hasElementPresent && hasDropdownElementPresent) {
//					// if (hasDropdownElementPresent) {
//					reportMessage(STEP_STATUS_PASS, "1");
//					// List<WebElement> elements = browserFactory.getDriver().findElements(elementLocation);
//					WebElement elements = browserFactory.getDriver().findElement(elementLocation);
//					// scrollToElement(elements.get(0));
//					// Select option = new Select(elements.get(0));
//					Select option = new Select(browserFactory.getDriver().findElement(elementLocation));
//					List<WebElement> dropDownOptions = option.getOptions();
//					option.selectByIndex(index);
//					reportMessage(STEP_STATUS_PASS, "4");
//					int elementsSize = dropDownOptions.size();
//					reportMessage(STEP_STATUS_PASS, "5");
//					if (elementsSize > 1) {
//						reportMessage(STEP_STATUS_WARN, "Multiple Elements Found '" + elementLocation.toString()
//						+ "', Selected by Index first element");
//					} else {
//						reportMessage(STEP_STATUS_PASS,
//								"Elements Found '" + elementLocation.toString() + "', Selected List By Index");
//					}
//
//				} else {
//					reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + elementLocation.toString() + "'");
//					Index.testres = "Failed";
//				}
//			} catch (org.openqa.selenium.TimeoutException e) {
//				reportMessage(STEP_STATUS_FAIL, "Wait for Element Elapsed, Element '" + targetInfo
//						+ "'search failed by given time seconds with timeout exception " + WAIT_ELEMENT_MAX);
//			}
//		}
//
//		public void selectByIndex(Target elementTarget, int index) {
//			selectByIndex2(elementTarget.locator, index, elementTarget.locatorInfo, false);
//		}
//
//		public void selectByIndex(Target elementTarget, int index, boolean hasAssert) {
//			selectByIndex2(elementTarget.locator, index, elementTarget.locatorInfo, hasAssert);
//		}
//
//		public void selectByIndex(By locator, int index) {
//			selectByIndex2(locator, index, "", false);
//		}
//
//		public void selectByIndex(By locator, int index, boolean hasAssert) {
//			selectByIndex2(locator, index, "", hasAssert);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : selectByVisibleText1 <br>
//		 * Description : Used to select text from drop down list<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : selectByVisibleText1(By.id(),"text")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void selectByVisibleText3(By elementLocation, String text, String targetInfo, boolean booleanAssert) {
//			boolean hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 60);
//			boolean hasDropdownElementPresent = waitForDropdownElementPresent(elementLocation);
//			reportMessage(STEP_STATUS_FIND,
//					"SELECT_BY_VISIBLE_TEXT Element '" + elementLocation.toString() + "' Data '" + text + "'");
//			try {
//				if (hasElementPresent && hasDropdownElementPresent) {
//
//					Select oSelect = new Select(browserFactory.getDriver().findElement(elementLocation));
//					List<WebElement> oSize = oSelect.getOptions();
//					int iListSize = oSize.size();
//
//					// Setting up the loop to print all the options
//					for (int i = 0; i < iListSize; i++) {
//						// Storing the value of the option
//						String sValue = oSelect.getOptions().get(i).getText();
//						// Printing the stored value
//						// reportMessage(STEP_STATUS_PASS, sValue);
//						// Putting a check on each option that if any of the option is
//						// equal to 'Africa" then select it
//						if (sValue.equals(text)) {
//							oSelect.selectByIndex(i);
//							break;
//						}
//
//					}
//
//					List<WebElement> elements = this.browserFactory.getDriver().findElements(elementLocation);
//					// scrollToElement(elements.get(0));
//					/*
//					 * Select option = new Select(elements.get(0)); option.selectByIndex(index);
//					 */
//					int elementsSize = elements.size();
//
//					if (elementsSize > 1) {
//						reportMessage(STEP_STATUS_WARN, "Multiple Elements Found '" + elementLocation.toString()
//						+ "', Selected by Index first element");
//					} else {
//						reportMessage(STEP_STATUS_PASS,
//								"Elements Found '" + elementLocation.toString() + "', Selected List By Index");
//					}
//
//				} else {
//					reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + elementLocation.toString() + "'");
//					Index.testres = "Failed";
//				}
//			} catch (org.openqa.selenium.TimeoutException e) {
//				reportMessage(STEP_STATUS_FAIL, "Wait for Element Elapsed, Element '" + targetInfo
//						+ "'search failed by given time seconds with timeout exception " + WAIT_ELEMENT_MAX);
//			}
//		}
//
//		public void selectByVisibleText1(Target elementTarget, String text) {
//			selectByVisibleText3(elementTarget.locator, text, elementTarget.locatorInfo, false);
//		}
//
//		public void selectByVisibleText1(Target elementTarget, String text, boolean hasAssert) {
//			selectByVisibleText3(elementTarget.locator, text, elementTarget.locatorInfo, hasAssert);
//		}
//
//		public void selectByVisibleText1(By locator, String text) {
//			selectByVisibleText3(locator, text, "", false);
//		}
//
//		public void selectByVisibleText1(By locator, String text, boolean hasAssert) {
//			selectByVisibleText3(locator, text, "", hasAssert);
//		}
//
//		public void selectByPartVisibleText(By elementLocation, String text) {
//			boolean hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 60);
//			boolean hasDropdownElementPresent = waitForDropdownElementPresent(elementLocation);
//			int IndexofElement = 0;
//			reportMessage(STEP_STATUS_FIND,
//					"SELECT_BY_VISIBLE_TEXT Element '" + elementLocation.toString() + "' Data '" + text + "'");
//			if (hasElementPresent && hasDropdownElementPresent) {
//
//				List<WebElement> elements = this.browserFactory.getDriver().findElements(elementLocation);
//				int elementsSize = elements.size();
//				// scrollToElement(elements.get(0));
//				for (int i = 0; i < elementsSize; i++) {
//					if (elements.get(i).getText().contains(text)) {
//						IndexofElement = i;
//					}
//				}
//				Select option = new Select(elements.get(0));
//				if (waitForLoading(browserFactory.getDriver(), elementLocation, 60)) {
//					option.selectByIndex(IndexofElement);
//
//				}
//				if (elementsSize > 1) {
//					reportMessage(STEP_STATUS_WARN, "Multiple Elements Found '" + elementLocation.toString()
//					+ "', Selected by Visible Text first element");
//				} else {
//					reportMessage(STEP_STATUS_PASS,
//							"Elements Found '" + elementLocation.toString() + "', Selected List By Text");
//				}
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + elementLocation.toString() + "'");
//				Index.testres = "Failed";
//			}
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : pahubAlertMessage <br>
//		 * Description : Used to get the message from pahub alert box<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call :pahubAlertMessage(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public boolean pahubAlertMessage(Target target, String alertMessage, boolean assertType) {
//			boolean waitforElementLoading = waitForLoading(browserFactory.getDriver(), target.locator, 60);
//			boolean hasElementPresent = waitForElementPresent(target);
//			String STEP_STATUS;
//			if (assertType) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target.locatorInfo);
//			if (hasElementPresent && waitforElementLoading) {
//				reportMessage(STEP_STATUS_DONE,
//						"PRESENCE_OF_ELEMENT Elements Present'" + target.locatorInfo + "', Element Present");
//				if (getTheText(target, true).contentEquals(alertMessage)) {
//					reportMessage(STEP_STATUS_DONE,
//							"PRESENCE_OF_ELEMENT Elements Present'" + target.locatorInfo + "', Element Present");
//				} else {
//					reportMessage(STEP_STATUS, "Alert Message not present'" + target.locatorInfo + "'");
//					Index.testres = "Failed";
//				}
//			} else {
//				reportMessage(STEP_STATUS, "PRESENCE_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/*
//		 * /** =========================================================================
//		 * <br> Function Name : pahubAlertOKButton <br> Description : Used to get the
//		 * message from pahub alert box<br> Author Name : Sariya Pathan <br> Arguments :
//		 * By<br> Return Value :boolean<br> Created Date : <br> Reviewed By : <br>
//		 * Reviewed Date : <br> Modified By : <br> Modified Date : <br> Used At : <br>
//		 * How to Call :pahubAlertMessage(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 *
//		 * 
//		 * public boolean pahubAlertOKButton(Target target,boolean assertType) { boolean
//		 * waitforElementLoading = waitForLoading(driver, target.locator, 60); boolean
//		 * hasElementPresent = waitForElementPresent(target); WebElement
//		 * AlertOKButton=browserFactory.getDriver().findElement(By.xpath(xpathExpression)) String
//		 * STEP_STATUS; if (assertType) { STEP_STATUS = STEP_STATUS_WARN; } else {
//		 * STEP_STATUS = STEP_STATUS_FAIL;
//		 * 
//		 * } reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" +
//		 * target.locatorInfo); if (hasElementPresent && waitforElementLoading) {
//		 * reportMessage(STEP_STATUS_DONE, "PRESENCE_OF_ELEMENT Elements Present'" +
//		 * target.locatorInfo + "', Element Present");
//		 * if(getTheText(target).contentEquals(alertMessage)) {
//		 * reportMessage(STEP_STATUS_DONE, "PRESENCE_OF_ELEMENT Elements Present'" +
//		 * target.locatorInfo + "', Element Present"); } else
//		 * {reportMessage(STEP_STATUS, "Alert Message not present'" + target.locatorInfo
//		 * + "'"); Index.testres = "Failed";} } else { reportMessage(STEP_STATUS,
//		 * "PRESENCE_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//		 * Index.testres = "Failed"; } return hasElementPresent; }
//		 */
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : locatorLoop <br>
//		 * Description : Used to loop through the locator<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call :locatorLoop(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public Target locatorLoop(String locator1, String locator2, int loopCounter) {
//
//			Target target1 = new Target(By.xpath(locator1), "Loop counter" + loopCounter);
//
//			Target ReturnTargetElement = null;
//			String ReturnElementLocator = locator1 + "[" + loopCounter + "]" + locator2;
//			ReturnTargetElement = new Target(By.xpath(ReturnElementLocator), "Target for looping counter" + loopCounter);
//			boolean waitforElementLoading = waitForLoading(browserFactory.getDriver(), target1.locator, 30);
//			boolean hasElementPresent = waitForElementPresent(target1);
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target1.locatorInfo);
//			if (hasElementPresent && waitforElementLoading) {
//				reportMessage(STEP_STATUS_DONE,
//						"PRESENCE_OF_ELEMENT Elements Present'" + target1.locatorInfo + "', Element Present");
//				// ReturnTargetElement=new Target(By.xpath(ReturnElementLocator),"Target for
//				// looping");
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "PRESENCE_OF_ELEMENT Element Not Present '" + target1.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return ReturnTargetElement;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : countOflocatorElements <br>
//		 * Description : count of the locator elements<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call :locatorElements(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public int countOfLocatorElements(Target target, boolean assertType) {
//			String STEP_STATUS;
//			if (assertType) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			int SizeOfElements = 0;
//			boolean waitforElementLoading = waitForLoading(browserFactory.getDriver(), target.locator, 60);
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target.locatorInfo);
//			if (hasElementPresent && waitforElementLoading) {
//				reportMessage(STEP_STATUS_DONE,
//						"PRESENCE_OF_ELEMENT Elements Present'" + target.locatorInfo + "', Element Present");
//
//				List<WebElement> NumberOflocatorElements = browserFactory.getDriver().findElements(target.locator);
//				SizeOfElements = NumberOflocatorElements.size();
//				reportMessage(STEP_STATUS_DONE,
//						"COUNT_OF_ELEMENT Elements Present'" + target.locatorInfo + "', is " + SizeOfElements);
//			} else {
//				reportMessage(STEP_STATUS, "PRESENCE_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//
//			return SizeOfElements;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : verifyElementPresent <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : verifyElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean verifyElementPresent(Target target) {
//			boolean waitforElementLoading = waitForLoading(browserFactory.getDriver(), target.locator, 10);
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target.locatorInfo);
//			if (hasElementPresent && waitforElementLoading) {
//				reportMessage(STEP_STATUS_DONE,
//						"PRESENCE_OF_ELEMENT Elements Present'" + target.locatorInfo + "', Element Present");
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "PRESENCE_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : verifyElementPresent <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : verifyElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean verifyElementPresent(Target target, boolean assertType) {
//			// boolean waitforElementLoading=waitForLoading(driver, target.locator, 30);
//			String STEP_STATUS;
//			if (assertType) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target.locatorInfo);
//			if (hasElementPresent) {
//
//				reportMessage(STEP_STATUS_PASS,
//						"PRESENCE_OF_ELEMENT Elements Present'" + target.locatorInfo + "', Element Present");
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "PRESENCE_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : verifyDropDownElementPresent <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : verifyDropDownElementPresent(Target target, boolean
//		 * assertType)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean verifyDropDownElementPresent(Target target, List DropDownList, boolean assertType) {
//			// boolean waitforElementLoading=waitForLoading(driver, target.locator, 30);
//			boolean hasElementPresent = waitForElementPresent(target);
//			if (hasElementPresent) {
//				Select optionSelect = new Select(browserFactory.getDriver().findElement(target.locator));
//
//				List<WebElement> allOptions = optionSelect.getOptions();
//				List<String> allOptionsValue = new ArrayList();
//				reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target.locatorInfo);
//				if (allOptions.size() > 0) {
//					for (int i = 0; i < allOptions.size(); i++) {
//						allOptionsValue.add(allOptions.get(i).getText());
//					}
//					if (allOptionsValue.containsAll(DropDownList)) {
//						reportMessage(STEP_STATUS_PASS, "PRESENCE_OF_ELEMENT Elements Present, Element Present");
//					}
//					/*
//					 * for (int i = 0; i < allOptions.size(); i++) {
//					 * 
//					 * if (allOptions.get(i).getText().equals(DropDownList.get(i))) {
//					 * reportMessage(STEP_STATUS_PASS, "PRESENCE_OF_ELEMENT Elements Present'" +
//					 * DropDownList.get(i) + "', Element Present"); }
//					 */
//					else {
//						reportMessage(STEP_STATUS_WARN, "PRESENCE_OF_ELEMENT Element Not Present ");
//					}
//
//				} else {
//					reportMessage(STEP_STATUS_WARN,
//							"PRESENCE_OF_ELEMENT Elements in dropdown Not Present '" + target.locatorInfo + "'");
//				}
//			} else
//
//			{
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "PRESENCE_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : verifyCheckBoxStackElementsPresence <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : verifyElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean verifyCheckBoxStackElementsPresence(Target target, List<String> CheckboxElements,
//				boolean assertType) {
//			// boolean waitforElementLoading=waitForLoading(driver, target.locator, 30);
//
//			boolean hasElementPresent = waitForElementPresent(target);
//
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_STACK of checkbox elements '" + target.locatorInfo);
//
//			if (hasElementPresent) {
//
//				List<WebElement> checkBoxesxpath = browserFactory.getDriver().findElements(target.locator);
//
//				WebElement stack = browserFactory.getDriver().findElement(target.locator);
//
//				for (int i = 0; i < CheckboxElements.size(); i++) {
//					// reportMessage(STEP_STATUS_PASS, stack.findElement(By.xpath("//td[contains(text(),'"+CheckboxElements.get(i)+"')]//input")));
//					int hasCheckBox = stack
//							.findElements(By.xpath("//td[contains(text(),'" + CheckboxElements.get(i) + "')]//input"))
//							.size();
//
//					if (hasCheckBox == 0) {
//						String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//						reportMessage(status,
//								"PRESENCE_OF_ELEMENT Checkbox Element Not Present '" + CheckboxElements.get(i) + "'");
//					} else {
//						reportMessage(STEP_STATUS_PASS, "PRESENCE_OF_ELEMENT Checkbox Element Present'"
//								+ CheckboxElements.get(i) + "', Element Present");
//					}
//
//				}
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "PRESENCE_OF_STACK of checkbox elements Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : verifyTableRowHeaderElementsPresence <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : verifyElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean verifyTableRowHeaderElementsPresence(Target target, List<String> RowHeaderElements,
//				boolean assertType) {
//			// boolean waitforElementLoading=waitForLoading(driver, target.locator, 30);
//
//			boolean hasElementPresent = waitForElementPresent(target);
//
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_TABLE with row header elements '" + target.locatorInfo);
//
//			if (hasElementPresent) {
//
//				List<WebElement> rowHeaderxpath = browserFactory.getDriver().findElements(target.locator);
//
//				WebElement rowHead = browserFactory.getDriver().findElement(target.locator);
//
//				for (int i = 0; i < RowHeaderElements.size(); i++) {
//					// reportMessage(STEP_STATUS_PASS, rowHead.findElement(By.xpath("//td[contains(text(),'"+RowHeaderElements.get(i)+"')]//input")));
//					int hasCheckBox = rowHead
//							.findElements(By.xpath("//th[contains(text(),'" + RowHeaderElements.get(i) + "')]")).size();
//
//					if (hasCheckBox == 0) {
//						String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//						reportMessage(status,
//								"PRESENCE_OF_TABLE Row Header element Not Present '" + RowHeaderElements.get(i) + "'");
//					} else {
//						reportMessage(STEP_STATUS_PASS, "PRESENCE_OF_TABLE Row Header element Present'"
//								+ RowHeaderElements.get(i) + "', Element Present");
//					}
//
//				}
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "PRESENCE_OF_TABLE Row Header element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : verifyTableColumnHeaderElementsPresence <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * \ Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : verifyElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean verifyTableColumnHeaderElementsPresence(Target target, List<String> ColHeaderElements,
//				boolean assertType) {
//			// boolean waitforElementLoading=waitForLoading(driver, target.locator, 30);
//
//			boolean hasElementPresent = waitForElementPresent(target);
//
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_TABLE with column header elements '" + target.locatorInfo);
//
//			if (hasElementPresent) {
//
//				List<WebElement> rowHeaderxpath = browserFactory.getDriver().findElements(target.locator);
//
//				WebElement ColHead = browserFactory.getDriver().findElement(target.locator);
//
//				for (int i = 0; i < ColHeaderElements.size(); i++) {
//					// reportMessage(STEP_STATUS_PASS, rowHead.findElement(By.xpath("//td[contains(text(),'"+RowHeaderElements.get(i)+"')]//input")));
//					int hasCheckBox = ColHead.findElements(By.xpath("//td[contains(.,'" + ColHeaderElements.get(i) + "')]"))
//							.size();
//
//					if (hasCheckBox == 0) {
//						String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//						reportMessage(status,
//								"PRESENCE_OF_TABLE column Header element Not Present '" + ColHeaderElements.get(i) + "'");
//					} else {
//						reportMessage(STEP_STATUS_PASS, "PRESENCE_OF_TABLE column Header element Present'"
//								+ ColHeaderElements.get(i) + "', Element Present");
//					}
//
//				}
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "PRESENCE_OF_TABLE column Header element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : verifylinkElementsPresence <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : verifyElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean verifyLinkElementsPresence(Target target, List<String> LinkElements, boolean assertType) {
//			// boolean waitforElementLoading=waitForLoading(driver, target.locator, 30);
//
//			boolean hasElementPresent = waitForElementPresent(target);
//
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_TABLE with column header elements '" + target.locatorInfo);
//
//			if (hasElementPresent) {
//
//				List<WebElement> rowHeaderxpath = browserFactory.getDriver().findElements(target.locator);
//
//				WebElement LinkElement = browserFactory.getDriver().findElement(target.locator);
//
//				for (int i = 0; i < LinkElements.size(); i++) {
//					// reportMessage(STEP_STATUS_PASS, rowHead.findElement(By.xpath("//td[contains(text(),'"+RowHeaderElements.get(i)+"')]//input")));
//					int hasLinkElement = LinkElement
//							.findElements(By.xpath("//a[contains(text(),'" + LinkElements.get(i) + "')]")).size();
//
//					if (hasLinkElement == 0) {
//						String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//						reportMessage(status, "PRESENCE_OF_LINK element Not Present '" + LinkElements.get(i) + "'");
//					} else {
//						reportMessage(STEP_STATUS_PASS,
//								"PRESENCE_OF_LINK element Present'" + LinkElements.get(i) + "', Element Present");
//					}
//
//				}
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "PRESENCE_OF_LINK element Not Present '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : hasElementPresent <br>
//		 * Description : Used to verify whether element is present on the page<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : hasElementPresent(By.id())<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public boolean hasElementPresent(Target target) {
//
//			boolean hasElementPresent = browserFactory.getDriver().findElements(target.locator).size() > 0;
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target.locatorInfo);
//			if (hasElementPresent) {
//				reportMessage(STEP_STATUS_PASS,
//						"PRESENCE_OF_ELEMENT Elements Present'" + target.locatorInfo + "', typed on element");
//			} else {
//				reportMessage(STEP_STATUS_WARN, "PRESENCE_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//			}
//			return hasElementPresent;
//		}
//
//		public static boolean presenceOfElement(WebDriver driver, Target target) {
//
//			Boolean isPresent = browserFactory.getDriver().findElements(target.locator).size() > 0;
//
//			return isPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : scrollToElement <br>
//		 * Description : Used to scroll page till element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : WebElement<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : scrollToElement(WebElement)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public void scrollToElement(WebElement element) {
//			((JavascriptExecutor) browserFactory.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : reportMessage <br>
//		 * Description : Used to report with the message<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : String,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : reportMessage("step_status","step_message")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		protected void reportMessage(String stepStatusPass, String stepMessage) {
//			synchronized (this) {
//				Calendar cal=Calendar.getInstance();
//				SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss:SSS");
//				String timestamp=sdf.format(cal.getTime());
//				Reporter.log(timestamp + ": " + stepStatusPass + ": " + stepMessage, true);
//				boolean hasStepPassed = true;
//				if (stepStatusPass.equalsIgnoreCase(STEP_STATUS_FAIL)) {
//					hasStepPassed = false;
//				}
//				Calendar calc = Calendar.getInstance();
//				SimpleDateFormat sdff = new SimpleDateFormat("HH:mm:ss:SSS");
//				String ftimestamp = sdff.format(cal.getTime());
//				Assert.assertTrue(hasStepPassed,ftimestamp);
//			}
//		}
//
//		public void assertTheElement() {
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : errorMessage <br>
//		 * Description : Used to check the error message<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : String,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : errorMessage("step_status","step_message")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean errorMessage(String systemString, String gettextString, boolean assertType) {
//			String STEP_STATUS;
//			boolean IserrorMessageEqual = true;
//			if (assertType) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//
//			if (systemString.equalsIgnoreCase(gettextString)) {
//				reportMessage(STEP_STATUS_PASS, "errorMessage is  '" + gettextString);
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "errorMessage is not equal to  '" + gettextString);
//				IserrorMessageEqual = false;
//			}
//			return IserrorMessageEqual;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : SendEmptyKeys <br>
//		 * Description : Used to check the error message<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : String,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : errorMessage("step_status","step_message")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public void sendEmptyKeys(Target target, String sendkeyString) {
//
//			boolean hasElementPresent = waitForLoading(browserFactory.getDriver(), target.locator, 30);
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target.locatorInfo);
//			if (hasElementPresent) {
//				reportMessage(STEP_STATUS_PASS,
//						"PRESENCE_OF_ELEMENT Elements Present'" + target.locatorInfo + "', typed on element");
//				browserFactory.getDriver().findElement(target.locator).sendKeys("");
//				reportMessage(STEP_STATUS_PASS, "Empty text typed on element'" + target.locatorInfo + "', Element Present");
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "PRESENCE_OF_ELEMENT Element Not Present '" + target.locatorInfo + "'");
//			}
//
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : SetTheAttribute <br>
//		 * Description : Used to report with the message<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : String,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : SetTheAttribute("step_status","step_message")<br>
//		 * =========================================================================
//		 * <br>
//		 * 
//		 * @return
//		 */
//		public boolean setTheAttribute(Target target, String attribute, String value, boolean assertType,
//				String elementID) {
//			// boolean waitforElementLoading=waitForLoading(driver, target.locator, 30);
//			String STEP_STATUS;
//			if (assertType) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			boolean hasElementPresent = waitForElementPresent(target);
//
//			reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT '" + target.locatorInfo);
//
//			if (hasElementPresent) {
//
//				JavascriptExecutor js = (JavascriptExecutor) browserFactory.getDriver();
//				WebElement ElementToBeSet = browserFactory.getDriver().findElement(target.locator);
//				js.executeScript(
//						"document.getElementById('" + elementID + "').setAttribute('" + attribute + "', '" + value + "')");
//				// js.executeScript("arguments[0].setAttribute('"+attribute+"','"+value+"')",ElementToBeSet);
//
//				if (!ElementToBeSet.getAttribute(attribute).contentEquals(value)) {
//					String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//					reportMessage(status,
//							"CHANGE_OF_ATTRIBUTE of an element'" + target.locatorInfo + "' with the value " + value);
//				} else {
//					reportMessage(STEP_STATUS_PASS,
//							"CHANGE_OF_ATTRIBUTE of an element '" + target.locatorInfo + "'with the value " + value);
//				}
//
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "CHANGE_OF_ATTRIBUTE of an element '" + target.locatorInfo + "'");
//				Index.testres = "Failed";
//			}
//			return hasElementPresent;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : switchToWindowsPopup <br>
//		 * Description : Used for window handling which switches to the popup<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : WebDriver,long<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : switchToWindowsPopup(driver,20)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public static String switchToWindowsPopup(WebDriver driver, long timeout) {
//
//			(new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(timeout))).until(new ExpectedCondition<Boolean>() {
//				@Override
//				public Boolean apply(WebDriver driver) {
//					return (browserFactory.getDriver().getWindowHandles().size() != 1);
//				}
//			});
//
//			Set<String> handles = browserFactory.getDriver().getWindowHandles();
//			Iterator<String> itr = handles.iterator();
//			Object lastHandle = itr.next();
//			Object firstHandle = browserFactory.getDriver().getWindowHandle();
//			Object tempHandle;
//			while (itr.hasNext()) {
//				tempHandle = itr.next();
//				if (!tempHandle.toString().equals(firstHandle))
//					lastHandle = tempHandle;
//			}
//			driver = browserFactory.getDriver().switchTo().window(lastHandle.toString());
//			// sometimes print modal was popped up very quickly after another window
//			// has been popped up.
//			// This will take care of that case.
//			// waitForLoad(driver, timeout);
//			return firstHandle.toString();
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : switchToMainWindow <br>
//		 * Description : Used for window handling which switches to the main window<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : WebDriver,string,long<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : switchToMainWindow(driver,"firsthandle",20)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public static void switchToMainWindow(WebDriver driver, String firstHandle, long timeout) {
//
//			(new WebDriverWait(browserFactory.getDriver(),Duration.ofSeconds(timeout))).until(new ExpectedCondition<Boolean>() {
//				@Override
//				public Boolean apply(WebDriver driver) {
//					return (browserFactory.getDriver().getWindowHandles().size() == 1);
//				}
//			});
//			driver = browserFactory.getDriver().switchTo().window(firstHandle);
//		}
//
//		/***********************************
//		 * framework update require
//		 ********************************************/
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : isAlertExistAndAct <br>
//		 * Description : Used for handling alerts and performs action if alert is
//		 * present<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : WebDriver,boolean,long<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : isAlertExistAndAct(driver,true,20)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public static Boolean isAlertExistAndAct(WebDriver driver, boolean accept, long timeout) {
//			try {
//				Alert alert = (new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(timeout))).until(ExpectedConditions.alertIsPresent());
//				if (alert != null) {
//					// TestLifeCycleSupportUtils.logStep(StepStatus.INFO, "", "Alert message is
//					// ["+alert.getText()+"]", Reporter.getCurrentTestResult());
//
//					String actResult = "dismissed";
//					browserFactory.getDriver().switchTo().alert();
//					if (accept) {
//
//						alert.accept();
//						actResult = "accepted";
//					} else {
//						alert.dismiss();
//					}
//
//					return true;
//				}
//			} catch (Exception e) {
//			}
//			return false;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : readText <br>
//		 * Description : Used for reading the text of the element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : readText(By.id(),"text")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public void readText(Target target, String text) {
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, "TYPE_TEXT Element '" + target.locatorInfo + "' Data '" + text + "'");
//			if (hasElementPresent) {
//
//				List<WebElement> elements = this.browserFactory.getDriver().findElements(target.locator);
//				int elementsSize = elements.size();
//				if (elementsSize > 1) {
//					reportMessage(STEP_STATUS_WARN,
//							"Multiple Elements Found '" + target.locatorInfo + "', typed on first element");
//				} else {
//					if (elements.get(0).getText().contentEquals(text)) {
//						reportMessage(STEP_STATUS_PASS,
//								"Elements Found '" + elements.get(0).getText().toString() + "', typed on element");
//					}
//				}
//				// scrollToElement(elements.get(0));
//				// elements.get(0).sendKeys(text);
//				Assert.assertEquals(elements.get(0).getText(), text);
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + target.locatorInfo + "'");
//			}
//
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : URLTitle <br>
//		 * Description : Used for getting the URL title<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : WebDriver<br>
//		 * Return Value :String<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : URLTitle(driver)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public String urlTitle(WebDriver driver) {
//
//			return browserFactory.getDriver().getTitle();
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : getURLOnBrowser <br>
//		 * Description : Used for starting the URL on browser<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : WebDriver<br>
//		 * Return Value :String<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : getURLOnBrowser(driver)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public static void getURLOnBrowser(WebDriver driver, String url) {
//			Log.info("Application url : "+url);
//			System.out.println("Application url : "+url);
//			browserFactory.getDriver().get(url);
//
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : elementdisplayedonpage <br>
//		 * Description : Used for verifying whether element is displayed<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By<br>
//		 * Return Value :boolean<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : elementdisplayedonpage(By)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public boolean elementDisplayedOnPage(By elementLocation) {
//
//			if (browserFactory.getDriver().findElement(elementLocation).isDisplayed()) {
//				return true;
//			} else
//				return false;
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : waitForLoad1 <br>
//		 * Description : Used to wait for page loading<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : WebDriver,long<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : waitForLoad1(driver,20)<br>
//		 * =========================================================================
//		 * <br>
//		 */
//
//		public static void waitForLoad1(WebDriver _driver, long timeout) {
//			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
//				@Override
//				public Boolean apply(WebDriver _driver) {
//					return ((JavascriptExecutor) _driver).executeScript("return document.readyState").equals("complete");
//				}
//			};
//			// _driver = _browserFactory.getDriver().switchTo().defaultContent();
//			(new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(timeout))).until(pageLoadCondition);
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : getTheText <br>
//		 * Description : Used to get text of element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : getTheText(By.id(),"text")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public String getTheText(Target target, boolean assertType) {
//
//			String STEP_STATUS;
//			if (assertType) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			String TextToReturn = null;
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, " Element '" + target.locatorInfo + "Present");
//			if (hasElementPresent) {
//
//				List<WebElement> elements = this.browserFactory.getDriver().findElements(target.locator);
//				int elementsSize = elements.size();
//				if (elementsSize > 1) {
//					String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//					reportMessage(status, "Multiple Elements Found '" + target.locatorInfo + "', now on first element");
//				} else {
//					if (elementsSize == 1) {
//						TextToReturn = browserFactory.getDriver().findElement(target.locator).getText();
//						// if(TextToReturn.isEmpty())
//						// {TextToReturn=browserFactory.getDriver().findElement(target.locator).getAttribute("textContent");}
//						reportMessage(STEP_STATUS_PASS, "Elements Found '" + TextToReturn);
//					}
//				}
//				// scrollToElement(elements.get(0));
//				// elements.get(0).sendKeys(text);
//
//			} else {
//				String status = assertType ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//				reportMessage(status, "Element Not Found '" + target.locatorInfo + "'");
//			}
//			return TextToReturn;
//
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : equalityOfText <br>
//		 * Description : Used to check equality text of element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : equalityOfText(By.id(),"text")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean equalityOfText(Target target, String string1, String string2) {
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, " Element '" + target.locatorInfo + "Present");
//			if (hasElementPresent) {
//
//				List<WebElement> elements = this.browserFactory.getDriver().findElements(target.locator);
//				int elementsSize = elements.size();
//				if (elementsSize > 1) {
//
//					reportMessage(STEP_STATUS_WARN,
//							"Multiple Elements Found '" + target.locatorInfo + "', now on first element");
//				} else {
//					if (string1.contains(string2)) {
//						reportMessage(STEP_STATUS_PASS, "Elements Found '" + string1);
//					}
//				}
//				// scrollToElement(elements.get(0));
//				// elements.get(0).sendKeys(text);
//
//			} else {
//				reportMessage(STEP_STATUS_WARN, "Element Not Found '" + string1 + "'");
//			}
//
//			return true;
//
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : clearTheText <br>
//		 * Description : Used to clear text of element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : clearTheText(By.id(),"text")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public boolean clearTheText(Target target) {
//			boolean hasElementPresent = waitForLoading(browserFactory.getDriver(), target.locator, 5);
//
//			if (browserFactory.getDriver().findElement(target.locator).getAttribute("value") != null) {
//				reportMessage(STEP_STATUS_FIND, "Element '" + target.locator.toString() + "' with text'");
//				try {
//					if (hasElementPresent) {
//
//						List<WebElement> elements = this.browserFactory.getDriver().findElements(target.locator);
//						int elementsSize = elements.size();
//						if (elementsSize > 1) {
//							reportMessage(STEP_STATUS_WARN,
//									"Multiple Elements Found '" + target.locatorInfo + "', on page");
//						} else {
//							reportMessage(STEP_STATUS_PASS, "Elements Found '" + target.locatorInfo + "', on page");
//						}
//						// scrollToElement(elements.get(0));
//						// Thread.sleep(5);
//						if (waitForLoading(browserFactory.getDriver(), target.locator, 5)) {
//							browserFactory.getDriver().findElement(target.locator).clear();
//							reportMessage(STEP_STATUS_PASS, "Elements Found and the text was cleared");
//						}
//
//						else {
//							reportMessage(STEP_STATUS_FAIL, "Element Not Found and was not cleared due to page load");
//						}
//					} else {
//						reportMessage(STEP_STATUS_FAIL, "Element Not Preasent");
//					}
//
//				} catch (org.openqa.selenium.TimeoutException e) {
//					reportMessage(STEP_STATUS_FAIL, "Wait for Element Elapsed, Element '" + target.locatorInfo
//							+ "'search failed by given time seconds with timeout exception " + WAIT_ELEMENT_MAX);
//					Log.info("Slow Page Load");
//				}
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "Element is already clear ");
//			}
//			return true;
//
//		}
//
//		/**
//		 * =========================================================================
//		 * <br>
//		 * Function Name : getTheAttribute <br>
//		 * Description : Used to get text of element<br>
//		 * Author Name : Sariya Pathan <br>
//		 * Arguments : By,String<br>
//		 * Return Value :<br>
//		 * Created Date : <br>
//		 * Reviewed By : <br>
//		 * Reviewed Date : <br>
//		 * Modified By : <br>
//		 * Modified Date : <br>
//		 * Used At : <br>
//		 * How to Call : getTheText(By.id(),"text")<br>
//		 * =========================================================================
//		 * <br>
//		 */
//		public String getTheAttribute(Target target) {
//			String TextToReturn = null;
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, " Element '" + target.locatorInfo + "Present");
//			if (hasElementPresent) {
//
//				List<WebElement> elements = this.browserFactory.getDriver().findElements(target.locator);
//				int elementsSize = elements.size();
//				if (elementsSize > 1) {
//
//					reportMessage(STEP_STATUS_WARN,
//							"Multiple Elements Found '" + target.locatorInfo + "', now on first element");
//				} else {
//					if (elementsSize == 1) {
//						TextToReturn = browserFactory.getDriver().findElement(target.locator).getAttribute("innerText");
//						// if(TextToReturn.isEmpty())
//						// {TextToReturn=browserFactory.getDriver().findElement(target.locator).getAttribute("textContent");}
//						reportMessage(STEP_STATUS_PASS, "Elements Found '" + TextToReturn);
//					}
//				}
//				// scrollToElement(elements.get(0));
//				// elements.get(0).sendKeys(text);
//
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + target.locatorInfo + "'");
//			}
//			return TextToReturn;
//
//		}
//
//		protected boolean isAttribtuePresent(Target target, String attribute) {
//			Boolean result = false;
//			WebElement element = browserFactory.getDriver().findElement(target.locator);
//			try {
//				String value = element.getAttribute(attribute);
//				if (value != null) {
//					result = true;
//				}
//			} catch (Exception e) {
//				reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + target.locatorInfo + "'");
//			}
//
//			return result;
//		}
//
//		public static boolean isPresent(Target elementTarget, WebDriver driver, int timeout) {
//			// return webpage.waitFor(ExpectedConditions.presenceOfElementLocated(by),
//			// timeout).isDisplayed();
//			WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(timeout));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(elementTarget.locator));
//			if (wait.until(ExpectedConditions.presenceOfElementLocated(elementTarget.locator)).isDisplayed())
//				return true;
//			else
//				return false;
//		}
//
//		public void printAssertResultToExcel(String Sheet_name, String Test_Case_Id, int Step_Number, String Test_Results) {
//
//			try {
//				ExcelUtil.setExcelFile(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"Test_Cases_Result_Sheet"), Sheet_name);
//
//				ExcelUtil.setCellData(Sheet_name, Test_Case_Id, Step_Number, Test_Results);
//			} catch (Exception e) {
//
//				e.printStackTrace();
//			}
//		}
//
//		//Added by Zuci
//		@AfterTest(description = "Class Level Teardown!", alwaysRun=true)
//
//		public void tearDown(ITestContext cname) throws IOException {
//			
//			if (Index.dynamicLoginThread.containsKey(Thread.currentThread().getName()))
//				Index.dynamicLogins.replace(Index.dynamicLoginThread.get(Thread.currentThread().getName()), true);
//
//		/*	if (ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"Authorization").equalsIgnoreCase("Skip")||
//					ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"Authorization").equalsIgnoreCase("M")) 
//				TestngEmailableReport.Authorization_map.replace(cname.getName(), "M");
//			else if (ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"Authorization").equalsIgnoreCase("Submit")||
//					ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"Authorization").equalsIgnoreCase("S"))
//				TestngEmailableReport.Authorization_map.replace(cname.getName(), "S");*/
//
//			synchronized (BaseTest.class) {
//				TriggerAPI(cname);
//				/*if (ExcelUtil.testdata.containsKey(Thread.currentThread().getName()+"."+"NPI")&&Index.valueTakenFromDB.contains(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"NPI"))) {
//				Index.valueTakenFromDB=Index.valueTakenFromDB.replace(","+ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"NPI").toString(), "");
//			}else if(Index.valueTakenFromDB.contains(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"MemberNumber"))) {
//				Index.valueTakenFromDB=Index.valueTakenFromDB.replace(","+ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"MemberNumber").toString(), "");
//			}*/
//				Iterator<String> dataRemove = ExcelUtil.testdata.keySet().iterator();
//				while (dataRemove.hasNext()) {
//					String stringV = (String) dataRemove.next();
//					if (stringV.contains(Thread.currentThread().getName())) {
//						dataRemove.remove();
//					}	
//				}
//				Iterator<String> dataRemoveApi = postApiData.keySet().iterator();
//				while (dataRemoveApi.hasNext()) {
//					String stringV = (String) dataRemoveApi.next();
//					if (stringV.contains(Thread.currentThread().getName())) {
//						dataRemoveApi.remove();
//					}	
//				}
//				Iterator<String> dataRemoveQcms = qcmsResponseData.keySet().iterator();
//				while (dataRemoveQcms.hasNext()) {
//					String stringV = (String) dataRemoveQcms.next();
//					if (stringV.contains(Thread.currentThread().getName())) {
//						dataRemoveQcms.remove();
//					}	
//				}
//				Iterator<String> dataRemoveRelatedCase = relatedTestCaseAPI.keySet().iterator();
//				while (dataRemoveRelatedCase.hasNext()) {
//					String stringV = (String) dataRemoveRelatedCase.next();
//					if (stringV.contains(Thread.currentThread().getName())) {
//						dataRemoveRelatedCase.remove();
//					}	
//				}
//				Iterator<String> dataRemoveRelatedCaseResult = relatedTestCaseAPIResult.keySet().iterator();
//				while (dataRemoveRelatedCaseResult.hasNext()) {
//					String stringV = (String) dataRemoveRelatedCaseResult.next();
//					if (stringV.contains(Thread.currentThread().getName())) {
//						dataRemoveRelatedCaseResult.remove();
//					}	
//				}
//				Iterator<String> ccMap = TestngEmailableReport.Customer_Config_map.keySet().iterator();
//				while (ccMap.hasNext()) {
//					String stringV = (String) ccMap.next();
//					if (stringV!=null) {
//						if (stringV
//								.equalsIgnoreCase(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"))) {
//							ccMap.remove();
//						} 
//					}	
//				}
//			}
//			
//			if (browserFactory.getDriver()!=null) 
//				//browserFactory.getDriver().close();	
//				closeAllWindows();
//		}
//		//Added end
//
//		public void JSClick(By locator,String targetInfo) {
//
//			WebElement element = browserFactory.getDriver().findElement(locator);
//			JavascriptExecutor executor = (JavascriptExecutor) browserFactory.getDriver();
//			executor.executeScript("arguments[0].click();", element);
//
//			reportMessage(STEP_STATUS_PASS, targetInfo);
//		}
//
//
//		// Added by siva for check the check box status
//		public boolean is_selected(By locator)
//		{
//			WebElement element = browserFactory.getDriver().findElement(locator);
//			boolean state = element.isSelected();
//
//			return state;
//		}
//		public String GetTSID(String classname)
//		{
//			String TS_ID = classname.split("_")[0];
//			TS_ID = TS_ID.split("TS")[1];
//			return TS_ID;
//		}
//
//		//*****************Added by zuci for api access of qcms************
//		public static void apiGetTestCase(Object requestObject) {
//
//			for (int i = 1; i <=3; i++) {
//				try {
//					FileWriter jsonFile = new FileWriter(System.getProperty("user.dir")
//							+ "\\"+Index.prop.getProperty("ModuleId").trim()+"\\src\\test\\resources\\APIGetTetsCaseResponse.json");
//					String get_url = (Index.prop.getProperty("Qcms.GetTestCase").trim().replace("%UserName%",
//							Encrypssio.DE_decrypt(Index.prop.getProperty("API.UserName")))).replace("%PassWord%",
//									Encrypssio.DE_decrypt(Index.prop.getProperty("API.PassWord")));
//					URL url = new URL(get_url);
//					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//					connection.setRequestMethod("POST");
//					connection.setRequestProperty("Content-Type", "application/json");
//					connection.setDoOutput(true);
//					byte[] byteBody = requestObject.toString().getBytes();
//					OutputStream outputStream = connection.getOutputStream();
//					outputStream.write(byteBody);
//					int response_out = connection.getResponseCode();
//					String response_msg_out = connection.getResponseMessage();
//					System.out.println("Response Code at iteration -"+i+"= "+ response_out);
//					System.out.println("Response Message at iteration -"+i+"= " + response_msg_out);
//					InputStream inputstream = connection.getInputStream();
//					InputStreamReader inputStreamReader = new InputStreamReader(inputstream);
//					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//					String line;
//					while ((line = bufferedReader.readLine()) != null) {
//						jsonFile.append(line.replace("â€“", "–").replace("ï¿½", "–").replace(" ", "–"));
//					}
//					jsonFile.flush();
//					if (response_msg_out.contains("OK")) 
//						break;	
//				}catch (Exception e) {
//					System.out.println(e.getMessage());
//					System.out.println("Unable to get test case at iteration-"+i);
//				}
//			}
//
//		}
//
//		public static void getTestCasesFromQcmsResponse() throws IOException {
//			FileReader reader = new FileReader(System.getProperty("user.dir")+"\\"+Index.prop.getProperty("ModuleId").trim()+"\\src\\test\\resources\\APIGetTetsCaseResponse.json");                        
//			JSONParser jsonParser = new JSONParser();
//			JSONObject jsonObject = null;
//			try {
//				jsonObject = (JSONObject) jsonParser.parse(reader);
//			} catch (org.json.simple.parser.ParseException e) {
//
//				e.printStackTrace();
//			}
//			if (jsonObject.get("TestCases")!=null) {
//				JSONArray testCasesOut=(JSONArray) jsonObject.get("TestCases");
//				for (int j = 0; j < testCasesOut.size(); j++) {
//					JSONObject details=(JSONObject) testCasesOut.get(j);
//					Set keys= details.keySet();
//					HashMap<String,String> response = new HashMap<>();	
//					for (Object keyObject : keys) {
//						if (details.get(keyObject)!=null) {
//							if (keyObject.toString().equals("TestCaseId")) {
//								response.put((keyObject.toString().trim()), "TS"+details.get(keyObject).toString().trim());
//							}
//							if (keyObject.toString().equals("DataSetId")) {
//								response.put((keyObject.toString().trim()), "DS"+details.get(keyObject).toString().trim());
//							}else if(keyObject.toString().equals("TestDataSets")) {
//								JSONArray dataArray=(JSONArray) details.get("TestDataSets");
//								int testDataArray_Size = dataArray.size();
//								if(testDataArray_Size==0)
//									Log.info("No TestDataSet availabe for Testcase : "+response.get("TestCaseId"));
//								else
//								{
//									for (Object dataset : dataArray) {	
//										JSONObject datasetObj=(JSONObject) dataset;
//										Set dataSetKey =datasetObj.keySet();
//										for (Object object : dataSetKey) {
//											if (object.equals("ApplicableClient")) {
//												response.put((object.toString().trim()), datasetObj.get(object).toString().trim());
//												break;
//											}
//										}
//									}
//								}
//							}
//							else
//								response.put(keyObject.toString().trim(), details.get(keyObject).toString().trim());
//
//							ArrayList<Object> IndexDetail = new ArrayList<>();
//							IndexDetail.add(response.get("TestCaseId"));
//							IndexDetail.add(response.get("Client"));
//							IndexDetail.add(response.get("Title"));
//							IndexDetail.add(response.get("IsBVT"));
//							IndexDetail.add(response.get("ApplicableClient"));
//							if(response.get("DataSetId")!=null)
//								IndexDetail.add(response.get("DataSetId"));
//							else
//								IndexDetail.add("NA");
//
//							IndexDetail.add(response.get("Source"));
//							IndexDetail.add(response.get("FunctionalArea"));
//							IndexDetail.add(response.get("Environment"));
//							IndexDetail.add(response.get("CRName"));
//							if (!IndexDetail.isEmpty()) {
//								responseData.put(j, IndexDetail);
//							}
//						}
//
//					}
//				}
//			}else
//				Assert.fail("No Records Available in API response");	
//		}
//
//		public static void getTestDataFromQcmsResponse(String cName) throws IOException {
//			String[] splitClass= cName.split("_");
//			FileReader reader = new FileReader(System.getProperty("user.dir")+"\\"+Index.prop.getProperty("ModuleId").trim()+"\\src\\test\\resources\\APIGetTetsCaseResponse.json");                        
//			JSONParser jsonParser = new JSONParser();
//			JSONObject jsonObject = null;
//			try {
//				jsonObject = (JSONObject) jsonParser.parse(reader);
//			} catch (org.json.simple.parser.ParseException e) {
//
//				e.printStackTrace();
//			}
//			JSONArray testCasesOut=(JSONArray) jsonObject.get("TestCases");
//			for (Object object : testCasesOut) {
//				JSONObject testCaseRecords= (JSONObject) object;	
//				//if (splitClass[1].equalsIgnoreCase("Global")) {
//				String Client=testCaseRecords.get("Client").toString();
//				if (Client.toString().contains("-")) {
//					String cxNameSplited[] = Client.split("-");
//					Client=cxNameSplited[0];
//				}
//				if (Client.equalsIgnoreCase(splitClass[1])) {
//					JSONArray dataArray;
//					if(((JSONArray) testCaseRecords.get("TestDataSets")).size()>0){
//						JSONObject dataDetails=(JSONObject) ((JSONArray) testCaseRecords.get("TestDataSets")).get(0);
//						String cxName=dataDetails.get("ApplicableClient").toString();
//						if (cxName.toString().contains("-")) {
//							String cxNameSplited[] = cxName.split("-");
//							cxName=cxNameSplited[0];
//						}
//						if (("TS"+testCaseRecords.get("TestCaseId")).equals(splitClass[0])&&
//								cxName.equalsIgnoreCase(splitClass[2])&&
//								("DS"+testCaseRecords.get("DataSetId").toString()).equalsIgnoreCase(splitClass[3])) {
//							//Storing customer name
//							ExcelUtil.testdata.put(Thread.currentThread().getName() + ".customerName",
//									cxName.trim());
//							TestngEmailableReport.clientName_map.put(cName, splitClass[1]+"_"+cxName.trim());
//
//							//Getting data from test case records
//							Set testCaseRecordsData=testCaseRecords.keySet();
//							for (Object CaseRecordsData : testCaseRecordsData) {
//								if(testCaseRecords.get(CaseRecordsData)!=null&&
//										!CaseRecordsData.toString().equalsIgnoreCase("TestDataSets")) {
//									if (CaseRecordsData.toString().trim().equalsIgnoreCase("Source")&&
//											testCaseRecords.get(CaseRecordsData).toString().trim().contains(","))
//										qcmsResponseData.put(
//												Thread.currentThread().getName() + "."
//														+ CaseRecordsData.toString().trim(),
//														testCaseRecords.get(CaseRecordsData).toString().split(",")[0].trim());
//									else
//										qcmsResponseData.put(
//												Thread.currentThread().getName() + "."
//														+ CaseRecordsData.toString().trim(),
//														testCaseRecords.get(CaseRecordsData).toString().trim());
//								}
//							}
//							//Getting data details
//							Set dataDetailsKeys=dataDetails.keySet();
//							for (Object dataDetailsKeyObj : dataDetailsKeys) {
//								if(dataDetails.get(dataDetailsKeyObj)!=null&&
//										!dataDetailsKeyObj.toString().equalsIgnoreCase("FieldValue")) {
//									postApiData.put(Thread.currentThread().getName() + "." +dataDetailsKeyObj.toString().trim(),dataDetails.get(dataDetailsKeyObj).toString().trim());
//								}
//							}
//							//Getting test data
//							JSONObject testData=(JSONObject) ((JSONObject)dataDetails.get("FieldValue")).get("root");
//							Set testdataKeys=testData.keySet();
//							for (Object testdataKeyObj : testdataKeys) {
//								if (testData.get(testdataKeyObj)!=null) {
//									ExcelUtil.testdata.put(Thread.currentThread().getName() + "." + testdataKeyObj.toString().trim(),testData.get(testdataKeyObj).toString().trim());
//								}
//							}
//							break;
//						}	
//					}
//				}
//
//				/*}else {
//					if (("TS"+testCaseRecords.get("TestCaseId")).equals(splitClass[0])&&
//							("DS"+testCaseRecords.get("DataSetId").toString()).equalsIgnoreCase(splitClass[2])) {
//						JSONArray dataArray;
//						if(((JSONArray) testCaseRecords.get("TestDataSets")).size()>0){
//							JSONObject dataDetails=(JSONObject) ((JSONArray) testCaseRecords.get("TestDataSets")).get(0);
//							String cxName=dataDetails.get("ApplicableClient").toString();
//							if (cxName.toString().contains("-")) {
//								String cxNameSplited[] = cxName.split("-");
//								cxName=cxNameSplited[0];
//							}
//							//Storing customer name
//							ExcelUtil.testdata.put(Thread.currentThread().getName() + ".customerName",
//									cxName.trim());
//							TestngEmailableReport.clientName_map.put(cName, cxName.trim());
//
//							//Getting data from test case records
//							Set testCaseRecordsData=testCaseRecords.keySet();
//							for (Object CaseRecordsData : testCaseRecordsData) {
//								if(testCaseRecords.get(CaseRecordsData)!=null&&
//										!CaseRecordsData.toString().equalsIgnoreCase("TestDataSets")) {
//									if (CaseRecordsData.toString().trim().equalsIgnoreCase("Source")&&
//											testCaseRecords.get(CaseRecordsData).toString().trim().contains(","))
//										qcmsResponseData.put(
//												Thread.currentThread().getName() + "." + CaseRecordsData.toString().trim(),
//												testCaseRecords.get(CaseRecordsData).toString().split(",")[0].trim());
//									else
//										qcmsResponseData.put(
//												Thread.currentThread().getName() + "." + CaseRecordsData.toString().trim(),
//												testCaseRecords.get(CaseRecordsData).toString().trim());
//								}
//							}
//							//Getting data details
//							Set dataDetailsKeys=dataDetails.keySet();
//							for (Object dataDetailsKeyObj : dataDetailsKeys) {
//								if(dataDetails.get(dataDetailsKeyObj)!=null&&
//										!dataDetailsKeyObj.toString().equalsIgnoreCase("FieldValue")) {
//									postApiData.put(Thread.currentThread().getName() + "." +dataDetailsKeyObj.toString().trim(),dataDetails.get(dataDetailsKeyObj).toString().trim());
//								}
//							}
//							//Getting test data
//							JSONObject testData=(JSONObject) ((JSONObject)dataDetails.get("FieldValue")).get("root");
//							Set testdataKeys=testData.keySet();
//							for (Object testdataKeyObj : testdataKeys) {
//								if (testData.get(testdataKeyObj)!=null) {
//
//									ExcelUtil.testdata.put(Thread.currentThread().getName() + "." + testdataKeyObj.toString().trim(),testData.get(testdataKeyObj).toString().trim());
//								}
//							}
//							break;
//						}	
//					}
//				}*/
//			}
//
//			if (postApiData.get(Thread.currentThread().getName()+".DataSetId")==null) {
//				Assert.fail("No Test Data Set Available for-"+cName+"\n"+"If record available in API response, please check the client value match with respective test case file titles");
//				//if client value contains global then file also should contains global next to the test case id
//				//if its not a global client then change the file into respective client name next to the case id
//			}
//
//		}
//
//		public void apiExecuteTestCase(Object obj) throws IOException {
//			String get_url=(Index.prop.getProperty("Qcms.ExecuteTestCase").trim().replace("%UserName%", Encrypssio.DE_decrypt(Index.prop.getProperty("API.UserName")))).replace("%PassWord%", Encrypssio.DE_decrypt(Index.prop.getProperty("API.PassWord")));
//			URL url_post = new URL(get_url);
//			HttpURLConnection urlConnection_post=(HttpURLConnection) url_post.openConnection();
//			urlConnection_post.setRequestMethod("POST");
//			urlConnection_post.setRequestProperty("Content-Type", "application/json");
//			urlConnection_post.setDoOutput(true);
//			byte[] byteBody=obj.toString().getBytes();
//			OutputStream outputStream=urlConnection_post.getOutputStream();
//			outputStream.write(byteBody);
//			int response_out=urlConnection_post.getResponseCode();
//			String response_msg_out=urlConnection_post.getResponseMessage();
//			System.out.println("Response Code= "+response_out);
//			System.out.println("Response Message= "+response_msg_out);
//			InputStream inputstream=urlConnection_post.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(inputstream);
//			BufferedReader buffer = new BufferedReader(inputStreamReader);
//			String line;
//			StringBuffer strbuffer = new StringBuffer();
//			while ((line=buffer.readLine())!=null) {
//				strbuffer.append(line);
//			}
//			System.out.println("Response Body= "+strbuffer);
//
//			if(strbuffer.toString().contains("Success"))
//				Log.info("Testcase execution status updated in QCMS successfully");		
//			else
//				Log.info("Testcase execution status not updated in QCMS");
//
//
//		}
//
//		public void apiReportDefect(Object obj) throws IOException {
//			String get_url=(Index.prop.getProperty("Qcms.ReportDefect").trim().replace("%UserName%", Encrypssio.DE_decrypt(Index.prop.getProperty("API.UserName")))).replace("%PassWord%", Encrypssio.DE_decrypt(Index.prop.getProperty("API.PassWord")));
//			URL url_post = new URL(get_url);
//			HttpURLConnection urlConnection_post=(HttpURLConnection) url_post.openConnection();
//			urlConnection_post.setRequestMethod("POST");
//			urlConnection_post.setRequestProperty("Content-Type", "application/json");
//			urlConnection_post.setDoOutput(true);
//			byte[] byteBody=obj.toString().getBytes();
//			OutputStream outputStream=urlConnection_post.getOutputStream();
//			outputStream.write(byteBody);
//			int response_out=urlConnection_post.getResponseCode();
//			String response_msg_out=urlConnection_post.getResponseMessage();
//			System.out.println("Response Code= "+response_out);
//			System.out.println("Response Message= "+response_msg_out);
//			InputStream inputstream=urlConnection_post.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(inputstream);
//			BufferedReader buffer = new BufferedReader(inputStreamReader);
//			String line;
//			StringBuffer strbuffer = new StringBuffer();
//			while ((line=buffer.readLine())!=null) {
//				strbuffer.append(line);
//			}
//			System.out.println("Response Body= "+strbuffer);
//
//			if(strbuffer.toString().contains("Success"))
//				Log.info("Defect Reported successfully");		
//			else
//				Log.info("Defect not Reported successfully");
//
//
//		}
//
//
//		public static void apiSendReport(Object obj) throws IOException {
//			String get_url=(Index.prop.getProperty("Qcms.ExecutionLog").trim().replace("%UserName%", Encrypssio.DE_decrypt(Index.prop.getProperty("API.UserName")))).replace("%PassWord%", Encrypssio.DE_decrypt(Index.prop.getProperty("API.PassWord")));
//			URL url_post = new URL(get_url);
//			HttpURLConnection urlConnection_post=(HttpURLConnection) url_post.openConnection();
//			urlConnection_post.setRequestMethod("POST");
//			urlConnection_post.setRequestProperty("Content-Type", "application/json");
//			urlConnection_post.setDoOutput(true);
//			byte[] byteBody=obj.toString().getBytes();
//			OutputStream outputStream=urlConnection_post.getOutputStream();
//			outputStream.write(byteBody);
//			int response_out=urlConnection_post.getResponseCode();
//			String response_msg_out=urlConnection_post.getResponseMessage();
//			System.out.println("Response Code= "+response_out);
//			System.out.println("Response Message= "+response_msg_out);
//			InputStream inputstream=urlConnection_post.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(inputstream);
//			BufferedReader buffer = new BufferedReader(inputStreamReader);
//			String line;
//			StringBuffer strbuffer = new StringBuffer();
//			while ((line=buffer.readLine())!=null) {
//				strbuffer.append(line);
//			}
//			System.out.println("Response Body= "+strbuffer);
//
//			if(strbuffer.toString().contains("Success"))
//				Log.info("Report updated in QCMS successfully");		
//			else
//				Log.info("Report not updated in QCMS");
//
//
//		}
//
//
//		public Object objectCreationForExecuteTestCase(String timeTaken) {
//			JSONObject obj=new JSONObject(); 
//			Object caseid=postApiData.get(Thread.currentThread().getName()+".TestCaseId").toString().replace("TS", "");
//			obj.put("TestCaseId",caseid);
//			if (postApiData.get(Thread.currentThread().getName() + ".Environment").toString().equalsIgnoreCase("qapoc")||
//					postApiData.get(Thread.currentThread().getName() + ".Environment").toString().equalsIgnoreCase("UiUx_qa")) 
//				obj.put("Environment", "qa");
//			else
//				obj.put("Environment", postApiData.get(Thread.currentThread().getName() + ".Environment"));
//			
//			if (postApiData.get(Thread.currentThread().getName()+".Browser").toString().equalsIgnoreCase("IE"))
//				obj.put("Browser","Edge(with IE mode ON)");
//			else 
//				obj.put("Browser",postApiData.get(Thread.currentThread().getName()+".Browser"));
//		
//			obj.put("TestExecutionStatus","Executed");
//			obj.put("ExecuteIteration",0);
//			if (postApiData.containsKey(Thread.currentThread().getName()+".TestResult")) 
//				obj.put("TestResult", postApiData.get(Thread.currentThread().getName() + ".TestResult"));
//			else {
//				obj.put("TestResult", "Skipped");
//				obj.replace("TestExecutionStatus","Skipped");
//			}
//			String execms="";
//			if (qcmsResponseData.get(Thread.currentThread().getName() + ".Source").equalsIgnoreCase("ePA"))
//				execms="Login User-ePA user \n Customer Configuraion API Failures-"+TestngEmailableReport.Customer_Config_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+".testName"))+"\n"+postApiData.get(Thread.currentThread().getName()+".ExecuteComments");
//			else if(qcmsResponseData.get(Thread.currentThread().getName() + ".Source").equalsIgnoreCase("PromptPA"))
//				execms="Login User-PromptPA user \n Customer Configuraion API Failures-"+TestngEmailableReport.Customer_Config_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+".testName"))+"\n"+postApiData.get(Thread.currentThread().getName()+".ExecuteComments");
//			else
//				execms=execms="Login User-"+TestngEmailableReport.LogedInUser.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+".testName"))+
//				"\n Second eligiblity popup comments : "+postApiData.get(Thread.currentThread().getName()+".MemberSecondEligibilityComment")+
//				"\n Customer Configuraion API Failures-"+TestngEmailableReport.Customer_Config_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+".testName"))+"\n"+postApiData.get(Thread.currentThread().getName()+".ExecuteComments");
//			obj.put("ExecuteComments",execms);
//			obj.put("Source", qcmsResponseData.get(Thread.currentThread().getName() + ".Source"));
//			obj.put("Client",postApiData.get(Thread.currentThread().getName()+".Client"));
//			obj.put("ApplicableClient",postApiData.get(Thread.currentThread().getName()+".ApplicableClient"));
//			obj.put("TargetRelease", qcmsResponseData.get(Thread.currentThread().getName() + ".TargetRelease"));
//			obj.put("TestDataSetID",postApiData.get(Thread.currentThread().getName()+".DataSetId"));
//			obj.put("TimeTaken", timeTaken);
//			obj.put("SystemName", postApiData.get(Thread.currentThread().getName()+".SystemName")+"--"+TestngEmailableReport.LogedInUser.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+".testName")));
//			obj.put("EligibilitySource", null);
//			if (TestngEmailableReport.ManuallyAddedPatient_map.containsKey(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName"))) 		
//				obj.replace("EligibilitySource",TestngEmailableReport.ManuallyAddedPatient_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName")));
//
//			obj.put("ClaimTestSource",null); 		
//			if (TestngEmailableReport.claimTest_map.containsKey(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName"))) 		
//				obj.replace("ClaimTestSource",TestngEmailableReport.claimTest_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName")));
//
//			obj.put("AuthSource", null);
//			
//			if (TestngEmailableReport.Authorization_map.containsKey(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName"))) 
//			{
//				if(TestngEmailableReport.Authorization_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName")).equalsIgnoreCase("NA")||
//						TestngEmailableReport.Authorization_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName")).equalsIgnoreCase("UN"))
//					obj.replace("AuthSource","");
//				else
//					obj.replace("AuthSource",TestngEmailableReport.Authorization_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName")));
//			}
//
//			JSONArray array = new JSONArray();
//			JSONObject subObj= new JSONObject();
//			subObj.put("FileName", postApiData.get(Thread.currentThread().getName()+".FileName"));
//			subObj.put("ImageBytes", postApiData.get(Thread.currentThread().getName()+".AttachmentFile"));
//			array.add(subObj);
//			obj.put("AttachmentFile", array);
//			return obj;
//		}
//
//		public Object objectCreationForReportDefect() {
//			JSONObject obj=new JSONObject();
//			obj.put("Stream","Agadia Bob");
//			obj.put("Client",postApiData.get(Thread.currentThread().getName()+".Client"));
//			obj.put("Release", qcmsResponseData.get(Thread.currentThread().getName() + ".TargetRelease"));
//			obj.put("CRName",qcmsResponseData.get(Thread.currentThread().getName() + ".CRName"));
//			obj.put("Priority","Medium");
//			obj.put("Severity","Medium");
//			if(postApiData.containsKey(Thread.currentThread().getName()+".TestResult"))
//				obj.put("ActualResult",postApiData.get(Thread.currentThread().getName()+".TestResult"));
//			if (postApiData.get(Thread.currentThread().getName() + ".Environment").toString().equalsIgnoreCase("qapoc")||
//					postApiData.get(Thread.currentThread().getName() + ".Environment").toString().equalsIgnoreCase("UiUx_qa")) 
//				obj.put("Environment", "qa");
//			else
//				obj.put("Environment", postApiData.get(Thread.currentThread().getName() + ".Environment"));
//			String execms="";
//			if (qcmsResponseData.get(Thread.currentThread().getName() + ".Source").equalsIgnoreCase("ePA"))
//				execms="Login User-ePA user \n"+postApiData.get(Thread.currentThread().getName()+".ExecuteComments");
//			else if(qcmsResponseData.get(Thread.currentThread().getName() + ".Source").equalsIgnoreCase("PromptPA"))
//				execms="Login User-PromptPA user \n"+postApiData.get(Thread.currentThread().getName()+".ExecuteComments");
//			else
//				execms=execms="Login User-"+TestngEmailableReport.LogedInUser.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+".testName"))+"\n"+postApiData.get(Thread.currentThread().getName()+".ExecuteComments");
//
//			obj.put("ExecuteComments",execms);
//			obj.put("Source", qcmsResponseData.get(Thread.currentThread().getName() + ".Source"));
//			if (postApiData.get(Thread.currentThread().getName()+".Browser").toString().equalsIgnoreCase("IE"))
//				obj.put("Browser","Edge(with IE mode ON)");
//			else 
//				obj.put("Browser",postApiData.get(Thread.currentThread().getName()+".Browser"));
//			Object caseid=postApiData.get(Thread.currentThread().getName()+".TestCaseId").toString().replace("TS", "");
//			obj.put("TestCaseId",caseid);		
//			obj.put("TestDataSetID",postApiData.get(Thread.currentThread().getName()+".DataSetId"));
//			JSONArray array = new JSONArray();
//			JSONObject subObj= new JSONObject();
//			subObj.put("FileName", postApiData.get(Thread.currentThread().getName()+".FileName"));
//			subObj.put("ImageBytes", postApiData.get(Thread.currentThread().getName()+".AttachmentFile"));
//			array.add(subObj);
//			obj.put("AttachmentFile", array);
//			return obj;
//		}
//
//
//		public static Object createObjectForSendReport() {
//			Date date= new Date();
//			SimpleDateFormat exeDate= new SimpleDateFormat("yyyy-MM-dd");
//			SimpleDateFormat exeTime= new SimpleDateFormat("HH:mm");
//			JSONObject obj=new JSONObject();
//			if (Index.prop.getProperty("Execute.From").trim().equalsIgnoreCase("Qcms")) {
//				obj.put("Release", Index.jsonObject.get("Release"));
//				if (Index.jsonObject.get("Client")!=null) {
//					obj.put("Client", Index.jsonObject.get("Client"));
//				}
//				if (Index.jsonObject.get("CR")!=null) {
//					obj.put("CRName", Index.jsonObject.get("CR"));
//				}
//				obj.put("TestCaseId",Index.jsonObject.get("TestCaseIDs"));		
//				obj.put("TargetRelease", Index.jsonObject.get("TargetRelease"));
//			}
//			obj.put("Stream","Agadia Bob");
//			obj.put("ExecutionDateTime", exeDate.format(date)+"T"+exeTime.format(date));
//			JSONArray array = new JSONArray();
//			JSONObject subObj= new JSONObject();
//			subObj.put("FileName", Index.dir+".zip");
//			subObj.put("ImageBytes", MailFunctions.byteReport());
//			array.add(subObj);
//			obj.put("Attachments", array);
//			System.out.println("API Send Report String-- "+obj);
//			return obj;
//
//		}
//
//		public static void updateApiGetTestCase() throws FileNotFoundException {
//			JSONObject obj=new JSONObject(); 
//			obj.put("Stream", System.getProperty("Stream"));
//			obj.put("Release", System.getProperty("Release"));
//			obj.put("ExecuteEnvironment", System.getProperty("ExecuteEnvironment"));
//			obj.put("TestLabsEnvironment", System.getProperty("TestLabsEnvironment"));
//			obj.put("TargetRelease", System.getProperty("TargetRelease"));
//			obj.put("TestCaseIDs", System.getProperty("TestCaseIDs"));
//			obj.put("Automated", System.getProperty("Automated"));
//			obj.put("TargetBrowser", System.getProperty("TargetBrowser"));
//			obj.put("Source", System.getProperty("Source"));
//			obj.put("IsBVT", System.getProperty("IsBVT"));
//			if (System.getProperty("ApplicableClients")!=null)
//				obj.put("ApplicableClients", System.getProperty("ApplicableClients"));
//			if (System.getProperty("Client")!=null)
//				obj.put("Client", System.getProperty("Client"));
//			if (System.getProperty("TestResult")!=null) 
//				obj.put("TestResult", System.getProperty("TestResult"));
//			if (System.getProperty("CR")!=null) 
//				obj.put("CR", System.getProperty("CR"));
//			FileWriter file;
//			try {
//				file = new FileWriter(System.getProperty("user.dir")+"\\"+Index.prop.getProperty("ModuleId").trim()+"\\src\\test\\resources\\APIGetTestCaseRequest.json");
//				file.write(obj.toJSONString());
//				file.flush();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}     
//		}
//
//
//		// Added by siva for check the element displayed or not
//		public boolean is_Displayed(By locator)
//		{
//			WebElement element = browserFactory.getDriver().findElement(locator);
//			boolean state = element.isDisplayed();
//			return state;
//		}
//		// Added by siva
//		public void hover(By locator)
//		{
//
//			WebElement element = browserFactory.getDriver().findElement(locator);
//			Actions actions = new Actions(browserFactory.getDriver());
//			actions.moveToElement(element).click().build().perform();
//		}
//		public String getAttribute(Target target, String Attribute) {
//			String TextToReturn = null;
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, " Element '" + target.locatorInfo + "Present");
//			if (hasElementPresent) {
//
//				List<WebElement> elements = this.browserFactory.getDriver().findElements(target.locator);
//				int elementsSize = elements.size();
//				if (elementsSize > 1) {
//
//					reportMessage(STEP_STATUS_WARN,
//							"Multiple Elements Found '" + target.locatorInfo + "', now on first element");
//				} else {
//					if (elementsSize == 1) {
//						TextToReturn = browserFactory.getDriver().findElement(target.locator).getAttribute(Attribute);
//						reportMessage(STEP_STATUS_PASS, "Elements Found '" + TextToReturn);
//					}
//				}
//
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + target.locatorInfo + "'");
//			}
//			return TextToReturn;
//
//		}
//		public WebElement getElement(By locator)
//		{
//			WebElement element = browserFactory.getDriver().findElement(locator);
//			return element;
//		}
//		public void actionsClick(Target target)
//		{
//			WebElement element = getElement(target.locator);
//			Actions act = new Actions(browserFactory.getDriver());
//			act.moveToElement(element).build().perform();
//			act.click(element).build().perform();
//			reportMessage(STEP_STATUS_PASS, "Clicked "+target.locator);
//		}
//		public void javaScriptClick(Target target)
//		{
//			if(verifyElementVisible(target, true))
//			{
//				WebElement element =browserFactory.getDriver().findElement(target.locator);
//				JavascriptExecutor executor = (JavascriptExecutor)browserFactory.getDriver();
//				executor.executeScript("arguments[0].click();", element);
//				reportMessage(STEP_STATUS_PASS, "Clicked "+target.locator);
//			}
//			else
//				reportMessage(STEP_STATUS_FAIL, target.locator+" not visible in java Script click");
//		}
//
//		public void javaScriptClick_Webelement(WebElement element1)
//		{
//
//			{
//				WebElement element =element1;
//				JavascriptExecutor executor = (JavascriptExecutor)browserFactory.getDriver();
//				executor.executeScript("arguments[0].click();", element);
//				reportMessage(STEP_STATUS_PASS, "Clicked "+element1);
//			}
//		}
//
//
//		public boolean isClickable(By locator) {
//			long waitTimeSeconds = 0;
//			boolean status = false;
//			try {
//				String jenkins_pageload_wait = System.getProperty("PageLoadWait");// To read a jenkins execution PageLoad Wait
//				if(jenkins_pageload_wait==null) {
//					waitTimeSeconds = Long.parseLong(Index.prop.getProperty("PageLoadWait").trim());
//				}
//				else {
//					waitTimeSeconds=Long.parseLong(System.getProperty("PageLoadWait"));
//				}
//				WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(),Duration.ofSeconds(waitTimeSeconds));
//				WebElement we = wait.until(ExpectedConditions.elementToBeClickable(locator));
//				if (we != null) {
//					status = true;
//					reportMessage(STEP_STATUS_DONE, "Element to be Clickable");
//				} else {
//					reportMessage(STEP_STATUS_FAIL, "Element Not Clickable");
//					Index.testres = "Failed";
//					Log.info("Slow Page Load");
//				}
//			} catch (WebDriverException wde) {
//				reportMessage(STEP_STATUS_WARN, "Element Not Clickable");
//			} 
//			return status;
//		}
//		public void btn_click(Target target)
//		{
//			WebElement element = getElement(target.locator);
//			elementClick(element);
//			reportMessage(STEP_STATUS_PASS, "Clicked "+target.locator);
//		}
//		public List getElements(Target target)
//		{
//			List<WebElement> elements = browserFactory.getDriver().findElements(target.locator);
//			return elements;
//		}
//		public void actionsTypeText(Target target, String value)
//		{
//			try {
//				WaitPageLoad(target.locator);
//				WebElement element =browserFactory.getDriver().findElement(target.locator);
//				element.clear();
//				element.sendKeys(Keys.CONTROL);
//				element.sendKeys(value);
//				reportMessage(STEP_STATUS_PASS, value+"Tex Enterd into"+target.locatorInfo);
//			}
//			catch(Exception e)
//			{
//				reportMessage(STEP_STATUS_FAIL, value+"Tex Not Enterd into"+target.locatorInfo);
//			}
//		}
//		//*********************************Added code end********
//		public void selectGivenDate(String Date,String elementid) {
//			click(JSONFileReader.getDynamicLocator("CalendarWithTimeZone",elementid+";"+Date));
//		}
//		public void selectCurrentDate(String elementid) {
//			Date date = new Date();
//			DateFormat df = new SimpleDateFormat("d");
//			df.setTimeZone(TimeZone.getTimeZone("UTC-5"));
//			click(JSONFileReader.getDynamicLocator("CalendarWithTimeZone",elementid+";"+df.format(date)));
//		}
//		public void queManagerDMRCalendarPicker() {
//			DateTimeFormatter startdate = DateTimeFormatter.ofPattern("d/MM/yyyy");
//			LocalDateTime now1 = LocalDateTime.now();
//
//			String currentdate1 = startdate.format(now1);
//			String[] arrOfStr1 = currentdate1.split("/");
//			String currentDate1 = arrOfStr1[0];
//			click(By.linkText(currentDate1));
//		}
//
//		public long getWaitTime()
//		{
//			long waitTimeSeconds = 0;
//			try {
//				String jenkins_pageload_wait = System.getProperty("CommonWait");// To read a jenkins execution PageLoad Wait
//				if(jenkins_pageload_wait==null) {
//					waitTimeSeconds = Long.parseLong(Index.prop.getProperty("CommonWait").trim());
//				}
//				else {
//					waitTimeSeconds=Long.parseLong(System.getProperty("CommonWait"));
//				}
//			} catch (WebDriverException wde) {
//				reportMessage(STEP_STATUS_WARN, "Page load wait, Page Not Loaded in given time seconds " + waitTimeSeconds);
//				Log.info("Browser got exited before it gets connected");
//			} 
//			return waitTimeSeconds;
//		}
//
//		public void click1_old2(By elementLocation, String targetInfo, boolean booleanAssert) {
//			for (int i = 1 ; i<=3 ; i++)
//			{
//				boolean hasElementPresent;
//				boolean hasElementClickable;
//				boolean blnClickButton = false;
//				// boolean hasElementNext;
//				String STEP_STATUS;
//				if (booleanAssert) {
//					STEP_STATUS = STEP_STATUS_WARN;
//				} else {
//					STEP_STATUS = STEP_STATUS_FAIL;
//
//				}
//				WebElement element;
//				try {
//					hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, 30);
//					hasElementClickable = waitForElementClickable(elementLocation);
//					if (hasElementPresent && hasElementClickable) {
//						element = browserFactory.getDriver().findElement(elementLocation);
//						if (element.isEnabled()) {
//							//Thread.sleep(5); //Commanded by Zuci QA
//							element.click();
//							blnClickButton = true;
//							// Thread.sleep(5);
//							/*
//							 * if(elementdisplayedonpage(elementLocation)) { element.click(); blnClickButton
//							 * = true; }else {
//							 */
//							reportMessage(STEP_STATUS_PASS, "Click1Method - CLICK_BUTTON : clicked '" + targetInfo + "' button successfully.");// }
//							//browserFactory.getDriver().findElement(elementLocation).click();
//							break;
//						}
//
//						if (!blnClickButton) {
//							String status = booleanAssert ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//							reportMessage(status,
//									"Click1Method - CLICK_BUTTON : click '" + targetInfo + "' button failed. Button not enabled");
//							Log.info("Click1Method - Button is not enabled for click");
//
//						} 
//					} else {
//						String status = booleanAssert ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//						reportMessage(status, "Click1Method - CLICK_BUTTON : click button '" + targetInfo + "' failed. Element not present");
//						Log.info("Click1Method - Slow Page Load therefore no element found");
//					}
//				} catch (Exception e) {
//
//					if(i==3)
//					{
//						Log.info(e.getMessage());
//						String status = booleanAssert ? STEP_STATUS_WARN : STEP_STATUS_FAIL;
//						reportMessage(status, "Click1Method - CLICK_BUTTON : click button '" + targetInfo + "' failed. Exception found");
//						Log.info("Click1Method - Slow Page Load");
//					}
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			}
//		}
//
//		public void click1(By elementLocation, String targetInfo, boolean booleanAssert) 
//		{
//			Target Locator = new Target(elementLocation, targetInfo);
//			boolean hasElementPresent;
//			boolean hasElementClickable;
//
//			boolean hasElementVisible ;
//			boolean blnClickButton = false;
//			String STEP_STATUS;
//			if (booleanAssert) {
//				STEP_STATUS = STEP_STATUS_WARN;
//			} else {
//				STEP_STATUS = STEP_STATUS_FAIL;
//
//			}
//			WebElement element;
//			try {
//				long waitTime = getWaitTime();
//				hasElementPresent = waitForLoading(browserFactory.getDriver(), elementLocation, waitTime);
//				hasElementClickable = waitForElementClickable(elementLocation);
//				hasElementVisible =  waitForElementvisibility(elementLocation);
//
//				if (hasElementPresent && hasElementClickable && hasElementVisible) {
//					element = browserFactory.getDriver().findElement(elementLocation);
//					if (element.isEnabled()) {
//						element.click();
//						blnClickButton = true;
//						reportMessage(STEP_STATUS_PASS, "Click1Method - CLICK_BUTTON : clicked '" + targetInfo + "' button successfully in Selenium click");
//					}
//					if (!blnClickButton) {
//						reportMessage(STEP_STATUS_FAIL,
//								"Click1Method - CLICK_BUTTON : click '" + targetInfo + "' button failed. Button not enabled");
//						Log.info("Button is not enabled for click");
//					} 
//
//				}
//				else {
//					reportMessage(STEP_STATUS_FAIL, "Click1Method - CLICK_BUTTON : click button '" + targetInfo + "' failed. Element not present");
//					Log.info("Slow Page Load therefore no element found");
//				}
//			} 
//			catch (Exception e) {
//				Log.info(e.getMessage());
//				reportMessage(STEP_STATUS_WARN,e.toString());
//				reportMessage(STEP_STATUS_WARN, "Click1Method - CLICK_BUTTON : click button '" + targetInfo + "' failed."+e.getMessage()+ "Exception found in Selenium click.");
//				Log.info("Slow Page Load");
//				try
//				{
//					actionsClick(Locator);
//					reportMessage(STEP_STATUS_PASS, "Click1Method - CLICK_BUTTON : clicked '" + targetInfo + "' button successfully in Actions click");
//				}
//				catch(Exception ac)
//				{
//					reportMessage(STEP_STATUS_WARN, ac.toString());
//					reportMessage(STEP_STATUS_WARN, ac.getMessage()+"Exception found in Actions Click");
//					try
//					{
//						javaScriptClick(Locator);
//						reportMessage(STEP_STATUS_PASS, "Click1Method - CLICK_BUTTON : clicked '" + targetInfo + "' button successfully in javaScript click");
//					}
//					catch(Exception js)
//					{
//						reportMessage(STEP_STATUS_WARN, ac.toString());
//						reportMessage(STEP_STATUS_FAIL, ac.getMessage()+"Exception found in Java script Click");
//					}
//				}
//			}
//		}
//
//		private boolean waitForElementvisibility(By locator) {
//			boolean hasElementVisble = false;
//			long waitTime = getWaitTime();
//			WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTime));
//			WebElement webelemenvisible = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//			if (browserFactory.getDriver().findElements(locator).size() > 0 )  {
//
//				if(webelemenvisible!=null) {
//					reportMessage(STEP_STATUS_PASS, "PRESENCE_OF_ELEMENT VISIBILITY	" + locator);
//
//					reportMessage(STEP_STATUS_FIND, "PRESENCE_OF_ELEMENT VISIBILITY	" + locator);
//
//					hasElementVisble = true;
//				}
//			}
//			else {
//				try {
//
//
//
//					WebElement webelemenvisible2 = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//
//
//					if (webelemenvisible2 != null) {
//						reportMessage(STEP_STATUS_PASS, "PRESENCE_OF_ELEMENT VISIBILITY	" + locator);
//
//						reportMessage(STEP_STATUS_FIND, "Verified 2nd time for PRESENCE_OF_ELEMENT VISIBILITY	 '" + locator);
//
//						hasElementVisble = true;
//					}
//				} 
//
//				catch (org.openqa.selenium.TimeoutException te) {
//					reportMessage(STEP_STATUS_WARN,
//							"Wait for Elementvisibility, Element was not visibile by given time seconds "+ WAIT_ELEMENT_MAX);
//					Log.info("Element not visible and  Page Load");
//				}
//			}
//			return hasElementVisble;
//		}
//
//		public void file_Upload(String file_Path)
//		{
//			try {
//				reportMessage(STEP_INFO, "FilePath-"+file_Path);
//				Runtime.getRuntime().exec(Index.pathProject+"\\agadia-test-pahub\\src\\test\\resources\\FileUpload.exe"+" "+file_Path);
//				Thread.sleep(15000);
//			} catch (IOException | InterruptedException e1) {
//				e1.printStackTrace();
//			}
//			/*try {
//				Thread.sleep(5000);
//				StringSelection ss = new StringSelection(file_Path);
//				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
//				Robot robot;
//				robot = new Robot();
//				robot.keyPress(KeyEvent.VK_CONTROL);
//				robot.keyPress(KeyEvent.VK_V);
//				robot.keyRelease(KeyEvent.VK_V);
//				Thread.sleep(3000);
//				robot.keyRelease(KeyEvent.VK_CONTROL);
//				robot.keyPress(KeyEvent.VK_ENTER);
//				robot.keyRelease(KeyEvent.VK_ENTER);
//				Thread.sleep(5000);
//				reportMessage(STEP_STATUS_PASS, "File slected from path: "+file_Path);
//
//			} catch (Exception e) {
//				reportMessage(STEP_STATUS_FAIL, "Exception Found. File not slected from path: "+file_Path);
//			}*/
//		}
//
//		//*****************Added by Zuci for create a copy of a file ************
//		public static String dynamicCreationOfJavaFile(String inputFilePath ,String clientName) {
//			String fileNameOut=null;
//			try {
//				File folder= new File(System.getProperty("user.dir")+"\\agadia-test-pahub\\src\\test\\java\\DynamicGlobal");
//				if (!folder.exists()) 
//					folder.mkdir();
//				File fip= new File(inputFilePath);
//				String outputFilePath = (inputFilePath.replace("_All_", "_"+clientName+"_")).replace("Global", "DynamicGlobal");
//				File fop= new File(outputFilePath);
//				if (!fop.exists()) {
//					FileReader fileReader = new FileReader(fip);
//					FileWriter fileWriter = new FileWriter(fop);
//					BufferedReader bufferReader = new BufferedReader(fileReader);
//					BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
//
//					String text;
//					while( (text=bufferReader.readLine()) != null )
//					{ 
//						if(text.contains("public class")&&text.contains("_All_"))
//						{
//							String putData = text.replaceAll("_All_", "_"+clientName+"_");
//							bufferWriter.write("\n"+putData);
//						}else if(text.contains("package Global")) {
//							String putData = text.replaceAll("package Global", "package DynamicGlobal");
//							bufferWriter.write("\n"+putData);
//						}else
//							bufferWriter.write("\n"+text);
//					}
//					bufferWriter.flush();
//					bufferReader.close();
//					bufferWriter.close();
//					if (fop.exists()) {
//						fileNameOut=fop.getName();
//
//						/*String string = System.getProperty("user.dir")+"\\agadia-test-pahub\\src\\test\\java\\DynamicGlobal\\"; 
//							StringSelection stringSelection = new StringSelection(string);
//							Toolkit.getDefaultToolkit().getSystemClipboard()
//							.setContents(stringSelection, null);
//
//							String command ="explorer.exe Shell:::{2559a1f3-21d7-11d4-bdaf-00c04f60b9f0}";
//							Runtime.getRuntime().exec(command);
//
//							Robot rbt= new Robot();
//							rbt.keyPress(KeyEvent.VK_CONTROL);
//							rbt.keyPress(KeyEvent.VK_V);
//
//							rbt.keyRelease(KeyEvent.VK_V);
//							rbt.keyRelease(KeyEvent.VK_CONTROL);
//
//							rbt.keyPress(KeyEvent.VK_ENTER);
//							rbt.keyRelease(KeyEvent.VK_ENTER);
//
//							rbt.keyPress(KeyEvent.VK_F5);
//							rbt.keyRelease(KeyEvent.VK_F5);
//							Thread.sleep(10000);*/
//					}
//				}
//			} catch (IOException | SecurityException | IllegalArgumentException e) {
//				System.out.println("Exception found in dynamic file creation method");
//				e.printStackTrace();
//			}
//			return fileNameOut;
//
//		}
//
//		//*************************Added by Zuci for delete a file*******
//		public static void dynamicDestructionOfJavaFile() {
//			File folder= new File(System.getProperty("user.dir")+"\\agadia-test-pahub\\src\\test\\java\\DynamicGlobal");
//			if (folder.exists()) {
//				FileUtils fileDelete= new FileUtils();
//				try {
//					fileDelete.deleteDirectory(folder);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				System.out.println("Dynamic folder deleted successfully");
//			}
//		}
//
//
//		public void Post_clickCheck( By LocatorOfElementToClick,String locatorinfo1 ,By PostLocatorOfElement,String locatorinfo2,String PostLocatorofAttributeTag, String PostLocatoOFAttributevalue ) {
//
//
//
//
//			String PostLocatorValue =browserFactory.getDriver().findElement(PostLocatorOfElement).getAttribute(PostLocatorofAttributeTag);
//
//
//			if(PostLocatorValue.equalsIgnoreCase(PostLocatoOFAttributevalue)) {
//				WebElement postclick_locator = browserFactory.getDriver().findElement(LocatorOfElementToClick);
//
//
//				if (postclick_locator.isEnabled()) {
//					postclick_locator.click();
//					reportMessage(STEP_STATUS_FIND, "Post locator identified in Postclick::	 '" + locatorinfo1);
//					reportMessage(STEP_STATUS_PASS, "Previous Click action done successfully agian and PostLocatorValue is ::- " + PostLocatorValue);
//
//
//				}
//
//				else {
//
//					reportMessage(STEP_STATUS_PASS, "The button successfully clicked:: "+PostLocatorValue);
//
//				}
//
//
//			}
//
//			else {
//				reportMessage(STEP_STATUS_PASS, "The button successfully clicked :: "+PostLocatorValue);
//
//
//			}
//
//
//
//
//
//
//		}
//
//		public void Postclick(Target ElementClicked,Target ElementToVerify, String AttrOfElementToVerify, String AttrValueOfElementToVerify  ) {
//			//public void Postclick(Target PostLocatorOfElementToClick,Target PostLocatorOfElement, String PostLocatorofAttributeTag, String PostLocatoOFAttributevalue  ) {
//			Post_clickCheck(ElementClicked.locator,ElementClicked.locatorInfo,ElementToVerify.locator,ElementToVerify.locatorInfo,AttrOfElementToVerify , AttrValueOfElementToVerify);
//
//		}
//
//		public long getDatetime(Date StartTime,Long long1) {
//			Date date = new Date();
//			long total = 0;
//			try {
//				if (long1!=null) 
//					total = (time.parse(time.format(date)).getTime() - (time.parse(time.format(StartTime)).getTime()+long1));
//				else
//					total = time.parse(time.format(date)).getTime() - time.parse(time.format(StartTime)).getTime();
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			if (total<=0) 
//				total=total+86400000;	
//			return total;
//		}
//
//		public void wait_Element_Invisible(Target target) {
//			long start = System.currentTimeMillis();
//			long finish = 0;
//			try {
//				WebDriverWait visiblewait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(getWaitTime()));
//				visiblewait.until(ExpectedConditions.visibilityOfElementLocated(target.locator));
//				WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(),Duration.ofSeconds(getWaitTime()));
//				wait.until(ExpectedConditions.invisibilityOfElementLocated(target.locator));
//				finish = System.currentTimeMillis();
//				String totalTime = String.valueOf(finish - start);
//				reportMessage(STEP_STATUS_PASS, target.locator.toString() + "Element Disappeared");
//				reportMessage(STEP_STATUS_DONE, "Common wait from config : "+String.valueOf(getWaitTime()) +" and time taken to element '"+target.locator.toString()+"' is disappear: "+totalTime+" ms");
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		public String get_Selected_Value_From_ddl(Target target) {
//
//			boolean hasDropdownElementPresent = waitForDropdownElementPresent(target.locator);
//			String defaultItem=null;
//			try {
//				if (hasDropdownElementPresent) {
//
//					Select select = new Select(getElement(target.locator));
//					WebElement option = select.getFirstSelectedOption();
//					defaultItem = option.getText();
//					reportMessage(STEP_STATUS_PASS, "The selected value of Drop Down - "+target.locatorInfo +" is: "+defaultItem);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				reportMessage(STEP_STATUS_FAIL, "Exception in reading value from drop down : "+target.locatorInfo);
//			}
//			return defaultItem;
//		}
//
//		public String[] get_Values_From_ddl(Target target) {
//
//			boolean hasDropdownElementPresent = waitForDropdownElementPresent(target.locator);
//			String ddl_options = " " ;
//			try {
//				if (hasDropdownElementPresent) {
//
//					Select select = new Select(getElement(target.locator));
//					List<WebElement> options = select.getOptions();
//					reportMessage(STEP_STATUS_PASS, "Options Size : "+String.valueOf(options.size()));
//					for(WebElement option : options)
//					{
//						if(option.getText().length()>1)
//							ddl_options = ddl_options+";;;"+option.getText();
//						else {
//							ddl_options = ddl_options+";;;"+option.getAttribute("innerText");
//							reportMessage(STEP_INFO, "DDL Values From InnerText:" +option.getAttribute("innerText"));
//						}
//					}
//					reportMessage(STEP_STATUS_PASS, "Successfully retrived the values form drop down - "+target.locatorInfo);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				reportMessage(STEP_STATUS_WARN, e.toString());
//				reportMessage(STEP_STATUS_FAIL, "Exception in reading value from drop down : "+target.locatorInfo);
//			}
//			reportMessage(STEP_INFO, "DDL Values: " +ddl_options);
//			ExcelUtil.testdata.put(Thread.currentThread().getName() + "." + "dropDownValues",ddl_options.replace("null;;;", ""));
//			return ddl_options.replace(" ;;;", "").split(";;;");
//		}
//
//		public String get_User_login_Number()
//		{
//			String[] login_Id = ExcelUtil.testdata.get(Thread.currentThread().getName()+".Login_Id").split("_");
//			return login_Id[login_Id.length-1];
//		}
//
//		public void apiCustomerConfig(String keyName, String keyValue){
//			synchronized (this) {
//				for (int i = 1; i <=1; i++) {
//					try {
//						String caseName = ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName");
//						FileReader reader = new FileReader(System.getProperty("user.dir")
//								+ "\\agadia-test-pahub\\src\\test\\resources\\CustomConfigLoginCredentials.json");
//						JSONParser jsonParser = new JSONParser();
//						JSONObject jsonObject = (JSONObject) ((JSONObject) jsonParser.parse(reader)).get(
//								ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName").toLowerCase());
//
//						JSONObject obj = new JSONObject();
//						if (ExcelUtil.testdata.containsKey(Thread.currentThread().getName() + "." + "Static_Login_Id"))
//							obj.put("LoginId",
//									ExcelUtil.testdata.get(Thread.currentThread().getName() + ".Static_Login_Id"));
//						else
//							obj.put("LoginId", ExcelUtil.testdata.get(Thread.currentThread().getName() + ".Login_Id"));
//						obj.put("UserName", "PAHubQAAutomationUser1 [Executed_On:"
//								+ postApiData.get(Thread.currentThread().getName() + ".SystemName") + "; Environment:"
//								+ postApiData.get(Thread.currentThread().getName() + ".Environment").toString()
//								+ "; TestCaseID:"
//								+ ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName").split("_")[0]
//										+ "]");
//						obj.put("CustomerName", jsonObject.get("apiCustomerName"));
//						obj.put("KeyName", keyName);
//						obj.put("KeyValue", keyValue);
//						System.out.println("API Customer Config String--" + obj);
//						String get_url = null;
//
//						switch (postApiData.get(Thread.currentThread().getName() + ".Environment").toString()
//								.toLowerCase()) {
//								case "qa": case "uiux_qa":
//									get_url = Index.prop.getProperty("customerConfig.url.qa").trim();
//									break;
//								case "auto_qa":
//									get_url = Index.prop.getProperty("customerConfig.url.autoqa").trim();
//									break;
//								case "obsolete_auto_qa":
//									get_url = Index.prop.getProperty("customerConfig.url.obsolete_auto_qa").trim();
//									break;
//								case "obsolete_qa":
//									get_url = Index.prop.getProperty("customerConfig.url.obsolete_qa_url").trim();
//									break;
//								case "qapoc":
//									get_url = Index.prop.getProperty("customerConfig.url.qapoc").trim();
//									break;
//
//								default:
//									System.out.println("Environment selection for API_Url switch case came to default mode");
//									break;
//						}
//
//						URL url_post = new URL(get_url);
//						HttpURLConnection urlConnection_post = (HttpURLConnection) url_post.openConnection();
//						urlConnection_post.setRequestMethod("POST");
//						urlConnection_post.setRequestProperty("Content-Type", "application/json");
//						urlConnection_post.setDoOutput(true);
//						byte[] byteBody = obj.toString().getBytes();
//						OutputStream outputStream = urlConnection_post.getOutputStream();
//						outputStream.write(byteBody);
//						int response_out = urlConnection_post.getResponseCode();
//						String response_msg_out = urlConnection_post.getResponseMessage();
//						System.out.println("Response Code= " + response_out);
//						System.out.println("Response Message= " + response_msg_out);
//						InputStream inputstream = urlConnection_post.getInputStream();
//						InputStreamReader inputStreamReader = new InputStreamReader(inputstream);
//						BufferedReader buffer = new BufferedReader(inputStreamReader);
//						String line;
//						StringBuffer strbuffer = new StringBuffer();
//						while ((line = buffer.readLine()) != null) {
//							strbuffer.append(line);
//						}
//						System.out.println("Response Body at iteration -"+i+" =" + strbuffer);
//						if (strbuffer.toString().contains("Success")) {
//							/*commented and may required in future*/
//							/*if (TestngEmailableReport.Customer_Config_map.containsKey(caseName)) {
//								String value=TestngEmailableReport.Customer_Config_map.get(caseName);
//								TestngEmailableReport.Customer_Config_map.replace(caseName,
//										value + "\n" +STEP_INFO+":"+ keyName + ":"
//												+ keyValue+"-Success at iteration-"+i);
//							}
//							else
//								TestngEmailableReport.Customer_Config_map.put(caseName, STEP_INFO+":"+keyName + ":" + keyValue+"-Success at iteration-"+i);*/
//							break;
//						} else if(i==1){
//							if (TestngEmailableReport.Customer_Config_map.containsKey(caseName)) {
//								String value=TestngEmailableReport.Customer_Config_map.get(caseName);
//								TestngEmailableReport.Customer_Config_map.replace(caseName,
//										value+ " ; "  +STEP_STATUS_WARN+": "+strbuffer+" for -"+ keyName + ":"+keyValue);
//							}
//							else
//								TestngEmailableReport.Customer_Config_map.put(caseName, STEP_STATUS_WARN+": "+strbuffer+" for -"+ keyName + ":"+keyValue);
//						}
//					} catch (Exception e) {
//						if (i==1) {
//							if (TestngEmailableReport.Customer_Config_map
//									.containsKey(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"))) {
//								String value = TestngEmailableReport.Customer_Config_map
//										.get(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"));
//								TestngEmailableReport.Customer_Config_map.replace(
//										ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"),
//										value + " ; " + STEP_STATUS_WARN +": "+e.getMessage() +" for -" + keyName + ":" + keyValue);
//							} else
//								TestngEmailableReport.Customer_Config_map.put(
//										ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"),
//										STEP_STATUS_WARN +": "+ e.getMessage()+" for- " + keyName + ":" + keyValue);
//						}
//						System.out.println("Unable to complete CustomerConfig Changes via API at iteration-"+i+"--"+e.getMessage());
//					} 
//				}
//			}
//		}
//
//		public void download_File(ITestContext cname, String file_Path, Target target, int tab_occurrence)
//		{
//			try {
//				Files.deleteIfExists(Paths.get(file_Path));
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			WaitPageLoad(target.locator);
//			actionsClick(target);
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(TestngEmailableReport.browser_map.get(cname.getName()).equalsIgnoreCase("IE")||
//					TestngEmailableReport.browser_map.get(cname.getName()).equalsIgnoreCase("Edge(with IE mode ON)")) {
//				Robot robot;
//				try {
//					robot = new Robot();
//					for(int i=1; i<=tab_occurrence; i++)
//					{
//						robot.keyPress(KeyEvent.VK_TAB);
//						Thread.sleep(1000);
//					}
//					Thread.sleep(1000);	
//					robot.keyPress(KeyEvent.VK_ENTER);
//					int i=0;
//					while(!Files.exists(Paths.get(file_Path))&&i<25)
//					{
//						Thread.sleep(1000);
//						i++;
//					}
//					reportMessage(STEP_INFO, "File Download wait time :: "+String.valueOf(i)+"ms");
//				} catch (AWTException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(Files.exists(Paths.get(file_Path)))
//				reportMessage(STEP_STATUS_PASS, "File Download completed :: "+file_Path);
//			else
//				reportMessage(STEP_STATUS_FAIL, "File Download Failed :: "+file_Path);
//		}
//
//		public String getAttribute_value(Target target) {
//			String TextToReturn = null;
//			boolean hasElementPresent = waitForElementPresent(target);
//			reportMessage(STEP_STATUS_FIND, " Element '" + target.locatorInfo + "Present");
//			if (hasElementPresent) {
//
//				List<WebElement> elements = this.browserFactory.getDriver().findElements(target.locator);
//				int elementsSize = elements.size();
//				if (elementsSize > 1) {
//
//					reportMessage(STEP_STATUS_WARN,
//							"Multiple Elements Found '" + target.locatorInfo + "', now on first element");
//				} else {
//					if (elementsSize == 1) {
//						TextToReturn = browserFactory.getDriver().findElement(target.locator).getAttribute("value");
//						// if(TextToReturn.isEmpty())
//						// {TextToReturn=browserFactory.getDriver().findElement(target.locator).getAttribute("textContent");}
//						reportMessage(STEP_STATUS_PASS, "Elements Found '" + TextToReturn);
//					}
//				}
//				// scrollToElement(elements.get(0));
//				// elements.get(0).sendKeys(text);
//
//			} else {
//				reportMessage(STEP_STATUS_FAIL, "Element Not Found '" + target.locatorInfo + "'");
//			}
//			return TextToReturn;
//
//		}
//
//		public boolean is_Enable(Target target)
//		{
//			WebElement element = browserFactory.getDriver().findElement(target.locator);
//			boolean state = element.isEnabled();
//			reportMessage(STEP_STATUS_PASS, "Element '" + target.locatorInfo + "'"+" isEnable : "+state);
//			return state;
//		}
//
//		public void queManagerCalendarPicker(int day, String command) {
//			DateTimeFormatter startdate = DateTimeFormatter.ofPattern("d/MM/yyyy");
//			LocalDateTime now1;
//			if(command.equalsIgnoreCase("plus"))
//				now1 = LocalDateTime.now().plusDays(day);
//			else
//				now1 = LocalDateTime.now().minusDays(day);
//			String currentdate1 = startdate.format(now1);
//			String[] arrOfStr1 = currentdate1.split("/");
//			String currentDate1 = arrOfStr1[0];
//			click(By.linkText(currentDate1));
//		}
//
//		public boolean isAlertPresent(){
//			boolean foundAlert = false;
//			WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(30));
//			try {
//				wait.until(ExpectedConditions.alertIsPresent());
//				foundAlert = true;
//			} catch (org.openqa.selenium.TimeoutException eTO) {
//				foundAlert = false;
//			}
//			return foundAlert;
//		}
//
//		public Boolean validateElementVisible(Target target){
//			if (verifyElementVisible(target,true)) {
//				reportMessage(STEP_STATUS_PASS, "Element Available-"+target.locatorInfo);
//				return true;
//			}
//			else {
//				reportMessage(STEP_STATUS_FAIL, "Element not Available-"+target.locatorInfo);
//				return false;
//			}
//
//		}
//
//		public void download_file(String folder_path, String file_path, String contains_name, Target target)
//		{
//			try { 
//				int iteration = 0;
//				File files = new File(folder_path); 
//				for(File file: files.listFiles()) {
//					if(file.getName().toLowerCase().contains(contains_name.toLowerCase())) 
//						file.delete();
//					while(file.exists()&&iteration<6)
//					{
//						Thread.sleep(2000);
//						iteration++;
//					}
//				}
//				WaitPageLoad(target.locator);
//				javaScriptClick(target);
//				fileSaveForEdgeWithIE();
//				File file = new File(file_path);
//				iteration = 0;
//				while(!file.exists()&&iteration<6)
//				{
//					Thread.sleep(10000);
//					iteration++;
//				}
//				if(file.exists()) {
//					reportMessage(STEP_STATUS_PASS, "File download in path : "+file_path);
//					ExcelUtil.testdata.put(Thread.currentThread().getName()+"."+contains_name,"true");
//				}else if(!ExcelUtil.testdata.get(Thread.currentThread().getName()+".testName").contains("77000"))
//					reportMessage(STEP_STATUS_FAIL, "File download Failed : "+file_path);
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				reportMessage(STEP_STATUS_FAIL, "Exception found in file downlod");
//			}
//		}
//
//		public void actionsType(Target target, String value)
//		{
//			WebElement element = getElement(target.locator);
//			Actions act = new Actions(browserFactory.getDriver());
//			act.moveToElement(element).build().perform();
//			act.click(element).build().perform();
//			element.clear();
//			element.sendKeys(value);
//			reportMessage(STEP_STATUS_PASS, "Entered text "+value);
//		}
//		public void FileDownload_KeyboardActions(String file_Path,int iteration) {
//
//
//			try {
//				Files.deleteIfExists(Paths.get(file_Path));
//				reportMessage(STEP_INFO, "FilePath deleted");
//				Robot rbt= new Robot();
//				for (int i = 0; i < iteration; i++) {
//					rbt.keyPress(KeyEvent.VK_TAB);
//					rbt.keyRelease(KeyEvent.VK_TAB);
//					System.out.println("Tab count: "+i);
//					Thread.sleep(500);
//				}
//				rbt.keyPress(KeyEvent.VK_ENTER);
//				rbt.keyRelease(KeyEvent.VK_ENTER);
//				Thread.sleep(10000);
//				rbt.keyPress(KeyEvent.VK_ENTER);
//				rbt.keyRelease(KeyEvent.VK_ENTER);
//				System.out.println("After enter pressed");
//				Thread.sleep(50000);
//				if(Files.exists(Paths.get(file_Path)))
//					reportMessage(STEP_STATUS_PASS, "File Download completed :: "+file_Path);
//				else
//					reportMessage(STEP_STATUS_FAIL, "File Download Failed :: "+file_Path);
//
//			} catch (AWTException | InterruptedException | IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//		public void get_EOC_Details(String req_Colms)
//		{
//			String EOC_Id = TestngEmailableReport.eocid_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"));
//			//944605
//			/*	String jdbc_servername = Index.prop.getProperty("sheet.Jdbc_servername.name").trim();
//			String jdbc_username = Index.prop.getProperty("sheet.Jdbc_username.name").trim();
//			String jdbc_password = Encrypssio.DE_decrypt(Encrypssio.DE_decrypt(Index.prop.getProperty("sheet.Jdbc_password.name").trim()));
//			//String jdbc_dbname = Index.prop.getProperty("sheet.Jdbc_dbname.name").trim();
//			//String jdbc_integratedsecurity=Index.prop.getProperty("sheet.Jdbc_integratedsecurity.value").trim();
//			 */		
//			try {
//				Connection con=JdbcSQLServerConnection.JdbcServerConnection();/*DriverManager.getConnection(jdbc_servername,jdbc_username,jdbc_password);*/
//				reportMessage(STEP_INFO,"DB Connected");
//				if (con != null) {
//					String query = "select * from hcsevent_eligible_details_Rad (nolock)  hedr\r\n" + 
//							"inner join hcsevent he on he.subjectid = hedr.eligibledetailsid \r\n" + 
//							"where he.eocid ="+EOC_Id;
//					reportMessage(STEP_INFO, "Query :: "+query);
//					Statement stat = con.createStatement();
//					ResultSet rs = stat.executeQuery(query);
//					reportMessage(STEP_INFO,"Query Executed");
//					while(rs.next())
//					{
//						int i=1;
//						if(req_Colms.equalsIgnoreCase("*"))
//						{
//							while(i<=rs.getMetaData().getColumnCount())
//							{
//								String colName = rs.getMetaData().getColumnName(i);
//								String value = rs.getString(colName);
//								dbEOCDetails.put(colName, value);
//								i++;
//							}
//						}
//						else
//						{
//							String[] colms = req_Colms.split(",");
//
//							for(String col:colms)
//								dbEOCDetails.put(Thread.currentThread().getName() + "."+col, rs.getString(col));
//						}
//					}
//					stat.close();
//					con.close();
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		public void closeAllWindows() {            
//			try {
//				Set<String> windows = browserFactory.getDriver().getWindowHandles();
//				Iterator<String> iter = windows.iterator();
//				String[] winNames=new String[windows.size()];
//				int i=0;
//				while (iter.hasNext()) {
//					winNames[i]=iter.next();
//					i++;
//				}
//				System.out.println("No of Current Windows:"+winNames.length);
//				if(winNames.length >=1) {
//					for(i = winNames.length; i >=1; i--) {
//						browserFactory.getDriver().switchTo().window(winNames[i - 1]);
//						browserFactory.getDriver().close();
//					}
//				}
//			}
//			catch(Exception e){         
//				e.printStackTrace();
//			}
//
//			if (browserFactory.getDriver()!=null) {
//				static_wait(3);
//				browserFactory.getDriver().quit();
//			}
//		}
//
//		public void static_wait(int waitTime)
//		{
//			for(int i=1;i<=waitTime;i++)
//			{
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		public List<String> getAllValuesFromDDL(By byValue) {
//			reportMessage(STEP_INFO, "List all options inside dropdown");
//			List<String> ddlList = new ArrayList<>();
//			Select sltOptions=new Select(browserFactory.getDriver().findElement(byValue));
//			for (WebElement string : sltOptions.getOptions()) {
//				ddlList.add(string.getText());
//			}
//			return ddlList; 
//		}
//
//		public void Calender_picker(String target_year,String target_month,String target_date) {
//			Date date = new Date();
//			DateFormat df = new SimpleDateFormat("d/MMMM/yyyy");
//			df.setTimeZone(TimeZone.getTimeZone("UTC-5"));
//			String currentdate=df.format(date);
//			String[] arrOfStr = currentdate.split("/"); 
//			String currentDate=arrOfStr[0];
//			String currentmonth=arrOfStr[1];
//			String currentyear=arrOfStr[2];
//			selectByVisibleText(JSONFileReader.getLocator(target_year),currentyear);
//			selectByVisibleText(JSONFileReader.getLocator(target_month),currentmonth);
//			click(JSONFileReader.getDynamicLocator(target_date,currentDate));	
//		}
//
//		public void getEocId() {
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			TestngEmailableReport.eocid_map.put(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"), browserFactory.getDriver().findElement(By.id("ctl00_EocUsercontrol_lblEOCID")).getText());
//			reportMessage(STEP_INFO, "EOCid stored ="+TestngEmailableReport.eocid_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName")));
//		}
//
//		public String getSlectedOption(By locator) {
//			Select select = new Select(browserFactory.getDriver().findElement(locator));
//			WebElement option = select.getFirstSelectedOption();
//			reportMessage(STEP_INFO, "The selected value of the dropdown is:"+select.getFirstSelectedOption());
//			return option.getText();	
//		}
//
//		public void getEOCDetailsFromHcsEventDrugs(String req_Colms)
//		{
//			String EOCs = TestngEmailableReport.eocid_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"));
//			/*String jdbc_servername = Index.prop.getProperty("sheet.Jdbc_servername.name").trim();
//			String jdbc_username = Index.prop.getProperty("sheet.Jdbc_username.name").trim();
//			String jdbc_password = Encrypssio.DE_decrypt(Index.prop.getProperty("sheet.Jdbc_password.name").trim());
//			String jdbc_dbname = Index.prop.getProperty("sheet.Jdbc_dbname.name").trim();
//			String jdbc_integratedsecurity=Index.prop.getProperty("sheet.Jdbc_integratedsecurity.value").trim();
//			reportMessage(STEP_INFO,"DB Connected");
//			try {
//				Connection con=DriverManager.getConnection(jdbc_servername,jdbc_username,jdbc_password);*/
//			try {
//				Connection con=JdbcSQLServerConnection.JdbcServerConnection();/*DriverManager.getConnection(jdbc_servername,jdbc_username,jdbc_password);*/
//				reportMessage(STEP_INFO,"DB Connected");
//				if (con != null) {
//					String query = "select "+req_Colms+" from HcsEventDrugs where EOCID in ("+EOCs+")";
//					reportMessage(STEP_INFO, "Query :: "+query);
//					Statement stat = con.createStatement();
//					ResultSet rs = stat.executeQuery(query);
//					reportMessage(STEP_INFO,"Query Executed");
//
//					while(rs.next())
//					{
//						int i=1;
//						if(req_Colms.equalsIgnoreCase("*"))
//						{
//							while(i<=rs.getMetaData().getColumnCount())
//							{
//								List<String> dbValues = new ArrayList<>();
//								String colName = rs.getMetaData().getColumnName(i);
//								String value = rs.getString(colName);
//								if(!dbDrugDetails.containsKey(Thread.currentThread().getName() + "."+colName))
//									dbDrugDetails.put(Thread.currentThread().getName() + "."+colName, value);
//								else
//									dbDrugDetails.replace(dbDrugDetails.get(Thread.currentThread().getName() + "."+colName),dbDrugDetails.get(Thread.currentThread().getName() + "."+colName)+"#delimeter#"+value);
//								i++;
//							}
//						}
//						else
//						{
//							String[] colms = req_Colms.split(",");
//
//							for(String col:colms)
//							{
//								if(!dbDrugDetails.containsKey(Thread.currentThread().getName() + "."+col))
//									dbDrugDetails.put(Thread.currentThread().getName() + "."+col, rs.getString(col));
//								else
//									dbDrugDetails.replace(Thread.currentThread().getName() + "."+col,dbDrugDetails.get(Thread.currentThread().getName() + "."+col)+"#delimeter#"+rs.getString(col));
//							}
//						}
//					}
//					stat.close();
//					con.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		public void getDataFromServiceagentlog(String req_Colms)
//		{
//			String EOCs = TestngEmailableReport.eocid_map.get(ExcelUtil.testdata.get(Thread.currentThread().getName() + ".testName"));
//			/*String jdbc_servername = Index.prop.getProperty("sheet.Jdbc_servername.name").trim();
//			String jdbc_username = Index.prop.getProperty("sheet.Jdbc_username.name").trim();
//			String jdbc_password = Encrypssio.DE_decrypt(Index.prop.getProperty("sheet.Jdbc_password.name").trim());
//			String jdbc_dbname = Index.prop.getProperty("sheet.Jdbc_dbname.name").trim();
//			String jdbc_integratedsecurity=Index.prop.getProperty("sheet.Jdbc_integratedsecurity.value").trim();
//
//			reportMessage(STEP_INFO,"DB Connected");
//			try {
//				Connection con=DriverManager.getConnection(jdbc_servername,jdbc_username,jdbc_password);*/
//			try {
//				LinkedHashMap<String,String> dbLogDetails = new LinkedHashMap<>();
//				Connection con=JdbcSQLServerConnection.JdbcServerConnection();/*DriverManager.getConnection(jdbc_servername,jdbc_username,jdbc_password);*/
//				reportMessage(STEP_INFO,"DB Connected");
//				if (con != null) {
//					String query = "select "+req_Colms+" from Serviceagentlog(NOLOCK) "
//							+ "where requestresponsekey like 'AgentEnvisionClaimTest%'and"
//							+ " LogMessage like '%CustomerRequest%'and eocid in ("+EOCs+") order by 1 desc";
//					reportMessage(STEP_INFO, "Query :: "+query);
//					Statement stat = con.createStatement();
//					ResultSet rs = stat.executeQuery(query);
//					reportMessage(STEP_INFO,"Query Executed");
//					while(rs.next())
//					{
//						int i=1;
//						if(req_Colms.equalsIgnoreCase("*"))
//						{
//							while(i<=rs.getMetaData().getColumnCount())
//							{
//								List<String> dbValues = new ArrayList<>();
//								String colName = rs.getMetaData().getColumnName(i);
//								String value = rs.getString(colName);
//								if(!dbLogDetails.containsKey(Thread.currentThread().getName() + "."+colName))
//									dbLogDetails.put(Thread.currentThread().getName() + "."+colName, value);
//								else
//									dbLogDetails.replace(dbLogDetails.get(Thread.currentThread().getName() + "."+colName),dbLogDetails.get(Thread.currentThread().getName() + "."+colName)+"#delimeter#"+value);
//								i++;
//							}
//						}
//						else
//						{
//							String[] colms = req_Colms.split(",");
//
//							for(String col:colms)
//							{
//								if(!dbLogDetails.containsKey(Thread.currentThread().getName() + "."+col))
//									dbLogDetails.put(Thread.currentThread().getName() + "."+col, rs.getString(col));
//								else
//									dbLogDetails.replace(Thread.currentThread().getName() + "."+col,dbLogDetails.get(Thread.currentThread().getName() + "."+col)+"#delimeter#"+rs.getString(col));
//							}
//						}
//					}
//					stat.close();
//					con.close();
//				}
//				ExcelUtil.db_Data.put(Thread.currentThread().getName()+".Serviceagentlogs",dbLogDetails);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		public void DropDownSelect_Click(Target target,String QCMS_Value) {
//
//			ExcelUtil.testdata.put(Thread.currentThread().getName() + ".ProductMatch", "false");
//
//			List<WebElement> elements = browserFactory.getDriver().findElements(target.locator);
//			reportMessage(STEP_INFO,"Number of products available in the Dropdown = "+ String.valueOf(elements.size()));
//
//			for (WebElement webElement : elements) {
//				if (webElement.getText().contains(QCMS_Value)) { 
//					reportMessage(STEP_INFO,"Product to be Selected = " +webElement.getText());
//					JavascriptExecutor js = ((JavascriptExecutor) browserFactory.getDriver());
//					js.executeScript("arguments[0].scrollIntoView(true);",webElement);
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					String RecipientName_Selected=webElement.getText();
//					Actions act = new Actions(browserFactory.getDriver());
//					act.moveToElement(webElement).build().perform();
//					act.click(webElement).build().perform();
//					loadProgress(JSONFileReader.getLocator("Processing").locator);
//					reportMessage(STEP_STATUS_PASS, "Product given from the QCMS =  " + QCMS_Value.toString());
//					reportMessage(STEP_STATUS_PASS, "Product selected is =  "+RecipientName_Selected);
//					ExcelUtil.testdata.replace(Thread.currentThread().getName() + ".ProductMatch", "true");
//					break;
//				}
//			}
//			if((ExcelUtil.testdata.get(Thread.currentThread().getName() + ".ProductMatch").equalsIgnoreCase("false")))
//				reportMessage(STEP_STATUS_FAIL, "Product to be selected -  "+QCMS_Value.toString() +" - is not available");
//		}
//
//		public String get_CurrentUrl()
//		{
//			return browserFactory.getDriver().getCurrentUrl();
//		}
//
//		public static String getCurrentdate()
//		{
//			DateFormat customFormat = new SimpleDateFormat("MMddyyyy");
//			Date currentDate = new Date();
//			return customFormat.format(currentDate);
//		}
//
//		public static String getCurrentHour()
//		{
//			DateFormat customFormat = new SimpleDateFormat("HH");
//			Calendar cal = Calendar.getInstance();
//			Date currentDate = new Date();
//			return customFormat.format(currentDate);
//		}
//
//		public static String getCurrentMM(int old)
//		{
//			DateFormat customFormat = new SimpleDateFormat("mm");
//			Date currentDate = new Date();
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(new Date());
//			cal.add(Calendar.MINUTE, old);
//			String olddate = customFormat.format(cal.getTime());
//			return olddate;
//		}
//		public static String getOlddate(int old){
//			DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(new Date());
//			cal.add(Calendar.DATE, -old);
//			String olddate = dateFormat.format(cal.getTime());
//			return olddate;
//		}
//		public static String getAddhour(int old){
//			DateFormat dateFormat = new SimpleDateFormat("HH");
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(new Date());
//			cal.add(Calendar.HOUR, old);
//			String olddate = dateFormat.format(cal.getTime());
//			return olddate;
//		}
//
//		public boolean localXMLCheck_UAT() {
//			synchronized (BaseTest.class) {
//				String LocalXMLConfig;
//				if (System.getProperty("LocalXML.ApplicableClients_Pre-UAT") != null) 
//					LocalXMLConfig=System.getProperty("LocalXML.ApplicableClients_Pre-UAT").trim();
//				else 
//					LocalXMLConfig=Index.prop.getProperty("LocalXML.ApplicableClients_Pre-UAT").trim();
//
//				boolean status = false;
//				if (!postApiData.get(Thread.currentThread().getName() + ".Environment").toString()
//						.equalsIgnoreCase("pre-uat")) {
//					status = true;
//					reportMessage(STEP_INFO,
//							"Execution environment is-" + postApiData.get(Thread.currentThread().getName() + ".Environment")
//							+ "  So, LocalXML Flow is Approved");
//					TestngEmailableReport.ManuallyAddedPatient_map.replace(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName"),"L");
//				} else if (postApiData.get(Thread.currentThread().getName() + ".Environment").toString()
//						.equalsIgnoreCase("pre-uat")
//						&& LocalXMLConfig.equalsIgnoreCase("All")) {
//					status = true;
//					reportMessage(STEP_INFO, "Execution environment is-"
//							+ postApiData.get(Thread.currentThread().getName() + ".Environment")
//							+ " but LocalXML.ApplicableClients_Pre-UAT flag is- All. So, LocalXML Flow is Approved");
//					TestngEmailableReport.ManuallyAddedPatient_map.replace(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName"),"L");
//				} else if (postApiData.get(Thread.currentThread().getName() + ".Environment").toString().equalsIgnoreCase(
//						"pre-uat") && LocalXMLConfig.equalsIgnoreCase("NA")) {
//					reportMessage(STEP_STATUS_WARN, "Execution environment is-"
//							+ postApiData.get(Thread.currentThread().getName() + ".Environment")
//							+ " and LocalXML.ApplicableClients_Pre-UAT flag is- NA. So, LocalXML Flow is Restricted");
//					Assert.fail("Execution environment is-"
//							+ postApiData.get(Thread.currentThread().getName() + ".Environment")
//							+ " and LocalXML.ApplicableClients_Pre-UAT flag is- NA. So, LocalXML Flow is Restricted");
//				}
//
//				else if (postApiData.get(Thread.currentThread().getName() + ".Environment").toString().equalsIgnoreCase(
//						"pre-uat") && LocalXMLConfig!=null) {
//					boolean insideStatus = false;
//					String[] cusList = LocalXMLConfig.split(",");
//					for (int i = 0; i < cusList.length; i++) {
//						if (cusList[i].trim().equalsIgnoreCase(
//								ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName").trim())) {
//							insideStatus = true;
//							break;
//						}
//					}
//					if (insideStatus) {
//						status = true;
//						reportMessage(STEP_INFO,
//								"Execution environment is-"
//										+ postApiData.get(Thread.currentThread().getName() + ".Environment")
//										+ " but LocalXML.ApplicableClients_Pre-UAT flag contains- "
//										+ ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName")
//										+ ". So, LocalXML Flow is Approved");
//						TestngEmailableReport.ManuallyAddedPatient_map.replace(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName"),"L");
//					} else {
//						reportMessage(STEP_STATUS_WARN,
//								"Execution environment is-"
//										+ postApiData.get(Thread.currentThread().getName() + ".Environment")
//										+ " and LocalXML.ApplicableClients_Pre-UAT flag not contains- "
//										+ ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName")
//										+ ". So, LocalXML Flow is Restricted");
//						Assert.fail("Execution environment is-"
//								+ postApiData.get(Thread.currentThread().getName() + ".Environment")
//								+ " and LocalXML.ApplicableClients_Pre-UAT flag not contains- "
//								+ ExcelUtil.testdata.get(Thread.currentThread().getName() + ".customerName")
//								+ ". So, LocalXML Flow is Restricted");
//					}
//
//					status = insideStatus;
//				}else
//					reportMessage(STEP_STATUS_FAIL, "LocalXML_UAT conditions not satisfied.");
//				return status;
//			}
//		}
//
//		public boolean manuallyAddedPatientCheck_UAT() {
//			boolean status = false;
//			if (!postApiData.get(Thread.currentThread().getName()+".Environment").toString().equalsIgnoreCase("pre-uat")) {
//				status=true;
//				reportMessage(STEP_INFO, "Execution environment is-"+postApiData.get(Thread.currentThread().getName()+".Environment")+" So, ManuallyAddedPatient Flow is Approved");
//				TestngEmailableReport.ManuallyAddedPatient_map.replace(ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"testName"),"M");
//			}
//			else {
//				reportMessage(STEP_STATUS_WARN, "Execution environment is-"+postApiData.get(Thread.currentThread().getName()+".Environment")+" So, ManuallyAddedPatient Flow is Restricted");
//				Assert.fail("Execution environment is-"+postApiData.get(Thread.currentThread().getName()+".Environment")+" So, ManuallyAddedPatient Flow is Restricted");
//			}
//
//			return status;
//		}
//
//		public void waitUrlLoad(String url)
//		{
//			long waitTimeSeconds = 0;
//			try
//			{
//				String jenkins_pageload_wait = System.getProperty("PageLoadWait");// To read a jenkins execution PageLoad Wait
//				if(jenkins_pageload_wait==null) {
//					waitTimeSeconds = Long.parseLong(Index.prop.getProperty("PageLoadWait").trim());
//				}
//				else
//					waitTimeSeconds=Long.parseLong(System.getProperty("PageLoadWait"));
//				WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTimeSeconds));
//				Boolean we = wait.until(ExpectedConditions.urlContains(url));
//			} catch (Exception wde) {
//				reportMessage(STEP_STATUS_WARN, "Page load wait, url Not Loaded in given time seconds " + waitTimeSeconds+" seconds");
//				Log.info("log : "+wde.getMessage());
//			} 
//		}
//
//		public void javaScriptClick(String script)
//		{
//			//WebElement element =browserFactory.getDriver().findElement(target.locator);
//			System.out.println("JS Script : "+script);
//			System.out.println(browserFactory.getDriver().getCurrentUrl());
//			JavascriptExecutor executor = (JavascriptExecutor)browserFactory.getDriver();
//			WebElement element = (WebElement)((JavascriptExecutor)browserFactory.getDriver()).executeScript("return document.querySelector('#viewer').shadowRoot.querySelector('#toolbar').shadowRoot.querySelector('#downloads').shadowRoot.querySelector('#download').shadowRoot.querySelector('#icon > iron-icon')");
//			//	executor.executeScript("return document.querySelector(\"#viewer\")");
//			element.click();
//			//executor.executeScript("arguments[0].click();", element);
//			reportMessage(STEP_STATUS_PASS, "Clicked "+script);
//		}
//
//		//For future use
//		/*public void SupportDocumentDownload(String script,Target target, String fileName)
//		{
//			System.out.println("window size : "+browserFactory.getDriver().getWindowHandles().size());
//			browserFactory.getDriver().switchTo().frame(getElement(JSONFileReader.getLocator("iframe_pdfViwer").locator));
//			String pdfSrc = getAttribute(JSONFileReader.getLocator("iframe_pdfViwer"), "src");
//
//			getURLOnBrowser(browserFactory.getDriver(), pdfSrc);
//			browserFactory.getDriver().switchTo().defaultContent();
//			String newhandle2 = switchToWindowsPopup(browserFactory.getDriver(), 30);
//			javaScriptClick("document.querySelector('pdf-viewer').shadowRoot.querySelector('viewer-toolbar').shadowRoot.querySelector('viewer-download-controls').shadowRoot.getElementById('download')");
//			switchToMainWindow(browserFactory.getDriver(), newhandle2, 10);
//			//file_Upload("pdfjq.pdf");
//			static_wait(10);
//			//javaScriptClick("document.querySelector('pdf-viewer').shadowRoot.querySelector('viewer-toolbar').shadowRoot.querySelector('viewer-download-controls').shadowRoot.getElementById('download')");
//			static_wait(50);
//		}*/
//
//		public void OpenNewTab() {
//			browserFactory.getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");
//		}
//
//		public void CloseCurrentWindow() {
//			browserFactory.getDriver().close();
//		}
//
//		public void TABTypeText(Target elementTarget, String text) {
//			reportMessage(STEP_STATUS_PASS, "Text from data '"+text);
//			browserFactory.getDriver().findElement(elementTarget.locator).sendKeys(Keys.TAB);
//			static_wait(1);
//			browserFactory.getDriver().findElement(elementTarget.locator).sendKeys(text);
//			reportMessage(STEP_STATUS_PASS, "Entered text '"+text);
//		}
//
//		public void javaScriptType(Target elementTarget, String text)
//		{
//			if(verifyElementVisible(elementTarget,true))
//			{
//				WebElement element =browserFactory.getDriver().findElement(elementTarget.locator);
//				element.clear();
//				JavascriptExecutor executor = (JavascriptExecutor)browserFactory.getDriver();
//				executor.executeScript("arguments[0].value='"+text+"';", element);
//				reportMessage(STEP_STATUS_PASS, "Entered text "+text);
//			}
//			else
//				reportMessage(STEP_STATUS_FAIL, elementTarget.locator + " Element Not visible");
//		}
//
//		public int getCoOrdinate(Target target, String Coordinate)
//		{
//			WebElement element = browserFactory.getDriver().findElement(target.locator);
//			int position = 0;
//			if(Coordinate.equalsIgnoreCase("X"))
//				position = element.getLocation().getX();
//			else
//				position = element.getLocation().getY();
//			reportMessage(STEP_STATUS_PASS, "Element '" + target.locatorInfo + "Coordinate "+Coordinate+ "'"+"  is "+position);
//			return position;
//		}
//
//		public void TriggerAPI(ITestContext cname) throws IOException {
//			Object obj= objectCreationForExecuteTestCase(TestngEmailableReport.convertTimeToString(getDatetime(cname.getStartDate(), TestngEmailableReport.threadWaitforActiveUser.get(cname.getName()))));
//			synchronized (obj) {
//				System.out.println("API Execute Test Case String-- "+obj);
//				String exeStatus=System.getProperty("QcmsExecuteTestCaseStatus");
//				boolean status;
//
//				if (exeStatus != null) 
//					status=exeStatus.trim().equalsIgnoreCase("Yes");
//				else 
//					status=Index.prop.getProperty("Qcms.ExecuteTestCaseStatus").trim().equalsIgnoreCase("Yes");
//
//				if(status)
//					apiExecuteTestCase(obj);
//
//				if (qcmsResponseData.get(Thread.currentThread().getName() + ".RelatedTestCases") != null) {
//					if (!qcmsResponseData.get(Thread.currentThread().getName() + ".RelatedTestCases")
//							.equalsIgnoreCase("null")) {
//						for (String iterable_element : qcmsResponseData
//								.get(Thread.currentThread().getName() + ".RelatedTestCases").split(",")) {
//							JSONObject objReplace = (JSONObject) obj;
//							objReplace.replace("TestCaseId", iterable_element.toString().trim());
//
//							//Tracking the status in general bucket(Skipped as Parent TC Failed). But to be precise it should be in  "Skipped as Parent TC Skipped"
//							if(!postApiData.containsKey(Thread.currentThread().getName()+".TestResult"))
//								objReplace.replace("TestResult", "Skipped as Parent TC Failed");					
//							else {
//								if (relatedTestCaseAPI.containsValue(iterable_element.toString())) {
//									objReplace.replace("TestResult", relatedTestCaseAPIResult
//											.get(Thread.currentThread().getName() + "." + iterable_element.toString().trim()));
//									objReplace.replace("ExecuteComments", relatedTestCaseAPIResult
//											.get(Thread.currentThread().getName() + "." + iterable_element.toString().trim()+".ExecuteComments"));
//									JSONArray replaceAttachment = (JSONArray) objReplace.get("AttachmentFile");
//									for (int i = 0; i < replaceAttachment.size(); i++) {
//										JSONObject replaceSS = (JSONObject) replaceAttachment.get(i);
//										replaceSS.replace("FileName",
//												relatedTestCaseAPIResult.get(Thread.currentThread().getName() + "."
//														+ iterable_element.toString().trim() + ".FileName"));
//										replaceSS.replace("ImageBytes",
//												relatedTestCaseAPIResult.get(Thread.currentThread().getName() + "."
//														+ iterable_element.toString().trim() + ".ImageBytes"));
//										replaceAttachment.remove(i);
//										replaceAttachment.add(replaceSS);
//									}
//									objReplace.replace("AttachmentFile", replaceAttachment);
//								}
//								//Tracking the status in general bucket(Skipped as Parent TC Failed). But to be precise it should be in  "Failed as Parent TC Failed"
//								else if(postApiData.get(Thread.currentThread().getName() + ".TestResult").toString().equalsIgnoreCase("Fail"))
//									objReplace.replace("TestResult", "Skipped as Parent TC Failed");
//
//							}
//
//							//System.out.println("Execute API triggered for Related case:" + objReplace);
//							if (status) 
//								apiExecuteTestCase(objReplace);
//
//						}
//					} 
//				}
//			}
//			if (postApiData.containsKey(Thread.currentThread().getName() + ".TestResult")) {
//				if (postApiData.get(Thread.currentThread().getName() + ".TestResult").toString().equalsIgnoreCase("Fail")) {
//					Object objDefect = objectCreationForReportDefect();
//					synchronized (objDefect) {
//						System.out.println("API Report Defect String-- " + objDefect);
//						String exeStatus=System.getProperty("QcmsReportDefectStatus");
//						boolean status;
//
//						if (exeStatus != null) 
//							status=exeStatus.trim().equalsIgnoreCase("Yes");
//						else 
//							status=Index.prop.getProperty("Qcms.ReportDefectStatus").trim().equalsIgnoreCase("Yes");
//
//						if (status) 
//							apiReportDefect(objDefect);
//					}
//				} 
//			}
//		}
//
//		public String ESTTimeZone() {
//			Date date = new Date();
//			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");
//			formatter.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
//			String Fulldate = formatter.format(date);
//			return Fulldate;
//		}
//
//
//		/*	public String closedeocfromdb() {
//			String eocid = null;
//			String cusId = null;                        
//			JSONParser jsonParser = new JSONParser();
//			JSONObject jsonObject = null;
//			try {
//				FileReader reader = new FileReader(System.getProperty("user.dir")+"\\agadia-test-pahub\\src\\test\\resources\\DuplicateEocConfig.json");
//				jsonObject = (JSONObject) jsonParser.parse(reader);
//			} catch (org.json.simple.parser.ParseException | IOException e) {
//				e.printStackTrace();
//			}
//
//			reportMessage(STEP_INFO,"Getting customerID info");
//			JSONArray customeridlist=(JSONArray) jsonObject.get("CustomerId");
//			for (Object object : customeridlist) {
//				JSONObject clients=(JSONObject)object;
//				cusId=clients.get(ExcelUtil.testdata.get(Thread.currentThread().getName()+".customerName")).toString();
//			}
//			System.out.println("CustomerID-"+cusId);
//			Connection con=JdbcSQLServerConnection.JdbcServerConnection();
//			reportMessage(STEP_INFO,"DB Connected");
//			String query="SET NOCOUNT ON\r\n" + 
//					"Declare @eocidvscount table (eid varchar(50))\r\n" + 
//					"Declare @eocidvsenddate table (eid varchar(50))\r\n" + 
//					"Declare @finaleocid table(eocid varchar(50))\r\n" + 
//					"insert into @eocidvscount (eid)select eocid from hcsevent where customerid="+cusId+" group by eocid having count(eocid)=1\r\n" + 
//					"insert into @eocidvsenddate (eid)select eocid from hcsevent where customerid="+cusId+" and enddate>getdate()-4\r\n" + 
//					"insert into @finaleocid(eocid)(select * from @eocidvscount intersect select * from @eocidvsenddate)\r\n" + 
//					"select top 1 * from @finaleocid";
//			if (con != null) {
//				reportMessage(STEP_INFO, "Query :: "+query);
//				try {
//					Statement stat = con.createStatement();
//					ResultSet rs= stat.executeQuery(query);
//					while (rs.next()) {
//						if (rs.getString(1) != null) {
//							eocid=rs.getString(1);
//							break;
//							//System.out.println(eocid);
//						}
//					}
//					stat.close();
//					con.close();
//
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				reportMessage(STEP_INFO,"Query Executed");
//			}	
//			return eocid;
//		}*/
//
//		/**
//		 *@Sivaranjani
//		 *Support ticket defect - 27600 
//		 *Getting the url by environment and source
//		 */
//		public String getPahuburlByEnvironment(String Environment, String source)
//		{
//			String applicationurl = "";
//			reportMessage(STEP_INFO, "Environment for getting url : "+Environment);
//			switch (source) {
//			case "pahub":					
//				switch (Environment.toLowerCase()) {
//				case "qa":
//					applicationurl = Index.prop.getProperty("qa_url").trim();
//					break;
//				case "auto_qa":
//					applicationurl = Index.prop.getProperty("autoqa_url").trim();
//					break;
//				case "pre-uat":
//					applicationurl = Index.prop.getProperty("Pre-UAT_url").trim();
//					break;
//				case "staging":
//					applicationurl = Index.prop.getProperty("Staging_url").trim();
//					break;
//				case "obsolete_auto_qa":
//					applicationurl = Index.prop.getProperty("obsolete_auto_qa").trim();
//					break;
//				case "obsolete_qa":
//					applicationurl = Index.prop.getProperty("obsolete_qa_url").trim();
//					break;
//
//				default:
//					reportMessage(STEP_STATUS_FAIL, "Environment not matched. Please provide valid Environment");
//					break;
//				}
//				break;
//			default:
//				reportMessage(STEP_STATUS_FAIL, "Source not matched. Please provide valid source");
//			}
//			reportMessage(STEP_INFO, "url : "+applicationurl);
//			return applicationurl;
//		}
//
//		public String copyTypedText(Target elTarget) {
//			Actions act=new Actions(browserFactory.getDriver());
//			act.click(browserFactory.getDriver().findElement(elTarget.locator));
//			act.keyDown(Keys.CONTROL);
//			act.sendKeys("a");
//			act.sendKeys("c");
//			act.build().perform();
//			Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
//			String result = "";
//			try {
//				result = (String) clipboard1.getData(DataFlavor.stringFlavor);
//			} catch (UnsupportedFlavorException | IOException e) {
//				e.printStackTrace();
//			}
//			return result;
//		}
//
//		public String getCurrentESTDataTime(String format)
//		{
//			Date date = new Date();
//			DateFormat formatter = new SimpleDateFormat(format+" z");
//			formatter.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
//			String currentDateTime = formatter.format(date).replace("EST", "").replace("EDT", "").trim();
//			reportMessage(STEP_INFO, "CurrentDateTime : "+currentDateTime);
//			return currentDateTime;
//		}
//
//		public  boolean dateFormatValidation(String date,String dateformat,String RegexDateFormat) {
//			String Regex = "[01]\\d/[0-3]\\d/\\d{4} ([01]?[0-9]|2[0-3]):[0-5][0-9]";
//			if (date == null || !date.matches(Regex))
//				return false;
//			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy h:m");
//			df.setLenient(false);
//			try {
//				df.parse(date);
//				reportMessage(STEP_INFO, date+ " is a Valid Date Format.");
//				return true;
//			} 
//			catch (ParseException e) {
//				reportMessage(STEP_INFO,e.toString());
//				reportMessage(STEP_INFO, date+ " is a Invalid Date Format.");
//				return false;
//			}
//		}
//
//		public boolean waitForElementClickable(WebElement element) {
//			/**
//			 * @Sivaranjani
//			 * For handling element click inruppted Exception
//			 * Wait element to be clickable till the configured time
//			 */
//			boolean hasElementClickable = false;
//			try {
//				long waitTime = getWaitTime();
//				//	WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), 30);
//				WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTime));
//				WebElement we = wait.until(ExpectedConditions.elementToBeClickable(element));
//				if (we != null) {
//					hasElementClickable = true;
//				}
//			} catch (org.openqa.selenium.TimeoutException te) {
//				reportMessage(STEP_STATUS_WARN,
//						"Wait for Element Clickable, Element was not Clickable by given time seconds "
//								+ WAIT_ELEMENT_MAX);
//				Log.info("Slow Page Load");
//			}
//			return hasElementClickable;
//		}
//
//		public void WaitPageLoadForElement(WebElement element) {
//			/**
//			 * @Sivaranjani
//			 * Wait till the element loaded in the respective page
//			 */
//			long waitTimeSeconds = 0;
//			long start = System.currentTimeMillis();
//			long finish =0;
//			try {
//				String jenkins_pageload_wait = System.getProperty("PageLoadWait");// To read a jenkins execution PageLoad Wait
//				if(jenkins_pageload_wait==null) {
//					waitTimeSeconds = Long.parseLong(Index.prop.getProperty("PageLoadWait").trim());
//				}
//				else {
//					waitTimeSeconds=Long.parseLong(System.getProperty("PageLoadWait"));
//				}
//				if (element.isDisplayed()) {
//					finish = System.currentTimeMillis();
//					String totalTime = String.valueOf(finish - start);
//					reportMessage(STEP_STATUS_DONE, "Page load wait, Page Loaded Succesfully");
//					reportMessage(STEP_STATUS_DONE, "Wait from config : "+String.valueOf(waitTimeSeconds) +"and time taken to load element '");
//				} else {
//					WebDriverWait wait = new WebDriverWait(browserFactory.getDriver(), Duration.ofSeconds(waitTimeSeconds));
//					WebElement we = wait.until(ExpectedConditions.visibilityOf(element));
//					if (we != null) {
//						finish = System.currentTimeMillis();
//						String totalTime = String.valueOf(finish - start);
//						reportMessage(STEP_STATUS_DONE, "Page load wait, Page Loaded Succesfully");
//						reportMessage(STEP_STATUS_DONE, "Wait from config : "+String.valueOf(waitTimeSeconds) +" and time taken to load element is '"+totalTime);
//					} else {
//						finish = System.currentTimeMillis();
//						String totalTime = String.valueOf(finish - start);
//						reportMessage(STEP_STATUS_FAIL,
//								"Page load wait, Page Not Loaded in given time seconds " + waitTimeSeconds);
//						reportMessage(STEP_STATUS_DONE, "Wait from config : "+String.valueOf(waitTimeSeconds) +" and time taken to load element '"+totalTime);
//						Index.testres = "Failed";
//						Log.info("Slow Page Load");
//					}
//				}
//			} catch (WebDriverException wde) {
//				reportMessage(STEP_STATUS_WARN, "Page load wait, Page Not Loaded in given time seconds " + waitTimeSeconds+" seconds");
//				Log.info("Browser got exited before it gets connected");
//			} 
//		}
//
//		public void elementClick(WebElement element)
//		{
//			/**
//			 * @Sivaranjani
//			 * Failure fix for click interrupted Exception in direct element click
//			 * Before click the WEB ELEMENT passed in method argument checks the DOM availability and element clickable status
//			 *  if faced in any exception in element click then again try to click the element till configured attempts
//			 */
//			WaitPageLoadForElement(element);
//			int noOfTryOnClick = 3;
//			int attemptToClick = 1;
//			while(attemptToClick<=noOfTryOnClick)
//			{
//				if(waitForElementClickable(element))
//				{
//					try {
//						element.click();
//						break;
//					}
//					catch(Exception e)
//					{
//						reportMessage(STEP_STATUS_WARN, e.toString());
//						reportMessage(STEP_STATUS_WARN, "Element not clickable at iteration : "+attemptToClick);
//						attemptToClick++;
//						continue;
//					}
//				}
//				else
//				{
//					attemptToClick++;
//					reportMessage(STEP_STATUS_WARN, "Element not clickable at iteration : "+attemptToClick);
//					continue;
//				}
//			}
//
//		}
//
//		public String getCurrentMinusOrPlusDate(String format, String FromDateAge, String action)
//		{
//			Date DateTimeValue = null;
//			Date date = new Date();
//			int DateAge = Integer.valueOf(FromDateAge);
//			if(action.equalsIgnoreCase("minus"))
//				DateTimeValue = new Date(date.getTime() - Duration.ofDays(DateAge).toMillis());
//			else
//				DateTimeValue = new Date(date.getTime() + Duration.ofDays(DateAge).toMillis());
//			DateFormat formatter = new SimpleDateFormat(format+" z");
//			formatter.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
//			String startDateValue = formatter.format(DateTimeValue).replace("EST", "").replace("EDT", "").trim();
//			reportMessage(STEP_INFO, "DateTime : "+startDateValue);
//			return startDateValue;
//		}
//
//		public  void openNewTab()
//		{
//			/**
//			 * @Sivaranjani
//			 * Support ticket Defect - 25925
//			 * Opens URL in New Tab of same browser
//			 */
//			static_wait(5);
//			JavascriptExecutor jse = (JavascriptExecutor)browserFactory.getDriver();
//			jse.executeScript("window.open()");
//			static_wait(2);
//		}
//
//		public String getFirstWindow() {
//			/**
//			 * @Sivaranjani
//			 * Support ticket Defect - 25925
//			 * Returns Current window handle Name
//			 **/
//			String firstHandle = browserFactory.getDriver().getWindowHandle();
//			return firstHandle;
//		}
//
//		public int getWindowSize() {
//			/**
//			 * @Ramkumar
//			 * July Release CR 2073
//			 * Returns Windows Size
//			 **/
//			Set<String> WindowSize = browserFactory.getDriver().getWindowHandles();
//			int WindowCount=WindowSize.size();
//			return WindowCount;
//		}
//		public void DropDownValidation(Target target) {
//			/**
//			 * Kirthika V
//			 * July Release CR 2072
//			 * To validate the values in the Dropdown List
//			 **/
//			String[] DropDown_List=get_Values_From_ddl(target);
//			String [] TestData_Values=ExcelUtil.testdata.get(Thread.currentThread().getName()+"."+"DropDownValues").split(";");
//
//			for(int i=1;i<DropDown_List.length-1;i++) {
//				if(TestData_Values[i-1].equalsIgnoreCase(DropDown_List[i]))
//					reportMessage(STEP_STATUS_PASS,"Expected Drop Down value present in the drop down list - "+DropDown_List[i]);
//				else {
//					ExcelUtil.testdata.put(Thread.currentThread().getName()+"."+"FieldValidation","False");
//					reportMessage(STEP_STATUS_WARN,"Expected Drop Down value not available in the drop down list: "+TestData_Values[i]);
//				}
//			}
//		}
//		
//		public void ConvertDateFormat(String Date, String ExpectedDateFormat,String ActualDateFormat) throws ParseException {
//			/**
//			 * @Roobini
//			 * October 2022 Release CR-3427
//			 * To validate the Date Format 
//			 */
//			reportMessage(STEP_INFO, "Date: " +Date);
//			reportMessage(STEP_INFO, "Expected Date Format: " +ExpectedDateFormat);
//			reportMessage(STEP_INFO, "Actual Date Format: " +ActualDateFormat);
//			DateFormat sdf = new SimpleDateFormat(ActualDateFormat);
//		    Date date = sdf.parse(Date);
//		    String ExpectedDate=new SimpleDateFormat(ExpectedDateFormat).format(date);
//		    reportMessage(STEP_INFO, "Expect Date: " +ExpectedDate);
//		    ExcelUtil.testdata.put(Thread.currentThread().getName()+"."+"ExpectedDateValue",ExpectedDate);
//
//		}
//		
//		public void fileSaveForEdgeWithIE() {
//			/*@Selvamuthukumar
//			*To download files in edge with IE mode using Alt+S
//			*/
//			if (strBrowserType.equalsIgnoreCase("Edge(with IE mode ON)")) {
//				static_wait(5);
//				Robot rbt;
//				try {
//					rbt = new Robot();
//					rbt.keyPress(KeyEvent.VK_ALT);
//					static_wait(1);
//					rbt.keyPress(KeyEvent.VK_S);
//					static_wait(2);
//					rbt.keyRelease(KeyEvent.VK_ALT);
//					rbt.keyRelease(KeyEvent.VK_S);
//				} catch (AWTException e) {
//					e.printStackTrace();
//				}	
//			}
//		}
//	}
//
//}
