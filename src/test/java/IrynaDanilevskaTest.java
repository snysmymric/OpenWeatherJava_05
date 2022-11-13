import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class IrynaDanilevskaTest extends BaseTest {

    @Ignore
    @Test
    public void testFarengeitDegreesDisplayingOnMainPage_AfterClickingOnImperialButton() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement switchDegreeValue = getDriver().findElement(
                By.xpath("//div[contains(text(),'Imperial: °F, mph')]")
        );
        switchDegreeValue.click();
        Thread.sleep(5000);

        WebElement valueDegreeLondon9Days = getDriver().findElement(
                By.xpath("//span[contains(text(),('°F'))]")
        );
        WebElement dewPoint = getDriver().findElement(
                By.xpath("//ul/li[contains(text(),('°F'))]")
        );
        WebElement feelsLike = getDriver().findElement(
                By.xpath("//div[contains(text(),('°F'))]")
        );
        String actualResult1 = valueDegreeLondon9Days.getText();
        String actualResult2 = dewPoint.getText();
        String actualResult3 = feelsLike.getText();

        Assert.assertTrue(actualResult1.contains(expectedResult));
        Assert.assertTrue(actualResult3.contains(expectedResult));
        Assert.assertTrue(actualResult2.contains(expectedResult));

        Thread.sleep(5000);
    }
}
