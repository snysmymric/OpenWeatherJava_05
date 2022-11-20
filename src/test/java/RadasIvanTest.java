import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class RadasIvanTest extends BaseTest {
    private final By DIFFERENT_WEATHER = By.xpath("//span[@class='control-el owm-switch']");

    private final By H3_DIALOG_TITLE = By.id("dialogTitle");

    private final By CURRENT_TIME = By.xpath("//div[@id='weather-widget']//span[@class='orange-text'][text()]");


    @Test
    public void testButtonDifferentWeatherIsClickableOnStartPage() {
        String expectedNameTitle = "Different weather";

        openBaseURL();
        click(DIFFERENT_WEATHER);

        String actualResult = getText(H3_DIALOG_TITLE);

        Assert.assertEquals(actualResult, expectedNameTitle);
    }

    @Test
    public void testCurentDateAndTimeStartPage() {
        String expectedResult = systemDate();

        openBaseURL();

        String actualResult = getText(CURRENT_TIME).substring(0, 10);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
