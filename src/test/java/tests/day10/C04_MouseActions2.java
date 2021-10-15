package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import javax.swing.*;

//Yeni bir class olusturalim: D14_MouseActions2

public class C04_MouseActions2 extends TestBase {

    @Test
    public void test() {

        //1- https://demoqa.com/droppable adresine gidelim
        driver.get("https://demoqa.com/droppable");

        //2- “Drag me” butonunu tutup “Drop here” kutusunun ustune birakalim
        Actions actions=new Actions(driver);
        WebElement dragElementi=driver.findElement(By.id("draggable")); // kaynagi yani tasinacak yaziyi locate ettik
        WebElement dropAlani=driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));// tasinacak olan alani locate ettik
        actions.dragAndDrop(dragElementi,dropAlani).perform(); // once kaynak sonra goturulecek alan

        //3- “Drop here” yazisi yerine “Dropped!” oldugunu test edin
        WebElement droppedYazisi= driver.findElement(By.xpath("//*[text()='Dropped!']"));
        String actualDroppedyazisi=droppedYazisi.getText();
        String expectedDroppedYazisi="Dropped!";
        Assert.assertEquals(actualDroppedyazisi,expectedDroppedYazisi,"dropped yazisi beklenenden farkli");
    }
}