import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Set;


@Ignore
public class SGsolodTest extends BaseTest {
    private final String url = "https://openweathermap.org/";

    @Test
    public void testOpenNewTab() throws InterruptedException {
        String expectedResult = "OpenWeather for business - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement onClickGuide = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text() = 'Guide']")
        );
        onClickGuide.click();
        Thread.sleep(10000);

        WebElement textLink = getDriver().findElement(
                By.xpath("//main[@class = 'wrapper']//a[text() = 'complex enterprise systems']")
        );

        String chrome1 = getDriver().getWindowHandle();
        textLink.click();
        Thread.sleep(10000);

        Set<String> currentChrome = getDriver().getWindowHandles();

        String chrome2 = null;
        for(String chrome : currentChrome) {
            if(!chrome.equals(chrome1)) {
                chrome2 = chrome;
                break;
            }
        }

        getDriver().switchTo().window(chrome2);

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);

    }

}
