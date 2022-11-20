import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.Set;

@Ignore
public class StadAlexTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }


    //явное ожидание - explicitly wait
    //WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

    @Ignore
    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testAskAQuestionIsClickable_LinkOpensCorrectly_EverythingIsClickableAsExpected() throws InterruptedException {

        By SUPPORT_DROPDOWN_MENU = By.xpath("//div[@id = \"support-dropdown\"]");
        By ASK_A_QUESTION = By.xpath("//ul[@id=\"support-dropdown-menu\"]/li[3]/a");

        By YES_AT_OPENWEATHER_USER = By.xpath("//form//span[1]//input");
        By NO_AT_OPENWEATHER_USER = By.xpath("//form//span[2]//input");

        By E_MAIL = By.xpath("//form//input[@class=\"form-control string email required\"]");

        By SUBJECT_DROPDOWN_MENU = By.xpath("//select[@id]");
        By SUBJECT_DROPDOWN_MENU_NOTHING = By.xpath("//select[@id]/option[1]");
        By SUBJECT_DROPDOWN_MENU_SALES = By.xpath("//select[@id]/option[2]");
        By SUBJECT_DROPDOWN_MENU_DATA_ISSUE = By.xpath("//select[@id]/option[3]");
        By SUBJECT_DROPDOWN_MENU_TECH_ISSUE = By.xpath("//select[@id]/option[4]");
        By SUBJECT_DROPDOWN_MENU_SUBSCRIPTION = By.xpath("//select[@id]/option[5]");
        By SUBJECT_DROPDOWN_MENU_INITIATIVES = By.xpath("//select[@id]/option[6]");
        By SUBJECT_DROPDOWN_MENU_OTHER = By.xpath("//select[@id]/option[7]");

        By MESSAGE = By.xpath("//textarea[@id = \"question_form_message\"]");

        By SUBMIT = By.xpath("//input[@class=\"btn btn-default\"]");

        String finalURL = ("https://home.openweathermap.org/questions");
        String expectedResult = finalURL;
        String expectedResult1 = ("reCAPTCHA verification failed, please try again.");

        openBaseURL();
        waitForGrayFrameDisappeared();
        String window1 = getDriver().getWindowHandle();

        click(SUPPORT_DROPDOWN_MENU, getWait5());
        click(ASK_A_QUESTION, getWait10());

        Set<String> currentWindows = getDriver().getWindowHandles();
        String window2 = null;

        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }

        getDriver().switchTo().window(window2);
        String actualResult = getDriver().getCurrentUrl();

        click(YES_AT_OPENWEATHER_USER, getWait5());

        click(NO_AT_OPENWEATHER_USER, getWait5());

        input("1234@1234.com", E_MAIL, getDriver());

        click(SUBJECT_DROPDOWN_MENU, getWait5());
        click(SUBJECT_DROPDOWN_MENU_NOTHING, getWait5());
        click(SUBJECT_DROPDOWN_MENU, getWait5());
        click(SUBJECT_DROPDOWN_MENU_SALES, getWait5());
        click(SUBJECT_DROPDOWN_MENU, getWait5());
        click(SUBJECT_DROPDOWN_MENU_DATA_ISSUE, getWait5());
        click(SUBJECT_DROPDOWN_MENU, getWait5());
        click(SUBJECT_DROPDOWN_MENU_TECH_ISSUE, getWait5());
        click(SUBJECT_DROPDOWN_MENU, getWait5());
        click(SUBJECT_DROPDOWN_MENU_SUBSCRIPTION, getWait5());
        click(SUBJECT_DROPDOWN_MENU, getWait5());
        click(SUBJECT_DROPDOWN_MENU_INITIATIVES, getWait5());
        click(SUBJECT_DROPDOWN_MENU, getWait5());
        click(SUBJECT_DROPDOWN_MENU_OTHER, getWait5());

        input("HELLO WORLD!", MESSAGE, getDriver());

        click(SUBMIT, getWait5());
        String actualResult1 = getText(By.xpath("//div[@class=\"help-block\"]"), getDriver());

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult1, expectedResult1);

        getDriver().close();
        getDriver().switchTo().window(window1);

    }
}