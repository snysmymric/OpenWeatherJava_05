import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

@Ignore
public class ElNov686Test extends BaseTest {
    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text ()= 'Paris, FR ']")
        );
        parisFRChoiceInDropDownMenu.click();

        WebElement h2CityNameHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testTemperatureImperialFahrenheitVerify() throws InterruptedException {
        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement imperialF = getDriver().findElement(By
                .xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']"));

        imperialF.click();
        Thread.sleep(5000);
        WebElement temperatureF = getDriver().findElement(By
                .xpath("//span[@class='heading']"));

        String actualResult = temperatureF.getText();
        actualResult = actualResult.substring(actualResult.length() - 1);

        String expectedResult = "F";

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testVerifyCookiesTextAndTwoButtons() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement cookiesText = getDriver().findElement(By
                .xpath("//p[@class='stick-footer-panel__description']"));
        String actualResult1 = cookiesText.getText();

        WebElement allowAll = getDriver().findElement(By.xpath("//button[text()='Allow all']"));
        Boolean actualResult2 = allowAll.isDisplayed();

        WebElement manageCookies = getDriver().findElement(By
                .xpath("//a[@class='stick-footer-panel__link']"));
        Boolean actualResult3 = manageCookies.isDisplayed();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertTrue(actualResult2);
        Assert.assertTrue(actualResult3);

        }
    @Test
    public void testMenuAPI_Verify30orangeButtons() throws InterruptedException {
        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement menuAPI = getDriver().findElement(By
                .xpath("//div[@id='desktop-menu']//a[normalize-space()='API']"));
        menuAPI.click();
        Thread.sleep(3000);

        List<WebElement> orangeButtons = getDriver().findElements(By
                .xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange')]"));

        Assert.assertEquals(orangeButtons.size(), expectedResult);
        }
    }

