package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.top_menu.PricePage;

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

    @Test
    public void testH2Headers() {
        final int expectedAmountH2Headers = 7;
        final List<String> expectedH2HeadersList = List.of(
                "\"One Call by Call\" subscription plan", "Professional collections", "Current weather and forecasts collection",
                "Special products","Historical weather collection", "Special products","Free data for students");

        List<String> actualH2HeadersList = openBaseURL()
                .clickPricingMenu()
                .getListPricingPageH2Headers();

        PricePage pricePage = new PricePage(getDriver());

        int actualAmountH2Headers = pricePage
                .getListPricingPageH2HeadersSize();

        Assert.assertEquals(actualH2HeadersList, expectedH2HeadersList);
        Assert.assertEquals(actualAmountH2Headers, expectedAmountH2Headers);
    }

    @Test
    public void testBtnLikeBtnOrange_AmountOfOrangeButtons() {
        final int expectedOrangeButtons = 2;
        final List<String> expectedHeaderButtons = Arrays.asList("Detailed pricing", "Detailed pricing");

        PricePage pricePage = openBaseURL().clickPricingMenu();

        Assert.assertEquals(pricePage.getOrangeButtonsAmount(), expectedOrangeButtons);
        Assert.assertEquals(pricePage.getHeadersOrangeButtons(),expectedHeaderButtons);
    }
}


