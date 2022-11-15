import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class ViktoriiaHukTest extends BaseTest {
    private static final String BASE_URL = "https://openweathermap.org/";
    private static final By DESKTOP_MENU_API = By.linkText("API");
    private static final By DESKTOP_MENU_PARTNERS =
            By.xpath("//div[@id='desktop-menu']//a[@href = '/examples']");
    private static final By ORANGE_BUTTON_IN_API_MENU = By.xpath("//a[contains(@class, 'orange')]");
    private static final By HYPER_LINK_JAVA_IN_MENU_PARTNERS =
            By.linkText("Java");
    private static final By BUTTON_VIEW_TO_HIT_HUB_IN_JAVA =
            By.xpath("//section [@id='java']/a");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void newWindow (){
        String originalWindow = getDriver().getWindowHandle();
        for (String windowHandle : getDriver().getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    @Test
    public void testWenPageApiHas30OrangeButten() {
        int expectedResult = 30;

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(DESKTOP_MENU_API, getWait10());

        int actualResult = getDriver().findElements(ORANGE_BUTTON_IN_API_MENU).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testVerifyActiveButtonViewOnGitHubAndRedirectionToGitHubFromJavaHyperlinkOnPartnersPage() {
        String expectedResult = "https://github.com/migtavares/owmClient";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(DESKTOP_MENU_PARTNERS, getWait5());
        click(HYPER_LINK_JAVA_IN_MENU_PARTNERS,getWait5());
        click(BUTTON_VIEW_TO_HIT_HUB_IN_JAVA, getWait20());
        newWindow();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }
}
