import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class SnegafalTest extends BaseTest {

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
    public void testTitleAndUrlPage_WhenClickingGuideMenu () throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultLink = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement guideInMenu = getDriver().findElement(
                By.xpath("//ul[@id='first-level-nav']//a[@href='/guide']"));
        guideInMenu.click();
        Thread.sleep(3000);
        String guidePageTitle = getDriver().getTitle();
        String guidePageLink = getDriver().getCurrentUrl();

        Assert.assertEquals(expectedResultTitle, guidePageTitle);
        Assert.assertEquals(expectedResultLink, guidePageLink);
    }

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

