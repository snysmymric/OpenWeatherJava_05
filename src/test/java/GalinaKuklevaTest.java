import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class GalinaKuklevaTest extends BaseTest {
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
    public void testOpenWeatherMapGuidePageTitle() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        String expectedResult_1 = "https://openweathermap.org/guide";
        String expectedResult_2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(baseUrl);
        Thread.sleep(7000);
        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//li/a [@href='/guide']")
        );
        guideButton.click();
        String actualResult_1 = getDriver().getCurrentUrl();
        String actualResult_2 = getDriver().getTitle();

        Assert.assertEquals(actualResult_1, expectedResult_1);
        Assert.assertEquals(actualResult_2, expectedResult_2);
    }

    @Ignore
    @Test
    public void testTemperatureInFahrenheits() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(baseUrl);
        Thread.sleep(7000);
        WebElement imperialData = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']")
        );
        imperialData.click();
        WebElement tempInTheCity = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//span[@class = 'heading']")
        );
        Boolean actualResult = tempInTheCity.getText().contains(expectedResult);

        Assert.assertTrue(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testNonCaptchaVerification() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        String eMail = "tester@test.com";
        String message = "Hello, World!";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        getDriver().get(baseUrl);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement supportButton = getDriver().findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        supportButton.click();
        Thread.sleep(5000);
        WebElement subMenuAskAQuestion = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text() = 'Ask a question']")
        );
        subMenuAskAQuestion.click();
        Thread.sleep(10000);
        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() != 1;
        Thread.sleep(10000);
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
        WebElement emailField = getDriver().findElement(
                By.id("question_form_email")
        );
        emailField.sendKeys(eMail);
        Thread.sleep(5000);
        WebElement subjectField = getDriver().findElement(
                By.id("question_form_subject")
        );
        subjectField.click();
        Thread.sleep(5000);
        WebElement optionTech = getDriver().findElement(
                By.xpath("//select[@id = 'question_form_subject']/option[@value = 'Tech Issue']")
        );
        optionTech.click();
        Thread.sleep(5000);
        WebElement messageField = getDriver().findElement(
                By.id("question_form_message")
        );
        messageField.sendKeys(message);
        Thread.sleep(5000);
        WebElement submitField = getDriver().findElement(
                By.xpath("//form[@id = 'new_question_form']//input[@value = 'Submit']")
        );
        submitField.click();
        Thread.sleep(5000);
        WebElement submitVerification = getDriver().findElement(
                By.xpath("//div[text() = 'reCAPTCHA verification failed, please try again.']")
        );
        String actualResult = submitVerification.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testDeskTopMenuClickAPIFind30Buttons1() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        getDriver().get(baseUrl);
        getDriver().manage().window().maximize();
        int expectedResult = 30;
        Thread.sleep(10000);
        WebElement element = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        element.click();
        int actualResult = getDriver().findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
