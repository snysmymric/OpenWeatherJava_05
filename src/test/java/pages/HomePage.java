package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class = 'panel-body']")
    WebElement signedInMessage;

    @FindBy(id = "user-dropdown")
    WebElement userTopMenu;

    @FindBy(xpath = "//ul[@id='user-dropdown-menu']/li")
    List<WebElement> userDropdownMenuLinks;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {

        return getText(signedInMessage);
    }

    public HomePage clickUserDropdown() {
        click(userTopMenu);

        return this;
    }

    public List<String> getTextUserDropDownMenuLInks() {

        return getListText(userDropdownMenuLinks);
    }
}
