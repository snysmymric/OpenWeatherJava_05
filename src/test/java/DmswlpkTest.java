//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//@Ignore
//public class DmswlpkTest extends BaseTest {
//
//    final static String BASE_URL = "https://openweathermap.org/";
//    final static By DIFFERENT_WEATHER_BTN = By.xpath("//span[contains(text(),'Different')]");
//    final static By DIFFERENT_WEATHER_POPUP_WINDOW = By.xpath("//div[@class='pop-up-container']");
//
//    private void openBaseURL() {
//        getDriver().get(BASE_URL);
//    }
//
//    private void waitGreyFrameDisappeared() {
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
//                By.className("owm-loader-container")));
//    }
//
//    private boolean getDisplayed(By by, WebDriver driver) {
//
//        return driver.findElement(by).isDisplayed();
//    }
//
//      private void click(By by,  WebDriverWait wait) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
//    }
//
//
//    @Test
//    public void verifyPopupDifferentWeatherWindowAppears() {
//        openBaseURL();
//
//        waitGreyFrameDisappeared();
//
//        click(DIFFERENT_WEATHER_BTN,getWait5());
//
//        Assert.assertTrue(getDisplayed(DIFFERENT_WEATHER_POPUP_WINDOW,getDriver()));
//    }
//
//}
