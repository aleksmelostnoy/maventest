import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[@href='/login']")
    WebElement signInElement;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage signIn() {
        signInElement.click();
        return new LoginPage(driver);
    }
}
