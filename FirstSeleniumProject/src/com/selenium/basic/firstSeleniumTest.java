package com.selenium.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class firstSeleniumTest {

	// *************************************************************
	// -------------->Implementing Properties<----------------------
	//**************************************************************
	WebDriver driver = null;
	static Properties p = readProperties();	
	public static Properties readProperties() {
		File file = new File("project.properties");
		  
		FileInputStream fileInput = null;
		
		try {
			fileInput = new FileInputStream(file);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Properties p = new Properties();
		
		//**************************************************************
		// ---------------->Load Properties File<-----------------------
		//**************************************************************
		try {
			p.load(fileInput);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	
	// **********************************************************************
	// -------------------->Select One Browser<------------------------------
	// -------------------->Runs Before Test Method<-------------------------
	// **********************************************************************
	@BeforeMethod
	public void OpenBrowser() {
		String browser = p.getProperty("browser");
		
		// *********************************************************
		// -----------------> EDGE <-----------------------------
		// *********************************************************
		if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Users\\Sid\\eclipse-workspace\\FirstSeleniumProject\\drivers\\msedgedriver.exe");
			driver=new EdgeDriver();
			driver.manage().window().maximize();									
		}
		
		
		// ********************************************************
		// -----------------> Chrome <-----------------------------
		// ********************************************************
else
{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\Sid\\eclipse-workspace\\FirstSeleniumProject\\drivers\\chromedriver.exe");
	ChromeOptions co = new ChromeOptions();
	driver = new ChromeDriver(co);
	driver.manage().window().maximize();
	co.addArguments("--disable-notifications");
	
}
	}
	
	
	// **********************************************************************
	// ------------------------>Closing Broswer<-----------------------------
	// ------------------------>Runs after Test Method<----------------------
	// **********************************************************************
	@AfterMethod
	public void quitBrowser() {
		  
		driver.quit();
	}
	
	
@Test

public void test1() throws IOException

	{		
			// **********************************************
			//--------------->Website name<------------------
			// **********************************************
			String url = p.getProperty("url");			
			driver.get(url);
			System.out.println("Website Name " + url );
		
			
			
			// ***********************************************
			//-------------->Set Mumbai Location<-------------
			// ***********************************************
			driver.findElement(By.xpath("//input[@id= 'autoComplete__home']")).click();			
			driver.findElement(By.xpath("//input[@id= 'autoComplete__home']")).sendKeys("Mumbai");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);						
			driver.findElement(By.xpath("//div[@class='oyo-cell oyo-cell--12-col oyo-cell--8-col-tablet oyo-cell--4-col-phone geoSuggestionsList__location u-textEllipsis ']")).click();
			
			
			
			// **********************************************
			// ---------------->Select Date<-----------------
			// **********************************************						
			driver.findElement(By.xpath("//div[@class='oyo-row oyo-row--no-spacing datePickerDesktop datePickerDesktop--home']")).click();
			driver.findElement(By.xpath("//span[@class='DateRangePicker__DateLabel' and contains(text(),'15')]")).click();
			driver.findElement(By.xpath("//span[@class='DateRangePicker__DateLabel' and contains(text(),'17')]")).click();
			

			
			// **************************************************
			// --------------->Click On Search<------------------
			// **************************************************
			driver.findElement(By.xpath("//button[@class= 'u-textCenter searchButton searchButton--home']")).click();
			
			
			
			// ***************************************************
			// -------------->Set Price 2000-3000<----------------
			// ***************************************************
			WebElement slider_min =driver.findElement(By.xpath("//span[@class ='input-range__slider-container']/div"));
			Actions move1 = new Actions(driver);
			move1.dragAndDropBy(slider_min, 170, 0).build().perform();
		
			
		
			// *******************************************************************************************
			// --------------->  Print On Console Top 5 Recommended Hotels With Price <-------------------
			// *******************************************************************************************
			List<WebElement> product=driver.findElements(By.xpath("//div[@class='oyo-row oyo-row--no-spacing ListingHotelCardWrapper']/descendant::h3"));
			List<WebElement> price=driver.findElements(By.xpath("//div[@class='oyo-row oyo-row--no-spacing ListingHotelCardWrapper']/descendant::span[@class= 'listingPrice__finalPrice']"));
			 for(int i=0;i<5;i++)
		     {System.err.println(i+1+". "+product.get(i).getAttribute("title")+"  "+price.get(i).getText());}
		    
		   			
	}

}
