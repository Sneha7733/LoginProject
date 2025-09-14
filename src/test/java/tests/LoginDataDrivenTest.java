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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LoginDataDrivenTest {
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

    @DataProvider(name = "loginData")
    public Iterator<Object[]> loginDataProvider() throws IOException {
        List<Object[]> testData = new ArrayList<>();

        // CSV inside com.selenium.login package
        String filePath = "src/main/java/com/selenium/login/testdata.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { // skip header row
                    header = false;
                    continue;
                }
                String[] parts = line.split(",");
                String username = parts[0].trim();
                String password = parts[1].trim();
                String expected = parts[2].trim();
                testData.add(new Object[]{username, password, expected});
            }
        }
        return testData.iterator();
    }

    // --- Data-driven test ---
    @Test(dataProvider = "loginData")
    public void testLoginFromCsv(String username, String password, String expectedResult) {
        loginPage.navigateToLoginPage();
        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("valid")) {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains("/secure"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/secure"),
                    "Successful Login: " + username);
        } else {
            String msg = loginPage.getErrorMessage();
            Assert.assertFalse(msg.isEmpty(),
                    "Your username is invalid! " + username);
        }
    }
}
