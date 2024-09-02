package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerInfoText;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgNameText;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industryText;
	
	@FindBy(id="dtlview_Type")
	private WebElement typeText;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phoneText;
	
	public OrganizationInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getHeaderInfoText() {
		return headerInfoText;
	}
	
	public WebElement getOrgNameText() {
		return orgNameText;
	}
	
	public WebElement getIndustryText() {
		return industryText;
	}

	public WebElement getTypeText() {
		return typeText;
	}
	
	public WebElement getPhoneText() {
		return phoneText;
	}
}
