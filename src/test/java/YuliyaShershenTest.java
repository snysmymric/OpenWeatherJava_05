import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class YuliyaShershenTest extends BaseTest {

    @Test
    public void testClickOpenWeatherLogoAndReloadPage() throws InterruptedException {
        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchLogoImage = getDriver().findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        searchLogoImage.click();
        Thread.sleep(7000);

        Assert.assertEquals(getDriver().getCurrentUrl(), url);

    }
}
