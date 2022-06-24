package seleniumOsnove;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import java.time.*;

public class Zadatak6Wikipedia {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass

    public void preSetUp (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @BeforeMethod
    public void setUp (){
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com/");
    }

    @Test
    public void test1() {
        WebElement googleSearchBox = driver.findElement(By.name("q"));
        googleSearchBox.sendKeys("wikipedia");
        WebElement googleSearchBtn = driver.findElement(By.name("btnK"));
        wait.until(ExpectedConditions.elementToBeClickable(googleSearchBtn));
        googleSearchBtn.click();
        WebElement wikipediaTextLink = driver.findElement(By.cssSelector(".LC20lb.MBeuO.DKV0Md"));
        wait.until(ExpectedConditions.elementToBeClickable(wikipediaTextLink));
        wikipediaTextLink.click();

        WebElement searchBoxWiki = driver.findElement(By.id("searchInput"));
        searchBoxWiki.sendKeys("Nikola Tesla");
        WebElement searchWikiButton = driver.findElement(By.cssSelector(".sprite.svg-search-icon"));
        wait.until(ExpectedConditions.elementToBeClickable(searchWikiButton));
        searchWikiButton.click();

        String actualNameWikipedia = driver.findElement(By.id("firstHeading")).getText();
        String expectedNameWikipedia = "Nikola Tesla1";

        Assert.assertEquals(actualNameWikipedia, expectedNameWikipedia);

    }

       @AfterMethod
        public void tearDown(){
            driver.quit();
        }

    }


