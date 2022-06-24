package seleniumOsnove;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class Zadatak1 {
    public static void main(String[] args) {
        ////Zadatak 1
        //Otvoriti browser, otici na youtube, otici na google, povecati prozor, vratiti se nazad i odraditi refresh

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://www.youtube.com/?hl=sr&gl=RS");
        driver.navigate().to("https://www.google.com/");
        driver.manage().window().maximize();
        driver.navigate().back();
        driver.navigate().refresh();



    }
}
