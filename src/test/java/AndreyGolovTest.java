import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class AndreyGolovTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By DIFFERENT_WEATHER_BUTTON = By.xpath("//div[@class = 'controls']//span");
    final static By BUTTON_X_ON_POPUP = By.xpath("//div[@class = 'pop-up-container']//*[@class='icon-close']");

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Test
    public void testXButtonIsDisplayed() {
        boolean expectedCondition = true;

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(DIFFERENT_WEATHER_BUTTON, getWait5());
        WebElement buttonX = getDriver().findElement(BUTTON_X_ON_POPUP);

        Assert.assertEquals(buttonX.isDisplayed(), expectedCondition);
    }
}
