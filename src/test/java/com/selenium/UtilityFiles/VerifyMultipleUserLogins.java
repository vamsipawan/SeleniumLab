package com.selenium.UtilityFiles;



import java.io.FileInputStream;
import java.io.FileOutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.testng.annotations.Test;

import com.selenium.TestBase.TestBaseClass;


public class VerifyMultipleUserLogins extends TestBaseClass {
	
	@Test
	public void testImportexport1() throws Exception {
		
		
		
	// Read data from excel sheet
	FileInputStream fi = new FileInputStream("D:\\rajasekhar\\com.xyzbank\\src\\main\\java\\com\\selenium\\ExcelPack\\Customer_data.xls");
	Workbook w = Workbook.getWorkbook(fi);
	Sheet s = w.getSheet("MultipleLoginFunctionality");
	
	String a[][] = new String[s.getRows()][s.getColumns()];
	// Write the input data into another excel file
		FileOutputStream fo = new FileOutputStream("D:\\multiple_LoginResult.xls");
		WritableWorkbook wwb = Workbook.createWorkbook(fo);
		WritableSheet ws = wwb.createSheet("loginresult1", 0);
		
	

//count total rows from excel
		System.out.println("s.getRows() = " + s.getRows());
//count total columns from excel
		System.out.println("s.getColumns = " + s.getColumns());
		
		for (int i = 0; i < s.getRows(); i++) 
		{
			
				for (int j = 0; j < s.getColumns(); j++) 
				{
					
					
					
					ws.addCell(new Label(j, i, s.getCell(j, i).getContents() ));
					
		        }
		}
		
		ws.addCell(new Label(3, 0, "Result"));
	
	
	for (int i = 1; i < s.getRows(); i++) 
	{
	
		driver.get(config.getProperty("TestSiteName"));
		
		String userID=s.getCell(1, i).getContents();
		String password=s.getCell(2, i).getContents();
		
		
		getObject("Login_userID").sendKeys(userID);
		getObject("Login_password").sendKeys(password);
		getObject("Login_button").click();
		
		Thread.sleep(5000);
	
	
try{
	String displaysuccess=getObject("Login_success").getText();

	if(displaysuccess.equals("Dashboard"))
	{
		
		ws.addCell(new Label(3, i, "pass"));
	
		
		  getObject("Logout_link").click();
		  Thread.sleep(10000);
		System.out.println("Login pass");
		
		/*
		new Actions(driver).moveToElement(getObject("Customer_module")).click().build().perform();
		   Thread.sleep(5000);
			new Actions(driver).moveToElement(getObject("Customers")).click().build().perform(); // Perform mouse hover action using 'clickAndHold' method
			  Thread.sleep(5000);
			 
			*/		  

			  
			  
			  
			  
			  
			  
    }
}


  catch(Exception e)
		{
	
	
	
	// to verify whether the particular text is present or not on a page
	boolean isTheTextPresent = driver.getPageSource().contains("No match for Username and/or Password");
	
	if(isTheTextPresent)
	{
		
		 System.out.println("invalid credential...." );
	}
		
	
	
   
Thread.sleep(5000);
	



		ws.addCell(new Label(3, i, "fail"));
		
		
		String UserName=userID;
    	takeScreenShot(UserName);
		
     

	

		
		
		
	        } 
	}
	wwb.write();
	wwb.close();

	
	
	}

	

	
}	

 
	
