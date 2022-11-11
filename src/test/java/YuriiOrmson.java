import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class YuriiOrmson extends BaseTest {
        @Test
        public void testH1TagText_WhenOpenGuidPage() throws InterruptedException {
            String url = "https://openweathermap.org/";
            String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
            String expectedResult1 = "https://openweathermap.org/guide";
            getDriver().get(url);
            Thread.sleep(5000);

            WebElement searchGuideLink = getDriver().findElement(
                    By.xpath("//div[@id = 'desktop-menu']/ul/li[1]"));
            searchGuideLink.click();
            Thread.sleep(2000);

            String actualResult = getDriver().getTitle();
            Assert.assertEquals(actualResult, expectedResult);
            String actualResult1 = getDriver().getCurrentUrl();
            Assert.assertEquals(actualResult1,expectedResult1);
        }
}
