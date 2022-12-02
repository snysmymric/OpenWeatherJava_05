package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class FooterMenuPage extends TopMenuPage {

    @FindBy(css = "a[href='/technology']")
    private WebElement ourTechnologyFooterMenu;

    @FindBy(css = "a[href='https://openweather.co.uk/privacy-policy']")
    private WebElement privacyPolicyFooterMenu;

    @FindBy (xpath="//div[@id='footer-website']//a[@href='/weather-dashboard']")
    private WebElement weatherDashboardFooterMenu;

    public FooterMenuPage(WebDriver driver) {
        super(driver);
    }

    public TechnologyPage clickOurTechnologyFooterMenu() {
        click(ourTechnologyFooterMenu);

        return new TechnologyPage(getDriver());
    }

    public void clickPrivacyPolicyFooterMenu() {

        click(privacyPolicyFooterMenu);
    }

    public WeatherDashboardPage clickWeatherDashboardFooterMenu(){
        click(weatherDashboardFooterMenu);

        return new WeatherDashboardPage(getDriver());
    }

    protected WebElement getOurTechnologyFooterMenu() {

        return ourTechnologyFooterMenu;
    }

    protected WebElement getPrivacyPolicyFooterMenu() {

        return privacyPolicyFooterMenu;
    }
}
