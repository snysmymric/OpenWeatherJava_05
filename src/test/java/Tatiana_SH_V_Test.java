import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;



public class Tatiana_SH_V_Test extends BaseTest {

    @Ignore
    @Test
    public void testGuideTitle() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideSearch = getDriver().findElement(
                By.xpath("//div/ul/li/a[@href = '/guide']")
        );
        guideSearch.click();
        Thread.sleep(2000);

        String actualResult1 = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult1, expectedResult1);

        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult2, expectedResult2);
    }
}
