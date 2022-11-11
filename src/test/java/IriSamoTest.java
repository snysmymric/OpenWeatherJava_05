import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class IriSamoTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCity = getDriver().findElement(
                By.xpath("//input[@placeholder='Search city']")
        );
        Thread.sleep(1000);
        searchCity.click();
        searchCity.sendKeys(cityName);
        Thread.sleep(1000);
        WebElement buttonSearch = getDriver().findElement(
                By.xpath("//button[@type='submit']")
        );
        buttonSearch.click();
        Thread.sleep(1000);
        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']"
                        + "//span[text()='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id=\"weather-widget\"]//h2")
        );
        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTabGuideUrlTitle() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedUrl = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement tabGuide = getDriver().findElement(
                By.xpath("//a[@href='/guide']")
        );
        tabGuide.click();
        Thread.sleep(1000);

        String actualTitle = getDriver().getTitle();
        String actualUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testUnitOfMeasurementFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedMeasurement = "°F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement areaMeasurementImperial = getDriver().findElement(
                By.xpath("//div[@class='switch-container']"
                        + "/div[text()='Imperial: °F, mph']")
        );
        areaMeasurementImperial.click();
        Thread.sleep(2000);

        String  currentTemp = getDriver().findElement(
                By.xpath("//div[@class='current-temp']//span")
        ).getText();
        String actualMeasurement = currentTemp.substring(currentTemp.length()-2);

        Assert.assertEquals(actualMeasurement, expectedMeasurement);
    }

    @Test
    public void testCookiesMessage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedMessage = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage "
                + "them individually.";
        int expectedQuantityButtons = 2;

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement panelMessage = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//p")
        );
        String actualText = panelMessage.getText();

        int btn = getDriver().findElements(
                By.xpath("//div/*[@class='stick-footer-panel__link']")
        ).size();

        Assert.assertEquals(btn, expectedQuantityButtons);
        Assert.assertEquals(actualText, expectedMessage);
    }
}