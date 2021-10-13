package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C02_IFrame {

    // IFrame bir site icerisindeki baska sitelerin olmasi durumudur.
    // mesela bazi sitelerdfe YouTube videolarina erisme imkani olmasi gibi

    // Her Allert JS Allert degildir
    // Allert ciktiginda sag click yapip incele diyebiliyorsak bu bir HTML alert'dir
    // HTML alert'ler siradan webelement'ler olarak locate edilip kullanilabilir
    // Sag click yapamiyorsak alert bir JS Allert'dur ve switchTo() kullanilarak handle edilebilir

    //  ● Bir class olusturun: C02_IframeTest
    //  ● https://the-internet.herokuapp.com/iframe adresine gidin.
    //  ● Bir metod olusturun: iframeTest
    // ○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve  konsolda  yazdirin.
    // ○ Text Box’a “Merhaba Dunya!” yazin.
    // ○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu  dogrulayin ve  konsolda yazdirin.

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/iframe");
    }

    //  ● Bir metod olusturun: iframeTest
    @Test
    public void iframeTest() {
        // ○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve  konsolda  yazdirin.
        WebElement baslikYaziElementi=driver.findElement(By.tagName("h3"));
        Assert.assertTrue(baslikYaziElementi.isEnabled(),"baslik yazisi erisilebilir degil");
        System.out.println(baslikYaziElementi.getText());

        // ○ Text Box’a “Merhaba Dunya!” yazin.
        driver.switchTo().frame(0); // veya frame(mce_0_ifr) // burada IFrame e gecis yaptik yani farkli bir alana gecis yaptik
        WebElement yaziKutusu=driver.findElement(By.xpath("//*[@id='tinymce']"));

        yaziKutusu.clear();
        yaziKutusu.sendKeys("Merhaba Dunya");
        // bir Iframe e gecis yaptiktan sonra tekrardan ana sayfa ile ilgili islem yapmak istersek
        // gecis yaptigimiz IFrame den geri donmeliyioz bunu da su sekilde yapariz

        driver.switchTo().parentFrame(); // parentFrame() yada defaultFrame() secilebilir.
        // bu ikisi arasindaki fark birden fazla IFrame icine gecis yapildiysa her Parent dedikce bis ust parent a gider.
        // ama defaultFrame() secersek en basa doner. bizde sadece bir tane IFrame oldugu icin farketmez.
        // ikisini de kullanabiliriz. duruma gore degerlendirmek gerekir.

        // ○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu  dogrulayin ve  konsolda yazdirin.
        WebElement elemantalLinki=driver.findElement(By.linkText("Elemental Selenium"));

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertTrue(elemantalLinki.isDisplayed());
        softAssert.assertAll();
        System.out.println(elemantalLinki.getText());

    }


    @AfterClass
    public void tearDown() {
        driver.close();

    }




}
