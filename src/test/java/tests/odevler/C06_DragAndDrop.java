package tests.odevler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class C06_DragAndDrop {

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

        //  Go to http://demo.guru99.com/test/drag_drop.html url
        driver.get("http://demo.guru99.com/test/drag_drop.html");

        Actions action=new Actions(driver);

        //  Drag and drop the BANK button to the Account section in DEBIT SIDE
        WebElement drag1=driver.findElement(By.xpath("(//a[@class='button button-orange'])[5]"));
        WebElement drop1=driver.findElement(By.id("bank"));
        action.dragAndDrop(drag1,drop1).perform();

        //  Drag and drop the SALES button to the Account section in CREDIT SIDE
        WebElement drag2=driver.findElement(By.xpath("(//a[@class='button button-orange'])[6]"));
        WebElement drop2=driver.findElement(By.id("loan"));
        action.dragAndDrop(drag2,drop2).perform();

        //  Drag and drop the 5000 button to the Amount section in DEBIT SIDE
        WebElement drag3=driver.findElement(By.xpath("(//a[@class='button button-orange'])[2]"));
        WebElement drop3=driver.findElement(By.id("amt7"));
        action.dragAndDrop(drag3,drop3).perform();

        //  Drag and drop the second 5000 button to the Amount section in CREDIT SIDE
        WebElement drag4=driver.findElement(By.xpath("(//a[@class='button button-orange'])[4]"));
        WebElement drop4=driver.findElement(By.id("amt8"));
        action.dragAndDrop(drag4,drop4).perform();

    }

}


