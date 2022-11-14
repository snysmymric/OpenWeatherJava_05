import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class ViktoriiaHukTest extends BaseTest {
    private static final String BASE_URL = "https://openweathermap.org/";
    private static final By DESKTOP_MENU_API = By.xpath("//div[@id = 'desktop-menu']/ul/li[2]/a");
    private static final By ORANGE_BUTTON_IN_API_MENU = By.xpath("//a[contains(@class, 'orange')]");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Test
    public void testWenPageApiHas30OrangeButten() {
        int expectedResult = 30;

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(DESKTOP_MENU_API, getWait10());

        int actualResult = getDriver().findElements(ORANGE_BUTTON_IN_API_MENU).size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
