import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AnnaAbgTest extends BaseTest {

    private static final String BASE_URL = "https://openweathermap.org/";

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(BASE_URL);
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
        Thread.sleep(1000);

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testThirtyOrangeButtons() throws InterruptedException {

        int expectedResult = 30;

        getDriver().get(BASE_URL);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//a[@href='/api']")).click();
        Thread.sleep(5000);

        int countButtons = getDriver().findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        Assert.assertEquals(countButtons, expectedResult);
    }
}
