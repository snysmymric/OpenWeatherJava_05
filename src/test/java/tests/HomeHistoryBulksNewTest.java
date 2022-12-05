package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomeHistoryBulksNewTest extends BaseTest {

    @Test
    public void testUncheckAllCheckmarkInTheListOfWeatherParameters() {

        boolean allCheckmarksAreNotSelected = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickHistoryBulkMenu()
                .clickWeatherParametersButton()
                .uncheckAllWeatherParameters()
                .allCheckmarksAreNotSelectedInTheWeatherParameters();

        Assert.assertTrue(allCheckmarksAreNotSelected);
    }
}
