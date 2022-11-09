import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MurzinovaTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();


        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testRedirectingToAPIGuidePage() throws InterruptedException {
        String urlBasic = "https://openweathermap.org/";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(urlBasic);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement guideLink = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']"));
        guideLink.click();

        String actualResult = getDriver().getTitle();
        Assert.assertEquals(actualResult,expectedResult);

        expectedResult = "https://openweathermap.org/guide";
        actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testTemperatureInF() throws InterruptedException {
        String urlBasic = "https://openweathermap.org/";
        String expectedResult = "F";

        getDriver().get(urlBasic);
        Thread.sleep(10000);

        WebElement measureUnitLink = getDriver().findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']"));
        measureUnitLink.click();

        WebElement cityTemperatureInF = getDriver().findElement(
                By.xpath("//span[@class='heading'][contains(text(),'F')]"));
        Thread.sleep(2000);

        Assert.assertTrue(cityTemperatureInF.getText().endsWith(expectedResult));
    }
}
