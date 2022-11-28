package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class TopMenuPage extends BasePage {

    @FindBy(xpath = "//div[@id = 'desktop-menu']//a[@href='/guide']")
    private WebElement guideMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/examples']")
    private WebElement partnersMenu;

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    public GuidePage clickGuideMenu() {
        click(guideMenu);

        return new GuidePage(getDriver());
    }

    public PartnersPage clickPartnersMenu() {
        click(partnersMenu);

        return new PartnersPage(getDriver());
    }
}
