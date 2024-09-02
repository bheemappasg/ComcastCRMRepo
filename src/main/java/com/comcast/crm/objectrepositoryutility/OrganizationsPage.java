package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrgBtn;
	
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateOrgBtn() {
		return createOrgBtn;
	}
}
