package seleniumOsnove;
import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class Selenium1 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://www.google.com");
        driver.navigate().to("https://www.youtube.com");
        driver.navigate().refresh();
        driver.navigate().back();
        driver.manage().window().maximize();
        driver.navigate().forward();
        driver.close();

        ////Zadatak 1
        //Otvoriti browser, otici na youtube, otici na google, povecati prozor, vratiti se nazad i odraditi refresh





    }
}
