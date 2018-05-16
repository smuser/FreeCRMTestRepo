package com.crm.qa.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage; // LoginPage objectrepo
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "Contacts";

	//All test cases should be independent of each other
	//Before each test case launch the browser and login
	//Test execution
	//After each test case close browser
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(1000);
		contactsPage = homePage.clickOnContactsPage();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest()
	{
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle,"CRMPRO", "Home Page Title mismatch.");
	}
	
	@Test(priority=2)
	public void verifyContactsLinkTest()
	{
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts link not available");
	}
	
	/*@Test(priority=3)
	public void validateCreateNewContact()
	{
		homePage.clickOnNewContactsLink();
		contactsPage.createNewContact("Mr.","Tom","Peter","Google");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() throws InvalidFormatException, IOException {
		Object data[][]= TestUtil.getTestData(sheetName);
		return data; 	
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company)
	{ 
		homePage.clickOnNewContactsLink();
		contactsPage.createNewContact(title,firstName,lastName, company);
	}*/
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
