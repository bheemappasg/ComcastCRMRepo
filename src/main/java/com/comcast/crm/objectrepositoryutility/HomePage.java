package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
	
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement accountLookUPBtn;
	
	@FindBy(partialLinkText="Sign Out")
	private WebElement signOutLink;
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getOrgLink() {
		return orgLink;
	}
	
	public WebElement getContactLink() {
		return contactLink;
	}
	
	public void logOut()
	{
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.mouseMoveOnElement(driver, accountLookUPBtn);
		signOutLink.click();
	}
}
