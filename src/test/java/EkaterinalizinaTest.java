import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EkaterinalizinaTest extends BaseTest {

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

        Thread.sleep(10000);

        WebElement parisFrhoiseInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFrhoiseInDropDownMenu.click();

        WebElement h2CityCountHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(10000);

        String actualResult = h2CityCountHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTemperatureChangedInToF() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String fTempSymbol = "°F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement ButtonTemperatureFarenheit = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']"));
        ButtonTemperatureFarenheit.click();

        Thread.sleep(10000);

        WebElement tempF = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//span[@class ='heading']"));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));
    }

    @Test
    public void testTwoButtonsCookies() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);

        Thread.sleep(10000);
        String expectedResult1 = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services." +
                " Any data collected is anonymised. You can allow all cookies" +
                " or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        WebElement cookiesText = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//p[contains(text (), 'We use cookies which')]"));
        String actualResult1 = cookiesText.getText();

        WebElement cookiesButton1 = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//button[text() = 'Allow all']"));
        WebElement cookiesButton2 = getDriver().findElement
                (By.xpath("//div[@id = 'stick-footer-panel']//a[text() = ' Manage cookies ']"));
        String actualResult2 = cookiesButton1.getText();
        String actualResult3 = cookiesButton2.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }
}
