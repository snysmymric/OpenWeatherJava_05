package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public abstract class FooterMenuPage extends TopMenuPage {

    @FindBy(xpath = "//div[@id='footer-website']//a[@href='/weather-dashboard']")
    private WebElement weatherDashboardFooterMenu;

    @FindBy(css = "a[href='/technology']")
    private WebElement ourTechnologyFooterMenu;

    @FindBy(css = "a[href='https://openweather.co.uk/privacy-policy']")
    private WebElement privacyPolicyFooterMenu;

    @FindBy(xpath = "//a[@href = '/about-us']")
    private WebElement aboutUsFooterMenu;

    @FindBy(xpath = "//div[@class='section-content']/ul/li/a[@href='/widgets-constructor']")
    private WebElement widgetsFooterMenu;

    @FindBy(xpath = "//a[@href='https://apps.apple.com/gb/app/openweather/id1535923697'] [@target='_blank']")
    private WebElement downloadOnTheAppStoreLinkFooterMenu;

    @FindBy(className = "social")
    private WebElement socialPanelFooterMenu;

    @FindBy(xpath = "//div[@class='social']/a")
    private List<WebElement> socialPanelIconsFooterMenu;

    @FindBy(xpath = "//img[@src='/themes/openweathermap/assets/img/owm_icons/icon_github.png']")
    private WebElement githubIconFooterMenu;

    @FindBy(xpath = "//a[@href='https://github.com/search?q=openweathermap&ref=cmdform']")
    private WebElement githubIconLinkFooterMenu;

    @FindBy(xpath = "//div[@id='footer-website']//a[@href='/stations']")
    private WebElement connectYourWeatherStationFooterMenu;

    @FindBy(xpath = ".//p[text()='Download OpenWeather app']")
    private WebElement textDownloadOpenWeatherApp;

    @FindBy(xpath = "//div[@id = 'footer-website']//a[@href = 'https://home.openweathermap.org/questions']")
    private WebElement askQuestionFooterMenu;

    @FindBy(xpath = "//div[@class='section-content']//a[@href='/price']")
    private WebElement pricingFooterMenu;

    @FindBy(xpath = "//div[@style='display: flex; flex-direction: row;']/a")
    private List<WebElement> storeIcons;

    @FindBy(xpath = "//img[@alt='Get it on Google Play']")
    private WebElement iconGooglePlay;

    @FindBy(xpath = "//div[@class='horizontal-section my-5']/div/span[2]")
    private WebElement copirightSign;

    @FindBy(xpath = "//div[@class = 'social']//img[contains(@src, 'facebook')]")
    private WebElement iconFacebook;

    public FooterMenuPage(WebDriver driver) {
        super(driver);
    }

    public WeatherDashboardPage clickWeatherDashboardFooterMenu() {
        click(weatherDashboardFooterMenu);

        return new WeatherDashboardPage(getDriver());
    }

    public TechnologyPage clickOurTechnologyFooterMenu() {
        click(ourTechnologyFooterMenu);

        return new TechnologyPage(getDriver());
    }

    public void clickPrivacyPolicyFooterMenu() {

        click(privacyPolicyFooterMenu);
    }

    protected WebElement getOurTechnologyFooterMenu() {

        return ourTechnologyFooterMenu;
    }

    protected WebElement getPrivacyPolicyFooterMenu() {

        return privacyPolicyFooterMenu;
    }

    protected WebElement getAboutUsFooterMenu() {

        return aboutUsFooterMenu;
    }

    public AboutUsPage clickOnAboutUsFooterMenu() {
        click20(aboutUsFooterMenu);

        return new AboutUsPage(getDriver());
    }

    protected WebElement getWidgetFooterMenu() {

        return widgetsFooterMenu;
    }

    public WidgetsPage clickWidgetsPageFooterMenu() {
        click(widgetsFooterMenu);

        return new WidgetsPage(getDriver());
    }

    public WebElement getDownloadOnTheAppStoreLinkFooterMenu() {

        return downloadOnTheAppStoreLinkFooterMenu;
    }

    public boolean isSocialPanelDisplayed() {

        return isDisplayedElement(socialPanelFooterMenu);
    }

    public int getSocialPanelSize() {

        return getListSize(socialPanelIconsFooterMenu);
    }

    public WebElement getGithubIconFooterMenu() {

        return githubIconFooterMenu;
    }

    public boolean isGithubIconDisplayed() {

        return isDisplayedElement(githubIconFooterMenu);
    }

    public MainPage clickToGithubIcon() {
        click20(githubIconLinkFooterMenu);

        return new MainPage(getDriver());
    }

    public void switchToGithubWebsite() {
        switchToAnotherWindow();
    }

    public WeatherStationsPage clickConnectYourWeatherStationFooterMenu() {
        click(connectYourWeatherStationFooterMenu);

        return new WeatherStationsPage(getDriver());
    }

    public String getTextDownloadOpenWeatherApp() {

        return getText(textDownloadOpenWeatherApp);
    }

    public boolean textDownloadOpenWeatherAppIsDisplayed() {

        return isDisplayedElement(textDownloadOpenWeatherApp);
    }

    public MainPage clickDownloadOnTheAppStoreLinkFooterMenu() {
        click20(downloadOnTheAppStoreLinkFooterMenu);

        return new MainPage(getDriver());
    }

    public void switchToAppStorePage() {
        switchToAnotherWindow();
    }

    protected WebElement getAskQuestionFooterMenu() {

        return askQuestionFooterMenu;
    }

    public FooterMenuPage clickOnAskQuestionFooterMenu() {
        wait10ElementToBeClickable(askQuestionFooterMenu);
        click20(askQuestionFooterMenu);

        return this;
    }

    public HomeAskQuestionPage switchToHomeAskQuestionPage() {
        switchToAnotherWindow();

        return new HomeAskQuestionPage(getDriver());
    }

    public PricePage clickPricingFooterMenu() {
        click20(pricingFooterMenu);

        return new PricePage(getDriver());
    }

    public int getStoreIconsSize() {

        return getListSize(storeIcons);
    }

    public String getCopyrightSign(){

        return getText(copirightSign);
    }

    public WebElement getDownloadGooglePlayLinkFooterMenu() {

        return iconGooglePlay;
    }

    public MainPage clickDownloadGooglePlayLinkFooterMenu() {
        click20(iconGooglePlay);

        return new MainPage(getDriver());
    }

    public MainPage clickFacebookIcon(){
        click20(iconFacebook);

        return new MainPage(getDriver());
    }

    public void switchToFacebookWebsite() {
        switchToAnotherWindow();
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public boolean clickAllIconsAndValidateDomains() {
        String mainWindow = getDriver().getWindowHandle();
        List<String> currentURLs = new ArrayList<>();

        for (int i = 0; i < socialPanelIconsFooterMenu.size(); i++) {
            click(socialPanelIconsFooterMenu.get(i));
            if (getDriver().getWindowHandles().size() > 1) {
                switchToAnotherWindow();
                currentURLs.add(getCurrentURL());
                getDriver().close();
                getDriver().switchTo().window(mainWindow);
            } else {
                currentURLs.add(getCurrentURL());
            }
        }

        final List<String> expectedDomains = List.of("www.facebook.com", "twitter.com", "www.linkedin.com", "medium.com",
                "t.me", "github.com");

        return isListContains(expectedDomains, currentURLs);
    }
}
