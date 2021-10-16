package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

//1. Bir class olusturun : C01_WaitTest
//2. Iki tane metod olusturun : implicitWait() , explicitWait()
//  Iki metod icin de asagidaki adimlari test edin.

public class C01_WaitTest extends TestBase {

    @Test
    public void ImplicitlyWaitTest() {
        //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //4. Remove butonuna basin.
        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();

        //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
        SoftAssert softAssert =new SoftAssert();
        WebElement sonucYazisi=driver.findElement(By.xpath("//p[@id='message']"));
        softAssert.assertTrue(sonucYazisi.isDisplayed());
        softAssert.assertAll();

        //6. Add buttonuna basin
        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();

        //7. It’s back mesajinin gorundugunu test edin
        sonucYazisi=driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(sonucYazisi.isDisplayed());
    }



    @Test
    public void explicitlyWaitTest() {

        //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //4. Remove butonuna basin.
        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();

        //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
        WebDriverWait wait=new WebDriverWait(driver,20);

        // explicitly wait i istersek locate islemi ile birlikte tek satirda yapabiliriz
        // ikisini birlikte yaptigimiz icin WebElement ile basladik
        WebElement yaziLocateIleBirlikte=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        Assert.assertTrue(yaziLocateIleBirlikte.isDisplayed());

        // veya once locate edip uygun method kullanarak beklemeyi yaptirabiliriz
        // burda once zaten WenElementi olusturdugumuz icin direkt wait ile basliyoruz
        // ancak bu test icin once WebElementi olusturmak anlamsiz olur,
        // cunku biz wait islemini zaten o WebElement olussun diye yapiyoruz.
        // wait olmadan o WebElement olmaz, o element olmadan da sectigimiz method calismaz
        // bu yuzden alttaki kodu yorum haline aldik
        /*
        WebElement sonucYazisi=driver.findElement(By.xpath("//p[@id='message']"));
        wait.until(ExpectedConditions.visibilityOf(sonucYazisi));
        Assert.assertTrue(sonucYazisi.isDisplayed());
        */

        //6. Add buttonuna basin
        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();

        //7. It’s back mesajinin gorundugunu test edin
        WebElement itsBackLocateIle=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        Assert.assertTrue(itsBackLocateIle.isDisplayed());

    }







}
