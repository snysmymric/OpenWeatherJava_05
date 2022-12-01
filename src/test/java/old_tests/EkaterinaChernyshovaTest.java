package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class EkaterinaChernyshovaTest extends BaseTest {

    final static By H2_CITY_NAME_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By GUIDE_MENU = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");
    final static By IMPERIAL_TEMPERATURE_BUTTON = By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']");
    final static By METRIC_TEMPERATURE_BUTTON = By.xpath("//div[@id = 'weather-widget']//div[text() = 'Metric: °C, m/s']");
    final static By SYMBOL_C = By.xpath("//span[@class = 'heading']");
    final static By GREEN_NOTIFICATION_BOTTON = By.xpath("//div[@class = 'widget-notification']");


    @Test
    public void testH2TagText_WhenSearchingCityCountry() {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_NAME_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_NAME_HEADER, oldH2Header);

        String actualResult = getText(H2_CITY_NAME_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }


    @Test
    public void testLinkAndTitleOnThePage_OpenWeatherMap() {

        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        click(GUIDE_MENU);

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }


    @Test
    public void testIfTemperatureShowsInCelsius() {

        String expectedResult = "°C";

        openBaseURL();
        click(IMPERIAL_TEMPERATURE_BUTTON);
        waitForGrayContainerDisappeared();
        click(METRIC_TEMPERATURE_BUTTON);
        String actualResult = getText(SYMBOL_C).substring(getText(SYMBOL_C).length()-2);

        Assert.assertEquals(actualResult, expectedResult);
    }


    @Test
    public void test_X_buttonColorGreen() {

        String wrongCityName = "fgh";
        String expectedNotificationBoxColor = "rgba(120, 203, 191, 0.8)";

        openBaseURL();
        click(SEARCH_CITY_FIELD);
        input(wrongCityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(GREEN_NOTIFICATION_BOTTON);

        String actualColorOfNotificationBox = backgroundColor(GREEN_NOTIFICATION_BOTTON);

        Assert.assertEquals(actualColorOfNotificationBox, expectedNotificationBoxColor);
    }
}