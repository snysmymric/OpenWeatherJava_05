import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class YuliaMatusevichTest extends BaseTest {
    final static By FACEBOOK_LINK = By.xpath("//div[@class = 'social']/a[1]");
    final static By TWITTER_LINK = By.xpath("//div[@class = 'social']/a[2]");
    final static By LINKEDIN_LINK = By.xpath("//div[@class = 'social']/a[3]");
    final static By MEDIUM_LINK = By.xpath("//div[@class = 'social']/a[4]");
    final static By TELEGRAM_LINK = By.xpath("//div[@class = 'social']/a[5]");

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

}


