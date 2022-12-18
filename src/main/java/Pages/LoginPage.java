package Pages;
import org.testng.Assert;
import java.io.IOException;



public class LoginPage extends webAction {
	boolean val=false;
	public boolean loginToApplication() throws IOException, InterruptedException {
	try {
		excelFileReaderOfLocators("LoginPage");
		element("loginUsername").sendKeys(user);
		element("loginPassword").sendKeys(password);
		element("btnLogin").click();
		driverWait();
	}catch(Exception e) {
		e.printStackTrace();
		Assert.fail(" Could not login to the application");
	}
	return val;
}
	public boolean pageValidation(String expectedTitle) {
		boolean returnValue=false;
		try {
			String title=driver.getTitle();
			if (title.equals(expectedTitle)) {
				message = "User navigation to the "+expectedTitle+" Application Page";
			} else {
				message = "User navigation to the "+expectedTitle+" Application Page";
				Assert.fail(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Could not valudate the page");
		}
		return returnValue;
	}
	

}
