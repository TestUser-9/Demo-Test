package Framework;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.relevantcodes.extentreports.LogStatus;

import Pages.webAction;

public class Log {
	
	// Initialize Log4j logs
	public static Logger Log = Logger.getLogger(Log.class);//
	//public static ExtentTest test;
	static int stepNum = 0;
	public static String incrementSteps() {
		stepNum++;
		return "Step No: " +stepNum  ;
	}
	public static void getReport(String message) throws InterruptedException {
		Thread.sleep(1000);
		String StepNum=incrementSteps();
		//Log.info(message + "- PASS");
	 	Log.info(StepNum + " - Pass - "  +message);
		webAction.test.log(LogStatus.PASS,StepNum+" - PASS - " +  message + " -- executed successfully");
		Thread.sleep(2000);
	}

	// This is to print log for the beginning of the test case, as we usually run so
	// many test cases as a test suite

	public static void startTestCase(String CaseID,String logTestCaseName) {
		webAction.test =ExtentReport.extent.startTest(CaseID);

		Log.info("****************************************************************************************");

		Log.info("$$$$$$$$$$$$$$$$$$$$$        " + logTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$");

		Log.info("****************************************************************************************");

	}

	// This is to print log for the ending of the test case

	public static void endTestCase(String sTestCaseName) {
		ExtentReport.extent.endTest(webAction.test);
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXXX");
		
	}

	// Need to create these methods, so that they can be called

	public static void info(String message) {

		Log.info(message);

	}

	public static void warn(String message) {

		Log.warn(message);

	}

	public static void error(String message) {

		Log.error(message);

	}

	public static void fatal(String message) {

		Log.fatal(message);

	}

	public static void debug(String message) {

		Log.debug(message);

	}

	public static void error(Throwable throwable) {
	Log.error(throwable);
	}

}