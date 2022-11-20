//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Ignore
//public class ViktoriyaEDTest extends BaseTest {
//
//    final static String BASE_URL = "https://openweathermap.org/";
//
//    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
//    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
//    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
//    final static By SEARCH_DROP_DOWN_MENU = By.className("search-dropdown-menu");
//    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']");
//    final static By GUIDE_BUTTON = By.xpath("//a[@href = '/guide']");
//    final static By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");
//    final static By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[@class='control-el owm-switch']");
//    final static By DIFFERENT_WEATHER_POP_UP = By.xpath("//div[@class='pop-up-container']");
//
//    private void openBaseURL() {
//        getDriver().get(BASE_URL);
//    }
//
//    private void waitForGrayWindowDisappeared() {
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
//    }
//
//    private String getText(By by, WebDriver driver) {
//        return driver.findElement(by).getText();
//    }
//
//    private void click(By by, WebDriverWait wait) {
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
//        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
//    }
//
//
//    @Test
//    public void testH2TagText_WhenSearchingCityCountry() {
//
//        String cityName = "Paris";
//        String expectedResult = "Paris, FR";
//
//        openBaseURL();
//        waitForGrayWindowDisappeared();
//
//        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER, getDriver());
//
//        click(SEARCH_CITY_FIELD, getWait5());
//        input(cityName, SEARCH_CITY_FIELD, getDriver());
//        click(SEARCH_BUTTON, getWait5());
//        waitElementToBeVisible(SEARCH_DROP_DOWN_MENU, getWait10());
//        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
//        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());
//
//        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//
//    @Test
//    public void test_SupportMenuIsClickable() {
//
//        final String[] SUBMENU_NAMES = {"FAQ", "How to start", "Ask a question"};
//
//        openBaseURL();
//        waitForGrayWindowDisappeared();
//
//        click(SUPPORT_BUTTON, getWait5());
//
//        Assert.assertTrue(getDriver().findElement(
//                By.xpath("//ul[@id='support-dropdown-menu']")).getAttribute("class").toString().equals("dropdown-menu dropdown-visible"));
//
//        List<WebElement> supportSubmenuList = getDriver().findElements(
//                By.xpath("//ul[@id='support-dropdown-menu']/li/a"));
//
//        Assert.assertTrue(supportSubmenuList.size() == 3);
//
//        for (int i = 0; i < supportSubmenuList.size(); i++) {
//            Assert.assertTrue(supportSubmenuList.get(i).getText().equals(SUBMENU_NAMES[i]));
//        }
//
//        click(SUPPORT_BUTTON, getWait5());
//
//        Assert.assertTrue(getDriver().findElement(
//                By.xpath("//ul[@id='support-dropdown-menu']")).getAttribute("class").toString().equals("dropdown-menu"));
//
//        click(SUPPORT_BUTTON, getWait5());
//        click(By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"), getWait10());
//
//        Assert.assertTrue(getDriver().getCurrentUrl().contains("https://openweathermap.org/faq"));
//
//        click(SUPPORT_BUTTON, getWait5());
//        click(By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']"), getWait10());
//
//        Assert.assertTrue(getDriver().getCurrentUrl().contains("https://openweathermap.org/appid"));
//
//        click(SUPPORT_BUTTON, getWait5());
//        click(By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"), getWait10());
//
//        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
//
//        Assert.assertEquals(tabs.size(), 2);
//
//        getDriver().switchTo().window(tabs.get(1));
//        getWait5().until(ExpectedConditions.not(ExpectedConditions.urlMatches(BASE_URL)));
//
//        Assert.assertTrue(getDriver().getCurrentUrl().contains("https://home.openweathermap.org/questions"));
//    }
//
//
//    @Test
//    public void test_VerifyPopupWindowIsShownWhenClickingDifferentWeatherButton() {
//
//        openBaseURL();
//        waitForGrayWindowDisappeared();
//
//        click(DIFFERENT_WEATHER_BUTTON, getWait5());
//
//        Assert.assertTrue(getDriver().findElement(DIFFERENT_WEATHER_POP_UP).isDisplayed());
//    }
//}
//
//
//
//
//
//
