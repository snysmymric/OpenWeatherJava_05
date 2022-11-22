import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class TellenqaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']");
    final static By GUIDE_MENU_BUTTON = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");
    final static By FOOTER_CONTAINER = By.xpath("//div[@class = 'inner-footer-container']");

    @Test
    public void testH2Tag_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testGuideMenuOpensGuidePage() {
        String expectedResult = "https://openweathermap.org/guide";

        openBaseURL();

        click(GUIDE_MENU_BUTTON);

        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResult);
    }

 @Test
    public void testFooterContainerIsDisplayed(){
        openBaseURL();

        Assert.assertTrue(getDriver().findElement(FOOTER_CONTAINER).isDisplayed());
    }
}