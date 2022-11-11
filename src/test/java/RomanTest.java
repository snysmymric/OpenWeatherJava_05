import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;

public class RomanTest extends BaseTest {

    @Test
    public void testClickabilityOfOneCallAPILink() {

        String url = "https://openweathermap.org/";
        String expectedResult = "One Call API 3.0";

        getDriver().get(url);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='section']//h2/a[@href='/api/one-call-3']")));

        WebElement linkOneCall = getDriver().findElement(
                By.xpath("//div[@class='section']//h2/a[@href='/api/one-call-3']"));
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].click();", linkOneCall);

        String actualResult = getDriver().findElement(
                By.xpath("//div[@class='topic']//li[@class='breadcrumb__leaf']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
