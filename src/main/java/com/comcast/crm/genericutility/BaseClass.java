package com.comcast.crm.genericutility;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	//// object creation for utility classes
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public DatabaseUtility dbLib = new DatabaseUtility();
	
	@BeforeSuite(groups = {"smokeTest" , "regressionTest"})
	public void beforeSuite()
	{
		Reporter.log("=== Connect to DB and Report Config ===",true);
		
		dbLib.getConnection();
	}
	
	//@Parameters("browser")
	@BeforeClass(groups = {"smokeTest" , "regressionTest"})
	public void beforeClass() throws Exception
	{
		Reporter.log("=== Open Browser ===",true);
		
		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		
		driver = wlib.getBrowser(driver, browser);
		sdriver = driver;
		
		UtilityClassObject.setSdriver(sdriver);
		
		wlib.maximize(driver);
		wlib.waitForPageToLoad(driver);

		wlib.get(driver, url);
	}
	
	@BeforeMethod(groups = {"smokeTest" , "regressionTest"})
	public void beforeMethod() throws Exception
	{
		Reporter.log("= login =",true);
		
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
		
		LoginPage l = new LoginPage(driver);
		l.setLogin(username, password);
	}
	
	@AfterMethod(groups = {"smokeTest" , "regressionTest"})
	public void afterMethod()
	{
		Reporter.log("= logout =",true);
		
		HomePage h = new HomePage(driver);
		h.logOut();
	}
	
	@AfterClass(groups = {"smokeTest" , "regressionTest"})
	public void afterClass()
	{
		Reporter.log("=== Close Browser ===",true);
		
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest" , "regressionTest"})
	public void afterSuite()
	{
		Reporter.log("=== Close DB Connection and Report BackUp ===",true);
		
		dbLib.closeConnection();
	}
}
