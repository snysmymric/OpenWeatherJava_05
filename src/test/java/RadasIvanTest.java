import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class RadasIvanTest extends BaseTest {
    private final By DIFFERENT_WEATHER = By.xpath("//span[@class='control-el owm-switch']");

    private final By H3_DIALOG_TITLE = By.id("dialogTitle");

    private final By CURRENT_TIME = By.xpath("//div[@id='weather-widget']//span[@class='orange-text'][text()]");

    private final By API = By.xpath("//div[@id='desktop-menu']//a[@href='/api']");

    private final By STATISTICAL_WEATHER_DATA_API = By.xpath("//a[@href='/api/statistics-api']");

    private final By HEADER = By.xpath("//h1[@class='breadcrumb-title']");


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
