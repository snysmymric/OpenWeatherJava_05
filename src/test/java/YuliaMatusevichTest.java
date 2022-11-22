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
    final static By TELEGRAM_LINK = By.xpath("//div[@class = 'social']/a[5]");
    final static By BLOG_BUTTON =
            By.xpath("//div[@id = 'desktop-menu']//a[@href = 'https://openweather.co.uk/blog/category/weather']");

    final static By BLOG_CATEGORIES_PANEL = By.xpath("//ul[@id ='blog-categories']");

    @Test
    public void testAllSocialMediaIconsVisibleAndClickable (){
        int expectedResult = 6;

        openBaseURL();

        int actualResult = findAllVisibleElements("//div[@class = 'social']/a",getWait10());

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testFacebookIcon_IfCorrespondingFacebookWebpageOpens(){
        String expectedResult ="facebook.com";

        openBaseURL();
        scrollToPageBottom();
        click20(FACEBOOK_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }
    @Test
    public void testTwitterIcon_OnHomePage_WhenClickIcon(){
        String expectedResult ="twitter.com";

        openBaseURL();
        scrollToPageBottom();
        click20(TWITTER_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }
    @Test
    public void testLinkedInIcon_OnHomePage_WhenClickIcon(){
        String expectedResult ="linkedin.com";

        openBaseURL();
        scrollToPageBottom();
        click20(LINKEDIN_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }
    @Test
    public void testMediumIcon_OnHomePage_WhenClickIcon(){
        String expectedResult ="medium.com";

        openBaseURL();
        scrollToPageBottom();
        click20(MEDIUM_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }
    @Ignore
    @Test
    public void testTelegramIcon_OnHomePage_WhenClickIcon(){
        String expectedResult ="t.me";

        openBaseURL();
        scrollToPageBottom();
        click20(TELEGRAM_LINK);
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
        jumpToNextWindow();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertTrue((actualResult.contains(expectedResult)));
    }
    @Test
    public void testBlogGategoriesName_whenSwitchToBlogPage(){
        final List<String> expectedCategoriesName =
                new ArrayList<> (Arrays.asList("ALL", "WEATHER", "AGRO", "PLATFORM", "TECHNOLOGIES", "TEAM&COMPANY"));

        openBaseURL();
        click20(BLOG_BUTTON);
        jumpToNextWindow();
        waitElementToBeVisible(BLOG_CATEGORIES_PANEL);
        List<WebElement> categories = getListOfElements(By.xpath("//a[@class = 'post-filters__link']"));
        List<String> actualCategoriesName = new ArrayList<>();
        for(WebElement category : categories){
            actualCategoriesName.add(category.getText());
        }

        Assert.assertEquals(actualCategoriesName, expectedCategoriesName);
    }

}


