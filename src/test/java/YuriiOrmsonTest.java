import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class YuriiOrmsonTest extends BaseTest {
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
        Assert.assertEquals(actualResult1, expectedResult1);
    }

    @Test
    public void testSpanHeading_CurrentTempFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "F";
        getDriver().get(url);
        Thread.sleep(10000);

        WebElement changeMeasureToFahrenheit = getDriver().findElement(
                By.xpath("//div[@class = 'switch-container']/div[3]"));
        changeMeasureToFahrenheit.click();
        Thread.sleep(10000);

        WebElement spanHeading = getDriver().findElement(
                By.xpath("//div[contains(@class, 'current-temp')]"));

        String spanHeadingText = spanHeading.getText();
        String actualResult = spanHeadingText.substring(spanHeadingText.length() - 1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testStickFooterPanel_WhenOpenFirstTime() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services." +
                " Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult1 = "Allow all";
        String expectedResult2 = "Manage cookies";
        getDriver().get(url);
        Thread.sleep(10000);

        WebElement stickFooterDescription = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//p[@class = 'stick-footer-panel__description']"));
        String actualResult = stickFooterDescription.getText();
        Assert.assertEquals(actualResult, expectedResult);

        WebElement allowAllButton = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//button"));
        String actualResult1 = allowAllButton.getText();
        Assert.assertEquals(actualResult1, expectedResult1);

        WebElement manageCookiesLink = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//a"));
        String actualResult2 = manageCookiesLink.getText();
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}
