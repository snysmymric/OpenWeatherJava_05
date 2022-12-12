package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeUsersPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement errorEmailMessage;

    public HomeUsersPage(WebDriver driver)
    {super(driver);}

    public String getErrorEmailMessage() {
        return getText(errorEmailMessage);
    }
}
