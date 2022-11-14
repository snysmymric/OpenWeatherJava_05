import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class KsutitovaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By GUEDE = By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }


    @Test
    public void testH2TextOpenWeatherMapInGuideLink() {

        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(GUEDE, getWait5());

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
