import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class Lenochkanik80Test extends BaseTest {
    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder= 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By SEARCH_API_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/api']");
    private final By API_TITLE = By.className("breadcrumb-title");

    @Test
    public void testH2TagText_WhenSearchingCityCountry()  {
        final String cityName = "Paris";
        final String expectedResult = "Paris, FR";

        openBaseURL();

        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

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
    public void testAPIMenu() {
        final String expectedResult = "https://openweathermap.org/api";

        openBaseURL();
        click(SEARCH_API_MENU);

        String actualResult = getCurrentURL();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTitle_WhenOpenAPIMenuPage() {
        final String expectedResult = "Weather API";

        openBaseURL();
        click(SEARCH_API_MENU);

        String actualResult = getText(API_TITLE);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
