package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Selenium13a {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://imgflip.com/memegenerator");

        WebElement uploadButton = driver.findElement(By.id("mm-show-upload"));
        uploadButton.click();
        wdwait.until(ExpectedConditions.elementToBeClickable(By.id("mm-upload-btn")));
        WebElement uploadButton2 = driver.findElement(By.id("mm-upload-file"));
        uploadButton2.sendKeys("C:\\Users\\Dragoljub\\Desktop\\years.jpg");

        WebElement uploadButton3 = driver.findElement(By.id("mm-upload-btn"));
        uploadButton3.click();

        wdwait.until(ExpectedConditions.elementToBeClickable(By.className("mm-text")));
        WebElement text = driver.findElement(By.className("mm-text"));
        text.sendKeys("Kad podesis thread sleep na godine umesto na milisekunde");

        WebElement generateMeme = driver.findElement(By.cssSelector(".mm-generate.b.but"));
        generateMeme.click();

    }
}