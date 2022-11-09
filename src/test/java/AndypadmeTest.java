import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AndypadmeTest extends BaseTest {

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
                By.xpath("//div[@id = 'weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFranceChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFranceChoiceInDropdownMenu.click();
        Thread.sleep(2000);

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        String actualResult = h2CityCountryHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testAPIGuideTitle_WhenNavigatingToGuidePage() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedUrl = "https://openweathermap.org/guide";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@id = 'desktop-menu']//li/a[@href = '/guide']")).click();
        Thread.sleep(500);

        String actualResult = getDriver().getTitle();
        String actualUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedTitle);
        Assert.assertEquals(actualUrl, expectedUrl);

    }
}
