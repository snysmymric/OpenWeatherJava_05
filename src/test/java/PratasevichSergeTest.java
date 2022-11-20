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
//public class PratasevichSergeTest extends BaseTest {
//    final static String Base_URL = "https://openweathermap.org/";
//    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
//    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
//    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
//    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
//    final static By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
//            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
//    final static By SIGN_IN_MENU = By.xpath("//li[@class='user-li']//a");
//    final static By SIGN_IN_PAGE_TITLE = By.xpath("//h3[@class='first-child']");
//    final static By FILL_IN_EMAIL_FIELD= By.xpath(
//            "//div[@class='input-group']//input[@class='string email optional form-control']");
//    final static By FILL_IN_PASSWORD_FIELD = By.xpath(
//            "//div[@class='input-group']//input[@id='user_password']");
//    final static By SUBMIT_BUTTON = By.xpath("//input[@value='Submit']");
//    final static By USER_CONTAINER = By.xpath("//div[@class='inner-user-container']");
//
//
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
//    private void clickMenu(By by, WebDriver driver){
//        driver.findElement(by).click();
//    }
//
//    private String getTitleSignIn(By by, WebDriverWait wait, WebDriver driver){
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
//    }
//
//    private String getLoggedInUserName(By by, WebDriverWait wait, WebDriver driver){
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
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
//    @Test
//    public void testSignInMenuIsClickable(){
//        String expectedResult = "Sign In To Your Account";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        clickMenu(SIGN_IN_MENU,getDriver());
//
//        String actualResult = getTitleSignIn(SIGN_IN_PAGE_TITLE, getWait20(),getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//    @Test
//    public void testSignInUserIsLoggedInWithValidInput(){
//        String email = "jka59433@xcoxc.com";
//        String password ="Tester12#";
//        String expectedResult = "Tester";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        clickMenu(SIGN_IN_MENU,getDriver());
//        click(FILL_IN_EMAIL_FIELD, getDriver());
//        input(email, FILL_IN_EMAIL_FIELD, getDriver());
//        click(FILL_IN_PASSWORD_FIELD, getDriver());
//        input(password, FILL_IN_PASSWORD_FIELD, getDriver());
//        click(SUBMIT_BUTTON, getDriver());
//
//        String actualResult = getLoggedInUserName(USER_CONTAINER, getWait20(), getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//}
