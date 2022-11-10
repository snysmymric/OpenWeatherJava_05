import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CordedWareTest extends BaseTest {

    @Test
    public void testTittleAndUrlWhenGuideMenu_HappyPath_Test() throws InterruptedException{

        // AAA
        // Arrange
        String url = "https://openweathermap.org/";
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        // Act
        getDriver().get(url); // 1. открыть ссылку по url
        Thread.sleep(10000);

        WebElement selectButtonMenu = getDriver().findElement( // 2.  Нажать на пункт меню Guide
                By.xpath("//a[@href = '/guide']")
        );
        selectButtonMenu.click();
        Thread.sleep(10000);

        String actualResultUrl = getDriver().getCurrentUrl(); // 3.1 Подтвердить, что перешли по ссылке
        String actualResultTitle = getDriver().getTitle(); // 3.2 Подтвердить, что заголовок изменился

        // Assert
        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        getDriver().quit();
    }
}
