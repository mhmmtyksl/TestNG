package tests.day09;

import org.testng.annotations.Test;
import utilities.TestBase;

public class C03_TestBaseIlkTest extends TestBase {
    // burada @BeforeClass vs olusturmadan sadece onlari onceden olusturdugumuz TestBase
    // class ina extends yaparak onlara ulasabiliriz.
    // TestBase deki driver in acces modifier i public de olabilir ama biz ayni package icinden
    // kullanacagimiz icin protected yaptik. bu sekilde de kullanabiliriz.


    @Test
    public void test() {

        driver.get("https://www.techproeducation.com");
        System.out.println(driver.getTitle());

        // burada sadece adres ve yazdirma yazdik ama testBase class i da calisti ve sayfayi acti
        // ardindan geri kapatti.
    }
}
