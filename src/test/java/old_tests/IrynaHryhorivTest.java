package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class IrynaHryhorivTest extends BaseTest {
    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By SEARCH_GUIDE_BUTTON = By.xpath("//a[@href='/guide']");
    private final By CONFIRM_API = By.xpath("//div[@id='desktop-menu']//li/a[@href='/api']");
    private final String TEMP_IMPERIAL_F = "Imperial: °F, mph";
    private final String TEMP_METRIC_C = "Metric: °C, m/s";
    private final String SYMBOL_TEMP_F = "°F";
    private final String SYMBOL_TEMP_C = "°C";
    final static By APPROVE_DEGREES = By.xpath("//div[@id='weather-widget']//span[@class='heading']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualResult = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testOpenAndClickToGuide() {

        String expectedResult = "https://openweathermap.org/guide";
        String expectedResult1 ="OpenWeatherMap API guide - OpenWeatherMap" ;

        openBaseURL();

        click20(SEARCH_GUIDE_BUTTON);

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
        Assert.assertEquals(getDriver().getTitle(), expectedResult1);
    }
    @Test
    public void testMenuAPIIsClickable(){
        String expectedResult = "https://openweathermap.org/api";

        openBaseURL();

        click20(CONFIRM_API);

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }

    @Test
    public void VerifyChangingTempUnitInHeading_WhenSwitchTempUnitButton() {

        openBaseURL();
        By temperature = By.xpath(String.format("//div[text()='%s']", TEMP_IMPERIAL_F));
        click20(temperature);
        Assert.assertTrue(getText(APPROVE_DEGREES).contains(SYMBOL_TEMP_F));
    }
    @Test
    public void VerifyMetricSymbolIsShownInCurrentTempWhenChangingUnitToMetric(){

        openBaseURL();
        By temperature = By.xpath(String.format("//div[text()='%s']", TEMP_METRIC_C));
        click20(temperature);
        Assert.assertTrue(getText(APPROVE_DEGREES).contains(SYMBOL_TEMP_C));
    }

    @Test
    public void testConfirmAPI30OrangeButton(){

        openBaseURL();
        int expectedResult = 30;

        click20(CONFIRM_API);

        int actualResult = seeAllElementAndCount(By.xpath("//a[contains(@class, 'orange')]"));

        Assert.assertEquals(actualResult, expectedResult);
    }
}

