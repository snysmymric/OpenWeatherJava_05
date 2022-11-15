import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValentinKuprinTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By GRID_CONTAINER_API_ICONS = By.xpath("//div[@class='grid-container api-icons']//a");

    private void openBaseUrl() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private List<String> apiIconsElements_h3AndSpan(By by) {
        List<String> h3AndSpanElements = new ArrayList<>();

        for (WebElement temp : getDriver().findElements(by)) {
            h3AndSpanElements.add(temp.getText());
        }
        return h3AndSpanElements;
    }

    @Test
    public void testH3TextApiIconsAndCountOfDays() {
        final List<String> expectedResult = Arrays.asList("current\n" +
                "weather\n" +
                "(current)", "hourly\n" +
                "forecast\n" +
                "(4 days)", "daily\n" +
                "forecast\n" +
                "(16 days)", "climatic\n" +
                "forecast\n" +
                "(30 days)", "historical\n" +
                "weather\n" +
                "(1 month, 1 year)");

        openBaseUrl();
        waitForGrayFrameDisappeared();

        List<String> actualResult = apiIconsElements_h3AndSpan(GRID_CONTAINER_API_ICONS);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
