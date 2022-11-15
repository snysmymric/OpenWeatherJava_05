import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AnnaAbgTest extends BaseTest {

    private static final String BASE_URL = "https://openweathermap.org/";
    private static By API_LINK_PAGE = By.xpath("//a[@href='/api']");
    private static By BUTTONS_COUNT = By.xpath("// a[contains(@class,'orange')]");
    private static By SEARCH_PARTNERS_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");

    private void OpenBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private int getButtonsCount(By by, WebDriver driver) {

        return driver.findElements(by).size();
    }

    private String getCurrentUrl() {

        return getDriver().getCurrentUrl();
    }


    @Test
    public void testThirtyOrangeButtons() {
        int expectedResult = 30;

        OpenBaseURL();
        waitForGrayFrameDisappeared();

        click(API_LINK_PAGE, getWait5());

        int countButtons = getButtonsCount(BUTTONS_COUNT, getDriver());

        Assert.assertEquals(countButtons, expectedResult);
    }

    @Test
    public void testPartnersAndSolutionsPage() {

        String expectedResultCurrentURL = "https://openweathermap.org/examples";

        OpenBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_PARTNERS_MENU, getWait5());

        String actualResultCurrentURL = getCurrentUrl();

        Assert.assertEquals(actualResultCurrentURL, expectedResultCurrentURL);
    }
}
