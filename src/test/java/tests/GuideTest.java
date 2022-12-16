package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.top_menu.GuidePage;
import pages.RoadRiskAPIPage;

public class GuideTest extends BaseTest {

    @Test
    public void testHomeGuideNavigatesToBaseURL() {
        final String expectedURL = "https://openweathermap.org/";

        GuidePage guidePage = openBaseURL()
                .clickGuideMenu()
                .clickHomeGuideLink();

        waitForGrayContainerDisappeared();

        Assert.assertEquals(guidePage.getCurrentURL(), expectedURL);
    }

    @Test
    public void testDedicatedWeatherSolarRadiationApiIsClickableAndLinkIsOpened() {
        final String expectedUrl = "https://openweathermap.org/api/solar-radiation";
        final String expectedTitle = "Solar radiation API - OpenWeatherMap";

        GuidePage solarRadiationPage = openBaseURL().clickGuideMenu().scrollToDedicatedWeatherProducts().
                clickSolarRadiationLink();

        Assert.assertEquals(solarRadiationPage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(solarRadiationPage.getTitle(), expectedTitle);
    }

    @Test
    public void testRoadRiskAPILink_NavigatesTo_RoadRiskAPIPage() {

        final String expectedURL = "https://openweathermap.org/api/road-risk";
        final String expectedTitle = "Road Risk - OpenWeatherMap";

        RoadRiskAPIPage roadRiskAPIPage = new RoadRiskAPIPage(getDriver());

        String oldURL = openBaseURL()
                .clickGuideMenu()
                .getCurrentURL();

        new GuidePage(getDriver()).clickRoadRiskAPILink();
        Assert.assertNotEquals(oldURL, getDriver().getCurrentUrl());

        String actualURL = roadRiskAPIPage.getCurrentURL();
        String actualTitle = roadRiskAPIPage.getTitle();

        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testOrangeButtonLearnMoreAmount() {
        final int expectedOrangeButtonLearnMoreAmount = 5;

        int actualOrangeButtonLearnMoreAmount = openBaseURL()
                .clickGuideMenu()
                .getButtonsLearnMoreAmount();

        Assert.assertEquals(actualOrangeButtonLearnMoreAmount, expectedOrangeButtonLearnMoreAmount);
    }
}
