import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class AlinaIvanovaTest extends BaseTest {

    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {

        String baseURL = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(baseURL);
        Thread.sleep(10000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder= 'Search city']"
                )
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(2000);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']"
                )
        );
        searchButton.click();
        Thread.sleep(3000);

        WebElement parisFRChooseInDropdownMenu = getDriver().findElement(By.xpath(
                        "//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"
                )
        );
        parisFRChooseInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2"
                )
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testWhenSearchingGuideMenu() throws InterruptedException {

        String baseURL = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(baseURL);
        Thread.sleep(10000);
        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id= 'desktop-menu']//a[@href= '/guide']"
                )
        );
        guideButton.click();
        Thread.sleep(2000);

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}
