import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class YuliaMatusevichTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By FACEBOOK_LINK = By.xpath("//div[@class = 'social']/a[1]");

    public void openBaseUrl(){
        getDriver().get(BASE_URL);
    }
    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }
    public int findAllVisibleElements(String xpath, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfAllElements(getDriver().findElement(By.xpath(xpath))));
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        return elements.size();
    }
    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
        public void scrollToPageBottom(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    @Test
    public void testAllSocialMediaIconsVisibleAndClickable (){
        int expectedResult = 6;

        openBaseUrl();
        waitForGrayFrameDisappeared();

        int actualResult = findAllVisibleElements("//div[@class = 'social']/a",getWait5());

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testFacebookIcon_IfCorrespondingFacebookWebpageOpens(){
        String expectedResult = "https://www.facebook.com/groups/270748973021342";

        openBaseUrl();
        waitForGrayFrameDisappeared();
        scrollToPageBottom();
        click(FACEBOOK_LINK, getWait5());

        for(String winHandle : getDriver().getWindowHandles()){
            getDriver().switchTo().window(winHandle);
        }

        Assert.assertTrue((getDriver().getCurrentUrl().contains(expectedResult)));
    }
}


