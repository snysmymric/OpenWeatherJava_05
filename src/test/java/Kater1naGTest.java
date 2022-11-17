import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class Kater1naGTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By CHOICE_PARIS_FR = By.xpath
            ("//ul[@class = 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']");
    private void openBaseURL(){
        getDriver().get(BASE_URL);
    }
    private void waitForGreyFrameDisappeared(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));

    }
    private String getText(By by,WebDriver driver){
        return driver.findElement(by).getText();
    }
    private void click(By by, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void input(String text,By by,WebDriver driver){
        driver.findElement(by).sendKeys(text);
    }
    private void waitElementToBeVisible(By by,WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    private void waitTextChanged(By by, String text, WebDriver driver, WebDriverWait wait){
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
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
@Ignore
    @Test
    public void testRedirectToGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String reUrl = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);

        WebElement searchGuide = getDriver().findElement(
                By.xpath("//a[@href='/guide']")
        );
        Thread.sleep(10000);
        searchGuide.click();

        String currentUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(currentUrl, reUrl);

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Ignore
    @Test
    public void testImperialF() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String tempButton = "Imperial: °F, mph";
        String expectedResult = "°F";

        getDriver().get(url);

        WebElement imperialF = getDriver().findElement(
                By.xpath("//*[@id='weather-widget']/div[1]/div/div/div[1]/div[2]/div[3]")
        );
        Thread.sleep(10000);
        imperialF.click();

        WebElement tempF = getDriver().findElement(
                By.xpath("//*[@id='weather-widget']/div[2]/div[1]/div[1]/div[2]/div[1]/span")
        );

        String letterF = tempF.getText();

        String actualResult;
        if(letterF.length() == 4){
            actualResult = letterF.substring(2);
        } else if (letterF.length() > 4) {
            actualResult = letterF.substring(3);
        }else {
            actualResult = letterF.substring(1);
        }

        Assert.assertEquals(actualResult, expectedResult);
    }
@Ignore
    @Test
    public void testBelowButtonsAndText() throws InterruptedException {

        String url = "https://openweathermap.org/";
        int expectedButtons = 2;
        String expectedResult = "We use cookies"
                + " which are essential for the site to work. We also use non-essential cookies to help us"
                + " improve our services. Any data collected is anonymised."
                + " You can allow all cookies or manage them individually.";

        getDriver().get(url);

        WebElement panelOfCookies = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']")
        );

        WebElement textElementOfPanel = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']/div/div/div/div/p")
        );

        String actualResult = textElementOfPanel.getText();

        int buttonsOfPanel  =  getDriver().findElements(
                By.tagName("button")
        ).size();

        Assert.assertTrue(panelOfCookies.isDisplayed());

        Assert.assertEquals(actualResult, expectedResult);

        Assert.assertEquals(buttonsOfPanel, expectedButtons);

    }
}
