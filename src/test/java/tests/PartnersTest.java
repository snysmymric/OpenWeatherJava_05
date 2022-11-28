package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PartnersTest extends BaseTest {

    @Test
    public void testPartnersAndSolutionsLinkPage() {

        final String expectedCurrentURL = "https://openweathermap.org/examples";

        String currentURL =
                openBaseURL_ReturnMainPage()
                        .clickPartnersMenu()
                        .getCurrentURL();

        Assert.assertEquals(currentURL, expectedCurrentURL);
    }

    @Test
    public void testBreadcrumbTitle() {

        final String expectedTitle = "Partners and solutions - OpenWeatherMap";

        String title = openBaseURL_ReturnMainPage()
                .clickPartnersMenu()
                .getTitle();

        Assert.assertEquals(title, expectedTitle);
    }

    @Test
    public void testBreadcrumbTitle_InnerText() {

        final String expectedTitle = "Partners and solutions";

        String innerText = openBaseURL_ReturnMainPage()
                .clickPartnersMenu()
                .getText_InnerText();

        Assert.assertEquals(innerText, expectedTitle);
    }

    @Test
    public void testActiveHyperlinkButton_SeeOnTheWebsite() {

        final String expectedURL = "https://camel.apache.org/components/next/weather-component.html";

        openBaseURL_ReturnMainPage()
                .clickPartnersMenu()
                .clickApacheCamelHyperLink()
                .clickSeeOnWebsiteButton()
                .switchToPartnerWindow();

        Assert.assertEquals(getExternalPageURL(), expectedURL);
    }
}
