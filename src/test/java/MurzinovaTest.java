import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultURL = "https://openweathermap.org/guide";

        getDriver().get(urlBasic);
        Thread.sleep(10000);

        WebElement guideLink = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']"));
        guideLink.click();

        String actualResultTitle = getDriver().getTitle();
        String actualResultURL = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultTitle,expectedResultTitle);
        Assert.assertEquals(actualResultURL,expectedResultURL);
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

    @Test
    public void testCookiesPanelAndButtons() throws InterruptedException {
        String urlBasic = "https://openweathermap.org/";
        String expectedResultPanelText = "We use cookies which are essential for the site to work. We also use " +
                "non-essential cookies to help us improve our services. Any data collected is anonymised. " +
                "You can allow all cookies or manage them individually.";
        String expectedResultAllowAllButton = "Allow all";
        String expectedResultManageCookiesButton = "Manage cookies";

        getDriver().get(urlBasic);
        Thread.sleep(10000);

        WebElement cookiesTextPanel = getDriver().findElement(By.className("stick-footer-panel__description"));
        Assert.assertTrue(cookiesTextPanel.isDisplayed());

        String actualResultPanelText = cookiesTextPanel.getText();
        Assert.assertEquals(actualResultPanelText,expectedResultPanelText);

        Assert.assertEquals(
                getDriver().findElements(By.xpath("//div[@class='stick-footer-panel__btn-container']/*"))
                        .size(),2);

        WebElement cookiesAllowAllButton = getDriver().findElement(
                By.xpath("//button[@class='stick-footer-panel__link']"));

        String actualResultAllowAllButton = cookiesAllowAllButton.getText();
        Assert.assertEquals(actualResultAllowAllButton,expectedResultAllowAllButton);

        WebElement cookiesManageCookiesButton = getDriver().findElement(
                By.xpath("//a[@class='stick-footer-panel__link']"));

        String actualResultManageCookiesButton = cookiesManageCookiesButton.getText();
        Assert.assertEquals(actualResultManageCookiesButton,expectedResultManageCookiesButton);
    }
}
