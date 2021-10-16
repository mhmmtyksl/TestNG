package tests.odevler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class C07_KeyboardActions {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws InterruptedException {

        //1- "http://webdriveruniversity.com/Actions" sayfasina gidin
        driver.get("http://webdriveruniversity.com/Actions");

        //2- Hover over Me First" kutusunun ustune gelin
        WebElement kutu=driver.findElement(By.xpath("(//button[@class='dropbtn'])[1]"));

        Actions actions=new Actions(driver);

        actions.click(kutu).perform();

        //3- Link 1" e tiklayin
        WebElement link=driver.findElement(By.xpath("(//a[@class='list-alert'])[1]"));

        actions.click(link).perform();

        Thread.sleep(4000);

        //4- Popup mesajini yazdirin
        String mesaj=driver.switchTo().alert().getText();
        System.out.println(mesaj);

        //5- Popup'i tamam diyerek kapatin
        driver.switchTo().alert().accept();

        //6- “Click and hold" kutusuna basili tutun
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        WebElement klick=driver.findElement(By.id("click-box"));
        actions.clickAndHold().perform();

        //7-“Click and hold" kutusunda cikan yaziyi yazdirin
        String yazi=driver.findElement(By.xpath("//*[.='Well done! keep holding that click now.....']")).getText();
        System.out.println(yazi);

        //8- “Double click me" butonunu cift tiklayin
        WebElement doubleClick=driver.findElement(By.id("double-click"));
        actions.doubleClick().doubleClick().perform();

        Thread.sleep(2000);

    }
    @AfterClass
    public void tearDown() {

        driver.close();
    }

}


