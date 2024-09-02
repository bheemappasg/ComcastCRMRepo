package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	@FindBy(name="lastname")
	private WebElement lastNametbx;
	
	@FindBy(name="support_start_date")
	private WebElement supportStartDatetbx;
	
	@FindBy(name="support_end_date")
	private WebElement supportEndDatetbx;
	
	@FindBy(name="account_name")
	private WebElement orgNametbx;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement orgLookupBtn;
	
	@FindBy(xpath="//input[@value='  Save  ']")
	private WebElement saveBtn;
	
	public CreatingNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastNametbx() {
		return lastNametbx;
	}
	
	public WebElement getSupportStartDatetbx() {
		return supportStartDatetbx;
	}

	public WebElement getSupportEndDatetbx() {
		return supportEndDatetbx;
	}
	
	public WebElement getOrgNametbx() {
		return orgNametbx;
	}
	
	public WebElement getOrgLookupBtn() {
		return orgLookupBtn;
	}
	
	public WebElement getSaveBtn(){
		return saveBtn;
	}
	
	public void createContact(String lastName)
	{
		lastNametbx.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createContact(String lastName,String startDate, String endDate)
	{
		lastNametbx.sendKeys(lastName);
		supportStartDatetbx.sendKeys(startDate);
		supportEndDatetbx.sendKeys(endDate);
		saveBtn.click();
	}
	
}
