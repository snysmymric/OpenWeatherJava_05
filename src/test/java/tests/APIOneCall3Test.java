package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.APIOneCall3Page;

public class APIOneCall3Test extends BaseTest {

    @Test
    public void testAPICallsParameteres() {

        final String expectedParametres ="Parameters";
        final String expectedFirstParameter = "lat, lon required Geographical coordinates (latitude, longitude). "
                + "If you need the geocoder to automatic convert city names and zip-codes "
                + "to geo coordinates and the other way around, please use our Geocoding API.";
        final String expectedSecondParameter = "appid required Your unique API key (you can always find it on your account page under the "
                + "\"API key\" tab)";
        final String expectedThirdParameter = "exclude optional By using this parameter you can exclude some parts "
                + "of the weather data from the API response. It should be a comma-delimited list (without spaces).\n"
                + "Available values:\ncurrent\nminutely\nhourly\ndaily\nalerts";
        final String expectedFourthParameter = "units optional Units of measurement. standard, metric and imperial units "
                + "are available. If you do not use the units parameter, standard units will be applied by default. Learn more";
        final String expectedFifthParameter = "lang optional You can use the lang parameter to get the output in your language. Learn more";

        APIOneCall3Page apiOneCall3Page = openBaseURL()
                .clickAPIMenu()
                .clickAPIDocButton();

        String actualParameteres = apiOneCall3Page.getAPIOneCall3Parametrs().get(0);
        Assert.assertEquals(actualParameteres,expectedParametres);

        String actualFirstParameter = apiOneCall3Page.getAPIOneCall3Parametrs().get(1);
        Assert.assertEquals(actualFirstParameter,expectedFirstParameter);

        String actualSecondParameter = apiOneCall3Page.getAPIOneCall3Parametrs().get(2);
        Assert.assertEquals(actualSecondParameter,expectedSecondParameter);

        String actualThirdParameter = apiOneCall3Page.getAPIOneCall3Parametrs().get(3);
        Assert.assertEquals(actualThirdParameter,expectedThirdParameter);

        String actualFourthParameter = apiOneCall3Page.getAPIOneCall3Parametrs().get(4);
        Assert.assertEquals(actualFourthParameter,expectedFourthParameter);

        String actualFifthParameter = apiOneCall3Page.getAPIOneCall3Parametrs().get(5);
        Assert.assertEquals(actualFifthParameter,expectedFifthParameter);
    }
}
