import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OlgaKhliupinaTest extends BaseTest {

   final static String BASE_URL = "https://openweathermap.org/";
   final static String TEMP_F = "Imperial: °F, mph";
   final static String SYMBOL_TEMP_F = "°F";
   final static By TEMP_UNIT_HEADING = By.xpath("//div[@class='current-temp']/span");

   private void openBaseURL() {
      getDriver().get(BASE_URL);
   }

   private void waitForGrayFrameDisappeared() {
      getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
              By.className("owm-loader-container")));
   }

   private String getText(By by, WebDriver driver) {

      return driver.findElement(by).getText();
   }

   private boolean isTempInSymbol(WebDriver driver, String temp, String symbolTemp) {
      WebElement tempUnit = driver.findElement(By.xpath(String.format("//div[text()='%s']", temp)));
      tempUnit.click();
      waitForGrayFrameDisappeared();

      return getText(TEMP_UNIT_HEADING, getDriver()).contains(symbolTemp);
   }


   @Ignore
   @Test
   public void testLinkAndTitle_WhenGoingToGuideMenu() throws InterruptedException {

      String url = "https://openweathermap.org/";
      String expectedResultUrl = "https://openweathermap.org/guide";
      String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

      getDriver().manage().window().maximize();
      getDriver().get(url);
      Thread.sleep(10000);

      WebElement menuGuide = getDriver().findElement(By.xpath("//div/ul//li/a[@href='/guide']"));
      menuGuide.click();
      Thread.sleep(1000);

      Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
      Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
   }

   @Ignore
   @Test
   public  void testCheckUrlAfterRefreshMainPage() throws InterruptedException {
      String url = "https://openweathermap.org/";
      String expectedResult = url;

      getDriver().get(url);
      Thread.sleep(10000);

      WebElement logo = getDriver().findElement(
              By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']"));
      logo.click();
      Thread.sleep(8000);

      Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
   }

   @Test
   public void testChangingTempUnitInHeadingToF_WhenSwitchTempUnitButtonToF() {
      openBaseURL();
      waitForGrayFrameDisappeared();

      Assert.assertTrue(isTempInSymbol(getDriver(), TEMP_F, SYMBOL_TEMP_F));
   }

   @Ignore
   @Test
   public void testChangeFromFToC() throws InterruptedException {
      String url = "https://openweathermap.org/";

      getDriver().get(url);
      Thread.sleep(10000);

      Assert.assertTrue(isTempInSymbol(getDriver(), "Imperial: °F, mph", "°F"));
      Assert.assertTrue(isTempInSymbol(getDriver(), "Metric: °C, m/s", "°C"));
   }
}
