import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class DmswlpkTest extends BaseTest {

    private static final By DIFFERENT_WEATHER_POPUP_WINDOW = By.xpath("//div[@class='pop-up-container']");
    private static final By DIFFERENT_WEATHER_BTN = By.xpath("//span[contains(text(),'Different')]");
    private static final By DIFFERENT_WEATHER_POPUP_WINDOW_H3_HEADER = By.id("dialogTitle");

    @Test
    public void testDifferentWeatherWindowPopups() {
        openBaseURL();

        String expectedHeader = "Different weather";

        click(DIFFERENT_WEATHER_BTN);

        Assert.assertTrue(isDisplayed(DIFFERENT_WEATHER_POPUP_WINDOW));
        Assert.assertEquals(getText(DIFFERENT_WEATHER_POPUP_WINDOW_H3_HEADER),expectedHeader);
    }
}
