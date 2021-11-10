package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

public class Test07 extends TestBase {

    @Test
    public void test() throws InterruptedException {
        //// 1) "http://automationpractice.com/" ADRESiNE GiDiN
        driver.get("http://automationpractice.com/");

        //// 2) 2. URUNUN UZERiNE GELiP Add to chart YAPIN
        Actions actions = new Actions(driver);
        WebElement birinciUrun = driver.findElement(By.xpath("(//a[@class='product-name'])[2]"));
        actions.moveToElement(birinciUrun).perform();
        driver.findElement(By.xpath("(//span[text()='Add to cart'])[2]")).click();
        driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();

        //// 3) 4. URUNUN UZERiNE GELiP Add to chart YAPIN
        WebElement ikinciUrun = driver.findElement(By.xpath("(//img[@title='Printed Dress'])[2]"));
        actions.moveToElement(ikinciUrun).perform();
        driver.findElement(By.xpath("(//span[text()='Add to cart'])[4]")).click();
        driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();

        //// 4) 5. URUNUN UZERiNE GELiP Add to chart YAPIN
        WebElement ucuncuUrun = driver.findElement(By.xpath("(//img[@title='Printed Summer Dress'])[1]"));
        actions.moveToElement(ucuncuUrun).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//span[text()='Add to cart'])[5]")).click();
        driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();

        //// 5) CHART a gelin 3 ürün olduğunu doğrulayın
        WebElement yazi = driver.findElement(By.xpath("//span[@class='ajax_cart_product_txt_s  unvisible']"));
        Assert.assertTrue(yazi.isDisplayed());

        //// 6) CHART'A GELiP Chek out TIKLAYIN
        WebElement cart=driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
        actions.moveToElement(cart).perform();
        driver.findElement(By.partialLinkText("Check out")).click();

        //// 7) toplam alışveriş miktarının 108.97 olduğunu doğrula
        SoftAssert softAssert=new SoftAssert();
        String expectedPreis="$108.97";
        String actualPreis=driver.findElement(By.xpath("//span[@id='total_price']")).getText();

        softAssert.assertEquals(actualPreis,expectedPreis, "fiyat beklenenden farkli");


        softAssert.assertAll();
    }
}