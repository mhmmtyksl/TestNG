package tests.day11;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import javax.sound.midi.Soundbank;
import java.nio.file.Files;
import java.nio.file.Paths;

public class C03_FileExist { // driver kullanmadigimiz icin extends yapmadik burada

    @Test
    public void test(){
        System.out.println(System.getProperty("user.home")); //  /Users/muhammetyuksel
        // "C:\Users\Lenovo\Desktop\picture.jpg"
        // Masaustundeki bir dosya yolunun tum bilgisayarlarda sorunsuz calismasi icin
        // dosya yolunu ikiye ayiracagiz
        // 1. herkesin bilgisayarinda farkli olan kisim
        //  bu kismi bilgisayardan System.getProperty("user.home") kodu ile alabiliriz
        // 2. herkes icin ayni olan kisim
        //    bu kisim 1.maddedeki yolun devami seklinde olur

        // ornek masaustumuzdeki picture dosyasi icin yol kaydedelim
        String dosyaYolu = System.getProperty("user.home")+ "/Desktop/picture.jpg";
        // mac ler icin bu sekilde ama windowslar icin daha farkli oluyor adres
        // bu yazilan kod dinamiktir her bilgisayarda calisir.

        System.out.println("dosya yolumuz : "+dosyaYolu); // dosya yolumuz : /Users/muhammetyuksel/Desktop/picture.jpg

        System.out.println(Files.exists (Paths.get (dosyaYolu))); // true
        // .isExists methodu var mi yokmu anlamindadir.

        Assert.assertTrue(Files.exists (Paths.get (dosyaYolu)));

        System.out.println(System.getProperty("user.dir")); // /Users/muhammetyuksel/Desktop/com.Batch30TestNG
        // ""user.dir"  icinde oldugumuz dosyanin yolunu getirir

    }
}