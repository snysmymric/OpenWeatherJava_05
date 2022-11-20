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
//public class NataliadylaiTest extends BaseTest {
//
//    final static String BASE_URL = "https://openweathermap.org/";
//
//    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
//    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
//    final static By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
//    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
//    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
//
//    final static By MENU_GUIDE = By.xpath("//div[@id='desktop-menu']//a[contains (text(), 'Guide')]");
//
//    final static By GUIDE_TITLE = By.xpath("//div/ul/li/a[@href = '/guide']");
//    final static By TEMP_UNIT = By.xpath("//div[text()='Imperial: °F, mph']");
//    final static By TEMP_UNIT_HEADING = By.xpath("//div[@class='current-temp']/span");
//    final static By LOGO = By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
//
//
//    private void openBaseURL(){
//        getDriver().get(BASE_URL);
//    }
//    private  void waitForGrayFrameDisappeared(){
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className(
//                "owm-loader-container"
//        )));
//    }
//    private String getText(By by, WebDriver driver){
//        return driver.findElement(by).getText();
//    }
//
//    private void click(By by, WebDriverWait wait){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
//    }
//
//    private void clickElement(By by, WebDriver driver){
//        driver.findElement(by).click();
//    }
//
//    private void input(String text, By by, WebDriver driver){
//        driver.findElement(by).sendKeys(text);
//    }
//
//    private void waitElementToBeVisible(By by, WebDriverWait wait){
//        wait .until(ExpectedConditions.visibilityOfElementLocated(by));
//    }
//
//    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
//
//        wait.until(ExpectedConditions.
//                not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
//    }
//
//    @Ignore
//    @Test
//    public void testH2TagText_WhenSearchCityCountry() {
//        String cityName = "Paris";
//        String expectedResult = "Paris, FR";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
//        click(SEARCH_CITY_FIELD, getWait5());
//        input(cityName,SEARCH_CITY_FIELD,getDriver());
//        click(SEARCH_BUTTON,getWait5());
//        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
//        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
//        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header,getDriver(),getWait10());
//        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//
//
//
//    @Ignore
//    @Test
//    public void test_OpenWeatherMapAPIGuide () throws InterruptedException {
//        String expectedResultUrl = "https://openweathermap.org/guide";
//        String expectedResultTitle = "Guide";
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        click(MENU_GUIDE, getWait10());
//
//        String actualResultTitle = getText(GUIDE_TITLE, getDriver());
//
//        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
//        Assert.assertEquals(actualResultTitle, expectedResultTitle);
//    }
//
//
//    @Test
//    public void testChangingTempUnitInHeading_WhenSwitchTempUnitButton()  {
//       openBaseURL();
//       waitForGrayFrameDisappeared();
//       click(TEMP_UNIT, getWait20());
//       waitElementToBeVisible(TEMP_UNIT_HEADING, getWait20());
//       Assert.assertTrue(getText(TEMP_UNIT_HEADING, getDriver()).contains("°F"));
//    }
//
//    @Ignore
//    @Test
//    public void test_CorrectPageUpdatedAfterClickOnLogo() {
//        String expectedResult = "https://openweathermap.org/";
//        String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        click(LOGO,getWait20());
//        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
//        Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
//    }
//}