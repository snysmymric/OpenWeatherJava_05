import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class VeraFesTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By LOGO =
            By.xpath("//a[@href]//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");

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

    private String getCurrentUrl(WebDriver driver) {

        return driver.getCurrentUrl();
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitElementToBeClicable(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Test
    public void test_LogoExistsClickableRedirects2Start() {

        String expectedResult = BASE_URL;

        openBaseURL();
        waitForGrayFrameDisappeared();

        waitElementToBeVisible(LOGO, getWait10());
        waitElementToBeClicable(LOGO, getWait5());
        click(LOGO, getWait5());

        String actualResult = getCurrentUrl(getDriver());

        Assert.assertEquals(actualResult,expectedResult);
    }
}
