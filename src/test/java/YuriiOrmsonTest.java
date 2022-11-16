import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class YuriiOrmsonTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static String PRICING_URL = "https://openweathermap.org/price";
    final static By A_HREF_PRICE = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/price']");
    final static By A_HREF_CURRENT_PROFESSIONAL_SUBSCRIPTIONS = By.xpath(
            "//main//p//a[@href ='#current' and contains(text(), 'professional subscriptions')]");
    final static By H1 = By.xpath("//h1");
    final static By PROFFESSIONAL_COLLECTION_SECTION = By.xpath(
            "//div[@class = 'col-sm-12']//h2[contains(text(),'Current weather and forecasts collection')]");
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
    @Test
    public void testScrollToProfessionalCollections_WhenClickOnProfessionalSubscriptions() {
        String expectedResult = "https://openweathermap.org/price#current";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();
        click(A_HREF_CURRENT_PROFESSIONAL_SUBSCRIPTIONS,getWait5());

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
    }
}
