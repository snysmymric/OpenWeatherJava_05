import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IriSamoTest extends BaseTest {
    private final By WEATHER_WIDGET_ELEMENTS =
            By.xpath("//div[@id='weather-widget']//*");

    @Test
    public void test_Start_Weather_AllElementsExistsAndVisible() {
        final String[] expectedStrings = {"Search", "Different Weather?", "Metric: °C, m/s",
                "Imperial: °F, mph", "Feels like", "Hourly forecast", "8-day forecast",};

        final List<String> expectedTexts = Arrays.asList(expectedStrings);

        openBaseURL();

        List<WebElement> elements = getListOfElements(WEATHER_WIDGET_ELEMENTS);

        Assert.assertTrue(elements.size() > 0);

        for (WebElement element : elements) {
            Assert.assertTrue(isDisplayed(WEATHER_WIDGET_ELEMENTS) &&
                    isElementEnabled(WEATHER_WIDGET_ELEMENTS));
        }

        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }

        int count = 0;
        for (String expectedText : expectedTexts) {
            for (String text : texts) {
                if (text.contains(expectedText)) {
                    count++;
                    break;
                }
            }
        }

        Assert.assertEquals(expectedTexts.size(), count);
    }
}