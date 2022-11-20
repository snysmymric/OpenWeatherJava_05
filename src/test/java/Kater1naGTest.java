import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class Kater1naGTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By CHOICE_PARIS_FR = By.xpath
            ("//ul[@class = 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']");
    final static By SEARCH_GUIDE = By.xpath("//a[@href='/guide']");
    final static By IMPERIAL_F = By.xpath("//*[@id='weather-widget']/div[1]/div/div/div[1]/div[2]/div[3]");
    final static By TEMP_F = By.xpath("//*[@id='weather-widget']/div[2]/div[1]/div[1]/div[2]/div[1]/span");

    final static By PANEL_OF_COOKIES = By.xpath("//div[@id='stick-footer-panel']");
    final static By TEXT_ELEMENT_OF_PANEL = By.xpath("//div[@id='stick-footer-panel']/div/div/div/div/p");
    final static By BUTTONS_OF_PANEL = By.tagName("button");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }
    private void waitForGreyFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));

    }
    private String getText(By by,WebDriver driver) {
        return driver.findElement(by).getText();
    }
    private WebElement getElement(By by, WebDriver driver) {
        WebElement element = driver.findElement(by);
        return element;
    }
    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void input(String text,By by,WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }
    private void waitElementToBeVisible(By by,WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    private void waitTextChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }
    private String symbolF(String text) {
        if(text.length() == 4){
            return text.substring(2);
        } else if (text.length() > 4) {
            return text.substring(3);
        }else {
            return text.substring(1);
        }
    }
    private int getSizeOfElements(By by, WebDriver driver){
        int sizeOfElement = getDriver().findElements(by).size();
        return sizeOfElement;
    }


    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGreyFrameDisappeared();
        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName,SEARCH_CITY_FIELD,getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU,getWait10());
        click(CHOICE_PARIS_FR, getWait10());
        waitTextChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(),getWait10());
        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountryByPressENTER() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGreyFrameDisappeared();
        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
        click(SEARCH_CITY_FIELD, getWait10());
        input(cityName,SEARCH_CITY_FIELD,getDriver());
        getElement(SEARCH_CITY_FIELD, getDriver()).sendKeys(Keys.ENTER);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU,getWait10());
        click(CHOICE_PARIS_FR, getWait10());
        waitTextChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(),getWait20());
        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }


    @Test
    public void testRedirectToGuide() {
        String expectedResultReUrl = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        waitForGreyFrameDisappeared();
        click(SEARCH_GUIDE, getWait5());
        String actualResultReUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResultReUrl, expectedResultReUrl);
        String actualResult = getDriver().getTitle();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testImperialF() {
        String expectedResultTempButton = "Imperial: °F, mph";
        String expectedResult = "°F";

        openBaseURL();
        waitForGreyFrameDisappeared();
        click(IMPERIAL_F, getWait10());
        waitElementToBeVisible(IMPERIAL_F, getWait10());
        String actualResultTempButton= getText(IMPERIAL_F, getDriver());
        waitElementToBeVisible(TEMP_F, getWait10());
        String  letterSymbolF = getText(TEMP_F,getDriver());
        String actualResult = symbolF(letterSymbolF);

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(expectedResultTempButton, actualResultTempButton);
    }

    @Test
    public void testBelowButtonsAndTextIntoCookiePanel()  {
        int expectedButtons = 2;
        String expectedText = "We use cookies"
                + " which are essential for the site to work. We also use non-essential cookies to help us"
                + " improve our services. Any data collected is anonymised."
                + " You can allow all cookies or manage them individually.";

        openBaseURL();
        waitForGreyFrameDisappeared();
        waitElementToBeVisible(PANEL_OF_COOKIES, getWait20());
        waitElementToBeVisible(TEXT_ELEMENT_OF_PANEL, getWait20());
        String actualText = getText(TEXT_ELEMENT_OF_PANEL, getDriver());
        int actualButtons = getSizeOfElements(BUTTONS_OF_PANEL, getDriver());

        Assert.assertTrue(getElement(PANEL_OF_COOKIES, getDriver()).isDisplayed());
        Assert.assertEquals(actualText, expectedText);
        Assert.assertEquals(actualButtons, expectedButtons);

    }
}
