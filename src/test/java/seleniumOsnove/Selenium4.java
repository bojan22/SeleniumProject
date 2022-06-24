package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Selenium4 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String google = "https://www.google.com";
        String youtube = "https://www.youtube.com";
        driver.navigate().to(google);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Kreiramo JavascriptExecutor jer nam je potreban za otvaranje tabova
        js.executeScript("window.open()");
        //dajemo komandu da se otvori novi tab
        js.executeScript("window.open()");
        js.executeScript("window.open()");

        ArrayList<String> listaTabova = new ArrayList<String>(driver.getWindowHandles());
        //Kreiramo listu "rucki" odnosno svih otvorenih tabova u browseru
        //Potrebno je imati listu rucki kako bismo mogli da se prebacujemo sa jednog taba na drugi

        driver.switchTo().window(listaTabova.get(2));
        //Ovom komandom prelazimo na treci tab
        driver.navigate().to(youtube);
        //Na trecem tabu otvaramo youtube
        //Moramo prvo da se prebacimo na zeljeni tab kako bismo poslali naredbu gde ce ici

        driver.switchTo().window(listaTabova.get(0));
        //Ovom komandom prelazimo na prvi tab
        driver.close();
        //driver.close() zatvara tab na kom se TRENUTNO nalazimo
        Thread.sleep(1000);

        driver.switchTo().window(listaTabova.get(1));
        driver.close();
        //Da bismo zatvorili drugi tab moramo prvo da se prebacimo na taj tab
        Thread.sleep(1000);

        driver.switchTo().window(listaTabova.get(3));
        driver.close();
        Thread.sleep(1000);

        //Ako otvorimo ukupno 4 taba, oni ce ovako biti otvoreni
        // 0 - 3 - 2 - 1
        //Zato sto pri pokretanju programa uvek nam je to prvi tab, odnosno tab 0
        //Kada otvorimo novi tab on se pomeri na prvo mesto u desno:
        // 0 - 1
        //Kada ponovo otvorimo novi tab on se takodje otvara na prvom mestu u desno:
        // 0 - 2 - 1
        //Isti princip je ako otvorimo jos jedan i na kraju bude
        // 0 - 3 - 2 - 1

    }
}