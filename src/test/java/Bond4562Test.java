import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

public class Bond4562Test extends BaseTest {

    private final String URL_BLOG_CAT_WEATHER = "https://openweather.co.uk/blog/category/weather";

    private final By SEARCH_GUIDE_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//a[@href='/guide']");
    private final By SEARCH_HEADER_ROW = By.xpath(
            "//div[@class='col-sm-7']/h1");
    private final By SEARCH_LOGO = By.xpath(
            "//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    private final By SEARCH_PRICING_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//a[@href='/price']");
    private final By SEARCH_BLOG_NEWS = By.xpath(
            "//div[@class='post-list']/div[@class='post']");
    private final By SEARCH_PAGINATION = By.xpath(
            "//div[@class='pagination-container']//a");
    private final By SEARCH_FOOTER = By.xpath(
            "//div[@id='footer-website']");


    @Test
    public void testClickGuideAndVerifyTitleANDUrl() {

        final String expectedUrl = "https://openweathermap.org/guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        final String expectedText = "Guide";

        openBaseURL();
        click(SEARCH_GUIDE_BUTTON);
        waitElementToBeVisible(SEARCH_HEADER_ROW);

        Assert.assertEquals(getText(SEARCH_HEADER_ROW), expectedText);
        Assert.assertEquals(getCurrentURL(), expectedUrl);
        Assert.assertEquals(getTitle(), expectedTitle);
    }

    @Test
    public void testWebsiteLogoClickableAndRedirectsStartPage() {

        openBaseURL();
        click(SEARCH_LOGO);
        waitForGrayContainerAppeared();

        Assert.assertEquals(getCurrentURL(), BASE_URL);
    }

    @Test
    public void testFromGuideToPricingForDesktopMenu() {

        final String expectedUrlGuide   = "https://openweathermap.org/guide";
        final String expectedTextGuide  = "Guide";

        final String expectedUrlPricing   = "https://openweathermap.org/price";
        final String expectedTextPricing  = "Pricing";

        openURL(expectedUrlGuide);
        click(SEARCH_PRICING_BUTTON);
        waitTextToBeChanged(SEARCH_HEADER_ROW, expectedTextGuide);

        Assert.assertEquals(getCurrentURL(), expectedUrlPricing);
        Assert.assertEquals(getText(SEARCH_HEADER_ROW), expectedTextPricing);
    }

    @Test
    public void testBlogSetNumberPageCategoryWeather() {

        openURL(URL_BLOG_CAT_WEATHER);
        List <WebElement> verifyChangeNews = getListOfElements(SEARCH_BLOG_NEWS);
        scrollToElement(SEARCH_FOOTER);
        getListOfElements(SEARCH_PAGINATION).get(0).click();

        Assert.assertTrue(getWait10().until(ExpectedConditions.stalenessOf(verifyChangeNews.get(0))));
    }



}