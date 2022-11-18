import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class BugorDmitriyTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By WE_USE_COOKIES = By.xpath("//div[@class='stick-footer-panel__container']//p[text()='We use cookies which are essential "
            + "for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. "
            + "You can allow all cookies or manage them individually.']");
    final static By ALLOW_ALL = By.xpath("//button[@class='stick-footer-panel__link']");
    final static By MANAGE_COOKIES = By.xpath("//div[@class='stick-footer-panel__btn-container']//a");
    final static By HEADING_SUPPORT_DROPDOWN = By.xpath("//div[@id='support-dropdown']");
    final static By FAQ = By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a [@href='/faq']");
    final static By HOW_TO_START = By.xpath("//ul[@class='dropdown-menu dropdown-visible']//li/a[@href='/appid']");
    final static By ASK_A_QUESTION = By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[text()='Ask a question']");
//    final static By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']/*");
    private void openBaseURL(){
        getDriver().get(BASE_URL);
    }

    private void waitForGreyFrameDisappeared(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

        @Test
        public void testH2TextWhenSearchingCityCountry() {

            String cityName = "Paris";
            String expectedResult = "Paris, FR";

            openBaseURL();
            waitForGreyFrameDisappeared();

            String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
            click(SEARCH_CITY_FIELD, getWait5());
            input(cityName, SEARCH_CITY_FIELD, getDriver());
            click(SEARCH_BUTTON, getWait10());
            waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
            click(PARIS_FR_CHOICE_DROPDOWN_MENU, getWait5());
            waitTextChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

            String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

            Assert.assertEquals(actualResult, expectedResult);
    }

            @Test
        public void testConfirmTextAboutCookies() {

            String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential"
                    + " cookies to help us improve our services. Any data collected is anonymised. You can allow all "
                    + "cookies or manage them individually.";
            String expectedResult2 = "Allow all";
            String expectedResult3 = "Manage cookies";

            openBaseURL();
            waitForGreyFrameDisappeared();

            String actualResult1WeUseCookies = getText(WE_USE_COOKIES, getDriver());
            String actualResult2AllowAll = getText(ALLOW_ALL, getDriver());
            String actualResult3ManageCookies = getText(MANAGE_COOKIES, getDriver());

            Assert.assertEquals(actualResult1WeUseCookies, expectedResult1);
            Assert.assertEquals(actualResult2AllowAll, expectedResult2);
            Assert.assertEquals(actualResult3ManageCookies, expectedResult3);
    }

            @Ignore
            @Test
        public void testMenuSupport() {

            String expectedResult1 = "FAQ";
            String expectedResult2 = "How to start";
            String expectedResult3 = "Ask a question";

            openBaseURL();
            waitForGreyFrameDisappeared();

            click(HEADING_SUPPORT_DROPDOWN, getWait5());

//            Assert.assertEquals(SUPPORT_DROPDOWN_MENU).size(), 3);
//            getDriver().findElements(By.xpath("//ul[@id='support-dropdown-menu']/*")).size(), 3
//            );

            String actualResult1FAQ = getText(FAQ, getDriver());
            String actualResult2HowToStart = getText(HOW_TO_START, getDriver());
            String actualResult3AskAQuestion = getText(ASK_A_QUESTION, getDriver());

            Assert.assertEquals(actualResult1FAQ, expectedResult1);
            Assert.assertEquals(actualResult2HowToStart, expectedResult2);
            Assert.assertEquals(actualResult3AskAQuestion, expectedResult3);
    }
   }