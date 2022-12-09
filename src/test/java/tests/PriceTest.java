package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PriceTest extends BaseTest {

    @Test
    public void testBtnBlock_AmountOfTransparentButtons() {
        final int expectedTransparentButtons = 19;

        int actualTransparentButtons = openBaseURL()
                .clickPricingMenu()
                .getTransparentButtonsAmount();

        Assert.assertEquals(actualTransparentButtons, expectedTransparentButtons);
    }

    @Test
    public void testH1BreadcrumbTitle_WhenOpenPricingPage() {
        final String expectedHeader = "Pricing";

        String actualHeader = openBaseURL()
                .clickPricingMenu()
                .getHeaderText();

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
    public void testH2AlertsAnchorEl_WhenOpenPricingPage() {
        final String expectedAlertsH2Header = "Special products";

        String actualAlertsH2Header = openBaseURL()
                .clickPricingMenu()
                .getAlertsH2Header();

        Assert.assertEquals(actualAlertsH2Header, expectedAlertsH2Header);
    }

    @Test
    public void testSectionIdAlertsAnchorEl_SubHeadersOfSection() {
        final List<String> expectedAlertsH4 = Arrays.asList("Solar Radiation API", "Solar Radiation API - Historical data",
                "Global Weather Alerts Push notifications", "Road Risk API (advanced configuration)",
                "Global Precipitation Map - Forecast and historical data", "Weather Maps 2.0 with 1-hour step");

        List<String> actualAlertsH4 = openBaseURL()
                .clickPricingMenu()
                .getSectionAlertsSubHeadersOfFirstColumn();

        Assert.assertEquals(actualAlertsH4, expectedAlertsH4);
    }

    @Test
    public void testSectionIdAlertsAnchorEl_countByRequest() {
        final int expectedByRequestAmount = 4;
        final String oldSubHeader = "By request";

        int actualByRequestAmount = openBaseURL()
                .clickPricingMenu()
                .waitGetRequestToBeChanged(oldSubHeader)
                .getByRequestSubHeadersAmount();

        Assert.assertEquals(actualByRequestAmount, expectedByRequestAmount);
    }

    @Test
    public void testBtnBlock_HeaderOfTransparentButtons() {
        final List<String> expectedHeaderButtons = Arrays.asList("Get API key", "Subscribe", "Subscribe", "Subscribe",
                "Subscribe", "Get access", "Get access", "Get access", "Get access", "Get access",
                "Get access", "Get", "Get", "Subscribe", "Subscribe", "Get", "Get access",
                "Get access", "Learn more");

        List<String> actualHeaderButtons = openBaseURL()
                .clickPricingMenu()
                .getHeadersTransparentButtons();

        Assert.assertEquals(actualHeaderButtons, expectedHeaderButtons);
    }
}
