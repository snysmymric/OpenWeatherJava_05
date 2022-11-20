import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class Lelya_DemlelTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id= 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id= 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id= 'weather-widget']// button[@type= 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class= 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']");
    final static By BUTTON_IMPERIAL_WITH_F = By.xpath("//div[@class='switch-container']/div[3]");
    final static By CITY_TEMPERATURE_IN_FAHRENHEIT = By.className("heading");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGreyFrameDisappeared() {
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    @Test
    public void testTemperatureIsFahrenheit_WhenClickButtonImperial() {
        String fTempSymbol = "°F";

        openBaseURL();
        waitForGreyFrameDisappeared();
        click(BUTTON_IMPERIAL_WITH_F, getWait5());
        getText(CITY_TEMPERATURE_IN_FAHRENHEIT, getDriver());

        String temperatureFahrenheit = getText(CITY_TEMPERATURE_IN_FAHRENHEIT, getDriver());

        Assert.assertEquals(getText(CITY_TEMPERATURE_IN_FAHRENHEIT, getDriver())
                .substring(temperatureFahrenheit.length() - 2), fTempSymbol);
        Assert.assertTrue(getText(CITY_TEMPERATURE_IN_FAHRENHEIT, getDriver()).contains(fTempSymbol));
    }
}
