import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AllaIgTest extends BaseTest {

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
        Thread.sleep(5000);
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
    }
    @Test
    public void testUrlAndTitle_WhenGuidePress() throws InterruptedException {

        String url = "https://openweathermap.org/";

        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);

        Thread.sleep(10000);

        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li/a[@href = '/guide']")
        );
        guideButton.click();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);

        String expectedCurrentUrl = "https://openweathermap.org/guide";

        String actualCurrentUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testCookiesPanelOpens_WhenOpenWebsite() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);

        Thread.sleep(10000);

        WebElement cookiesPanel = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']/div/div/div/div/p")
        );
        String actualResult = cookiesPanel.getText();
        String expectedResult = ("We use cookies which are essential for the site to work. We also use non-essential" +
                " cookies" +
                " to help us improve our services. Any data collected is anonymised. You can allow all cookies or " +
                "manage them individually.");
        Assert.assertEquals(actualResult, expectedResult);

        WebElement cookiesButtonsAll = getDriver().findElement(
                By.xpath("//*[@id= 'stick-footer-panel']/div/div/div/div/div/button")
        );
        String actualNameButton = cookiesButtonsAll.getText();
        String expectedNameButton = ("Allow all");
        Assert.assertEquals(actualNameButton, expectedNameButton);

        WebElement cookiesButtonsManage = getDriver().findElement(
                By.xpath("//*[@id='stick-footer-panel']/div/div/div/div/div/a")
        );

        String actualNameButton1 = cookiesButtonsManage.getText();
        String expectedNameButton1 = ("Manage cookies");
        Assert.assertEquals(actualNameButton1, expectedNameButton1);
    }
}
