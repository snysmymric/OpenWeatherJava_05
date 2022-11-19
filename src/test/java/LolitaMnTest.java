import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class LolitaMnTest extends BaseTest {

    final static String URL = "https://openweathermap.org/";

    final static By FOOTER_PANEL_CONTAINER = By.xpath("//div[@class='stick-footer-panel__container']");
    final static By BOTTOM_PANEL = By.xpath("//div[@class='stick-footer-panel__container']");
    final static By TEXT_PANEL =  By.xpath("//p[@class='stick-footer-panel__description']");
    final static By ALLOW_ALL_BUTTON = By.xpath("//button[@type='button' and text()='Allow all']");
    final static By MANAGE_BUTTON = By.xpath("//a[@href='/cookies-settings' and text()=' Manage cookies ']");
    final static By SIGN_IN_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//a[@href='https://openweathermap.org/home/sign_in']");
    final static By SIGN_IN_FORM = By.className("sign-form");


    private void openBaseURL() {
        getDriver().get(URL);

    }

    private String getCurrentUrl() {

        return getDriver().getCurrentUrl();
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

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
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

    @Test
    public void testSignInButtonNavigateToLogInPage() {
        String expectedResultUrl = "https://home.openweathermap.org/users/sign_in";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldCurrentUrl = getCurrentUrl();

        click(SIGN_IN_BUTTON, getWait5());
        waitForElementGetVisible(SIGN_IN_FORM,getWait10());

        String actualResultUrl = getCurrentUrl();

        Assert.assertNotEquals(oldCurrentUrl,actualResultUrl);
        Assert.assertEquals(expectedResultUrl,actualResultUrl);
    }
}
