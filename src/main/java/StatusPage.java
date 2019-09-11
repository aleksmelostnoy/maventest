import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StatusPage {

    private final WebDriver driver;

    By statusMessage = By.xpath("//input[@aria-label='What is your current status?']");
    By busyCheckBox = By.name("limited_availability");
    By clearStatusCombobox = By.xpath("//summary[@aria-haspopup='true']");
    By statusPeriod = By.xpath("//button[@title='in 30 minutes']");
    By setStatusButton = By.xpath("//button[contains(@class, 'js-user-status-submit')]");

    public StatusPage(WebDriver driver) {
        this.driver = driver;
    }

    public StatusPage typeStatusMessage() {
        driver.findElement(statusMessage).sendKeys("I am free :) ");
        return this;
    }

    public StatusPage checkBusy() {
        WebElement busyCheckBoxWebElement = driver.findElement(busyCheckBox);
        if (!busyCheckBoxWebElement.isSelected()) {
            busyCheckBoxWebElement.click();
        }
        return this;
    }

    public StatusPage select30minutsForClearStatus() {
        driver.findElement(clearStatusCombobox).click();
        driver.findElement(statusPeriod).click();
        return this;
    }

    public HomePage clickStatusButton() {
        driver.findElement(setStatusButton).click();
        return new HomePage(driver);
    }


}
