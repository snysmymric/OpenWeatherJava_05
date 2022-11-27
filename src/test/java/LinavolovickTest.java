import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinavolovickTest extends BaseTest {
    private final String WEATHER_API_URL = "https://openweathermap.org/api#current";
    private final String WEATHER_API_URL2 = "https://openweathermap.org/api";
    private final By LOGO = By.xpath("//li[@class='logo']/descendant::img");
    private final By CURRENT_AND_FORECAST_APIS_LINK = By.xpath("//div[@id='footer-website']" +
            "//a[@href='/api#current']");
    private final By NAV_GUIDE = By.xpath("//div[@id='desktop-menu']//a[@href='/guide']");
    private final By GUIDE_APIS_LINK = By.xpath("//p[starts-with(text(),'Our weather products')]//a[@href='/api']");
    final static By SIGN_IN_LINK = By.xpath("//li[@class='user-li']/a[text()='Sign in']");
    final static By CREATE_AN_ACCOUNT = By.xpath("//a[@href='/users/sign_up']");
    final static By USERNAME = By.xpath("//input[@id='user_username']");
    final static By ENTER_EMAIL = By.xpath("//input[@id='user_email']");
    final static By PASSWORD = By.xpath("//input[@id='user_password']");
    final static By REPEAT_PASSWORD = By.xpath("//input[@id='user_password_confirmation']");
    final static By CHECKBOX_AGE_CONFIRM = By.xpath("//input[@id='agreement_is_age_confirmed']");
    final static By CHECKBOX_ACCEPT = By.xpath("//input[@id='agreement_is_accepted']");
    final static By CHECKBOX_MAILING_SYSTEM = By.xpath("//input[@id='mailing_system']");
    final static By CHECKBOX_MAILING_PRODUCT = By.xpath("//input[@id='mailing_product']");
    final static By CHECKBOX_MAILING_NEWS = By.xpath("//input[@id='mailing_news']");
    final static By CREATE_ACCOUNT_BUTTON = By.xpath("//input[@value='Create Account']");
    final static By CAPTCHA_UNCHECKED_ERROR_TEXT = By.xpath("//div[@class='has-error']/div[@class='help-block']");


    @Test
    public void testVerifyLogoExistsAndRedirectsToStartPage() {
        final String expectedResult = BASE_URL;

        openBaseURL();
        click(LOGO);

        Assert.assertEquals(getCurrentURL(), expectedResult);
    }

    @Test
    public void testCurrentAndForecastAPIsLink() {
        final String expectedResult = WEATHER_API_URL;

        openBaseURL();
        scrollToPageBottom();
        click(CURRENT_AND_FORECAST_APIS_LINK);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testGuideAPIsLink() {
        final String expectedAPIsLink = WEATHER_API_URL2;

        openBaseURL();
        click(NAV_GUIDE);
        click(GUIDE_APIS_LINK);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedAPIsLink);
    }

    @Test
    public void testCreateNewAccountWithoutCaptchaError() {
        String username = "foma123";
        String email = "foma123@gmail.com";
        String password = "Foma123foma";

        openBaseURL();
        click(SIGN_IN_LINK);
        click(CREATE_AN_ACCOUNT);
        click(USERNAME);
        input(username, USERNAME);
        click(ENTER_EMAIL);
        input(email, ENTER_EMAIL);
        click(PASSWORD);
        input(password, PASSWORD);
        click(REPEAT_PASSWORD);
        input(password, REPEAT_PASSWORD);
        click(CHECKBOX_AGE_CONFIRM);
        click(CHECKBOX_ACCEPT);
        click(CHECKBOX_MAILING_SYSTEM);
        click(CHECKBOX_MAILING_PRODUCT);
        click(CHECKBOX_MAILING_NEWS);
        click(CREATE_ACCOUNT_BUTTON);

        String expectedCaptchaErrorText = getText(CAPTCHA_UNCHECKED_ERROR_TEXT);
        String actualResult = getText(CAPTCHA_UNCHECKED_ERROR_TEXT);

        Assert.assertEquals(actualResult, expectedCaptchaErrorText);
    }
}