import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class DimaZadrutsiyTest extends BaseTest {

    @Ignore
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
    @Ignore
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
}
