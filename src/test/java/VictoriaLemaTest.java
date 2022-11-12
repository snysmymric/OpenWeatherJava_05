import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VictoriaLemaTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(3000);
        WebElement parisFRChoiceDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        parisFRChoiceDropDownMenu.click();
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(3000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPageTitleAndPageURL_WhenGoingToPageTitle() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultURL = "https://openweathermap.org/guide";
        String expectedResultPageTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']")
        );
        guideButton.click();
        Thread.sleep(5000);
        String actualResultEndPoint = getDriver().getCurrentUrl();
        String actualResultPageTitle = getDriver().getTitle();

        Assert.assertEquals(actualResultPageTitle, expectedResultPageTitle);
        Assert.assertEquals(actualResultEndPoint, expectedResultURL);
    }

}
