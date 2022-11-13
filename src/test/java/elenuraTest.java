import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

@Ignore
public class elenuraTest extends BaseTest {
    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@ placeholder =  'Search city']")
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

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);

        getDriver().quit();
    }

    @Test

    public void testTitleOpenWeatherMapAPIGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResultOpenPage = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement Title = getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='/guide']")
        );
        Title.click();
        Thread.sleep(3000);

        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResultOpenPage);
        Assert.assertEquals(getDriver().getTitle(),expectedResultTitle);
    }

    @Test

    public void testUnitOfMeasureFahrenheit () throws InterruptedException {
        String url = "https://openweathermap.org/";
        boolean expectedResultFahrenheit = true;

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement tempUnit = getDriver().findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
        );
        tempUnit.click();
        Thread.sleep(5000);

        WebElement tempUnitHeading = getDriver().findElement(
                By.xpath("//div[@class='current-temp']/span")
        );
        Assert.assertEquals(tempUnitHeading.getText().contains("°F"),expectedResultFahrenheit);
    }

    @Test
    public void testNumberOfOrangeButtons() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(10000);

        WebElement menuAPI = getDriver().findElement(
                By.xpath("//li/a[@href='/api']")
        );
        menuAPI.click();
        Thread.sleep(2000);

        List<WebElement> orangeButtons = getDriver().findElements(
                By.xpath("//a[@type='button' and contains(@class,'orange') or contains(@class, 'btn-orange')]")
        );

        Assert.assertEquals(orangeButtons.size(),30);
    }

    @Test
    public void  testLogoNotChangeURL() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(10000);

        WebElement logo = getDriver().findElement(
                By.xpath("//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        logo.click();
        Thread.sleep(5000);

        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResult);
    }

    @Test
    public void testCookiesPanel_Footer_WhenOpenWebsite() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultPanelText = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. Any data collected " +
                "is anonymised. You can allow all cookies or manage them individually.";
        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement cookies = getDriver().findElement(
                By.xpath("//div/p[@class='stick-footer-panel__description']")
        );
        Assert.assertEquals(cookies.getText(), expectedResultPanelText);

        WebElement allowAllButton = getDriver().findElement(
                By.xpath("//div/button[@type='button']")
        );
        Assert.assertEquals(allowAllButton.getText(), expectedResultAllowAll);

        WebElement manageCookies = getDriver().findElement(
                By.xpath("//div/a[@href='/cookies-settings']")
        );
        Assert.assertEquals(manageCookies.getText(), expectedResultManageCookies);
    }

    @Test
    public void testWeatherInYourCityField() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        boolean expectedResult = true;
        String expectedResult2 = "Rome";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement weatherInYourCityField = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//input[@placeholder='Weather in your city']")
        );
        weatherInYourCityField.click();
        weatherInYourCityField.sendKeys(cityName);
        weatherInYourCityField.sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().getCurrentUrl().contains("find"), expectedResult);

        WebElement searchField = getDriver().findElement(
                By.xpath("//div[@class='form-group']/input[@class='form-control border-color col-sm-12']")
        );
        Assert.assertEquals(searchField.getAttribute("value"), expectedResult2);
    }
}
