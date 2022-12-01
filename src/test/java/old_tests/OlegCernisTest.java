package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class OlegCernisTest extends BaseTest {

    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder= 'Search city']");
    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By MOLDOVA_MD_CHOICE_IN_DROPDOWN_MENU =
            By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Chisinau, MD ']");
    private final By FAHRENHEIT = By.xpath("//div[text()= 'Imperial: °F, mph']");
    private final By METRIC = By.xpath("//div[text()= 'Metric: °C, m/s']");
    private final By CONFIRM_TEMP = By.xpath("//div[@class = 'current-temp']/span");
    private final By DASHBOARD_LINK =
            By.xpath("//div[@class='footer-section']//a[@href ='/weather-dashboard']");

    @Test
    public void testVerifyThatLocationIsChangedInCurrentWeatherBlockAfterInputNewCity() {
        final String newCity = "Chisinau, MD ";
        final String expectedCity = "Chisinau, MD ";

        openBaseURL();

        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(newCity, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(MOLDOVA_MD_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        Assert.assertEquals(expectedCity, newCity);
    }

    @Test
    public void testVerifyChangingTempUnitInHeading_WhenSwitchTempUnitButton() {
        openBaseURL();
        click(FAHRENHEIT);

        String actualResult = getText(CONFIRM_TEMP);
        Assert.assertTrue(actualResult.contains("°F"));
    }

    @Test
    public void testVerifyMetricSymbolIsShownInCurrentTempWhenChangingUnitToMetric() {
        openBaseURL();
        click(METRIC);

        String actualResult2 = getText(CONFIRM_TEMP);
        Assert.assertTrue(actualResult2.contains("°C"));
    }

    @Test
    public void testVerifyIsLinkDashboardClickable() {
        final String expectedLink = "https://openweathermap.org/weather-dashboard";
        openBaseURL();

        scrollByVisibleElement(DASHBOARD_LINK);
        click(DASHBOARD_LINK);

        String actualLink = getDriver().getCurrentUrl();
        Assert.assertEquals(actualLink, expectedLink);
    }

    @Test
    public void testIsLinkDashboardClickable() {
        openBaseURL();
        logger_Info("Link before click - " + getCurrentURL());
        logger_Info("Title before click - " + getTitle());

        scrollByPixels("0", "5200");
        click(DASHBOARD_LINK);

        logger_Info("Link after click - " + getCurrentURL());
        logger_Info("Title after click - " + getTitle());
    }
}
