package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class C01_HardAssertion {

    // amazon sayfasina gidin
    // url'in amazon icerdigini test edin
    // title'in amazon icerdigini test edin
    // java kelimesi aratin ve listedeki ilk urunun java kelimesi icerdigini test edin

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test1(){
        // amazon sayfasina gidin
        driver.get("https://www.amazon.com");

        // url'in amazon icerdigini test edin
        Assert.assertTrue(driver.getCurrentUrl().contains("amazon"));

        // title'in "Amazon" icerdigini test edin
        Assert.assertTrue(driver.getTitle().contains("Amazon"));
        // basta kucuk a ile amazon yazmistik ama harf buyuk oldugu icin test burda Failed oldu ve calismayi durdurdu.
        // daha sonra handle edince calismaya devam etti.

        // hardAssert  oldugu icin ilk Failed olan assertin da execution durur
        // ancak buradaki hata duzeltilince test calismaya devam eder.
        System.out.println("test failed oldugundan bu satir calismadi");

        // java kelimesi aratin ve listedeki ilk urunun java kelimesi icerdigini test edin
        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("java"+ Keys.ENTER);
        WebElement ilkUrun=driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]"));
        Assert.assertTrue(ilkUrun.getText().contains("Java"),"ilk urun java icermiyor");
        // burada da ilk basta kucuk j ile java arattik be hata verdi tekrar ahndle etmemiz gerekti
        // ama her hata icin kodu tekrar tekrar calistirmamiz gerekti.
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
}

// bu yontemde yani HardAssertion da bir kod calismazsa sonrasindakilere hic gecmez
// bu hata handle edilince sonraki kodlara gecer ve inceler.
// bu da her defasinda failed dureumunda tekrar tekrar koda donup duzeltme yapmak gerekir ve bu da
// ciddi bir zaman kaybidir. bunu tercih edenler de var ama softAssertion buna gore daha mantikli

