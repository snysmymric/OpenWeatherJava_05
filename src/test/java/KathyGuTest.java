//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//import org.openqa.selenium.Keys;
//
//import java.util.List;
//
//@Ignore
//public class KathyGuTest extends BaseTest {
//    final static String BASE_URL = "https://openweathermap.org/";
//    final static By GUIDE_TAB = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");
//    final static By LOGO = By.className("logo");
//    final static By SEARCH_CITY_FIELD =  By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
//    final static By SEARCH_DROPDOWN_MENU= By.className("search-dropdown-menu");
//
//    private void openBaseUrl() {
//        getDriver().get(BASE_URL);
//    }
//    private void waitForGreyFrameDisappeared() {
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
//            By.className("owm-loader-container")));
//    }
//    private String getCurrentUrl() {
//       return getDriver().getCurrentUrl();
//    }
//    private String getTitle() {
//        return getDriver().getTitle();
//    }
//    private void click (By by, WebDriverWait wait) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
//    }
//    private void clickEnter (By by, WebDriverWait wait) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(Keys.ENTER);
//    }
//    private void input(String text, By by, WebDriver driver) {
//        driver.findElement(by).sendKeys(text);
//    }
//    private void waitElementToBeVisible(By by, WebDriverWait wait){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//    }
//
//    @Test
//
//    public void testStartPageTitleAndURL_WhenOpenBaseUrl() {
//        String expectedTitle = "Сurrent weather and forecast - OpenWeatherMap";
//        String expectedUrl = "https://openweathermap.org/";
//
//        openBaseUrl();
//        waitForGreyFrameDisappeared();
//
//        Assert.assertEquals(getTitle(), expectedTitle);
//        Assert.assertEquals(getCurrentUrl(), expectedUrl);
//    }
//    @Test
//
//    public void testClickOnLogoRedirectUserToStartPage() {
//        String startPage = "https://openweathermap.org/";
//
//        openBaseUrl();
//        waitForGreyFrameDisappeared();
//        click(GUIDE_TAB, getWait5());
//        click(LOGO, getWait5());
//
//        Assert.assertEquals(getCurrentUrl(),startPage);
//    }
//
//    @Test
//
//    public void testSearchForCityByCityName() {
//        String cityName = "Paris";
//
//        openBaseUrl();
//        waitForGreyFrameDisappeared();
//        click(SEARCH_CITY_FIELD, getWait5());
//        input(cityName, SEARCH_CITY_FIELD,getDriver());
//        clickEnter(SEARCH_CITY_FIELD,getWait5());
//        waitElementToBeVisible(SEARCH_DROPDOWN_MENU,getWait10());
//        List<WebElement> cityNameList = getDriver().findElements(
//                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[contains(text(), 'Paris')]")
//        );
//
//        Assert.assertEquals(cityNameList.size(), 5);
//        for (int i = 0; i < cityNameList.size(); i ++) {
//           Assert.assertTrue(cityNameList.get(i).getText().contains(cityName));
//       }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
