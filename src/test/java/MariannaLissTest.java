import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class MariannaLissTest extends BaseTest {

    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By GITHUB_ICON = By.xpath("//img[@src='/themes/openweathermap/assets/img/owm_icons/icon_github.png']");
    final static By GITHUB_LINK = By.xpath("//a[@href='https://github.com/search?q=openweathermap&ref=cmdform']");

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
    public void testClickIconInFooterAndOpenWebpageGithub() {
        final String expectedResult = "https://github.com/search?q=openweathermap&ref=cmdform";

        openBaseURL();
        scrollToPageBottom();
        click20(GITHUB_LINK);

        Assert.assertTrue(isDisplayed(GITHUB_ICON));

        click20(GITHUB_LINK);
        switchToAnotherWindow(getDriver());

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

    }
}