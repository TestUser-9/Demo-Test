package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeavePage extends webAction {
	public void navigatetoLeave() throws IOException {
		try {
			excelFileReaderOfLocators("HomePage");
			excelFileReaderOfNumeric("UserCreation_LeaveTest");
			element("leave").click();

		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Could not navigate to the Admin page");
		}

	}
//	public void sameDayLeave(String key) throws AWTException {
//		Robot rob = new Robot();
////		if (key.equalsIgnoreCase("FullDay")){
////			rob.keyPress(KeyEvent.VK_TAB);
////			rob.keyRelease(KeyEvent.VK_TAB);
////		}
//		if (key.equalsIgnoreCase("Morning")) {
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyRelease(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_TAB);
//			rob.keyRelease(KeyEvent.VK_TAB);
//			
//		}
//		else if (key.equalsIgnoreCase("Evening")) {
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyRelease(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_TAB);
//			rob.keyRelease(KeyEvent.VK_TAB);
//		}
//		else if(key.equalsIgnoreCase("specifyTime")){
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_DOWN);
//			rob.keyRelease(KeyEvent.VK_DOWN);
//			rob.keyPress(KeyEvent.VK_ENTER);
//			rob.keyRelease(KeyEvent.VK_ENTER);
//			//element("llfromdate").sendKeys(excelHashMap.get("llFromDate"));
//			element("samedaystart").sendKeys("10:00 AM");
//			element("samedayend").sendKeys("06:00 PM");
//			
//		}
//	}
//	public void moreThanOneDayLeave(String key,String value) {
//		if(key.equalsIgnoreCase("AllDay")) {
//			
//		}
//		
//	}
	public void applLeave() throws IOException, AWTException, InterruptedException {
		navigatetoLeave();
		excelFileReaderOfLocators("LeavePage");
		element("btnapply").click();
		element("leaveType").click();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		
		
		element("llfromdate").sendKeys(excelHashMap.get("llFromDate"));
		//element("fromDate").sendKeys("2022-11-11");
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		//element("toDate").sendKeys("2022-11-11");
		element("llTodate").sendKeys(excelHashMap.get("llToDate"));
		Thread.sleep(1000);
		element("comments").sendKeys("commandss");
		element("apply").click();
		
		
		
		
	}

}
