package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class leavepagee extends webAction {
	public void navigatetoLeave() throws IOException {
		try {
			excelFileReaderOfLocators("HomePage");
			excelFileReaderOfNumeric("UserCreation_LeaveTest");
			element("leave").click();
			Thread.sleep(5000);

		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Could not navigate to the Admin page");
		}

	}

	public void applyLeave() throws IOException, AWTException, InterruptedException {
		navigatetoLeave();
		excelFileReaderOfLocators("LeavePage");
		Thread.sleep(2000);
//		Robot r = new Robot();
//		element("apply").click();
//		element("leaveType").click();
//		r.keyPress(KeyEvent.VK_DOWN);
//		r.keyRelease(KeyEvent.VK_DOWN);
//		Thread.sleep(2000);
//		r.keyPress(KeyEvent.VK_TAB);
//		r.keyRelease(KeyEvent.VK_TAB);
//		Thread.sleep(5000);
//		System.out.println(excelHashMap.get("FromDate"));
//		element("fromDate").sendKeys(excelHashMap.get("FromDate"));
//		Thread.sleep(1000);
//		System.out.println(excelHashMap.get("ToDate"));
//		element("toDate").clear();
//		Thread.sleep(1000);
//		element("toDate").sendKeys(excelHashMap.get("ToDate"));
//
//		Thread.sleep(1000);
//		System.out.println(excelHashMap.get("commandss"));
//		element("comments").sendKeys(excelHashMap.get("commandss"));
//		element("btnapply").click();
		
		
		
//		element("Leaveclick").click();
//		Thread.sleep(3000);
		element("apply").click();
		Thread.sleep(3000);
		Robot r = new Robot();
		Thread.sleep(3000);
		element("LeaveType");
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		System.out.println(excelHashMap.get("Fromdate"));
		WebElement fromdate =  element("FromDate ");
		fromdate.sendKeys(excelHashMap.get("Fromdate"));
		

		WebElement todate =element("ToDate");
		todate.click();
		todate.clear();
		todate.sendKeys(excelHashMap.get("Todate"));
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
//		driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]")).click();
		Thread.sleep(2000);
		WebElement txtcommad= element("Txtcommands");
		 txtcommad.click();
		 txtcommad.sendKeys("Apply for leave");
		element("btnApply").click();


	}
}