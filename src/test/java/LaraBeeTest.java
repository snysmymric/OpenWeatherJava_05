import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class LaraBeeTest extends BaseTest {

    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath(
            "//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By LOGO = By.xpath(
            "//a[@href]//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    private final By DOWNLOAD_ON_THE_APP_BUTTON = By.xpath(
            "//div[@class='my-5']//a/img[@src='/themes/openweathermap/assets/img/mobile_app/app-store-badge.svg']");
    private final By DOWNLOAD_ON_THE_APP_LINK = By.xpath(
            "//a[@href='https://apps.apple.com/gb/app/openweather/id1535923697'] [@target='_blank']");
    private final By GET_IT_ON_GOOGLE_PLAY_BUTTON = By.xpath(
            "//img[@src='/themes/openweathermap/assets/img/mobile_app/google-play-badge.png']");
    private final By GET_IT_ON_GOOGLE_PLAY_LINK = By.xpath(
            "//a[@href='https://play.google.com/store/apps/details?id=uk.co.openweather'][@target='_blank']");
    private final By TEXT_DOWNLOAD_OPEN_WEATHER_APP = By.xpath("//div[@class='my-5']/p[@style='margin: 0;']");

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
    public void testCheckUrlAfterReloadMainPage() {
        openBaseURL();
        waitElementToBeVisible(LOGO);
        click(LOGO);

        Assert.assertEquals(getCurrentURL(), BASE_URL);
    }

    @Test
    public void testDownloadOnTheAPPStoreLinkExists_WhenClickButton() {
        final String expectedDownloadOnTheAPPStoreLinkExists =
                "https://apps.apple.com/gb/app/openweather/id1535923697";

        openBaseURL();
        scrollByVisibleElement(DOWNLOAD_ON_THE_APP_BUTTON);

        Assert.assertTrue(isDisplayed(GET_IT_ON_GOOGLE_PLAY_BUTTON));

        click(DOWNLOAD_ON_THE_APP_LINK);
        switchToAnotherWindow(getDriver());

        String actualDownloadOnTheAPPStoreLinkExists = getDriver().getCurrentUrl();

        Assert.assertTrue((actualDownloadOnTheAPPStoreLinkExists
                .contains(expectedDownloadOnTheAPPStoreLinkExists)));
    }

    @Test
    public void testGetItOnGooglePlayStoreLinkExists_WhenClickButton() {
        final String expectedGetItOnGooglePlayStoreLinkExists =
                "https://play.google.com/store/apps/details?id=uk.co.openweather";

        openBaseURL();
        scrollByVisibleElement(GET_IT_ON_GOOGLE_PLAY_BUTTON);

        Assert.assertTrue(isDisplayed(GET_IT_ON_GOOGLE_PLAY_BUTTON));

        click(GET_IT_ON_GOOGLE_PLAY_LINK);
        switchToAnotherWindow(getDriver());

        String actualGetItOnGooglePlayStoreLinkExists = getDriver().getCurrentUrl();

        Assert.assertTrue((actualGetItOnGooglePlayStoreLinkExists.
                contains(expectedGetItOnGooglePlayStoreLinkExists)));
    }

    @Test
    public void testDownloadOpenWeatherAppAndAllStoreButtonsAreVisible() {
        final int expectedAllStoreButtonsVisible = 2;
        final String expectedText = "Download OpenWeather app";

        openBaseURL();
        waitElementToBeVisible(TEXT_DOWNLOAD_OPEN_WEATHER_APP);

        String actualText = getText(TEXT_DOWNLOAD_OPEN_WEATHER_APP);

        Assert.assertEquals(actualText, expectedText);

        int actualAllStoreButtonsVisible = findAllVisibleElements(
                "//div[@class='my-5']/div[@style='display: flex; flex-direction: row;']/a", getWait10());

        Assert.assertEquals(actualAllStoreButtonsVisible, expectedAllStoreButtonsVisible);
    }
}
