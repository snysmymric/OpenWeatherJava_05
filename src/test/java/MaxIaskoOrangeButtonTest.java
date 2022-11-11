import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MaxIaskoOrangeButtonTest extends BaseTest {

    @Test
    public void testSearch30OrangeButtons() throws InterruptedException {
        final String url = "http://openweathermap.org/";
        final int expectedResult = 30;
        getDriver().get(url);

        Thread.sleep(10000);
        WebElement searchApiPage = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        searchApiPage.click();

        int actualResult = getDriver().findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange')]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}

