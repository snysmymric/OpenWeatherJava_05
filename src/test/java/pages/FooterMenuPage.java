package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class FooterMenuPage extends TopMenuPage {

    @FindBy (xpath="//div[@id='footer-website']//a[@href='/weather-dashboard']")
    private WebElement weatherDashboardFooterMenu;

    @FindBy(css = "a[href='/technology']")
    private WebElement ourTechnologyFooterMenu;

    @FindBy(css = "a[href='https://openweather.co.uk/privacy-policy']")
    private WebElement privacyPolicyFooterMenu;

    @FindBy(xpath = "//a[@href = '/about-us']")
    private WebElement aboutUsFooterMenu;

    @FindBy (xpath = "//div[@class='section-content']/ul/li/a[@href='/widgets-constructor']")
    private WebElement widgetsFooterMenu;

    @FindBy (xpath="//a[@href='https://apps.apple.com/gb/app/openweather/id1535923697'] [@target='_blank']")
    private WebElement downloadOnTheAppStoreLink;

    @FindBy(className = "social")
    private WebElement socialPanelFooterMenu;

    @FindBy(xpath = "//div[@class='social']/a")
    private List<WebElement> socialPanelIconsFooterMenu;

    @FindBy(xpath = "//img[@src='/themes/openweathermap/assets/img/owm_icons/icon_github.png']")
    private WebElement githubIconFooterMenu;

    @FindBy(xpath = "//a[@href='https://github.com/search?q=openweathermap&ref=cmdform']")
    private WebElement githubIconLinkFootermenu;

    @FindBy(xpath = "//div[@id='footer-website']//a[@href='/stations']")
    private WebElement connectYourWeatherStationFooterMenu;

    @FindBy(xpath = ".//p[text()='Download OpenWeather app']")
    private WebElement textDownloadOpenWeatherApp;

    public FooterMenuPage(WebDriver driver) {
        super(driver);
    }

    public WeatherDashboardPage clickWeatherDashboardFooterMenu(){
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

    protected WebElement getDownloadOnTheAppStoreLink() {

        return downloadOnTheAppStoreLink;
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
        click20(githubIconLinkFootermenu);

        return  new MainPage(getDriver());
    }

    public MainPage switchToGithubWebsite() {
        switchToAnotherWindow();

        return new MainPage(getDriver());
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
}
