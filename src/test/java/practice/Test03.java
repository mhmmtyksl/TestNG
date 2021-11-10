package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test03 {


    WebDriver driver;
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void dropDown() {
        // 1) "https://www.facebook.com/" SAYFASINA GiDiN
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//button[@class='_42ft _4jy0 _9xo7 _4jy3 _4jy1 selected _51sy']")).click();

        // 2) YENi HESAP OLUSTUR BUTONUNA TIKLAYIN
        driver.findElement(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']")).click();

        // 3) DOGUM TARiHi BOLUMUNDEKi GUNLERiN LiSTESiNi ALIN
        WebElement dropDownGun=driver.findElement(By.id("day"));
        Select select=new Select(dropDownGun);
        List<WebElement> daySecenekler=select.getOptions();
        System.out.println("=====GUNLER=====");
        for (WebElement each: daySecenekler) {
            System.out.println(each.getText());
        }

        // 4) DOGUM TARiHi BOLUMUNDEKi AYLARIN LiSTESiNi ALIN
        WebElement dropDownAy=driver.findElement(By.id("month"));
        Select select1=new Select(dropDownAy);
        List<WebElement> aySecenekler=select1.getOptions();
        System.out.println("=====AYLAR=====");
        for (WebElement each: aySecenekler) {
            System.out.println(each.getText());
        }

        // 5) DOGUM TARiHi BOLUMUNDEKi YILLARIN LiSTESiNi ALIN
        WebElement dropDownYil=driver.findElement(By.id("year"));
        Select select2= new Select(dropDownYil);
        List<WebElement> yilSecenekler=select2.getOptions();
        System.out.println("=====YILLAR=====");
        for (WebElement each: yilSecenekler) {
            System.out.println(each.getText());
        }

    }
}
