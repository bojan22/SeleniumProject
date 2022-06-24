package domaciZadatak;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import java.time.*;

public class WordPressDZ {
    WebDriver driver;
    WebDriverWait wait;
    String validUsername="bojanqa";
    String validPassword = "bojanqa123";
    String expectedURL = "https://wordpress.com/read";
    String baseURL = "https://wordpress.com/log-in?redirect_to=https%3A%2F%2Fwordpress.com%2F";
    String invalidEmail = "bojan@test.qa";
    String invalidPassword = "bojanqa123456";


    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    @BeforeMethod
    public void pageSetup(){
        driver.manage().window().maximize();
        driver.navigate().to(baseURL);
    }
    @Test (priority = 100) //morao sam da stavim ovaj test da se radi poslednji jer ostali nisu hteli da rade ako se on prvi pokrene...
    public void successfulLogin() throws InterruptedException {
        WebElement emailTextbox = driver.findElement(By.id("usernameOrEmail"));
        emailTextbox.sendKeys(validUsername);
        WebElement continueButton = driver.findElement(By.cssSelector(".button.form-button.is-primary"));
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        WebElement passwordTextbox = driver.findElement(By.id("password"));
        passwordTextbox.sendKeys(validPassword);
        continueButton.click();
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".masterbar__item.masterbar__item-me")));
        WebElement profileButton = driver.findElement(By.cssSelector(".masterbar__item.masterbar__item-me"));
        profileButton.click();
        //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("sidebar__me-signout-text"))));
        Thread.sleep(3000); //ovaj wait nekad ne radi kako treba ne znam zasto pa moram da ubacim explicit wait...
        WebElement logOutButton = driver.findElement(By.className("sidebar__me-signout-text"));

        Assert.assertTrue(logOutButton.isDisplayed());

        String actualUsername = driver.findElement(By.className("profile-gravatar__user-display-name")).getText();

        Assert.assertEquals(actualUsername, validUsername);
        logOutButton.click();
    }
    @Test (priority = 20)
    public void invalidEmailTest(){
        String expectedMassageError = "User does not exist. Would you like to create a new account?";
        String expectedURL = "https://wordpress.com/log-in?redirect_to=https%3A%2F%2Fwordpress.com%2F";

        wait.until(ExpectedConditions.urlToBe(baseURL));
        Assert.assertEquals(baseURL, expectedURL);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("usernameOrEmail")));
        WebElement emailTextBox = driver.findElement(By.id("usernameOrEmail"));
        emailTextBox.sendKeys(invalidEmail);
        WebElement continueButton = driver.findElement(By.cssSelector(".button.form-button.is-primary"));
        continueButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".form-input-validation.is-error")));
        String errorMassage = driver.findElement(By.cssSelector(".form-input-validation.is-error")).getText();

        Assert.assertEquals(errorMassage, expectedMassageError);
    }
    @Test (priority = 30)
    public void validUsernameInvalidPasswordTest() {
        WebElement emailTextBox = driver.findElement(By.id("usernameOrEmail"));
        emailTextBox.sendKeys(validUsername);
        WebElement continueButton = driver.findElement(By.cssSelector(".button.form-button.is-primary"));
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys(invalidPassword);
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".form-input-validation.is-error")));
        String errorPasswordMassage = driver.findElement(By.className("login__form-password")).findElement(By.cssSelector(".form-input-validation.is-error")).getText();

        Assert.assertEquals(errorPasswordMassage, "Oops, that's not the right password. Please try again!");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.form-button.is-primary")));
        WebElement logInButton = driver.findElement(By.cssSelector(".button.form-button.is-primary"));
        Assert.assertTrue(logInButton.isDisplayed());
    }
    @Test (priority = 40)
    public void upperCaseValidCredentialsTest(){
        WebElement emailTextbox = driver.findElement(By.id("usernameOrEmail"));
        emailTextbox.sendKeys(validUsername.toUpperCase());
        WebElement continueButton = driver.findElement(By.cssSelector(".button.form-button.is-primary"));
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        WebElement passwordTextbox = driver.findElement(By.id("password"));
        passwordTextbox.sendKeys(validPassword.toUpperCase());
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".form-input-validation.is-error")));
        String errorPasswordMassage = driver.findElement(By.className("login__form-password")).findElement(By.cssSelector(".form-input-validation.is-error")).getText();

        Assert.assertEquals(errorPasswordMassage, "Oops, that's not the right password. Please try again!");


    }



   @AfterClass
   public void teardown(){
        driver.close();
        driver.quit();
    }




}
