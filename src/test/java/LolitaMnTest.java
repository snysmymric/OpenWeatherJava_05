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
    public void testCheckPageTitle_WhenClickOnGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultURL = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href = '/guide']")
        );

        guideButton.click();

        Thread.sleep(5000);

        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        Assert.assertEquals(actualResultURL, expectedResultURL);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

    }

    @Test
    public void testCheck_F_TemperatureMeasurement() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String fSymbol = "°F";
        String expectedResult = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();

        Thread.sleep(10000);

        WebElement fButton = getDriver().findElement(
                By.xpath("//div[@class='option' and text()='Imperial: °F, mph']")
        );

        Thread.sleep(2000);
        fButton.click();

        WebElement fTitle = getDriver().findElement(By.xpath("//span[@class='heading']"));
        String fTitle2 = fTitle.getText();
        String actualResult = fTitle2.substring(fTitle2.length() - 2);

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertTrue(fTitle2.contains(fSymbol));

    }

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
