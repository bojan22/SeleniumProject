package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Selenium11a {
    public static void main(String[] args) throws InterruptedException {

        /*Napraviti program koji ce dodati ovaj proizvod u korpu
        https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
        zatim obrisati proizvod brisanjem cookie-a i proveriti da li je korpa prazna*/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

        WebElement cartBeforeAdding = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartBeforeAdding.getText(), "0");

        WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
        addToCart.click();

        WebElement cartAfterAdding = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAfterAdding.getText(), "1");

        WebElement notification = driver.findElement(By.cssSelector(".a-size-medium-plus.a-color-base.sw-atc-text.a-text-bold"));
        Assert.assertEquals(notification.getText(), "Added to Cart");

        WebElement goToCart = driver.findElement(By.id("sw-gtc"));
        goToCart.click();

        WebElement itemContent = driver.findElement(By.className("sc-list-item-content"));
        Assert.assertTrue(itemContent.isDisplayed());

        WebElement itemTitle = driver.findElement(By.className("a-truncate-cut"));
        Assert.assertEquals(itemTitle.getText(), "Selenium Framework Design in Data-Driven Testing: Build data-driven test frameworks using Selenium WebDriver, AppiumDriver, Java, and TestNG");

        driver.manage().deleteCookieNamed("session-id");
        driver.navigate().refresh();

        WebElement emptyCartNotification = driver.findElement(By.cssSelector(".a-row.sc-your-amazon-cart-is-empty"));
        Assert.assertEquals(emptyCartNotification.getText(),"Your Amazon Cart is empty");

        WebElement cartAfterRemovingItem = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAfterRemovingItem.getText(), "0");

    }
}