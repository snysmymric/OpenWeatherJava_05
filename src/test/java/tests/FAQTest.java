package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FAQPage;
import pages.MainPage;

public class FAQTest extends BaseTest {

    @Test
    public void testFAQButtonIsClickable() {
        final String expectedResult = "Frequently Asked Questions";

        openBaseURL_ReturnMainPage();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSupportButton();
        mainPage.clickFAQButton();

        FAQPage FAQPage = new FAQPage(getDriver());

        Assert.assertEquals(FAQPage.getTextBreadCrumbsFAQ(), expectedResult);
    }
}
