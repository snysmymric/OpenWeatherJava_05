import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KathyGuTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    private void openBaseUrl() {
        getDriver().get(BASE_URL);
    }
    private void waitForGreyFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
            By.className("owm-loader-container")));
    }
    private String getCurrentUrl() {
       return getDriver().getCurrentUrl();
    }
    private String getTitle() {
        return getDriver().getTitle();
    }
    @Test

    public void testStartPageTitleAndURL_WhenOpenBaseUrl() {
        String expectedTitle = "Сurrent weather and forecast - OpenWeatherMap";
        String expectedUrl = "https://openweathermap.org/";

        openBaseUrl();
        waitForGreyFrameDisappeared();

        Assert.assertEquals(getTitle(), expectedTitle);
        Assert.assertEquals(getCurrentUrl(), expectedUrl);
    }
}

























