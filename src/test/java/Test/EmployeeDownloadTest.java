package Test;

import org.testng.annotations.Test;

import Framework.Log;
import Pages.AdminPageDownload;


public class EmployeeDownloadTest {

	
	public class TestUserCreation extends AdminPageDownload {

	@Test
		public void userCreation() {
			testCaseID = "Orange_UserCreation";
			Log.startTestCase(testCaseID, "Test_Orange_UserCreation");
			try {
		
				//login to application
				message = " Login to Application";
				
				loginToApplication();
				Log.getReport(message);
				System.out.println("Logged In");
				
				//DownloadFile
				downloadfiles(outValue, message);
				Log.getReport(message);
				System.out.println("File Downloaded");
// add
				
} catch (Exception e) {
	e.printStackTrace();
}
Log.endTestCase("Test_Orange_UserCreation");		
}
	}}
			
			
	
