import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Maksim2404Test extends BaseTest {
    final static String Base_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private void openBaseURL(){
        getDriver().get(Base_URL);
    }
    private void waitForGrayFrameDisappeared(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }
    private String getText(By by, WebDriver driver){
        return driver.findElement(by).getText();
    }
    private void click(By by, WebDriver driver){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait5().until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void input(String text, By by, WebDriver driver){
        driver.findElement(by).sendKeys(text);
    }
    private void waitElementToBeVisible(By by, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait){
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }
    @Test
    public void testH2TextWhenSearchingCityCountry() throws InterruptedException {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getDriver());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getDriver());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_CHOICE_IN_DROP_DOWN_MENU, getDriver());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());
        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Ignore
    @Test
    public void testConfirmThatSupportHave3Types() throws InterruptedException {
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchButton = getDriver().findElement(
                By.id("support-dropdown")
        );
        searchButton.click();
        Thread.sleep(2000);

        WebElement confirm1 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/faq']"));

        String actualResult1 = confirm1.getText();
        Thread.sleep(2000);

        WebElement confirm2 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']")
        );

        String actualResult2 = confirm2.getText();
        Thread.sleep(2000);

        WebElement confirm3 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']")
        );

        String actualResult3 = confirm3.getText();


        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }

    @Ignore
    @Test
    public void testconfirmSite() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement getLogo = getDriver().findElement(
                By.xpath("//li[@class='logo']"));
        getLogo.click();
        Thread.sleep(5000);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
    }
}