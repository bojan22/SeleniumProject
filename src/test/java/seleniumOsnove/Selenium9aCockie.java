package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Selenium9aCockie {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("https://wordpress.com/log-in?redirect_to=https%3A%2F%2Fwordpress.com%2F");

        driver.manage().deleteAllCookies();

        Cookie kolacic = new Cookie("wordpress_logged_in","dragoljubqa%7C1748802798%7CVw666jO8PUDnJO1FieeDS1urIlpl35VTeUCb7d70%7Cf0790e8ed246ed444210a189df05d982e9f3ee4cb1b771fd20066b524eeef1aa");
        driver.manage().addCookie(kolacic);
        driver.navigate().refresh();

        WebElement continueButton = driver.findElement(By.cssSelector(".button.is-primary"));
        continueButton.click();
    }
}