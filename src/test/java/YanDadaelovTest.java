import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class YanDadaelovTest extends BaseTest {

    private final By NAVIGATION_BAR_CONTAINER = By.xpath("//nav[@id='nav-website']");
    private final By NAVIGATION_BAR_ITEMS = By.xpath("//ul[@id='first-level-nav']");

    @Test
    public void testStartPageUrl() {
        openBaseURL();
        final String actual_URL = getCurrentURL();

        Assert.assertEquals(actual_URL, BASE_URL);
    }

    @Test
    public void testStartPageTitleText() {
        final String startPageTitle = "Сurrent weather and forecast - OpenWeatherMap";

        openBaseURL();
        final String actual_titleText = getTitle();

        Assert.assertEquals(actual_titleText, startPageTitle);
    }

    @Test
    public void testNavigationBarIsDisplayedOnMainPage() {
        openBaseURL();

        Assert.assertTrue(isDisplayed(NAVIGATION_BAR_CONTAINER)
                && isDisplayed(NAVIGATION_BAR_ITEMS));
    }
}
