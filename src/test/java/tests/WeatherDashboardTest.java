package tests;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WeatherDashboardPage;

public class WeatherDashboardTest extends BaseTest {

    @Test
    public void testDashboardLink() {
        final String expectedResult = "https://openweathermap.org/weather-dashboard";

        openBaseURL_ReturnMainPage();
        new WeatherDashboardPage(getDriver())
               .scrollToDashboardLink()
               .clickDashboardMenu();

        Assert.assertEquals(getCurrentURL(), expectedResult);
    }
}
