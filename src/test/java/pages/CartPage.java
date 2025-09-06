package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReportManager;

import java.time.Duration;

public class CartPage {
   WebDriver driver;
   public CartPage(WebDriver driver)
   {
	   this.driver=driver;
   }
	   //identify the Objects or Webelements in this page 
	   By AddToCartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
	   By AddBikeLightToCartButton = By.id("add-to-cart-sauce-labs-bike-light");
	   By AddBoltTShirtToCartButton = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
	   By CartIcon = By.xpath("//a[@class='shopping_cart_link']");
	   
	   
	   //Create User Defined Methods for the Objects
	   
	   public WebElement AddToCartButton() 
	   {
		   return driver.findElement(AddToCartButton); 
	   }  
	   public WebElement CartIcon() 
	   {
		   return driver.findElement(CartIcon); 
	   }  
	   public WebElement AddBikeLightToCartButton() {
		    return driver.findElement(AddBikeLightToCartButton);
		}
	   public WebElement AddBoltTShirtToCartButton() {
		    return driver.findElement(AddBoltTShirtToCartButton);
		}
	   
   }

