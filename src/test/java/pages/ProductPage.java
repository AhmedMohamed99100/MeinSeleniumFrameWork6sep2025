package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReportManager;

import java.time.Duration;

public class ProductPage {
   WebDriver driver;
   public ProductPage(WebDriver driver)
   {
	   this.driver=driver;
   }
	   //identify the Objects or Webelements in this page
	   By ProductChoosing = By.xpath("//div[@class='inventory_item_name ']");
	   
	   //Create User Defined Methods for the Objects
	   
	   public WebElement ProductChoosing() 
	   {
		   return driver.findElement(ProductChoosing); 
	   }  
	   
   }

