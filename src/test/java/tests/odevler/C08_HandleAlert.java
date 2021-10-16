package tests.odevler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class C08_HandleAlert {

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

        //  go to url :http://demo.automationtesting.in/Alerts.html
        driver.get("http://demo.automationtesting.in/Alerts.html");

        //  click  "Alert with OK" and click 'click the button to display an alert box:'
        driver.findElement(By.xpath("(//a[@class='analystic'])[1]")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();

        //  accept Alert(I am an alert box!) and print alert on console
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        //  click "Alert with OK & Cancel" and click 'click the button to display a confirm box'
        driver.findElement(By.xpath("(//a[@class='analystic'])[2]")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        //  cancel Alert  (Press a Button !)
        driver.switchTo().alert().dismiss();

        //  click "Alert with Textbox" and click 'click the button to demonstrate the prompt box'
        driver.findElement(By.xpath("(//a[@class='analystic'])[3]")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();

        //  and then sendKeys 'TechProEducation' (Please enter your name)
        driver.switchTo().alert().sendKeys("TechProEducation");
        driver.switchTo().alert().accept();

        //  finally print on console this mesaaje "Hello TechproEducation How are you today"
        System.out.println(driver.findElement(By.id("demo1")).getText());
    }

    @AfterClass
    public void tearDown() {

        driver.close();
    }
}
