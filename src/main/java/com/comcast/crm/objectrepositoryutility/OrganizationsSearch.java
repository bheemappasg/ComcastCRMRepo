package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsSearch {

	@FindBy(id="search_txt")
	private WebElement orgSearchtbx;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	public OrganizationsSearch(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgSearchtbx() {
		return orgSearchtbx;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	
	
	
}
