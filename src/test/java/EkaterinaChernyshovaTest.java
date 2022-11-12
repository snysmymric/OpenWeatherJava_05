import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EkaterinaChernyshovaTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(2000);

        WebElement cityChoiceInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        cityChoiceInDropDownMenu.click();

        WebElement H2CityNameHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = H2CityNameHeader.getText();


        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testLinkAndTitleOnThePage_OpenWeatherMap() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(5000);

        WebElement guideMenu = getDriver().findElement(By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']")
        );
        guideMenu.click();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testIfTemperatureShowsInCelsius() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "°C";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement imperialFahrenheitTemperatureButton = getDriver().findElement(By.xpath(
                "//div[@id = 'weather-widget']//div[@class = 'option'][text() = 'Imperial: °F, mph']")
        );
        imperialFahrenheitTemperatureButton.click();
        Thread.sleep(5000);

        WebElement imperialCelciusTemperatureButton = getDriver().findElement(By.xpath(
                "//div[@class = 'option'][text() = 'Metric: °C, m/s']")
        );
        imperialCelciusTemperatureButton.click();
        Thread.sleep(5000);

        WebElement symbolC = getDriver().findElement(By.xpath(
                "//span[@class = 'heading']")
        );

        Boolean actualResult = symbolC.isDisplayed();

        Assert.assertTrue(actualResult, expectedResult);
    }
}