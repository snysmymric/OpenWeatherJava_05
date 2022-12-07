package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BulkPage;


public class BulkTest extends BaseTest {

    @Test
    public void testH2Header() {

        final String expectedH2Header = "How to use the service";

        String actualH2Header = openBaseURL()
                .scrollToBulkLink().clickBulks().getH2Header();

        Assert.assertEquals(actualH2Header, expectedH2Header);
    }

    @Test
    public void testBulkFilesAPIRequests() {

        final String expextedCurrentAndForecastBulkRequest =
                "https://bulk.openweathermap.org/snapshot/{BULK_FILE_NAME}?appid={API key}";
        final String expectedSevenDaysArchiveBulkRequest =
                "https://bulk.openweathermap.org/archive/{BULK_FILE_NAME}?appid={API key}";

        BulkPage bulkPage = openBaseURL()
                            .scrollToBulkLink()
                            .clickBulks();
        String currentAndForecastBulkRequest = bulkPage.getBulkFilesRequests().get(0);
        String sevenDaysArchiveBulkRequest = bulkPage.getBulkFilesRequests().get(1);

        Assert.assertEquals(currentAndForecastBulkRequest,expextedCurrentAndForecastBulkRequest);
        Assert.assertEquals(sevenDaysArchiveBulkRequest,expectedSevenDaysArchiveBulkRequest);
    }
}
