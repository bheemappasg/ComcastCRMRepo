package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
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
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class CreateContactWithName {
	
	@Test
	public void createContactWithName() throws Exception
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
		ContactsPage con = new ContactsPage(driver);
		CreatingNewContactPage c = new CreatingNewContactPage(driver);
		ContactInformationPage ci = new ContactInformationPage(driver);
		
		l.setLogin(username, password);
		
		// Step2 : Navigate to Contacts Module 
		h.getContactLink().click();
		
		//Step 3 :click on Create Contact link
		con.getCreateContactBtn().click();
		
		//Step 4 : Enter all the details and create organization
	
		String lastName = elib.getDataFromExcelFile("Contact", 4, 2)+jlib.getRandomNumber();
	
		//enter last name name	
		c.createContact(lastName);
		
		String result = ci.getHeaderInfoText().getText();
		
		//verify whether the contact has been creatred or not
		if(result.contains(lastName) )
		{
			String actlastName = ci.getContactNameText().getText();
			if(actlastName.contains(lastName))
				System.out.println("Contact has been added successfully === PASS");
			else
				System.out.println("Contact has not been added === FAIL");	
			
		}
		else
		{
			System.out.println("Contact has not been added === FAIL");
			Assert.fail();
		}
		
		h.logOut();
		
		wlib.quit(driver);
	}
	
}
