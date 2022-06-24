package seleniumOsnove;

import org.testng.annotations.*;

public class Selenium14anotacije {

    @BeforeClass
    public void beforeClass() {
        System.out.println("***************");
        System.out.println("STAMPA IZ BEFORE CLASS");
        System.out.println("***************");
        //Metoda se izvrsava samo jednom na pocetku
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("-------------");
        System.out.println("Stampa iz before method");
        System.out.println("-------------");
        //Metoda se izvrsava pre svake test metode
        //Ponavlja se onoliko puta koliko imamo test metoda
    }

    @Test
    public void test1() {
        System.out.println("TEST 1");
    }

    @Test
    public void test2() {
        System.out.println("TEST 2");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("++++++++++++++");
        System.out.println("Stampa iz after method");
        System.out.println("++++++++++++++");
        //Metoda se izvrsava posle svake test metode
        //Ponavlja se onoliko puta koliko imamo test metoda
    }

    @AfterClass
    public void afterClass() {
        System.out.println("===============");
        System.out.println("STAMPA IZ AFTER CLASS");
        System.out.println("===============");
        //Metoda se izvrsava samo jednom na kraju
    }

}