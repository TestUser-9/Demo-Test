package Pages;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class AdminPageDownload extends webAction {
	public static void downloadfiles(WebElement element,String name) {
        element.click();
        File filelocation = new File("C:\\Users\\indhu.gurunadhan\\Downloads");
        System.out.println("HI");



       filelocation.listFiles();
        File[] totalFiles = filelocation.listFiles();
        for (File File : totalFiles) {
            if (File.getName().equals(name)) {
                System.out.println("the file is downloaded");
            }



       }



	}
}














