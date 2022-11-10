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
    public void guidePageUrlTitleCheck() throws InterruptedException {

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
    public void test_TC_11_02_fahrenheitSwitcher() throws InterruptedException {

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
}