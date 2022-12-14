package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.top_menu.FAQPage;
import pages.MainPage;

public class FAQTest extends BaseTest {

    @Test
    public void testFAQButtonIsClickable() {
        final String expectedResult = "Frequently Asked Questions";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSupportMenu();
        mainPage.clickFAQSupportSubmenu();

        FAQPage FAQPage = new FAQPage(getDriver());

        Assert.assertEquals(FAQPage.getFrequentlyAskedQuestionsHeader(), expectedResult);
    }

    @Test
    public void testTitle() {
        final String expectedTitle = "Frequently Asked Questions - OpenWeatherMap";

        String actualTitle = openBaseURL()
                .clickSupportMenu()
                .clickFAQSupportSubmenu()
                .getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void test_CheckH3HeadersAmount() {
        final int expectedH3HeadersAmount = 10;

        int actualH3HeadersAmount = openBaseURL().
                clickSupportMenu().
                clickFAQSupportSubmenu().
                getH3HeadersAmount();

        Assert.assertEquals(actualH3HeadersAmount, expectedH3HeadersAmount);
    }

    @Test
    public void testAllFAQInnerDescriptionIsDisplayed() {
        final int expectedOpenedFAQAmount = 59;

        int actualOpenedFAQAmount = openBaseURL()
                .clickSupportMenu()
                .clickFAQSupportSubmenu()
                .getOpenedFAQAmount();

        Assert.assertEquals(actualOpenedFAQAmount, expectedOpenedFAQAmount);
    }
}
