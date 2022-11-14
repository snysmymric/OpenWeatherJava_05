import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class WeraStremedlowskaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";

    final static By SUPPORT_DROPDOWN = By.id("support-dropdown");
    final static By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']/li");
    final static By SUBMENU_FAQ = By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/faq']");
    final static By SUBMENU_HOW_TO_START = By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']");
    final static By SUBMENU_ASK_A_QUESTION = By
            .xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private int size(By by, WebDriver driver) {

        return driver.findElements(by).size();
    }

    @Test
    public void testMenuSupportHasThreeSubmenus()  {
        String expectedResultSubmenu1 = "FAQ";
        String expectedResultSubmenu2 = "How to start";
        String expectedResultSubmenu3 = "Ask a question";

            openBaseURL();
            waitForGrayFrameDisappeared();
            click(SUPPORT_DROPDOWN, getWait5());

        Assert.assertEquals(size(SUPPORT_DROPDOWN_MENU, getDriver()), 3);

        Assert.assertEquals(getText(SUBMENU_FAQ, getDriver()), expectedResultSubmenu1);
        Assert.assertEquals(getText(SUBMENU_HOW_TO_START, getDriver()), expectedResultSubmenu2);
        Assert.assertEquals(getText(SUBMENU_ASK_A_QUESTION, getDriver()), expectedResultSubmenu3);

    }
}
