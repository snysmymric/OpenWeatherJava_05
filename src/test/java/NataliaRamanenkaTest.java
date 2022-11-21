import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class NataliaRamanenkaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_GUIDE_BUTTON = By.xpath(
            "//div[@id = 'desktop-menu']/ul/li/a[@href = '/guide']");
    final static By SEARCH_HEAD_OF_PAGE_GUIDE = By.xpath(
            "//div[@class = 'col-sm-7']/h1");
    final static By ABOUT_US = By.xpath("//a[@href = '/about-us']");

    final static By SEARCH_HEAD_OF_ABOUT_US = By.xpath("//div[@class = 'about-us']//h1");

    @Test
    public void testGuideLinkAndTitle()  {
        final String expectedUrl = "https://openweathermap.org/guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();

        click(SEARCH_GUIDE_BUTTON);
        waitElementToBeVisible(SEARCH_HEAD_OF_PAGE_GUIDE);

        Assert.assertEquals(getCurrentURL(), expectedUrl);
        Assert.assertEquals(getTitle(), expectedTitle);
    }
    @Test
    public void testAboutUsVisibleAndClickable(){
        String expectedUrl = "https://openweathermap.org/about-us";

        openBaseURL();

        scrollByVisibleElement(ABOUT_US);
        click(ABOUT_US);

        switchToAnotherWindow(getDriver());
        waitElementToBeVisible(SEARCH_HEAD_OF_ABOUT_US);

        Assert.assertEquals(getCurrentURL(),expectedUrl);
    }

}