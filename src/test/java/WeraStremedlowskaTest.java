import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Ignore
public class WeraStremedlowskaTest extends BaseTest {

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
    public void testLogoClick() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        getDriver().findElement(
                By.xpath("//ul/li/a/img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);

    }

    @Test
    public void testTempFahrenheitChangesToCelsius(){
        String url = "https://openweathermap.org/";
        String expectedResult = "°C";

        getDriver().get(url);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        getDriver().findElement(
                        By.xpath("//div[@id='weather-widget']/div[1]/div/div/div[1]/div[2]/div[text()='Imperial: °F, mph']"))
                .click();

        getDriver().findElement(By.xpath("//div[@id='weather-widget']/div/div/div/div/div/div[2]")).click();

        WebElement celsius = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//div[2]/div/div/div/div/span")
        );


        String actualResult = celsius.getText();
        actualResult = actualResult.substring(actualResult.length() - 2);

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testMenuSupportHasThreeSubmenus()  {
        String baseUrl = "https://openweathermap.org/";
        String expectedResultSubmenu1 = "FAQ";
        String expectedResultSubmenu2 = "How to start";
        String expectedResultSubmenu3 = "Ask a question";

        getDriver().get(baseUrl);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        getDriver().findElement(By.id("support-dropdown")).click();

        Assert.assertEquals(getDriver().findElements(
                By.xpath("//ul[@id='support-dropdown-menu']/li")).size(), 3);

        String actualResult1 = getDriver().findElement(By.xpath("//body/nav/ul[1]/div/ul/li[12]/ul/li[1]/a")).getText();
        String actualResult2 = getDriver().findElement(By.xpath("//body/nav/ul[1]/div/ul/li[12]/ul/li[2]/a")).getText();
        String actualResult3 = getDriver().findElement(By.xpath("//body/nav/ul[1]/div/ul/li[12]/ul/li[3]/a")).getText();

        Assert.assertEquals(actualResult1, expectedResultSubmenu1);
        Assert.assertEquals(actualResult2, expectedResultSubmenu2);
        Assert.assertEquals(actualResult3, expectedResultSubmenu3);

    }
}
