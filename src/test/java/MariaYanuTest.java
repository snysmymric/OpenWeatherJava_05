import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MariaYanuTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(2000);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(7000);

        WebElement parisFRChoiceInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ' ]")
        );
        parisFRChoiceInDropDownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@class = 'section-content']//h2")
        );
        Thread.sleep(7000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);

    }

    @Test
    public void testGuideUrlAndHeader() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideElementInMenu = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")
        );
        guideElementInMenu.click();
        Thread.sleep(10000);
        String actualResultUrl = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        Assert.assertEquals(actualResultUrl,expectedResultUrl);
        Assert.assertEquals(actualResultTitle,expectedResultTitle);
    }
}
