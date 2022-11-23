import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class IriSamoTest extends BaseTest {

    private final By SEARCH_CITY_FIELD =
            By.xpath("//div[@id = 'weather-widget']"
                    + "//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    private final By ICON_CURRENT_LOCATION =
            By.xpath("//div[@id='weather-widget']"
                    + "//*[@class='icon-current-location']");
    private final By USER_SUBMITTED_DIFFERENT_WEATHER =
            By.xpath("//span[@class='control-el owm-switch']");
    private final By METRIC_UNIT_OF_MEASUREMENT =
            By.xpath("//div[@class='switch-container']"
                    + "//div[contains(text(), 'Metric')]");
    private final By IMPERIAL_UNIT_OF_MEASUREMENT =
            By.xpath("//div[@class='switch-container']"
                    + "//div[contains(text(), 'Imperial')]");
    private final By WEATHER_WIDGET_CURRENT_DATE =
            By.xpath("//div[@id='weather-widget']"
                    + "//span[@class='orange-text']");
    private final By WEATHER_WIDGET_H2_CITY_NAME =
            By.xpath("//div[@class='current-container mobile-padding']//h2");
    private final By WEATHER_WIDGET_H3_HOURLY_FORECAST =
            By.xpath("//div[@id='weather-widget']//div//h3[@class='mobile-padding']");
    private final By WEATHER_WIDGET_H3_8_DAY_FORECAST =
            By.xpath("//div[@id='weather-widget']//div//h3[text()='8-day forecast']");
    private final By WEATHER_WIDGET_GRAPH =
            By.xpath("//div[@id=\"weather-widget\"]//div[@class='chart-container']");
    private final By WEATHER_WIDGET_MAP =
            By.xpath("//div[@id=\"widget-map\"]");

    @Test
    public void test_Start_Weather_AllElementsExistsAndVisible() {
        openBaseURL();

        List<By> weatherWidgetControlContainer = new ArrayList<By>() {{
            add(SEARCH_CITY_FIELD);
            add(SEARCH_BUTTON);
            add(ICON_CURRENT_LOCATION);
            add(USER_SUBMITTED_DIFFERENT_WEATHER);
            add(METRIC_UNIT_OF_MEASUREMENT);
            add(IMPERIAL_UNIT_OF_MEASUREMENT);
        }};

        for (By by : weatherWidgetControlContainer) {
            waitElementToBeClickable(by);
        }

        for (By by : weatherWidgetControlContainer) {
            Assert.assertTrue(isElementEnabled(by));
            Assert.assertTrue(isElementExists(by));
        }
        //----------------------------------------------------------

        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, hh");
        final String expectedDate = formatter.format(currentDate).toLowerCase();

        String actualDate =
                getText(WEATHER_WIDGET_CURRENT_DATE)
                        .toLowerCase().substring(0, 10);

        Assert.assertEquals(actualDate, expectedDate);
        //-------------------------------------------------------------

        List<By> weatherWidgetSectionContent = new ArrayList<By>() {{
            add(WEATHER_WIDGET_H2_CITY_NAME);
            add(WEATHER_WIDGET_H3_HOURLY_FORECAST);
            add(WEATHER_WIDGET_H3_8_DAY_FORECAST);
            add(WEATHER_WIDGET_GRAPH);
            add(WEATHER_WIDGET_MAP);
        }};

        for (int i = 0; i < weatherWidgetSectionContent.size(); i++) {
            Assert.assertTrue(isElementEnabled(weatherWidgetSectionContent.get(i)));
            Assert.assertTrue(isElementExists(weatherWidgetSectionContent.get(i)));
        }
        //-----------------------------------------------------------

        List<WebElement> MapLinkDayWeatherList = (
                getDriver().findElements(
                        By.xpath("//div[@id='weather-widget']"
                                + "//ul[@class='day-list']/* "
                                + "| //a[@href='https://www.openstreetmap.org/copyright']")));

        for (int i = 0; i < MapLinkDayWeatherList.size(); i++) {
            waitElementToBeClickable((
                    By.xpath("//div[@id='weather-widget']"
                            + "//ul[@class='day-list']/* "
                            + "| //a[@href='https://www.openstreetmap.org/copyright']")));
        }

        for (WebElement webElement : MapLinkDayWeatherList) {
            Assert.assertTrue(webElement.isDisplayed());
            Assert.assertTrue(webElement.isEnabled());
        }

        Assert.assertEquals(MapLinkDayWeatherList.size(), 9);
    }
}