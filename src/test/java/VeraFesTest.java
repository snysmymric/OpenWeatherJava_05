import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VeraFesTest extends BaseTest {

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
        Thread.sleep(10000);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        Thread.sleep(10000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader  = getDriver().findElement(By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(10000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void test_FahrenheitUnits_IfChooseF_11_02() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String menuOption = "Imperial: °F, mph";
        boolean expectedResult = true;
        String fTempSymbol = "F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement findTempFSwitcher = driver.findElement(By.xpath(
                "//div[@class='switch-container']//div[contains(text(), '" +menuOption+ "')]"));
        findTempFSwitcher.click();

        WebElement findTempIndicator = driver.findElement(By.xpath(
                "//div[@class='current-temp']/span"));

        String tempIndidcatorF = findTempIndicator.getText();
        String actualResult = tempIndidcatorF.substring(tempIndidcatorF.length() - 2);

        Assert.assertTrue(findTempIndicator.getText().contains(fTempSymbol));
        Assert.assertTrue(expectedResult, actualResult);

        driver.quit();
    }
}
