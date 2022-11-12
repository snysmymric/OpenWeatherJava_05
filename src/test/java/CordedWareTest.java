import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CordedWareTest extends BaseTest {

    private final String WEATHER_PAGE_URL = "https://openweathermap.org/";
    @Test
    public void testTittleAndUrlWhenGuideMenu_HappyPath_Test() throws InterruptedException{

        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(WEATHER_PAGE_URL);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//a[@href = '/guide']")).click();

        String actualResultUrl = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }

    @Test
    public void testTemperatureForCity_HappyPath_Test() throws InterruptedException {

        String fSymbol = "°F";
        String expectedResult = "°F";

        getDriver().get(WEATHER_PAGE_URL);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[3][@class = 'option']")).click();
        Thread.sleep(10000);

        WebElement checkTemperatureHeader = getDriver().findElement(By.xpath("//div[@class = " +
                "'current-container mobile-padding']/div[2]/div/span[@class = 'heading']"));
        checkTemperatureHeader.getText();

        String actualResult = checkTemperatureHeader.getText().substring(checkTemperatureHeader.getText().length()-2);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
