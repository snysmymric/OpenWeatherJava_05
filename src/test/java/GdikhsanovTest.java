import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GdikhsanovTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    private void openBaseUrl() {
        getDriver().get(BASE_URL);
    }

    private void waitForGreyFrameDisappeared() {
        getWait20().until(ExpectedConditions
                .invisibilityOfElementLocated(By.className("owm-loader-container")));
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
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text));
    }

    @Test
    public void test_TitleAndUrl_WhenGoToGuide() {

        String expectedUrl = String.format("%sguide", BASE_URL);
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseUrl();
        waitForGreyFrameDisappeared();

        click(By.xpath("//div[@id='desktop-menu']//a[@href='/guide']"), getWait5());

        String currentUrl = getDriver().getCurrentUrl();
        String currentTitle = getDriver().getTitle();

        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(currentTitle, expectedTitle);
    }
}