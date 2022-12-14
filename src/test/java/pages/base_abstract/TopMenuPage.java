package pages.base_abstract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;
import pages.top_menu.*;
import pages.home.HomeAskQuestionPage;
import pages.home.HomeMarketplacePage;
import pages.home.HomePage;
import pages.home.HomeSignInPage;

import java.util.ArrayList;
import java.util.List;

public abstract class TopMenuPage extends BasePage {

    @FindBy(xpath = "//li[@class='logo']/a")
    private WebElement logo;

    @FindBy(xpath = "//div[@id='desktop-menu']//form[@role='search']")
    private WebElement searchBoxTopMenu;

    @FindBy(xpath = "//div//input[@name='q']")
    private WebElement searchFieldTopMenu;

    @FindBy(xpath = "//div[@id = 'desktop-menu']//a[@href='/guide']")
    private WebElement guideTopMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//li/a[@href='/api']")
    private WebElement apiTopMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='https://home.openweathermap.org/marketplace']")
    private WebElement marketplaceTopMenu;

    @FindBy(xpath = "//div[@id = 'desktop-menu']//a[@href = '/price']")
    private WebElement pricingTopMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/weathermap']")
    private WebElement mapsTopMenu;

    @FindBy(xpath = "//div[@id = 'desktop-menu']//a[@href='/our-initiatives']")
    private WebElement ourInitiativesTopMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/examples']")
    private WebElement partnersTopMenu;

    @FindBy(xpath = "//div[@id='desktop-menu']/ul/li")
    private List<WebElement> topMenuButtons;

    @FindBy(xpath = "//li[@class='user-li']/a" )
    private WebElement signInTopMenu;

    @FindBy(id = "support-dropdown")
    private WebElement supportTopMenu;

    @FindBy(xpath = "//li[@class='with-dropdown']//ul")
    private WebElement supportTopMenuDropdown;

    @FindBy(xpath = "//li[@class='with-dropdown']//li/a")
    private List<WebElement> supportTopMenuDropdownLinks;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']/li/a[@href='/faq']")
    private WebElement faqSupportSubmenu;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']/li/a[@href='/appid']")
    private WebElement howToStartSupportSubmenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//li/a[@href='https://home.openweathermap.org/questions']")
    private WebElement askQuestionSupportSubmenu;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/weather-dashboard']")
    private WebElement dashboardTopMenu;

    @FindBy(xpath = "//nav/ul/li[@id='hamburger']/img")
    private WebElement hamburgerTopMenuIcon;

    @FindBy(xpath = "//ul[@id='mobile-menu']/li/a")
    private List <WebElement> hamburgerTopMenuDropdownLinks;

    private String originalHandle;

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    public TopMenuPage(WebDriver driver, String originalHandle) {
        super(driver);
        this.originalHandle = originalHandle;
    }

    public int countTopMenuButtons() {

        return getListSize(topMenuButtons);
    }

    public String getInnerTextOfPlaceholder(String attribute) {

        return getAttribute(searchFieldTopMenu, attribute);
    }

    public List<String> getLinksText() {

        return getTexts(supportTopMenuDropdownLinks);
    }

    public MainPage getWindowWithHamburgerMenu(int width, int height) {
        setNewDimensionOfWindow(width, height);

        return new MainPage(getDriver());
    }

    public String getSupportMenuIsActiveValue() {

        return getAttribute(supportTopMenuDropdown, "class");
    }

    public int getNumberOfOptionsHamburgerMenu() {

        return getListSize(hamburgerTopMenuDropdownLinks);
    }

    public List<String> getHamburgerMenuList () {

        return getListText(hamburgerTopMenuDropdownLinks);
    }

    public MainPage clickLogo() {
        click(logo);

        return new MainPage(getDriver());
    }

    public GuidePage clickGuideMenu() {
        click(guideTopMenu);

        return new GuidePage(getDriver());
    }

    public APIPage clickAPIMenu() {
        click(apiTopMenu);

        return new APIPage(getDriver());
    }

    public HomeMarketplacePage clickMarketplaceMenu() {
        click(marketplaceTopMenu);

        return new HomeMarketplacePage(getDriver());
    }

    public PricePage clickPricingMenu() {
        click(pricingTopMenu);

        return new PricePage(getDriver());
    }

    public WeatherMapsPage clickMapsMenu() {
        click(mapsTopMenu);

        return new WeatherMapsPage(getDriver());
    }

    public OurInitiativesPage clickOurInitiativesMenu() {
        click(ourInitiativesTopMenu);

        return new OurInitiativesPage(getDriver());
    }

    public PartnersPage clickPartnersMenu() {
        click(partnersTopMenu);

        return new PartnersPage(getDriver());
    }

    public MainPage clickSupportMenu() {
        click(supportTopMenu);

        return new MainPage(getDriver());
    }

    public FAQPage clickFAQSupportSubmenu() {
        click(faqSupportSubmenu);

        return new FAQPage(getDriver());
    }

    public HowToStartPage clickHowToStartSupportSubmenu() {
        click(howToStartSupportSubmenu);

        return new HowToStartPage(getDriver());
    }

    public FindPage inputSearchCriteriaIntoSearchFieldAndEnter(String text) {
        inputAndEnter(searchFieldTopMenu, text);

        return new FindPage(getDriver());
    }

    public WeatherDashboardPage clickDashboardMenu() {
        click(dashboardTopMenu);

        return new WeatherDashboardPage(getDriver());
    }

    public WeatherDashboardPage clickDashboardMenuSaveOriginalHandle() {
        click(dashboardTopMenu);

        return new WeatherDashboardPage(getDriver(), getOriginalHandle());
    }

    public HomeSignInPage clickSignInMenu() {
        click(signInTopMenu);

        return new HomeSignInPage(getDriver());
    }

    public HomePage signIn() {
        clickSignInMenu().signInRegularUser();

        return new HomePage(getDriver());
    }

    public HomeSignInPage signOut() {
        click(getDriver().findElement(By.id("user-dropdown")));
        click(getDriver().findElement(By.xpath("//a[@href='/users/sign_out']")));

        return new HomeSignInPage(getDriver());
    }

    public List<String> clickAllMenus() {
        String mainWindow = getDriver().getWindowHandle();
        List<String> urlList = new ArrayList<>();

        for (int i = 0; i < topMenuButtons.size() - 1; i++) {
            click(topMenuButtons.get(i));
            if (getDriver().getWindowHandles().size() > 1) {
                switchToAnotherWindow();
                urlList.add(getCurrentURL());
                getDriver().close();
                getDriver().switchTo().window(mainWindow);
            } else {
                urlList.add(getCurrentURL());
            }
        }

        return urlList;
    }

    public MainPage clickAndClearSearchFieldTopMenu() {
        click(searchFieldTopMenu);
        clear(searchFieldTopMenu);

       return new MainPage(getDriver());
    }

    public MainPage clickHamburgerMenuIcon() {
        click(hamburgerTopMenuIcon);

        return new MainPage(getDriver());
    }

    public HomeAskQuestionPage clickAskQuestionSupportSubmenu() {
        click(askQuestionSupportSubmenu);
        switchToAnotherWindow();

        return new HomeAskQuestionPage(getDriver());
    }
    
    public FindPage inputRomeIntoSearchFieldAndEnter() {
        inputAndEnter(searchFieldTopMenu, "Rome");

        return new FindPage(getDriver());
    }

    /**
     *  Save unique identifier of windows opened
     */
    public TopMenuPage saveOriginalHandle() {
        this.originalHandle = getDriver().getWindowHandle();

        return this;
    }

    public String getOriginalHandle() {

        return originalHandle;
    }

    public TopMenuPage closeOpenedTab() {
        getDriver().getWindowHandles()
                .stream()
                .filter(handle -> !handle.equals(originalHandle))
                .forEach(handle -> {
                    getDriver().switchTo().window(handle);
                    getDriver().close();
                });

        return this;
    }

    public TopMenuPage switchToOriginalTab() {
        if (originalHandle != null) {
            getDriver().switchTo().window(originalHandle);
        }

        return this;
    }

    public boolean isPlaceholderDisplayed() {

        return isDisplayedElement(searchBoxTopMenu);
    }

    public boolean isDisplayedSupportDropdownContainer() {

        return isDisplayedElement(supportTopMenuDropdown);
    }

    public boolean IsDispalyedHamburgerIcon() {

        return isDisplayedElement(hamburgerTopMenuIcon);
    }

    public boolean isDispalyedLogoIcon() {

        return isDisplayedElement(logo);
    }
}
