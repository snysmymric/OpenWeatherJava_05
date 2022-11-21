import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class NataliadylaiTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By MENU_GUIDE = By.xpath("//div[@id='desktop-menu']//a[contains (text(), 'Guide')]");
    final static By GUIDE_TITLE = By.xpath("//div/ul/li/a[@href = '/guide']");
    final static By LOGO = By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");


    @Test
    public void testH2TagText_WhenSearchCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);
        click(SEARCH_CITY_FIELD);
        input(cityName,SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);
        String actualResult = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_OpenWeatherMapAPIGuide () throws InterruptedException {
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "Guide";
        openBaseURL();
        click(MENU_GUIDE);

        String actualResultTitle = getText(GUIDE_TITLE);

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
        
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }

    @Test
    public void test_CorrectPageUpdatedAfterClickOnLogo() {
        String expectedResult = "https://openweathermap.org/";
        String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";
        openBaseURL();
        click(LOGO);

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);

        Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
    }
}