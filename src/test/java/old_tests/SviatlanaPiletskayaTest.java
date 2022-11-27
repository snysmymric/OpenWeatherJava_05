package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import org.testng.annotations.Test;
import base.BaseTest;

public class SviatlanaPiletskayaTest extends BaseTest {
    private final By NAME_WEBSITE = By.xpath("//div[@class='section where-to']//span[@class= 'orange-text']");
    private final By DESCRIPTION_WEBSITE = By.xpath("//div[@class='section where-to']//span[@class= 'white-text']");

    @Test
    public void testHeaderStartPage() {
        String expectedResultName = "OpenWeather";
        String expectedResultDescription = "Weather forecasts, nowcasts and history in a fast and elegant way";

        openBaseURL();

        String actualResultName = getText(NAME_WEBSITE);
        String actualResultDescription = getText(DESCRIPTION_WEBSITE);
        String actualColorName = getCssStyleColor(NAME_WEBSITE);
        String actualColorDescription = getCssStyleColor(DESCRIPTION_WEBSITE);

        Assert.assertEquals(actualResultName, expectedResultName);
        Assert.assertEquals(actualResultDescription, expectedResultDescription);

        String CSS_STYLE_WHITE_TEXT_COLOR = "#ffffff";
        String CSS_STYLE_ORANGE_TEXT_COLOR = "#eb6e4b";
        Assert.assertTrue(actualColorName.equals(CSS_STYLE_ORANGE_TEXT_COLOR)
                && actualColorDescription.equals(CSS_STYLE_WHITE_TEXT_COLOR));
    }
}