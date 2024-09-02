package com.comcast.crm.orgtest;




import org.openqa.selenium.WebDriver;
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

public class createOrgWithOrgNameTest {

	@Test
	public void createOrgWithOrgName() throws Exception
	{
		WebDriver driver=null;
		
		//Step 1 : Login to the application by reading data from property file
		
		//// onject creation for utility classes
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		System.out.println("hi");
		
		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
		
		driver = wlib.getBrowser(driver, browser);

		wlib.maximize(driver);
		wlib.waitForPageToLoad(driver);

		wlib.get(driver,url);
	
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
		
		String organizationName = elib.getDataFromExcelFile("CreateOrg", 1, 2)+jlib.getRandomNumber();
		c.createOrg(organizationName);
		//enter Organization name
		
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
		
		h.logOut();
		
		wlib.quit(driver);
	}
}
