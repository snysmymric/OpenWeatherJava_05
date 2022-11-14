import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ViktoriyaEDTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROP_DOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']");
    final static By GUIDE_BUTTON = By.xpath("//a[@href = '/guide']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayWindowDisappeared() {
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

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    private void waitURLToBeChanged(String url2, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.urlToBe(url2));
    }


    @Test
    public void testH2TagText_WhenSearchingCityCountry() {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayWindowDisappeared();

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROP_DOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }


    @Test
    public void test_TitleText_WhenChooseMenuGuide() {

        String expectedResult = "https://openweathermap.org/guide";
        String expectedResult1 = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        waitForGrayWindowDisappeared();

        click(GUIDE_BUTTON, getWait5());

        waitURLToBeChanged(expectedResult, getDriver(), getWait5());

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
        Assert.assertEquals(getDriver().getTitle(), expectedResult1);
    }

    @Ignore
    @Test
    public void test_CheckWeather_WhenChooseFahrenheit() throws InterruptedException {

        String expectedResult = "°F";

        getDriver().get(BASE_URL);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@class='switch-container']/div[3]")).click();
        WebElement weatherInF = getDriver().findElement(By.xpath("//span[@class='heading']"));
        Thread.sleep(2000);

        Assert.assertTrue(weatherInF.getText().contains(expectedResult));
    }

    @Ignore
    @Test
    public void test_CheckCookiesText() throws InterruptedException {

        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential " +
                "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies " +
                "or manage them individually.";
        String expectedResult1 = "Allow all";
        String expectedResult3 = "Manage cookies";

        getDriver().get(BASE_URL);
        Thread.sleep(10000);

        Assert.assertTrue(getDriver().findElement(By.className("stick-footer-panel__container")).isDisplayed());

        WebElement cookiesText = getDriver().findElement(By.xpath("//div[@id='stick-footer-panel']//p"));
        WebElement allowAllButton = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//button"));
        WebElement manageCookiesButton = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//a[@href='/cookies-settings']"));

        Assert.assertEquals(cookiesText.getText(), expectedResult);
        Assert.assertEquals(allowAllButton.getText(), expectedResult1);
        Assert.assertEquals(manageCookiesButton.getText(), expectedResult3);
    }

    @Ignore
    @Test
    public void testSupportDropDownMenu() throws InterruptedException {

        String expectedResult = "FAQ";
        String expectedResult1 = "How to start";
        String expectedResult2 = "Ask a question";

        getDriver().get(BASE_URL);
//        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@id='support-dropdown']")).click();

        WebElement FAQ = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));
        WebElement HowToStart = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']"));
        WebElement AskAQuestion = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"));

        Assert.assertEquals(FAQ.getText(), expectedResult);
        Assert.assertEquals(HowToStart.getText(), expectedResult1);
        Assert.assertEquals(AskAQuestion.getText(), expectedResult2);
    }

    @Ignore
    @Test
    public void test_ClickSubmitWithoutCaptcha() throws InterruptedException {

        String emailTest = "tester@test.com";
        String message = "This is test";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        getDriver().get(BASE_URL);
//        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@id='support-dropdown']")).click();
        getDriver().findElement(By.linkText("Ask a question")).click();

        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        Assert.assertEquals(tabs.size(), 2);
        getDriver().switchTo().window(tabs.get(1));
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='question_form_email']")));

        getDriver().findElement(By.id("question_form_email")).sendKeys(emailTest);
        getDriver().findElement(By.xpath("//select[@id='question_form_subject']")).click();
        getDriver().findElement(
                By.xpath("//select[@id='question_form_subject']//option[text()='Other']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='question_form_message']")).sendKeys(message);
        getDriver().findElement(By.xpath("//input[@data-disable-with='Create Question form']")).click();
        String actualResult = getDriver().findElement(By.xpath("//div[@class='help-block']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void test_SwitchFahrenheitToCelsius() throws InterruptedException {

        getDriver().get(BASE_URL);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@id='weather-widget']//div[text()='Imperial: °F, mph']")).click();
        WebElement displayCurrentWeather = getDriver().findElement(By.xpath("//div[@class='current-temp']/span"));

        Assert.assertTrue(displayCurrentWeather.getText().contains("°F"));

        getDriver().findElement(By.xpath("//div[@id='weather-widget']//div[text()='Metric: °C, m/s']")).click();

        Assert.assertTrue(displayCurrentWeather.getText().contains("°C"));
    }

    @Ignore
    @Test
    public void test_FindRome() throws InterruptedException {

        String city = "Rome";
        boolean expectedResult = true;

        getDriver().get(BASE_URL);
        Thread.sleep(10000);

        getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//input[@placeholder='Weather in your city']")
        ).sendKeys(city + "\n");

        boolean actualResult = getDriver().getCurrentUrl().contains("find") && getDriver().getCurrentUrl().contains(city);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void test_APICheck30Buttons() throws InterruptedException {

        int expectedResult = 30;

        getDriver().get(BASE_URL);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@id='desktop-menu']//a[@href='/api']")).click();

        List<WebElement> orangeButtons29 = getDriver().findElements(By.xpath("//a[@class='btn_block orange round']"));
        List<WebElement> orangeButton1 = getDriver().findElements(By.xpath("//a[@class='ow-btn round btn-orange']"));

        int actualResult = orangeButtons29.size() + orangeButton1.size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}






