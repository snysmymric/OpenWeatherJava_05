import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NstzyaTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        getDriver().manage().window().maximize();
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

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(//ищем элемент страницы по заранее построенному XPath
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        Thread.sleep(2000);

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Ignore
    @Test
    public void testUrlAndMetaTitle_WhenOpenGuidePage() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement barButtonGuide = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//ul/li/a[@href='/guide']")
        );
        barButtonGuide.click();

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

        expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }


}
