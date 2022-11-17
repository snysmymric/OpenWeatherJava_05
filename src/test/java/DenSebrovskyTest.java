import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DenSebrovskyTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");
    final static By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappear() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void waitUntilElementIsVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Test
    public void testSupportMenuIsClickable() {
        final int expectedSubmenuButtonsAmount = 3;

        openBaseURL();
        waitForGrayFrameDisappear();
        click(SUPPORT_BUTTON, getWait5());
        waitUntilElementIsVisible(SUPPORT_DROPDOWN_MENU, getWait5());
        Assert.assertTrue(getDriver().findElement(SUPPORT_DROPDOWN_MENU).isDisplayed());

        int actualSubmenuButtonsAmount = getDriver().findElements(By.xpath("//ul[@id='support-dropdown-menu']/*")).size();
        Assert.assertEquals(actualSubmenuButtonsAmount, expectedSubmenuButtonsAmount);

        click(SUPPORT_BUTTON, getWait5());
        Assert.assertTrue(getDriver().findElement(SUPPORT_DROPDOWN_MENU).isEnabled());
    }
}
