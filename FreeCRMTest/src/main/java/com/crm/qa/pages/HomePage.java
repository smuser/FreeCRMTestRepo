package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'User: Naveen K')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[@title='Contacts']")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals)]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks)]")
	WebElement tasksLink;
	
	@FindBy(xpath="//a[@title='New Contact']")
	WebElement newContactLink;
	
	//Initializing Page Objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean verifyCorrectUserName()
	{
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsPage()
	{
		contactsLink.click();
		return new ContactsPage(); // please return the contacts page object as that is landing
		
		//If we are clicking on button or link that method should return landing page object
	}
	
	public void clickOnNewContactsLink()
	{
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
	
	public DealsPage clickOnDealsPage()
	{
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksPage()
	{
		tasksLink.click();
		return new TasksPage();
	}
	
	

}
