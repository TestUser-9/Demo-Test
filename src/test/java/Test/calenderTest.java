package Test;

import java.io.IOException;

import org.testng.annotations.Test;

import Framework.Log;
import Pages.CalenderHandling;
import Pages.webAction;

public class calenderTest extends CalenderHandling{
	@Test
	public void calender_test() throws IOException, InterruptedException {
	testCaseID = "CalenderHandling";
	Log.startTestCase(testCaseID, testCaseID);
    message = "before calendar handling";
    Log.getReport(message);
	calender();
	message = "after calendar handling";
	Log.getReport(message);
	
	excelFileWriter("indhu","zucisystems");
	 

	

}
}
