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

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickAPIMenu();

        APIPage apiPage = new APIPage(getDriver());

        Assert.assertEquals(apiPage.getCurrentURL(), expectedURL);
    }

    @Test
    public void testMarketplaceMenuNavigatesToMarketplacePage() {
        final String expectedURL = "https://home.openweathermap.org/marketplace";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickMarketplaceMenu();

        switchToAnotherWindow(getDriver());

        Assert.assertEquals(getCurrentURL(), expectedURL);
    }

    @Test
    public void testLinkAndTitle_WhenGoingToGuideMenu() {
        final String expectedUrl = "https://openweathermap.org/guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        GuidePage guidePage = openBaseURL_ReturnMainPage().clickGuideMenu();

        Assert.assertEquals(guidePage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(guidePage.getTitle(), expectedTitle);
    }
}
