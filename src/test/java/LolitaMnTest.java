import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class LolitaMnTest extends BaseTest {

    final static String URL = "https://openweathermap.org/";

    final static By FOOTER_PANEL_CONTAINER = By.xpath("//div[@class='stick-footer-panel__container']");
    final static By BOTTOM_PANEL = By.xpath("//div[@class='stick-footer-panel__container']");
    final static By TEXT_PANEL =  By.xpath("//p[@class='stick-footer-panel__description']");
    final static By ALLOW_ALL_BUTTON = By.xpath("//button[@type='button' and text()='Allow all']");
    final static By MANAGE_BUTTON = By.xpath("//a[@href='/cookies-settings' and text()=' Manage cookies ']");
    final static By SIGN_IN_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//a[@href='https://openweathermap.org/home/sign_in']");
    final static By SIGN_IN_FORM = By.className("sign-form");
    final static By SIGN_IN_EMAIL_FIELD = By.id("user_email");
    final static By SIGN_IN_PASSWORD_FIELD = By.id("user_password");
    final static By SIGN_IN_SUBMIT_BUTTON = By.xpath("//input[@value='Submit' and @name='commit']");
    final static By SUCCESS_SIGN_IN_MESSAGE = By.className("panel-body");


    @Test
    public void testCheckTextInTheBottomOfThePage() {
        final String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential "
                + "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies"
                + " or manage them individually.";


        openBaseURL();

        waitElementToBeVisible(FOOTER_PANEL_CONTAINER);
        waitElementToBeVisible(BOTTOM_PANEL);
        waitElementToBeVisible(ALLOW_ALL_BUTTON);
        waitElementToBeVisible(MANAGE_BUTTON);

        String actualResultTextPanel = getText(TEXT_PANEL);

        Assert.assertEquals(actualResultTextPanel, expectedResult);
    }

    @Test
    public void testSignInButtonNavigateToLogInPage() {
        final String expectedResultUrl = "https://home.openweathermap.org/users/sign_in";

        openBaseURL();

        String oldCurrentUrl = getCurrentURL();

        click(SIGN_IN_BUTTON);
        waitElementToBeVisible(SIGN_IN_FORM);

        String actualResultUrl = getCurrentURL();

        Assert.assertNotEquals(oldCurrentUrl,actualResultUrl);
        Assert.assertEquals(expectedResultUrl,actualResultUrl);
    }

    @Test
    public void testUserIsSignedInSuccessfully() {
        final String expectedResultUrl = "https://home.openweathermap.org/";
        final String expectedResultText = "Signed in successfully.";
        final String userEmail = "jka59433@xcoxc.com";
        final String userPassword = "Tester12#";

        openBaseURL();

        click(SIGN_IN_BUTTON);

        String currentUrl = getCurrentURL();

        waitElementToBeVisible(SIGN_IN_FORM);
        click(SIGN_IN_EMAIL_FIELD);
        input(userEmail,SIGN_IN_EMAIL_FIELD);
        click(SIGN_IN_PASSWORD_FIELD);
        input(userPassword, SIGN_IN_PASSWORD_FIELD);
        click(SIGN_IN_SUBMIT_BUTTON);

        String actualResultNewCurrentUrl = getCurrentURL();
        String actualResultMessage = getText(SUCCESS_SIGN_IN_MESSAGE);

        Assert.assertNotEquals(currentUrl, actualResultNewCurrentUrl);
        Assert.assertEquals(expectedResultUrl,actualResultNewCurrentUrl);
        Assert.assertEquals(expectedResultText, actualResultMessage);
    }

}
