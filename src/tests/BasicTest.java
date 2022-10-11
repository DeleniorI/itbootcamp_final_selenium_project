package tests;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasicTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    protected CitiesPage citiesPage;
    protected LoginPage loginPage;
    protected MessagePopUpPage messagePopUpPage;
    protected NavPage navPage;
    protected SignupPage signupPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        baseUrl = "https://vue-demo.daniel-avellaneda.com";
        citiesPage = new CitiesPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        messagePopUpPage = new MessagePopUpPage(driver, wait);
        navPage = new NavPage(driver, wait);
        signupPage = new SignupPage(driver, wait);


    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
    }

//    @AfterMethod
//    public void takeScreenShotIfTestFails(ITestResult result) {
//        if (ITestResult.FAILURE == result.getStatus()) {
//            try {
//                TakesScreenshot ts = (TakesScreenshot) driver;
//                File source = ts.getScreenshotAs(OutputType.FILE);
//                String scPath = System.getProperty("user.dir");
//                try {
//                    FileHandler.copy(source, new File(scPath + "/screenshot/" + result.getName() + ".png"));
//                    System.out.println("Screenshot taken");
//                } catch (IOException e) {
//                    System.out.println("There is an error with taking a screenshot");
//                }
//            } catch (Exception e) {
//                System.out.println("Exception while taking screenshot " + e.getMessage());
//            }
//        }
//
//    }


}



