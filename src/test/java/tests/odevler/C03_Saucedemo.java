package tests.odevler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class C03_Saucedemo {

    /*
     * 1) Navigate to  https://www.saucedemo.com/
     * 2) Enter the user name  as "standard_user"
     * 3) Enter the password as   "secret_sauce"
     * 4) Click on login button
     * 5) Choose price low to high
     * 6) Verify item prices are sorted from low to high
     */

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void test() {
        // 1) Navigate to  "https://www.saucedemo.com/"
        driver.get("https://www.saucedemo.com/");

        // 2) Enter the user name  as "standard_user"
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        // 3) Enter the password as   "secret_sauce"
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        // 4) Click on login button
        driver.findElement(By.id("login-button")).click();

        // 5) Choose price low to high
        WebElement dropDown=driver.findElement(By.className("product_sort_container"));
        Select select=new Select(dropDown);
        select.selectByValue("lohi");

        // 6) Verify item prices are sorted from low to high
        String actualData=driver.findElement(By.className("active_option")).getText();
        String expectedValue="PRICE (LOW TO HIGH)";

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualData,expectedValue,"Secilen option 'Price (low to high)' degildir");

        softAssert.assertAll();

    }
    @AfterClass
    public void tearDown() {

        driver.close();
    }

}
