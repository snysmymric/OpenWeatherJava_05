package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class LaraBeeTest extends BaseTest {

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
    private final By TEXT_DOWNLOAD_OPEN_WEATHER_APP = By.xpath(".//p[text()='Download OpenWeather app']");
    private final By ALL_STORE_BUTTONS = By.xpath(
            "//div[@class='my-5']/div[@style='display: flex; flex-direction: row;']/a");

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
        jumpToNextWindow();

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
        jumpToNextWindow();

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

        int actualAllStoreButtonsVisible = seeAllElementAndCount(ALL_STORE_BUTTONS);

        Assert.assertEquals(actualAllStoreButtonsVisible, expectedAllStoreButtonsVisible);
    }
}
