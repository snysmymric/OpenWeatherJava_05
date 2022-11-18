import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class ElNov686Test extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_CITY_FIELD = By
            .xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_FROM_DROPDOWN_MENU = By
            .xpath("//ul[@class = 'search-dropdown-menu']/li/span[text ()= 'Paris, FR ']");
    final static By H2_CITYNAME_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");

    private void getBaseUrl() {
        getDriver().get(BASE_URL);
    }

    private void greyFrame() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("own-loader-container")));
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
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    private void waitTextTobeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    private int countOrangeButtons(By by, WebDriver driver) {
        return driver.findElements(by).size();
    }

    @Test
    public void testH2TagTextWhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getBaseUrl();
        greyFrame();

        String oldH2Header = getText(H2_CITYNAME_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_FROM_DROPDOWN_MENU, getWait10());
        waitTextTobeChanged(H2_CITYNAME_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H2_CITYNAME_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testTemperatureImperialFahrenheitVerify() {
        String expectedResult = "F";

        getBaseUrl();
        greyFrame();

        click(By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']"), getWait10());
        String actualResult = getText(By.xpath("//span[@class='heading']"), getDriver());

        actualResult = actualResult.substring(actualResult.length() - 1);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMenuAPI_Verify30orangeButtons() {
        int expectedResult = 30;

        getBaseUrl();
        greyFrame();
        click(By.xpath("//div[@id='desktop-menu']//a[normalize-space()='API']"), getWait10());
        int actualResult = countOrangeButtons(By.xpath("//a[contains(@class, 'btn_block orange round') " +
                "or contains(@class, 'ow-btn round btn-orange')]"), getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMainNavBarMenuPartnersMenuisclickable() {
        String expectedResult = "https://openweathermap.org/examples";

        getBaseUrl();
        greyFrame();
        click(By.xpath("//div[@id='desktop-menu']//a[normalize-space()='Partners']"), getWait10());
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testStartWeatherLocationVerifyLocationButtonShowingDefaultLocation() {
        String expectedResult1 = "Location unavailable. Displaying default location: London";
        String expectedResult2 = "London, GB";

        getBaseUrl();
        greyFrame();
        click(By.xpath("//div[@class='control-el']//*[name()='svg']"), getWait5());

        String actualResult1 = getText(By
                .xpath("//span[contains(text(),'Location unavailable. Displaying default location:')]"), getDriver());
        String actualResult2 = getText(By
                .xpath("//h2[normalize-space()='London, GB']"), getDriver());

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}

