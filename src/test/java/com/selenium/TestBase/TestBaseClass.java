package com.selenium.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestBaseClass {

	public static Properties config;
	public static Properties OR;
	public static WebDriver driver;

	@BeforeTest
	public void intilize() throws IOException {
		// create Properties object for that config.properties file
		config = new Properties();

		FileInputStream fis = new FileInputStream(
				"D:\\rajasekhar\\com.xyzbank\\src\\main\\java\\com\\selenium\\property\\config.properties");
		// load config property file
		// F:\SeleniumMavenProject\src\main\java\com\selenium\Config\OR.properties
		config.load(fis);
		// //create Properties object for that OR.properties file
		OR = new Properties();

		FileInputStream fiss = new FileInputStream(
				"D:\\rajasekhar\\com.xyzbank\\src\\main\\java\\com\\selenium\\property\\OR.properties");
		// load xpath
		OR.load(fiss);

		if (config.getProperty("browser").equals("Firefox")) {

			// System.setProperty("webdriver.gecko.driver",
			// "C:\\Users\\pc\\Downloads\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (config.getProperty("browser").equals("IE")) {

			driver = new InternetExplorerDriver();

		}
		// Maximize the browser window
		// driver.manage().window().maximize();

	}

	public static WebElement getObject(String xpathvalue) {
		try {
			return driver.findElement(By.xpath(OR.getProperty(xpathvalue)));
		} catch (Throwable t) {
			return null;
		}

	}

	public static void takeScreenShot(String UserName) throws IOException {

		// Take screenshot and store as a file format
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// now copy the screenshot to desired location using copyFile method
		FileUtils.copyFile(scrFile, new File(config.getProperty("ScreenshotPath") + "-" + UserName + "-" + ".png"));

	}

	// @AfterTest – Run After any Test method that belongs to the classes is
	// invoked
	@AfterTest
	public void close_browser() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("close_browser");
		driver.quit();

	}

}