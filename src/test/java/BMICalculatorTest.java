import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class BMICalculatorTest {

    protected WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get("https://www.calculator.net/bmi-calculator.html");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                new Object[] {"170", "65", "35", "22.5", "Normal"},
                new Object[] {"170", "45", "35", "15.6", "Severe thinness"},
                new Object[] {"170", "78", "35", "27", "Overweight"},
                new Object[] {"170", "90", "35", "31.1", "Obese Class I"},
                new Object[] {"170", "110", "35", "38.1", "Obese Class II"},
                new Object[] {"170", "125", "35", "43.3", "Obese Class III"},
        };
    }

    @Test(dataProvider = "testData")
    public void bMICalculatorTest(String height, String weight, String age, String bmi, String category) {

        try {
            WebElement heightField = driver.findElement(By.name("cheightmeter"));
            heightField.clear();
            heightField.sendKeys(height);

            WebElement weightField = driver.findElement(By.name("ckg"));
            weightField.clear();
            weightField.sendKeys(weight);

            WebElement ageField = driver.findElement(By.name("cage"));
            ageField.clear();
            ageField.sendKeys(age);

            WebElement calculateButton = driver.findElement(By.xpath("//input[@alt='Calculate']"));
            calculateButton.click();

            WebElement bmiLabel = driver.findElement(By.xpath("//div[contains(@class, 'rightresult')]/div/b"));
            Assert.assertTrue(bmiLabel.getText().contains(bmi));

            WebElement bmiCategoryLabel = driver.findElement(By.xpath("//div[contains(@class, 'rightresult')]/div/font/b"));
            Assert.assertEquals(bmiCategoryLabel.getText(),category);

        } catch (Error e) { //Capture and append Exceptions/Errors
            verificationErrors.append(e.toString());
        }
    }

}
