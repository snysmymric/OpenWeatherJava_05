import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class PratasevichSergeTest extends BaseTest {
    final static String Base_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By SIGN_IN_MENU = By.xpath("//li[@class='user-li']//a");
    final static By SIGN_IN_PAGE_TITLE = By.xpath("//h3[@class='first-child']");
    final static By FILL_IN_EMAIL_FIELD= By.xpath(
            "//div[@class='input-group']//input[@class='string email optional form-control']");
    final static By FILL_IN_PASSWORD_FIELD = By.xpath(
            "//div[@class='input-group']//input[@id='user_password']");
    final static By SUBMIT_BUTTON = By.xpath("//input[@value='Submit']");
    final static By USER_CONTAINER = By.xpath("//div[@class='inner-user-container']");
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
    public void testSignInMenuIsClickable(){
        String expectedResult = "Sign In To Your Account";

        openBaseURL();
        click(SIGN_IN_MENU);
        String actualResult = getText(SIGN_IN_PAGE_TITLE);

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testSignInUserIsLoggedInWithValidInput(){
        String email = "jka59433@xcoxc.com";
        String password ="Tester12#";
        String expectedResult = "Tester";

        openBaseURL();
        click(SIGN_IN_MENU);
        click(FILL_IN_EMAIL_FIELD);
        input(email, FILL_IN_EMAIL_FIELD);
        click(FILL_IN_PASSWORD_FIELD);
        input(password, FILL_IN_PASSWORD_FIELD);
        click(SUBMIT_BUTTON);

        String actualResult = getText(USER_CONTAINER);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
