package Test;

import org.testng.annotations.Test;

import Framework.PdfReader;
import Framework.ReadingMails;

public class GetEmailTest {

//	 @Test
//	    public void getMail() {
//	    	ReadingMails sample = new ReadingMails();
//	        sample.readMails();
//	       
//}
	 @Test
	 public void readPdf() {
		 PdfReader read = new PdfReader();
		 String file_Path = "C:\\Users\\vignesh.chandran\\Downloads\\PDF\\document.pdf";
		 
		read.PDF_PageCount(file_Path);
	 }
}
