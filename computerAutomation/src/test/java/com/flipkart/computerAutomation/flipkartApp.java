package com.flipkart.computerAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class flipkartApp
{
	static WebDriver driver;
	private static String price;

	@BeforeClass
	public void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com");
	}

  @Test(priority = 0)
  public void searchComputers() throws InterruptedException
  { 
	  WebDriverWait wait = new WebDriverWait(driver, 40);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title='Search for Products, Brands and More']")));
	  driver.findElement(By.xpath("//div[@class='O8ZS_U']/input")).sendKeys("laptops");
	  driver.findElement(By.xpath("//div[@class='O8ZS_U']/input")).sendKeys(Keys.ENTER);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title='HP']//div[@class='_1p7h2j']")));
	  Thread.sleep(1000);
	  JavascriptExecutor jse = (JavascriptExecutor)driver;
	  jse.executeScript("window.scrollBy(0,250)", "");
	  driver.findElement(By.xpath("//div[@title='HP']//div[@class='_1p7h2j']")).click();
  }
  
  @Test(priority = 1)
  public void searchHPComputers() throws InterruptedException
  {
	  Thread.sleep(2000);
	  WebDriverWait wait1 = new WebDriverWait(driver, 40);
	  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='Newest First']")));
	  Thread.sleep(1000);
	  List<WebElement> element =  driver.findElements(By.xpath("//div[@class='_1vC4OE _2rQ-NK']"));
	  for(WebElement web : element)
	  {
		 String amount =  web.getText();
		 int length = amount.length();
		 price = amount.substring(1, length);
		 System.out.println("Amount : "+ price);
		 Thread.sleep(1000);
		 if(price.equals("78,990"))
		 {
			 JavascriptExecutor jse = (JavascriptExecutor)driver;
			  jse.executeScript("window.scrollBy(0,250)", "");
			  Thread.sleep(2000); 
			 driver.findElement(By.xpath("//div[@class='_1vC4OE _2rQ-NK'][text()='78,990']")).click();
			 Thread.sleep(2000);
			 break;
		 }
		 /*else
		 {
			 hpFirst = driver.findElement(By.xpath("//div[@class='_3T_wwx']"
			 		+ "//div[@class='col _2-gKeQ'][1]//div[@class='_1vC4OE _2rQ-NK']")).getText();
			 driver.findElement(By.xpath("//div[@class='_3T_wwx']//div[@class='col _2-gKeQ'][1]")).click();
			 Thread.sleep(2000);
			 break;
		 }*/
	  }
	
	  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")));
  }
  
  
  @Test(priority = 2)
  public void addToCart() throws InterruptedException
  {
	  String currentPrice = driver.findElement(By.xpath("//div[@class='_1vC4OE _37U4_g']")).getText();
	  int currentLen = currentPrice.length();
	  String price1 = currentPrice.substring(1, currentLen);
	  if(price1.equals(price))
	  {
		  JavascriptExecutor jse = (JavascriptExecutor)driver;
		  jse.executeScript("window.scrollBy(0,250)", "");
		  Thread.sleep(2000); 
		  driver.findElement(By.xpath("//li[button[@class='_2AkmmA _2Npkh4 _2MWPVK']]")).click();
		  WebDriverWait wait2 = new WebDriverWait(driver, 40);
		  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2AkmmA _14O7kc mrmU5i']")));  
	  }
	 System.out.println("CurrentPrice : "+price);
	 
	 
  }

  
  @AfterClass
  public void closeBrowser() throws InterruptedException
  {
	  Thread.sleep(2000);
	  driver.close();
	  driver.quit();
  }
  
}
