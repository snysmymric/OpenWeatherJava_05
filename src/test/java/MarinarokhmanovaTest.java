import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class MarinarokhmanovaTest extends BaseTest {
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");

    final static By GUIDE_SEARCH_FIELD = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");

    final static By LOGO_OPEN_WEATHER =
            By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");

    final static By HOME_GUIDE_BUTTON = By.xpath("//div[@class='container']//a[@href]");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_CHOICE_IN_DROP_DOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualResult = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testOpenWeatherMapGuide() {

        String expectedResult = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();

        click(GUIDE_SEARCH_FIELD);

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
        Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
    }

    @Test
    public void testLogoComp_OpenWeather()  {

        String expectedResult = "https://openweathermap.org/";

        openBaseURL();

        click(LOGO_OPEN_WEATHER);

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }

    @Test
    public void testGuideVisibleAndClickableHomeBaseURLOpened () {

        String expectedResult = "https://openweathermap.org/";

        openBaseURL();

        click(GUIDE_SEARCH_FIELD);
        waitElementToBeVisible(HOME_GUIDE_BUTTON);
        waitElementToBeClickable(HOME_GUIDE_BUTTON);
        click(HOME_GUIDE_BUTTON);
        getWait10();

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
