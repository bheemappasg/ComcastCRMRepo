package com.comcast.crm.generic.fileutility;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	private static final CellType String = null;

	public String getDataFromExcelFile(String sheetName, int rowCount, int cellCount) throws Exception
	{
		FileInputStream fis = new FileInputStream("./testdata/Testscript.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowCount).getCell(cellCount).getStringCellValue();
		
		return data;
	}
	
	public int getLastRowNum(String sheetName) throws Exception
	{
		FileInputStream fis = new FileInputStream("./testdata/Testscript.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		int rowNum = wb.getSheet(sheetName).getLastRowNum();
		return rowNum;
		
	}
	
	public void setDataBackToExcelFile(String sheetName, int rowCount, int cellCount,String data) throws Exception
	{
		FileInputStream fis = new FileInputStream("./testdata/Testscript.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowCount).createCell(cellCount, String);
		wb.getSheet(sheetName).getRow(rowCount).getCell(cellCount).setCellValue(data);
		
		FileOutputStream fout = new FileOutputStream("./testdata/Testscript.xlsx");
		wb.write(fout);
		
	}
}
