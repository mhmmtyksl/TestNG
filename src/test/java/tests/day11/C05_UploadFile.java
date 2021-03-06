package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

//1.Tests packagenin altina bir class oluşturun : C05_UploadFile

public class C05_UploadFile extends TestBase {

    @Test
    public void test() throws InterruptedException {

        //2.https://the-internet.herokuapp.com/upload adresine gidelim
        driver.get("https://the-internet.herokuapp.com/upload");

        //3.chooseFile butonuna basalim


        //4.Yuklemek istediginiz dosyayi secelim.
        String dosyaYolu = System.getProperty("user.home")+ "/Downloads/logo.png";
        WebElement dosyaYukle=driver.findElement(By.id("file-upload"));

        dosyaYukle.sendKeys(dosyaYolu); // once yuklenecek yeri yaziyoruz sendkeys in icine ise
        // yuklemek istedigimiz dosyanin yolunu yaziyoruz

        Thread.sleep(5000);

        //5.Upload butonuna basalim.
        driver.findElement(By.id("file-submit")).click(); // burada ise upload butonunu locate ettik
        // ve click tusuna bastik yuklemesi icin.
        Thread.sleep(5000);

        //6.“File Uploaded!” textinin goruntulendigini test edelim.
        WebElement sonucYazisiElementi=driver.findElement(By.tagName("h3"));
        Assert.assertTrue(sonucYazisiElementi.isDisplayed());
        // burada ise ekranda File Uploaded yazisinin goruntulendigini dogruladik




    }


}
