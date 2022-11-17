import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class Dasha1991Test extends BaseTest {

    final static String  BASE_URL = "https://openweathermap.org/";
    final static By GUIDE_TITLE = By.className("breadcrumb-title");
    final static By MENU_GUIDE = By.xpath("//div/ul//li/a[@href='/guide']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }
    private void waitForGrayFrameDissappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }
    private String getText(By by,WebDriver driver) {
        return driver.findElement(by).getText();
    }
    private void click(By by, WebDriverWait wait) {

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait5().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Test
    public void testButtonGuide_OpenWeather() {

        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "Guide";

        openBaseURL();
        waitForGrayFrameDissappeared();
        click(MENU_GUIDE, getWait10());

        String actualResultTitle = getText(GUIDE_TITLE, getDriver());

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

    }
    @Ignore
    @Test
    public void testTemperatureF() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement temperatureF = getDriver().findElement(By.xpath("//div[@class = 'option'][2]"));

        temperatureF.click();
        Thread.sleep(2000);

        WebElement imageFTemperature = getDriver().findElement(By.xpath("//span[@class = 'heading']"));

        boolean actualResult = imageFTemperature.getText().contains("°F");
        Assert.assertTrue(actualResult);

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

        WebElement findSupportButton = getDriver().findElement(By.xpath("//div[@id='support-dropdown']"));

        findSupportButton.click();
        Thread.sleep(2000);

        WebElement buttonFAQ = getDriver().findElement(By.xpath
                ("//ul[@class = 'dropdown-menu dropdown-visible']//li/a[@href = '/faq']"));
        WebElement buttonHowToStart = getDriver().findElement(By.xpath
                ("//ul[@class = 'dropdown-menu dropdown-visible']/li[2]//a"));
        WebElement buttonAskAQuestion = getDriver().findElement(By.xpath
                ("//ul[@class = 'dropdown-menu dropdown-visible']//li[3]/a"));
        Thread.sleep(2000);
        String actualResult1 = buttonFAQ.getText();
        Assert.assertEquals(actualResult1, expectedResult1);

        String actualResult2 = buttonHowToStart.getText();
        Assert.assertEquals(actualResult2, expectedResult2);

        String actualResult3 = buttonAskAQuestion.getText();
        Assert.assertEquals(actualResult3, expectedResult3);
    }
    @Ignore
    @Test
    public void testCheckUseCookies() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. Any data collected " +
                "is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement textUseCookies = getDriver().findElement(By.className("stick-footer-panel__description"));
        WebElement buttonAllowAll = getDriver().findElement(By.xpath("//button[@type = 'button']"));
        WebElement buttonManageCookies = getDriver().findElement(By.xpath
                ("//a[@class='stick-footer-panel__link']"));

        String actualResult = textUseCookies.getText();
        Assert.assertEquals(actualResult, expectedResult);

        String actualResult2 = buttonAllowAll.getText();
        Assert.assertEquals(actualResult2, expectedResult2);

        String actualResult3 = buttonManageCookies.getText();
        Assert.assertEquals(actualResult3, expectedResult3);
    }
}
