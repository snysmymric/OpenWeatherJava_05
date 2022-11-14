import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class KsutitovaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }


    @Ignore
    @Test
    public void testH2TextOpenWeatherMapInGuideLink() throws InterruptedException {

        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        getDriver().get(BASE_URL);
        Thread.sleep(10000);

        WebElement menuGuide = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']")
        );

        menuGuide.click();

        String urlGuede = getDriver().getCurrentUrl();
        String title = getDriver().getTitle();

        Assert.assertEquals(urlGuede, expectedResultUrl);
        Assert.assertEquals(title, expectedResultTitle);
    }


    @Ignore
    @Test
    public void testConfirmTemperatureFaringate() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement imperialF = getDriver().findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
        );
        Thread.sleep(5000);
        imperialF.click();

        WebElement faringate = getDriver().findElement(
                By.xpath("//div[@class = 'current-temp']/span")
        );

        Thread.sleep(5000);
        String actualResult = faringate.getText();

        Assert.assertTrue(actualResult.contains("F"));

    }

}
