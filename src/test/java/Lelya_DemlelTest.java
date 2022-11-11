import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Lelya_DemlelTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id= 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id= 'weather-widget']// button[@type= 'submit']")
        );
        searchButton.click();
        Thread.sleep(10000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class= 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id= 'weather-widget']//h2")
        );
        Thread.sleep(10000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void temperatureFahrenheit_WhenClickButtonImperial() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultstr = "°F";
        String fTempSymbol = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement buttonImperial = getDriver().findElement(
                By.xpath("//div[@class='switch-container']/div[3]"));
        buttonImperial.click();
        Thread.sleep(10000);
        WebElement cityTemperatureInFahrenheit = getDriver().findElement(
                By.className("heading")
        );
        String temperatureFahrenheit = cityTemperatureInFahrenheit.getText();

        Assert.assertEquals(temperatureFahrenheit
                .substring(temperatureFahrenheit.length() - 2), expectedResultstr);
        Assert.assertTrue(cityTemperatureInFahrenheit.getText().contains(fTempSymbol));
    }
}
