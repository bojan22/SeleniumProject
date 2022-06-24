package seleniumOsnove;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium12imageUpload {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://crop-circle.imageonline.co/#circlecropresult");

        WebElement imageUploadButton = driver.findElement(By.id("inputImage"));
        imageUploadButton.sendKeys("C:\\Users\\Dragoljub\\Desktop\\yoda.png");
        //Pronadjete dugme za upload slike i prosledite lokaciju slike koje zelite
        //Za lokaciju slike prvo je pronadjete, desni klik na nju, klik na Properties
        //Kopirate lokaciju tako sto zaokruzite, kopirate i ovde paste-ujete u sendKeys
        //Napomena za korisnike Macbook-a, staviti obrnute kose crte. Ne "\\" nego ove "//"
        //i na kraju dopisati naziv slike i ekstenziju


    }
}