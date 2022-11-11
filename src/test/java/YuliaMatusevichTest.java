import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class YuliaMatusevichTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(3000);

        WebElement parisFrChoiceDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']"));
        parisFrChoiceDropdownMenu.click();

        WebElement h2CityNameHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(3000);

        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testFahrenheitUnits_WhehChooseF() throws InterruptedException {
        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement findFahrenheitSwitcher = getDriver().findElement(
                By.xpath("//div[@class = 'switch-container']//div[text() = 'Imperial: °F, mph']"));
        findFahrenheitSwitcher.click();

        WebElement findTempIndicator = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//span[@class = 'heading']"));

        Assert.assertTrue(findTempIndicator.getText().contains("F"));
    }

    @Test
    public void testDownpageBannerTextAndButtons_WhenBasicURLLoaded() throws InterruptedException {
        String url = "https://openweathermap.org/";
        final String expectedBannerText = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        final int expectedQuantityOfButtons = 2;
        final String expectedTextAllowButton = "Allow all";
        final String expectedTextCookiesButton = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(10000);

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//p[@class = 'stick-footer-panel__description']")).getText(), expectedBannerText);
        Assert.assertEquals(getDriver().findElements(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")).size(), expectedQuantityOfButtons);
        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//button")).getText(), expectedTextAllowButton);
        Assert.assertEquals(getDriver().findElement(
                By.xpath("//a[@href = '/cookies-settings']")).getText(), expectedTextCookiesButton);
    }
}


