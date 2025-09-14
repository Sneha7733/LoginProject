package com.selenium.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // CSS selectors
    private final By usernameFieldCss = By.cssSelector("#username");
    private final By passwordFieldCss = By.cssSelector("#password");
    private final By loginButtonCss = By.cssSelector("button.radius");
    private final By errorMessageCss = By.cssSelector("#flash");

    // XPath alternatives
    private final By usernameFieldXpath = By.xpath("//input[@id='username']");
    private final By passwordFieldXpath = By.xpath("//input[@id='password']");
    private final By loginButtonXpath = By.xpath("//button[@type='submit']");
    private final By errorMessageXpath = By.xpath("//div[@id='flash']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // 3. Navigate to login page
    public void navigateToLoginPage() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    // 4. Enter username (using CSS by default)
    public void enterUsername(String username) {
        driver.findElement(usernameFieldCss).clear();
        driver.findElement(usernameFieldCss).sendKeys(username);
    }

    // Alternative: Enter username using XPath
    public void enterUsernameByXpath(String username) {
        driver.findElement(usernameFieldXpath).clear();
        driver.findElement(usernameFieldXpath).sendKeys(username);
    }

    // 5. Enter password (using CSS)
    public void enterPassword(String password) {
        driver.findElement(passwordFieldCss).clear();
        driver.findElement(passwordFieldCss).sendKeys(password);
    }

    // Alternative: Enter password using XPath
    public void enterPasswordByXpath(String password) {
        driver.findElement(passwordFieldXpath).clear();
        driver.findElement(passwordFieldXpath).sendKeys(password);
    }

    // Click login button (CSS)
    public void clickLogin() {
        driver.findElement(loginButtonCss).click();
    }

    // Alternative: click using XPath
    public void clickLoginByXpath() {
        driver.findElement(loginButtonXpath).click();
    }

    // 5. Combined login() method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // 6. Get error message (waits until visible)
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageCss));
        return driver.findElement(errorMessageCss).getText().trim();
    }

    // Alternative get error message using XPath
    public String getErrorMessageByXpath() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageXpath));
        return driver.findElement(errorMessageXpath).getText().trim();
    }
}
