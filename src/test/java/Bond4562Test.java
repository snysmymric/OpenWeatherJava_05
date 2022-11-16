import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;




public class Bond4562Test extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By SEARCH_GUIDE_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//a[@href='/guide']");
    final static By SEARCH_HEADER_ROW = By.xpath(
            "//div[@class='col-sm-7']/h1");
    final static By SEARCH_LOGO = By.xpath(
            "//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");


    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitCloseForGrayFrame() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void waitOpenGrayFrame() {
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private void waitElementToBeText(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }



    private String actualUrl() {

        return getDriver().getCurrentUrl();
    }

    private String actualTitle() {

        return getDriver().getTitle();
    }


    @Test
    public void testClickGuideAndVerifyTitleANDUrl() {

        String expectedUrl = "https://openweathermap.org/guide";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedText = "Guide";

        openBaseURL();
        waitCloseForGrayFrame();
        click(SEARCH_GUIDE_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_HEADER_ROW, getWait5());

        Assert.assertEquals(getText(SEARCH_HEADER_ROW, getDriver()), expectedText);
        Assert.assertEquals(actualUrl(), expectedUrl);
        Assert.assertEquals(actualTitle(), expectedTitle);
    }

    @Test
    public void testVerifyWebsiteLogoClickableRedirectsStartPage() {

        openBaseURL();
        waitCloseForGrayFrame();
        click(SEARCH_LOGO, getWait5());
        waitOpenGrayFrame();
    }






}