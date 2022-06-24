package domaciZadatak;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;

import java.time.*;

public class AmazonSelenumDZ {
    // BOJAN ZIVKOVIC
//Napraviti program koji ce dodati ovaj proizvod u korpu
// https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
//zatim obrisati proizvod brisanjem cookie-a i proveriti da li je korpa prazna

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //never used on this computer in test

        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2 ");

        //before adding item to cart check
        WebElement cartEmpty = driver.findElement(By.id("nav-cart-count"));
        String actualCart = cartEmpty.getText();
        String expectedCart = "0";
        Assert.assertEquals(actualCart, expectedCart);

        //adding item to cart
        WebElement addToCartBtn = driver.findElement(By.id("submit.add-to-cart"));
        addToCartBtn.click();

        //check item is in cart
        WebElement addTocartFull = driver.findElement(By.id("nav-cart-count-container"));
        String actualCartFull = addTocartFull.getText();
        String ecpectedCartFull = "1";
        Assert.assertEquals(actualCartFull, ecpectedCartFull);

        //check actual item is in cart
        WebElement goToCartBtn = driver.findElement(By.id("sw-gtc"));
        goToCartBtn.click();
        //display resolution is smaller than expected text and UI text sometimes collapse and assert fails
        String actualTextItem = driver.findElement(By.className("a-truncate-cut")).getText();

        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("a-truncate-cut")));
        String expectedItemText = "Selenium Framework Design in Data-Driven Testing: Build data-driven test frameworks using Selenium WebDriver, AppiumDriver, Java, and TestNG";
        Assert.assertEquals(actualTextItem, expectedItemText);

        //deleting using cookie
        driver.manage().deleteCookieNamed("session-id");
        driver.navigate().refresh();

        //chech if item is removed

        String acutalTextEmptyCart = driver.findElement(By.cssSelector(".a-row.sc-your-amazon-cart-is-empty")).getText();
        String expectedTextEmptyCart = "Your Amazon Cart is empty";
        Assert.assertEquals(acutalTextEmptyCart, expectedTextEmptyCart);

        String emptyCartFinal = driver.findElement(By.id("nav-cart-count")).getText();
        String expectedCartFinal = "0";
        Assert.assertEquals(emptyCartFinal, expectedCartFinal);

        driver.quit();







    }


}
