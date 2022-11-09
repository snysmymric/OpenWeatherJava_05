import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VadimKachanovichTest extends BaseTest {
    @Test
    public void testWhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        getDriver().manage().window().maximize();
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
        Thread.sleep(2000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(3000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckTitleName() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedGuideLink = "https://openweathermap.org/guide";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement guideTab = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[@href='/guide']")
        );

        guideTab.click();
        Thread.sleep(2000);
        String actualGuideLink = getDriver().getCurrentUrl();
        Thread.sleep(5000);
        String actualTitle = getDriver().getTitle();
        Assert.assertEquals(actualGuideLink, expectedGuideLink);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testFooterCookie() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultFooterCookie = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedAllowAllButton = "Allow all";
        String expectedManageCookiesButton = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(8000);
        WebElement footerCookie = getDriver().findElement(
                By.xpath("//div[@id= 'stick-footer-panel']//p[@class = 'stick-footer-panel__description']")
        );
        WebElement buttonAllowAll = getDriver().findElement(By.xpath("//div[@id= 'stick-footer-panel']//div/button"));
        WebElement buttonManageCookies = getDriver().findElement(By.xpath("//div[@id= 'stick-footer-panel']//div/a"));

        String аctualAllowAllButton = buttonAllowAll.getText();
        String аctualManageCookiesButton = buttonManageCookies.getText();
        String actualResultFooterCookie = footerCookie.getText();

        Assert.assertEquals(actualResultFooterCookie, expectedResultFooterCookie);
        Assert.assertEquals(аctualAllowAllButton, expectedAllowAllButton);
        Assert.assertEquals(аctualManageCookiesButton, expectedManageCookiesButton);
    }
}
