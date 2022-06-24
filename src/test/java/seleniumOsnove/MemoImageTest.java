package seleniumOsnove;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;

public class MemoImageTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://imgflip.com/memegenerator");

        WebElement uploadNewTampleteBtn = driver.findElement(By.id("mm-show-upload"));
        uploadNewTampleteBtn.click();

        WebElement uploadImageBtn = driver.findElement(By.id("mm-upload-file"));
        //uploadImageBtn.isEnabled();
        //uploadImageBtn.clear();
        uploadImageBtn.sendKeys("C:\\Users\\BOKI\\Desktop\\image.jpg");

        WebElement checkImageContent = driver.findElement(By.id("mm-upload-img-preview-wrap"));
        Assert.assertTrue(checkImageContent.isDisplayed());

        WebElement uploadBtn = driver.findElement(By.id("mm-upload-btn"));
        uploadBtn.click();




    }
}
