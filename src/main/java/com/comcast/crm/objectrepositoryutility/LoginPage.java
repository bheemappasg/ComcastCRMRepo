package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement untbx;

	@FindBy(name = "user_password")
	private WebElement pwtbx;

	@FindBy(id = "submitButton")
	private WebElement lgBtn;

	public void setLogin(String username, String password) {
		untbx.sendKeys(username);
		pwtbx.sendKeys(password);
		lgBtn.click();
	}

	public WebElement getUntbx() {
		return untbx;
	}

	public WebElement getPwtbx() {
		return pwtbx;
	}

	public WebElement getLgBtn() {
		return lgBtn;
	}
}
