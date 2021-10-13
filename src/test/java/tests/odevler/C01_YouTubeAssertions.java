package tests.odevler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class C01_YouTubeAssertions {

    // 1) Bir class oluşturun: YoutubeAssertions
    // 2) https://www.youtube.com adresine gidin

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.youtube.com");
        driver.findElement(By.xpath("//*[.='Ich stimme zu']")).click();
    }

    //3) Aşağıdaki adları kullanarak 4 test metodu oluşturun ve gerekli testleri yapin
    //○ titleTest => Sayfa başlığının “YouTube” oldugunu test edin
    @Test
    public void testTitle() {
        Assert.assertEquals(driver.getTitle(),"YouTube","title YouTube degil");
    }

    //○ imageTest => YouTube resminin görüntülendiğini (isDisplayed()) test edin

    @Test
    public void testResim() {
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='yt-simple-endpoint style-scope ytd-topbar-logo-renderer']")).isDisplayed());
    }

    // ○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
    @Test
    public void testSearchBox() {
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='search_query']")).isEnabled());
    }

    //○ wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin
    @Test
    public void testWrongTitle() {
        Assert.assertNotEquals(driver.getTitle(), "youtube");
    }

    @AfterMethod
    public void tearDown() {
    //driver.close();
}




}
