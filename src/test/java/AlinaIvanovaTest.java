import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class AlinaIvanovaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";

    final static By MENU_OUR_INITIATIVES = By.xpath("//div[@id= 'desktop-menu']//a[@href= '/our-initiatives']");

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
    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }
    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }
    private String getCurrentURL() {
        return getDriver().getCurrentUrl();
    }
    @Test
    public void testVerifyOurInitiativesMenuIsClickablePageOpened() {

        String expectedResult = "https://openweathermap.org/our-initiatives";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(MENU_OUR_INITIATIVES, getWait5());

        String actualResult = getCurrentURL();
        Assert.assertEquals(actualResult, expectedResult);
    }

}
