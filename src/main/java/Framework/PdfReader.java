package Framework;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * @author vignesh.chandran
 *
 */
public class PdfReader {

	String file_Path = "C:\\Users\\vignesh.chandran\\Downloads\\PDF\\document.pdf";
	    public static String getPdfString(String file_Path) throws IOException
	    {

			   File file = new File("C:\\Users\\vignesh.chandran\\Downloads\\PDF\\document.pdf");
			   PDDocument document = Loader.loadPDF(file);
			      //Instantiate PDFTextStripper class
			      PDFTextStripper pdfStripper = new PDFTextStripper();
			      pdfStripper.setSortByPosition(true);
			      pdfStripper.setAddMoreFormatting(true);
			      //Retrieving text from PDF document
			      String text = pdfStripper.getText(document);
			      //Closing the document
			      System.out.println(text);
			      document.close();
			      return text;
	   }
	   
	   public static String get_Pdf_Value_by_Key(String Key, String file_Path) throws IOException
	   {
		   String pfd_Content = getPdfString(file_Path);
		   String[] pfd_Row_Content = pfd_Content.split("\n");
		   String value ="";
		   for(int i=0;i < pfd_Row_Content.length;i++)
		   {
			   if(!pfd_Row_Content[i].isEmpty()) {
			   if(pfd_Row_Content[i].contains(Key))
			   {
				   if(Key.equalsIgnoreCase("Ingredient NDC Quantity Ingr Cost Basis"))
					  i = i+1;
				   value = pfd_Row_Content[i];
				   break;
			   }}
		   }
		  return value;
	   }
	   
	   public static String get_Pdf_Key_next_value(String Key, String file_Path) throws IOException
	   {
		   String pfd_Content = getPdfString(file_Path);
		   String[] pfd_Row_Content = pfd_Content.split("\n");
		   System.out.println("****************** Inside map ************");
		   String value ="";
		   for(int i=0;i < pfd_Row_Content.length;i++)
		   {
			   if(pfd_Row_Content[i].contains(Key))
			   {
				   value = pfd_Row_Content[i+2];
				   break;
			   }
		   }
		   return value;
	}
	   public static int PDF_PageCount(String file_Path) {
		   File file = new File(file_Path);
		   int No_Of_Pages=0;
		      try {
		    	  PDDocument document = Loader.loadPDF(file);
		    	  No_Of_Pages= document.getNumberOfPages();
		    	  document.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    return No_Of_Pages;
	   }
	   
	   public static String getPfdStringByPage(String file_Path, int startPageNo, int endPageNo)
	   {

			   File file = new File(file_Path);
			   String Pdftext ="";
			   PDDocument document;
			try {
				document = Loader.loadPDF(file);
			
			      //Instantiate PDFTextStripper class
			      PDFTextStripper pdfStripper = new PDFTextStripper();
			      pdfStripper.setSortByPosition(true);
			      pdfStripper.setAddMoreFormatting(true);
			      pdfStripper.setStartPage(startPageNo);
			      if(endPageNo==0)
			    	  endPageNo = pdfStripper.getEndPage();
			      pdfStripper.setEndPage(endPageNo);
			      //Retrieving text from PDF document
			        Pdftext = pdfStripper.getText(document);
			      //Closing the document
			      document.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			      return Pdftext;
	   }
	   
	   public static String get_Pdf_Value_by_Key(String Key, String file_Path, int next) throws IOException
	   {
		   String pfd_Content = getPdfString(file_Path);
		   String[] pfd_Row_Content = pfd_Content.split("\n");
		   String value ="";
		   for(int i=0;i < pfd_Row_Content.length;i++)
		   {
			   if(!pfd_Row_Content[i].isEmpty()) {
				   if(pfd_Row_Content[i].contains(Key))
				   {
					   i = i+next;
					   value = pfd_Row_Content[i];
					   break;
				   }
				}
		   }
		  return value;
	   }
	   
	   public static int getImageCountFromPDF(String filePath)
	   {
		   System.out.println("PDF Read Started");
		   PDDocument document;
		   int numImages = 0;
		try {
			//document = PDDocument.load(new File(filePath));
              document = Loader.loadPDF(new File (filePath));
		    for (int i = 0; i < document.getNumberOfPages(); i++)
		    {
		        PDPage page = document.getPage(i);
		 
		        CountImages countImages = new CountImages(page);
		        countImages.processPage(page);
		 
		        numImages += countImages.numImages;
		    }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return numImages;
	   }
	   
	 public static class CountImages extends PDFGraphicsStreamEngine {

	    public int numImages = 0;
	    private Set<COSStream> duplicates = new HashSet<COSStream>();
	    
	    protected CountImages(PDPage page) throws IOException
	    {
	        super(page);
	    }

		@Override
		public void appendRectangle(Point2D p0, Point2D p1, Point2D p2, Point2D p3) throws IOException {			
		}

		@Override
		public void drawImage(PDImage pdImage) throws IOException {
			 if (pdImage instanceof PDImageXObject) {
		            PDImageXObject xobject = (PDImageXObject)pdImage;
		 
		            if (duplicates.contains(xobject.getCOSObject()) == false) {
		            //    numImages++;
		                duplicates.add(xobject.getCOSObject());
		                System.out.println(duplicates.size());
		            }
		        } //else
		            numImages++;
		}

		@Override
		public void clip(int windingRule) throws IOException {
		}

		@Override
		public void moveTo(float x, float y) throws IOException {	
		}

		@Override
		public void lineTo(float x, float y) throws IOException {
		}

		@Override
		public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) throws IOException {
		}

		@Override
		public Point2D getCurrentPoint() throws IOException {
			return null;
		}

		@Override
		public void closePath() throws IOException {
		}
		@Override
		public void endPath() throws IOException {
		
		}
		@Override
		public void strokePath() throws IOException {
		}
		@Override
		public void fillPath(int windingRule) throws IOException {
		}
		@Override
		public void fillAndStrokePath(int windingRule) throws IOException {
		}
		@Override
		public void shadingFill(COSName shadingName) throws IOException {			
		}
	 
	   } 
	}


