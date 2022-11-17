import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;




public class TimberhutGuideClickabilityTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";

    @Test
    public void guideClickabilityTest() {

        getDriver().get(BASE_URL);

        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("own-loader-container")));

        getWait5().until(ExpectedConditions.
                visibilityOfElementLocated(
                        By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")
                )
        );
        WebElement guideMenu = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")
        );
        guideMenu.click();

        String expectedResult = "https://openweathermap.org/guide";
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
    }
}