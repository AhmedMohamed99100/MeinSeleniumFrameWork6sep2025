package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReportManager;

import java.time.Duration;

public class LoginPage {
   WebDriver driver;
   public LoginPage(WebDriver driver)
   {
	   this.driver=driver;
   }
	   //identify the Objects or Webelements in this page
	   By UserName = By.id("user-name");
	   By PassWord = By.cssSelector("input#password");
	   By LoginButton = By.id("login-button");
	   
	   //Create User Defined Methods for the Objects
	   
	   public WebElement UserName() 
	   {
		   return driver.findElement(UserName); 
	   }
	   public WebElement PassWord() 
	   {
		   return driver.findElement(PassWord); 
	   }
	   public WebElement LoginButton() 
	   {
		   return driver.findElement(LoginButton); 
	   }
	    
	   
   }

