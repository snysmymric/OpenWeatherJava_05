import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class RomanTest extends BaseTest {

    private static final String BASE_URL = "https://openweathermap.org/";

    private static final By ONE_CALL_API_LINK = By.xpath("//div[@class='section']//h2/a[@href='/api/one-call-3']");
    private static final By H1_MAIN = By.xpath("//main//h1");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    public void scrollToPage(){
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("window.scrollBy(0,500)", "");
    }

    private String getText(By by) {

        return getDriver().findElement(by).getText();
    }

    @Test
    public void testClickabilityOfOneCallAPILink() {
        final String expectedResult = "One Call API 3.0";

        openBaseURL();
        waitForGrayFrameDisappeared();

        scrollToPage();
        getDriver().findElement(ONE_CALL_API_LINK).click();

        String actualResult = getText(H1_MAIN);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
