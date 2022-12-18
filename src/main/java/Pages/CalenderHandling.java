package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Framework.Log;

public class CalenderHandling extends webAction {

	public void calender() throws InterruptedException, IOException {
		
		

		String date;
		try {
			excelFileReaderOfLocators("MakemyTrip");
			
			element("langselect").click();
			Thread.sleep(2000);
			element("radionbtn").click();
			element("subbutton").click();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		date = excelFileReader("UserCreation_calender");
		Thread.sleep(3000);
		element("departure").click();

		Thread.sleep(2000);

		boolean flag = true; 

		while (flag) {
			Thread.sleep(3000);

			List<WebElement> monthsel = elements("clkdate", date);

			if (monthsel.size() > 0) {
				Thread.sleep(3000);
				WebElement i = monthsel.get(0);
				i.click();

				flag = false;

			}

			else {
				element("nextmonth").click();

			}
		}
		Log.endTestCase(testCaseID);
	}

}
