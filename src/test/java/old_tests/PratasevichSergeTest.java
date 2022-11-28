package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;
import java.util.ArrayList;
import java.util.List;

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
    final static By FILL_IN_EMAIL_FIELD = By.xpath(
            "//div[@class='input-group']//input[@class='string email optional form-control']");
    final static By FILL_IN_PASSWORD_FIELD = By.xpath(
            "//div[@class='input-group']//input[@id='user_password']");
    final static By SUBMIT_BUTTON = By.xpath("//input[@value='Submit']");
    final static By USER_CONTAINER = By.xpath("//div[@class='inner-user-container']");
    final static By BLOG_MENU = By.xpath(
            "//div[@id='desktop-menu']//a[@href='https://openweather.co.uk/blog/category/weather']");
    final static By POST_FILTERS = By.xpath("//div[@class='post-filters']/form/ul");
    final static By SUB_MENU_BLOG = By.xpath("//li[@class='post-filters__item']");
    final static By ALL_SUB_MENUS_BLOG = By.xpath("//div[@class='post-filters']//li");
    final static String BLOG_URL = "https://openweather.co.uk/blog";
    final static By ALL_PAGE_NUMBER_BUTTONS_BLOG = By.xpath(
            "//ul[@class='pagination']/li");
    final static By SUB_MENU_ALL = By.xpath("//ul[@class='post-filters__items']//label[@for='all']");

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
    public void testSignInMenuIsClickable() {
        String expectedResult = "Sign In To Your Account";

        openBaseURL();
        click(SIGN_IN_MENU);
        String actualResult = getText(SIGN_IN_PAGE_TITLE);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testSignInUserIsLoggedInWithValidInput() {
        String email = "jka59433@xcoxc.com";
        String password = "Tester12#";
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

    @Test
    public void testBlogMenuIsOpened(){
        String expectedPestFiltersItemsAreVisible = "blog-categories";
        String attribute = "id";

        openBaseURL();
        click(BLOG_MENU);
        jumpToNextWindow();
        getWait20();

        String actualPestFiltersItemsAreVisible = getTextByAttribute(POST_FILTERS, attribute);

        Assert.assertEquals(actualPestFiltersItemsAreVisible, expectedPestFiltersItemsAreVisible);
    }

    @Test
    public void testAllSubMenusOfBlogPageAreVisibleAndClickable() {
        String blogUrl = "https://openweather.co.uk/blog";

        int expectedQuantityOfAllSubMenuBlog = 6;
        List<String> expectedSubMenuNames = new ArrayList<>();
        expectedSubMenuNames.add("ALL");
        expectedSubMenuNames.add("WEATHER");
        expectedSubMenuNames.add("AGRO");
        expectedSubMenuNames.add("PLATFORM");
        expectedSubMenuNames.add("TECHNOLOGIES");
        expectedSubMenuNames.add("TEAM&COMPANY");

        openBaseURL();
        getDriver().get(blogUrl);
        int actualQuantityOfAllSubMenuBlog = getListSize(SUB_MENU_BLOG);

        Assert.assertEquals(actualQuantityOfAllSubMenuBlog, expectedQuantityOfAllSubMenuBlog);

        List<String> actualSubMenuNames = getListText(ALL_SUB_MENUS_BLOG);

        Assert.assertEquals(actualSubMenuNames, expectedSubMenuNames);
    }

    @Test
    public void testQuantityOFPageNumberButtonsOnSubMenuAll(){
        int expectedQuantityOfPageNumberButtons = 13;

        openBaseURL();
        click(BLOG_MENU);
        jumpToNextWindow();
        click(SUB_MENU_ALL);
        scrollByVisibleElement(ALL_PAGE_NUMBER_BUTTONS_BLOG);
        int actualQuantityOfPageNumberButtons = getListSize(ALL_PAGE_NUMBER_BUTTONS_BLOG);

        Assert.assertEquals(actualQuantityOfPageNumberButtons, expectedQuantityOfPageNumberButtons);
    }
}
