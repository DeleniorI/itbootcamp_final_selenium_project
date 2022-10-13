package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class ProfileTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private BasicTest basicTest;

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
    public void visitTheProfilePage() throws InterruptedException {
        basicTest.navPage.getLoginLink().click();
        basicTest.loginPage.getEmailInput().sendKeys("admin@admin.com");
        basicTest.loginPage.getPasswordInput().sendKeys("12345");
        basicTest.loginPage.getLoginButton().click();
        basicTest.navPage.getMyProfileLink().click();
        String actualUrlResult = driver.getCurrentUrl();
        String expectedUrlResult = "/profile";
        Assert.assertTrue(actualUrlResult.contains(expectedUrlResult), "URL should contain '/profile'");

        // Waiter for progress bar to get invisible, it won't read the email input otherwise
        basicTest.profilePage.waitForProgressBar();

        String actualEmailResult = basicTest.profilePage.getEmailInput().getAttribute("value");
        String expectedEmailResult = "admin@admin.com";
        Assert.assertEquals(actualEmailResult, expectedEmailResult, "Email value should be 'admin@admin.com'");
        basicTest.navPage.getLogoutButton().click();


    }
    @Test (priority = 20)
    public void checkInputTypes() {
        basicTest.navPage.getLoginLink().click();
        basicTest.loginPage.getEmailInput().sendKeys("admin@admin.com");
        basicTest.loginPage.getPasswordInput().sendKeys("12345");
        basicTest.loginPage.getLoginButton().click();
        basicTest.navPage.getMyProfileLink().click();
        basicTest.profilePage.waitForProgressBar();
        String actualEmailType = basicTest.profilePage.getEmailInput().getAttribute("type");
        String expectedEmailType = "email";
        Assert.assertEquals(actualEmailType, expectedEmailType, "Email attribute 'type' should be 'email'");

        String actualEmailDisabled = basicTest.profilePage.getEmailInput().getAttribute("disabled");
        String expectedEmailDisabled = "true";
        Assert.assertEquals(actualEmailDisabled, expectedEmailDisabled, "Email attribute 'disabled' should be 'disabled'");

        String actualNameType = basicTest.profilePage.getNameInput().getAttribute("type");
        String expectedNameType = "text";
        Assert.assertEquals(actualNameType, expectedNameType, "Name attribute 'type' should be 'text'");

        String actualCityType = basicTest.profilePage.getCityInput().getAttribute("type");
        String expectedCityType = "text";
        Assert.assertEquals(actualCityType, expectedCityType, "City attribute 'type' should be 'text'");

        String actualCountryType = basicTest.profilePage.getCountryInput().getAttribute("type");
        String expectedCountryType = "text";
        Assert.assertEquals(actualCountryType, expectedCountryType, "Country attribute 'type' should be 'text'");

        String actualPhoneType = basicTest.profilePage.getPhoneInput().getAttribute("type");
        String expectedPhoneType = "tel";
        Assert.assertEquals(actualPhoneType, expectedPhoneType, "Phone attribute 'type' should be 'tel'");

        String actualGitType = basicTest.profilePage.getGitUrlInput().getAttribute("type");
        String expectedGitType = "url";
        Assert.assertEquals(actualGitType,expectedGitType, "Git input attribute 'type' should be 'url'");

        String actualTwitterType = basicTest.profilePage.getTwitterUrlInput().getAttribute("type");
        String expectedTwitterType = "url";
        Assert.assertEquals(actualTwitterType,expectedTwitterType, "Twitter input attribute 'type' should be 'url'");

        basicTest.navPage.getLogoutButton().click();
    }

    @Test(priority = 30)
    public void editProfile() {
        basicTest.navPage.getLoginLink().click();


        basicTest.loginPage.getEmailInput().sendKeys("admin@admin.com");
        basicTest.loginPage.getPasswordInput().sendKeys("12345");
        basicTest.loginPage.getLoginButton().click();
        basicTest.navPage.getMyProfileLink().click();
        basicTest.profilePage.waitForProgressBar();

        // Cannot use clear method, bugged
        basicTest.profilePage.getNameInput().sendKeys(Keys.CONTROL, "A");
        basicTest.profilePage.getNameInput().sendKeys(Keys.BACK_SPACE);
        basicTest.profilePage.getPhoneInput().sendKeys(Keys.CONTROL, "A");
        basicTest.profilePage.getPhoneInput().sendKeys(Keys.BACK_SPACE);
        basicTest.profilePage.getCityInput().sendKeys(Keys.CONTROL, "A");
        basicTest.profilePage.getCityInput().sendKeys(Keys.BACK_SPACE);
        basicTest.profilePage.getCountryInput().sendKeys(Keys.CONTROL, "A");
        basicTest.profilePage.getCountryInput().sendKeys(Keys.BACK_SPACE);
        basicTest.profilePage.getTwitterUrlInput().sendKeys(Keys.CONTROL, "A");
        basicTest.profilePage.getTwitterUrlInput().sendKeys(Keys.BACK_SPACE);
        basicTest.profilePage.getGitUrlInput().sendKeys(Keys.CONTROL, "A");
        basicTest.profilePage.getGitUrlInput().sendKeys(Keys.BACK_SPACE);

        basicTest.profilePage.getNameInput().sendKeys("Ilija Nestorovic");
        basicTest.profilePage.getPhoneInput().sendKeys("+38161283223");
        basicTest.profilePage.getCityInput().sendKeys("Bucaramanga");
        basicTest.profilePage.getCityFromDropDown().click();
        basicTest.profilePage.getCountryInput().sendKeys("Colombia");
        basicTest.profilePage.getTwitterUrlInput().sendKeys("https://twitter.com/profile/milan1232");
        basicTest.profilePage.getGitUrlInput().sendKeys("https://github.com/DeleniorI");

        basicTest.profilePage.getSaveButton().click();

        wait.until(webDriver -> basicTest.messagePopUpPage.successDialog().isDisplayed());
        String actualResult = basicTest.messagePopUpPage.getProfileSavedPopUp().getText();
        String expectedResult = "Profile saved successfuly";
        Assert.assertTrue(actualResult.contains(expectedResult), "Popup should be 'Profile saved successfully'");
        String actualEmailValue = basicTest.profilePage.getEmailInput().getAttribute("value");
        String expectedEmailValue = "admin@admin.com";
        Assert.assertEquals(actualEmailValue, expectedEmailValue, "Email attribute 'value' should be 'admin@admin.com'");

        String actualNameValue = basicTest.profilePage.getNameInput().getAttribute("value");
        String expectedNameValue = "Ilija Nestorovic";
        Assert.assertEquals(actualNameValue, expectedNameValue, "Name attribute 'type' should be 'Ilija Nestorovic'");

        String actualCityValue = basicTest.profilePage.getCityInput().getAttribute("value");
        String expectedCityValue = "Bucaramanga";
        Assert.assertEquals(actualCityValue, expectedCityValue, "City attribute 'value' should be 'Bucaramanga'");

        String actualCountryValue = basicTest.profilePage.getCountryInput().getAttribute("value");
        String expectedCountryValue = "Colombia";
        Assert.assertEquals(actualCountryValue, expectedCountryValue, "Country attribute 'value' should be 'Colombia'");

        String actualPhoneValue = basicTest.profilePage.getPhoneInput().getAttribute("value");
        String expectedPhoneValue = "+38161283223";
        Assert.assertEquals(actualPhoneValue, expectedPhoneValue, "Phone attribute 'value' should be '+38161283223'");

        String actualGitValue = basicTest.profilePage.getGitUrlInput().getAttribute("value");
        String expectedGitValue = "https://github.com/deleniori";
        Assert.assertEquals(actualGitValue,expectedGitValue, "Git input attribute 'value' should be 'https://github.com/DeleniorI'");

        String actualTwitterValue = basicTest.profilePage.getTwitterUrlInput().getAttribute("value");
        String expectedTwitterValue = "https://twitter.com/profile/milan1232";
        Assert.assertEquals(actualTwitterValue,expectedTwitterValue, "Twitter input attribute 'type' should be 'https://twitter.com/profile/milan1232'");

        basicTest.navPage.getLogoutButton().click();

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
