package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

        //1- Bir Class olusturalim C01_KeyboardActions1

public class C01_KeyboardActions extends TestBase {


    @Test
    public void test() {

        //2- https://www.amazon.com sayfasina gidelim
        driver.get("https://www.amazon.com");

        //3- Arama kutusuna actions method’larine kullanarak samsung A71 yazdirin ve Enter’a basarak arama yaptirin
        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));

        Actions actions=new Actions(driver);

        // aramaKutusu.sendKeys("samsung A71"); // simdiye kadar bu sekilde yapiyorduk
        // yani once locate edip sonra istedigimizi yaziyorduk
        // simdi ogrenecegimiz yol her ne kadar daha uzun gibi girunsede daha kullanisli oldugunu gorecegiz
        /*
        actions.click(aramaKutusu).perform(); // sayfaya gittikten sonra oncelikle aramaKutusuna tikladik
        actions.sendKeys("samsung ").perform();
        actions.keyDown(Keys.SHIFT).perform();
        actions.sendKeys("a").perform();
        actions.keyUp(Keys.SHIFT).perform();
        actions.sendKeys("71").perform();
        actions.sendKeys(Keys.ENTER).perform();
        */
        // bu yukardakileri asagidaki gibi kisaltabiliriz.
        // functional programing den kaynakli bu kullanisli programlama

        actions.click(aramaKutusu)
                .sendKeys("samsung ")
                .keyDown(Keys.SHIFT)// buyuk yazmak icin Shift tusuna bastik
                .sendKeys("a")
                .keyUp(Keys.SHIFT) // Shift tusunu tekrar serbest biraktik
                .sendKeys("71")
                .sendKeys(Keys.ENTER).perform();


        //4- aramanin gerceklestigini test edin
        String arananKelime="samsung A71";
        String actualTitle=driver.getTitle();
        // baslikta aradigimiz kelime varsa arama gerceklesmistir

        Assert.assertTrue(actualTitle.contains(arananKelime),"arama yapilamadi");
    }


}
