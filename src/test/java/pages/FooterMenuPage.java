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

    @FindBy(className = "social")
    private WebElement socialPanelFooterMenu;

    @FindBy(xpath = "//div[@class='social']/a")
    private List<WebElement> socialPanelIconsFooterMenu;

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

    public boolean isSocialPanelDisplayed() {

        return isDisplayedElement(socialPanelFooterMenu);
    }

    public int getSocialPanelSize() {

        return getListSize(socialPanelIconsFooterMenu);
    }
}
