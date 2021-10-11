package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class C01_Priority {

    // 3test methodu olusturun
    // 1. amazon anasayfasina
    // 2. techproeducation anasayfasina
    // 3. facebook anasayfasina gitsin
    // ve sayfa title larini yazdirsin

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test (priority = 10)
    public void amazonTest(){
        driver.get("https://www.amazon.com");
    }

    @Test
    public void techProTest(){
        driver.get("https://www.techproeducation.com");
    }

    @Test (priority = 0)
    public void faceBookTest(){
        driver.get("https://www.facebook.com");
    }

    @Test (priority =-1)
    public void yahooTest(){
        driver.get("https://www.yahoo.com");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

//Priorty belirtmez isek priority value default olarak '0' olur.
//1-Priority value'su negatif olanlar value'larina gore siralanarak ilk olarak calisir.
//2-Priority belirtmediklerimiz oncesinde kendi aralarinda alfabetik olarak calisir,
//3-Priority'leri pozitif olanlar value'larina gore siralanarak calisir.

