package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TechnologyPage;

public class FooterMenuTest extends BaseTest {

    @Test
    public void testOurTechnologyFooterLinkNavigatesToTechnologyPage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedURL = "https://openweathermap.org/technology";
        final String expectedTitle = "Weather model - OpenWeatherMap";

        TechnologyPage technologyPage = openBaseURL_ReturnMainPage()
                .scrollToOurTechnologyFooterMenu()
                .clickOurTechnologyFooterMenu();

        Assert.assertNotEquals(basePageTitle, technologyPage.getTitle());
        Assert.assertEquals(technologyPage.getCurrentURL(), expectedURL);
        Assert.assertEquals(technologyPage.getTitle(), expectedTitle);
    }

    @Test
    public void testPrivacyPolicyFooterLinkNavigatesToPrivacyPolicyWeb() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedURL = "https://openweather.co.uk/privacy-policy";
        final String expectedTitle = "Privacy policy - OpenWeatherMap";

        openBaseURL_ReturnMainPage()
                .scrollToPrivacyPolicyFooterMenu()
                .clickPrivacyPolicyFooterMenu();

        switchToAnotherWindow(getDriver());

        Assert.assertNotEquals(basePageTitle, getExternalPageTitle());
        Assert.assertEquals(getExternalPageURL(), expectedURL);
        Assert.assertEquals(getExternalPageTitle(), expectedTitle);
    }

}
