package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

import java.util.List;

public abstract class TopMenuPage extends BasePage {

    @FindBy(xpath = "//div[@id = 'desktop-menu']//a[@href='/guide']")
    private WebElement guideMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/examples']")
    private WebElement partnersMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/weathermap']")
    private WebElement mapsMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//li/a[@href='/api']")
    private WebElement apiMenu;


    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='https://home.openweathermap.org/marketplace']")
    private WebElement marketplaceMenu;

    @FindBy(id = "support-dropdown")
    private WebElement supportMenu;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']/li/a[@href='/appid']")
    private WebElement howToStartSupportSubmenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//form[@role='search']")
    private WebElement searchBoxTopMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//input[@type='text']")
    private WebElement searchFieldTopMenu;

    @FindBy(xpath = "//div//input[@name='q']")
    private WebElement searchField;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']")
    private WebElement supportDropdownMenu;

    @FindBy(xpath = "//div[@id = 'desktop-menu']//a[@href='/our-initiatives']")
    private WebElement ourInitiativesMenu;

    @FindBy(id = "support-dropdown")
    private WebElement searchSupportButton;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']/li/a[@href='/faq']")
    private WebElement confirmFAQ;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']/li/a[@href='/appid']")
    private WebElement confirmHowToStart;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']")
    private WebElement confirmAskAQuestion;

    @FindBy(id = "support-dropdown")
    private WebElement supportDropDMenu;

    @FindBy(xpath = "//li[@class='with-dropdown']//li/a")
    private List<WebElement> supportMenuLinks;

    @FindBy(xpath = "//div[@id = 'desktop-menu']//a[@href = '/price']")
    private WebElement pricingMenu;

    @FindBy(xpath = "//li[@class='logo']/a")
    private WebElement logo;

    @FindBy(xpath = "//div[@id='desktop-menu']/ul/li")
    private List<WebElement> topMenuButtons;

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

    public WeatherMapsPage clickMapsMenu() {
        click(mapsMenu);

        return new WeatherMapsPage(getDriver());
    }

    public APIPage clickAPIMenu() {
        click(apiMenu);

        return new APIPage(getDriver());
    }

    public HomeMarketplacePage clickMarketplaceMenu() {
        click(marketplaceMenu);

        return new HomeMarketplacePage(getDriver());
    }

    public String getInnerTextOfPlaceholder(String attribute) {

        return getAttribute(searchFieldTopMenu, attribute);
    }

    public MainPage clickLogo() {
        click(logo);

        return new MainPage(getDriver());
    }

    public PricePage clickPricingMenu() {
        click(pricingMenu);

        return new PricePage(getDriver());
    }

    public MainPage clickSupportMenu() {
        click(supportMenu);

        return new MainPage(getDriver());
    }

    public OurInitiativesPage clickOurInitiativesMenu() {
        click(ourInitiativesMenu);

        return new OurInitiativesPage(getDriver());
    }

    public HowToStartPage clickHowToStartSupportSubmenu() {
        click(howToStartSupportSubmenu);

        return new HowToStartPage(getDriver());
    }

    public boolean isPlaceholderDisplayed() {

        return isDisplayedElement(searchBoxTopMenu);
    }

    public FindPage inputTopMenuSearchFieldAndEnter(String text) {
        inputAndEnter(searchField, text);

        return new FindPage(getDriver());
    }

    public String getTextFAQ() {

        return getText(confirmFAQ);
    }

    public String getTextHowToStart() {

        return getText(confirmHowToStart);
    }

    public String getTextAskAQuestion() {

        return getText(confirmAskAQuestion);
    }

    public MainPage clickOnSupportMenu() {
        click(supportDropDMenu);

        return new MainPage(getDriver());
    }

    public List<String> getLinksList() {
        List<String> linksList = new ArrayList<>();
        for (WebElement link : supportMenuLinks) {
            linksList.add(link.getText());
        }
        return linksList;
    }

    public int countTopMenuButtons() {

        return getListSize(topMenuButtons);
    }

    public String getSupportMenuIsActiveValue(String attribute) {

        return getAttribute(supportDropdownMenu, attribute);

    }
}
