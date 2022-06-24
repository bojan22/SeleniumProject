package automationPractice.com;


import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import java.time.*;
import java.util.*;

public class LoginTests {
    WebDriver driver;
    WebDriverWait wait;


//BEFORE
    @BeforeClass
    public void preSetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    @BeforeMethod
    public void setUp(){
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/");
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();
        WebElement logInPractice = driver.findElement(By.linkText("Test Login Page"));
        logInPractice.click();
    }

//TESTS
    @Test
    public void successfulLogin (){
        WebElement usernameFiled = driver.findElement(By.id("username"));
        WebElement passwordFiled = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameFiled.sendKeys("student");
        passwordFiled.sendKeys("Password123");
        submitButton.click();

        WebElement logOutBtn = driver.findElement(By.cssSelector(".wp-block-button__link.has-text-color.has-background.has-very-dark-gray-background-color"));
        Assert.assertTrue(logOutBtn.isDisplayed());
        WebElement logInTextFiled = driver.findElement(By.className("post-title"));
        Assert.assertEquals(logInTextFiled.getText(), "Logged In Successfully");
    }
    @Test (priority = 20)
    public void emptyFiledLogin(){
        WebElement usernameFiled = driver.findElement(By.id("username"));
        WebElement passwordFiled = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameFiled.sendKeys("");
        passwordFiled.sendKeys("");
        submitButton.click();
        //Assert error massage
        WebElement errorNotification = driver.findElement(By.id("error"));
        Assert.assertEquals(errorNotification.getText(), "Your username is invalid!");
        // Assert current url
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://practicetestautomation.com/practice-test-login/";
        Assert.assertEquals(currentUrl, expectedUrl );
    }
    @Test (priority = 30)
    public void wrongUsernameLogin(){
        WebElement usernameFiled = driver.findElement(By.id("username"));
        WebElement passwordFiled = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameFiled.sendKeys("nepoznat studnet");
        passwordFiled.sendKeys("Password123");
        submitButton.click();

        WebElement errorNotification = driver.findElement(By.id("error"));
        Assert.assertEquals(errorNotification.getText(), "Your username is invalid!");
    }
    @Test (priority = 40)
    public void WrongPasswordLogin(){
        WebElement usernameFiled = driver.findElement(By.id("username"));
        WebElement passwordFiled = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameFiled.sendKeys("student");
        passwordFiled.sendKeys("Password12345");
        submitButton.click();

        WebElement errorNotification = driver.findElement(By.id("error"));
        Assert.assertEquals(errorNotification.getText(), "Your password is invalid!");

    }
    @Test
    public void EmailAdressLogin(){
        WebElement usernameFiled = driver.findElement(By.id("username"));
        WebElement passwordFiled = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameFiled.sendKeys("student@gmail.com");
        passwordFiled.sendKeys("Password123");
        submitButton.click();

        WebElement errorNotification = driver.findElement(By.id("error"));
        Assert.assertEquals(errorNotification.getText(), "Your username is invalid!");
    }
    @Test
    public void UsernamePasswortToUpperCaseLogin() throws InterruptedException {
        WebElement usernameFiled = driver.findElement(By.id("username"));
        WebElement passwordFiled = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameFiled.sendKeys("student".toUpperCase());
        passwordFiled.sendKeys("Password123".toUpperCase());
        submitButton.click();

        WebElement errorNotification = driver.findElement(By.id("error"));
        Assert.assertEquals(errorNotification.getText(), "Your username is invalid!");
        //WebElement logOutBtn = driver.findElement(By.cssSelector(".wp-block-button__link.has-text-color.has-background.has-very-dark-gray-background-color"));
        boolean check = false;
        try {
            check = driver.findElement(By.cssSelector(".wp-block-button__link.has-text-color.has-background.has-very-dark-gray-background-color")).isDisplayed();

        } catch (Exception e){

        }
        Assert.assertFalse(check);





    }


//AFTER
    /*@AfterClass
    public void tearDown() {

        driver.close();
        driver.quit();
    } */



}
