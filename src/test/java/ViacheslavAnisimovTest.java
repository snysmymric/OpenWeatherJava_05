import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

@Ignore
public class ViacheslavAnisimovTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static String EMAIL = "jka59433@xcoxc.com";
    final static String PASSWORD = "Tester12#";
    final static String SYMBOL_FAHRENHEIT = "°F";

    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By HAMBURGER_MENU = By.id("hamburger");
    final static By LINK_TO_GUIDE_IN_HAMBURGER_MENU = By.xpath("//ul[@id='mobile-menu']//a[@href='/guide']");
    final static By LINK_TO_MARKETPLACE = By.linkText("Marketplace");
    final static By LINK_TO_HISTORY_WEATHER_DATA = By.xpath("//div[@id='footer-website']//a[@href='/api#history']");
    final static By LINK_SIGN_IN = By.xpath("//div[@id='desktop-menu']//li/a[@href='https://openweathermap.org/home/sign_in']");
    final static By ENTER_EMAIL_FIELD = By.xpath("//input[@id='user_email']");
    final static By PASSWORD_FIELD = By.xpath(("//input[@id='user_password']"));
    final static By SUBMIT_BUTTON = By.xpath("//input[@name='commit']");
    final static By SUCCESSFULL_LOGIN_TEXT = By.xpath("//div[@class='panel-body']");
    final static By FAHRENHEIT = By.xpath("//div[@class='option'][contains(text(), 'Imperial: °F, mph')]");
    final static By TEMP_TEXT_IN_HEADING =By.xpath("//span[@class='heading'][contains(text(), '°F')]");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("own-loader-container"))
        );
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

    private void switchToNewTab(By by, WebDriverWait wait) {
        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() == 1;

        WebElement guideMenu = getDriver().findElement(by);
        guideMenu.click();

        getWait10();

        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    private void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    private void enterEmailAndPassword(String email, String password) {
        WebElement emailField = getDriver().findElement(ENTER_EMAIL_FIELD);
        emailField.click();
        emailField.sendKeys(email);

        WebElement passwordField = getDriver().findElement(PASSWORD_FIELD);
        passwordField.click();
        passwordField.sendKeys(password);

        WebElement submitButton = getDriver().findElement(SUBMIT_BUTTON);
        submitButton.click();
    }

    private boolean isTemp(By by, String text) {
        if (getDriver().findElement(by).getText().contains(text)) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait10());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testHamburgerMenuAndGuidePageTitle() {
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().manage().window().setSize(new Dimension(1024, 970));

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(HAMBURGER_MENU, getWait10());
        click(LINK_TO_GUIDE_IN_HAMBURGER_MENU, getWait10());

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test

    public void testMarketplaceMenuIsClickableOpenCustomWeatherProducts() {
        String expectedResult1 = "https://home.openweathermap.org/marketplace";
        String expectedResult2 = "Custom Weather Products";

        openBaseURL();
        waitForGrayFrameDisappeared();

        switchToNewTab(LINK_TO_MARKETPLACE, getWait10());

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testLinkHistoricalWeatherData() {
        String expectedResult = "https://openweathermap.org/api#history";

        openBaseURL();
        waitForGrayFrameDisappeared();

        scrollDown();

        click(LINK_TO_HISTORY_WEATHER_DATA, getWait10());

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void TestSignInToYourAccount_HappyPath() {
        String expectedResult = "Signed in successfully.";
        openBaseURL();
        waitForGrayFrameDisappeared();

        click(LINK_SIGN_IN, getWait5());

        click(ENTER_EMAIL_FIELD, getWait5());

        enterEmailAndPassword(EMAIL, PASSWORD);

        String actualResult = getDriver().findElement(SUCCESSFULL_LOGIN_TEXT).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testChangeTempFahrenheitInHeadingAfterClickButtonF() {

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(FAHRENHEIT, getWait10());
        waitForGrayFrameDisappeared();

        Assert.assertEquals(isTemp(TEMP_TEXT_IN_HEADING, SYMBOL_FAHRENHEIT), true);
    }
}
