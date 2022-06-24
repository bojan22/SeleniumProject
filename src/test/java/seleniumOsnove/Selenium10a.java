package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Selenium10a {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://demoqa.com/login");

        String expectedUsername = "dragoljubqa";

        Cookie kolacic1 = new Cookie("userName",expectedUsername);
        Cookie kolacic2 = new Cookie("userID","362c8c84-7846-4bcd-9625-ef0df047ff54");
        Cookie kolacic3 = new Cookie("expires","2022-06-09T19%3A32%3A43.277Z");
        Cookie kolacic4 = new Cookie("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImRyYWdvbGp1YnFhIiwicGFzc3dvcmQiOiJRd2VydHkxMjMhQCMiLCJpYXQiOjE2NTQxOTgzNjN9.2q4Ig3YACdgUMfqY7zeUlgPwZY8SL6uib7O7OJAlCD4");

        driver.manage().addCookie(kolacic1);
        driver.manage().addCookie(kolacic2);
        driver.manage().addCookie(kolacic3);
        driver.manage().addCookie(kolacic4);

        driver.navigate().refresh();

        WebElement username = driver.findElement(By.id("userName-value"));
        String actualUsername = username.getText();

        Assert.assertEquals(actualUsername, expectedUsername);

        WebElement logOutButton = driver.findElement(By.id("submit"));
        Assert.assertTrue(logOutButton.isDisplayed());

        WebElement logOutButtonWRONG = driver.findElement(By.cssSelector(".btn.btn-primary"));
        //Prethodni webelement je nadjen preko pogresnog lokatora jer ovaj lokator za LOG OUT ima istu vrednosti kao
        //i LOG IN dugme. Sto znaci da ako bismo trazili da dugme log out ne postoji necemo uspeti jer ce program
        //naci log in dugme (zbog istog lokatora)

        //driver.manage().deleteAllCookies();
        //mozemo da se izlogujemo ako obrisemo sve kolacice
        //driver.manage().deleteCookieNamed("token");
        //mozemo da obrisemo specifican kolacic ako prosledimo samo naziv
        //driver.manage().deleteCookie(kolacic1);
        //mozemo da obrisemo specifican kolacic ako prosledimo prethodno kreiran objekat kolacica
        //driver.navigate().refresh();

        logOutButton.click();

        //Kada hocemo da assertujemo da element NE POSTOJI na stranici onda je potrebno uraditi to preko
        //try catch bloka

        boolean check = false;
        //prvo moramo da kreiramo neki boolean koji cemo koristiti za assert
        //podesili smo vrednost na false i kasnije pri assertu trazimo da taj boolean bude false

        try {
            check = logOutButton.isDisplayed();
            //ako je element ipak pronadjen onda ce boolean postati TRUE i assert na kraju ce pasti
        } catch (Exception elkwjgjpft) {
            //U parametre catch moramo da prosledimo Exception i neki random string naziv
            //Najcesce se u parametar salje Exception e
        }

        Assert.assertFalse(check);

        //Kako da testiramo da smo dobro uradili sve?
        //Prvo promenimo assertFalse u assertTrue
        //Nakon toga vratimo na assertFalse i zakomentarisemo deo bloka gde se izlogujemo
        //Tako ce boolean zapravo postati true jer ce element biti pronadjen

    }
}