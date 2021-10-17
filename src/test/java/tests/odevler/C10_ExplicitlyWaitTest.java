package tests.odevler;

    // 1. Bir class olusturun : ExplicitlyWaitTest

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class C10_ExplicitlyWaitTest {

    WebDriver driver;
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    // 2. Bir metod olusturun : enableTest()
    @Test
    public void enableTest() {

        // 3. https://demoqa.com/dynamic-properties adresine gidin.
        driver.get("https://demoqa.com/dynamic-properties");

        // 4. Will enable 5 seconds’in etkin olmadigini(enabled) test edin
        WebElement willEnable5SecondsButton=driver.findElement(By.id("enableAfter"));
        Assert.assertFalse(willEnable5SecondsButton.isEnabled());

        // 5. Will enable 5 seconds etkin oluncaya kadar bekleyin ve enabled oldugunu test edin
        WebDriverWait wait=new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(willEnable5SecondsButton));
        Assert.assertTrue(willEnable5SecondsButton.isEnabled());
    }

    // 6. Bir metod olusturun : visibleTest()
    @Test
    public void visibleTest() {

        // 7. Ayni sayfaya tekrar gidin, Visible After 5 Seconds butonunun
        //   gorunmesini bekleyin, ve gorunur oldugunu test edin
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait=new WebDriverWait(driver,20);
        WebElement visibleAfter5Seconds=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
        Assert.assertTrue(visibleAfter5Seconds.isDisplayed());

    }
    @AfterClass
    public void tearDown() {
        driver.quit();

    }

}
