package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.TechnologyPage;
import pages.WeatherDashboardPage;

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

    @Test
    public void testWeatherDashboardLinkNabigatesToWeatherDashboardPage() {
        final String expectedResult = "https://openweathermap.org/weather-dashboard";

        WeatherDashboardPage weatherDashboardPage = openBaseURL_ReturnMainPage()
                .scrollToWeatherDashboardFooterMenu()
                .clickWeatherDashboardFooterMenu();

        Assert.assertEquals(weatherDashboardPage.getCurrentURL(), expectedResult);
    }

    @Test
    public void testAboutUsFooterLinkNavigatesToAboutUsPage() {
        final String expectedUrl = "https://openweathermap.org/about-us";

        String actualUrl = openBaseURL_ReturnMainPage()
                .scrollToAboutUsFooterMenu()
                .clickOnAboutUsFooterMenu()
                .waitForAboutUsPageHeaderBeVisible()
                .getCurrentURL();

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testWidgetsFooterLinkNavigatesToWidgetsPage() {
        final String expectedURL = "https://openweathermap.org/widgets-constructor";

        final String actualURL = openBaseURL_ReturnMainPage()
                .scrollToWeatherDashboardFooterMenu()
                .clickWidgetsPageFooterMenu()
                .getCurrentURL();

        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test
    public void testSocialPanelExistsAndHasIcons() {
        final int expectedQuantity = 6;

        MainPage mainPage = openBaseURL_ReturnMainPage();

        Assert.assertTrue(mainPage.isSocialPanelDisplayed());
        Assert.assertEquals(mainPage.getSocialPanelSize(), expectedQuantity);
    }
}
