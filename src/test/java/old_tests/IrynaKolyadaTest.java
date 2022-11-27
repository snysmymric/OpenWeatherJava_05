package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

public class IrynaKolyadaTest extends BaseTest {
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By PLACEHOLDER_BOX = By.xpath("//div[@id='desktop-menu']//form[@role='search']");
    final static By PLACEHOLDER_FIELD = By.xpath("//div[@id='desktop-menu']//input[@type='text']");
    final static By PLACEHOLDER_VALUE = By.xpath("//div//input[@id='search_str']");
    final static By HAMBURGER_BUTTON_IMAGE = By.xpath("//nav/ul/li[@id='hamburger']/img");
    final static By HAMBURGER_DROPDOWN_MENU = By.xpath("//ul[@id='mobile-menu']/li/a");
    final static By ERROR_MESSAGE_EMPTY_PLACEHOLDER = By.xpath("//div[@class='row']/div/ul/li");

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
    public void testTextOfPlaceholderNavBar_homePage() {
        final String expectedResult = "Weather in your city";

        openBaseURL();

        Assert.assertTrue(isDisplayed(PLACEHOLDER_BOX));

        Assert.assertEquals(getTextByAttribute(PLACEHOLDER_FIELD, "placeholder"), expectedResult);
    }

    @Test
    public void testSearchByWordPlaceholderNavBar_homePage() {
        final String city = "Barcelona";

        openBaseURL();
        click(PLACEHOLDER_FIELD);
        inputAndEnter(PLACEHOLDER_FIELD, city);

        Assert.assertEquals(getTextByAttribute(PLACEHOLDER_VALUE, "value"), city);
        Assert.assertTrue(getCurrentURL().contains(city));
    }

    @Test
    public void testHamburgerMenuNavBarisDisplayed_homePage() {
        String expectedResult = "https://openweathermap.org/themes/openweathermap/assets/img/owm_icons/icon_hamburger.svg";
        int width = 1020;
        int heigth = 880;
        String attributeName = "src";
        int numberOfButtonsOnHamburgerMenu = 12;
        List<String> listOfHamburgerMenu = List.of("Guide", "API", "Dashboard", "Marketplace", "Pricing",
                "Maps", "Our Initiatives", "Partners", "Blog", "For Business", "Ask a question", "Sign in");

        openBaseURL();
        getSmallSizeWindow(width, heigth);

        Assert.assertEquals(getTextByAttribute(HAMBURGER_BUTTON_IMAGE, attributeName), expectedResult);
        click(HAMBURGER_BUTTON_IMAGE);

        Assert.assertTrue(getListOfElements(HAMBURGER_DROPDOWN_MENU).size() > 0);
        Assert.assertEquals(getListOfElements(HAMBURGER_DROPDOWN_MENU).size(), numberOfButtonsOnHamburgerMenu);
        Assert.assertEquals(getListText(HAMBURGER_DROPDOWN_MENU), listOfHamburgerMenu);
    }

    @Test
    public void testEmptyPlaceholderNavBar_homePage() {
        String emptyFiled = "";
        String defaultCity = "London, UK";
        String attribute = "value";
        String errorMessage = "To make it more precise put the city's name, comma, 2-letter country code (ISO3166). You will get all proper cities in chosen country.\n" +
                "The order is important - the first is city name then comma then country. Example - London, GB or New York, US.";

        openBaseURL();
        inputAndEnter(PLACEHOLDER_FIELD,emptyFiled);

        Assert.assertEquals(getTextByAttribute(PLACEHOLDER_VALUE,attribute),defaultCity);
        Assert.assertEquals(getText(ERROR_MESSAGE_EMPTY_PLACEHOLDER), errorMessage);
    }
}
