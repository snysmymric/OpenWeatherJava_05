//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//import java.util.ArrayList;
//
//@Ignore
//public class YuliaKonkovaTest extends BaseTest {
//    final static String BASE_URL = "https://openweathermap.org/";
//    final static By H2_CITY_NAME_HEADER = By.xpath("//div[@id='weather-widget']//h2");
//    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']");
//    final static By BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
//    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
//    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU =
//            By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']");
//
//    final static By SUPPORT_DROPDOWNMENU_IN_NAVBAR = By.xpath("//div[@id='support-dropdown']");
//    final static By SUBMENUES_OF_SUPPORT = By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li");
//    final static By FIRST_SUBMENUE_OF_SUPPORT =
//            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/faq']");
//    final static By SECOND_SUBMENUE_OF_SUPPORT =
//            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/appid']");
//    final static By THIRD_SUBMENUE_OF_SUPPORT =
//            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li" +
//                    "/a[@href='https://home.openweathermap.org/questions']");
//
//    private void openBaseUrl(){
//        getDriver().get(BASE_URL);
//    }
//
//    private void waitUntilGreyFrameDisappeared(){
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
//    }
//
//    private String getText (By by, WebDriver driver){
//
//        return driver.findElement(by).getText();
//    }
//
//    private void click (By by, WebDriverWait wait){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
//    }
//
//    private void input (String text, By by, WebDriver driver){
//        driver.findElement(by).sendKeys(text);
//    }
//
//    private void waitElementToBeVisible (By by, WebDriverWait wait){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//    }
//
//    private void textToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait){
//        wait.until(ExpectedConditions
//                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by),text)));
//    }
//
//    @Test
//    public void testH2TagText_WhenSearchingCityCountry(){
//        String cityName = "Paris";
//        String expectedResult = "Paris, FR";
//
//        openBaseUrl();
//        waitUntilGreyFrameDisappeared();
//
//        String oldH2Header = getText(H2_CITY_NAME_HEADER,getDriver());
//
//        click(SEARCH_CITY_FIELD, getWait10());
//        input(cityName, SEARCH_CITY_FIELD, getDriver());
//        click(BUTTON, getWait5());
//        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
//        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait5());
//        textToBeChanged(H2_CITY_NAME_HEADER, oldH2Header, getDriver(), getWait10());
//
//        String actualResult = getText(H2_CITY_NAME_HEADER,getDriver());
//
//        Assert.assertEquals(actualResult,expectedResult);
//    }
//
//    @Test
//    public void testMenuSupportHasThreeSubmenus() {
//        int expectedNumberOfSubmenues = 3;
//        String expectedFirstSubmenue = "FAQ";
//        String expectedSecondSubmenue = "How to start";
//        String expectedThirdSubmenue = "Ask a question";
//
//        openBaseUrl();
//        waitUntilGreyFrameDisappeared();
//
//        click(SUPPORT_DROPDOWNMENU_IN_NAVBAR, getWait10());
//        waitElementToBeVisible(SUBMENUES_OF_SUPPORT, getWait10());
//
//        ArrayList list = (ArrayList) getDriver().findElements(SUBMENUES_OF_SUPPORT);
//        int actualNumberOfSubmenues = list.size();
//
//        Assert.assertEquals(actualNumberOfSubmenues,expectedNumberOfSubmenues);
//        Assert.assertEquals(getText(FIRST_SUBMENUE_OF_SUPPORT, getDriver()),expectedFirstSubmenue);
//        Assert.assertEquals(getText(SECOND_SUBMENUE_OF_SUPPORT, getDriver()), expectedSecondSubmenue);
//        Assert.assertEquals(getText(THIRD_SUBMENUE_OF_SUPPORT, getDriver()), expectedThirdSubmenue);
//    }
//}
