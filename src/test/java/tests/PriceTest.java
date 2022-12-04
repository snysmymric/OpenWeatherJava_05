package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PriceTest extends BaseTest {

    @Test
    public void testBtnBlock_AmountOfTransparentButtons() {
        final int expectedTransparentButtons = 19;

        int actualTransparentButtons = openBaseURL_ReturnMainPage()
                .clickPricingMenu()
                .getTransparentButtonsAmount();

        Assert.assertEquals(actualTransparentButtons, expectedTransparentButtons);
    }

    @Test
    public void testH1BreadcrumbTitle_WhenOpenPricingPage() {
        String expectedHeader = "Pricing";

        String actualHeader = openBaseURL_ReturnMainPage()
                .clickPricingMenu()
                .getHeaderText();

        Assert.assertEquals(actualHeader, expectedHeader);
    }
}
