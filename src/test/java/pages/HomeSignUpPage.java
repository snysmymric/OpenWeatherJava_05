package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeSignUpPage extends FooterMenuPage {

    @FindBy(xpath="//h3[@class='first-child']")
    private WebElement confirmSubscribeFreeLinkOpened;

    public HomeSignUpPage(WebDriver driver) {
        super(driver);
    }

    public HomeSignUpPage confirmPageSubscribeFreeOpen() {
        wait10ElementToBeVisible(confirmSubscribeFreeLinkOpened);

        return this;
    }
}
