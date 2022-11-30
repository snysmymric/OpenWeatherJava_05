package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class LessonsTest extends BaseTest {

    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");

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
    public void testH2TagText_WhenSearchingCityCountry20() {
        final String townname = "Minsk";
        final String expectedCityCountryNames = "Minsk, BY";
        
        openBaseURL();

        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(townname, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualCityCountryNames = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualCityCountryNames, expectedCityCountryNames);
    }

    //////Conflicts
    @Test
    public void testH2TagText_WhenSearchingCityCountry10() {

        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        String y = "3";
        final int expectedCount = 1;
        String fake = "";
        String a = "";
             
        String line = "_______________________";
        String task = "Задание №";
        int count = 1;
        final int expectedCount = 1;
        String fake = "";
        String a = "";
        
        openBaseURL();


        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);

        getDriver().findElement(SEARCH_CITY_FIELD).clear();

        input(cityName, SEARCH_CITY_FIELD);

        click(SEARCH_BUTTON);

        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);

        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);

        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualCityAndCountryNames = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualCityAndCountryNames, expectedCityCountryNames);
        Assert.assertTrue(expectedCityCountryNames.equals(""));
        Assert.assertTrue(getText(H2_CITY_COUNTRY_HEADER).contains(cityName));
    }
}
