package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class BulkTest extends BaseTest {

    @Test
    public void testH2Header() {

        final String expectedH2Header = "How to use the service";

        String actualH2Header = openBaseURL()
                .scrollToBulkLink().clickBulks().getH2Header();

        Assert.assertEquals(actualH2Header, expectedH2Header);
    }
 }


