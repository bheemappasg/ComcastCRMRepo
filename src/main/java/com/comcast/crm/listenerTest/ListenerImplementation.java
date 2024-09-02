package com.comcast.crm.listenerTest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.genericutility.BaseClass;

public class ListenerImplementation extends BaseClass implements ITestListener,ISuiteListener {

	public ExtentReports report;
	public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		Reporter.log(" Report config ");
		
		String sysTime= new Date().toString().replace(":","_").replace(" ","_");
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReports/report_"+sysTime+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//Add environment information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Operating System", "Windows-10");
		report.setSystemInfo("Browser", "Chrome 100");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log(" Report Backup ");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String name = result.getMethod().getMethodName();
		test = report.createTest(name);
		test.log(Status.INFO, name +" test case execution started");
		UtilityClassObject.setTest(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		/*String sysTime= new Date().toString().replace(":","_").replace(" ","_");
		String name = result.getName()+"_"+sysTime;
		TakesScreenshot t=(TakesScreenshot) sdriver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File dest=new File("./ScreenShot/"+name+".png");
		try {
			FileUtils.copyFile(src, dest);
		} 
		catch (IOException e) {		
		}*/
		
		String sysTime= new Date().toString().replace(":","_").replace(" ","_");
		String name = result.getMethod().getMethodName();
		TakesScreenshot t=(TakesScreenshot) UtilityClassObject.getSdriver();
		String src = t.getScreenshotAs(OutputType.BASE64);
		
		test.addScreenCaptureFromBase64String(src,name+"_"+sysTime);
		test.log(Status.FAIL, name+"== Failed ===");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}
}
