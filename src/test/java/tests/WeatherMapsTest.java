package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.top_menu.WeatherMapsPage;

import java.util.ArrayList;
import java.util.List;

public class WeatherMapsTest extends BaseTest {

    @Test
    public void testZoomInOutControl() {
        String expectedZoomIN = "+";
        String expectedZoomOut = "-";
        String partialUrl = "http";

        WeatherMapsPage weatherMapsPage = openBaseURL()
                .clickMapsMenu()
                .clickZoomInLoupe()
                .waitUntilUrlContains(partialUrl)
                .clickZoomOutLoupe();

        String actualZoomIN = weatherMapsPage.getZoomInText();
        String actualZoomOUT = weatherMapsPage.getZoomOutText();

        Assert.assertEquals(actualZoomIN, expectedZoomIN);
        Assert.assertEquals(actualZoomOUT, expectedZoomOut);
    }

    @Test
    public void testWeatherControlLayersText() {
        List<String> expectedLayersTexts = new ArrayList<>();
        expectedLayersTexts.add("Temperature");
        expectedLayersTexts.add("Pressure");
        expectedLayersTexts.add("Wind speed");
        expectedLayersTexts.add("Clouds");
        expectedLayersTexts.add("Global Precipitation");

        List<String> actualLayersTexts =
                openBaseURL()
                        .clickMapsMenu()
                        .getMenusTexts();

        Assert.assertTrue(actualLayersTexts.size() > 0);
        Assert.assertEquals(actualLayersTexts, expectedLayersTexts);
    }

    @Test
    public void testButtonLoop_MapPage() {
        String locationROME = "Rome, Italy";

         WeatherMapsPage weatherMapsPage = openBaseURL()
                .clickMapsMenu()
                .clickNominatimSearchButton();

        Assert.assertTrue(weatherMapsPage.isLoopDisplayBlockDisplayed());

        String oldUrl = String.valueOf(weatherMapsPage
                .clickLoopDisplayBlock()
                .inputSearchCriteriaIntoSearchFieldAndEnter(locationROME));

        Assert.assertNotEquals(oldUrl, getDriver().getCurrentUrl());
    }
}
