package Framework;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DatePicker{
	public static WebDriver driver;
	public static String month_year;
	public static String select_day;
	@Test
	public void driverset() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.makemytrip.com/flights/");
	driver.manage().window().maximize();
	WebElement  departure = driver.findElement(By.xpath("//span[contains(text(),'DEPARTURE')]"));
	 departure .click();
	Thread.sleep(1000);
	Date d = new Date(1);
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
	String date = formatter.format(d);
	String splitter[] = date.split("-");
	String month_year = splitter[1];
	String day = splitter[0]; 
	System.out.println(month_year);
	System.out.println(day);
    selectDate(month_year,day); 
	
	}

	public  void  selectDate(String month, String select_day) throws InterruptedException
	{ 
	List<WebElement> elements = driver.findElements(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]"));

	for (int i=0; i<elements.size();i++)
	{
	System.out.println(elements.get(i).getText());

	//Selecting the month
	if(elements.get(i).getText().equals(month_year))
	{ 

	//Selecting the date 
	List<WebElement> days = driver.findElements(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[3]"));
	for (WebElement d:days)
	{ 
	System.out.println(d.getText());
	if(d.getText().equals(select_day))
	{
	d.click();
	Thread.sleep(10000);
	return;
	}
	} 

	} 

	}
	driver.findElement(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/div/a/span")).click();
	selectDate(month_year,select_day);

	}

	
}
