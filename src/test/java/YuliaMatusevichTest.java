import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class YuliaMatusevichTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

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
    @Test
    public void testAllSocialMediaIconsVisibleAndClickable (){
        int expectedResult = 6;

        openBaseUrl();
        waitForGrayFrameDisappeared();

        int actualResult = findAllVisibleElements("//div[@class = 'social']/a",getWait5());

        Assert.assertEquals(actualResult,expectedResult);
    }
}


