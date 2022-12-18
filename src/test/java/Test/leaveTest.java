package Test;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import Framework.Log;
import Pages.LeavePage;
import Pages.leavepagee;
import Pages.webAction;

public class leaveTest extends leavepagee {
	@Test
	public void applyleave() {
		testCaseID = "Orange_LeaveUser";
		Log.startTestCase(testCaseID, "Orange_LeaveUser");

		try {
			loginToApplication();
			navigatetoLeave();
			applyLeave();
			//excelFileWriterforTestNG("UnitTest_04",)
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("Orange_LeaveUser");
	}

	
}
