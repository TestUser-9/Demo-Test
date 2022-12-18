package Framework;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PdfStripper {
	 

	    String url = "C:\\Users\\vignesh.chandran\\Downloads\\PDF\\document.pdf";

	    @Test

	    public void verifyTextFromPDF() {

	     try {

	     String pdfContent = getPdfContent(url);

	     Assert.assertTrue(pdfContent.contains("Test"));

	     } catch (IOException e) {

	     // TODO Auto-generated catch block

	     e.printStackTrace();

	     }

	    }

	    public static String getPdfContent(String url) throws IOException {

	     URL pdfURL = new URL(url);

	     InputStream is = pdfURL.openStream();

	     BufferedInputStream bis = new BufferedInputStream(is);

	     PDDocument doc = Loader.loadPDF(bis);

	     PDFTextStripper strip = new PDFTextStripper();

	     String stripText = strip.getText(doc);

	     System.out.println(stripText);

	     doc.close();

	     return stripText;

	    }

	}

