import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class AllaIgTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By MENU_GUIDE = By.xpath("//div/ul//li/a[@href='/guide']");
    final static By GUIDE_TITLE = By.className("breadcrumb-title");

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

    @Ignore
    @Test
    public void testUrlAndTitle_WhenGuidePress() throws InterruptedException {

        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "Guide";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(MENU_GUIDE, getWait10());

        String actualResultTitle = getText(GUIDE_TITLE, getDriver());

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }
}
