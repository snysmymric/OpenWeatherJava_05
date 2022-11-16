import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KathyGuTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By GUIDE_TAB = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");
    final static By LOGO = By.className("logo");

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
    private void click (By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
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
    @Test

    public void testClickOnLogoRedirectUserToStartPage() {
        String startPage = "https://openweathermap.org/";

        openBaseUrl();
        waitForGreyFrameDisappeared();
        click(GUIDE_TAB, getWait5());
        click(LOGO, getWait5());

        Assert.assertEquals(getCurrentUrl(),startPage);
    }
}































