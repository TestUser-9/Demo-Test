package Test;
	
import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import Framework.PropertyReader;
import Pages.webAction;


public class TestNGXML{
	
	public void xml(String vartest) throws IOException, AWTException, InterruptedException {
		String execution_value;
		String cell_Value;
		String variablename;
		String variablevalue;
		HashMap<String, String> excelHashMap= new HashMap<String, String>();
		String filePath =  System.getProperty("user.dir")+"\\src\\data\\TestNGXML.xlsx";
		String fileName =  PropertyReader.readProperty("fileName");
		String sheetName =  PropertyReader.readProperty("sheetName");
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook Workbook = null;    
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			Workbook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			Workbook = new HSSFWorkbook(inputStream);
		}
		Sheet WorkSheet = Workbook.getSheet(sheetName);
		int rowCount = WorkSheet.getLastRowNum()-WorkSheet.getFirstRowNum();
		//creating suite
		XmlSuite xmlsuite_patricia = new XmlSuite();
		
		for (int l = 1; l < rowCount+1; l++){
				Row deleterow = WorkSheet.getRow(l);
				Cell deletecell = deleterow.getCell(7);
				Cell pathdelete=deleterow.getCell(8);
				Cell issueNodelete=deleterow.getCell(9);
				
				if (deletecell!=null&& WorkSheet.getRow(l).getCell(0).toString().equalsIgnoreCase(vartest)) {
					deleterow.removeCell(deletecell);
					if (pathdelete!=null) {
						deleterow.removeCell(pathdelete);
					}
					if (issueNodelete!=null) {
						deleterow.removeCell(issueNodelete);
					}
				}	
		}

		for (int i = 0; i < rowCount+1; i++) {
			Row row = WorkSheet.getRow(i);
			Cell cell= row.getCell(0);
			cell_Value = cell.toString();
			Cell execution = row.getCell(3);
			execution_value=execution.toString();
			if (cell_Value.equalsIgnoreCase(vartest)&& execution_value.equalsIgnoreCase("Yes"))
			{	
				int columnnum=row.getLastCellNum()-row.getFirstCellNum();
				XmlTest xmltest_patricia = new XmlTest(xmlsuite_patricia);
				for (int j = 0; j < columnnum; j++) {
					Row rowvar=WorkSheet.getRow(0);
					Cell var=rowvar.getCell(j);
					variablename=var.toString();
					Cell clm=row.getCell(j);
					variablevalue=clm.toString();
					excelHashMap.put(variablename, variablevalue);
				}
				if (excelHashMap.get("Parameters Name")!=null) {

					String[] variablenames=excelHashMap.get("Parameters Name").split(",");
					String[] variablevalues=excelHashMap.get("Parameters Value").split(",");

					for (int k = 0; k < variablevalues.length; k++)
					{
						xmltest_patricia.addParameter(variablenames[k].trim(),variablevalues[k].trim());
					}
				}
				String class_V = excelHashMap.get("TestClassName");
				XmlClass xmlclass_patricia = new   XmlClass(class_V);
				java.util.List<XmlClass> test= new ArrayList<XmlClass>();
				xmltest_patricia.setName(class_V+"_"+excelHashMap.get("Browser Setup"));
				System.out.println(class_V+"_"+excelHashMap.get("Browser Setup"));
				webAction.browserHashMap.put(class_V+"_"+excelHashMap.get("Browser Setup"), row.getCell(4).toString());
				webAction.testHashMap.put(class_V+"_"+excelHashMap.get("Browser Setup"), row.getCell(1).toString());
				test.add(xmlclass_patricia);
				xmltest_patricia.setXmlClasses(test);	
			}	
		}	
		
		TestNG testxml = new TestNG();
		java.util.List<XmlSuite> testsuite= new ArrayList<XmlSuite>();
		testsuite.add(xmlsuite_patricia);
		testxml.setXmlSuites(testsuite);
		
		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(filePath);
		Workbook.write(outputStream);
		outputStream.close();
	
		testxml.run();
	}
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {

		TestNGXML obj = new TestNGXML();
		obj.xml("UnitTest");
		//obj.zephyrStatusUpload("UnitTest");
	}
}
