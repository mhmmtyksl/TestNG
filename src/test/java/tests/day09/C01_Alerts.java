package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C01_Alerts {

    // Her Alert JavaScript Alert degildir.
    // uzerine sag tiklayabiliyorsak bu bir HTML alerttir
    // hicbir sekilde sag veya sol tiklanmayip sadece tamam i tiklayabiliyorsak bu bir JavaScript Alerttir.


    // ● Bir class olusturun: C01_Alerts
    // ● https://the-internet.herokuapp.com/javascript_alerts adresine gidin.
    // ● Bir metod olusturun: acceptAlert
    //        ○ 1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının
    //        “You successfully clicked an alert” oldugunu test edin.
    // ● Bir metod olusturun: dismissAlert
    //        ○ 2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
    //        “successfuly” icermedigini test edin.
    // ● Bir metod olusturun: sendKeysAlert
    //        ○ 3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna
    //        tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    // ● Bir metod olusturun: acceptAlert
    //        ○ 1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının
    //        “You successfully clicked an alert” oldugunu test edin.
    @Test
    public void acceptAlert() {
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
        driver.switchTo().alert().accept();

        WebElement resultYaziElementi=driver.findElement(By.id("result"));
        String expectedResult="You successfully clicked an alert";
        String actualResult=resultYaziElementi.getText();
        Assert.assertEquals(actualResult,expectedResult,"sonuc yazisi istenen ile ayni degil");
    }

    // ● Bir metod olusturun: dismissAlert
    //        ○ 2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
    //        “successfuly” icermedigini test edin.
    @Test
    public void dismissAlert() {
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();

        String istenmeyenLelime="successfuly";
        WebElement resultYaziElementi=driver.findElement(By.id("result"));

        String actualResult=resultYaziElementi.getText();
        Assert.assertFalse(actualResult.contains(istenmeyenLelime),"result yazisi istenmeyen kelimeyi iceriyor");
    }

    // ● Bir metod olusturun: sendKeysAlert
    //        ○ 3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna
    //        tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.
    @Test
    public void sendKeysAlert() {
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
        String isim="Mehmet";
        driver.switchTo().alert().sendKeys(isim);
        driver.switchTo().alert().accept();

        WebElement resultYaziElementi=driver.findElement(By.id("result"));

        String actualResult=resultYaziElementi.getText();

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(actualResult.contains(isim));
        softAssert.assertAll();

    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
