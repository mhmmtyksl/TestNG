package tests.odevler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class C02_DropDownAmazon {

    //● Bir class oluşturun: C3_DropDownAmazon
    //● https://www.amazon.com/ adresine gidin.

    WebDriver driver;


    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com/");

    }

    //- Test 1
    //Arama kutusunun yanindaki kategori menusundeki kategori
    //sayisinin 45 oldugunu test edin
    @Test
    public void test1(){
        WebElement dropDown=driver.findElement(By.id("searchDropdownBox"));
        Select select=new Select(dropDown);
        List<WebElement> kategoriler=select.getOptions();
        // System.out.println(kategoriler.size());

        Assert.assertEquals(kategoriler.size(),45,"Test FAILED, kategori sayisi 45 degildir");
        System.out.println("Mevcut kategori sayisi : "+ (kategoriler.size()-1));
        // burada 1 cikardik cunku Alle yazisi da bir kategori olarak sayilmakta
    }

    //-Test 2
    // 1. Kategori menusunden Bücher secenegini secin
    // 2. Arama kutusuna Java yazin ve aratin
    // 3. Bulunan sonuc sayisini yazdirin
    // 4. Sonucun Java kelimesini icerdigini test edin
    @Test
    public void test2(){
        WebElement dropDown=driver.findElement(By.id("searchDropdownBox"));
        Select select=new Select(dropDown);
        select.selectByValue("search-alias=stripbooks-intl-ship");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java"+ Keys.ENTER);
        WebElement sonuc=driver.findElement(By.xpath("//*[.='1-16 von mehr als 30.000 Ergebnissen oder Vorschlägen für']"));
        System.out.println(sonuc.getText());
        Assert.assertTrue(sonuc.getText().contains("Java"),"Sonuc Java kelimesini icermiyor");
    }
    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
