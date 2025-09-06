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
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckOutpage;
import pages.CompleteMessagePage;
import pages.LoginPage;
import pages.OverViewPage;

public class AddBoltTShirtTest { 
    WebDriver driver;
    LoginPage LP;
    CartPage CartP;
    CheckOutpage CheckP;
    OverViewPage OVP;
    CompleteMessagePage CMP;

    @BeforeMethod
    @Parameters("browser")
    public void SeUP(String browser) throws IOException {
        if (browser.equalsIgnoreCase("FireFox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        takeScreenshot("Startseite"); // Screenshot Startseite direkt nach Öffnen
    }

    @Test(priority = 1)
    public void SaucDemoTC1() throws InterruptedException {
        try {
            // Loginpage
            Thread.sleep(3000);
            LP = new LoginPage(driver);
            LP.UserName().sendKeys("standard_user");
            LP.PassWord().sendKeys("secret_sauce");
            LP.LoginButton().click();
            Thread.sleep(5000);
            takeScreenshot("Nach_Login"); // Screenshot nach Login

            // CartPage
            CartP = new CartPage(driver);
            CartP.AddBoltTShirtToCartButton().click();
            Thread.sleep(3000);
            takeScreenshot("Nach_Produkt_Hinzufuegen"); // Screenshot nach Produkt hinzufügen

            CartP.CartIcon().click();
            Thread.sleep(3000);
            takeScreenshot("Warenkorb_Anzeigen"); // Screenshot Warenkorb-Ansicht

            // CheckoutPage
            CheckP = new CheckOutpage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            CheckP.CheckOutButton().click();
            Thread.sleep(3000);
            takeScreenshot("Checkout_Seite"); // Screenshot Checkout-Startseite

            wait.until(ExpectedConditions.visibilityOf(CheckP.FirstName())).sendKeys("Ahmed");
            wait.until(ExpectedConditions.visibilityOf(CheckP.LastName())).sendKeys("Saied");
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(CheckP.PostalCode())).sendKeys("6228");
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(CheckP.CotinueButton())).click();
            Thread.sleep(3000);
            takeScreenshot("Nach_Checkout_Info"); // Screenshot nach Eingabe der Checkout-Daten

            // OverviewPage
            OVP = new OverViewPage(driver);
            OVP.FinishButton().click();
            Thread.sleep(3000);
            takeScreenshot("Übersichtsseite"); // Screenshot Übersicht nach Klick Finish

            // CompleteMessagePage
            CMP = new CompleteMessagePage(driver);
            CMP.BackToHomeButton().click();
            Thread.sleep(3000);
            takeScreenshot("Test_Abgeschlossen"); // Screenshot nach Abschluss

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

    // Verbesserte Screenshot-Methode mit Seiteninfo im Dateinamen
    public void takeScreenshot(String name) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String pageInfo = "";
        try {
            String url = driver.getCurrentUrl();
            pageInfo = url.replaceAll("https?://", "")
                          .replaceAll("[^a-zA-Z0-9-_]", "_");
        } catch (Exception e) {
            pageInfo = "UnbekannteSeite";
        }

        File dest = new File("screenshots/" + name + "_" + pageInfo + "_" + timestamp + ".png");

        try {
            FileUtils.copyFile(src, dest);
            System.out.println("📸 Screenshot gespeichert: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Fehler beim Speichern des Screenshots: " + e.getMessage());
        }
    }
}
