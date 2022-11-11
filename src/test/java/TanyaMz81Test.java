import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TanyaMz81Test extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);

        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(By.xpath
                ("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(2000);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(2000);

        WebElement parisFRChoiceInDropDownMenu = getDriver().findElement(By.xpath(
                "//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']"));
        parisFRChoiceInDropDownMenu.click();
        Thread.sleep(2000);

        WebElement h2CityCountryHeader = getDriver().findElement(By.xpath("//div[@id='weather-widget']//h2"));
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTheLinkOfThePageGuide() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String titleExpectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchGuideButton = getDriver().findElement(By.xpath(
                "//div[@id='desktop-menu']//ul/li/a[@href='/guide']"));
        searchGuideButton.click();

        WebElement pageTitle = getDriver().findElement(By.xpath("//html/head/title"));
        String titleActualResult = getDriver().getTitle();
        Assert.assertEquals(titleActualResult, titleExpectedResult);

        String linkActualResult = getDriver().getCurrentUrl();
        String linkExpectedResult = "https://openweathermap.org/guide";
        Assert.assertEquals(linkActualResult, linkExpectedResult);
    }
    @Test
    public void testTheTemperatureFarenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement imperialButton = getDriver().findElement(By.xpath(
                "//div[@class = 'switch-container']/div[3]"));
        imperialButton.click();
        Thread.sleep(2000);

        WebElement farSign = getDriver().findElement(By.xpath(
                "//div[@class='current-temp']/span"));
        Thread.sleep(2000);

        String actualResult = farSign.getText();
        boolean bool1 = actualResult.endsWith("F");
        Assert.assertTrue(bool1);
    }

    @Test
    public void testCookiesMessage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also "
                + "use non-essential cookies to help us improve our services. Any data collected is "
                + "anonymised. You can allow all cookies or manage them individually.";
        String allowAllExpectedResult = "Allow all";
        String manageCookiesExpectedResult = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement cookiesField = getDriver().findElement(By.xpath(
                "//div[@class='stick-footer-panel']/div/div/div/p")
        );
        String actualResult = cookiesField.getText();
        Assert.assertEquals(actualResult, expectedResult);

        WebElement allowAllButton = getDriver().findElement(By.xpath("//button[@type = 'button']")
        );
        String allowAllActualResult = allowAllButton.getText();
        Assert.assertEquals(allowAllActualResult, allowAllExpectedResult);
        Thread.sleep(2000);

        WebElement manageCookiesButton = getDriver().findElement(By.xpath("//a[@href='/cookies-settings']")
        );
        String manageCookiesActualResult = manageCookiesButton.getText();
        Assert.assertEquals(manageCookiesExpectedResult, manageCookiesActualResult);
    }
}
