package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class C02_DropDown {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test(){
        // Bir class oluşturun: DropDown
        //● https://the-internet.herokuapp.com/dropdown adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropDown=driver.findElement(By.id("dropdown")); // burada kutuyu locate ettik
        Select select=new Select(dropDown); // burada Select class ini kullanarak bir obje olusturduk
        // ve parametre olarak locate ettigimiz WebElement i yazdik

        //    1.Index kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
        select.selectByIndex(1);// isteigimiz option i select objesini kullanarak seciyoruz
        System.out.println(select.getFirstSelectedOption().getText());

        //    2.Value kullanarak Seçenek 2'yi (Option 2) seçin ve yazdırın
        select.selectByValue("2"); // burda value yi String olarak vermisler o yuzden " " icerisinde yazilmasi gerekir
        System.out.println(select.getFirstSelectedOption().getText());

        //    3.Visible Text(Görünen metin) kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
        select.selectByVisibleText("Option 1");
        select.selectByVisibleText("Option 2"); // buraya gelinceye kadar en son ne varsa onu yazdirir.
        // bu ikinci kisim normalde soruda yok biz gormek icin yaptik.
        System.out.println("1 cikarsa yanlis 2 cikarsa dogru "+ select.getFirstSelectedOption().getText());

        //    4.Tüm dropdown değerleri(value) yazdırın
        List<WebElement> tumOpsiyonlar=select.getOptions();
        for (WebElement each: tumOpsiyonlar) {
            System.out.println(each.getText());
        }
        //    5. Dropdown’un boyutunu bulun, Dropdown’da 4 öğe varsa konsolda True , degilse False yazdırın.
        System.out.println(tumOpsiyonlar.size());
        if (tumOpsiyonlar.size()==4) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }











}
