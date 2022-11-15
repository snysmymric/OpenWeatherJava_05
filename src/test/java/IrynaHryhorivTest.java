import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class IrynaHryhorivTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";

    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By SEARCH_GUIDE_BUTTON = By.xpath("//a[@href='/guide']");

    final static By CONFIRM_API = (By.xpath("//*[@id='desktop-menu']/ul/li[2]/a"));


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
    private void waitURLToBeChanged(String url2, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.urlToBe(url2));
    }
    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testOpenAndClickToGuide() {

        String expectedResult = "https://openweathermap.org/guide";
        String expectedResult1 ="OpenWeatherMap API guide - OpenWeatherMap" ;

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_GUIDE_BUTTON,getWait5());

        waitURLToBeChanged(expectedResult, getDriver(), getWait10());

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
        Assert.assertEquals(getDriver().getTitle(), expectedResult1);
    }
    @Test
    public void testMenuAPIIsClickable(){
        String expectedResult = "https://openweathermap.org/api";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(CONFIRM_API, getWait5());

        waitURLToBeChanged(expectedResult, getDriver(), getWait10());

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }
}
