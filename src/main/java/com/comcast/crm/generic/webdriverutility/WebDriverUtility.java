package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public String switchNewDriverTab(WebDriver driver,String partialUrl)
	{
		Set<String> allWh = driver.getWindowHandles();
		String pwh = driver.getWindowHandle();
		
		for(String wh : allWh)
		{
			driver.switchTo().window(wh);
			if(!(wh.equals(pwh)))
			{
				break;
			}
		}
		return pwh;
	}
	
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,String attrVal)
	{
		driver.switchTo().frame(attrVal);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	public void select(WebElement element, String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	public void selectByVal(WebElement element, String text)
	{
		Select sel = new Select(element);
		sel.selectByValue(text);
	}
	
	public List<WebElement> getOptions(WebElement element)
	{
		Select sel = new Select(element);
		List<WebElement> allOpt = sel.getOptions();
		return allOpt;
	}
	public boolean isMultiple(WebElement element)
	{
		Select sel = new Select(element);
		if(sel.isMultiple())
			return true;
		else
			return false;
	}
	
	public WebElement getFirstSelectedOption(WebElement element)
	{
		Select sel = new Select(element);
		WebElement ele=null;
		if(sel.isMultiple())
		{
			ele = sel.getFirstSelectedOption();
		}
		return ele;
	}
	
	public List<WebElement> getAllSelectedOptions(WebElement element)
	{
		Select sel = new Select(element);
		List<WebElement> ele=null;
		if(sel.isMultiple())
		{
			ele = sel.getAllSelectedOptions();
		}
		return ele;
	}
	
	
	
	public void deSelect(WebElement element,String text)
	{
		Select sel = new Select(element);
	
		if(sel.isMultiple())
		{
			sel.deselectByVisibleText(text);
		}	
	}
	
	public void deSelect(WebElement element,int index)
	{
		Select sel = new Select(element);
	
		if(sel.isMultiple())
		{
			sel.deselectByIndex(index);
		}	
	}
	
	public void deSelectByVal(WebElement element,String text)
	{
		Select sel = new Select(element);
	
		if(sel.isMultiple())
		{
			sel.deselectByValue(text);
		}	
	}
	
	public void deSelectAll(WebElement element)
	{
		Select sel = new Select(element);
	
		if(sel.isMultiple())
		{
			sel.deselectAll();
		}	
	}
	
	public WebElement getWrappedElement(WebElement element)
	{
		Select sel = new Select(element);
		WebElement ele = sel.getWrappedElement();
		return ele;
	}
		
	public void mouseMoveOnElement(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
	}
	
	public void rightClickOnElement(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).build().perform();
	}
	
	public void doubleClickOnElement(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).build().perform();
	}
	
	public void dragAndDropElement(WebDriver driver,WebElement source,WebElement target)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(source,target).build().perform();
	}
	
	public void scrollToElement(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.scrollToElement(element);
	}
	
	public void scrollByAmount(WebDriver driver, int xAxis,int yAxis)
	{
		Actions act = new Actions(driver);
		act.scrollByAmount(xAxis, yAxis);
	}
	
	public void getX(WebElement element)
	{
		element.getLocation().getX();
	}
	
	public void getY(WebElement element)
	{
		element.getLocation().getY();
	}
	
	public String getTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver)
	{
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver)
	{
		return driver.getPageSource();
	}
	
	public String getAttribute(WebElement element,String attrName)
	{
		return element.getAttribute(attrName);
	}
	public String getText(WebElement element)
	{
		return element.getText();
	}
	
	public String getTagName(WebElement element)
	{
		return element.getTagName();
	}
	
	public String getCssValue(WebElement element,String propName)
	{
		return element.getCssValue(propName);
	}
	
	public void maximize(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public void minimize(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	public void get(WebDriver driver, String url)
	{
		driver.get(url);
	}
	
	public void quit(WebDriver driver)
	{
		driver.quit();
	}
	
	public WebDriver getBrowser(WebDriver driver , String browser)
	{
		browser = browser.toLowerCase();
		
		switch(browser)
		{
			case "chrome" : {
				driver = new ChromeDriver();
				break;
			}
			case "firefox" : {
				driver = new FirefoxDriver();
				break;
			}
			case "edge" : {
				driver = new EdgeDriver();
				break;
			}
			case "safari" : {
				driver = new SafariDriver();
				break;
			}
			default :
			{
				driver = new ChromeDriver();
			}
		}
		
		return driver;
	}
	
	public void getScreenShot(WebDriver driver) throws Exception
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileInputStream dest = new FileInputStream("./screenshot/ss.png");
		FileUtils.copyFile(src, src);
	}
}
