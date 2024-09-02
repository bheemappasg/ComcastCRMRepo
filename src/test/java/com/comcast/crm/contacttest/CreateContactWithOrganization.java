package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.objectrepositoryutility.OrganizationsSearch;

public class CreateContactWithOrganization {

	@Test
	public void createContactWithOrganization() throws Exception
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
		OrganizationsPage o = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		OrganizationsSearch os = new OrganizationsSearch(driver);
		
		l.setLogin(username, password);
		
		// Step2 : Navigate to Organization Module 
		h.getOrgLink().click();
		
		//Step 3 :click on Create Organization link
		o.getCreateOrgBtn().click();
		
		//Step 4 : Enter all the details and create organization

		String organizationName = elib.getDataFromExcelFile("Contact", 7, 2)+jlib.getRandomNumber();
		String lastName = elib.getDataFromExcelFile("Contact", 7, 3)+jlib.getRandomNumber();
		
		//enter Organization name
		cno.createOrg(organizationName);
		
		String result = oi.getHeaderInfoText().getText();
		
		//verify whether the organization has been creatred or not
		if(result.contains(organizationName) )
		{
			String actOrgName = oi.getOrgNameText().getText();
			if(actOrgName.equals(organizationName))
				System.out.println(organizationName+" has been added successfully === PASS");
			else
				System.out.println(organizationName+" has not been added === FAIL");	
		}
		else
		{
			System.out.println(organizationName+" has not been added");	
			Assert.fail();
		}		
		
		//// create contact with organization name script starts here... 
		//create organization here is a pre condition
		h.getContactLink().click();
		
		//Step 5 :click on Create Contact link
		con.getCreateContactBtn().click();
		
		//Step 6 : Enter all the details and create contact

		//enter last name name
		c.getLastNametbx().sendKeys(lastName);
		c.getOrgLookupBtn().click();
		
		String parentWindow = wlib.switchNewDriverTab(driver, "");
	
		os.getOrgSearchtbx().sendKeys(organizationName);
		os.getSearchBtn().click();	
		
		driver.findElement(By.xpath("//a[text()='"+organizationName+"']")).click();
		
		driver.switchTo().window(parentWindow);
		
		c.getSaveBtn().click();
		
		String result1 = ci.getHeaderInfoText().getText();
		
		//verify whether the contact has been created or not
		if(result1.contains(lastName) )
		{
			String actOrgName = ci.getOrgNameText().getText();
			if(actOrgName.contains(organizationName))
				System.out.println("Contact has been added successfully with orgsnization === PASS");
			else
				System.out.println("Contact has not been added with orgsnization === FAIL");	
		}
		else
		{
			System.out.println("Contact has not been added with organization === FAIL");
			Assert.fail();
		}

		h.logOut();
		
		wlib.quit(driver);
	}
}
