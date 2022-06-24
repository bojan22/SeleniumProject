package automationPractice.com;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import java.time.*;

public class ExeptionTests {
    WebDriver driver;
    WebDriverWait wait;
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
        WebElement testExeptions = driver.findElement(By.linkText("Test Exceptions"));
        testExeptions.click();
    }
    @Test
    public void NoSuchElementException(){
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("row2"))));
        WebElement raw2 = driver.findElement(By.id("row2"));
        Assert.assertTrue(raw2.isDisplayed());


    }



}
