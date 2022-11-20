import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.Random;

@Ignore
public class OMalanovaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By NOTICE_TEXT = By.xpath("//div[@id = 'weather-widget']//div[@class = 'sub not-found notFoundOpen']");
    final static By NOTICE_CONTAINER = By.xpath("//div[@class = 'sub not-found']");
    final static By NOTIFICATION_TEXT = By.xpath("//div[@class = 'widget-notification']");
    final static String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    final static int STR_LENGTH = (int) (Math.random() * 78 + 3);
    final static int STR_LENGTH_012 = (int) (Math.random() * 3);
    final static By TEMP = By.xpath("//span[@class='heading']");
    final static By SWITCH_TEMP_F = By.xpath("//div[text()='Imperial: °F, mph']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private void click(By by,  WebDriverWait wait) {
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
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    public String createRandomString(int strLength) {
        Random random = new Random();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            int number = random.nextInt(CHARS.length());
            char ch = CHARS.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }

    @Test
    public void testNoticeText_WhenCityNameFromRandomSymbols() {
        String cityName = createRandomString(STR_LENGTH);
        String expectedResult = "Not found. To make search more precise put the city's name, comma, 2-letter country code (ISO3166).";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        String actualResult = getText(NOTICE_TEXT, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testViewNotification_WhenCityNameFromRandomSymbols() {
        String cityName = createRandomString(STR_LENGTH);
        String expectedResult = "No results for " + cityName;

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        String actualResult = getText(NOTIFICATION_TEXT, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testNoView_NoticeText_WhenCityNameFromRandomSymbols() {
        String cityName = createRandomString(STR_LENGTH_012);

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_CITY_FIELD, getWait10());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait10());

        Assert.assertFalse(getDriver().findElement(NOTICE_CONTAINER).isDisplayed());

    }

    @Test
    public void testCheckCorrectTemperatureConversion_WhenSwitchTempUnitButton(){
        String cityName = "Paris";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait10());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String currentTempCstr = getText(TEMP, getDriver());
        int currentTempCint = Integer.parseInt(currentTempCstr.substring(0, currentTempCstr.length() - 2)) * 2 + 30;

        click(SWITCH_TEMP_F, getWait5());
        waitForGrayFrameDisappeared();

        String currentTempFstr = getText(TEMP, getDriver());
        int currentTempFint = Integer.parseInt(currentTempFstr.substring(0, currentTempFstr.length() - 2));

        boolean difference = (Math.abs(currentTempFint - currentTempCint) <= 1);

        Assert.assertTrue(difference);
    }
}
