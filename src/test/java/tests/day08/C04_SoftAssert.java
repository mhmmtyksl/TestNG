package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C04_SoftAssert {

    // Yeni bir Class Olusturun : D11_SoftAssert1
    //1.“https://www.hepsiburada.com/” Adresine gidin
    //2.Basliginin “Türkiye'nin En Büyük Online Alışveriş Sitesi" icerdigini dogrulayin
    //3.search kutusuna araba yazip arattirin
    //4.bulunan sonuc sayisini yazdirin
    //5.sonuc yazisinin "araba" icerdigini dogrulayin
    //6.Sonuc yazisinin “oto” kelimesi icermedigini dogrulayin

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void test() {
        //1.“https://www.hepsiburada.com/” Adresine gidin
        driver.get("https://www.hepsiburada.com/");

        //2.Basliginin “Türkiye'nin En Büyük Online Alışveriş Sitesi" icerdigini dogrulayin
        // dogrulayin in ingilizcesi verify bunu gorursek hemen softAssert aklimiza gelmeli ve bunu kullanmaliyiz
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertTrue(driver.getTitle().contains("Türkiye'nin En Büyük Online Alışveriş Sitesi"),"title istenen cumleyi icermiyor");
        // softAssertta aralara testlere mesaj yazmak onemli cunku nerde hata oldugunu bu sekilde anlariz

        //3.search kutusuna araba yazip arattirin
        driver.findElement(By.xpath("//input[@class='desktopOldAutosuggestTheme-input']")).sendKeys("araba"+ Keys.ENTER);

        //4.bulunan sonuc sayisini yazdirin
        WebElement sonucYazisiElementi=driver.findElement(By.className("category-suggestion-title"));

        System.out.println(sonucYazisiElementi.getText());

        //5.sonuc yazisinin "araba" icerdigini dogrulayin
        softAssert.assertTrue(sonucYazisiElementi.getText().contains("araba"),"sonuc yazisi araba icermiyor");

        //6.Sonuc yazisinin “oto” kelimesi icermedigini dogrulayin
        softAssert.assertFalse(sonucYazisiElementi.getText().contains("oto"), "sonuc yazisi oto iceriyor");

        softAssert.assertAll();

    }
    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
