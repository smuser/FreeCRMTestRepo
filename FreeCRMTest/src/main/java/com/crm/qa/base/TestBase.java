package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase()
	{
		try
		{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"D:\\Workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void initialization()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			//Path set for webdriver binaries
			/*System.setProperty("webdriver.chrome.driver", "D:\\SofwareSelenium\\jarfiles\\chromedriver.exe");
			driver = new ChromeDriver();*/
			
			//Another way where the latest exe will automaticlay be downloaded no need to download and place in path for webdriver binaries
			ChromeDriverManager.getInstance().setup();
			driver = new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("FF"))
		{
			///System.setProperty("webdriver.gecko.driver", "D:\\SofwareSelenium\\jarfiles\\drivers\\geckodriver.exe");
			FirefoxDriverManager.getInstance().setup();
			driver = new FirefoxDriver();
		}
				
		//Registering webdriver event and wrapping driver in event driver and loggin each event
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
				
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS); // taking values from test util class
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
	
		driver.get(prop.getProperty("url"));
	}
}


