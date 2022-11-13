import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
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
        Thread.sleep(7000);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        Thread.sleep(7000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader  = getDriver().findElement(By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(7000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void test_FahrenheitUnits_IfChooseF_11_02() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String menuOption = "Imperial: °F, mph";
        boolean expectedResult = true;
        String fTempSymbol = "F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement findTempFSwitcher = getDriver().findElement(By.xpath(
                "//div[contains(text(),'"+menuOption+"')]"));
        findTempFSwitcher.click();

        Thread.sleep(5000);
        WebElement findTempIndicator = getDriver().findElement(By.xpath(
                "//div[@class='current-temp']/span"));

        Thread.sleep(3000);
        String tempIndidcatorF = findTempIndicator.getText();
        String actualResult = tempIndidcatorF.substring(tempIndidcatorF.length() - 2);

        Assert.assertTrue(findTempIndicator.getText().contains(fTempSymbol));
        Assert.assertTrue(expectedResult, actualResult);
    }

    @Test
    public void test_CheckCurrentURLonClickLogo() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement logo = getDriver().findElement(
                By.xpath("//a[@href]//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        logo.click();
        Thread.sleep(1000);

        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
    }

}
