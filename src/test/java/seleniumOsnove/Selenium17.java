package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Selenium17 {

    WebDriver driver;
    WebDriverWait wdwait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/");
    }

    public void goToLoginPage() {
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();
        WebElement loginPageButton = driver.findElement(By.linkText("Test Login Page"));
        loginPageButton.click();
    }

    @Test (priority = 10)
    public void successfulLogin() {

        goToLoginPage();

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys("student");
        passwordField.sendKeys("Password123");
        submitButton.click();

        WebElement logoutButton = driver.findElement(By.cssSelector(".wp-block-button__link.has-text-color.has-background.has-very-dark-gray-background-color"));
        WebElement loginTitle = driver.findElement(By.className("post-title"));

        Assert.assertTrue(logoutButton.isDisplayed());
        Assert.assertEquals(loginTitle.getText(), "Logged In Successfully");

    }

    @Test (priority = 20)
    public void unsuccessfulLoginWithInvalidUsername() {

        goToLoginPage();

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys("pogresan username");
        passwordField.sendKeys("Password123");
        submitButton.click();

        boolean check = false;
        try {
            check = driver.findElement(By.cssSelector(".wp-block-button__link.has-text-color.has-background.has-very-dark-gray-background-color")).isDisplayed();
        } catch (Exception e) {

        }

        WebElement errorMessage = driver.findElement(By.id("error"));

        Assert.assertFalse(check);
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Test (priority = 30)
    public void unsuccessfulLoginWithInvalidPassword() {

        goToLoginPage();

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys("student");
        passwordField.sendKeys("pogresan password");
        submitButton.click();

        boolean check = false;
        try {
            check = driver.findElement(By.cssSelector(".wp-block-button__link.has-text-color.has-background.has-very-dark-gray-background-color")).isDisplayed();
        } catch (Exception e) {

        }

        WebElement errorMessage = driver.findElement(By.id("error"));

        Assert.assertFalse(check);
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @AfterMethod
    public void removeCookies() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}