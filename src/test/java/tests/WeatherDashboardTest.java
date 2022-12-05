package tests;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WeatherDashboardPage;

public class WeatherDashboardTest extends BaseTest {

    @Test
    public void testDashboardLink() {
        final String expectedResult = "https://openweathermap.org/weather-dashboard";

        WeatherDashboardPage weatherDashboardPage = openBaseURL()
                .scrollToWeatherDashboardFooterMenu()
                .clickWeatherDashboardFooterMenu();

        Assert.assertEquals(weatherDashboardPage.getCurrentURL(), expectedResult);
    }

    @Test
    public void testDashboardLinkWhitReport() {
//        final String expectedLink = "https://openweathermap.org/weather-dashboard";
//
//        WeatherDashboardPage weatherDashboardPage = openBaseURL()
//                .logger_Info("Link before click - " + getCurrentURL())
//                .logger_Info("Title before click - " + getTitle())
//                .scrollByCoordinatesToWeatherDashboardFooterMenu()
//                .clickWeatherDashboardFooterMenu()
//                .logger_Info("Link after click - " + getCurrentURL())
//                .logger_Info("Title after click - " + getTitle());
//        Assert.assertEquals(weatherDashboardPage.getCurrentURL(), expectedLink);
    }
}
