import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Yuliya14Test extends BaseTest {

    @Test
    public void testMenuGuide_TitleOpenWeatherMap () throws InterruptedException{

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(8000);

        WebElement menuGuide = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li/a[@href = '/guide']")
        );
        menuGuide.click();

        String actualResultUrl = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

    }



}
