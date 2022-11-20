import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class MariaDymshaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = \"weather-widget\"]//input[@placeholder=\"Search city\"]");
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
    public void testImperial() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String fTempSymbol = "°F";
        getDriver().get(url);

        Thread.sleep(7000);

        WebElement menuImperial = getDriver().findElement(
                By.xpath("//div[@class = 'option']/following-sibling::div"));
        menuImperial.click();

        Thread.sleep(3000);

        WebElement tempF = getDriver().findElement(
                By.xpath("//div[@class = 'current-temp']/span"));

        String tempInF = tempF.getText();
        String actualResult = tempInF.substring((tempInF.length() - 2));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testOpenAndClickToGuide() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";
        getDriver().get(url);

        Thread.sleep(7000);

        WebElement searchButtonInMenu = getDriver().findElement(
                By.xpath("//li//a[text() = 'Guide']"));
        searchButtonInMenu.click();

        Thread.sleep(3000);

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Ignore
    @Test
    public void testSupportHaveThreeButton() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";
        getDriver().get(url);

        Thread.sleep(7000);

        WebElement supportDropdown = getDriver().findElement(
                By.xpath("//div[@id='support-dropdown']"));
        supportDropdown.click();

        Thread.sleep(3000);

        WebElement checkIfTextFAQIsPresent = getDriver().findElement(
                By.xpath("//ul//li//a[@href='/faq']"));

        String actualResultIfTextFAQIsPresent = checkIfTextFAQIsPresent.getText();

        WebElement checkIfTextHowToStartIsPresent = getDriver().findElement(
                By.xpath("//li//li//a[@href = '/appid']"));

        String actualResultIfTextHowToStartIsPresent = checkIfTextHowToStartIsPresent.getText();

        WebElement checkIfTextAskAQuestionIsPresent = getDriver().findElement(
                By.xpath("//li//li//a[@href = 'https://home.openweathermap.org/questions']"));

        String actualResultIfTextAskAQuestionIsPresent = checkIfTextAskAQuestionIsPresent.getText();

        Assert.assertEquals(actualResultIfTextHowToStartIsPresent, expectedResultHowToStart);
        Assert.assertEquals(actualResultIfTextFAQIsPresent, expectedResultFAQ);
        Assert.assertEquals(actualResultIfTextAskAQuestionIsPresent, expectedResultAskAQuestion);
    }

    @Ignore
    @Test
    public void testChangingTemperatureValues() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String temp = "°C";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement clickOnImperial = getDriver().findElement(
                By.xpath("//div[text()='Imperial: °F, mph']"));
        clickOnImperial.click();
        Thread.sleep(2000);

        WebElement clickOnMetric = getDriver().findElement(
                By.xpath("//div[text()='Metric: °C, m/s']"));
        clickOnMetric.click();
        Thread.sleep(2000);

        String tempC = getDriver().findElement(
                By.xpath("//span[@class = 'heading']")).getText();

        Boolean actualResult = tempC.contains(temp);

        Assert.assertTrue(actualResult);
    }
}
