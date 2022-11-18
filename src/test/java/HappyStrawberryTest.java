import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HappyStrawberryTest extends BaseTest {

    private static final String URL = "https://openweathermap.org/";

    private void openBaseURL() {
        getDriver().get(URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    @Test
    public void testMenuPricingIsClickable() {

        openBaseURL();

        waitForGrayFrameDisappeared();

        WebElement MenuPricing = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/price']")
        );
        MenuPricing.click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div/div/div[1]/h1[@class='breadcrumb-title']")
        ).getText(), "Pricing");
    }
}
