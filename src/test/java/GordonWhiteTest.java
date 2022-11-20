import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class GordonWhiteTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_CITY_FIELD = By.xpath("//ul[@id='first-level-nav']//input[@type='text']");

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

    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }

    @Test
    public void testSearchFieldWithTextOnMainPageWhenPressEnter() {
        String cityName = "Rome";
        String wordName = "find";
        boolean expectedResult = true;

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_CITY_FIELD, getWait10());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        getDriver().findElement(SEARCH_CITY_FIELD).sendKeys(Keys.RETURN);

        String currentUrl = getDriver().getCurrentUrl();
        boolean actualResult = currentUrl.contains(cityName) && currentUrl.contains(wordName);

        Assert.assertEquals(actualResult, expectedResult);
    }
}