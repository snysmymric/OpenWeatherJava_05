package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
