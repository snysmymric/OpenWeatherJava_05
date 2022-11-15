import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class LolitaMnTest extends BaseTest {


    @Test
    public void testCheckTextInTheBottomOfThePage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential "
                + "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies"
                + " or manage them individually.";
        String allowAll = "Allow all";
        String manageCookies = " Manage cookies ";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement bottomPanel = getDriver().findElement(
                By.xpath("//div[@class='stick-footer-panel__container']")
        );

        WebElement textPanel = getDriver().findElement(
                By.xpath("//p[@class='stick-footer-panel__description']")
        );

        String actualResultTextPanel = textPanel.getText();

        WebElement allowAllButton = getDriver().findElement(
                By.xpath("//button[@type='button' and text()='" + allowAll + "']")
        );

        WebElement manageButton = getDriver().findElement(
                By.xpath("//a[@href='/cookies-settings' and text()='" + manageCookies + "']")
        );
        Assert.assertTrue(bottomPanel.isDisplayed());
        Assert.assertTrue(allowAllButton.isDisplayed());
        Assert.assertTrue(manageButton.isDisplayed());
        Assert.assertEquals(actualResultTextPanel, expectedResult);

    }
}
