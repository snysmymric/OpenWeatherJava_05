import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Random;

public class OMalanovaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By NOTICE_TEXT = By.xpath("//div[@id = 'weather-widget']//div[@class = 'sub not-found notFoundOpen']");
    final static String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    final static int STR_LENGTH = 9;
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

    public String createRandomString(int strLength) {
        Random random = new Random();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < STR_LENGTH; i++) {
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
}
