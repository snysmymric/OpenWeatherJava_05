package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class Lenochkanik80Test extends BaseTest {
    private final By CONNECT_YOUR_WEATHER_STATION = By.xpath("//div[@onclick='toggleFooterSection(event)']//a[@href='/stations']");

    @Test
    public void testConnectYourWeatherStation_FooterTechnologies() {
        final String expectedResultURL = "https://openweathermap.org/stations";
        final String expectedResultTitle = "Weather Stations - OpenWeatherMap";

        openBaseURL();
        scrollByVisibleElement(CONNECT_YOUR_WEATHER_STATION);
        click(CONNECT_YOUR_WEATHER_STATION);

        String actualResultURL = getCurrentURL();
        String actualResultTitle = getTitle();

        Assert.assertEquals(actualResultURL, expectedResultURL);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }
}
