package tests.odevler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class C05_JqueryScript {

    // go to web site : https://www.jqueryscript.net/demo/Easy-iFrame-based-Twitter-Emoji-Picker-Plugin-jQuery-Emoojis/
    // maximize the web site
    // click on second emoji
    // click all second emoji's element
    // go back parent iframe
    // fill out the form,(Fill the form with the texts you want)
    // click on apply button

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
        // go to web site (Bu siteye gidin): https://www.jqueryscript.net/demo/Easy-iFrame-based-Twitter-Emoji-Picker-Plugin-jQuery-Emoojis/
        driver.get("https://www.jqueryscript.net/demo/Easy-iFrame-based-Twitter-Emoji-Picker-Plugin-jQuery-Emoojis/");

        // maximize the web site (sayfayi buyutun)
        driver.manage().window().maximize();

        // click on second emoji(ikinci emojiye tiklayin)
        driver.switchTo().frame("emoojis");
        driver.findElement(By.xpath(" //a[@href='#nature']")).click();

        // click all second emoji's element(ikinci emojinin tum elemanlarina tiklayin
        List<WebElement> allEmojis=driver.findElements(By.xpath("//div[@class='mdl-tabs__panel is-active']/div/img"));
        allEmojis.stream().forEach(t-> t.click());

        // go back parent iframe(parent iframe e gidin)
        driver.switchTo().defaultContent();

        //fill out the form,(Fill the form with the texts you want)(Formu istediğiniz metinlerle doldurun)
        List <WebElement> inputs = driver.findElements(By.tagName("input"));
        List<String> personalize= new ArrayList<>(Arrays.asList("Text","Smiles","Nature","Food","Activities","Places","Objects","Symbols","Flags","Emojis","Tab"));
        for (int i = 0;i<inputs.size(); i++) {
            inputs.get(i).sendKeys(personalize.get(i));
        }
        //click on apply button(uygula düğmesine tıklayın)
        driver.findElement(By.id("send")).click();

    }










}
