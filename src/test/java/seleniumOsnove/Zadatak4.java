package seleniumOsnove;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.util.*;

public class Zadatak4 {
    ////Zadatak 1
    //Pokrenuti browser, otici na google, otvoriti jos 2 taba, u drugom otici na youtube, u trecem otici na linkedin.
    // Nakon toga zatvoriti sve tabove posebno.
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.navigate().to("https://www.google.com/");
        js.executeScript("window.open()");
        js.executeScript("window.open()");
        //lista tabova
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());

        driver.switchTo().window(tabList.get(1));
        driver.navigate().to("https://www.youtube.com/?hl=sr&gl=RS");

        driver.switchTo().window(tabList.get(2));
        driver.navigate().to("https://www.linkedin.com/");
        //zatvaranje tabova pojedinacno
        driver.close();
        driver.switchTo().window(tabList.get(1));
        driver.close();
        driver.switchTo().window(tabList.get(0));
        driver.close();




    }



}
