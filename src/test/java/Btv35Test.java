import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class
Btv35Test extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(7000);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(2000);

        WebElement parisFRInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']"));
        parisFRInDropDownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(7000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Ignore
    @Test
    public void testGuideMenuWithTitle_OpenWeatherMapAPIGuide() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String guideUrl = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement menuGuide = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a [@href = '/guide']"));
        menuGuide.click();
        Thread.sleep(7000);

        getDriver().getCurrentUrl();

        Assert.assertEquals(guideUrl, getDriver().getCurrentUrl());

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testSwitchingTemperatureToFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement switchToFahrenheit = getDriver().findElement(
                By.xpath("//div[@id ='weather-widget']//div[text() = 'Imperial: °F, mph']"));
        switchToFahrenheit.click();
        Thread.sleep(7000);

        WebElement temperatureInFahrenheit = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//span[contains (text(),'F')]"));

        String temperatureInFahrenheitText = temperatureInFahrenheit.getText();
        String actualResult = temperatureInFahrenheitText.substring(temperatureInFahrenheitText.length() - 1);

        Assert.assertEquals(actualResult,expectedResult);

        Assert.assertTrue(temperatureInFahrenheit.getText().contains(expectedResult));
    }
    @Test
    public void testTwoButtonsOnTheCookiesMessage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cookies = "We use cookies which are essential for the site to work. We also use "
                + "non-essential cookies to help us improve our services. Any data collected is "
                + "anonymised. You can allow all cookies or manage them individually.";
        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement cookiesPanel = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']" +
                        "//p[@class='stick-footer-panel__description']"));
        Assert.assertEquals(cookies, cookiesPanel.getText());

        WebElement buttonAllowAll = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//button[@class='stick-footer-panel__link']"));
        String actualResultAllowAllButton = buttonAllowAll.getText();

        WebElement buttonManageCookies = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//a[text()=' Manage cookies '] "));
        String actualResultButtonManageCookies = buttonManageCookies.getText();

        Assert.assertEquals(actualResultAllowAllButton, expectedResultAllowAll);
        Assert.assertEquals(actualResultButtonManageCookies, expectedResultManageCookies);
    }
}
