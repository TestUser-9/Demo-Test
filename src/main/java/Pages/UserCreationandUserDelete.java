package Pages;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class UserCreationandUserDelete extends webAction {
	Date date = new Date();

	public void navigatetoAddUser() throws IOException {
		try {
			excelFileReaderOfLocators("HomePage");
			excelFileReaderOfNumeric("UserCreation_AddUser");
			element("admin").click();
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Could not navigate to the Admin page");
		}
	}

	public void addUser() throws IOException {
		try {
			navigatetoAddUser();
			element("btnAdd").click();
			excelFileReaderOfLocators("AddUser");
			excelFileReaderOfNumeric("UserCreation_AddUser");

			WebElement role = element("userRole");
			role.click();
			Thread.sleep(3000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			element("employee Name").sendKeys(excelHashMap.get("Employee Name"));
//			WebElement emp = element("employee Name");
//			Point corr = emp.getLocation();
//			int x = corr.getX();
//			int y = corr.getY() + 6;
//			r.mouseMove(x, y);
//			Thread.sleep(3000);
//			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//			Thread.sleep(3000);

//			r.keyPress(KeyEvent.VK_DOWN);
//			r.keyPress(KeyEvent.VK_ENTER);

			WebElement status = element("status");
			status.click();
			Thread.sleep(3000);
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			element("userName").sendKeys(excelHashMap.get("Username"));
			// sts.selectByVisibleText(excelHashMap.get("Status"));

			element("password").sendKeys(excelHashMap.get("Password"));

			element("confirmPassword").sendKeys(excelHashMap.get("Confirm Password"));
			driverUntilWait(element("btnSave"));
			element("btnSave").click();
			// excelFileWriter("Created_Username", user_name);
		} catch (Exception e) {
			Assert.fail("Could not complete the Add user process");
			e.printStackTrace();
		}
	}

	public void searchforUser() throws IOException {
		try {
			navigatetoAddUser();
			element("searchfiedUsername").sendKeys(excelHashMap.get("Created_Username"));
			element("btnSearch").click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to find the user");
		}
	}

	public void validateUserCreation() throws IOException {
		try {
			searchforUser();
			Thread.sleep(1000);
			WebElement searchResult = driver
					.findElement(By.xpath("//a[text()='" + excelHashMap.get("Created_Username") + "']"));
			if (searchResult.isDisplayed()) {
				message = " The username- " + excelHashMap.get("Created_Username") + " is available";
			}
		} catch (Exception e) {
			message = " The username- " + excelHashMap.get("Created_Username") + " is not available";
			Assert.fail(message);
			e.printStackTrace();
		}
	}

//	public void deleteUser() throws IOException {
//		try {
//			searchforUser();
//			element("checkbtnUserSelect").click();
//			element("btnDelete").click();
//			element("btnDeleteConfirm").click();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail("Unable to delete the user");
//		}
//	}

}
