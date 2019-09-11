import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    By loginInputField = By.id("login_field");
    By passwordInputField = By.id("password");
    By loginButton = By.name("commit");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage typeUserName(String userName) {
        driver.findElement(loginInputField).sendKeys(userName);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordInputField).sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        driver.findElement(loginButton).submit();
        return new HomePage(driver);
    }
    public HomePage loginAs(String userName, String password) {
        typeUserName(userName);
        typePassword(password);
        return submitLogin();
    }

}
