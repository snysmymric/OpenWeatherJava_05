import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.AssertJUnit.assertEquals;

public class IrynaOkunTest extends BaseTest {

    private static final String URL = "https://openweathermap.org/";

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(URL);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement searchParisFRChoiceDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        searchParisFRChoiceDropdownMenu.click();

        WebElement h2CityCountryNameHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(5000);

        String actualResult = h2CityCountryNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSubmit30OrangeButtons() throws InterruptedException {
        String expectedResult = "30";

        getDriver().get(URL);
        Thread.sleep(10000);
        getDriver().findElement(By.xpath("//div/ul/li/a[@href='/api']")).click();

        String actualResult = String.valueOf(getDriver().findElements(
                By.xpath("//a[contains(@class, 'orange')]")
        ).size());

        assertEquals(actualResult, expectedResult);
    }

}
