package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test08_TestNGDataProviderTest extends TestBase {
    // Siteyi açınız. http://opencart.abstracta.us/index.php?route=account/login ,
    // login yapiniz Email: asd@gmail.com   password : 123qweasd
    // Search fonksiyonunu kullanarak
    // Mac,ipod ve samsung icin Dataprovider ile yapınız.
    @Test (dataProvider =  "getData")
    void UserNameTest (String username, String password ) {
        System.out.println("username : " + username + " "  + "password :" + password);
    }
    @DataProvider
    public Object [][] getData(){
        Object [][] data = {
                {"Muhammet", "123"},
                {"Murat", "asd"},
                {"Kubilay", "1q2w"}
        };
        return data;
    }

    @Test(dataProvider = "getData2")
    void userNameTest(String userName) {
        System.out.println("Username : "+userName);
    }
    @DataProvider
    public Iterator<Object> getData2() {
        List<Object> data=new ArrayList<>();
        data.add("Merve");
        data.add("Esra");
        data.add("Hakan");
        return data.iterator();
    }
}
