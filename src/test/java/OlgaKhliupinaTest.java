import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import java.util.List;

public class OlgaKhliupinaTest extends BaseTest {

   final static By SWITCH_TEMP_F = By.xpath("//div[text()='Imperial: °F, mph']");
   final static String SYMBOL_TEMP_F = "°F";
   final static  By SWITCH_TEMP_C = By.xpath("//div[text()='Metric: °C, m/s']");
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
   final static By CURRENT_WEATHER_ICON = By.xpath("//div/a[@href='/current']");
   final static By HOURLY_FORECAST_ICON = By.xpath("//div/a[@href='/api/hourly-forecast']");
   final static By DAILY_FORECAST_ICON = By.xpath("//div/a[@href='/forecast16']");
   final static By CLIMATIC_FORECAST_ICON = By.xpath("//div/a[@href='/api/forecast30']");
   final static By HISTORICAL_WEATHER_ICON = By.xpath("//div/a[@href='/history']");
   final static By SOCIAL_PANEL_ICONS = By.xpath("//div[@class='social']/a");
   final static By WEATHER_DATA_ICONS = By.xpath("//div/a[@class='stats white-text']");
   final static By DAY_LIST_VALUES = By.xpath("//div[@class='day-list-values']/div/span");

   @Test
   public void testLinkAndTitle_WhenGoingToGuideMenu() {
      final String expectedUrl = "https://openweathermap.org/guide";
      final String expectedTitle = "Guide";

      openBaseURL();
      click(MENU_GUIDE);

      String actualResultTitle = getText(GUIDE_TITLE);

      Assert.assertEquals(getCurrentURL(), expectedUrl);
      Assert.assertEquals(actualResultTitle, expectedTitle);
   }

   @Test
   public  void testCheckUrlTitle_AfterClickOnLogo() {
      final String expectedURL = "https://openweathermap.org/";
      final String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

      openBaseURL();
      click(LOGO);

      Assert.assertEquals(getCurrentURL(), expectedURL);
      Assert.assertEquals(getTitle(), expectedResultTitle);
   }

   @Test
   public void testChangingTempUnitInHeadingToF_WhenSwitchTempUnitButtonToF() {
      openBaseURL();
      click(SWITCH_TEMP_F);
      waitForGrayContainerDisappeared();

      Assert.assertTrue(isTextContains(TEMP_UNIT_HEADING, SYMBOL_TEMP_F));
   }

   @Test
   public void testChangingTempUnitInHeadingToC_WhenSwitchTempUnitButtonToC() {
      openBaseURL();
      click(SWITCH_TEMP_C);
      waitForGrayContainerDisappeared();

      Assert.assertTrue(isTextContains(TEMP_UNIT_HEADING, SYMBOL_TEMP_C));
   }

   @Test
   public void testCheckTempInF_inDayList_whenSwitchTempToF() {
      openBaseURL();
      click(SWITCH_TEMP_F);
      waitForGrayContainerDisappeared();

      getListOfElements(DAY_LIST_VALUES)
              .forEach(element -> Assert.assertTrue(isTextContains(DAY_LIST_VALUES, SYMBOL_TEMP_F)));
   }

   @Test
   public void testGithubIconExistsAfterClickNavigateToGithub() {
      final String expectedURL = "https://github.com/search?q=openweathermap&ref=cmdform";

      openBaseURL();
      scrollByVisibleElement(GITHUB_ICON);

      Assert.assertTrue(isDisplayed(GITHUB_ICON));

      click20(GITHUB_ICON_LINK);
      switchToAnotherWindow(getDriver());

      Assert.assertEquals(getCurrentURL(), expectedURL);
   }

   @Test
   public void testSocialPanelExistsAndHas6Icons() {
      final int expectedQuantity = 6;

      openBaseURL();

      Assert.assertTrue(isDisplayed(SOCIAL_PANEL));
      Assert.assertEquals(getListSize(SOCIAL_PANEL_ICONS), expectedQuantity);
   }

   @Test
   public void test5IconsAreDisplayed_SectionOrangeBackgroundWhiteText() {
      final int expectedQuantity = 5;

      openBaseURL();

      Assert.assertEquals(getListSize(WEATHER_DATA_ICONS), expectedQuantity);
      List.of(CURRENT_WEATHER_ICON, HOURLY_FORECAST_ICON, DAILY_FORECAST_ICON, CLIMATIC_FORECAST_ICON,
              HISTORICAL_WEATHER_ICON)
              .forEach(icon -> Assert.assertTrue(isDisplayed(icon)));
   }
}
