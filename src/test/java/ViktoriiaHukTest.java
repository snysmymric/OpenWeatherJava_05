import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ViktoriiaHukTest extends BaseTest {
    private static final String URL = "https://openweathermap.org/";

    @Test
    public void testWenPageApiHas30OrangeButten() throws InterruptedException {
        int expectedResult = 30;

        getDriver().get(URL);
        Thread.sleep(7000);
        getDriver().findElement(By.xpath("//div[@id = 'desktop-menu']/ul/li[2]/a")).click();

        int actualResult = getDriver().findElements(By.xpath("//a[contains(@class, 'orange')]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
