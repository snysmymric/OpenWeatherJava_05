//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//@Ignore
//public class SafOlgaTest extends BaseTest {
//
//    final static String BASE_URL = "https://openweathermap.org/";
//
//    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
//    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
//    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
//    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
//    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
//
//    private void openBaseURL() {
//        getDriver().get(BASE_URL);
//    }
//
//    private void waitForGrayFrameDisappeared() {
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
//                By.className("owm-loader-container")));
//    }
//
//    private String getText(By by, WebDriver driver) {
//
//        return driver.findElement(by).getText();
//    }
//
//    private void click(By by,  WebDriverWait wait) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
//    }
//
//    private void input(String text, By by, WebDriver driver) {
//        driver.findElement(by).sendKeys(text);
//    }
//
//    private void waitElementToBeVisible(By by, WebDriverWait wait) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//    }
//
//    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
//        wait.until(ExpectedConditions
//                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
//    }
//
//    private void inputKeys(String text, By by, WebDriver driver) {
//        driver.findElement(by).sendKeys(text + Keys.ENTER);
//    }
//
//    @Test
//    public void testNoResultsMassegeInSearchBar() {
//
//        String cityName = "pome,";
//        String expectedResult = "No results for " + cityName;
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//
//        click(SEARCH_CITY_FIELD, getWait5());
//        inputKeys(cityName, SEARCH_CITY_FIELD, getDriver());
//
//
//        WebElement messageError = getDriver().findElement(
//                By.xpath("//div[@class='widget-notification']"));
//
//        String actualResult = messageError.getText();
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//}
