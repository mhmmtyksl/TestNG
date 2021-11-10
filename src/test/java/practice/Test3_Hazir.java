package practice;
import org.junit.*;
public class Test3_Hazir {
    //BASLANGICTA SADECE BiR DEFA CALISIR
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }

    //TEST'iN SONUNDA SADECE BiR DEFA CALISIR.
    @AfterClass
    public static void  afterClass() {
        System.out.println("After Class");
    }

    //HER TESTEN ONCE CALISIR.
    @Before
    public void before() {
        System.out.println("Before");
    }

    //HER TESTEN SONRA CALISIR.
    @After
    public void after() {
        System.out.println("after");
    }

    //TEST 1
    @Test
    public void test1() {
        System.out.println("Test 1");
    }

    //TEST 2
    @Test
    public void test2() {
        System.out.println("Test 2");
    }

    //TEST 3
    @Test
    public void test3() {
        System.out.println("Test 3");
    }
}