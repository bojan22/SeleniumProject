package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class Selenium16 {

    WebDriver driver;
    WebDriverWait wdwait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    @Test
    public void test1() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("wikipedia");
        searchBox.sendKeys(ENTER);
        List<WebElement> pageItems = driver.findElements(By.cssSelector(".LC20lb.MBeuO.DKV0Md"));
        for (int i = 0; i < pageItems.size(); i++) {
            if (pageItems.get(i).getText().equals("Wikipedia")) {
                pageItems.get(i).click();
                break;
            }
        }
        WebElement wikipediaSearchBox = driver.findElement(By.id("searchInput"));
        wikipediaSearchBox.sendKeys("Nikola Tesla");
        wikipediaSearchBox.sendKeys(ENTER);

        WebElement heading = driver.findElement(By.id("firstHeading"));
        Assert.assertEquals(heading.getText(), "Nikola Tesla");
        Assert.assertEquals(driver.getCurrentUrl(), "https://en.wikipedia.org/wiki/Nikola_Tesla");
        WebElement pageImage = driver.findElement(By.className("infobox-image"));
        Assert.assertTrue(pageImage.isDisplayed());

    }

    @AfterMethod
    public void removeCookies() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}