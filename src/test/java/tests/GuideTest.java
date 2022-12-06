package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GuidePage;
import pages.RoadRiskAPIPage;

public class GuideTest extends BaseTest {

    @Test
    public void testHomeGuideNavigatesToBaseURL() {
        final String expectedURL = "https://openweathermap.org/";

        GuidePage guidePage = openBaseURL()
                .clickGuideMenu()
                .clickHomeGuide();

        waitForGrayContainerDisappeared();

        Assert.assertEquals(guidePage.getCurrentURL(), expectedURL);
    }

    @Test
    public void testRoadRiskAPILink_NavigatesTo_RoadRiskAPIPage(){

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
}
