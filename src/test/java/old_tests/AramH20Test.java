package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AramH20Test extends BaseTest {
    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath(
            "//div[@id = 'weather-widget']/div/div/div//div/div/input[@placeholder = 'Search city' ]");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath(
            "//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryName = "Paris, FR";
        openBaseURL();
        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);
        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);
        String actualCityCountryName = getText(H2_CITY_COUNTRY_HEADER);
        Assert.assertEquals(actualCityCountryName, expectedCityCountryName);
    }



    private final By GUIDE_MENU_BUTTON = By.xpath("//div[@id='desktop-menu']/ul/li[1]/a");


    @Test
    public void testGuideHeadersWeatherh4_VerifyGuidePageHeaders() {
        final String expectedHeaderNameH1 = "Weather data in a fast and easy-to-use way";
        final List<String> expectedHeaderNames = Arrays.asList("OpenWeather products", "Dedicated weather products", "Openweather NWP model", "How to start using Weather API");
        final By HEADERS_H1 = By.xpath("//main[@class='wrapper']/div[2]/div/div//h1");
        final By HEADERS_H2 = By.xpath("//main[@class='wrapper']/div[2]/div/div//h2");
        openBaseURL();
        click(GUIDE_MENU_BUTTON);
        String actualHeaderNamesH1 = getText(HEADERS_H1);
        List<String> actualHeaderNames = getListText(HEADERS_H2);

        Assert.assertEquals(actualHeaderNamesH1, expectedHeaderNameH1);
        if (actualHeaderNames.size() > 0) {
            for(int i =0; i < actualHeaderNames.size(); i++){
                Assert.assertEquals(actualHeaderNames.get(i), expectedHeaderNames.get(i));
            }
        }
    }
}