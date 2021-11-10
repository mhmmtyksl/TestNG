package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Test04 {

    WebDriver driver;
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void test() throws InterruptedException {

        // 1) https://www.ntv.com.tr/ ADRESİNE GİT
        driver.get("https://www.ntv.com.tr/");

        // 2) SAYFANIN TiTLE'NIN "NTV HABER - Haberler, Son Dakika Haberleri" OLDUGUNU DOGRULA
        String expectedTitle="NTV HABER - Haberler, Son Dakika Haberleri";
        String actualTitle=driver.getTitle();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualTitle,expectedTitle,"Title istenenden farkli");

        // 3) SAYFANIN HANDLE DEGERiNi AL.
        String ilkSayfaHandle=driver.getWindowHandle();
        Thread.sleep(2000);

        // 4) SPOR SAYFASINA TIKLA
        driver.findElement(By.xpath("//a[@class='header-category-link spor']")).click();

        // 5) URL'iN "https://www.ntvspor.net/" OLDUGUNU DOGRULA
        System.out.println(driver.getCurrentUrl());
        String yeniSayfaHandle="";
        Set<String> tumHandles=driver.getWindowHandles();
        for (String each: tumHandles) {
            if(!each.equals(ilkSayfaHandle)) {
                yeniSayfaHandle=each;
            }
        }
        System.out.println("2. sayfanin handle degeri : "+yeniSayfaHandle);

        driver.switchTo().window(yeniSayfaHandle);
        System.out.println("Yeni sayfa Url : "+driver.getCurrentUrl());
        String expectedUrl="https://www.ntvspor.net/";
        String actualUrl=driver.getCurrentUrl();
        softAssert.assertEquals(actualUrl,expectedUrl,"Url beklenenden farkli");

        softAssert.assertAll();

        // 6) ANA SAYFAYA GERi DON
        driver.switchTo().defaultContent();

    }
    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
