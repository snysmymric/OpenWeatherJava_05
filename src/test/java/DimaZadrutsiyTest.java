import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class DimaZadrutsiyTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCitiCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);


        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type ='submit']"));
        searchButton.click();

        Thread.sleep(3000);
        WebElement firstChoiesInDropdownMenu = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//ul[@class='search-dropdown-menu']/li/span[text()"
                        + "='Paris, FR ']"));
        firstChoiesInDropdownMenu.click();

        Thread.sleep(4000);
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(3000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPageReload() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "Loading";

        getDriver().get(url);

        Thread.sleep(10000);
        WebElement clickOnTheLogo = getDriver().findElement(By.xpath("//li[@class='logo']"));
        clickOnTheLogo.click();

        WebElement loading = getDriver().findElement(By.xpath("//div[@aria-label='Loading']"));

        String actualResult = loading.getAttribute("aria-label");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIconCurrentLocation() throws InterruptedException {

        String url = "https://openweathermap.org/";

        String cityName = "Norway";
        String expectedResult = "London, GB";

        getDriver().get(url);

        Thread.sleep(10000);
        WebElement findCity = getDriver().findElement(By.xpath("//div//input[@placeholder='Search city']"));
        findCity.click();
        findCity.sendKeys(cityName);
        findCity.sendKeys(Keys.ENTER);

        Thread.sleep(4000);
        WebElement chooseCity = getDriver().findElement(By.xpath("//ul//span[text()='45.787, -87.904']"));
        chooseCity.click();

        Thread.sleep(4000);
        WebElement pressCurrentLocation = getDriver().findElement(By.xpath("//div[@class='control-el']"));
        pressCurrentLocation.click();

        Thread.sleep(7000);
        WebElement actualLocation = getDriver().findElement(By.xpath("//h2[@data-v-3e6e9f12]"));

        String actualResult = actualLocation.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testOrangeBatton() throws InterruptedException {

        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        getDriver().get(url);

        Thread.sleep(10000);
        WebElement linkAPI = getDriver().findElement(By.xpath("//a[@href='/api']"));
        linkAPI.click();

        int actualResult = getDriver().findElements(By.xpath("//a[contains(@class, 'orange')]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSearchLineCityRome() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        String find = "find";

        String expectedResult = "Rome";

        getDriver().get(url);

        Thread.sleep(10000);
        WebElement searchLine = getDriver().findElement(By.name("q"));
        searchLine.click();
        searchLine.sendKeys(cityName);
        searchLine.sendKeys(Keys.ENTER);

        boolean actualResult = getDriver().getCurrentUrl().contains(find) && getDriver().getCurrentUrl().contains(cityName);

        WebElement cityInTheSearchBar = getDriver().findElement(By.id("search_str"));

        String actualResult2 = cityInTheSearchBar.getAttribute("value");

        Assert.assertTrue(actualResult);
        Assert.assertEquals(actualResult2, expectedResult);
    }
}
