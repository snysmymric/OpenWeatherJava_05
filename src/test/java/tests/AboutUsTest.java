package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class AboutUsTest extends BaseTest {

    @Test
    public void testWhereToHasActiveFourOptions() {

        List <String> fourOptions = Arrays.asList("Buy by Subscription", "Buy in the Marketplace",
                "Products documentation", "News and Updates");

        List<String> actualOptionsUnderWhereTo =
                openBaseURL().scrollToFooterMenu().clickOnAboutUsFooterMenu()
                .scrollToWhereTo()
                .waitAllOptionsAreVisibleAndClickable()
                .getOptionsText();

        Assert.assertEquals(actualOptionsUnderWhereTo, fourOptions);
    }
}
