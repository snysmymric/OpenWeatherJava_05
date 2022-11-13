import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


@Ignore
public class IvanRamin7Test extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchCity = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCity.click();
        searchCity.sendKeys(cityName);
        WebElement searchButton = getDriver().findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(3000);
        WebElement parisFRChoiceFromDropdownMenu = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//ul[@class = 'search-dropdown-menu']//span[text() = 'Paris, FR ']")
        );
        parisFRChoiceFromDropdownMenu.click();

        WebElement h2CityNameHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(3000);
        String actualResult = h2CityNameHeader.getText();


        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmFarenheits() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String fahrenheitExpectedResult = "°F";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement fahrenheitButton = getDriver().findElement(
                By.xpath("//div[@class='page-container']//div[text() = 'Imperial: °F, mph']")
        );
        fahrenheitButton.click();
        Thread.sleep(2000);
        WebElement celciusFahrenheits = getDriver().findElement(
                By.xpath("//div[@class='section-content']//span[@class = 'heading']")
        );
        boolean actualResult = celciusFahrenheits.getText().contains(fahrenheitExpectedResult);

        Assert.assertTrue(actualResult);
    }
}