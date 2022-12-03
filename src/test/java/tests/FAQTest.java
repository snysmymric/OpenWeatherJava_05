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

        mainPage.clickSupportMenu();
        mainPage.clickFAQSupportSubmenu();

        FAQPage FAQPage = new FAQPage(getDriver());

        Assert.assertEquals(FAQPage.getFrequentlyAskedQuestionsHeader(), expectedResult);
    }

    @Test
    public void testTitle() {
        final String expectedTitle = "Frequently Asked Questions - OpenWeatherMap";

        String actualTitle = openBaseURL_ReturnMainPage()
                .clickSupportMenu()
                .clickFAQSupportSubmenu()
                .getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
