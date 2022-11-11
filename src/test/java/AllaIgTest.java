import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AllaIgTest extends BaseTest {

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
        Thread.sleep(5000);
        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testUrlAndTitle_WhenGuidePress() throws InterruptedException {

        String url = "https://openweathermap.org/";

        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);

        Thread.sleep(10000);

        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li/a[@href = '/guide']")
        );
        guideButton.click();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);

        String expectedCurrentUrl = "https://openweathermap.org/guide";

        String actualCurrentUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
