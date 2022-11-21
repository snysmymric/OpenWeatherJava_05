import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class WeraStremedlowskaTest extends BaseTest {
    private final By SUPPORT_DROPDOWN = By.id("support-dropdown");
    private final By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']/li");
    private final By SUBMENU_FAQ = By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/faq']");
    private final By SUBMENU_HOW_TO_START = By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']");
    private final By SUBMENU_ASK_A_QUESTION = By
            .xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']");
    private final static By BREADCRUMB_TITLE = By.className("breadcrumb-title");
    private final By our_technology_link = By.cssSelector("a[href='/technology']");

    @Test
    public void testH1TagText_WhenMenuHowToStartIsClicked() {
        final String expectedResult = "How to start using professional collections";
        final String expectedResultUrl = "https://openweathermap.org/appid";

        openBaseURL();
        click(SUPPORT_DROPDOWN);
        click(SUBMENU_HOW_TO_START);

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
        Assert.assertEquals(getText(BREADCRUMB_TITLE), expectedResult);
    }

    @Test
    public void testOurTechnologyLinkVisibleAndClickable() {
        final String expectedURL = "https://openweathermap.org/technology";
        final String expectedTitle = "Weather model - OpenWeatherMap";

        openBaseURL();
        scrollByVisibleElement(our_technology_link);
        click(our_technology_link);

        Assert.assertEquals(getCurrentURL(), expectedURL);
        Assert.assertEquals(getTitle(), expectedTitle);
    }
}
