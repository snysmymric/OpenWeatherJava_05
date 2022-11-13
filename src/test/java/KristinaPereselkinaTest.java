import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class KristinaPereselkinaTest extends BaseTest {

    @Test
    public void testRedirectionToApiGuidePage() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";

        String expectedResultLink = "https://openweathermap.org/guide";
        String expectedResultTab = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(baseUrl);
        getDriver().manage().window().maximize();
        Thread.sleep(5000);

        WebElement searcGuideLink = getDriver().findElement(By.xpath("//div[@id='desktop-menu']/ul/li[1]"));
        searcGuideLink.click();

        Thread.sleep(2000);

        String actualResultTab = getDriver().getTitle();
        String actualResultLink = getDriver().getCurrentUrl();

        assertEquals(actualResultTab, expectedResultTab);
        assertEquals(actualResultLink, expectedResultLink);
    }
}
