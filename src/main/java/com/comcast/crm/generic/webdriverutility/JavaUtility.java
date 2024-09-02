package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber()
	{
		Random ranDom = new Random();
		int randomNumber = ranDom.nextInt(5000);
		return randomNumber;
	}
	
	public String getDateInTheFormatyyyyMMdd()
	{
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dateObj);
		return date;
	}
	
	public String getRequiredDateInTheFormatyyyyMMdd(int days)
	{
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDdate = sim.format(cal.getTime());
		return reqDdate;
	}
}
