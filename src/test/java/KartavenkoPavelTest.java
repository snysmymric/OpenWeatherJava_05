//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//@Ignore
//public class KartavenkoPavelTest extends BaseTest {
//private static final String URL = "https://openweathermap.org/";
//
//    private void getWait() {
//        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
//                .until(ExpectedConditions.invisibilityOfElementLocated(
//                        By.xpath("//div[@class='owm-loader-container']/div")));
//        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void testCheckThirtyOrangeButtons() {
//        final int expectedResult = 30;
//        getDriver().get(URL);
//        getWait();
//
//        getDriver().findElement(By.xpath("//ul[@id='first-level-nav']//a[@href='/api']")).click();
//        List<WebElement> buttonList = getDriver().findElements(By.xpath("//a[contains(@class,'orange')]"));
//
//        Assert.assertEquals(buttonList.size(), expectedResult);
//    }
//
//    @Test
//    public void testCheckTopInputFieldSearch() {
//        final String city = "Rome";
//
//        getDriver().get(URL);
//        getWait();
//
//        getDriver().findElement(By.xpath("//div//input[@name='q']")).sendKeys(city + "\n");
//        String path = getDriver().getCurrentUrl();
//        boolean actualQueryPath = path.contains("find") && path.contains(city);
//        WebElement searchField = getDriver().findElement(By.id("search_str"));
//        String actualResult = searchField.getAttribute("value");
//
//        Assert.assertTrue(actualQueryPath);
//        Assert.assertEquals(actualResult, city);
//
//    }
//}
