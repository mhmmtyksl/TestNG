package tests.day11;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;

//1. Tests packagenin altina bir class oluşturalim : C04_FileDownload

public class C04_FileDownload extends TestBase {

    @Test
    public void downloadTest() throws InterruptedException {

        //2. Iki tane metod oluşturun : isExist() ve downloadTest()
        //3. downloadTest () metodunun icinde aşağıdaki testi yapalim:
        // - https://the-internet.herokuapp.com/download adresine gidelim.
        driver.get("https://the-internet.herokuapp.com/download");

        // - logo.png dosyasını indirelim
        driver.findElement(By.xpath("//a[text()='logo.png']")).click();
        Thread.sleep(5000); // bu sureyi vermemizin sebebi indirabilmesi icin zaman vermek
    }
        //4. Ardından isExist()  methoduyda dosyanın başarıyla indirilip indirilmediğini test edelim
        @Test
        public void isExist(){

        String dosyaYolu = System.getProperty("user.home")+ "/Downloads/logo.png";
            Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));
            System.out.println(dosyaYolu); // /Users/muhammetyuksel/Downloads/logo.png

            // burada dikkat edilmesi gereken onemli bir husus var once mutlaka download testinin calkismasi gerekir
            // aksi halde indirme islemi yapilmadigi icin isExists fail olur ve hata verir. bunun icin
            // kodlara priorty yani sira verilebilir. bizim ornegimizde alfabetik olarak d once geldigi icin
            // test calisti ama buna dikkat etmek gerekir


        }



}
