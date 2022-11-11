import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TellenqaTest extends BaseTest {

    @Test
    public void testH2Tag_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);


        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(5000);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFrChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFrChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testGuideMenuOpensGuidePage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideMenuButton = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']")
        );
        guideMenuButton.click();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

    }

    @Test
    public void testCookiesConfirmation() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedCookiesText = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";

        String textButton1 = "Allow all";
        String textButton2 = " Manage cookies ";

        getDriver().get(url);
        Thread.sleep(10000);
        getDriver().manage().window().maximize();

        WebElement cookiesFooterText = getDriver().findElement(By.className("stick-footer-panel__description"));
        String actualCookiesText = cookiesFooterText.getText();

        Assert.assertEquals(expectedCookiesText, actualCookiesText);

        Assert.assertEquals(
                getDriver().findElements(By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")).
                        size(), 2);

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = '"
                                + textButton1 + "']")
                ).isDisplayed()
        );

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = '"
                                + textButton2 + "']")
                ).isDisplayed()
        );
    }
}