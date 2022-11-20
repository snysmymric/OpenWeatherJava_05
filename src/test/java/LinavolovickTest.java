import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class LinavolovickTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static String WEATHER_API_URL = "https://openweathermap.org/api#current";

    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By LOGO = By.xpath("//li[@class='logo']/descendant::img");
    final static By CURRENT_AND_FORECAST_APIS_LINK = By.xpath("//li/a[@href='/api#current']");

    private void openBaseURL(){
        getDriver().get(BASE_URL);
    }

    private void waitForGreyFrameDisapearred() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container"))
        );
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private String getCurrentUrl() {

        return getDriver().getCurrentUrl();
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

    private void waitElementToBeClickable(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        }

    private void waitTextChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    private void useJavascriptExecutorToPerfomClick() {
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].click();", getDriver().findElement(CURRENT_AND_FORECAST_APIS_LINK));
    }

    private void scrollDown () {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGreyFrameDisapearred();

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testVerifyLogoExistsAndRedirectsToStartPage() {
        String expectedResult = BASE_URL;

        openBaseURL();
        waitForGreyFrameDisapearred();
        click(LOGO, getWait5());

        Assert.assertEquals(getCurrentUrl(), expectedResult);
    }

    @Ignore
    @Test
    public void testVerifyCurrentAndForecastAPIsLinkExistsAndRedirectsToWeatherAPIPage() {
        String expectedResult = WEATHER_API_URL;

        openBaseURL();
        waitForGreyFrameDisapearred();
        useJavascriptExecutorToPerfomClick();

        Assert.assertEquals(getCurrentUrl(), expectedResult);
    }
}
