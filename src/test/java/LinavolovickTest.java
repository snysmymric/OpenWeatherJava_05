import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class LinavolovickTest extends BaseTest {

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
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryElement = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryElement.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testGuideUrlAndTitle() throws InterruptedException {

        String url = "https://openweathermap.org/guide";
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideElementMenu = getDriver().findElement(
                By.xpath("//a[@href='/guide']")
        );

        guideElementMenu.click();

        String actualResultUrl = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }

    @Test
    public void testCheckImperialForFahrenheit() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement menuImperial = getDriver().findElement(
                By.xpath("//div[@class='switch-container']/div[3]")
        );
        menuImperial.click();

        WebElement tempF = getDriver().findElement(
                By.xpath("//span[@class='heading']")
        );
        Thread.sleep(2000);

        String tempInF = tempF.getText();
        String actualResult = tempInF.substring((tempInF.length()-2));

        Assert.assertEquals(actualResult, expectedResult);
    }
}
