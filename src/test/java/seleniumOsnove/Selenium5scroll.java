package seleniumOsnove;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class Selenium5scroll {
    public static void main(String[] args) {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String youtube = "https://www.ctshop.rs/";
        driver.navigate().to(youtube);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement element = driver.findElement(By.xpath("/html/body/footer/div/section/div/div/div[2]/div[1]"));
        //radi scroll do elementa koji je naznacen
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        //moze i po kordinatama guglati na guru99 link :
        // https://www.guru99.com/execute-javascript-selenium-webdriver.html
    }

}