import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// @Test(priority = 1) suteikia testo eiliskumo pirmuma pagal nurodytus skaicius//

public class TestClass2 {

    WebDriver _globalDriver;
    String _email;
    String _user;

    public static boolean assertEquals(String actual, String expected) {
        return actual.equals(expected);
    }


    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            email.append(randomChar);
        }

        // Adding '@' symbol
        email.append("@");

        // Selecting random domain
        String randomDomain = domains[random.nextInt(domains.length)];
        email.append(randomDomain);

        return email.toString();
    }
    public String generateUsername() {
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder username = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            username.append(randomChar);

        }
        return username.toString();
    }

    @BeforeClass
    public void SetupUserDetails() {
        _email = generateRandomEmail();
        _user = generateUsername();
    }

    @BeforeTest
    public void SetupWebDriver() {
        _globalDriver = new ChromeDriver();
        _globalDriver.get("https://elenta.lt/");
    }


    @Test
    public void atidarytiNarsykle()
    {
        ChromeDriver driver = new ChromeDriver();
        _globalDriver.get("https://www.google.lt");
    }
    @Test // anotacija, kuri leidžia javai suprasti, kad tai yra testas ir jį reikia paleisti.
    public void test1(){//jei atidaro teisingai pakūrėme projektą
        WebDriver driver = new ChromeDriver();
        _globalDriver.get("https://www.skelbiu.lt");//reikia https
    }
    @Test //leisti po viena
    public void test2(){//jei atidaro teisingai pakūrėme projektą
        WebDriver driver = new ChromeDriver();
        _globalDriver.get("https://www.skelbiu.lt");

        WebElement cookieButton = _globalDriver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[1]/div/div[2]/div/button[1]")); //čia pilnas xphath. parodyti kaip išsitraukti pilną ir dalinį. (//*[@id="onetrust-accept-btn-handler"]) papasakoti kuo skirias. Dalinis xpath eina nuo artimiausio tėvinio unikalaus švyturio, pilnas nuo HTML pradžios.
        cookieButton.click();
        _globalDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/a[3]")).sendKeys("traktorius");// operacija nekuriant kintamojo.
        _globalDriver.findElement(By.xpath("//*[@id=\"searchButton\"]")).click();
    }


    @ Test//leisti po viena
    public void test3(){//jei atidaro teisingai pakūrėme projektą
        WebDriver driver = new ChromeDriver();
        _globalDriver.get("https://www.skelbiu.lt");

        _globalDriver.findElement(By.id("onetrust-accept-btn-handler")).click();
        _globalDriver.findElement(By.id("searchKeyword")).sendKeys("traktorius");// operacija nekuriant kintamojo.
        // //*[@id="searchKeyword"]  paltginti dalinio xpath struktūrą su id. iš xpath matosi, kad elementas turi savo id.
        // papasakoti kad galima selectinti pagal id, unikalu, pagal klase, (jei vienas elementas ok, jei keli su ta klase paims pirmą. arba su findelements galima paimti visus su x klase, bet tai vėliau.)
        _globalDriver.findElement(By.id("searchButton")).click();

    }
    @ Test //Registravimasis teisingais duomenimis
    public void TS1 (){

        WebElement cookieButton = _globalDriver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]/p"));
        cookieButton.click();
        WebElement logInButton = _globalDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/a[3]"));
        logInButton.click();
        WebElement registrationButton = _globalDriver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[10]/td/p/a"));
        registrationButton.click();

        _globalDriver.findElement(By.id("UserName")).click();
        _globalDriver.findElement(By.id("UserName")).sendKeys(_user);
        _globalDriver.findElement(By.id("Email")).click();
        _globalDriver.findElement(By.id("Email")).sendKeys(_email);
        _globalDriver.findElement(By.id("Password")).click();
        _globalDriver.findElement(By.id("Password")).sendKeys("FiktyviAnketa123");
        _globalDriver.findElement(By.id("Password2")).click();
        _globalDriver.findElement(By.id("Password2")).sendKeys("FiktyviAnketa123");

        WebElement registrationButton2 = _globalDriver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registrationButton2.click();
        WebElement resultText = _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/h1/b"));
        Assert.assertEquals(resultText.getText(),"Jūs sėkmingai prisiregistravote!");
        _globalDriver.close();
    }
    @ Test
    public void TS2(){//Prisijungiama su uzregistruotu vartotoju ir ikeliamas skelbimas//

        //Prisijungimo procesas//

        WebElement cookieButton = _globalDriver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]/p"));
        cookieButton.click();
        WebElement logInButton = _globalDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/a[3]"));
        logInButton.click();
        _globalDriver.findElement(By.id("UserName")).click();
        _globalDriver.findElement(By.id("UserName")).sendKeys("PūkisMiau.");
        _globalDriver.findElement(By.id("Password")).click();
        _globalDriver.findElement(By.id("Password")).sendKeys("FiktyviAnketa123");
        WebElement signInButton = _globalDriver.findElement(By.xpath("//html/body/div[1]/form/fieldset/table/tbody/tr[4]/td[2]/input"));
        signInButton.click();
        //Skelbimo ikelimo procesas//
        _globalDriver.findElement(By.id("submit-new-ad-nav-button")).click();
        WebElement selectCategory = _globalDriver.findElement(By.xpath("/html/body/div[1]/ul/li[4]/a"));
        selectCategory.click();
        WebElement selectCategory2 = _globalDriver.findElement(By.xpath("/html/body/div[1]/ul/li[13]/a"));
        selectCategory2.click();
        _globalDriver.findElement(By.id("title")).click();
        _globalDriver.findElement(By.id("title")).sendKeys("Parduodu knygą \"Tiems, kurie neskaito\"");
        _globalDriver.findElement(By.id("text")).click();
        _globalDriver.findElement(By.id("text")).sendKeys("Knyga yra naudota, geros būklės.");
        _globalDriver.findElement(By.id("price")).click();
        _globalDriver.findElement(By.id("price")).sendKeys("7");
        _globalDriver.findElement(By.id("location-search-box")).click();
        _globalDriver.findElement(By.id("location-search-box")).sendKeys("Palanga");
        _globalDriver.findElement(By.id("phone")).click();
        _globalDriver.findElement(By.id("phone")).sendKeys("+37063586485");
        _globalDriver.findElement(By.id("email")).click();
        _globalDriver.findElement(By.id("email")).sendKeys("pukismiau123@inbox.lt");
        _globalDriver.findElement(By.id("submit-button")).click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //_globalDriver.findElement(By.id("inputfile")).click();
        _globalDriver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\palub\\OneDrive\\Paveikslėliai\\New folder\\Screenshot 2024-03-28 160630.jpg");
        _globalDriver.findElement(By.id("forward-button")).click();
        _globalDriver.findElement(By.id("forward-button")).click();











    }


}
