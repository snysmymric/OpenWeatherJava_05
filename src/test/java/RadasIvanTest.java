//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Ignore
//public class RadasIvanTest extends BaseTest {
//    final static String BASE_URL = "https://openweathermap.org/";
//    final static By DIFFERENT_WEATHER = By.xpath("//span[@class='control-el owm-switch']");
//
//    final static By H3_DIALOG_TITLE = By.id("dialogTitle");
//
//    final static By CURRENT_TIME = By.xpath("//div[@id='weather-widget']//span[@class='orange-text'][text()]");
//
//    private void openBaseURL() {
//        getDriver().get(BASE_URL);
//    }
//
//    private void waitForGrayFrameDisappeared() {
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
//                By.className("owm-loader-container")));
//    }
//
//    private void click(By by, WebDriverWait wait) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
//    }
//
//    private String getText(By by, WebDriver driver) {
//
//        return driver.findElement(by).getText();
//    }
//
//    private String systemDate() {
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mma");
//
//        return sdf.format(date).substring(0, 10);
//    }
//
//    @Test
//    public void testButtonDifferentWeatherIsClickableOnStartPage() {
//        String expectedResult = "Different weather";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        click(DIFFERENT_WEATHER, getWait5());
//
//        String actualResult = getText(H3_DIALOG_TITLE, getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//
//    @Test
//    public void testCurentDateAndTimeStartPage() {
//
//        String expectedResult = systemDate();
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//
//        String actualResult = getText(CURRENT_TIME, getDriver()).substring(0, 10);
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//}
