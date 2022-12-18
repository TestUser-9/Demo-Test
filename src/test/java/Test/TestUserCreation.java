package Test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Framework.Log;

import Pages.UserCreationandUserDelete;

public class TestUserCreation extends UserCreationandUserDelete {

	@Test
	public void userCreation() {
		testCaseID = "Orange_UserCreation";
		Log.startTestCase(testCaseID, "Test_Orange_UserCreation");
		try {
			//Page validation
//			login.pageValidation("OrangeHRM");
//			Log.getReport(message);

			//login to application
			message = " Login to Application";
			
			loginToApplication();
			Log.getReport(message);
			System.out.println("Logged In");

			//Add user
			message = " Add User";
			addUser();
			Log.getReport(message);
			
			
			message ="send email";
			emailUtil();
		    Log.getReport(message);

//		    message = "ReadSentMail";
//		    readMails();
//		    Log.getReport(message);
		    		
			//validate the created user
//			validateUserCreation();
//			Log.getReport(message);

			//logout from the application
//			message = " Logout from the application";
//			logoutFromApplication();
//			Log.getReport(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("Test_Orange_UserCreation");		
	}


}
