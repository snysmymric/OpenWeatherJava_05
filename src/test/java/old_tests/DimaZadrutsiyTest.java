package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.List;


public class DimaZadrutsiyTest extends BaseTest {

    private final By LOGO = By.xpath("//li[@class='logo']");
    private final By SEARCH_CITY_FIELD = By.xpath("//div//input[@placeholder='Search city']");
    private final By BUTTON_DIFFERENT_WEATHER = By.xpath("//span[text()='Different Weather?']");
    private final By LOADING = By.xpath("//div[@aria-label='Loading']");
    private final By CLICK_CITY_NORWAY = By.xpath("//span[text()='45.787, -87.904']");
    private final By PRESS_BUTTON_LOCATION = By.xpath("//div[@class='control-el']");
    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id]//h2");
    private final By ELEMENTS_DIFFERENT_WEATHER = By.xpath("//ul[@class='icons']/li");
    private final By BUTTON_X_IN_DIFFERENT_WEATHER_MENU = By
            .xpath("//div[@class='pop-up-container']//*[name()='path' and @fill='#8a8a8a']");
    private final By BUTTON_SEND_IN_DIFFERENT_WEATHER_MENU = By.xpath("//button[text()='Send']");
    private final By ERROR_MASSAGE = By.xpath("//span[text()='No results for Rrr']");
    private final By BUTTON_BACK_COLOR = By.xpath("//div[@class='widget-notification']");
    private final By BUTTON_MARKETPLACE = By.xpath("//div[@id]//a[text()='Marketplace']");
    private final By DESKTOP_MENU_HISTORY_BULK = By.xpath("//ul[@id='desktop-menu']//a[text()='History Bulk']");
    private final By BUTTON_WEATHER_PARAMETERS = By.xpath("//span[text()='Weather Parameters:']");
    private final By ELEMENTS_POP_UP_MENU_WEATHER_PARAMETERS = By
            .xpath("//div[@class='owm-check-box-group columns']//label");
    private final By SEARCH_FIELD_CREATE_NEW_HISTORY_BULK = By.xpath("//input[@id='firstSearch']");
    private final By CHOOSE_CITY = By.className("pac-item");
    private final By ADD_LOCATION = By.xpath("//div[@class='map-container']//button[@class='button-round dark']");
    private final By CITY_LIST = By.xpath("//tr/td[@contenteditable='true']");
    private final By BUTTON_DOCUMENTATION = By.xpath("//a[text()='Documentation']");
    private final By BUTTON_BUY_HISTORY_BULK = By.xpath("//a[text()='Buy History Bulk']");
    private final By LOGO_ON_PAGE_CREATE_NEW_HISTORY_BULK = By.xpath("//a[@class='logo']");
    private final By LOGO_SIGN_IN_TO_YOUR_ACCOUNT = By.xpath("//li[@class='logo']");

    @Test
    public void testCheckingTheCheckboxeOfAListOfElements() {

        openBaseURL();
        click20(BUTTON_MARKETPLACE);
        jumpToNextWindow();
        click20(DESKTOP_MENU_HISTORY_BULK);
        click20(BUTTON_WEATHER_PARAMETERS);
        clickAllElements(ELEMENTS_POP_UP_MENU_WEATHER_PARAMETERS);

        boolean actualResult = checkingForUnselectedElements(ELEMENTS_POP_UP_MENU_WEATHER_PARAMETERS);

        Assert.assertTrue(actualResult);
    }

    @Ignore
    @Test
    public void testCreateNewHistoryBulk() {
        final String CityName = "New York, NY, USA";
        final String CityName2 = "Chernihiv, Chernihiv Oblast, Ukraine";
        final String CityName3 = "Reykjavík, Iceland";

        final List<String> expectedResult = new ArrayList<>();
        expectedResult.add("New York ");
        expectedResult.add("Chernihiv ");
        expectedResult.add("Reykjavík ");

        openBaseURL();
        click20(BUTTON_MARKETPLACE);
        jumpToNextWindow();
        click20(DESKTOP_MENU_HISTORY_BULK);
        click20(SEARCH_FIELD_CREATE_NEW_HISTORY_BULK);
        input(CityName, SEARCH_FIELD_CREATE_NEW_HISTORY_BULK);
        click20(CHOOSE_CITY);
        click20(ADD_LOCATION);
        input(CityName2, SEARCH_FIELD_CREATE_NEW_HISTORY_BULK);
        click20(CHOOSE_CITY);
        click20(ADD_LOCATION);
        input(CityName3, SEARCH_FIELD_CREATE_NEW_HISTORY_BULK);
        click20(CHOOSE_CITY);
        click20(ADD_LOCATION);

        List<String> actualResult = getListText(CITY_LIST);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSwitchTo6DifferentWindows() {

        final int expectedNumberOfOpenPages = 6;

        openBaseURL();
        click20(BUTTON_MARKETPLACE);
        switchToSpecificWindows(1);
        click20(BUTTON_DOCUMENTATION);
        switchToSpecificWindows(2);
        click20(BUTTON_BUY_HISTORY_BULK);
        switchToSpecificWindows(3);
        click20(LOGO_ON_PAGE_CREATE_NEW_HISTORY_BULK);
        click20(LOGO_SIGN_IN_TO_YOUR_ACCOUNT);
        switchToSpecificWindows(1);
        click20(BUTTON_DOCUMENTATION);
        switchToSpecificWindows(4);
        click20(BUTTON_BUY_HISTORY_BULK);
        switchToSpecificWindows(5);
        click20(LOGO_ON_PAGE_CREATE_NEW_HISTORY_BULK);
        click20(LOGO_SIGN_IN_TO_YOUR_ACCOUNT);

        int actualNumberOfOpenPages = returnsTheWindowIdList().size();

        Assert.assertEquals(actualNumberOfOpenPages, expectedNumberOfOpenPages);
    }
}