package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PartnersTest extends BaseTest {

    @Test
    public void testPartnersMenuNavigatesToPartnersPage() {

        final String expectedCurrentURL = "https://openweathermap.org/examples";

        String currentURL =
                openBaseURL_ReturnMainPage()
                        .clickPartnersMenu()
                        .getCurrentURL();

        Assert.assertEquals(currentURL, expectedCurrentURL);
    }

    @Test
    public void testPartnersPageTitle() {

        final String expectedTitle = "Partners and solutions - OpenWeatherMap";

        String title = openBaseURL_ReturnMainPage()
                .clickPartnersMenu()
                .getTitle();

        Assert.assertEquals(title, expectedTitle);
    }

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
