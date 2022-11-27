package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class NataliaRamanenkaTest extends BaseTest {
    final static By SEARCH_GUIDE_BUTTON = By.xpath(
            "//div[@id = 'desktop-menu']/ul/li/a[@href = '/guide']");
    final static By SEARCH_HEAD_OF_PAGE_GUIDE = By.xpath(
            "//div[@class = 'col-sm-7']/h1");
    final static By ABOUT_US = By.xpath("//a[@href = '/about-us']");
    final static By SEARCH_HEAD_OF_ABOUT_US = By.xpath("//div[@class = 'about-us']//h1");
    final static By ASK_A_QUESTION = By.xpath("//div[@class = 'footer-section not-foldable']" +
            "//a[@href = 'https://home.openweathermap.org/questions']");
    final static By SEARCH_HEAD_OF_ASK_A_QUESTION = By.xpath("//h4[@class ='headline']");
    final static By WIDGET_MAP = By.xpath("//div[@id = 'widget-map']");
    final static By TIME_CONTANER = By.xpath("//a[@class = 'map-info-block']");
    final static By MAP = By.xpath("//div[@id = 'map']");
    final static By OPEN_STREET_MAP = By.xpath("//a[@href = 'https://www.openstreetmap.org/copyright']");
    final static By SEARCH_HEAD_OF_OPEN_STREET_MAP = By.xpath("//div[@id = 'content']//h1");

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
        final String expectedUrl = "https://openweathermap.org/about-us";

        openBaseURL();
        scrollByVisibleElement(ABOUT_US);
        click(ABOUT_US);
        switchToAnotherWindow(getDriver());
        waitElementToBeVisible(SEARCH_HEAD_OF_ABOUT_US);

        Assert.assertEquals(getCurrentURL(),expectedUrl);
    }

    @Test
    public void testAskAQuestionVisibleAndClickable(){
        final String expectedUrl = "https://home.openweathermap.org/questions";

        openBaseURL();
        scrollByVisibleElement(ASK_A_QUESTION);
        click(ASK_A_QUESTION);
        switchToAnotherWindow(getDriver());
        waitElementToBeVisible(SEARCH_HEAD_OF_ASK_A_QUESTION);

        Assert.assertEquals(getCurrentURL(),expectedUrl);
    }

    @Test
    public void testMapOpened_WhenClickOnTimeContaner(){
        final String expectedPartialUrl = "https://openweathermap.org/weathermap?basemap=" +
                "map&cities=true&layer=radar";

        openBaseURL();
        scrollByVisibleElement(WIDGET_MAP);
        click(TIME_CONTANER);
        switchToAnotherWindow(getDriver());
        waitElementToBeVisible(MAP);

        Assert.assertTrue(isContainsTextInUrl(expectedPartialUrl));
    }

    @Test
    public void testOpenStreetMapOpened_WhenClickOnIt(){
        final String expectedUrl = "https://www.openstreetmap.org/copyright";

        openBaseURL();
        click(OPEN_STREET_MAP);
        switchToAnotherWindow(getDriver());
        waitElementToBeVisible(SEARCH_HEAD_OF_OPEN_STREET_MAP);

        Assert.assertEquals(getCurrentURL(),expectedUrl);
    }
}