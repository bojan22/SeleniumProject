package seleniumOsnove;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.time.*;

public class Zadatak5 {
    ////Zadatak 3
    ////Na sajtu https://cms.demo.katalon.com/ dodati u korpu "Patient ninja" proizvod i proveriti
    // da li je dodat u korpu

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.navigate().to("https://cms.demo.katalon.com/");
        WebElement addToCartButton = driver.findElement(By.cssSelector
                (".button.product_type_simple.add_to_cart_button.ajax_add_to_cart"));
        WebElement pictureItem1 = driver.findElement(By.cssSelector
                (".woocommerce-LoopProduct-link.woocommerce-loop-product__link"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", pictureItem1);
        //wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();

        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) addToCartButton));
        //Thread.sleep(5000);
        //driver.findElement(By.cssSelector(".button.product_type_simple.add_to_cart_button.ajax_add_to_cart")).click();
        //driver.findElement(By.cssSelector(".button.product_type_simple.add_to_cart_button.ajax_add_to_cart")).click();



    }


}
