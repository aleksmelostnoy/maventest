import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CalcTest {

    private Calc calc = new Calc();

    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println("@BeforeMethod");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("@AfterMethod");
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        System.out.println("@BeforeClass");
    }

    @AfterClass
    public void afterClass() throws Exception {
        System.out.println("@AfterClass");
    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        System.out.println("@BeforeSuite");
    }

    @AfterSuite
    public void afterSuite() throws Exception {
        System.out.println("@AfterSuite");
    }

    @Test
    public void testSum() throws Exception {
        System.out.println("Выполняется тест");
        assertEquals(calc.summ(2,3), 5);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullPointerException() {
        List list = null;
        int size = list.size();
    }

    @Test(timeOut = 1000)
    public void waitLongTime() throws Exception {
        Thread.sleep(10);
    }

    @Test(dependsOnMethods={"initEnvironmentTest"})
    public void testmethod() {
        System.out.println("This is testmethod");
    }

    @Test
    public void initEnvironmentTest() {
        System.out.println("This is initEnvironmentTest");
    }

    @DataProvider
    public Object[][] summLocalData() {
        return new Object[][]{
                {5, calc.summ(2, 3)},
                {0, calc.summ(0, 0)},
                {0, calc.summ(-2, 2)},
                {-100, calc.summ(-30, -70)},
                {10000, calc.summ(3000, 7000)},
        };
    }

    @Test(dataProvider = "summLocalData")
    public void sum(int rezult, int sumDigit) {
        assertEquals(rezult, sumDigit);
        System.out.println(rezult);
    }

}


