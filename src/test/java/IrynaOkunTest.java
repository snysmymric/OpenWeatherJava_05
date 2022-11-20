import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import static org.testng.AssertJUnit.assertEquals;

@Ignore
public class IrynaOkunTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H_2_CITY_COUNTRY_NAME_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath
            ("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By SEARCH_PARIS_FR_CHOICE_DROPDOWN_MENU = By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");

    final static By API_NAVIGATION_BUTTON = By.xpath("//div/ul/li/a[@href='/api']");
    final static By ORANGE_BUTTONS = By.xpath("//a[contains(@class, 'orange')]");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void openUrl(String url) {
        getDriver().get(url);
    }

    private void waitForGrayFrameDisappear() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
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
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    private String countQuantityOfElementsOnPage(By by, WebDriver driver) {
        String textOfNumber = String.valueOf(getDriver().findElements(by).size());
        return textOfNumber;
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappear();

        String oldH2Header = getText(H_2_CITY_COUNTRY_NAME_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait10());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait10());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(SEARCH_PARIS_FR_CHOICE_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H_2_CITY_COUNTRY_NAME_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTRY_NAME_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSubmit30OrangeButtons() {
        String expectedResult = "30";
        openBaseURL();
        waitForGrayFrameDisappear();
        click(API_NAVIGATION_BUTTON, getWait10());

        String actualResult = countQuantityOfElementsOnPage(ORANGE_BUTTONS, getDriver());

        assertEquals(actualResult, expectedResult);
    }

}
