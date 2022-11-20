import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class TellenqaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWMMENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']");
    final static By GUIDE_MENU_BUTTON = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGreyFrameDissapeared() {
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

    private void waitElementTobeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    @Test
    public void testH2Tag_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGreyFrameDissapeared();

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER,getDriver());

        click(SEARCH_CITY_FIELD,getWait10());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementTobeVisible(SEARCH_DROPDOWMMENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header,getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testGuideMenuOpensGuidePage() {
        String expectedResult = "https://openweathermap.org/guide";

        openBaseURL();
        waitForGreyFrameDissapeared();

        click(GUIDE_MENU_BUTTON, getWait5());

        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResult);
    }
}