import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class ViacheslavAnisimovTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static String EMAIL = "jka59433@xcoxc.com";
    final static String PASSWORD = "Tester12#";
    final static String SYMBOL_FAHRENHEIT = "°F";
    final static By HAMBURGER_MENU = By.id("hamburger");
    final static By LINK_TO_GUIDE_IN_HAMBURGER_MENU = By.xpath("//ul[@id='mobile-menu']//a[@href='/guide']");
    final static By LINK_TO_MARKETPLACE = By.linkText("Marketplace");
    final static By LINK_TO_HISTORY_WEATHER_DATA = By.xpath("//div[@id='footer-website']//a[@href='/api#history']");
    final static By LINK_SIGN_IN = By.xpath("//div[@id='desktop-menu']//li/a[@href='https://openweathermap.org/home/sign_in']");
    final static By ENTER_EMAIL_FIELD = By.xpath("//input[@id='user_email']");
    final static By PASSWORD_FIELD = By.xpath(("//input[@id='user_password']"));
    final static By SUBMIT_BUTTON = By.xpath("//input[@name='commit']");
    final static By SUCCESSFULL_LOGIN_TEXT = By.xpath("//div[@class='panel-body']");
    final static By FAHRENHEIT = By.xpath("//div[@class='option'][contains(text(), 'Imperial: °F, mph')]");
    final static By TEMP_TEXT_IN_HEADING =By.xpath("//span[@class='heading'][contains(text(), '°F')]");

    @Test
    public void testHamburgerMenuAndGuidePageTitle() {

        final String expectedResult1 = "https://openweathermap.org/guide";
        final String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().manage().window().setSize(new Dimension(1024, 970));

        openBaseURL();

        click(HAMBURGER_MENU);
        click(LINK_TO_GUIDE_IN_HAMBURGER_MENU);

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testMarketplaceMenuIsClickableOpenCustomWeatherProducts() {
        final String expectedResult1 = "https://home.openweathermap.org/marketplace";
        final String expectedResult2 = "Custom Weather Products";

        openBaseURL();

        click(LINK_TO_MARKETPLACE);
        jumpToNextWindow();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

    }

    @Test
    public void testLinkHistoricalWeatherData() {
        String expectedResult = "https://openweathermap.org/api#history";

        openBaseURL();

        scrollToPageBottom();

        click(LINK_TO_HISTORY_WEATHER_DATA);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSignInToYourAccount_HappyPath() {
        String expectedResult = "Signed in successfully.";

        openBaseURL();

        click(LINK_SIGN_IN);

        input(EMAIL, ENTER_EMAIL_FIELD);
        input(PASSWORD, PASSWORD_FIELD);
        click(SUBMIT_BUTTON);

        String actualResult = getDriver().findElement(SUCCESSFULL_LOGIN_TEXT).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testChangeTempFahrenheitInHeadingAfterClickButtonF() {

        openBaseURL();

        click(FAHRENHEIT);
        waitForGrayContainerDisappeared();

        Assert.assertTrue(getText(TEMP_TEXT_IN_HEADING).contains(SYMBOL_FAHRENHEIT));
    }
}
