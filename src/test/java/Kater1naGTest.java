import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Kater1naGTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry () throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//input[@placeholder = 'Search city']")
        );
        Thread.sleep(10000);
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(5000);

        WebElement choiceParisFr = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']")
        );
        choiceParisFr.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

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
}
