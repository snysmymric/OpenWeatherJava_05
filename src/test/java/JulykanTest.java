import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class JulykanTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By LOGO = By.className("logo");
    final static By DESKTOP_MENU = By.id("desktop-menu");
    final static By H1_OUR_INITIATIVES_PAGE = By.className("breadcrumb-title");
    final static By OUR_INITIATIVES = By.xpath("//div[@id='desktop-menu']//a[@href='/our-initiatives']");
    final static By DIFFERENT_WEATHER_BUTTON = By.xpath("//div[@class='controls']//span");
    final static By MORE_OPTIONS_BUTTON = By.xpath("//div[@class='more-options']");
    final static By MORE_OPTIONS_PANEL = By.xpath("//div[@class = 'more-options']/following-sibling::div");
    final static By LESS_OPTIONS_BUTTON = By.xpath("//div[@class='more-options']");

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
    public void testMainNavBarAllElementsExistsVisible() {
        openBaseURL();
        Assert.assertTrue(isElementExists(LOGO));
        Assert.assertTrue(isElementExists(DESKTOP_MENU));
    }

    @Test
    public void testTitleAndUrl_WhenClickOurInitiatives_AtDesktopMenu() {
        String expectedUrl = String.format("%sour-initiatives", BASE_URL);
        String expectedTitle = "Our Initiatives - OpenWeatherMap";
        String expectedH1 = "Our Initiatives";

        openBaseURL();

        click(OUR_INITIATIVES);

        String currentUrl = getCurrentURL();
        String currentTitle = getTitle();
        String currentH1 =getText(H1_OUR_INITIATIVES_PAGE);

        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(currentTitle, expectedTitle);
        Assert.assertEquals(currentH1, expectedH1);
    }

    @Test
    public void testLessOptionsIsClickableWhenClickDifferentWeather() {
        String expectedText = "Less options";

        openBaseURL();

        click(DIFFERENT_WEATHER_BUTTON);
        click(MORE_OPTIONS_BUTTON);

        Assert.assertEquals(getText(MORE_OPTIONS_BUTTON), expectedText);
        Assert.assertTrue(isDisplayed(MORE_OPTIONS_PANEL));

        click(LESS_OPTIONS_BUTTON);

        Assert.assertFalse(isDisplayed(MORE_OPTIONS_PANEL));
    }
}
