package Test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import Framework.Log;
import Pages.UserCreationandUserDelete;
public class TestUserDelete extends UserCreationandUserDelete {
	@Test
	public void deleteUserfromList() {
		testCaseID = "Orange_Delete User";
		Log.startTestCase(testCaseID, "Test_Orange_Delete User");
		try {
			
			pageValidation("OrangeHRM");
			Log.getReport(message);

			//login to application
			message = " Login to Application";
			loginToApplication();
			Log.getReport(message);

			//Add user
			message = " Delete User";
			//deleteUser();
			Log.getReport(message);

			//validate the created user
			validateUserCreation();
			Log.getReport(message);

			//logout from the application
			message = " Logout from the application";
			logoutFromApplication();
			Log.getReport(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("Test_Orange_Delete User");		
	}
}
