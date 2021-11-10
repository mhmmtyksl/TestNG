package practice;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Test2_Hazir {

    public static void main(String[] args) throws InterruptedException {

        // 1) https://gmibank.com/ ADRESiNE GiDiN
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://gmibank.com/");

        // 2) Sign In BUTONUNA TIKLAYIN
        driver.findElement(By.xpath("(//a[@class='dropdown-toggle nav-link'])[2]")).click();
        driver.findElement(By.xpath("//*[span='Sign in']")).click();

        // 3) Sign in EKRANINA GiTTiGiNiZi DOGRULAYIN
        System.out.println(driver.getCurrentUrl());
        String expectedUrl = "https://gmibank.com/login";

        if(driver.getCurrentUrl().equals(expectedUrl)){
            System.out.println("URL Sign in Testi PASS");
        }else {
            System.out.println("URL Sign in Testi FAİLED");
        }

        Thread.sleep(5000);

        // 4) Sign in EKRANINA GiTTiGiNiZi FARKLI BiR WebElement'i KULLANARAK,2. KEZ DOGRULAYIN

        if((driver.findElement(By.xpath("(//span[.='Sign in'])[2]")).getText()).equals("Sign in")){
            System.out.println("URL Sign in Testi PASS");
        } else {
            System.out.println("URL Sign in Testi FAILED");
        }

        // 5) VERiLEN KULLANICI ADI VE SiFRE iLE SAYFAYA GiRiS YAPIN (Username: employee32, Password: team32team32-)

        WebElement username = driver.findElement(By.cssSelector("#username"));
        username.sendKeys("employee32", Keys.TAB);

        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.sendKeys("team32team32-", Keys.ENTER);

        // 6) VERiLEN KULLANICI iLE GiRiS YAPTIGINIZI DOGRULAYIN
        String userPAssw=driver.findElement(By.xpath("(//*[span='employee32 employee32'])")).getText();
        if (userPAssw.equals("employee32 employee32")){
            System.out.println("User-PASSWORD Test PASS");
        }else{
            System.out.println("User-PASSWORD Test FAİLED");
        }

        // 7) VERiLEN KULLANICI iLE GiRiS YAPTIGINIZI FARKLI BiR WEBELEMENT'i KULLANARAK 2. KEZ DOGRULAYIN
        WebElement myOperations = driver.findElement(By.xpath("//span[.='My Operations']"));
        System.out.println(myOperations.getText());

        if(myOperations.getText().equals("My Operations")){
            System.out.println("Giris testi basarili, Test PASS");
        } else {
            System.out.println("Giris testi basarisiz, Test FAILED");
        }

        // 8) SAYFADAN CIKIS YAPIN
        driver.findElement(By.xpath("(//*[span='employee32 employee32'])")).click();
        driver.findElement(By.xpath("//span[.='Sign out']")).click();

        // 9) SAYFADAN CIKIS YAPILDIGINI DOGRULAYIN
        System.out.println(driver.findElement(By.xpath("//h2")).getText());

        if(driver.findElement(By.xpath("//h2")).isDisplayed()){
            System.out.println("Sign out basarili, Test PASS");
        } else {
            System.out.println("Sign out basarisiz, Test FAILED");
        }

        // 10) FARKLI BiR WEBELEMENT'i KULLANARAK, SAYFADAN CIKIS YAPILDIGINI 2. KEZ DOGRULAYIN
        if(driver.findElement(By.linkText("SIGN IN AGAIN")).isDisplayed()){
            System.out.println("Sign out basarili, Test PASS");
        } else {
            System.out.println("Sign out basarisiz, Test FAILED");
        }
    }
}