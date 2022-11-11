import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class YuliaZhukoTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName="Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(7000);
        WebElement searchSityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchSityField.click();
        searchSityField.sendKeys(cityName);
        Thread.sleep(2000);
        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(3000);
        String actualResult = h2CityCountryHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testOpenGuideCheckTitle() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        getDriver().get(url);
        Thread.sleep(10000);
        String actualUrl = "https://openweathermap.org/guide";

        WebElement searchGuideField = getDriver().findElement(
                By.xpath("//div[@id= 'desktop-menu']//a[@href='/guide']")
        );
        searchGuideField.click();
        Thread.sleep(5000);

        String expectedUrl = getDriver().getCurrentUrl();
        assertEquals(actualUrl,expectedUrl);
        String actualTitle = getDriver().getTitle();
        Assert.assertEquals(actualTitle,expectTitle);
    }

}
