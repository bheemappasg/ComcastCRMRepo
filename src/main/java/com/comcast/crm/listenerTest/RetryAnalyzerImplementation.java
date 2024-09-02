package com.comcast.crm.listenerTest;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImplementation implements IRetryAnalyzer {

	int count=0;
	int limitCount=4;
	@Override
	public boolean retry(ITestResult result) {
		if(count<limitCount)
		{
			count++;
			return true;
		}
		else
			return false;
	}

}
