package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewOrganizationPage {
	
	@FindBy(name="accountname")
	private WebElement orgNametbx;
	
	@FindBy(name="industry")
	private WebElement industrySelBx;
	
	@FindBy(name="accounttype")
	private WebElement typeSelBx;
	
	@FindBy(name="phone")
	private WebElement phonetbx;
	
	@FindBy(xpath="//input[@value='  Save  ']")
	private WebElement saveBtn;
	
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgNametbx() {
		return orgNametbx;
	}
	
	public WebElement getIndustrySelBx() {
		return industrySelBx;
	}
	
	public WebElement getTypeSelBx() {
		return typeSelBx;
	}
	
	public WebElement getPhonetbx() {
		return phonetbx;
	}
	
	public WebElement getSaveBtn(){
		return saveBtn;
	}
	
	public void createOrg(String organizationName)
	{
		orgNametbx.sendKeys(organizationName);	
		saveBtn.click();	
	}
	
	public void createOrg(String organizationName,String phone)
	{
		orgNametbx.sendKeys(organizationName);	
		phonetbx.sendKeys(phone);
		saveBtn.click();	
	}
	
	public void createOrg(String organizationName,String industryData, String typeData)
	{
		orgNametbx.sendKeys(organizationName);	
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.select(industrySelBx, industryData);
		wlib.select(typeSelBx, typeData);
		saveBtn.click();	
	}	
}
