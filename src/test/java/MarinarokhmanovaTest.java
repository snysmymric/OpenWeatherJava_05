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
//public class MarinarokhmanovaTest extends BaseTest {
//    final static String Base_URL = "https://openweathermap.org/";
//    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
//    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
//    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
//    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
//    final static By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
//            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
//
//    final static By GUIDE_SEARCH_FIELD = By.xpath("//a[@href][text()='Guide']");
//
//    final static By LOGO_OPEN_WEATHER =
//            By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
//
//    final static By UNITS_IMPERIAL_F = By.xpath("//div[@class ='option'][text()='Imperial: °F, mph']");
//
//    final static By UNITS_IMPERIAL_F_CITY = By.xpath("//div[@class ='current-temp']/span");
//
//
//    private void openBaseURL(){
//        getDriver().get(Base_URL);
//    }
//
//    private void waitForGrayFrameDisappeared(){
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
//    }
//
//    private String getText(By by, WebDriver driver){
//        return driver.findElement(by).getText();
//    }
//
//    private void click(By by, WebDriver driver){
//        getWait5().until(ExpectedConditions.visibilityOfElementLocated(by));
//        getWait5().until(ExpectedConditions.elementToBeClickable(by)).click();
//    }
//
//    private void input(String text, By by, WebDriver driver){
//        driver.findElement(by).sendKeys(text);
//    }
//
//    private void waitElementToBeVisible(By by, WebDriverWait wait){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//    }
//
//    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait){
//        wait.until(ExpectedConditions
//                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
//    }
//
//    @Test
//    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
//
//        String cityName = "Paris";
//        String expectedResult = "Paris, FR";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//
//        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
//
//        click(SEARCH_CITY_FIELD, getDriver());
//        input(cityName, SEARCH_CITY_FIELD, getDriver());
//        click(SEARCH_BUTTON, getDriver());
//        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
//        click(PARIS_CHOICE_IN_DROP_DOWN_MENU, getDriver());
//        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());
//
//        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//
//    @Ignore
//    @Test
//    public void testOpenWeatherMapGuide() {
//
//        String expectedResult = "https://openweathermap.org/guide";
//        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//
//        click(GUIDE_SEARCH_FIELD, getDriver());
//
//        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
//        Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
//    }
//
//    @Test
//    public void testLogoComp_OpenWeather()  {
//
//        String expectedResult = "https://openweathermap.org/";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//
//        click(LOGO_OPEN_WEATHER, getDriver());
//
//        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
//    }
//}
