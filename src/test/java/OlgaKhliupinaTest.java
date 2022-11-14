import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OlgaKhliupinaTest extends BaseTest {

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

   @Ignore
   static boolean isTempInSymbol(WebDriver driver, String temp, String symbolTemp) throws InterruptedException {
      WebElement tempUnit = driver.findElement(By.xpath(String.format("//div[text()='%s']", temp)));
      tempUnit.click();
      Thread.sleep(1000);

      WebElement tempUnitHeading = driver.findElement(By.xpath("//div[@class='current-temp']/span"));

      return tempUnitHeading.getText().contains(symbolTemp);
   }

   @Ignore
   @Test
   public void testChangingTempUnitInHeading_WhenSwitchTempUnitButton() throws InterruptedException {
      String url = "https://openweathermap.org/";
      String temp = "Imperial: °F, mph";
      String symbolTemp = "°F";

      getDriver().get(url);
      Thread.sleep(10000);

      Assert.assertTrue(isTempInSymbol(getDriver(), temp, symbolTemp));
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
