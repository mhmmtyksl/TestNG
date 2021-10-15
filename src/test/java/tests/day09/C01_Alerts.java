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

    // Her Alert JavaScript Alert degildir. uzerinde incele yapilamiyorsa JavaScript alert tir.
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
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");// burada iken istenen sayfaya gonderdik
    }

    // ● Bir metod olusturun: acceptAlert
    //        ○ 1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının
    //        “You successfully clicked an alert” oldugunu test edin.
    @Test
    public void acceptAlert() {
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click(); // burada text le locate ettik
        driver.switchTo().alert().accept();// burada once Alert e gecis yaptik ardindan tamam i tikladik

        WebElement resultYaziElementi=driver.findElement(By.id("result")); // id kullanarak locate ettik
        String expectedResult="You successfully clicked an alert";
        String actualResult=resultYaziElementi.getText();// sonuc yazisindaki metni string olarak aldik
        Assert.assertEquals(actualResult,expectedResult,"sonuc yazisi istenen ile ayni degil");
        // burada yazilan degerlerin yerleri farkli actual expected ve yazilan masaj yerleri JUnit e gore degisik
        // burada Assert i Assert sinifindan bir defa import ederek baslarina Asssert yazmadan da kullanabiliriz
    }

    // ● Bir metod olusturun: dismissAlert
    //        ○ 2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
    //        “successfuly” icermedigini test edin.
    @Test
    public void dismissAlert() {
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();// text ile locate ettik
        driver.switchTo().alert().dismiss();// driver ile alert e gectik ve iptal i tikladik
        // ustte gecis yapmis omamiz yeterli degil her defasinda alert e gecis yapmaliyiz

        String istenmeyenLelime="successfuly";
        WebElement resultYaziElementi=driver.findElement(By.id("result"));

        String actualResult=resultYaziElementi.getText();// sonuc yazisindaki metni string olarak aldik
        Assert.assertFalse(actualResult.contains(istenmeyenLelime),"result yazisi istenmeyen kelimeyi iceriyor");
    }

    // ● Bir metod olusturun: sendKeysAlert
    //        ○ 3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna
    //        tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.
    @Test
    public void sendKeysAlert() {
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();// text ile locate ettik
        String isim="Mehmet"; // stringe deger atadik
        driver.switchTo().alert().sendKeys(isim);// driver ile alert e gecis yaptik ve alert te metin yazdik
        driver.switchTo().alert().accept(); // tamam i tikladik

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

// Handle Allert Methods
// ● accept() => Bir uyarıda(alert) OK'ı tıklamakla aynı.
//  driver.switchTo().alert().accept();
// ● dismiss() => Bir uyarıda(alert) Cancel'ı tıklamakla aynı.
//  driver.switchTo().alert().dismiss();
// ● getText() => Uyarıdaki(alert) mesajı almak için.
//  driver.switchTo().alert().getText();
// ● sendKeys(“Text”) => Uyarı(alert) text kutusuna text göndermek için
//  driver.switchTo().alert().sendKeys("Text");