package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends HomeTopMenuPage {

    @FindBy(xpath = "//div[@class = 'panel-body']")
    WebElement signedInMessage;

    @FindBy(id = "user-dropdown")
    WebElement userTopMenu;

    @FindBy(xpath = "//ul[@id='user-dropdown-menu']/li")
    List<WebElement> userDropdownMenuLinks;

    @FindBy(xpath = "//ul[@id='myTab']//a[@href='/api_keys']")
    private WebElement apiKeysTab;

    @FindBy(xpath = "//h2")
    private List<WebElement> h2Title;

    @FindBy(className = "btn_like")
    private List<WebElement> orangeButtons;

    @FindBy(xpath = "//ul[@class='nav nav-tabs pull-left']//a")
    private List<WebElement> navTabs;

    @FindBy(xpath = "//div[@class = 'panel-body']")
    WebElement signOutMessage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {

        return getText(signedInMessage);
    }

    public List<String> getTextUserDropDownMenuLInks() {

        return getListText(userDropdownMenuLinks);
    }

    public HomeAPIKeysPage clickAPIKeysTab() {
        click(apiKeysTab);

        return new HomeAPIKeysPage(getDriver());
    }

    public int getH2Titles() {

        return getListSize(h2Title);
    }

    public List<String> listH2TitlesSighInPage() {

        return getTexts(h2Title);
    }

    public int orangeButtonsSignIn() {
        allElementsVisibleAndClickable(orangeButtons);

        return getListSize(orangeButtons);
    }

    public int navTabs() {
        allElementsVisibleAndClickable(navTabs);

        return getListSize(navTabs);
    }

    public List<String> listNavTabElements() {

        return getTexts(navTabs);
    }

    public String getMessage() {

        return getText(signOutMessage);
    }
    
    public HomePage clickUserDropdown() {
        click(userTopMenu);

        return this;
    }
}
