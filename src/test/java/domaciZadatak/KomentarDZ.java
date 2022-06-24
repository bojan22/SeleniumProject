package domaciZadatak;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;

public class KomentarDZ {
    // BOJAN ZIVKOVIC
    //Otici na sajt https://s1.demo.opensourcecms.com/wordpress/
    //Naci comment sekciju. Ostaviti komentar. Assertovati da li je komentar prikazan na stranici.

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://s1.demo.opensourcecms.com/wordpress/");

        //click on Hello World link
        WebElement helloWorldLink = driver.findElement(By.linkText("Hello world!"));
        helloWorldLink.click();

        //adding comment, name and email
        String comment = "Pozdrav za ItBootCamp-ovce!";//try to add random generated word for future automation
        String name = "Bojan Zivkovic";//try to add random generated name for future automation (javafaker?)
        WebElement comentTextBox = driver.findElement(By.id("comment"));
        comentTextBox.sendKeys(comment);
        WebElement nameTextBox = driver.findElement(By.id("author"));
        nameTextBox.sendKeys(name);
        WebElement emailTextBox = driver.findElement(By.id("email"));
        emailTextBox.sendKeys("bojan@qa.rs");
        WebElement commentBtn = driver.findElement(By.id("submit"));
        commentBtn.click();

        //verify if coment is present and same in UI as expected
        String actualComment=driver.findElement(By.cssSelector(".comment.odd.alt.thread-odd.thread-alt.depth-1")).findElement(By.className("comment-content")).getText();
        Assert.assertEquals(actualComment, comment);

        //pokusao da asertujem ime da li je taj komentar ostavio bas onaj ko je napisao , nisam uspeo da nadjem lokator ovako samo xpath
        //String actualName = driver.findElement(By.cssSelector(".comment-author.vcard")).findElement(By.className("fn")).getText();

        //verify if comment is from same name as provided before
        String actualName = driver.findElement(By.xpath("/html/body/div/div/div/div/div/main/div/ol/li[2]/article/footer/div[1]/b")).getText();
        Assert.assertEquals(actualName, name);

        driver.quit();


    }


}
