package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest extends BaseTest {

    @Test
    public void testCheckAllOrangeButtons() {
        final int expectedAmountOfButtons = 30;

        int actualAmountOfButtons = openBaseURL()
                .clickAPIMenu()
                .getOrangeButtonsAmount();

        Assert.assertEquals(actualAmountOfButtons, expectedAmountOfButtons);
    }

    @Test
    public void testAPIPageHeader() {
        final String expectedHeader = "Weather API";

        String header = openBaseURL()
                .clickAPIMenu()
                .getPageHeader();

        Assert.assertEquals(header,expectedHeader);
    }
}
