import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class MurzinovaTest extends BaseTest {
    private final By GUIDE_LINK = By.xpath("//div[@id='desktop-menu']//a[@href='/guide']");
    private final By SIGN_IN_LINK = By.xpath(
            "//div[@id='desktop-menu']//a[@href='https://openweathermap.org/home/sign_in']");
    private final By CREATE_AN_ACCOUNT_LINK = By.xpath(
            "//p/a[@href='/users/sign_up'][text()='Create an Account.']");
    private final By CLICK_HERE_TO_RECOVER_LINK = By.xpath("//a[@href='#'][text()='Click here to recover.']");
    private final By RECOVERING_PASSWORD_FORM = By.xpath("//form[@class='simple_form form-inline']");

    @Test
    public void testRedirectingToAPIGuidePage() {
        final String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        final String expectedResultURL = "https://openweathermap.org/guide";

        openBaseURL();
        click(GUIDE_LINK);

        String actualResultTitle = getTitle();
        String actualResultURL = getCurrentURL();

        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertEquals(actualResultURL, expectedResultURL);
    }

    @Test
    public void testSignInLinkIsClickable() {
        final String expectedResult = "https://home.openweathermap.org/users/sign_in";

        openBaseURL();
        click(SIGN_IN_LINK);

        String actualResult = getCurrentURL();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCreateAnAccountLinkIsClickableRedirectionToCreateAnAccountPage() {
        final String expectedResult = "https://home.openweathermap.org/users/sign_up";

        openBaseURL();
        click20(SIGN_IN_LINK);
        click20(CREATE_AN_ACCOUNT_LINK);

        String actualResult = getCurrentURL();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testClickHereToRecoverLinkIsClickable() {
        openBaseURL();
        click20(SIGN_IN_LINK);
        click20(CLICK_HERE_TO_RECOVER_LINK);

        Assert.assertTrue(isDisplayed(RECOVERING_PASSWORD_FORM));
    }
}