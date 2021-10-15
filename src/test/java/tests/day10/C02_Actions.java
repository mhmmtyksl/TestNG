package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C02_Actions extends TestBase {

    @Test
    public void test() {
        // amazon anasayfaya gidin
        driver.get("https://amazon.com");

        // nutella icin arama yapin
        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("nutella"+ Keys.ENTER);

        // 9.urunu tiklayin
        driver.findElement(By.xpath("//img[@data-image-index='9']")).click();
        // normalde burada 9. elemana girt diyoruz ama ekranda onu gormedigi icin hata verir ve bulamaz.
        // bunu cozmek icin sayfanin asagi dogru indirilmesi gerekir yoksa hata verir bulamaz cunku urunu.
        // ancak amazon da ayni class tan yazdiklari icin kod calisti, normalde calismamasi gerekir.

    }
}
