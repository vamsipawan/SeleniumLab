package com.selenium.UtilityFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.TestBase.TestBaseClass;

public class AddCustomerGroups extends TestBaseClass {

	@Test
	public void customersAdd() throws Exception {
		// opening the browser
		driver.get(config.getProperty("TestSiteName"));

		// Enter the user name
		getObject("Login_userID").sendKeys("Kadmin");

		// Enter the password
		getObject("Login_password").sendKeys("K@admin");

		// click on login button
		getObject("Login_button").click();

		// Taking "Dashboard" value into a "loginsuccess" variable
		String loginsuccess = getObject("Login_success").getText();

		// Verifying whether text is present on the home page
		if (loginsuccess.equals("Dashboard")) {

			System.out.println("Dashboard successfully verified");
		} else {
			System.out.println("Dashboard  not verified successfully");
		}

		// Read data from excel sheet
		FileInputStream fi = new FileInputStream(
				"D:\\rajasekhar\\com.xyzbank\\src\\main\\java\\com\\selenium\\ExcelPack\\Customer_data.xls");
		Workbook w = Workbook.getWorkbook(fi);
		Sheet s = w.getSheet("addCustomerGroup");

		// count total no of rows from excel
		System.out.println("s.getRows() = " + s.getRows());
		
		// count total no of columns from excel
		System.out.println("s.getColumns = " + s.getColumns());

		for (int i = 1; i < s.getRows(); i++) {

			String CustomerGroupName = s.getCell(0,i).getContents();
			String Description = s.getCell(1,i).getContents();
			

			getObject("Customer_module").click();
			getObject("Customergroup").click();
			getObject("AddNew_Customergroup").click();
			Thread.sleep(10000);

			getObject("CustomerGroupName").sendKeys(CustomerGroupName);
			getObject("Description").sendKeys(Description);
			

			getObject("Save_button").click();
			

			// To verify whether the text exists on webpage or not
			boolean customerGroupModified = driver.getPageSource().contains("modified customer groups!");

			if (customerGroupModified == true) {
				System.out.println("customer group  :" + CustomerGroupName);
				System.out.println(" customer group created successfully");
			} else {
				System.out.println(" Unable to create customer group ");
			}
			Thread.sleep(10000);

		}
		getObject("Logout_link").click();
		w.close();

	}

}
