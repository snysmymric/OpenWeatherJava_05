package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.top_menu.PricePage;

import java.util.Arrays;
import java.util.List;

public class PriceTest extends BaseTest {

    @Test
    public void testTransparentButtonsAmount() {
        final int expectedTransparentButtons = 19;

        int actualTransparentButtons = openBaseURL()
                .clickPricingMenu()
                .getTransparentButtonsAmount();

        Assert.assertEquals(actualTransparentButtons, expectedTransparentButtons);
    }

    @Test
    public void testH1HeaderPricingPage() {
        final String expectedHeader = "Pricing";

        String actualHeader = openBaseURL()
                .clickPricingMenu()
                .getH1Header();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testCurrentWeatherAndForecastsCollectionsNames() {
        final List<String> expectedNames = Arrays.asList("Free", "Startup", "Developer", "Professional", "Enterprise");

        List<String> collectionsNames = openBaseURL()
                .clickPricingMenu()
                .getCollectionsNames();

        Assert.assertEquals(collectionsNames, expectedNames);
    }

    @Test
    public void testAlertsH2HeaderPricingPage() {
        final String expectedAlertsH2Header = "Special products";

        String actualAlertsH2Header = openBaseURL()
                .clickPricingMenu()
                .getAlertsH2Header();

        Assert.assertEquals(actualAlertsH2Header, expectedAlertsH2Header);
    }

    @Test
    public void testAlertsH4HeadersPricingPage() {
        final List<String> expectedAlertsH4 = Arrays.asList(
                "Solar Radiation API", "Solar Radiation API - Historical data",
                "Global Weather Alerts Push notifications", "Road Risk API (advanced configuration)",
                "Global Precipitation Map - Forecast and historical data", "Weather Maps 2.0 with 1-hour step");

        List<String> actualAlertsH4 = openBaseURL()
                .clickPricingMenu()
                .getAlertsH4Headers();

        Assert.assertEquals(actualAlertsH4, expectedAlertsH4);
    }

    @Test
    public void testAlertsPriceByRequestAmount() {
        final int expectedByRequestAmount = 4;
        final String oldSubHeader = "By request";

        int actualByRequestAmount = openBaseURL()
                .clickPricingMenu()
                .waitGetRequestToBeChanged(oldSubHeader)
                .getAlertsByRequestAmount();

        Assert.assertEquals(actualByRequestAmount, expectedByRequestAmount);
    }

    @Test
    public void testTransparentButtonsLabels() {
        final List<String> expectedHeaderButtons = Arrays.asList(
                "Get API key", "Subscribe", "Subscribe", "Subscribe",
                "Subscribe", "Get access", "Get access", "Get access", "Get access", "Get access",
                "Get access", "Get", "Get", "Subscribe", "Subscribe", "Get", "Get access",
                "Get access", "Learn more");

        List<String> actualHeaderButtons = openBaseURL()
                .clickPricingMenu()
                .getTransparentButtonsLabels();

        Assert.assertEquals(actualHeaderButtons, expectedHeaderButtons);
    }

    @Test
    public void testH2HeadersPricingPage() {
        final int expectedAmountH2Headers = 7;
        final List<String> expectedH2Headers = List.of(
                "\"One Call by Call\" subscription plan", "Professional collections", "Current weather and forecasts collection",
                "Special products","Historical weather collection", "Special products","Free data for students");

        List<String> actualH2Headers = openBaseURL()
                .clickPricingMenu()
                .getH2HeadersPricingPage();

        PricePage pricePage = new PricePage(getDriver());

        int actualAmountH2Headers = pricePage
                .getH2HeadersAmountPricingPage();

        Assert.assertEquals(actualH2Headers, expectedH2Headers);
        Assert.assertEquals(actualAmountH2Headers, expectedAmountH2Headers);
    }

    @Test
    public void testDetailedPricingButtons() {
        final int expectedDetailedPricingButtons  = 2;
        final List<String> expectedLabels = Arrays.asList("Detailed pricing", "Detailed pricing");

        PricePage pricePage = openBaseURL().clickPricingMenu();

        Assert.assertEquals(pricePage.getDetailedPricingButtonsAmount(), expectedDetailedPricingButtons );
        Assert.assertEquals(pricePage.getDetailedPricingButtonsLabels(),expectedLabels);
    }
}


