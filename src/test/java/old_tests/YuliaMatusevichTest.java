package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YuliaMatusevichTest extends BaseTest {
    final static By FACEBOOK_LINK = By.xpath("//div[@class = 'social']/a[1]");
    final static By TWITTER_LINK = By.xpath("//div[@class = 'social']/a[2]");
    final static By LINKEDIN_LINK = By.xpath("//div[@class = 'social']/a[3]");
    final static By MEDIUM_LINK = By.xpath("//div[@class = 'social']/a[4]");
    final static By TELEGRAM_ICON = By.xpath(
            "//div[@class = 'social']//img[@src= '/themes/openweathermap/assets/img/owm_icons/icon_telegram.png']");
    final static By BLOG_BUTTON =
            By.xpath("//div[@id = 'desktop-menu']//a[@href = 'https://openweather.co.uk/blog/category/weather']");

    final static By BLOG_CATEGORIES_PANEL = By.xpath("//ul[@id ='blog-categories']");
    final static By WEATHER_BLOG_CATEGORY = By.xpath(
            "//ul[@class = 'post-filters__items']//a[@href = '/blog/category/weather']");

    @Test
    public void testAllSocialMediaIconsVisibleAndClickable() {
        int expectedResult = 6;

        openBaseURL();

        int actualResult = findAllVisibleElements("//div[@class = 'social']/a", getWait10());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testFacebookIcon_IfCorrespondingFacebookWebpageOpens() {
        String expectedResult = "facebook.com";

        openBaseURL();
        scrollToPageBottom();
        click20(FACEBOOK_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }

    @Test
    public void testTwitterIcon_OnHomePage_WhenClickIcon() {
        String expectedResult = "twitter.com";

        openBaseURL();
        scrollToPageBottom();
        click20(TWITTER_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }

    @Test
    public void testLinkedInIcon_OnHomePage_WhenClickIcon() {
        String expectedResult = "linkedin.com";

        openBaseURL();
        scrollToPageBottom();
        click20(LINKEDIN_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }

    @Test
    public void testMediumIcon_OnHomePage_WhenClickIcon() {
        String expectedResult = "medium.com";

        openBaseURL();
        scrollToPageBottom();
        click20(MEDIUM_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }

    @Test
    public void testTelegramIcon_OnHomePage_WhenClickIcon() {
        String expectedResult = "t.me";

        openBaseURL();
        scrollToPageBottom();
        click20(TELEGRAM_ICON);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }

    @Test
    public void testBlogGategoriesName_whenSwitchToBlogPage() {
        final List<String> expectedCategoriesName =
                new ArrayList<>(Arrays.asList("ALL", "WEATHER", "AGRO", "PLATFORM", "TECHNOLOGIES", "TEAM&COMPANY"));

        openBaseURL();
        click20(BLOG_BUTTON);
        jumpToNextWindow();
        waitElementToBeVisible(BLOG_CATEGORIES_PANEL);
        List<WebElement> categories = getListOfElements(By.xpath("//a[@class = 'post-filters__link']"));
        List<String> actualCategoriesName = new ArrayList<>();
        for (WebElement category : categories) {
            actualCategoriesName.add(category.getText());
        }

        Assert.assertEquals(actualCategoriesName, expectedCategoriesName);
    }

    @Test
    public void testPaginationCountPages_WeatherCategoryBlogMenu() {
        final int navigationButtons = 2;
        final int expectedPagesQuantity = 10;

        openBaseURL();
        click20(BLOG_BUTTON);
        jumpToNextWindow();
        click20(WEATHER_BLOG_CATEGORY);
        scrollToPageBottom();
        int actualPagesQuantity =
                findAllVisibleElements(" //ul[@class = 'pagination']/li/*", getWait20()) - navigationButtons;

        Assert.assertEquals(actualPagesQuantity, expectedPagesQuantity);
    }
}

