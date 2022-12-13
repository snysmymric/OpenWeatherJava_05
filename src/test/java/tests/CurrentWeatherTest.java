package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CurrentWeatherPage;

public class CurrentWeatherTest extends BaseTest {

    @Test
    public void testCorrectAPICallCodeIsDisplayed() {
        String expectedAPICallCode = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}";

        CurrentWeatherPage currentWeatherPage = openBaseURL()
                .scrollByOrangeBackground()
                .clickCurrentWeatherIcon();

        String actualAPICallCode = currentWeatherPage.getAPICallCode();

        Assert.assertTrue(currentWeatherPage.isAPICallCodeDisplayed());
        Assert.assertEquals(actualAPICallCode, expectedAPICallCode);
    }
}
