package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class WeraStremedlowskaTest extends BaseTest {
    private final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";

    private final By SUPPORT_DROPDOWN = By.id("support-dropdown");
    private final By SUBMENU_HOW_TO_START = By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']");
    private final By ourTechnologyLink = By.cssSelector("a[href='/technology']");
    private final By privacyPolicy = By.cssSelector("a[href='https://openweather.co.uk/privacy-policy']");

    @Test
    public void testHowToStartMenuNavigatesToHowToStartPage() {
        final String expectedTitle = "How to start to work with Openweather API - OpenWeatherMap";
        final String expectedUrl = "https://openweathermap.org/appid";

        openBaseURL();
        click(SUPPORT_DROPDOWN);
        click(SUBMENU_HOW_TO_START);
        waitURLToBeChanged(BASE_URL);

        Assert.assertNotEquals(basePageTitle, getTitle());
        Assert.assertEquals(getCurrentURL(), expectedUrl);
        Assert.assertEquals(getTitle(), expectedTitle);
    }

    @Test
    public void testOurTechnologyLinkNavigatesToTechnologyPage() {
        final String expectedURL = "https://openweathermap.org/technology";
        final String expectedTitle = "Weather model - OpenWeatherMap";

        openBaseURL();
        scrollByVisibleElement(ourTechnologyLink);
        click(ourTechnologyLink);
        waitURLToBeChanged(BASE_URL);

        Assert.assertNotEquals(basePageTitle, getTitle());
        Assert.assertEquals(getCurrentURL(), expectedURL);
        Assert.assertEquals(getTitle(), expectedTitle);
    }

    @Test
    public void testPrivacyPolicyLinkNavigatesToPrivacyPolicyPage() {
        final String expectedURL = "https://openweather.co.uk/privacy-policy";
        final String expectedTitle = "Privacy policy - OpenWeatherMap";

        openBaseURL();
        scrollByVisibleElement(privacyPolicy);
        click(privacyPolicy);
        switchWindow();
        waitURLToBeChanged(BASE_URL);

        Assert.assertNotEquals(basePageTitle, getTitle());
        Assert.assertEquals(getCurrentURL(), expectedURL);
        Assert.assertEquals(getTitle(), expectedTitle);
    }
}
