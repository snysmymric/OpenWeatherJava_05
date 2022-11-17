import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class VadimKachanovichTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static String CLASS_GRAY_FRAME = "owm-loader-container";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");


    String cityName = "Paris";
    String expectedResult = "Paris, FR";
    private void getURL () {
        getDriver().get(BASE_URL);
    }
    private void waitForGrayFrameDisappeared () {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className(CLASS_GRAY_FRAME)));
    }
    private String getText (By by, WebDriver driver) {
        return  driver.findElement(by).getText();
    }
    private void click (By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void inputText (By by, WebDriver driver, String text) {
        driver.findElement(by).sendKeys(text);
    }
    private void waitToBeVisibleElement (By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    private void waitTextToBeChanged (By by, String text, WebDriver driver,WebDriverWait wait) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(by),text)));
    };

    @Test
    public void testWhenSearchingCityCountry()  {

        getURL();
        waitForGrayFrameDisappeared();
        String startH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
        click(SEARCH_CITY_FIELD,getWait5());
        inputText(SEARCH_CITY_FIELD, getDriver(), cityName);
        click(SEARCH_BUTTON,getWait10());
        waitToBeVisibleElement(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER,startH2Header,getDriver(),getWait10());
        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());
        Assert.assertEquals(actualResult,expectedResult);
    }
}
