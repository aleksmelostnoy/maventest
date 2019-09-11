import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private final WebDriver driver;

    By avatar = By.xpath("//*[@aria-label='View profile and more']/img");
    By loginName = By.xpath("//a[text() = 'Signed in as ']/strong");
    By setupStatusButton = By.xpath("//details-menu/div[contains(@class, 'user-status-container')]");
    By signOutButton = By.xpath("//form[@class = 'logout-form']/button");
    By settingsMenuItem = By.xpath("//a[@href='/settings/profile']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage clickAvatar() {
        driver.findElement(avatar).click();
        return this;
    }

    public WebElement getLoginNameWebElement() {
        return driver.findElement(loginName);
    }

    public StatusPage clickSetStatusButton() {
        driver.findElement(setupStatusButton).click();
        return new StatusPage(driver);
    }

    public SettingsPage clickSettingsMenuItem() {
        driver.findElement(settingsMenuItem).click();
        return new SettingsPage(driver);
    }

    public LoginPage signOut() {
        driver.findElement(signOutButton).click();
        return new LoginPage(driver);
    }

}
