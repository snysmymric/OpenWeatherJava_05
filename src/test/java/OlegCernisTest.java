
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class OlegCernisTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By searchCityField = By.xpath("//div[@id='weather-widget']//input[@placeholder= 'Search city']");
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By searchButton = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By MOLDOVA_MD_CHOICE_IN_DROPDOWN_MENU =
            By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Chisinau, MD ']");
    final static By fahrenheit = By.xpath("//div[text()= 'Imperial: °F, mph']");
    final static By metric = By.xpath("//div[text()= 'Metric: °C, m/s']");
    final static By confirmTemp = By.xpath("//div[@class = 'current-temp']/span");

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

    private boolean compareSymbol(WebDriver driver, String temp, String symbolTemp) {
        click(By.xpath(String.format("//div[text()='%s']", temp)), getWait5());

        return getText(confirmTemp, getDriver()).contains(symbolTemp);
    }

    @Ignore
    @Test
    public void testVerifyThatLocationIsChangedInCurrentWeatherBlockAfterInputNewCity() {

        String newCity = "Chisinau, MD ";
        String expectedResult = "Chisinau, MD ";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
        click(searchCityField, getWait5());
        input(newCity, searchCityField, getDriver());
        click(searchButton, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait5());
        click(MOLDOVA_MD_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        Assert.assertEquals(expectedResult, newCity);
    }

    @Ignore
    @Test
    public void testVerifyChangingTempUnitInHeading_WhenSwitchTempUnitButton() {

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(fahrenheit, getWait5());

        String actualResult = getText(confirmTemp, getDriver());

        Assert.assertTrue(actualResult.contains("°F"));

    }

    @Test
    public void testVerifyMetricSymbolIsShownInCurrentTempWhenChangingUnitToMetric() {

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(metric, getWait5());

        String actualResult2 = getText(confirmTemp,getDriver());

        Assert.assertTrue(actualResult2.contains("°C"));
    }
}
