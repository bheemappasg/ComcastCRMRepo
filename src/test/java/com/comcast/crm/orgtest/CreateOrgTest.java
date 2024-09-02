package com.comcast.crm.orgtest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.genericutility.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

/**
 * 
 * @author Poornima
 * This class contains test cases to create organization through name, industry and phone
 * 
 */
@Listeners(com.comcast.crm.listenerTest.ListenerImplementation.class)
public class CreateOrgTest extends BaseClass {

	
	/**
	 * 
	 * @throws Exception
	 * This method is used to create the organization with mandatory field 
	 * i.e., Organization Name
	 */
	/*@Test(groups = {"smokeTest" , "regressionTest"} , retryAnalyzer = com.comcast.crm.listenerTest.RetryAnalyzerImplementation.class)*/
	@Test(groups = {"smokeTest" , "regressionTest"})
	public void createOrgWithOrgName() throws Exception {

		/* object creation for POM classes */
		HomePage h = new HomePage(driver);
		OrganizationsPage o = new OrganizationsPage(driver);
		CreatingNewOrganizationPage c = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		
		UtilityClassObject.getTest().log(Status.INFO, "User is able to open the browser");
		/* Step2 : Navigate to Organization Module*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization module");
		h.getOrgLink().click();
		/* Step 3 :click on Create Organization link*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization creation page");
		o.getCreateOrgBtn().click();
		/* Step 4 : Enter all the details and create organization*/
		UtilityClassObject.getTest().log(Status.INFO, "Import the Details from Excel file and create organization");
		
		String organizationName = elib.getDataFromExcelFile("CreateOrg", 1, 2) + jlib.getRandomNumber();
		c.createOrg(organizationName);
		System.out.println("Hello... welcome to Git");

		/* verify whether the organization has been creatred or not*/
		UtilityClassObject.getTest().log(Status.INFO, "verify whether organization is create or not");
		String result = oi.getHeaderInfoText().getText();
		boolean status = result.contains(organizationName);
		Assert.assertTrue(status);
		
		UtilityClassObject.getTest().log(Status.INFO, "verify the details");
		String actOrgName = oi.getOrgNameText().getText().trim();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actOrgName, organizationName);
		/*sa.assertEquals("hi", "bye");*/
		sa.assertAll();
	}
	
	/**
	 * 
	 * @throws Exception
	 * This method is used to create the Organization with Industry and Type
	 */
	@Test(groups = {"regressionTest"})
	public void createOrgWithIndustry() throws Exception
	{
		System.out.println("Hello... welcome to Git");
		
		/*object creation for POM classes*/
		HomePage h = new HomePage(driver);
		OrganizationsPage o = new OrganizationsPage(driver);
		CreatingNewOrganizationPage c = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		
		UtilityClassObject.getTest().log(Status.INFO, "User is able to open the browser");
		
		/* Step2 : Navigate to Organization Module */
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization Module");
		h.getOrgLink().click();		
		
		/*Step 3 :click on Create Organization link*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create organization page");
		o.getCreateOrgBtn().click();
		
		/*Step 4 : Enter all the details and create organization*/
		UtilityClassObject.getTest().log(Status.INFO, "Import the details from excel and create organization");
		String organizationName = elib.getDataFromExcelFile("CreateOrg", 1, 2)+jlib.getRandomNumber();
		
		/*enter Organization name, industry and type and click on save*/
		
		String industryData = elib.getDataFromExcelFile("CreateOrg", 4, 3);
		
		/*select the Type*/
		
		String typeData = elib.getDataFromExcelFile("CreateOrg", 4, 4);
		
		c.createOrg(organizationName,industryData, typeData );
		
		/*verify whether the organization has been creatred or not*/
		UtilityClassObject.getTest().log(Status.INFO, "verify whether the organization is created or not");
		String result = oi.getHeaderInfoText().getText();
		boolean status = result.contains(organizationName);
		Assert.assertTrue(status);
		System.out.println("hello");
		
		UtilityClassObject.getTest().log(Status.INFO, "verify the details");
		String actIndustry = oi.getIndustryText().getText().trim();
		String actType = oi.getTypeText().getText().trim();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actIndustry, industryData);
		sa.assertEquals(actType, typeData);
		sa.assertAll();
	}
	
	/**
	 * 
	 * @throws Exception
	 * This method is used to create an Organization with Phone
	 */
	@Test(groups = {"regressionTest"})
	public void createOrgWithPhone() throws Exception
	{
		/*object creation for POM classes*/
		HomePage h = new HomePage(driver);
		OrganizationsPage o = new OrganizationsPage(driver);
		CreatingNewOrganizationPage c = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "User is able to open the browser");
		
		/* Step2 : Navigate to Organization Module */
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to organizations module");
		h.getOrgLink().click();	
		
		/*Step 3 :click on Create Organization link*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create organizations page");
		o.getCreateOrgBtn().click();
		
		/*Step 4 : Enter all the details and create organization*/
		UtilityClassObject.getTest().log(Status.INFO, "Import the details from excel and create organization");
		String organizationName = elib.getDataFromExcelFile("CreateOrg", 7, 2)+jlib.getRandomNumber();
		String phone = elib.getDataFromExcelFile("CreateOrg", 7, 3);

		/*enter Organization name and phone */
		c.createOrg(organizationName, phone);
		
		/*verify whether the organization has been creatred or not*/
		UtilityClassObject.getTest().log(Status.INFO, "verify whether the organization is created or not");
		String result = oi.getHeaderInfoText().getText();
		boolean status = result.contains(organizationName);
		Assert.assertTrue(status);
		System.out.println("hi");
		UtilityClassObject.getTest().log(Status.INFO, "verify the details");
		String actphone = oi.getPhoneText().getText().trim();
		System.out.println(" close git ");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actphone, phone);
		sa.assertAll();
		
	}
}
