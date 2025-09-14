package tests;

import com.selenium.login.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordManagerOnboarding,PasswordImport");

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test(priority = 1)
    public void testValidLogin() {
        loginPage.navigateToLoginPage();
        loginPage.login("tomsmith", "SuperSecretPassword!");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/secure"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"),
                "User should be redirected to secure area after valid login");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        loginPage.navigateToLoginPage();
        loginPage.login("wrongusername", "wrongpassword");
        String msg = loginPage.getErrorMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!") || msg.toLowerCase().contains("invalid"),
                "Expected invalid username error, got: " + msg);
    }

    @Test(priority = 3)
    public void testEmptyFields() {
        loginPage.navigateToLoginPage();
        loginPage.login("", "");
        String msg = loginPage.getErrorMessage();
        Assert.assertFalse(msg.isEmpty(), "Expected an error message when fields are empty.");
    }
}


