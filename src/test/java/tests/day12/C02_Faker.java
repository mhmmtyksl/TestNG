package tests.day12;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C02_Faker extends TestBase {

    @Test
    public void test() throws InterruptedException {

        // "https://facebook.com"  Adresine gidin
        driver.get("https://facebook.com");
        driver.findElement(By.xpath("//button[@class='_42ft _4jy0 _9xo7 _4jy3 _4jy1 selected _51sy']")).click();
        // bunun amaci cookies onayini yapmak icin

        //"create new account" butonuna basin
        driver.findElement(By.linkText("Neues Konto erstellen")).click();

        //"firstName" giris kutusuna bir isim yazin
        WebElement nameKutusu=driver.findElement(By.xpath("//input[@name='firstname']"));
        // burada ilk olarak isim kutusunu locate ediyoruz ve artik sonrakileri
        // locate etmeden actions methodlariyla direkt tab ile doldurabiliyoruz

        //"surname" giris kutusuna bir soyisim yazin
        //"email" giris kutusuna bir email yazin
        //"email" onay kutusuna emaili tekrar yazin
        //Bir sifre girin
        //Tarih icin gun secin
        //Tarih icin ay secin
        //Tarih icin yil secin
        //Cinsiyeti secin

        Faker faker=new Faker(); // burada faker dan yararlaniyoruz her defasinda bize farkli bilgilerle giris sagliyor

        String email=faker.internet().emailAddress();
        // email adresini string e atadik cunku dogrulama icin tekrar istiyor eger her defasinda
        // faker dan alacak olsa farkli email ler geleceginden hesap olusmaz ve test basarisiz olur
        // bunun icin ilk basta string e atayip bir defa faklerdan aldiktan sonra aynisini kullaniyoruz

        Actions actions=new Actions(driver);
        actions
                .click(nameKutusu)
                .sendKeys(faker.name().name())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                .sendKeys(Keys.TAB) // burada iki defa tab a basmamiz gerekiyor
                .sendKeys(Keys.TAB)
                .sendKeys(String.valueOf(faker.number().numberBetween(1,31)))
                .sendKeys(Keys.TAB)
                .sendKeys("May")
                .sendKeys(Keys.TAB)
                .sendKeys(String.valueOf(faker.number().numberBetween(1950,2020)))
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ARROW_RIGHT) // ok isaretiyle saga gecerek cinsiyet seciyoruz
                //.sendKeys(Keys.ARROW_LEFT) // bunu da yazarsak kadin i secer
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB).perform();

        Thread.sleep(10000); // bilgilerimizi gormek icin 10 sn beklettik
                actions.sendKeys(Keys.ENTER)
                        .perform();

        //Isaretlediginiz cinsiyetin secili, diger cinsiyet kutusunun secili olmadigini test edin.
        //Sayfayi kapatin
        WebElement erkek=driver.findElement(By.xpath("//input[@value='2']"));
        Assert.assertTrue(erkek.isSelected());

        WebElement kadin=driver.findElement(By.xpath("//input[@value='1']"));
        Assert.assertFalse(kadin.isSelected());

        WebElement diger=driver.findElement(By.xpath("//input[@value='-1']"));
        Assert.assertFalse(diger.isSelected());

    }
}
