package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.APIPage;

public class TopMenuTest extends BaseTest {
    @Test
    public void testAPIMenuNavigatesToAPIPage() {
        final String expectedURL = "https://openweathermap.org/api";

        openBaseURL();

        APIPage apiPage = new APIPage(getDriver());

        apiPage.clickAPIMenu();

        Assert.assertEquals(apiPage.getCurrentURL(), expectedURL);
    }
}
