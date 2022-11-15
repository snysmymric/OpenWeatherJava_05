import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class YuriiOrmsonTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By A_HREF_PRICE = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/price']");
    final static By H1 = By.xpath("//h1");
    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }
    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }
    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private String getText(By by, WebDriver driver) {
        return driver.findElement(by).getText();
    }
    @Test
    public void testH1BreadcrumbTitle_WhenOpenPricingPage() {
        String expectedResult = "Pricing";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();

        String actualResult = getText(H1,getDriver());

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testUrl_WhenOpenPricingPage() {
        String expectedResult = "https://openweathermap.org/price";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testTabTitle_WhenOpenPricingPage() {
        String expectedResult = "Pricing - OpenWeatherMap";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult,expectedResult);
    }
}
