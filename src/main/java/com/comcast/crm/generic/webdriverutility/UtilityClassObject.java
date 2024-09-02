package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> sdriver = new ThreadLocal<WebDriver>();
	
	public static ExtentTest getTest()
	{
		return test.get();
	}
	
	public static WebDriver getSdriver()
	{
		return sdriver.get();
	}
	
	public static void setTest(ExtentTest actTest)
	{
		test.set(actTest);
	}
	
	public static void setSdriver(WebDriver actSdriver)
	{
		sdriver.set(actSdriver);
	}
}
