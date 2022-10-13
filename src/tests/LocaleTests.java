package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class LocaleTests {
    WebDriver driver;
    WebDriverWait wait;
    BasicTest basicTest;

    @BeforeClass
    public void setup() {
        basicTest = new BasicTest();
        basicTest.setup();
        driver = basicTest.driver;
        wait = basicTest.wait;


    }
    @BeforeMethod
    public void beforeMethod() {
        basicTest.beforeMethod();
    }
    @Test(priority = 10)
    public void setLocaleToEs() {
        basicTest.navPage.getLanguageButton().click();
        basicTest.navPage.getSpanishLanguage().click();
        String actualResult = basicTest.landingPage.getLandingTitle().getText();
        String expectedResult = "Página de aterrizaje";
        Assert.assertEquals(actualResult, expectedResult, "Title should be 'Página de aterrizaje'");
    }

    @Test(priority = 20)
    public void setLocaleToEn() {
        basicTest.navPage.getLanguageButton().click();
        basicTest.navPage.getEnglishLanguage().click();
        String actualResult = basicTest.landingPage.getLandingTitle().getText();
        String expectedResult = "Landing";
        Assert.assertEquals(actualResult, expectedResult, "Title should be 'Landing'");
    }

    @Test(priority = 30)
    public void setLocaleToCn() {

        basicTest.navPage.getLanguageButton().click();
        basicTest.navPage.getChineseLanguage().click();
        String actualResult = basicTest.landingPage.getLandingTitle().getText();
        String expectedResult = "首页";
        Assert.assertEquals(actualResult, expectedResult, "Title should be '首页'");
    }
    @Test(priority = 40)
    public void setLocaleToFr() {
        basicTest.navPage.getLanguageButton().click();
        basicTest.navPage.getFrenchLanguage().click();
        String actualResult = basicTest.landingPage.getLandingTitle().getText();
        String expectedResult = "Page d'atterrissage";
        Assert.assertEquals(actualResult, expectedResult, "Title should be 'Page d'atterrissage'");
    }

    @AfterMethod
    public void takeScreenShotIfTestFails(ITestResult result) {
        basicTest.takeScreenShotIfTestFails(result);
    }

    @AfterClass
    public void afterClass() {
        basicTest.afterClass();
    }
}
