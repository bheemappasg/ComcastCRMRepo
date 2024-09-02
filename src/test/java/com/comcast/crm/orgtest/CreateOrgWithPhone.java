package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWithPhone {

	@Test
	public void createOrgWithPhone() throws Exception
	{
		WebDriver driver=null;
		
		//Step 1 : Login to the application by reading data from property file
		
		///object creation for utility classes
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		
		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
		

		driver = wlib.getBrowser(driver, browser);
		wlib.waitForPageToLoad(driver);
		wlib.maximize(driver);

		wlib.get(driver, url);
		
		//object creation for POM classes
		LoginPage l = new LoginPage(driver);
		HomePage h = new HomePage(driver);
		OrganizationsPage o = new OrganizationsPage(driver);
		CreatingNewOrganizationPage c = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
				
		l.setLogin(username, password);
		
		// Step2 : Navigate to Organization Module 
		h.getOrgLink().click();		
		//Step 3 :click on Create Organization link
		o.getCreateOrgBtn().click();
		//Step 4 : Enter all the details and create organization
	
		String organizationName = elib.getDataFromExcelFile("CreateOrg", 7, 2)+jlib.getRandomNumber();
		String phone = elib.getDataFromExcelFile("CreateOrg", 7, 3);

		//enter Organization name and phone 
		c.createOrg(organizationName, phone);
		
		String result = oi.getHeaderInfoText().getText();
		
		//verify whether the organization has been creatred or not
		if(result.contains(organizationName) )
		{
			String actphone = oi.getPhoneText().getText();
			if(actphone.equals(phone))
				System.out.println("Phone has been verified === PASS");
			else
				System.out.println("Phone has not been verified === FAIL");	
			
		}
		else
		{
			System.out.println("Phone has not been verified === FAIL");	
			Assert.fail();
		}
		
		h.logOut();
		
		wlib.quit(driver);
	}
}
