package Test;

import org.testng.annotations.Test;

import Framework.Log;

import Pages.webAction;

public class LoginTest extends webAction {
	@Test
	public void loginTest() {
		testCaseID = "Login_Positive";
		Log.startTestCase(testCaseID, "Test_Orange_Login");
		try {
			//Page validation
			pageValidation("OrangeHRM");
			Log.getReport(message);

			//login to application
			message = " Login to Application";
			loginToApplication();
			Log.getReport(message);
			//closeDriver();
			//logout from application
			logoutFromApplication();
			Log.getReport(message);
			System.out.println("Logged Out");
			 excelFileWriter("Username","zucisystems");
			closeDriver();
			
			
			
}
	 catch (Exception e) {
		e.printStackTrace();
	}
	Log.endTestCase("Test_Orange_Login");		
}
	
	
}
	


