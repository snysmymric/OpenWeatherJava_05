package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.APIPage;
import pages.GuidePage;
import pages.MainPage;

public class TopMenuTest extends BaseTest {
    @Test
    public void testAPIMenuNavigatesToAPIPage() {
        final String expectedURL = "https://openweathermap.org/api";

        openBaseURL();

        APIPage apiPage = new APIPage(getDriver());

        apiPage.clickAPIMenu();

        Assert.assertEquals(apiPage.getCurrentURL(), expectedURL);
    }

    @Test
    public void testLinkAndTitle_WhenGoingToGuideMenu() {
        final String expectedUrl = "https://openweathermap.org/guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL_ReturnMainPage();

        MainPage mainPage = new MainPage(getDriver());
        GuidePage guidePage = mainPage.clickGuideMenu();

        Assert.assertEquals(guidePage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(guidePage.getTitle(), expectedTitle);
    }
}
