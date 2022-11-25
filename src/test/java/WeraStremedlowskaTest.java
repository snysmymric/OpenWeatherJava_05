import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class WeraStremedlowskaTest extends BaseTest {
    private final String base_page_title = "Сurrent weather and forecast - OpenWeatherMap";

    private final By SUPPORT_DROPDOWN = By.id("support-dropdown");
    private final By SUBMENU_HOW_TO_START = By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']");
    private final By our_technology_link = By.cssSelector("a[href='/technology']");
    private final By privacy_policy = By.cssSelector("a[href='https://openweather.co.uk/privacy-policy']");

    @Test
    public void testH1TagText_WhenMenuHowToStartIsClicked() {
        final String expectedTitle = "How to start to work with Openweather API - OpenWeatherMap";
        final String expectedResultUrl = "https://openweathermap.org/appid";

        openBaseURL();
        click(SUPPORT_DROPDOWN);
        click(SUBMENU_HOW_TO_START);
        waitURLToBeChanged(BASE_URL);

        Assert.assertNotEquals(base_page_title, getTitle());
        Assert.assertEquals(getCurrentURL(), expectedResultUrl);
        Assert.assertEquals(getTitle(), expectedTitle);
    }

    @Test
    public void testOurTechnologyLinkVisibleAndClickable() {
        final String expectedURL = "https://openweathermap.org/technology";
        final String expectedTitle = "Weather model - OpenWeatherMap";

        openBaseURL();
        scrollByVisibleElement(our_technology_link);
        click(our_technology_link);
        waitURLToBeChanged(BASE_URL);

        Assert.assertNotEquals(base_page_title, getTitle());
        Assert.assertEquals(getCurrentURL(), expectedURL);
        Assert.assertEquals(getTitle(), expectedTitle);
    }

    @Test
    public void testPrivacyPolicyLinkVisibleAndClickable() {
        final String expectedURL = "https://openweather.co.uk/privacy-policy";
        final String expectedTitle = "Privacy policy - OpenWeatherMap";

        openBaseURL();
        scrollByVisibleElement(privacy_policy);
        click(privacy_policy);
        switchWindow();

        Assert.assertEquals(getCurrentURL(), expectedURL);
        Assert.assertEquals(getTitle(), expectedTitle);
    }
}
