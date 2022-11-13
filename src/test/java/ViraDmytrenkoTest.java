import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class ViraDmytrenkoTest extends BaseTest {
    static String url = "https://openweathermap.org/";

    @Test
    public void testTitleTextAndURLText() throws InterruptedException {
        getDriver().get(url);
        Thread.sleep(10000);
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().findElement(By.xpath("//div[@id='desktop-menu']//a[text()='Guide']")).click();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}
