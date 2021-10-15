package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.Set;

public class C03_MouseActions1 extends TestBase {

    //1- Yeni bir class olusturalim: C03_MouseActions1
    //2- https://the-internet.herokuapp.com/context_menu sitesine gidelim
    //3- Cizili alan uzerinde sag click yapalim
    //4- Alert’te cikan yazinin “You selected a context menu” oldugunu
    //   test edelim.
    //5- Tamam diyerek alert’I kapatalim
    //6- Elemental Selenium linkine tiklayalim
    //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim

    @Test
    public void test() {

        //2- https://the-internet.herokuapp.com/context_menu sitesine gidelim
        driver.get("https://the-internet.herokuapp.com/context_menu");

        //3- Cizili alan uzerinde sag click yapalim
        Actions actions=new Actions(driver);
        WebElement ciziliAlan=driver.findElement(By.id("hot-spot"));

        actions.contextClick(ciziliAlan).perform();

        //4- Alert’te cikan yazinin “You selected a context menu” oldugunu test edelim.
        String expectedAlertYazisi="You selected a context menu";
        String actualAlertYazisi=driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertYazisi,expectedAlertYazisi,"Alert yazisi istenenden farkli");

        //5- Tamam diyerek alert’I kapatalim
        driver.switchTo().alert().accept();

        //6- Elemental Selenium linkine tiklayalim
        String ilkSayfaHandle=driver.getWindowHandle();
        driver.findElement(By.linkText("Elemental Selenium")).click();

        //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim
        Set<String> windowTumHandlelar=driver.getWindowHandles();
        String ikinciSayfaHandle="";

        for (String each:windowTumHandlelar) {
            if (!each.equals(ilkSayfaHandle)) {
                ikinciSayfaHandle=each;
            }
        }
        driver.switchTo().window(ikinciSayfaHandle);
        String expectedIkinciSayfaYazi="Elemental Selenium";
        String actualIkinciSayfaYazi=driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(actualIkinciSayfaYazi,expectedIkinciSayfaYazi,"ekinci sayfadaki yazi istenenden farkli");

    }

}
//  Actions Class
//  ➢ 1.Adım: Actions class’ta bir object oluşturulur.
//      Actions actions= new Actions(driver);
//  ➢ 2. Adım: Üzerinde çalışmak istediğiniz WebElement öğesini bulunur.
//      WebElement element = driver.findElement(By.id("ID"));
//  ➢ 3.Adim : Ardından bu webelement üzerinde action gerçekleştirilir. Örneğin sağ tıklamak için.
//      actions.contextClick(element).perform();
//  perform() En sonda action’i gerçekleştirmek için kullanılır

//  Mouse Aksiyonlari
//  ■ doubleClick (): Öğeye çift tıklama yapar
//  ■ clickAndHold (): Fareyi serbest bırakmadan uzun tıklama yapar
//  ■ dragAndDrop (): Öğeyi bir noktadan diğerine sürükler ve bırakır
//  ■ moveToElement (): Fare işaretçisini öğenin ortasına kaydırır
//  ■ contextClick (): Fare üzerinde sağ tıklama yapar