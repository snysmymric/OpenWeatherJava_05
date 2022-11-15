import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class LiudmilaPlucciTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU =  By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class=\"search-dropdown-menu\"]//li//span[text() = 'Paris, FR ']");
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

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName,SEARCH_CITY_FIELD,getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER,oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult,expectedResult);

    }
    @Ignore
    @Test
    public void testApproveGuidePage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement ButtonGuide = getDriver().findElement(
                By.xpath("//a[@href='/guide']"));

        ButtonGuide.click();

        String actualResult2 = getDriver().getTitle();
        String actualResult1 = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult1,expectedResult1);
        Assert.assertEquals(actualResult2,expectedResult2);
    }
    @Ignore
    @Test
    public void testApproveTwoButtonsInPanel() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        getDriver().get(url);

        Thread.sleep(10000);
        WebElement textElement = getDriver().findElement(
                By.className("stick-footer-panel__description"));

        WebElement buttonAllowAll = getDriver().findElement(By.xpath("//button[text()='Allow all']"));
        WebElement buttonManageCookies = getDriver().findElement(
                By.xpath("//a[@href='/cookies-settings']"));

        Assert.assertEquals(buttonAllowAll.getText(),"Allow all");
        Assert.assertEquals(buttonManageCookies.getText(),"Manage cookies");
        Assert.assertEquals(textElement.getText(),expectedResult);
    }
    @Ignore

    @Test
    public void testApproveMenuSupportWithThreeSubMenu() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        getDriver().get(url);
        Thread.sleep(7000);
        WebElement supportDropdown = getDriver().findElement(By.xpath("//div[@id='support-dropdown']"));
        supportDropdown.click();
        Thread.sleep(3000);

        WebElement checkIfTextFAQIsPresent = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));
        WebElement checkIfTextHowToStartIsPresent = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']"));
        WebElement checkIfTextAskAQuestionIsPresent = getDriver().findElement(By.xpath(
                "//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));

        Assert.assertEquals(checkIfTextFAQIsPresent.getText(), expectedResultFAQ);
        Assert.assertEquals(checkIfTextHowToStartIsPresent.getText(), expectedResultHowToStart);
        Assert.assertEquals(checkIfTextAskAQuestionIsPresent.getText(), expectedResultAskAQuestion);
    }
}
