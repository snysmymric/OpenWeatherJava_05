import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;


public class ElNov686Test extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_CITY_FIELD = By
            .xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_FROM_DROPDOWN_MENU= By
            .xpath("//ul[@class = 'search-dropdown-menu']/li/span[text ()= 'Paris, FR ']");
    final static By H2_CITYNAME_HEADER= By.xpath("//div[@id = 'weather-widget']//h2");

    private void getBaseUrl(){
        getDriver().get(BASE_URL);
    }

    private void greyFrame(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("own-loader-container")));
    }

    private String getText(By by, WebDriver driver) {
        return driver.findElement(by).getText();
    }

    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void input (String text, By by,WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }

    private void waitElemantToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    private void waitTextTobeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text )));
    }

    @Test
    public void testH2TagTextWhenSearchingCityCountry()  {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getBaseUrl();
        greyFrame();

        String oldH2Header = getText(H2_CITYNAME_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD,getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElemantToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_FROM_DROPDOWN_MENU,getWait10());
        waitTextTobeChanged(H2_CITYNAME_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H2_CITYNAME_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Ignore
    @Test
    public void testTemperatureImperialFahrenheitVerify() throws InterruptedException {
        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement imperialF = getDriver().findElement(By
                .xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']"));

        imperialF.click();
        Thread.sleep(5000);
        WebElement temperatureF = getDriver().findElement(By
                .xpath("//span[@class='heading']"));

        String actualResult = temperatureF.getText();
        actualResult = actualResult.substring(actualResult.length() - 1);

        String expectedResult = "F";

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Ignore
    @Test
    public void testVerifyCookiesTextAndTwoButtons() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement cookiesText = getDriver().findElement(By
                .xpath("//p[@class='stick-footer-panel__description']"));
        String actualResult1 = cookiesText.getText();

        WebElement allowAll = getDriver().findElement(By.xpath("//button[text()='Allow all']"));
        Boolean actualResult2 = allowAll.isDisplayed();

        WebElement manageCookies = getDriver().findElement(By
                .xpath("//a[@class='stick-footer-panel__link']"));
        Boolean actualResult3 = manageCookies.isDisplayed();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertTrue(actualResult2);
        Assert.assertTrue(actualResult3);

        }
    @Ignore
    @Test
    public void testMenuAPI_Verify30orangeButtons() throws InterruptedException {
        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement menuAPI = getDriver().findElement(By
                .xpath("//div[@id='desktop-menu']//a[normalize-space()='API']"));
        menuAPI.click();
        Thread.sleep(3000);

        List<WebElement> orangeButtons = getDriver().findElements(By
                .xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange')]"));

        Assert.assertEquals(orangeButtons.size(), expectedResult);
        }
    }

