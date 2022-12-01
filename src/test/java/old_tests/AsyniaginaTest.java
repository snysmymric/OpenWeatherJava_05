package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class AsyniaginaTest extends BaseTest {

    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By SUBSCRIBE_FOR_FREE = By.xpath("//a[@href='https://home.openweathermap.org/users/sign_up']");
    private final By COMPANY_TEXT = By.xpath("//div[@class='footer-section']//div[@class='section-content']/p");
    private final By ACCURACY_AND_QUALITY_OF_WEATHER_DATA = By.xpath("//a[@href='/accuracy-and-quality']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        openBaseURL();

        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualCityCountryNames = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testSubscribeForFreeLinkExists_WhenClickLink() {
        String expectedSubscribeForFreeLinkExists = "https://home.openweathermap.org/users/sign_up";

        openBaseURL();
        scrollByVisibleElement(SUBSCRIBE_FOR_FREE);

        Assert.assertTrue(isDisplayed(SUBSCRIBE_FOR_FREE));

        click(SUBSCRIBE_FOR_FREE);
        switchToAnotherWindow(getDriver());

        String actualSubscribeForFreeLinkExists = getDriver().getCurrentUrl();

        Assert.assertTrue(actualSubscribeForFreeLinkExists
                .contains(expectedSubscribeForFreeLinkExists));
    }

    @Test
    public void testCompanyTextIsExist() {
        String expectedCompanyText = "OpenWeather is a team of IT experts and data scientists that has been practising deep weather data science. For each point on the globe, OpenWeather provides historical, current and forecasted weather data via light-speed APIs. Headquarters in London, UK.";

        openBaseURL();
        scrollByVisibleElement(COMPANY_TEXT);
        String actualCompanyText = getText(COMPANY_TEXT);

        Assert.assertTrue(isDisplayed(COMPANY_TEXT));
        Assert.assertEquals(actualCompanyText, expectedCompanyText);
    }

    @Test
    public void testAccurancyAndQualityLinkVisibleAndClickable() {
        String expectedAccurancyAndQualityOfWeatherDataLink = "https://openweathermap.org/accuracy-and-quality";

        openBaseURL();
        scrollByVisibleElement(ACCURACY_AND_QUALITY_OF_WEATHER_DATA);

        Assert.assertTrue(isDisplayed(ACCURACY_AND_QUALITY_OF_WEATHER_DATA));

        click(ACCURACY_AND_QUALITY_OF_WEATHER_DATA);
        switchToAnotherWindow(getDriver());

        String actualAccurancyAndQualityOfWeatherDataLink = getDriver().getCurrentUrl();

        Assert.assertTrue(actualAccurancyAndQualityOfWeatherDataLink
                .contains(expectedAccurancyAndQualityOfWeatherDataLink));
    }
}