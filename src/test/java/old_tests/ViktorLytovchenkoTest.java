package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class ViktorLytovchenkoTest extends BaseTest {

    private final By WEATHER_IN_YOUR_CITY_FIELD = By.xpath("//div[@id = 'desktop-menu']/form/input[@type = 'text']");
    private final By CITY_IN_VALUE = By.xpath("//input[@id='search_str']");
    private final By DESKTOP_MENU_ITEM = By.xpath("//div[@id='desktop-menu']//a[text()='Dashboard']");

    @Test
    public void testTypeInSearchBarRome() {
        final String cityName = "Rome";
        final String expectedResult = "https://openweathermap.org/find?q=Rome";
        final String expectedResult1 = "Rome";

        openBaseURL();

        click(WEATHER_IN_YOUR_CITY_FIELD);
        inputAndEnter(WEATHER_IN_YOUR_CITY_FIELD, cityName);

        String actualResult = getCurrentURL();
        String actualResult1 = getTextByAttribute(CITY_IN_VALUE, "value");

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult1, expectedResult1);
    }

    @Test
    public void testOpenInMenuDashboard() {
        final String expectedResult = "https://openweathermap.org/weather-dashboard";

        openBaseURL();

        click(DESKTOP_MENU_ITEM);

        String actualResult = getCurrentURL();

        Assert.assertEquals(actualResult, expectedResult);
    }
}


