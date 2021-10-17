package tests.odevler;

    // 1. Bir class olusturun : EnableTest

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
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class C09_EnableTest {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    // 2. Bir metod olusturun : isEnabled()
    @Test
    public void isEnabledTest() {

        // 3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        // 4. Textbox’in etkin olmadigini(enabled) dogrulayın
        WebElement textBox=driver.findElement(By.xpath("//input[@type='text']"));
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertFalse(textBox.isEnabled());

        // 5. Enable butonuna tıklayın ve textbox etkin oluncaya kadar bekleyin
        driver.findElement(By.xpath("//button[@onclick='swapInput()']")).click();
        WebDriverWait wait=new WebDriverWait(driver,20);
        WebElement textBoxEtkin=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='swapInput()']")));
        Assert.assertTrue(textBoxEtkin.isEnabled());

        // 6. “It’s enabled!” mesajinin goruntulendigini dogrulayın.
        WebElement itsEnabled=driver.findElement(By.id("message"));
        softAssert.assertTrue(itsEnabled.isDisplayed());

        // 7. Textbox’in etkin oldugunu(enabled) dogrulayın.
        softAssert.assertTrue(textBox.isEnabled());
        softAssert.assertAll();
    }
    @AfterClass
    public void tearDown() {
        driver.quit();

    }

}
