package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReportManager;

import java.time.Duration;

public class CompleteMessagePage {
   WebDriver driver;
   public CompleteMessagePage(WebDriver driver)
   {
	   this.driver=driver;
   }
	   //identify the Objects or Webelements in this page
	  
	   By CompleteMessage = By.className("complete-header");
	   By BackToHomeButton = By.name("back-to-products");

	   
	   
	   //Create User Defined Methods for the Objects
	   
	   public WebElement CompleteMessage() 
	   {
		   return driver.findElement(CompleteMessage); 
		   
	   }  
	   public WebElement BackToHomeButton() 
	   {
		   return driver.findElement(BackToHomeButton); 
		   
	   }  
	   
   }

