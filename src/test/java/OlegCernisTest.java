
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class OlegCernisTest extends BaseTest {

    final static By searchCityField = By.xpath("//div[@id='weather-widget']//input[@placeholder= 'Search city']");
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By searchButton = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By MOLDOVA_MD_CHOICE_IN_DROPDOWN_MENU =
            By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Chisinau, MD ']");
    final static By fahrenheit = By.xpath("//div[text()= 'Imperial: °F, mph']");
    final static By metric = By.xpath("//div[text()= 'Metric: °C, m/s']");
    final static By confirmTemp = By.xpath("//div[@class = 'current-temp']/span");

    @Test
    public void testVerifyThatLocationIsChangedInCurrentWeatherBlockAfterInputNewCity() {
        final String newCity = "Chisinau, MD ";
        final String expectedCity = "Chisinau, MD ";

        openBaseURL();

        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);
        
        click(searchCityField);
        input(newCity, searchCityField);
        click(searchButton);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(MOLDOVA_MD_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        Assert.assertEquals(expectedCity, newCity);
    }

    @Test
    public void testVerifyChangingTempUnitInHeading_WhenSwitchTempUnitButton() {
        openBaseURL();
        click(fahrenheit);

        String actualResult = getText(confirmTemp);
        Assert.assertTrue(actualResult.contains("°F"));

    }

    @Test
    public void testVerifyMetricSymbolIsShownInCurrentTempWhenChangingUnitToMetric() {
        openBaseURL();
        click(metric);

        String actualResult2 = getText(confirmTemp);
        Assert.assertTrue(actualResult2.contains("°C"));
    }
}
