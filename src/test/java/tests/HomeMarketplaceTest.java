package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeMarketplaceTest extends BaseTest {

    @Test
    public void testAllButtonsAreVisibleAndClickable() {

        final boolean areAllButtonsVisibleAndClickable =
                openBaseURL_ReturnMainPage()
                        .clickMarketplaceMenu()
                        .switchToMarketplaceWindow()
                        .areAllButtonsVisibleAndClickable();

        Assert.assertTrue(areAllButtonsVisibleAndClickable);
    }
}
