package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class OlgaKorolenkoTest extends BaseTest {

    private final static By H_2_CITY_CONTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final static By SEARCH_CITY_FIELD = By.xpath("//div[@id ='weather-widget']//input[@placeholder = 'Search city']");
    private final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final static By PARIS_FR_CHOICE_ID_DROPDOWN_MENU =  By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() ='Paris, FR ']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        openBaseURL();

        final String oldH2Header = getText(H_2_CITY_CONTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_ID_DROPDOWN_MENU);
        waitTextToBeChanged(H_2_CITY_CONTRY_HEADER, oldH2Header);

        String actualCityCountryNames = getText(H_2_CITY_CONTRY_HEADER);

        Assert.assertEquals(actualCityCountryNames,expectedCityCountryNames);
    }
}
