import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OlgaAbduTest extends BaseTest {

    @Test
    public void testTemperatureInFahrenheit() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");
        getDriver().manage().window().maximize();
        Thread.sleep(7000);

        WebElement imperialF = getDriver().findElement(By.xpath("//div[contains(text(),'Imperial')]"));
        Assert.assertEquals(imperialF.getText(), "Imperial: °F, mph");
        imperialF.click();
        Thread.sleep(7000);

        String actualResult = getDriver().findElement(By.xpath("//span[@class='heading']")).getText();
        Assert.assertEquals(actualResult.contains("°F"), true);

    }

}
