import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ChromeTest {

    protected WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        String login = "aleksmelostnoy";
        String password = "MoscowNeverSleep22";
        driver.get("https://github.com/");

        StartPage startPage = new StartPage(driver);
        LoginPage loginPage = startPage.signIn();
        HomePage homePage = loginPage.loginAs(login, password);
        homePage.clickAvatar();
        String actualText = homePage.getLoginNameWebElement().getText();
        Assert.assertEquals(actualText, login);
        StatusPage statusPage = homePage.clickSetStatusButton();
        statusPage.typeStatusMessage()
                .checkBusy()
                .select30minutsForClearStatus();
        homePage = statusPage.clickStatusButton();
        SettingsPage settingsPage = homePage.clickSettingsMenuItem();


        homePage.signOut();

    }

}
