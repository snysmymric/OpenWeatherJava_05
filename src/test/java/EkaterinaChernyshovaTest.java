import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EkaterinaChernyshovaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H2_CITY_NAME_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");


    private void openBaseURL(String url) {
        getDriver().get(url);
    }

    private void waitForGrayFrameDisappear() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated((by)));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL(BASE_URL);
        waitForGrayFrameDisappear();

        String oldH2Header = getText(H2_CITY_NAME_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H2_CITY_NAME_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H2_CITY_NAME_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }
@Ignore
    @Test
    public void testLinkAndTitleOnThePage_OpenWeatherMap() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(5000);

        WebElement guideMenu = getDriver().findElement(By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']")
        );
        guideMenu.click();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
@Ignore
    @Test
    public void testIfTemperatureShowsInCelsius() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "°C";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement imperialFahrenheitTemperatureButton = getDriver().findElement(By.xpath(
                "//div[@id = 'weather-widget']//div[@class = 'option'][text() = 'Imperial: °F, mph']")
        );
        imperialFahrenheitTemperatureButton.click();
        Thread.sleep(5000);

        WebElement imperialCelciusTemperatureButton = getDriver().findElement(By.xpath(
                "//div[@class = 'option'][text() = 'Metric: °C, m/s']")
        );
        imperialCelciusTemperatureButton.click();
        Thread.sleep(5000);

        WebElement symbolC = getDriver().findElement(By.xpath(
                "//span[@class = 'heading']")
        );

        Boolean actualResult = symbolC.isDisplayed();

        Assert.assertTrue(actualResult, expectedResult);
    }
}