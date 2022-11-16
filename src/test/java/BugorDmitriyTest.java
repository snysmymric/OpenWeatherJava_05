import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @Ignore
            @Test
        public void testConfirmText() throws InterruptedException {
            String url = "https://openweathermap.org/";
            String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential"
                    + " cookies to help us improve our services. Any data collected is anonymised. You can allow all "
                    + "cookies or manage them individually.";
            String expectedResult2 = "Allow all";
            String expectedResult3 = "Manage cookies";

            getDriver().get(url);
            Thread.sleep(10000);

            String actualResult1 = getDriver().findElement(
                    By.xpath("//div[@class='stick-footer-panel__container']//p[text()='We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.']")
            ).getText();

            String actualResultAllowAll = getDriver().findElement(
                    By.xpath("//button[@class='stick-footer-panel__link']")).getText();

            String actualResultManageCookies = getDriver().findElement(
                    By.xpath("//div[@class='stick-footer-panel__btn-container']//a")).getText();

            Assert.assertEquals(actualResult1, expectedResult1);
            Assert.assertEquals(actualResultAllowAll, expectedResult2);
            Assert.assertEquals(actualResultManageCookies, expectedResult3);
    }

    @Ignore
        @Test
        public void testMenuSupport() throws InterruptedException {
            String url = "https://openweathermap.org/";
            String expectedResult1 = "FAQ";
            String expectedResult2 = "How to start";
            String expectedResult3 = "Ask a question";

            getDriver().get(url);
            Thread.sleep(10000);

            WebElement headingSupportDropdown = getDriver().findElement(
                By.xpath("//div[@id='support-dropdown']")
            );
            headingSupportDropdown.click();
            Thread.sleep(5000);

            Assert.assertEquals(
            getDriver().findElements(By.xpath("//ul[@id='support-dropdown-menu']/*")).size(), 3
            );

            String actualResultFAQ = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a [@href='/faq']")).getText();
            String actualResultHowToStart = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//li/a[@href='/appid']")).getText();
            String actualResultAskAQuestion = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[text()='Ask a question']")).getText();

            Assert.assertEquals(actualResultFAQ, expectedResult1);
            Assert.assertEquals(actualResultHowToStart, expectedResult2);
            Assert.assertEquals(actualResultAskAQuestion, expectedResult3);
    }
   }