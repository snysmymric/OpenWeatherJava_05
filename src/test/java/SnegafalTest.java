import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SnegafalTest extends BaseTest {

    static final String BASE_URL = "https://openweathermap.org/";
    static final By GUIDE_IN_MENU = By.xpath("//ul[@id='first-level-nav']//a[@href='/guide']");

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Ignore
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);
        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();
        Thread.sleep(2000);
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);

        Assert.assertEquals(h2CityCountryHeader.getText(), expectedResult);
    }

    @Test
    public void testTitleAndUrlPage_WhenClickingGuideMenu() {

        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultLink = "https://openweathermap.org/guide";

        getDriver().get(BASE_URL);
        waitForGrayFrameDisappeared();
        click(GUIDE_IN_MENU, getWait5());

        Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultLink);
    }

    @Ignore
    @Test
    public void testTemperatureInCityInFarenheit () throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "F";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement farenheitMeasure = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//div[text()='Imperial: °F, mph']"));
        farenheitMeasure.click();
        WebElement farenheitTemperatureTextInCity = getDriver().findElement(
                By.xpath("//div[@class='current-temp']/span[@class='heading']"));
        String[] farenheitText = farenheitTemperatureTextInCity.getText().split("");
        Thread.sleep(1000);

        Assert.assertEquals(farenheitText[farenheitText.length - 1], expectedResult);
    }

    @Ignore
    @Test
    public void testCookiePanelWithTwoButtonsInFooter () throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultCookieText = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. Any data " +
                "collected is anonymised. You can allow all cookies or manage them individually.";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement cookieTextPanel = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//p"));
        String actualResultCookiePanelText = cookieTextPanel.getText();
        WebElement allowAllButton = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//button[text()='Allow all']"));
        WebElement manageCookiesButton = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//a[@href='/cookies-settings']"));

        Assert.assertEquals(expectedResultCookieText, actualResultCookiePanelText);
        Assert.assertTrue(allowAllButton.isDisplayed());
        Assert.assertTrue(manageCookiesButton.isDisplayed());
    }
}

