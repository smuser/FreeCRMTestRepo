package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 30;
	public static String TESTDATA_SHEET_PATH = "D:\\Workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMTestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	static int rowcount = 0;
	static FileUtils fileutil;

	public void switchToFrame() {
		driver.switchTo().frame("mainPanel");
	}

	public static Object[][] getTestData(String sheetName)
			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		FileInputStream fileio = null;
		try {
			fileio = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		book = WorkbookFactory.create(fileio);
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;

	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		// TODO Auto-generated method stub

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		// File destFile= new File(currentDir + "/screenshot/" +
		// System.currentTimeMillis() + ".png");
		// fileutil.copyFile(scrFile, destFile);

		FileUtils.copyFile(srcFile, new File(currentDir + "/screenshot/" + System.currentTimeMillis() + ".png"));
		// FileHandler.copyFile(srcFile, new
		// File("D:\\Workspace\\FreeCRMTest\\screenshot")+".png");

	}

	public static void highlightElementForTest(WebElement element) {
		// Create the JavascriptExecutor object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// call the executeScript method
		js.executeScript("arguments[0].setAttribute('style', 'background: light yellow; border: 2px solid red;');",
				element);
	}

}
