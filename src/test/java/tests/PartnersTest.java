package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PartnersTest extends BaseTest {

    @Test
    public void testPartnersPageHeader() {
        final String expectedHeader = "Partners and solutions";

        String actualHeader = openBaseURL_ReturnMainPage()
                .clickPartnersMenu()
                .getPageHeader();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testSeeOnTheWebsiteButtonNavigatesToApacheWebsite() {
        final String expectedURL = "https://camel.apache.org/components/next/weather-component.html";

        openBaseURL_ReturnMainPage()
                .clickPartnersMenu()
                .clickApacheCamelHyperLink()
                .clickSeeOnWebsiteButton()
                .switchToPartnerWindow();

        Assert.assertEquals(getExternalPageURL(), expectedURL);
    }
}
