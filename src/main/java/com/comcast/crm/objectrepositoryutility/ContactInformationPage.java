package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {

	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerInfoText;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement contactNameText;
	
	@FindBy(id="mouseArea_Support Start Date")
	private WebElement supportStartDateText;
	
	@FindBy(id="mouseArea_Support End Date")
	private WebElement supportEndDateText;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgNameText;
	
	public ContactInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getHeaderInfoText() {
		return headerInfoText;
	}
	
	public WebElement getContactNameText() {
		return contactNameText;
	}
	
	public WebElement getSupportStartDateText() {
		return supportStartDateText;
	}

	public WebElement getSupportEndDateText() {
		return supportEndDateText;
	}

	public WebElement getOrgNameText() {
		return orgNameText;
	}
}
