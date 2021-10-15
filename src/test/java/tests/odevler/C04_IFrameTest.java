package tests.odevler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

    //  ● Bir class olusturun: IframeTest02

public class C04_IFrameTest {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws InterruptedException {

        //  1) http://demo.guru99.com/test/guru99home/ sitesine gidiniz
        driver.get("http://demo.guru99.com/test/guru99home/");

        //  2) sayfadaki iframe sayısını bulunuz.
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("Sayfadaki IFrame sayisi : " + size);

        //  3) ilk iframe'deki (Youtube) play butonuna tıklayınız.
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']")).click();
        Thread.sleep(6000); // bunun amaci 6 saniye boyunca videonun oynatildigini gormek
        driver.findElement(By.xpath("//button[@aria-label='Pause (k)']")).click(); // bunun amaci videonun surekli devam etmesini onlemek

        //  4) ilk iframe'den çıkıp ana sayfaya dönünüz
        driver.switchTo().defaultContent(); // veya driver.switchTo().parentFrame(); kullanilabilir

        //  5) ikinci iframe'deki (Jmeter Made Easy) linke (https://www.guru99.com/live-selenium- project.html) tıklayınız
        driver.switchTo().frame(1);
        driver.findElement(By.xpath("//img[@src='Jmeter720.png']")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.close();

    }

}
