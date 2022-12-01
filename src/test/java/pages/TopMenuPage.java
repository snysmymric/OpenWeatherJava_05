package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//div[@id='desktop-menu']//form[@role='search']")
    private WebElement searchBoxTopMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//input[@type='text']")
    private WebElement searchFieldTopMenu;
    
    @FindBy(xpath = "//div//input[@name='q']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@id='support-dropdown']")
    private WebElement supportMenu;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']")
    private WebElement supportDropdownMenu;

    @FindBy(xpath = "//div[@id = 'desktop-menu']//a[@href='/our-initiatives']")
    private WebElement ourInitiativesMenu;

    @FindBy(xpath = "//li[@class='logo']/a")
    private WebElement logo;

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

        return  new HomeMarketplacePage(getDriver());
    }

    public MainPage clickLogo() {
        click(logo);

        return new MainPage(getDriver());
    }
    
    public String getInnerTextOfPlaceholder (String attribute){

        return getAttributeOfElement(searchFieldTopMenu,attribute);
    }

    public boolean placeholderIsDisplayed (){

        return isDisplayed(searchBoxTopMenu);
    }

    public OurInitiativesPage clickOurInitiativesMenu() {
        click(ourInitiativesMenu);

        return new OurInitiativesPage(getDriver());
    }
    
    public FindPage inputTopMenuSearchFieldAndEnter(String text) {
        inputAndEnter(searchField, text);

        return new FindPage(getDriver());
    }

    public MainPage clickSupportMenu() {
        click(supportMenu);

        return new MainPage(getDriver());
    }

    public String getActualAttributeValue(String attribute) {

        return getAttributeOfElement(supportDropdownMenu, attribute);
    }
}
