import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class YanDadaelovTest extends BaseTest {

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
        Thread.sleep(2000);


        WebElement parisFRChoiceDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//li/span[text()='Paris, FR ']")
        );
        parisFRChoiceDropDownMenu.click();
        Thread.sleep(2000);


        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }


    @Test
    public void testUrlAndTitleOnGuidePage() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult_guideUrl = "https://openweathermap.org/guide";
        String expectedResult_guideUrlTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideLink = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")
        );

        guideLink.click();
        Thread.sleep(2000);

        String actualResult_guideUrl = getDriver().getCurrentUrl();
        String actualResult_guideUrlTitle = getDriver().getTitle();

        Assert.assertEquals(actualResult_guideUrl, expectedResult_guideUrl);
        Assert.assertEquals(actualResult_guideUrlTitle, expectedResult_guideUrlTitle);
    }


    @Test
    public void testTemperatureFahrenheitSwitcherOnMainPage() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement fahrenheitSwitcher = getDriver().findElement(
                By.xpath("//div[@class='switch-container']/div[text()='Imperial: °F, mph']")
        );
        fahrenheitSwitcher.click();
        Thread.sleep(1000);

        String temperature = getDriver().findElement(
                        By.xpath("//div[@class='section-content']//span[@class='heading']"))
                .getText();

        String actualResult;

        if (temperature.isBlank()) {
            actualResult = temperature;
        } else {
            actualResult = temperature.substring(temperature.length() - 1);
        }

        Assert.assertEquals(actualResult, expectedResult);
    }


    @Test
    public void testCookiesPanelTextAndButtonsOnMainPage() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult_allowAllButtonText = "Allow all";
        String expectedResult_manageCookiesLinkText = "Manage cookies";
        String expectedResult_warningText = "We use cookies which are essential for the site to work."
                + " We also use non-essential cookies to help us improve our services."
                + " Any data collected is anonymised. You can allow all cookies or manage them individually.";

        getDriver().get(url);
        Thread.sleep(10000);

        String actualResult_warningText = getDriver().findElement(
                        By.xpath("//p[@class='stick-footer-panel__description']"))
                .getText();

        String actualResult_allowAllButtonText = getDriver().findElement(
                        By.xpath("//div[@class='stick-footer-panel__btn-container']/button"))
                .getText();

        String actualResult_manageCookiesLinkText = getDriver().findElement(
                        By.xpath("//div[@class='stick-footer-panel__btn-container']/a"))
                .getText();

        Assert.assertEquals(actualResult_manageCookiesLinkText, expectedResult_manageCookiesLinkText);
        Assert.assertEquals(actualResult_allowAllButtonText, expectedResult_allowAllButtonText);
        Assert.assertEquals(actualResult_warningText, expectedResult_warningText);
    }
}
