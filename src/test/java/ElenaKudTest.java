import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class ElenaKudTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

        @Test
    public void testShowingTempInFahrenheitAfterSwitchFromCelsius() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultCurrentWeatherInFahrenheit = "F";
        String tempInFahrenheit = "F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement showTempInFahrenheit = getDriver().findElement(
                By.xpath("//div[@class = 'option'][contains(text(), 'Imperial')]")
        );
        showTempInFahrenheit.click();

        WebElement currentTempInFahrenheit = getDriver().findElement(
                By.xpath("//span[@class = 'heading']")
        );

        String actualResultCurrentWeatherInFahrenheit =
                currentTempInFahrenheit.getText().substring(currentTempInFahrenheit.getText().length() - 1);

        Assert.assertEquals(actualResultCurrentWeatherInFahrenheit, expectedResultCurrentWeatherInFahrenheit);
        Assert.assertTrue(currentTempInFahrenheit.getText().contains(tempInFahrenheit));
    }

}
