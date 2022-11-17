import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class LolitaMnTest extends BaseTest {

    final static String URL = "https://openweathermap.org/";

    final static By FOOTER_PANEL_CONTAINER = By.xpath("//div[@class='stick-footer-panel__container']");
    final static By BOTTOM_PANEL = By.xpath("//div[@class='stick-footer-panel__container']");
    final static By TEXT_PANEL =  By.xpath("//p[@class='stick-footer-panel__description']");
    final static By ALLOW_ALL_BUTTON = By.xpath("//button[@type='button' and text()='Allow all']");
    final static By MANAGE_BUTTON = By.xpath("//a[@href='/cookies-settings' and text()=' Manage cookies ']");


    private void openBaseURL() {
        getDriver().get(URL);

    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private void waitForElementGetVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }




    @Test
    public void testCheckTextInTheBottomOfThePage() {
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential "
                + "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies"
                + " or manage them individually.";


        openBaseURL();
        waitForGrayFrameDisappeared();
        waitForElementGetVisible(FOOTER_PANEL_CONTAINER, getWait5());
        waitForElementGetVisible(BOTTOM_PANEL, getWait5());
        waitForElementGetVisible(ALLOW_ALL_BUTTON, getWait5());
        waitForElementGetVisible(MANAGE_BUTTON, getWait5());

        String actualResultTextPanel = getText(TEXT_PANEL, getDriver());

        Assert.assertEquals(actualResultTextPanel, expectedResult);

    }
}
