import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
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

    @Ignore
    @Test
    public void testCheckTitleName() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedGuideLink = "https://openweathermap.org/guide";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement guideTab = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[@href='/guide']")
        );

        guideTab.click();
        Thread.sleep(2000);
        String actualGuideLink = getDriver().getCurrentUrl();
        Thread.sleep(5000);
        String actualTitle = getDriver().getTitle();
        Assert.assertEquals(actualGuideLink, expectedGuideLink);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Ignore
    @Test
    public void testFooterCookie() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultFooterCookie = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedAllowAllButton = "Allow all";
        String expectedManageCookiesButton = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(8000);
        WebElement footerCookie = getDriver().findElement(
                By.xpath("//div[@id= 'stick-footer-panel']//p[@class = 'stick-footer-panel__description']")
        );
        WebElement buttonAllowAll = getDriver().findElement(By.xpath("//div[@id= 'stick-footer-panel']//div/button"));
        WebElement buttonManageCookies = getDriver().findElement(By.xpath("//div[@id= 'stick-footer-panel']//div/a"));

        String аctualAllowAllButton = buttonAllowAll.getText();
        String аctualManageCookiesButton = buttonManageCookies.getText();
        String actualResultFooterCookie = footerCookie.getText();

        Assert.assertEquals(actualResultFooterCookie, expectedResultFooterCookie);
        Assert.assertEquals(аctualAllowAllButton, expectedAllowAllButton);
        Assert.assertEquals(аctualManageCookiesButton, expectedManageCookiesButton);
    }

    @Ignore
    @Test
    public void testClickAPIfind30Buttons() throws InterruptedException {

        String url = "https://openweathermap.org/";
        int expectedResult = 30;
        getDriver().get(url);
        Thread.sleep(10000);

        WebElement element = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        element.click();

        int actualResult = getDriver().findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
