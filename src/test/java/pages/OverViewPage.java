package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReportManager;

import java.time.Duration;

public class OverViewPage {
   WebDriver driver;
   public OverViewPage(WebDriver driver)
   {
	   this.driver=driver;
   }
	   //identify the Objects or Webelements in this page
	  
	   By FinishButton = By.name("finish");
	  
	   
	   
	   //Create User Defined Methods for the Objects
	   
	   public WebElement FinishButton() 
	   {
		   return driver.findElement(FinishButton); 
	   }  
	   
   }

