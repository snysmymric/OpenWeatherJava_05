import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MarinarokhmanovaTest extends BaseTest {
    final static String Base_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");

    private void openBaseURL(){
        getDriver().get(Base_URL);
    }

    private void waitForGrayFrameDisappeared(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver){
        return driver.findElement(by).getText();
    }

    private void click(By by, WebDriver driver){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait5().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void input(String text, By by, WebDriver driver){
        driver.findElement(by).sendKeys(text);
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait){
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getDriver());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getDriver());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_CHOICE_IN_DROP_DOWN_MENU, getDriver());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testOpenWeatherMapGuide() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement guideSearchField = getDriver().findElement(
                By.xpath("//a[@href][text()='Guide']")
        );
        guideSearchField.click();
        Thread.sleep(2000);

        String confirmPageWithLink = getDriver().getTitle();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = confirmPageWithLink.intern();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Ignore
    @Test
    public void testUnitsImperialCtoF() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);

        WebElement unitsImperialF = getDriver().findElement(
                By.xpath("//div[@class ='option'][text()='Imperial: °F, mph']")
        );
        unitsImperialF.click();
        Thread.sleep(2000);

        WebElement unitsImperialFCity = getDriver().findElement(
                By.xpath("//div[@class ='current-temp']/span")
        );
        unitsImperialFCity.click();
        Thread.sleep(2000);

        boolean actualResult = unitsImperialFCity.getText().contains("F");

        Assert.assertTrue(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testImperialMetric_FtoC() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "°C";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement panelImperial = getDriver().findElement(
                By.xpath("//div[@class ='option'][text()='Imperial: °F, mph']")
        );
        panelImperial.click();
        Thread.sleep(1000);

        WebElement panelMetric = getDriver().findElement(
                By.xpath("//div[@class ='option'][text()='Metric: °C, m/s']")
        );
        panelMetric.click();
        Thread.sleep(1000);

        WebElement panelTemperatureC = getDriver().findElement(
                By.xpath("//div[@class = 'controls']//div[@class ='option']")
        );

        boolean actualResult = panelTemperatureC.getText().contains("°C");

        Assert.assertTrue(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testLogoComp_OpenWeather() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement logoCompOpenWeather = getDriver().findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        logoCompOpenWeather.click();
        Thread.sleep(10000);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
