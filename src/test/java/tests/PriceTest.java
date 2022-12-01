package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PricePage;

public class PriceTest extends BaseTest {

    @Test
    public void testBtnBlock_AmmountOfTransparentButtons() {
        final int expectedTransparentButtons = 19;

        int actualTransparentButtons = openBaseURL_ReturnMainPage()
                .clickPricingMenu()
                .getTransparentButtonsAmount();

        Assert.assertEquals(actualTransparentButtons, expectedTransparentButtons);
    }
}
