import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

@Ignore
public class Bond4562Test extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static String URL_BLOG_CAT_WEATHER = "https://openweather.co.uk/blog/category/weather";

    final static By SEARCH_GUIDE_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//a[@href='/guide']");
    final static By SEARCH_HEADER_ROW = By.xpath(
            "//div[@class='col-sm-7']/h1");
    final static By SEARCH_LOGO = By.xpath(
            "//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    final static By SEARCH_PRICING_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//a[@href='/price']");
    final static By SEARCH_BLOG_NEWS = By.xpath(
            "//div[@class='post-list']/div[@class='post']");
    final static By SEARCH_PAGINATION = By.xpath(
            "//div[@class='pagination-container']//a");
    final static By SEARCH_FOOTER = By.xpath(
            "//div[@id='footer-website']");


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

    private List<WebElement> getWebElements(By by) {

        return getDriver().findElements(by);
    }

    private void scrollTo(By by) {
        WebElement scroll = getDriver().findElement(by);
        new Actions(getDriver())
                .scrollToElement(scroll)
                .perform();
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
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

    @Test
    public void testFromGuideToPricingForDesktopMenu() {

        String expectedUrlGuide   = "https://openweathermap.org/guide";
        String expectedTitleGuide = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedTextGuide  = "Guide";

        String expectedUrlPricing   = "https://openweathermap.org/price";
        String expectedTitlePricing = "Pricing - OpenWeatherMap";
        String expectedTextPricing  = "Pricing";

        getDriver().get(expectedUrlGuide);
        Assert.assertEquals(actualTitle(), expectedTitleGuide);
        Assert.assertEquals(getText(SEARCH_HEADER_ROW, getDriver()), expectedTextGuide);

        click(SEARCH_PRICING_BUTTON,getWait5());

        getDriver().get(expectedUrlPricing);
        Assert.assertEquals(actualTitle(), expectedTitlePricing);
        Assert.assertEquals(getText(SEARCH_HEADER_ROW, getDriver()), expectedTextPricing);
    }

    @Test
    public void testBlogSetNumberPageCategoryWeather() {

        getDriver().get(URL_BLOG_CAT_WEATHER);
        List <WebElement> verifyChangeNews = getDriver().findElements(SEARCH_BLOG_NEWS);

        scrollTo(SEARCH_FOOTER);
        getWebElements(SEARCH_PAGINATION).get(0).click();
        getWait5().until(ExpectedConditions.stalenessOf(verifyChangeNews.get(0)));
    }






}