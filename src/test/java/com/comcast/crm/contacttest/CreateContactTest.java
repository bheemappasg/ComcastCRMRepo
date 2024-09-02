package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.comcast.crm.genericutility.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsSearch;

@Listeners(com.comcast.crm.listenerTest.ListenerImplementation.class)
public class CreateContactTest extends BaseClass {

	@Test(groups = {"smokeTest" , "regressionTest"})
	public void createContactWithName() throws Exception
	{
		//object creation for POM classes
		HomePage h = new HomePage(driver);
		ContactsPage con = new ContactsPage(driver);
		CreatingNewContactPage c = new CreatingNewContactPage(driver);
		ContactInformationPage ci = new ContactInformationPage(driver);
		
		// Step2 : Navigate to Contacts Module 
		h.getContactLink().click();
		
		//Step 3 :click on Create Contact link
		con.getCreateContactBtn().click();
		
		//Step 4 : Enter all the details and create organization
	
		String lastName = elib.getDataFromExcelFile("Contact", 4, 2)+jlib.getRandomNumber();
	
		//enter last name name	
		c.createContact(lastName);
		
		//verify whether the contact has been creatred or not
		String result = ci.getHeaderInfoText().getText();
		boolean status = result.contains(lastName);
		Assert.assertTrue(status);
		
		String actlastName = ci.getContactNameText().getText().trim();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actlastName, lastName);
		sa.assertAll();
	}
	
	@Test(groups = {"regressionTest"})
	public void createContactWithSuppDate() throws Exception
	{
		//object creation for POM classes
		HomePage h = new HomePage(driver);
		ContactsPage con = new ContactsPage(driver);
		CreatingNewContactPage c = new CreatingNewContactPage(driver);
		ContactInformationPage ci = new ContactInformationPage(driver);
				
		// Step2 : Navigate to Contacts Module 
		h.getContactLink().click();
		
		//Step 3 :click on Create Contact link
		con.getCreateContactBtn().click();
		
		//Step 4 : Enter all the details and create organization
		String lastName = elib.getDataFromExcelFile("Contact", 4, 2)+jlib.getRandomNumber();
		
		c.getSupportEndDatetbx().clear();
		c.getSupportEndDatetbx().clear();
	
		String suppStartDate = jlib.getDateInTheFormatyyyyMMdd();
		String suppEndDate = jlib.getRequiredDateInTheFormatyyyyMMdd(30);
		
		c.createContact(lastName, suppStartDate, suppEndDate);
		
		//verify whether the contact has been creatred or not
		String result = ci.getHeaderInfoText().getText().trim();
		boolean status = result.contains(lastName);
		Assert.assertTrue(status);
		
		String actSuppStartDate = ci.getSupportStartDateText().getText().trim();
		String actSuppEndDate = ci.getSupportEndDateText().getText().trim();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actSuppStartDate, suppStartDate);
		sa.assertEquals(actSuppEndDate, suppEndDate);
		sa.assertAll();
	}
	
	@Test(groups = {"regressionTest"})
	public void createContactWithOrganization() throws Exception
	{
		//object creation for POM classes
		HomePage h = new HomePage(driver);
		ContactsPage con = new ContactsPage(driver);
		CreatingNewContactPage c = new CreatingNewContactPage(driver);
		ContactInformationPage ci = new ContactInformationPage(driver);
		OrganizationsPage o = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		OrganizationsSearch os = new OrganizationsSearch(driver);
		
		// Step2 : Navigate to Organization Module 
		h.getOrgLink().click();
		
		//Step 3 :click on Create Organization link
		o.getCreateOrgBtn().click();
		
		//Step 4 : Enter all the details and create organization

		String organizationName = elib.getDataFromExcelFile("Contact", 7, 2)+jlib.getRandomNumber();
		String lastName = elib.getDataFromExcelFile("Contact", 7, 3)+jlib.getRandomNumber();
		
		//enter Organization name
		cno.createOrg(organizationName);
		
		//verify whether the organization has been creatred or not
		String result = ci.getHeaderInfoText().getText();
		boolean status = result.contains(organizationName);
		Assert.assertTrue(status);
		
		String actOrgName = oi.getOrgNameText().getText().trim();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actOrgName, organizationName);
		sa.assertAll();
		
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
		
		//verify whether the contact has been created or not
		String result1 = ci.getHeaderInfoText().getText().trim();
		boolean status1 = result1.contains(lastName);
		Assert.assertTrue(status1);
		
		String actLastName = ci.getContactNameText().getText().trim();
		String actOrgName1 = ci.getOrgNameText().getText().trim();
		sa.assertEquals(actLastName, lastName);
		sa.assertEquals(actOrgName, organizationName);
		sa.assertAll();
	}
}
