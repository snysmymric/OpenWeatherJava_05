import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


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


    @Test
    public void testUpdatePage() {
        final String expectedResult = "Loading";

        openBaseURL();

        click(LOGO);

        String actualResult = getTextByAttribute(LOADING, "aria-label");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIconCurrentLocation() {
        final String cityName = "Norway";

        final String expectedResult = "London, GB";

        openBaseURL();
        click(SEARCH_CITY_FIELD);
        inputAndEnter(SEARCH_CITY_FIELD, cityName);
        click(CLICK_CITY_NORWAY);
        click(PRESS_BUTTON_LOCATION);

        String actualResult = getTextWaiting(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testAllIconsAreShownAndClickable() {
        final int expectedResult = 9;

        openBaseURL();

        click(BUTTON_DIFFERENT_WEATHER);

        int actualResult = seeAllElementAndCount(ELEMENTS_DIFFERENT_WEATHER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_X_ButtonIsShown() {

        openBaseURL();
        click20(BUTTON_DIFFERENT_WEATHER);

        boolean actualResult = isDisplayed(BUTTON_X_IN_DIFFERENT_WEATHER_MENU);

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testSendButtonIsShown() {

        openBaseURL();

        click(BUTTON_DIFFERENT_WEATHER);

        boolean actualResult = isDisplayed(BUTTON_SEND_IN_DIFFERENT_WEATHER_MENU);

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testNoticeText_WhenCityNameFromRandomSymbols() {
        final String cityName = "Rrr";

        final String expectedResult = "No results for Rrr";

        openBaseURL();
        inputAndEnter(SEARCH_CITY_FIELD, cityName);

        String actualResult = getText(ERROR_MASSAGE);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_X_buttonColorGreen() {
        final String cityName = "Rrr";

        final String expectedResult = "rgba(120, 203, 191, 0.8)";

        openBaseURL();
        inputAndEnter(SEARCH_CITY_FIELD, cityName);

        String actualResult = backgroundColor(BUTTON_BACK_COLOR);

        Assert.assertEquals(actualResult, expectedResult);
    }

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
}