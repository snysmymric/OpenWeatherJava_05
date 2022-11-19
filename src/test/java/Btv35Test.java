import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class Btv35Test extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']" +
            "//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROP_DOWN_MENU =
            By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By MENU_GUIDE = By.xpath("//div[@id = 'desktop-menu']//a [@href = '/guide']");
    final static By COOKIES_PANEL = By.xpath("//div[@id = 'stick-footer-panel']" +
            "//p[@class='stick-footer-panel__description']");
    final static By BUTTON_ALLOW_ALL = By.xpath("//div[@id='stick-footer-panel']"
            + "//button[@class='stick-footer-panel__link']");
    final static By BUTTON_MANAGE_COOKIES = By.xpath("//div[@id='stick-footer-panel']"
            + "//a[text()=' Manage cookies ']");
    final static By SWITCH_TO_FAHRENHEIT = By.xpath("//div[@id ='weather-widget']"
            + "//div[text() = 'Imperial: °F, mph']");
    final static By TEMPERATURE_IN_FAHRENHEIT = By.xpath("//div[@id='weather-widget']"
            +"//span[contains (text(),'F')]");
    private void openBaseUrl () {
        getDriver().get(BASE_URL);
    }
    private void waitForGreyFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }
    private String getText (By by, WebDriver driver) {
        return driver.findElement(by).getText();
    }
    private void click (By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void input (String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }
    private void waitElementToBeVisible (By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    private void waitTextToBeChanged (By by, String text,WebDriver driver,WebDriverWait wait) {
        wait.until(ExpectedConditions.
                not(ExpectedConditions.textToBePresentInElement(driver.findElement(by),text)));
    }
    private String currentUrl () {
        return getDriver().getCurrentUrl();
    }
    @Test
    public void testH2TagText_WhenSearchingCityCountry(){
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseUrl();
        waitForGreyFrameDisappeared();
        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
        click(SEARCH_BUTTON,getWait5());
        input(cityName,SEARCH_CITY_FIELD,getDriver());
        click(SEARCH_BUTTON,getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU,getWait10());
        click(PARIS_FR_CHOICE_IN_DROP_DOWN_MENU,getWait5());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER,oldH2Header,getDriver(),getWait10());
        String actualResult = getText(H2_CITY_COUNTRY_HEADER,getDriver());

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testGuideMenuWithTitle_OpenWeatherMapAPIGuide() {
        String guideUrl = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseUrl();
        waitForGreyFrameDisappeared();
        click(MENU_GUIDE,getWait5());
        Assert.assertEquals(guideUrl,currentUrl());

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testSwitchingTemperatureToFahrenheit() {
        String expectedResult = "F";

        openBaseUrl();
        waitForGreyFrameDisappeared();
        click(SWITCH_TO_FAHRENHEIT,getWait5());
        String actualResult = getText(TEMPERATURE_IN_FAHRENHEIT,getDriver()).
                substring(getText(TEMPERATURE_IN_FAHRENHEIT,getDriver()).length() - 1);

        Assert.assertEquals(actualResult,expectedResult);

        Assert.assertTrue(getText(TEMPERATURE_IN_FAHRENHEIT,getDriver()).contains(expectedResult));
    }
    @Test
    public void testTwoButtonsOnTheCookiesMessage(){
        String cookies = "We use cookies which are essential for the site to work. We also use "
                + "non-essential cookies to help us improve our services. Any data collected is "
                + "anonymised. You can allow all cookies or manage them individually.";
        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";

        openBaseUrl();

        Assert.assertEquals(cookies, getText(COOKIES_PANEL,getDriver()));

        String actualResultAllowAllButton = getText(BUTTON_ALLOW_ALL, getDriver());

        String actualResultButtonManageCookies = getText(BUTTON_MANAGE_COOKIES,getDriver());

        Assert.assertEquals(actualResultAllowAllButton, expectedResultAllowAll);
        Assert.assertEquals(actualResultButtonManageCookies, expectedResultManageCookies);
    }
}
