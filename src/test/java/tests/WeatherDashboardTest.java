package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WeatherDashboardPage;

import static org.testng.Assert.assertEquals;
import static pages.HomeDashBoardNewTriggerPage.FAHRENHEIT;

public class WeatherDashboardTest extends BaseTest {

    private final String dashboardWeatherPageURL = "https://openweathermap.org/weather-dashboard";
    private final String eventsPageURL = "https://home.openweathermap.org/dashboard/events?campaign_id=weather_dashboard_website";
    private final String createNewTriggerURL = "https://home.openweathermap.org/dashboard/triggers/create";

    @Test
    public void testDashboardLink() {
        final String expectedResult = "https://openweathermap.org/weather-dashboard";

        WeatherDashboardPage weatherDashboardPage = openBaseURL()
                .scrollToWeatherDashboardFooterMenu()
                .clickWeatherDashboardFooterMenu();

        assertEquals(weatherDashboardPage.getCurrentURL(), expectedResult);
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

    @BeforeMethod
    public void setUp() {
        openBaseURL().signIn();
        String originalHandle = getDriver().getWindowHandle();

        openBaseURL()
                .clickDashboardMenu()
                .clickTryTheDashBoardButton()
                .switchToEventsDashBoardWindow()
                .deleteTriggers();

        for(String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                getDriver().switchTo().window(handle);
                getDriver().close();
            }
        }
        getDriver().switchTo().window(originalHandle);
    }

    @Test
    public void testCreateNewTrigger() {
        // GIVEN
        final String expectedMessage = "Trigger Created";

        // WHEN
        String actualTriggerText = openBaseURL()
                .clickDashboardMenu()
                .clickTryTheDashBoardButton()
                .switchToEventsDashBoardWindow()
                .clickCreateNewTriggerButton()
                .inputNameNewTrigger("Air temperature cold")
                .clickOnLocationField()
                .clickOnCoordinates()
                .setCoordinatesLatitude("61.0932")
                .setCoordinatesLongitude("-149.8457")
                .clickOnSetCoordinates()
                .clickOnSetLocation()
                .setUnits(FAHRENHEIT)
                .clickOnCreateNewTrigger()
                .getTriggerCreatedText();

        // THEN
        assertEquals(actualTriggerText, expectedMessage);
    }
}
