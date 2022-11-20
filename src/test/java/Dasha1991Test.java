import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class Dasha1991Test extends BaseTest {

     final static String BASE_URL = "https://openweathermap.org/";
     final static  By NAME_WEBSITE_H1 = By.xpath
             ("//div[@class='mobile-padding']//span[@class = 'orange-text']");
     final static By NAME_H2_HEADER = By.xpath
             ("//div[@class = 'mobile-padding']//span[@class = 'white-text']");

     private void openBaseURL() {
         getDriver().get(BASE_URL);
     }
    private void waitForGrayFrameDissappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }
    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void waitUntilElementIsVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    private String getText(By by, WebDriver driver) {
        return driver.findElement(by).getText();
    }

    @Ignore
    @Test
    public void testHeaderStartPage() {
         String expectedResultH1 = "OpenWeather";
         String expectedResultH2 = "Weather forecasts, nowcasts and history in a fast and elegant way";

         openBaseURL();
         waitForGrayFrameDissappeared();

         String actualResultH1 = getText(NAME_WEBSITE_H1,getDriver());
         String actualResultH2 = getText(NAME_H2_HEADER,getDriver());

         Assert.assertEquals(actualResultH1,expectedResultH1);
         Assert.assertEquals(actualResultH2,expectedResultH2);
    }
    @Test
    public void testNameOfURL() {
         String expectedResultURL = "https://openweathermap.org/";
         String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

         openBaseURL();
         waitForGrayFrameDissappeared();

         String actualResultURL = getDriver().getCurrentUrl();
         String actualResultTitle = getDriver().getTitle();

         Assert.assertEquals(actualResultURL,expectedResultURL);
         Assert.assertEquals(actualResultTitle,expectedResultTitle);
    }
}
