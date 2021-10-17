package tests.odevler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.Set;

public class C11_HandleWindow extends TestBase {

    @Test
    public void test() {

        //  go to url :http://demo.guru99.com/popup.php
        driver.get("http://demo.guru99.com/popup.php");

        //  get the first window
        String firstWindowHandle=driver.getWindowHandle();

        //  clicking on click here button
        WebElement clickHere=driver.findElement(By.xpath("//a[@target='_blank']"));
        clickHere.click();

        //  get all the window in the set
        Set<String> allWindowHandles=driver.getWindowHandles();

        //  switch to window
        String secondWindowHandle="";
        for (String each:allWindowHandles) {
            if (!each.equals(firstWindowHandle)) {
                secondWindowHandle=each;
            }
        }
        driver.switchTo().window(secondWindowHandle);

        //  input email id (somepne@gmail.com) and type something in that input
        WebElement input=driver.findElement(By.name("emailid"));
        input.sendKeys("somepne@gmail.com");

        //  Clicking on the submit button
        WebElement submit=driver.findElement(By.name("btnLogin"));
        submit.click();

        //  verify title as expected
        String expectedTitle="Guru99 Bank Home Page";
        String actualTitle=driver.getTitle();

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualTitle,expectedTitle,"The title is different, Test FAILED");
        softAssert.assertAll();

        //  switch to first window
        driver.switchTo().window(firstWindowHandle);
    }
}
