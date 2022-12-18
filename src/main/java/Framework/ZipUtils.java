package Framework;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.openqa.selenium.io.Zip;

public class ZipUtils {
	public ZipUtils() throws IOException {
	
//public void ZipUnzip() throws IOException {
	        String zip =Zip.zip(new File("./test-output"));
	        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("./test-outputZipped.zip"),1000);
	        byte[] decode = Base64.getDecoder().decode(zip);
	        stream.write(decode);
	        stream.close();

	       
}
}	


