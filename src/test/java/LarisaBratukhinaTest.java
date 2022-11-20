import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

@Ignore
public class LarisaBratukhinaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H_2_CITY_COUNTY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By LOGO = By.xpath(
            "//ul/li[@class='logo']//a[@href='/']/img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_DROPDOWN_MENU = By.xpath(
            "//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By TEXT_DOWNLOAD_OPEN_WEATHER_APP = By.xpath("//div[@class='my-5']/p[@style='margin: 0;']");
    final static By DOWNLOAD_BUTTONS_PANEL = By.xpath(
            "//div[@class='my-5']/div[@style='display: flex; flex-direction: row;']/a");
    final static By DOWNLOAD_ON_THE_APP_STORE_LINK = By.xpath(
            "//div[@class='my-5']//a/img[@src='/themes/openweathermap/assets/img/mobile_app/app-store-badge.svg']");
    final static By GET_IT_ON_GOOGLE_PLAY_LINK = By.xpath(
            "//div[@class='my-5']//a/img[@src='/themes/openweathermap/assets/img/mobile_app/google-play-badge.png']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("own-loader-condainer")));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    private void waitElementToBeClickable(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    private String getCurrentUrl() {

        return getDriver().getCurrentUrl();
    }

    private void waitLoadingLogoToBeChanged() {
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    public int findAllVisibleElements(String xpath, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfAllElements(getDriver().findElement(By.xpath(xpath))));
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        return elements.size();
    }

    private void scrollByVisibleElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
    }

    private void switchToAnotherWindow(WebDriver driver) {
        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @Ignore
    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H_2_CITY_COUNTY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H_2_CITY_COUNTY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckUrlAfterReloadMainPage() {

        openBaseURL();
        waitForGrayFrameDisappeared();
        waitElementToBeVisible(LOGO, getWait10());
        waitElementToBeClickable(LOGO, getWait5());
        click(LOGO, getWait5());
        waitLoadingLogoToBeChanged();

        Assert.assertEquals(getCurrentUrl(), BASE_URL);
    }

    @Test
    public void testDownloadOpenWeatherAppAndAllStoreButtonsAreVisible() {
        int expectedResultAllStoreButtonsVisible = 2;
        String expectedResultText = "Download OpenWeather app";

        openBaseURL();
        waitForGrayFrameDisappeared();
        waitElementToBeVisible(TEXT_DOWNLOAD_OPEN_WEATHER_APP, getWait5());

        String actualResultText = getText(TEXT_DOWNLOAD_OPEN_WEATHER_APP, getDriver());

        Assert.assertEquals(actualResultText, expectedResultText);

        int actualResultAllStoreButtonsVisible = findAllVisibleElements(
                "//div[@class='my-5']/div[@style='display: flex; flex-direction: row;']/a", getWait5());

        scrollByVisibleElement(DOWNLOAD_BUTTONS_PANEL);

        Assert.assertEquals(actualResultAllStoreButtonsVisible, expectedResultAllStoreButtonsVisible);
    }

    @Test
    public void testDownloadOnTheAPPStoreLinkVisibleAndClickable() {
        String expectedResultDownloadOnTheAPPStoreLinkClickable = "https://apps.apple.com/gb/app/openweather/id1535923697";

        openBaseURL();
        waitForGrayFrameDisappeared();
        scrollByVisibleElement(DOWNLOAD_ON_THE_APP_STORE_LINK);
        click(DOWNLOAD_ON_THE_APP_STORE_LINK, getWait10());
        switchToAnotherWindow(getDriver());

        String actualResultDownloadOnTheAPPStoreLinkClickable = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResultDownloadOnTheAPPStoreLinkClickable
                .contains(expectedResultDownloadOnTheAPPStoreLinkClickable)));
    }

    @Ignore
    @Test
    public void testGetItOnGooglePlayStoreLinkIsVisibleAndClickable() {
        String expectedResultGetItOnGooglePlayStoreLinkClickable =
                "https://play.google.com/store/apps/details?id=uk.co.openweather";

        openBaseURL();
        waitForGrayFrameDisappeared();
        scrollByVisibleElement(GET_IT_ON_GOOGLE_PLAY_LINK);
        click(GET_IT_ON_GOOGLE_PLAY_LINK, getWait10());
        switchToAnotherWindow(getDriver());

        String actualResultGetItOnGooglePlayStoreLinkClickable = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResultGetItOnGooglePlayStoreLinkClickable.
                contains(expectedResultGetItOnGooglePlayStoreLinkClickable)));
    }
}
