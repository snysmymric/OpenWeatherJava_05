package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WeatherMapsPage;

public class WeatherMapsTest extends BaseTest {

    @Test
    public void testZoomLeafletControl () {
        String expectedZoomIN = "+";
        String expectedZoomOut = "-";
        String partialUrl = "http";

        WeatherMapsPage weatherMapsPage = openBaseURL_ReturnMainPage()
                .clickMapsMenu()
                .clickZoomInLoupe()
                .waitUntilUrlContainsHttp(partialUrl)
                .clickZoomOutLoupe();

        String actualZoomIN = weatherMapsPage.getZoomTextIn();
        String actualZoomOUT = weatherMapsPage.getZoomTextOut();

        Assert.assertEquals(actualZoomIN, expectedZoomIN);
        Assert.assertEquals(actualZoomOUT, expectedZoomOut);
    }
}