import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class MurzinovaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By GUIDE_LINK = By.xpath("//div[@id='desktop-menu']//a[@href='/guide']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }
    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Test
    public void testRedirectingToAPIGuidePage() {
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultURL = "https://openweathermap.org/guide";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(GUIDE_LINK, getWait5());

        String actualResultTitle = getDriver().getTitle();
        String actualResultURL = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertEquals(actualResultURL, expectedResultURL);
    }
}