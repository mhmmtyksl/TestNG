package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class C01_Priority {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    // 3test methodu olusturun
    // 1. amazon anasayfasina gitsin
    @Test (priority = 10)
    public void amazonTest(){
        driver.get("https://www.amazon.com");
    }

    // 2. techproeducation anasayfasina gitsin
    @Test
    public void techProTest(){
        driver.get("https://www.techproeducation.com");
    }

    // 3. facebook anasayfasina gitsin
    @Test (priority = 0)
    public void faceBookTest(){
        driver.get("https://www.facebook.com");
    }

    // ve sayfa title larini yazdirsin
    @Test (priority =-1)
    public void yahooTest(){
        driver.get("https://www.yahoo.com");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

// Priorty belirtmez isek priority value default olarak '0' olur.
// 1-Priority value'su negatif olanlar value'larina gore siralanarak ilk olarak calisir.
// 2-Priority belirtmediklerimiz oncesinde kendi aralarinda alfabetik olarak calisir,
// 3-Priority'leri pozitif olanlar value'larina gore siralanarak calisir.
// ornek vermek gerekirse sunlar yazilirsa siariyla su sekilde calisirlar
// -10 -5 (0 veya hic yazilmayan varsa(onu da 0 kabul eder) alfabetik siraya gore olur) 4 10... devam eder

