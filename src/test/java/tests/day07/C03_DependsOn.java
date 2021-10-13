package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class C03_DependsOn {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    // ● Bir class oluşturun: DependsOnTest
    //● https://www.amazon.com/ adresine gidin.
    //1. Test : amazon ana sayfaya gittiginizi test edin
    @Test
    public void test1() {
        driver.get("https://www.amazon.com/");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com/", "Url amazon degil");
    }

    //2. Test : 1.Test basarili ise search Box’i kullanarak “Nutella” icin arama yapin ve aramanizin gerceklestigini Test edin
    @Test (dependsOnMethods = "test1") // bunu yapmadigimiz zaman sadece test2 calistirildigi zaman kod calismaz cunku
    // amazon a git komutu 1. methodun icinde ve amazon a gidilmedigi icin bu method calismaz.
    // ama dependsOnMethods yazilinca sadece 2. method calistirilsa dahi amazona gidilir test1 de otomatik calisir.
    // eger test1 FAILED olursa test2 hic calismaz onu gormezden gelir. ve ignore yazar.
    public void test2() {
        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Nutella"+ Keys.ENTER);

        Assert.assertTrue(driver.getTitle().contains("Nutella"), "title Nutella icermiyor");
    }

    //3.Test : “Nutella” icin arama yapildiysa ilk urunu tiklayin ve fiyatinin $16.83 oldugunu test edin
    @Test(dependsOnMethods = "test2") // sadece 3 u calistirmak istersek zincir seklinde calisip test1 i kendiliginden calistiramaz
    public void test3() {
        driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).click();
        WebElement fiyat=driver.findElement(By.xpath("//span[@class='a-size-base a-color-price']"));
        System.out.println(fiyat.getText());
        Assert.assertEquals(fiyat.getText(), "16,83 $");

    }
    @AfterClass
    public void tearDown() {
         //driver.close();
    }

}
