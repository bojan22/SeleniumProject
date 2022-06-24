package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium6 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String URL = "https://www.ctshop.rs/";
        driver.navigate().to(URL);

        //Lokatori
        //Sinonim je Selektor
        //Po prioritetu lokatore treba gledati po:
        //ID - najstabilniji i jedinstven
        //Name
        //Class
        //CssSelector
        //Relative Xpath
        //Absolute Xpath

        //***ako je u class selectoru postoji ' '(space)  razmak u recima
        // onda se taj lokator trazi preko **CSS selektora *** u tom slucaju space zamenjujemo . tackom
        // i na pocetak stavljamo tacku

        WebElement idElement = driver.findElement(By.id("sharkskin-header-search-collapse"));
        WebElement nameElement = driver.findElement(By.name("google_esf"));
        WebElement classElement = driver.findElement(By.className("col-md-12"));
        WebElement cssElement = driver.findElement(By.cssSelector(".adsbygoogle.adsbygoogle-noablate"));
        WebElement elementXpathFull = driver.findElement(By.xpath("/html/body/footer/div/section/div"));
        WebElement elementXpathPartial = driver.findElement(By.xpath("//div/section/div"));


    }

}