package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReportManager;

import java.time.Duration;

public class CheckOutpage {
   WebDriver driver;
   public CheckOutpage(WebDriver driver)
   {
	   this.driver=driver;
   }
	   //identify the Objects or Webelements in this page
	   By CheckOutButton = By.id("checkout");
	   By FirstName = By.id("first-name");
	   By LastName = By.id("last-name");
	   By PostalCode = By.name("postalCode");
	   By CotinueButton = By.name("continue");
	   
	   
	   //Create User Defined Methods for the Objects
	   
	   public WebElement CheckOutButton() 
	   {
		   return driver.findElement(CheckOutButton); 
	   }  
	   public WebElement FirstName() 
	   {
		   return driver.findElement(FirstName); 
	   }  
	   public WebElement LastName() 
	   {
		   return driver.findElement(LastName); 
	   }
	   public WebElement PostalCode() 
	   {
		   return driver.findElement(PostalCode); 
	   }
	   public WebElement CotinueButton() 
	   {
		   return driver.findElement(CotinueButton); 
	   }  
	   
   }

