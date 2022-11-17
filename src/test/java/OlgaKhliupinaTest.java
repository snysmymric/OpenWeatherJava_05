import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OlgaKhliupinaTest extends BaseTest {

   final static String BASE_URL = "https://openweathermap.org/";
   final static String TEMP_F = "Imperial: °F, mph";
   final static String SYMBOL_TEMP_F = "°F";
   final static  String TEMP_C = "Metric: °C, m/s";
   final static String SYMBOL_TEMP_C = "°C";
   final static By TEMP_UNIT_HEADING = By.xpath("//div[@class='current-temp']/span");
   final static By MENU_GUIDE = By.xpath("//div/ul//li/a[@href='/guide']");
   final static By GUIDE_TITLE = By.className("breadcrumb-title");

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
      click(By.xpath(String.format("//div[text()='%s']", temp)), getWait5());
      waitForGrayFrameDisappeared();

      return getText(TEMP_UNIT_HEADING, getDriver()).contains(symbolTemp);
   }

   private void click(By by,  WebDriverWait wait) {
      wait.until(ExpectedConditions.visibilityOfElementLocated(by));
      wait.until(ExpectedConditions.elementToBeClickable(by)).click();
   }

   @Test
   public void testLinkAndTitle_WhenGoingToGuideMenu() {

      String expectedResultUrl = "https://openweathermap.org/guide";
      String expectedResultTitle = "Guide";

      openBaseURL();
      waitForGrayFrameDisappeared();
      click(MENU_GUIDE, getWait10());

      String actualResultTitle = getText(GUIDE_TITLE, getDriver());

      Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
      Assert.assertEquals(actualResultTitle, expectedResultTitle);
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

   @Test
   public void testChangingTempUnitInHeadingToC_WhenSwitchTempUnitButtonToC() {
      openBaseURL();
      waitForGrayFrameDisappeared();

      Assert.assertTrue(isTempInSymbol(getDriver(), TEMP_C, SYMBOL_TEMP_C));
   }
}
