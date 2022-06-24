package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Selenium8a {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("https://wordpress.com/log-in?redirect_to=https%3A%2F%2Fwordpress.com%2F");

        String username="dragoljubqa";
        //Username sam sacuvao kao string jer ga takodje kasnije korisnim za assert na kraju
        WebElement emailTextbox = driver.findElement(By.id("usernameOrEmail"));
        //Element trazimo tako sto na stranici kliknemo desni klik -> Inspect -> ponovo na isti element desni klik
        //i ponovo na Inspect da bi nam se u htmlu highlight-ovao selektovan element
        //dvoklik na vrednost elementa kako bismo ga selektovali (vrednost je ono sto pise unutar navodnika na lokatoru)
        //CTRL + C da kopiramo vrednost
        //CTRL + F da otvorimo search
        //Vrednost se automatski ubaci u search (ako se ne ubaci automatski onda je potrebno paste-ovati)
        //Bice prikazan broj ponavljanja vrednosti u html-u i proveravamo da li je lokator jedinstven
        //NAPOMENA: Ako prikaze vise od 1 podudaranja, ne znaci da element nije jedinstven!
        //Proveriti na kojim mestima se podudara vrednost i samo ako je pod istim lokatorom (id, name, class...)
        //i apsolutno ista vrednost, onda nije jedinstvena i unosenjem string ili klik na taj webelement
        //ce uneti/kliknuti na prvi webelement koji nadje u htmlu
        //Sto znaci mozda ce vas test proci ako je vas zeljeni element na prvom mestu
        emailTextbox.sendKeys(username);

        WebElement continueButton = driver.findElement(By.cssSelector(".button.form-button.is-primary"));
        continueButton.click();

        wdwait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        //Primetio sam da je u liniji ispod test pao jer nije mogao da nadje element pa sam dodao Thread sleep
        WebElement passwordTextbox = driver.findElement(By.id("password"));
        passwordTextbox.sendKeys("Qwerty123");

        //WebElement loginButton = driver.findElement(By.cssSelector(".button.form-button.is-primary"));
        //loginButton.click();
        //Primecujem da loginButton ima isti lokator kao continueButton i mogu da koristim taj webelement za klik
        continueButton.click();

        String expectedURL = "https://wordpress.com/home/dragoljubqa.wordpress.com";
        wdwait.until(ExpectedConditions.urlToBe(expectedURL));
        //Moram ovde da stavim wdwait umesto thread sleepa koji ce cekati dok se URL ne promeni
        //Kasnije proveravamo da li se URL promenio i moze se smatrati da je ovo 'dupli' posao
        //jer je jasno da do asserta nece ni doci ako se ne prosledi dobar URL
        //ali smatram da je potrebno raditi i za to assert makar i radili duplo, bolje nego da ne assertujem uopste

        //U linijama ispod assertujem da li je korisniku otvoren adekvatan URL sa prosledjenim username-om
        //I moram da dodam thread sleep kako bih sacekao da se URL promeni
        //String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        //U assertu mogu direktno da prosledim za ocekivanu vrednost da driver uhvati URL, ne moram da kreiram poseban string

        WebElement profileButton = driver.findElement(By.cssSelector(".masterbar__item.masterbar__item-me"));
        profileButton.click();

        wdwait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("sidebar__me-signout"))));
        WebElement logOutButton = driver.findElement(By.className("sidebar__me-signout"));
        Assert.assertTrue(logOutButton.isDisplayed());

        String actualUsername = driver.findElement(By.className("profile-gravatar__user-display-name")).getText();
        Assert.assertEquals(actualUsername, username);

    }
}