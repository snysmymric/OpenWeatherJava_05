import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SviatlanaPiletskayaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By NAME_WEBSITE = By.xpath("//div[@class='section where-to']//span[@class= 'orange-text']");
    final static By DESCRIPTION_WEBSITE = By.xpath("//div[@class='section where-to']//span[@class= 'white-text']");
    final static String CSS_STYLE_ORANGE_TEXT_COLOR = "#eb6e4b";
    final static String CSS_STYLE_WHITE_TEXT_COLOR = "#ffffff";

    private void openBaseUrl() {
        getDriver().get(BASE_URL);
    }

    private void waitForGreyFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver) {
        return driver.findElement(by).getText();
    }

    private String getCssStyleColor(By by, WebDriver driver) {
        String color = driver.findElement(by).getCssValue("color");

        String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
        int hexValue1 = Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2 = Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3 = Integer.parseInt(hexValue[2]);

        return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
    }

    @Test
    public void testHeaderStartPage() {
        String expectedResultName = "OpenWeather";
        String expectedResultDescription = "Weather forecasts, nowcasts and history in a fast and elegant way";

        openBaseUrl();
        waitForGreyFrameDisappeared();

        String actualResultName = getText(NAME_WEBSITE, getDriver());
        String actualResultDescription = getText(DESCRIPTION_WEBSITE, getDriver());

        String actualColorName = getCssStyleColor(NAME_WEBSITE, getDriver());
        String actualColorDescription = getCssStyleColor(DESCRIPTION_WEBSITE, getDriver());

        Assert.assertEquals(actualResultName, expectedResultName);
        Assert.assertEquals(actualResultDescription, expectedResultDescription);

        Assert.assertTrue(actualColorName.equals(CSS_STYLE_ORANGE_TEXT_COLOR)
                && actualColorDescription.equals(CSS_STYLE_WHITE_TEXT_COLOR));
    }
}