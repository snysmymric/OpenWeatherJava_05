import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Kater1naGTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry () throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//input[@placeholder = 'Search city']")
        );
        Thread.sleep(10000);
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(5000);

        WebElement choiceParisFr = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']")
        );
        choiceParisFr.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testRedirectToGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String reUrl = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);

        WebElement searchGuide = getDriver().findElement(
                By.xpath("//a[@href='/guide']")
        );
        Thread.sleep(10000);
        searchGuide.click();

        String currentUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(currentUrl, reUrl);

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testImperialF() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String tempButton = "Imperial: °F, mph";
        String expectedResult = "°F";

        getDriver().get(url);

        WebElement imperialF = getDriver().findElement(
                By.xpath("//*[@id='weather-widget']/div[1]/div/div/div[1]/div[2]/div[3]")
        );
        Thread.sleep(10000);
        imperialF.click();

        WebElement tempF = getDriver().findElement(
                By.xpath("//*[@id='weather-widget']/div[2]/div[1]/div[1]/div[2]/div[1]/span")
        );

        String letterF = tempF.getText();

        String actualResult;
        if(letterF.length() == 4){
            actualResult = letterF.substring(2);
        } else if (letterF.length() > 4) {
            actualResult = letterF.substring(3);
        }else {
            actualResult = letterF.substring(1);
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testBelowButtonsAndText() throws InterruptedException {

        String url = "https://openweathermap.org/";
        int expectedButtons = 2;
        String expectedResult = "We use cookies"
                + " which are essential for the site to work. We also use non-essential cookies to help us"
                + " improve our services. Any data collected is anonymised."
                + " You can allow all cookies or manage them individually.";

        getDriver().get(url);

        WebElement panelOfCookies = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']")
        );

        WebElement textElementOfPanel = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']/div/div/div/div/p")
        );

        String actualResult = textElementOfPanel.getText();

        int buttonsOfPanel  =  getDriver().findElements(
                By.tagName("button")
        ).size();

        Assert.assertTrue(panelOfCookies.isDisplayed());

        Assert.assertEquals(actualResult, expectedResult);

        Assert.assertEquals(buttonsOfPanel, expectedButtons);

    }
}
