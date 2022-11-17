import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class NataliadylaiTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");

    final static By MENU_GUIDE = By.xpath("//div[@id='desktop-menu']//a[contains (text(), 'Guide')]");

    final static By GUIDE_TITLE = By.xpath("//div/ul/li/a[@href = '/guide']");

    private void openBaseURL(){
        getDriver().get(BASE_URL);
    }
    private  void waitForGrayFrameDisappeared(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className(
                "owm-loader-container"
        )));
    }
    private String getText(By by, WebDriver driver){
        return driver.findElement(by).getText();
    }

    private void click(By by, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void input(String text, By by, WebDriver driver){
        driver.findElement(by).sendKeys(text);
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait){
        wait .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {

        wait.until(ExpectedConditions.
                not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    @Ignore
    @Test
    public void testH2TagText_WhenSearchCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();
        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName,SEARCH_CITY_FIELD,getDriver());
        click(SEARCH_BUTTON,getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header,getDriver(),getWait10());
        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }




    @Test
    public void test_OpenWeatherMapAPIGuide () throws InterruptedException {
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "Guide";
        openBaseURL();
        waitForGrayFrameDisappeared();
        click(MENU_GUIDE, getWait10());

        String actualResultTitle = getText(GUIDE_TITLE, getDriver());

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }


    @Ignore
    @Test
    public void testChangingTempUnitInHeading_WhenSwitchTempUnitButton() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(10000);
        WebElement tempUnit = getDriver().findElement(
                By.xpath("//div[text()='Imperial: °F, mph']"));
        tempUnit.click();
        Thread.sleep(2000);
        WebElement tempUnitHeading = getDriver().findElement(
                By.xpath("//div[@class='current-temp']/span"));

        boolean actualResult = tempUnitHeading.getText().contains("°F");

        Assert.assertTrue(actualResult);
    }

    @Ignore
    @Test
    public void test_ConfirmCookiesOnTheFooter() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String button1 = "Allow";
        String button2 = "Manage cookies";
        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        Assert.assertTrue(getDriver().findElement(By.className("stick-footer-panel__container")).isDisplayed());
        Assert.assertEquals(getDriver().findElements(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")).size(), 2);
        WebElement cookies = getDriver().findElement(
                By.className("stick-footer-panel__description"));
        Thread.sleep(2000);
        String actualResult = cookies.getText();
        Assert.assertEquals(actualResult,expectedResult);

        Assert.assertTrue(getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = 'Allow all']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = ' Manage cookies ']")).isDisplayed());
    }
    @Ignore
    @Test
    public void test_checkSupportDropdownOnPAgeHeader() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement support = getDriver().findElement(
                By.xpath("//div[@id = 'support-dropdown']"));
        support.click();
        Assert.assertEquals(getDriver().findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/li")).size(), 3);
        WebElement faq = getDriver().findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']//a[@href ='/faq']")
        );
        String actualResult1 = faq.getText();
        Assert.assertEquals(actualResult1,expectedResult1);
        WebElement start = getDriver().findElement(
                By.xpath("//ul[@id ='support-dropdown-menu']//a[@href ='/appid']")
        );
        String actualResult2 = start.getText();
        Assert.assertEquals(actualResult2,expectedResult2);
        WebElement question = getDriver().findElement(
                By.xpath("//ul[@id ='support-dropdown-menu']//a[@href = 'https://home.openweathermap.org/questions']")
        );
        String actualResult3 = question.getText();
        Assert.assertEquals(actualResult3,expectedResult3);
    }
    @Ignore
    @Test
    public void test_SwitchingMeasurementsBetweenFandC() throws InterruptedException {
        String url = "https://openweathermap.org/";
        boolean expectedResult = true;

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement imperial = getDriver().findElement(
                By.xpath("//div[@class= 'option'][2]")
        );
        imperial.click();
        Thread.sleep(3000);
        WebElement metric = getDriver().findElement(
                By.xpath("//div[@class= 'option'][1]")
        );
        metric.click();
        Thread.sleep(3000);
        boolean actualResult = metric.getText().contains("°C");
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Ignore
    @Test
    public void test_CorrectPageUpdatedAfterClickOnLogo() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";
        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement logo = getDriver().findElement(
                By.xpath("//a[@href]//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        logo.click();
        Thread.sleep(2000);
        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
        Thread.sleep(2000);
    }
}