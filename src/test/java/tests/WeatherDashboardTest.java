package tests;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WeatherDashboardPage;

public class WeatherDashboardTest extends BaseTest {

    @Test
    public void testDashboardLink() {
        final String expectedResult = "https://openweathermap.org/weather-dashboard";

        WeatherDashboardPage weatherDashboardPage = openBaseURL_ReturnMainPage()
                .scrollToWeatherDashboardFooterMenu()
                .clickWeatherDashboardFooterMenu();

        Assert.assertEquals(weatherDashboardPage.getCurrentURL(), expectedResult);
    }

    @Test
    public void testDashboardLinkWhitRapport() {

//        WeatherDashboardPage weatherDashboardPage = openBaseURL_ReturnMainPage()
//                .logger_Info("Link before click - " + getCurrentURL())
//                .logger_Info("Title before click - " + getTitle())
//                .scrollToDashboardLink()
//                .clickDashboardMenu()
//                .logger_Info("Link after click - " + getCurrentURL())
//                .logger_Info("Title after click - " + getTitle());
    }
}
