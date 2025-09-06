package testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import pages.CartPage;
import pages.CheckOutpage;
import pages.CompleteMessagePage;
import pages.LoginPage;
import pages.OverViewPage;

public class PurchaseSauceLabsBikeLightTest {
    WebDriver driver;
    LoginPage LP;
    CartPage CartP;
    CheckOutpage CheckP;
    OverViewPage OVP;
    CompleteMessagePage CMP;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) throws IOException {
        if (browser.equalsIgnoreCase("FireFox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        takeScreenshot("Startseite");
    }

    @Test(priority = 2)
    public void PurchaseSauceLabsBikeLightTest() throws InterruptedException {
        try {
            Thread.sleep(2000);
            // Login
            LP = new LoginPage(driver);
            LP.UserName().sendKeys("standard_user");
            LP.PassWord().sendKeys("secret_sauce");
            LP.LoginButton().click();
            Thread.sleep(3000);
            takeScreenshot("Nach_Login");

            // Add Bike Light to cart
            CartP = new CartPage(driver);
            CartP.AddBikeLightToCartButton().click();
            takeScreenshot("Nach_Produkt_Hinzufuegen");
            CartP.CartIcon().click();
            Thread.sleep(2000);
            takeScreenshot("Warenkorb_Anzeigen");

            // Checkout
            CheckP = new CheckOutpage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            CheckP.CheckOutButton().click();
            Thread.sleep(2000);
            takeScreenshot("Checkout_Seite");

            wait.until(ExpectedConditions.visibilityOf(CheckP.FirstName())).sendKeys("Ahmed");
            wait.until(ExpectedConditions.visibilityOf(CheckP.LastName())).sendKeys("Saied");
            wait.until(ExpectedConditions.visibilityOf(CheckP.PostalCode())).sendKeys("6228");
            wait.until(ExpectedConditions.elementToBeClickable(CheckP.CotinueButton())).click();
            Thread.sleep(2000);
            takeScreenshot("Nach_Checkout_Info");

            // Finish Order
            OVP = new OverViewPage(driver);
            OVP.FinishButton().click();
            Thread.sleep(2000);
            takeScreenshot("Übersichtsseite");

            CMP = new CompleteMessagePage(driver);
            CMP.BackToHomeButton().click();
            Thread.sleep(2000);
            takeScreenshot("Test_Abgeschlossen");

        } catch (Exception e) {
            takeScreenshot("Fehler_im_Test");
            throw e;
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot("Fehlgeschlagen_" + result.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshot(String name) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File dest = new File("screenshots/" + name + "_" + timestamp + ".png");
        try {
            FileUtils.copyFile(src, dest);
            System.out.println("📸 Screenshot gespeichert: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Fehler beim Speichern des Screenshots: " + e.getMessage());
        }
    }
}
