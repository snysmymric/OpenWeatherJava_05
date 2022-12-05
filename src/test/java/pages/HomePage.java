package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class = 'panel-body']")
    WebElement signedInMessage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {

        return getText(signedInMessage);
    }
}
