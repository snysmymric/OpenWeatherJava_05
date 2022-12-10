package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomeZipCodeDataNewPage;

public class HomeMarketplaceTest extends BaseTest {

    @Test
    public void testAllButtonsAreVisibleAndClickable() {

        final boolean areAllButtonsVisibleAndClickable =
                openBaseURL()
                        .clickMarketplaceMenu()
                        .switchToMarketplaceWindow()
                        .areAllButtonsVisibleAndClickable();

        Assert.assertTrue(areAllButtonsVisibleAndClickable);
    }

    @Test
    public void test_WeatherDataByStateLink_NavigatesTo_HomeZipCodeDataPage() {
        final String expectedURL = "https://home.openweathermap.org/zip_code_data/new";
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedTitle = "Marketplace: History Bulk, History Forecast Bulk, Historical Weather Data by State for all ZIP codes, USA - OpenWeather";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu();

        Assert.assertEquals(homeZipCodeDataNewPage.getCurrentURL(), expectedURL);
        Assert.assertNotEquals(basePageTitle, homeZipCodeDataNewPage.getTitle());
        Assert.assertEquals(homeZipCodeDataNewPage.getTitle(), expectedTitle);
    }
}
