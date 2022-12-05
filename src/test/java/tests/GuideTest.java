package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GuidePage;

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
}
