import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
   final static By LOGO = By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
   final static By GITHUB_ICON =
           By.xpath("//img[@src='/themes/openweathermap/assets/img/owm_icons/icon_github.png']");
   final static By GITHUB_ICON_LINK =
           By.xpath("//a[@href='https://github.com/search?q=openweathermap&ref=cmdform']");
   final static By ALLOW_ALL_BUTTON = By.xpath("//button[@type='button']");
   final static By SOCIAL_PANEL = By.className("social");

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

   private void visibleElement(By by, WebDriverWait wait) {
      wait.until(ExpectedConditions.visibilityOfElementLocated(by));
   }

   private void scrollDownToElement(By by, WebDriver driver) {
      Actions a = new Actions(driver);
      a.moveToElement(driver.findElement(by));
   }

   private void switchToAnotherWindow(WebDriver driver) {
      String originalWindow = driver.getWindowHandle();

      for (String windowHandle : driver.getWindowHandles()) {
         if (!originalWindow.equals(windowHandle)) {
            driver.switchTo().window(windowHandle);
            break;
         }
      }
   }

   private boolean isElementDisplayed(By by, WebDriver driver) {

      return driver.findElement(by).isDisplayed();
   }

   private int quantityOfElements(By by, WebDriver driver) {

      return driver.findElements(by).size();
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

   @Test
   public  void testCheckUrlTitle_AfterRefreshMainPage() {
      String expectedResult = "https://openweathermap.org/";
      String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

      openBaseURL();
      waitForGrayFrameDisappeared();
      click(LOGO, getWait20());

      Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
      Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
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

   @Test
   public void testGithubIconExistsAfterClickNavigateToGithub() throws InterruptedException {
      String expectedResult2 = "https://github.com/search?q=openweathermap&ref=cmdform";

      openBaseURL();
      waitForGrayFrameDisappeared();
      scrollDownToElement(GITHUB_ICON, getDriver());
      click(ALLOW_ALL_BUTTON, getWait20());

      Assert.assertTrue(isElementDisplayed(GITHUB_ICON, getDriver()));

      click(GITHUB_ICON_LINK,getWait20());
      switchToAnotherWindow(getDriver());

      Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult2);
   }

   @Test
   public void testSocialPanelExistsAndHas6Icons() {
      int expectedResult2 = 6;

      openBaseURL();
      waitForGrayFrameDisappeared();

      Assert.assertTrue(isElementDisplayed(SOCIAL_PANEL, getDriver()));
      Assert.assertEquals(quantityOfElements(
              By.xpath("//div[@class='social']/a"), getDriver()), expectedResult2);
   }
}
