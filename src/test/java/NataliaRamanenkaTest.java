import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class NataliaRamanenkaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_GUIDE_BUTTON = By.xpath(
            "//div[@id = 'desktop-menu']/ul/li/a[@href = '/guide']");
    final static By SEARCH_HEAD_OF_PAGE_GUIDE = By.xpath(
            "//div[@class='col-sm-7']/h1");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }
    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private String actualUrl() {

        return getDriver().getCurrentUrl();
    }

    private String actualTitle() {

        return getDriver().getTitle();
    }

    @Test
    public void testGuideLinkAndTitle() throws InterruptedException {
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_GUIDE_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_HEAD_OF_PAGE_GUIDE, getWait5());

        Assert.assertEquals(actualUrl(), expectedResultUrl);
        Assert.assertEquals(actualTitle(), expectedResultTitle);
    }

}
