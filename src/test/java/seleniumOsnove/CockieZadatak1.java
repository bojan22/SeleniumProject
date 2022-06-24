package seleniumOsnove;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class CockieZadatak1 {
//Zadatak 2 - Ulogovati se na sajt https://demoqa.com/ preko kolacica, izlogovati se i asertovati da je korisnik izlogovan
public static void main(String[] args) {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.navigate().to( "https://demoqa.com/login" );
    Cookie logIncookie = new Cookie("userName", "bojan88");
    driver.manage().addCookie(logIncookie);
    driver.navigate().refresh();

}



}
