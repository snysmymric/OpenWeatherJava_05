import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class OlgaKorolenkoTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searthCityField = getDriver().findElement(
                By.xpath("//div[@id ='weather-widget']//input[@placeholder= 'Search city']"));
        searthCityField.click();
        searthCityField.sendKeys(cityName);

        WebElement searthButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searthButton.click();
        Thread.sleep(1000);

        WebElement parisFRChouiceIdDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() ='Paris, FR ']")
        );
        parisFRChouiceIdDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();
        Assert.assertEquals(actualResult,expectedResult);

    }
}
